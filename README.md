
  "bindings": [
    {
      "name": "myBlob",
      "type": "blobTrigger",
      "direction": "in",
      "path": "esp/onealm360/{name}",
      "connection": "https://gscd1weustaoe2020crit019.blob.core.windows.net/configuration"
     
    },
    {
      "name": "starter",
      "type": "orchestrationClient",
      "direction": "in"
    }
  ]
}
