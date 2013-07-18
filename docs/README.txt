1. Introduction
----------------------------------------
The Portal API Abstraction Layer provides a set of interfaces and
static services to access functionality provided by a portal. Applications
that use the API layer simply need to write against its APIs. The portal
container will provide the functionality and have the API layer library 
in a location accessible to the deployed application.

2. Installation
----------------------------------------
The ant build file for the project provides a deploy target. This depends
on the compile and deploy targets to create the JAR and then copies it
into the portal's library directory. The portal location needs to be
configured in the build.properties.
A compiled instance of this library should be included in binary form in
the portal project. This will allow the API layer project to be developed
while the portal keeps a stable release of the JAR in the project. The
version of the API abstraction layer specified in build.properties is
noted in the manifest of the JAR.

3. Deployment
----------------------------------------
The deployment is taken care of by the portal. It needs to put the API layer
library in a shared location so that all the applications that will use it
are accessing the same instance of the library.

4. Testing
----------------------------------------
There is a test portlet included with the package. It runs through some basic
tests for each of the different services provided by the API layer. The build
script is setup to use the portlet and servlet JARs that come with uPortal
and the path to uPortal needs to be configured in build.properties for any of
the test. targets to work.

The 'test.deploy' target will compile the test, create the WAR file and call
the uPortal deployPortletApp target to deploy the portlet to the servlet
container.

The test portlet can be published using the portletID
'AbstractionLayerTests.AbstractionLayerTestPortlet'

The docs/tests directory has a set of screenshots of expected results for
the tests. The test results may not be exactly the same every time they
are run since the person and group tests depend on the portal's configuration
and the state of the data stores for the portal.
