#!/bin/bash

# Script per eseguire un task gradle in un insieme di progetti Gradle 

if [ $# -eq 0 ]; then
    echo "Usage: gradle-all-projects.sh <task>"
	exit 1 
fi

TASK=$1 

echo Executing gradle $TASK on all projects in this folder 

# determina il path relativo in cui si trova lo script 
# (rispetto alla posizione da cui è stata richiesta l'esecuzione dello script) 
PATH_TO_SCRIPT=`dirname $0`

# determina i progetti Gradle in questa cartella 
PROJECTS=$(ls ${PATH_TO_SCRIPT}/*/build.gradle)

# esegue il task richiesto in tutti i progetti Gradle 
for project in ${PROJECTS}; do 
	DIR="$(dirname "${project}")" 
	FILE="$(basename "${project}")"
	echo ""
	echo "Now executing gradle $TASK on project ${DIR}"
	gradle --project-dir ${DIR} ${TASK}
	
	if [ "$TASK" = "clean" ]; then 
		echo "Now deleting ${DIR}/.gradle folder"
		rm -rf ${DIR}/.gradle
		echo "Now deleting ${DIR}/build folder"
		rm -rf ${DIR}/build
	fi
	
done 
