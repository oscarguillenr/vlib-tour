#!/bin/bash

MODULE_VERSION=0.1-SNAPSHOT
RABBITMQ_CLIENT_VERSION=4.2.1
PRODUCER=eu.telecomsudparis.rabbitmq.tutorial.LobbyRoomRPCClient
CONSUMER=eu.telecomsudparis.rabbitmq.tutorial.LobbyRoomRPCServer

if [[ -f ./target/step6-${MODULE_VERSION}.jar ]]
then
    export JARS=./target/step6-${MODULE_VERSION}.jar
else
    echo Archive file ./target/step6-${MODULE_VERSION}.jar missing
    echo Run maven install to generate it
fi

if [[ -f ${HOME}/.m2/repository/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar ]]
then
    export JARS=${HOME}/.m2/repository/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar:${JARS}
else
    echo Archive file ${HOME}/.m2/repository/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar missing
    echo Run maven install to install it on your local maven repository
fi

if [[ -f ${HOME}/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar ]]
then
    export JARS=${HOME}/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:${JARS}
else
    echo Archive file ${HOME}/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar missing
    echo Run maven install to install it on your local maven repository
fi

if [[ -f ${HOME}/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar ]]
then
    export JARS=${HOME}/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar:${JARS}
else
    echo Archive file ${HOME}/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar missing
    echo Run maven install to install it on your local maven repository
fi

rabbitmqctl stop
rabbitmq-server -detached
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app
java -cp ${JARS} ${CONSUMER} &
TOREMOVE=$!
sleep 2
rabbitmqctl list_queues name durable auto_delete
sleep 2
java -cp ${JARS} ${PRODUCER} 1 1 &
sleep 1
java -cp ${JARS} ${PRODUCER} 2 1 &
sleep 1
java -cp ${JARS} ${PRODUCER} 3 3 &
sleep 1
java -cp ${JARS} ${PRODUCER} 4 4 &
sleep 1
java -cp ${JARS} ${PRODUCER} 5 10 &
sleep 1
java -cp ${JARS} ${PRODUCER} 11 1 &
sleep 1
java -cp ${JARS} ${PRODUCER} 10 10 &
sleep 5
kill -15 $TOREMOVE
echo END OF TEST
echo FORCE CLOSE OF CONSUMER
rabbitmqctl stop_app
rabbitmqctl stop





