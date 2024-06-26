FROM php:8.3-rc-apache

RUN docker-php-ext-install mysqli


COPY apache2.conf /etc/apache2/apache2.conf

RUN service apache2 restart
