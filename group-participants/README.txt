To compile and install, execute the following command:
$ mvn clean install

The EJB bean is deployed with the Embedded Glassfish Server using
Maven and this is done in the JUnit test of the module
group_participant_bean. 

Afterwards, the EJB bean can be deployed with the Glassfish asadmin tool.
The EJB bean is deployed with the Glassfish asadmin tool.
Before executing the example, check your configuration:
$ asadmin help

To run the example, execute the following commands:
$ asadmin start-domain domain1; asadmin start-database; asadmin deploy group_participant_bean/target/entity-bean.jar

$ (cd group_participant_client/; java -classpath $CLASSPATH:../group_participant_bean/target/entity-bean.jar:target/group_participant_client-4.0-SNAPSHOT.jar enterprise/entity_bean_client/StatelessJavaClient)

Undeploy the component and stop the container by executing the commands:
$ asadmin undeploy entity-bean; asadmin stop-database; asadmin stop-domain domain1

Known Bug:
----------
The command mvn exec:java to execute the client in the module
ejb-31-entity-bean-example-client does not work:
...
Inserting Customer and Orders... OK
Verifying that all are inserted... OK
javax.ejb.EJBException: java.rmi.MarshalException: CORBA MARSHAL 1330446347 Maybe; nested exception is: 
	org.omg.CORBA.MARSHAL: WARNING: 00810011: Exception from readValue on ValueHandler in CDRInputStream  vmcid: OMG  minor code: 11 completed: Maybe
	at enterprise.entity_bean_api._StatelessSession_Wrapper.findCustomer(enterprise/entity_bean_api/_StatelessSession_Wrapper.java)
	at enterprise.entity_bean_client.StatelessJavaClient.main(StatelessJavaClient.java:21)
