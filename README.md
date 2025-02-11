stages:
  
  - template: '/azdevops/build/flyway-tools/1.0.0/build-stage.yml@templatesRepo'
    parameters:
      azureDevOpsRepository: '${{ variables.azureDevOpsRepository }}'
      azureDevOpsConfigRepository: '${{ variables.azureDevOpsConfigRepository }}'
      azureDevOpsRepositoryTools: '${{ variables.azureDevOpsRepositoryTools }}'
      azureDevOpsRepositoryTemplates: '${{ variables.azureDevOpsRepositoryTemplates }}'
      buildDeploy: '${{ parameters.buildDeploy }}'
      env: '${{ parameters.env }}'
