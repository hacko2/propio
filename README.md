{
    "@type": "MessageCard",
    "@context": "http://schema.org/extensions",
    "themeColor": "0076D7",
    "summary": "${title1}",
    "sections": [{
        "activityTitle": "${title1}",
        "activitySubtitle": "${title2}",
        "activityImage": "https://cdn.vsassets.io/ext/ms.vss-build-web/common-library/Nav-Launch.3tiJhd.png",
        "facts": [{
            "name": "Description",
            "value": "${description}"
        }, {
            "name": "Log",
            "value": "${log}"
        }, {
            "name": "Date",
            "value": "${createdUtc}"
        }],
        "markdown": true
    }],
    "potentialAction": [{
        "@type": "OpenUri",
        "name": "Review",
        "targets": [{
            "os": "default",
            "uri": "${viewUrl}"
        }]
    }]
}
