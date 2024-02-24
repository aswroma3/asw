###########################################
# Dockerfile to share the www folder (in the container) 
###########################################

# BusyBox ha un footprint minimale 
FROM busybox:latest

# Author: Luca Cabibbo 
LABEL author="Luca Cabibbo <luca.cabibbo@uniroma3.it>"

COPY www /var/www/html

VOLUME /var/www/html 

ENTRYPOINT ["/bin/true"]