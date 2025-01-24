# Leer la variable de entrada que es la ruta donde se encuentran los archivos a buscar
while getopts "p:" opt; do
        case $opt in
                p)
                        SEARCH_PATH=$OPTARG
                        ;;
                \?)
                        err "Invalid option: -$OPTARG"
                        exit 1
                        ;;
                :)
                        err "Option -$OPTARG requires an argument."
                        exit 1
                        ;;
        esac
done
