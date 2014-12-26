Eionet HelpAdmin software
=========================

This is a short installation guide for the EIONET HelpAdmin software.

HelpAdmin is small web-application that enables to administer various
informative texts in other web-applications. These other applications
must include eionet-help.jar (see inside lib/eionet/eionet-help directory)
on their classpath and also have the HLP_SCREEN and HLP_AREA tables
in their database. See documents inside the doc/ directory for more.

HelpAdmin accesses the remote applications via XML-RPC router that
must be present at the other side. The full URLs of these routers
are configured in helpadmin.properties which gets its values from
the local properties file that is provided at build time (see for
more below).

Below, TRUNK denotes the directory where you have downloaded HelpAdmin's
SVN trunk into.


1. Create local properties file for the build.

In TRUNK, make a copy of the unittest.properties and call it
local.properties. Inside the file, make replacements appropriate
to your environment.

You can use another name instead of local.properties, but it must be
in the format of somename.properties. In such a case you need to
explicitly provide the name of that properties file to the build
(see below).

2. Run Maven build.

HelpAdmin is built with Apache Maven.
Assuming you have Maven installed, and its "mvn" executable on your
path, execute the following command while in TRUNK:

```sh
mvn clean install
```

If you named your local.properties for example to otherName.properties,
you need to pass it to the build like this:

```sh
mvn -Denv=otherName clean install
```

3. Deploy helpadmin.war to the Tomcat.

The previous step produced a target/helpadmin.war file.
You have to copy this into Tomcat's webapps, and restart Tomcat
to deploy the application.


