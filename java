#!/bin/bash


####### FUNCTIONS #######
function flyway_migrate () {
    
    salida=""
    temp_file="/azp/agent/_work/_temp/tempErr"

    echo "Start repair process"
    ./flyway repair -url="$url" -user="$dbUser" -password="$dbPassword" -initSql="$flywayInitSql" -table="$flywayTableName" -locations="$flywaySqlLocation" -schemas="$flywaySchemas" -placeholders.environment="$flywayPlaceholders_environment" 2>&1 | grep -v "Flyway OSS Edition" | grep -v "Picked up" | tee "$temp_file" | grep -E "ERROR|FATAL" >&2
    echo "End repair process"
    echo ""
    echo "Start migrate process"
    ./flyway migrate -url="$url" -user="$dbUser" -password="$dbPassword" -createSchemas="$flywayCreateSchemas" -initSql="$flywayInitSql" -table="$flywayTableName" -locations="$flywaySqlLocation" -defaultSchema="$flywayDefaultSchema" -schemas="$flywaySchemas" -baselineOnMigrate="$flywayBaselineOnMigrate" -baselineVersion="$flywayBaselineVersion" -placeholders.environment="$flywayPlaceholders_environment" 2>&1 | grep -v "Flyway OSS Edition" | grep -v "Picked up" | tee "$temp_file" | grep -E "ERROR|FATAL" >&2

    if [ -s "$temp_file" ]; then
      echo ""
      echo "Errores en el proceso, revisar errores!!"
      salida=$(cat $temp_file)
      echo "##vso[task.complete result=SucceededWithIssues;]DONE"
      echo ""
      echo "Salida errores:"
      echo "$salida"
    else
        echo "Data migrated"
    fi

    # Remove tmp files
    rm $temp_file
    
}

function flyway_clean () {
    echo "Start clean process"
    ./flyway clean -url="$url" -user="$dbUser" -password="$dbPassword" -schemas="$flywaySchemas"
    echo "Data migrated"
}


####### END FUNCTIONS #######



####### MAIN #######

# Get input parameters
PARAMS_FILE=""
if [ "${ARMPARAMETERSFILES}" != "" ]
then
    PARAMS_FILE="${ARMPARAMETERSFILES}"
else
    PARAMS_FILE="../${RELEASE_ENVIRONMENTNAME}/$PROJECT/$ENTORNO/$ENTORNO${AGENT_JOBNAME}p.json"
fi

flywayTableName=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayTableName.value')
flywaySqlLocation=$(eval echo $(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywaySqlLocation.value'))
flywayCreateSchemas=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayCreateSchemas.value')
flywayInitSql=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayInitSql.value')
flywayDefaultSchema=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayDefaultSchema.value')
flywaySchemas=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywaySchemas.value')
flywayBaselineOnMigrate=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayBaselineOnMigrate.value')
flywayBaselineVersion=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayBaselineVersion.value')
flywayPlaceholders_environment=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.flywayPlaceholders_environment.value')
dbName=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.dbName.value')
dbWarehouse=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.dbWarehouse.value')
dbRole=$(cat "$PARAMS_FILE" | jq -r -c '.parameters.dbRole.value')


dbType=$1
dbTenant=$2
dbUser=$3
dbPassword=$4

# Generate URL snowflake
if [ "$dbType" == "snowflake" ]
then
    url="jdbc:snowflake://$dbTenant.snowflakecomputing.com/?tracing=OFF&logLevel=OFF&db=$dbName&warehouse=$dbWarehouse&role=$dbRole"
elif [ "$dbType" == "postgre" ]
then
    url="jdbc:postgresql://$dbTenant:5432/$dbName?sslmode=require"
elif [ "$dbType" == "sqlserver" ]
then
    url="jdbc:sqlserver://$dbTenant:1433;database=$dbName;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
else
    echo "Tipo de bbdd incorrecto o no soportado"
    exit 1
fi

# Extract flyway-commandline and go to path
if [ "${FLYWAYVERSIONDIRECTORY}" != "" ]
then
    tar -xzf "${FLYWAYVERSIONDIRECTORY}/flyway-commandline-11.2.0-linux-x64.tar.gz" -C "${FLYWAYVERSIONDIRECTORY}"
    cd "${FLYWAYVERSIONDIRECTORY}/flyway-11.2.0"
    # Call flyway function
    rm -rf ./lib/aad/slf4j-api-1.7.30.jar    
    export JAVA_TOOL_OPTIONS="--add-opens=java.base/java.nio=ALL-UNNAMED"
    flyway_migrate

else
    tar -xzf ./flyway/flyway-commandline-11.2.0-linux-x64.tar.gz -C ./flyway/
    cd ./flyway/flyway-11.2.0
    rm -rf ./lib/aad/slf4j-api-1.7.30.jar
    # Call flyway function    
    export JAVA_TOOL_OPTIONS="--add-opens=java.base/java.nio=ALL-UNNAMED"
    flyway_migrate

fi
