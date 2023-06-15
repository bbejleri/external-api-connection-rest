# Connecting to an external fake API using REST Calls 

### Modularity of the project

The project consists in the following sections:

1. Main Package: The package where the Main class and beans are.

2. Caching Package: Cache configurations

3. Controller Package: Package which holds all of the controllers of this project as well as separated API classes where the logic of the REST calls of the controllers and the argument validators are implemented respectively.

4. Error Package: Package to hold custom errors/error configs/general error classes.

5. Logging Package: Package to maintain logging classes.

6. Model Package: Package contains model objects used throughout the project.

7. RestClient Package: Package contains classes where the external API is called. These methods are then called from controller/services to process the information retrieved from the external API.

8. Service PAckage: Package which contain services/service interfaces to work with data coming from RestClient classes.


### Project Build

The project uses gradle as a build tool. Consider gradle documentation to trigger a project build.

### Lombok Project

The project uses lombok project through the respective dependencies (see build.gradle) and lombok IDE installations. 
Lombok is used to avoid standard creation of constructors/getter and setter methods for practicallity and better readability. 

### OpenAPI

The project uses OpenAPI library for building a swagger like documentation of the implemented REST API calls. To check the generated documentation please build and run the project and call the following endpoint from any browser: http://localhost:8080/


### Mockito

The project uses Mockito for implementation of the unit tests.
