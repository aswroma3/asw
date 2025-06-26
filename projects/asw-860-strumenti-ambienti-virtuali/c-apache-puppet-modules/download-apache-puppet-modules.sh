#!/bin/bash

PUPPET_MODULE_PATH=/vagrant/puppet/modules

function moduleExists {
	MODULE=${PUPPET_MODULE_PATH}/$1
	if [ -e $MODULE ]
	then
		return 0
	else
		return 1
	fi
}

echo "Downloading Puppet modules from forge.puppet.com..."

mkdir -p ${PUPPET_MODULE_PATH}

if ! moduleExists stdlib ; then 
	puppet module install puppetlabs-stdlib --version 9.6.0 --force --modulepath ${PUPPET_MODULE_PATH}
fi 
if ! moduleExists concat ; then 
	puppet module install puppetlabs-concat --version 9.0.2 --force --modulepath ${PUPPET_MODULE_PATH}
fi 
if ! moduleExists apache ; then 
	puppet module install puppetlabs-apache --version 12.2.0 --force --modulepath ${PUPPET_MODULE_PATH}
fi 
