 while IFS= read -r -d '' file; do

                                # Extraer los valores del parámetro spn_name del archivo JSON y agregarlo al array
                                while IFS= read -r SPN_NAME; do

                                        SPN_DESCRIPTION=$(jq -r '.parameters.spn_list[] | select(.spn_name == "'"$SPN_NAME"'") | .spn_description' "$file")
                                        
                                        if [[ ! "$SPN_DESCRIPTION" =~ [Ll][Oo][Cc][Aa][Ll] ]]; then                                   
                                        
                                                if [ -n "$SPN_NAME" ] && [ "$SPN_NAME" != "null" ]; then

                                                        
                                                        carpetaProyecto=$(basename "$(dirname "$SEARCH_PATH")")
                                                        echo "Carpeta proyecto: $carpetaProyecto"
                                                        apiconnect
                                                        expiration
                                                fi
                                        fi                                 

                                done < <(jq -r '.parameters.spn_list[].spn_name' "$file")
                                

                        done < <(find $SEARCH_PATH -type f -name "${ENTORNO}*.json" -not -name "${ENTORNO}akvspnsre.json" -not -name "${ENTORNO}akvdbr*.json" -print0)
                
