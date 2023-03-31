#!/bin/bash

docker run -d -it --name $1 --link zookeeper:zookeeper --link kafka:kafka quay.io/debezium/kafka:2.1 watch-topic -a -k $2