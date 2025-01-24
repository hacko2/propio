jobs:
  - job: 'script_alert_${{ parameters.jobName }}'
    displayName: 'script_alert_${{ parameters.jobName }}'
    continueOnError: false    
    workspace:
      clean: all
    variables:
      - template: pipeline/vars/${{ parameters.env }}/vars-spn.yml@iacRepo
      - name: armWorkingDirectory
        value: '$(System.ArtifactsDirectory)/drop/spn'
      - name: toolsDirectory
        value: '$(System.ArtifactsDirectory)/drop/iac/global/tools'
      - name: ENTORNO
        value: '${{ parameters.env }}'
      - group: notifications
      - name: notificationURL
        value: $(webhook_teams_${{ parameters.env }}_spn)
      - group: notifications
      - name: TEAMS_WEBHOOK_URL
        value: $(TEAMS_WEBHOOK_URL)
      - name: notifCurrentTime
        value: $[format('{0:yyyy}-{0:MM}-{0:dd}_{0:HH}:{0:mm}:{0:ss}', pipeline.startTime)]
