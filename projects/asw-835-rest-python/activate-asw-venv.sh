#!/bin/bash
if [[ "$0" = "$BASH_SOURCE" ]]; then
	echo "Please use source or .: . activate-asw-venv.sh"
else
	ASW_VENV=/home/asw/venv
	echo "Activating virtual environment $ASW_VENV"
	source $ASW_VENV/bin/activate 
fi
echo "Please use deactivate to deactivate venv"
