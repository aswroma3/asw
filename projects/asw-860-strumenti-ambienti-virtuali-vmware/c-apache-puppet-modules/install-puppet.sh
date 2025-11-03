#!/bin/bash

# see https://medium.com/@Joachim8675309/vagrant-provisioning-with-puppet-553a59f0c48e 

echo "Installing Puppet..."

command -v puppet > /dev/null && { echo "Puppet is installed! skipping" ; exit 0; }

wget https://apt.puppetlabs.com/puppet8-release-$(lsb_release -cs).deb
dpkg -i puppet8-release-$(lsb_release -cs).deb
apt-get -qq update
apt-get install -y puppet-agent
