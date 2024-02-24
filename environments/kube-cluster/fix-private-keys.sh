#!/bin/bash

# risolve un bug di Vagrant 2.4.0 per Windows, che memorizza le chiavi private in formati Windows anziché Linux 
# see https://github.com/hashicorp/vagrant/issues/13284

for pk in .vagrant/machines/*/virtualbox/private_key ; do 
	echo "fixing $pk"
	cp $pk $pk.bak
	dos2unix $pk
done 




