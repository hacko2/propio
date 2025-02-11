- task: CopyFiles@2
        displayName: Copy Repository Flyway DDLs
        inputs:
          SourceFolder: './${{ parameters.azureDevOpsRepository }}/flyway'
          Contents: '**'
          ${{ if eq(variables.subprojectName,'') }}:
            TargetFolder: './artifact/flywayDDL/${{ variables.projectName }}'
          ${{ else }}:
            TargetFolder: './artifact/flywayDDL/${{ variables.projectName }}_${{ variables.subprojectName }}'
          CleanTargetFolder: true
          OverWrite: true
