{
  "cluster_name": {
    "type": "fixed",
    "value": "manual_cluster"
  },
  "spark_version": {
    "type": "fixed",
    "value": "14.3.x-scala2.12",
    "hidden": false
  },
  "autoscale.min_workers": {
    "type": "fixed",
    "value": 1,
    "hidden": false
  },
  "autoscale.max_workers": {
    "type": "fixed",
    "value": 8,
    "hidden": false
  },
  "autotermination_minutes": {
    "type": "fixed",
    "value": 10,
    "hidden": false
  },
  "driver_node_type_id": {
    "type": "fixed",
    "value": "Standard_DS4_v2",
    "hidden": false
  },
  "node_type_id": {
    "type": "fixed",
    "value": "Standard_DS4_v2",
    "hidden": false
  },
  "spark_conf.spark.driver.extraJavaOptions": {
    "type": "fixed",
    "value": "-Dhttps.proxyHost=proxy.sig.umbrella.com -Dhttp.proxyHost=proxy.sig.umbrella.com -Dhttps.proxyPort=443 -Dhttp.proxyPort=80 -Dhttp.nonProxyHosts='localhost|127.0.0.1|::1|*.corp|*.azurewebsites.net|*.database.windows.net|*.documents.azure.com|*.servicebus.windows.net|*.vaultcore.azure.net|*.vault.azure.net|gscd1weustacorsdhcrit123.blob.core.windows.net|gscd1weustacorsdhcrit122.blob.core.windows.net|gscd1weustacorsdhcrit123.dfs.core.windows.net|gscd1weustacorsdhcrit122.dfs.core.windows.net|gscd1weustacorsdhcrit123.web.core.windows.net|gscd1weustacorsdhcrit122.web.core.windows.net|gscd1weustacorsdhcrit124.web.core.windows.net|gscd1weustacorsdhcrit124.dfs.core.windows.net|gscd1weustacorsdhcrit124.blob.core.windows.net'"
  },
  "spark_conf.spark.executor.extraJavaOptions": {
    "type": "fixed",
    "value": "-Dhttps.proxyHost=proxy.sig.umbrella.com -Dhttp.proxyHost=proxy.sig.umbrella.com -Dhttps.proxyPort=443 -Dhttp.proxyPort=80 -Dhttp.nonProxyHosts='localhost|127.0.0.1|::1|*.corp|*.azurewebsites.net|*.database.windows.net|*.documents.azure.com|*.servicebus.windows.net|*.vaultcore.azure.net|*.vault.azure.net|gscd1weustacorsdhcrit123.blob.core.windows.net|gscd1weustacorsdhcrit122.blob.core.windows.net|gscd1weustacorsdhcrit123.dfs.core.windows.net|gscd1weustacorsdhcrit122.dfs.core.windows.net|gscd1weustacorsdhcrit123.web.core.windows.net|gscd1weustacorsdhcrit122.web.core.windows.net|gscd1weustacorsdhcrit124.web.core.windows.net|gscd1weustacorsdhcrit124.dfs.core.windows.net|gscd1weustacorsdhcrit124.blob.core.windows.net'"
  },
  "custom_tags.Project": {
    "type": "fixed",
    "value": "ESG"
  },
  "custom_tags.Environment": {
    "type": "fixed",
    "value": "dev"
  },
  "spark_env_vars.HTTPS_PROXY": {
    "type": "fixed",
    "value": "http://proxy.sig.umbrella.com:443"
  },
  "spark_env_vars.HTTP_PROXY": {
    "type": "fixed",
    "value": "http://proxy.sig.umbrella.com:80"
  },
  "spark_env_vars.PYSPARK_PYTHON": {
    "type": "fixed",
    "value": "/databricks/python3/bin/python3"
  },
  "spark_env_vars.environment": {
    "type": "fixed",
    "value": "dev"
  },
  "cluster_log_conf.type": {
    "type": "fixed",
    "value": "DBFS"
  },
  "cluster_log_conf.path": {
    "type": "fixed",
    "value": "dbfs:/logs"
  },
  "azure_attributes.first_on_demand": {
    "type": "fixed",
    "value": 1,
    "hidden": false
  },
  "azure_attributes.availability": {
    "type": "fixed",
    "value": "SPOT_WITH_FALLBACK_AZURE",
    "hidden": false
  },
  "azure_attributes.spot_bid_max_price": {
    "type": "fixed",
    "value": -1,
    "hidden": false
  },
  "spark_conf.spark.sql.hive.metastore.version": {
    "type": "fixed",
    "value": "2.3.9"
  },
  "spark_conf.spark.sql.hive.metastore.jars": {
    "type": "fixed",
    "value": "builtin"
  },
  "spark_conf.spark.hadoop.javax.jdo.option.ConnectionUserName": {
    "type": "fixed",
    "value": "psqldbresg"
  },
  "spark_conf.spark.hadoop.javax.jdo.option.ConnectionURL": {
    "type": "fixed",
    "value": "jdbc:postgresql://gscd1weupfsoe2020crit006.postgres.database.azure.com:5432/metastore?sslmode=require"
  },
  "spark_conf.spark.hadoop.javax.jdo.option.ConnectionPassword": {
    "type": "fixed",
    "value": "{{secrets/scpesgsdhcrit002/dbrpfsmetastorepassesg}}"
  },
  "spark_conf.spark.hadoop.javax.jdo.option.ConnectionDriverName": {
    "type": "fixed",
    "value": "org.postgresql.Driver"
  },
  "spark_env_vars.IS_ACTIVE_CUSTOM_DNS": {
    "type": "fixed",
    "value": "true"
  },
  "spark_env_vars.IS_CUSTOM_DNS": {
    "type": "fixed",
    "value": "107.105.97.20"
  },
  "spark_env_vars.IS_LIBRARIES_DEFINITION_NAME": {
    "type": "fixed",
    "value": "manual_cluster"
  },
  "spark_env_vars.IS_DYNATRACE_TOKEN": {
    "type": "fixed",
    "value": "{{secrets/scpesgsdhcrit002/genericdyntoken}}"
  },
  "spark_env_vars.IS_DYNATRACE_ENABLE_GANGLIA_TOKEN": {
    "type": "fixed",
    "value": "{{secrets/scpesgsdhcrit002/genericdyngangliatoken}}"
  },
  "spark_env_vars.IS_DATABRICKS_TOKEN": {
    "type": "fixed",
    "value": "{{secrets/scpesgsdhcrit002/dbrdynagangliageneral}}"
  },
  "spark_env_vars.IS_DYNATRACE_ACTIVE_GATE_URL": {
    "type": "fixed",
    "value": "https://gscp1weudntl-mntrsn-004.unix.aacc.corp:9999"
  },
  "spark_env_vars.IS_DYNATRACE_SAAS_TENANT": {
    "type": "fixed",
    "value": "vle34905"
  },
  "spark_env_vars.IS_DATABRICKS_CP_URL": {
    "type": "fixed",
    "value": "https://adb-3744238945693302.2.azuredatabricks.net/"
  },
  "spark_env_vars.IS_DYNATRACE_HOSTS_GROUP": {
    "type": "fixed",
    "value": "SDH.ESG.DBR.DEVELOPMENT"
  },
  "init_scripts.0.workspace.destination": {
    "type": "fixed",
    "hidden": false,
    "value": "/initScripts/initScriptsCustomDnsManager.sh"
  },
  "init_scripts.1.workspace.destination": {
    "type": "fixed",
    "hidden": false,
    "value": "/initScripts/initScriptsLibrariesManager.sh"
  },
  "init_scripts.2.workspace.destination": {
    "type": "fixed",
    "hidden": false,
    "value": "/initScripts/initScriptsCertificatesManager.sh"
  },
  "init_scripts.3.workspace.destination": {
    "type": "fixed",
    "hidden": false,
    "value": "/initScripts/initScriptsDynatrace.sh"
  }
}
