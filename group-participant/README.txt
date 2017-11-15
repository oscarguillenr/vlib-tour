This helloworld example is extracted and adapted from
https://github.com/javaee/glassfish-samples/tree/master/ws/javaee7/ejb/hello-stateless-ejb

To compile and install, execute the following command:
$ mvn clean install

The EJB bean is deployed with the Embedded Glassfish Server using
Maven and this is done in the JUnit test of the module
entity-bean-example-bean. For some explanations on the usage of
Embedded Glassfish in JUnit tests, see for instance
https://docs.oracle.com/javaee/7/tutorial/ejb-embedded003.htm.

Afterwards, the EJB bean can be deployed with the Glassfish asadmin tool.
The EJB bean is deployed with the Glassfish asadmin tool.
Before executing the example, check your configuration:
$ asadmin help

To run the example, execute the following commands:
$ asadmin start-domain domain1; asadmin start-database; asadmin deploy group-participant-bean/target/entity-bean.jar

$ (cd group-participant-client/; java -classpath $CLASSPATH:../group-participant-bean/target/entity-bean.jar:target/group-participant-client-4.0-SNAPSHOT.jar client/GroupAndParticipantsClient)

Undeploy the component and stop the container by executing the commands:
$ asadmin undeploy entity-bean; asadmin stop-database; asadmin stop-domain domain1

