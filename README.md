# propio
# Starter pipeline
# Start with a minimal pipeline that you can customize to build and deploy your code.
# Add steps that build, run tests, deploy, and more:
# https://aka.ms/yaml

parameters:

  - name: buildDeploy
    displayName: Build/Deploy
    type: string
    default: "build"
    values:
      - build
      - deploy

  - name: env
    displayName: Environment
    type: string
    default: "dev"
    values:
      - loc
      - hyb
      - dev
      - pre
      - pro
      - hub
      - pro2

resources:
  repositories:
  - repository: iacRepo
    type: git
    name: GSC-AZURECLOUD/gsc.global.spn
    ref: TestIntegraciones
    trigger:
      branches:
        include:
        - TestIntegraciones

  - repository: toolsRepo
    type: git
    name: OE2020_CORE/OE2020_CORE_TOOLS
    ref: TestIntegraciones
    trigger:
      branches:
        include:
        - TestIntegraciones
      paths:
        include:
        - tools/spn/*


  - repository: templatesRepo
    type: git
    name: GSC-AZURECLOUD/gsc.global.iac.templates
    ref: master
    trigger:
      branches:
        include:
        - master
      paths:
        include:
        - azdevops/arm/spn-jobs/*
        - azdevops/build/spn-build/*
        - azdevops/orch/spn-deploy/*

# schedules:
# - cron: "0 5 * * *"
#   displayName: Daily midnight build
#   branches:
#     include:
#     - master
#   always: true

variables:
  scheduledEnvs: "loc,hyb,dev,pre,pro,hub,pro2"


  azureDevOpsRepository: 'gsc.global.spn'
  azureDevOpsRepositoryTools: 'OE2020_CORE_TOOLS'
  azureDevOpsRepositoryTemplates: 'gsc.global.iac.templates'

stages:
  - ${{ if eq( variables['Build.Reason'], 'Schedule' ) }}:
    - ${{ each scheduledEnv in split(variables.scheduledEnvs, ',') }}:
      - template: '/azdevops/orch/spn-deploy/1/alert-deploy-stage.yml@templatesRepo'
        parameters:
          env: '${{ scheduledEnv }}'
          azureDevOpsProjectID: '$(System.TeamProjectId)'
          azureDevOpsBuildID: '$(System.DefinitionId)'
          buildDeploy: 'deploy'
  
  - ${{ if not(eq( variables['Build.Reason'], 'Schedule' )) }}:
    - template: '/azdevops/build/spn-build/1/build-stage.yml@templatesRepo'
      parameters:
        azureDevOpsRepository: '${{ variables.azureDevOpsRepository }}'
        azureDevOpsRepositoryTools: '${{ variables.azureDevOpsRepositoryTools }}'
        azureDevOpsRepositoryTemplates: '${{ variables.azureDevOpsRepositoryTemplates }}'
        buildDeploy: '${{ parameters.buildDeploy }}'

    - template: '/azdevops/orch/spn-deploy/1/alert-deploy-stage.yml@templatesRepo'
      parameters:
        env: '${{ parameters.env }}'
        azureDevOpsProjectID: '$(System.TeamProjectId)'
        azureDevOpsBuildID: '$(System.DefinitionId)'
        buildDeploy: '${{ parameters.buildDeploy }}'
