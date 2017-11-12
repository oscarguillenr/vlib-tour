To compile and install, execute the following command:
$ mvn clean install

The services are first of all tested in JUnit tests.

Secondly, you can start a server and then some clients using two consoles.
In the first console, execute the following command:
$ mvn exec:java@server
...
Hit enter to stop it...
Then, in the second console, execute the following command:
$ mvn exec:java@client
To stop the server, hit return in the first console.

...
Hit enter to stop it...
Then,test some of the services from a Web browser with those URLs
http://localhost:8083/MyServer/application.wadl
http://localhost:8083/MyServer/stations/all
http://localhost:8083/MyServer/stations/31703
http://localhost:8083/MyServer/stations/31705
To stop the server, hit return in the first console.
