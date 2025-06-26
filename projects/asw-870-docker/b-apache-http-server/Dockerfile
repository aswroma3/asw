###########################################
# Dockerfile to build an apache2 image
###########################################

# Base image is Ubuntu
FROM ubuntu:24.04

# Author: Luca Cabibbo 
LABEL author="Luca Cabibbo <luca.cabibbo@uniroma3.it>"

# Install apache2 package
RUN apt-get update && \
    apt-get install -y apache2 

# Funziona, ma è preferibile l'altra soluzione 
# RUN apt-get update 
# RUN apt-get install -y apache2 

# Set the log directory PATH
ENV APACHE_LOG_DIR=/var/log/apache2

# Expose port 80
EXPOSE 80

VOLUME /var/www/html 

# Launch apache2 server in the foreground
ENTRYPOINT ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"] 
