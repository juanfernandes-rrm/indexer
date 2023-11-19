# src/scripts/indexer.sh

#!/bin/bash
java -cp "libs/*" com.tads.Main "$@" 2> erro.log > resultado.log
