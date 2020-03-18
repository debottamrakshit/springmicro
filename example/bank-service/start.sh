#!/bin/sh
until $(wget --spider http://logstash:9600); do
    sleep 50
done
echo "Host Found - `date`"
java -jar app.jar