#!/bin/bash

# Ruta a la carpeta principal que contiene los repositorios
CARPETA_PRINCIPAL=$(pwd)

# Lógica para reemplazar el nombre en el archivo flyway.yml
reemplazar_nombre() {
    local repo_path="$1"
    local repo_name=$(basename "$repo_path")
    local flyway_file="$repo_path/pipeline/flyway.yml"
    
    if [ -f "$flyway_file" ]; then
        # Reemplazar correctamente solo el final de la cadena
        sed -i -E "s/(oe2020rrhsdh\.flyway\.snowstructure\.)[a-zA-Z0-9_-]+/\1$repo_name/" "$flyway_file"
        echo "Modificado $flyway_file en el repositorio $repo_name"
    else
        echo "No se encontró flyway.yml en el repositorio $repo_name"
    fi
}

# Recorrer todos los subdirectorios de la carpeta principal
for repo in "$CARPETA_PRINCIPAL"/*; do
    if [ -d "$repo/.git" ]; then
        echo "Procesando repositorio: $repo"
        reemplazar_nombre "$repo"
    else
        echo "No es un repositorio Git: $repo"
    fi
done

