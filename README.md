# myTweet RESTful Web Service with Spring Boot


### Installation dependencies ###
 - Java 8
 - maven 3

### Building and starting the server ###
To build the backend and start the server, run the following command on the root folder of the repository:

    mvn spring-boot:run

The application is accessible at the following URL:

    http://localhost:8080/

#### REST API ####
The REST API is composed of 2 services:

##### Wall Service #####

Url           |Verb          | Description
--------------|------------- | -------------
/             |GET          | return list of all tweets
/user| PUT| Used to save the user max calories per day
/user|POST| creates a new user
##### User Service #####

Url           |Verb          | Description
--------------|------------- | -------------
/user         |GET          | retrieves info for the currently logged-in user (number of calories of today, etc.) 
/user| PUT| Used to save the user max calories per day
/user|POST| creates a new user