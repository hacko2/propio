Salida errores:
Picked up JAVA_TOOL_OPTIONS: --add-opens=java.base/java.nio=ALL-UNNAMED
Jan 22, 2025 5:08:08 PM net.snowflake.client.jdbc.SnowflakeConnectionV1 initConnectionWithImpl
INFO: Initializing new connection
Jan 22, 2025 5:08:08 PM net.snowflake.client.jdbc.DefaultSFConnectionHandler initLogger
INFO: Setting logger with log level OFF and log pattern %h/snowflake_jdbc%u.log
Picked up JAVA_TOOL_OPTIONS: --add-opens=java.base/java.nio=ALL-UNNAMED
Jan 22, 2025 5:08:16 PM net.snowflake.client.jdbc.SnowflakeConnectionV1 initConnectionWithImpl
INFO: Initializing new connection
Jan 22, 2025 5:08:16 PM net.snowflake.client.jdbc.DefaultSFConnectionHandler initLogger
INFO: Setting logger with log level OFF and log pattern %h/snowflake_jdbc%u.log

./flyway repair -url="$url" -user="$dbUser" -password="$dbPassword" -initSql="$flywayInitSql" -table="$flywayTableName" -locations="$flywaySqlLocation" -schemas="$flywaySchemas" -placeholders.environment="$flywayPlaceholders_environment" 2> >(grep -E "ERROR|FATAL|Flyway OSS Edition 11.2.0 by Redgate" >&2) > $temp_file
