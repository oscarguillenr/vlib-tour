#!/bin/bash

MODULE_VERSION=0.1-SNAPSHOT
RABBITMQ_CLIENT_VERSION=4.2.1
PRODUCER=vlibtour.lobbyroom.client.LobbyRoomRPCClient
CONSUMER=vlibtour.lobbyroom.server.LobbyRoomRPCServer

if [[ -f ./target/lobby-room-${MODULE_VERSION}.jar ]]
then
    export JARS=./target/lobby-room-${MODULE_VERSION}.jar
else
    echo Archive file ./target/lobby-room-${MODULE_VERSION}.jar missing
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
# Argv[0] = groupId
# Argv[1] = userId that creates the group
# Argv[2] = userId that joins the group
java -cp ${JARS} ${PRODUCER} Group1 User1 User2 &
sleep 1
java -cp ${JARS} ${PRODUCER} Group2 User8 User3 &
sleep 1
java -cp ${JARS} ${PRODUCER} Group3 User5 User6 &
sleep 1
java -cp ${JARS} ${PRODUCER} Group4 User4 User7&
sleep 1
java -cp ${JARS} ${PRODUCER} Group5 User10 User11 &
sleep 1
java -cp ${JARS} ${PRODUCER} Group11 User20 User21 &
sleep 1
java -cp ${JARS} ${PRODUCER} Group10 User15 User16 &
sleep 5
kill -15 $TOREMOVE
echo END OF TEST
echo FORCE CLOSE OF CONSUMER
rabbitmqctl stop_app
rabbitmqctl stop





