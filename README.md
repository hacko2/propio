function flyway_migrate () {
    
    salida=""
    temp_file="/azp/agent/_work/_temp/tempErr"
    ok "Version de FlyWay 11.2.0"
    echo ""
    ok "Start repair process"
    ./flyway repair \
        -url="$url" \
        -user="$dbUser" \
        -password="$dbPassword" \
        -initSql="$flywayInitSql" \
        -table="$flywayTableName" \
        -locations="$flywaySqlLocation" \
        -schemas="$flywaySchemas" \
        -placeholders.environment="$flywayPlaceholders_environment" \
        2>&1 | grep -vE "Flyway OSS Edition|Picked up|Flyway upgrade recommended|See release notes|QUOTED_IDENTIFIERS_IGNORE_CASE" | tee "$temp_file"

    ok "End repair process"
    echo ""
    ok "Start migrate process"
    ./flyway migrate \
        -url="$url" \
        -user="$dbUser" \
        -password="$dbPassword" \
        -createSchemas="$flywayCreateSchemas" \
        -initSql="$flywayInitSql" \
        -table="$flywayTableName" \
        -locations="$flywaySqlLocation" \
        -defaultSchema="$flywayDefaultSchema" \
        -schemas="$flywaySchemas" \
        -baselineOnMigrate="$flywayBaselineOnMigrate" \
        -baselineVersion="$flywayBaselineVersion" \
        -placeholders.environment="$flywayPlaceholders_environment" \
        2>&1 | grep -vE "Flyway OSS Edition|Picked up|Flyway upgrade recommended|See release notes|QUOTED_IDENTIFIERS_IGNORE_CASE" | tee "$temp_file"

    # Mostrar si hubo errores después
    if grep -qE "ERROR|FATAL" "$temp_file"; then
        err "Errores detectados en la migración."
        grep -E "ERROR|FATAL" "$temp_file" >&2
        echo "##vso[task.complete result=SucceededWithIssues;]DONE"
    else
        ok "Migración completada con éxito."
    fi

    # Remove tmp files
    rm $temp_file
    
}
