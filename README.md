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
      - dev
  
  - name: buildID
    displayName: Build ID for checkouts
    type: string
    default: "latest"

resources:
  repositories:
  - repository: iacRepo
    type: git
    name: OE2020_FINREP/oe2020finrepdata.flyway.snowstructure
    ref: master
    trigger:
      branches:
        include:
        - master
      paths:
        include:
        - flyway/dev/*
  
  - repository: iacConfigRepo
    type: git
    name: OE2020_CORE/OE2020_CORE_FLYWAY_CONFIG
    ref: master
    trigger:
      branches:
        include:
        - master
      paths:
        include:
        - oe2020flywayconfig/flywayoe2020config/finrep/dev/*

  - repository: toolsRepo
    type: git
    name: OE2020_CORE/OE2020_CORE_TOOLS
    ref: master
    trigger:
      branches:
        include:
        - master
      paths:
        include:
        - tools/flyway/*


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
        - azdevops/arm/flyway-jobs/*
        - azdevops/build/flyway-tools/*
        - azdevops/orch/flyway-deploy/*


variables:
  azureDevOpsRepository: 'oe2020finrepdata.flyway.snowstructure'
  azureDevOpsConfigRepository: 'OE2020_CORE_FLYWAY_CONFIG'
  azureDevOpsRepositoryTools: 'OE2020_CORE_TOOLS'
  azureDevOpsRepositoryTemplates: 'gsc.global.iac.templates'

stages:
  
  - template: '/azdevops/build/flyway-tools/1.0.0/build-stage.yml@templatesRepo'
    parameters:
      azureDevOpsRepository: '${{ variables.azureDevOpsRepository }}'
      azureDevOpsConfigRepository: '${{ variables.azureDevOpsConfigRepository }}'
      azureDevOpsRepositoryTools: '${{ variables.azureDevOpsRepositoryTools }}'
      azureDevOpsRepositoryTemplates: '${{ variables.azureDevOpsRepositoryTemplates }}'
      buildDeploy: '${{ parameters.buildDeploy }}'
      env: '${{ parameters.env }}'

  - template: '/azdevops/orch/flyway-deploy/2.0.0/deploy-flyway.yml@templatesRepo'
    parameters:
      env: '${{ parameters.env }}'
      azureDevOpsProjectID: '$(System.TeamProjectId)'
      azureDevOpsBuildID: '$(System.DefinitionId)'
      buildDeploy: '${{ parameters.buildDeploy }}'
      buildID: '${{ parameters.buildID }}'
