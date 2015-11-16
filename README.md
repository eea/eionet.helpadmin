Eionet HelpAdmin software
=========================

This is a short installation guide for the Eionet HelpAdmin software.

HelpAdmin is small web-application that enables to administer various
informative texts in other web-applications. These other applications
must include eionet-help.jar (see inside lib/eionet/eionet-help directory)
on their classpath and also have the HLP_SCREEN and HLP_AREA tables
in their database. See documents inside the doc/ directory for more.

HelpAdmin accesses the remote applications via an XML-RPC router that
must be present at the other side. The full URLs of these routers
are configured in helpadmin.properties which gets its values from
the local properties file that is provided at run time (see for
more below).

Below, ROOT denotes the directory where you have downloaded HelpAdmin's
git repository into.


1. Run Maven build.

HelpAdmin is built with Apache Maven.
Assuming you have Maven installed, and its "mvn" executable on your
path, execute the following command while in ROOT:

```sh
mvn clean install
```

2. Define the environment variable HELPADMIN_CONF for Tomcat.

When the application starts up then it will look in the environment
variable HELPADMIN_CONR for a path name to a properties file. 

```sh
export HELPADMIN_CONF=/var/local/helpadmin/helpadmin.properties
```

In Linux you add the line to /etc/sysconfig/tomcat without the export key word.
If it is not specified, then it will load a default properties file in the class path.

3. Deploy helpadmin.war to the Tomcat.

The previous step produced a target/helpadmin.war file.
You have to copy this into Tomcat's webapps, and restart Tomcat
to deploy the application.

Deployment as Docker container
------------------------------

HelpAdmin can be deployed as a Docker container. To do so you first build the image using Maven:

```
mvn -Pdocker clean install docker:push
```
Then you can deploy it with docker-compose using a YAML file derived from the one you see in the docs directory.
