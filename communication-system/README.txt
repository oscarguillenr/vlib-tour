This tutorial step is extracted and adapted from
https://www.rabbitmq.com/getstarted.html

The RPC service is implemented into three different versions:
(1) using the JAVA client library with the standard AMQP calls,
(2) using the RabbitMQ-specific class StringRpcServer,
(3) using the RabbitMQ-specific classes of the package com.rabbitmq.tools.jsonrpc.

The RabbitMQ broker is deployed pragmatically during the JUnit test of
the module. In addition, we use the RabbitMQ Management Plugin through
the HTTP-based API (cf. https://www.rabbitmq.com/management.html) with
the JAVA client Hop (cf. https://github.com/rabbitmq/hop). To this
end, before executing the tests, install the plugin with the following
command:
$ rabbitmq-plugins --offline enable rabbitmq_management

To execute and test, launch the following command:
$ mvn clean install

To run in a console using shell commands the different entities with
the same scenarios as the one programmed in the JUnit tests, execute the
following scripts:
$ ./run.sh
$ ./run2.sh
$ ./run3.sh
