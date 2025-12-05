#!/bin/bash

echo "========================"
echo "unconfiguring aws access"
echo "========================"

rm -rf /home/vagrant/.aws/
rm -rf /home/vagrant/.kube/

