%scala
var preSPN =  "gsci1glbsp4onemetauth100" 
var preSTA = "gsci1weustaonemetcrit100"
var storageAccount = {preSTA}
spark.conf.set(s"fs.azure.account.auth.type.${storageAccount}.dfs.core.windows.net", "OAuth")
spark.conf.set(s"fs.azure.account.oauth.provider.type.${storageAccount}.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider")
spark.conf.set(s"fs.azure.account.oauth2.client.secret.${storageAccount}.dfs.core.windows.net", dbutils.secrets.get("scponemetcrit002", {preSPN}).split("""\[###]""")(0))
spark.conf.set(s"fs.azure.account.oauth2.client.id.${storageAccount}.dfs.core.windows.net", dbutils.secrets.get("scponemetcrit002", {preSPN}).split("""\[###]""")(1))
spark.conf.set(s"fs.azure.account.oauth2.client.endpoint.${storageAccount}.dfs.core.windows.net", "https://login.microsoftonline.com/35595a02-4d6d-44ac-99e1-f9ab4cd872db/oauth2/token")
