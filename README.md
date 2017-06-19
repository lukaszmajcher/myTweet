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
/             |GET          | return list of tweets
/             |POST         | add new tweet and user

Example tweet JSON

```javascript
{
"message": "nndddnnn",
"author": "bbb"
}
```

##### User Service #####
Url           |Verb          | Description
--------------|------------- | -------------
/users         |GET          | return list of users
/users/{id}    |GET          | return user detail
/usert/{id}/tweets|GET       | return list of tweets for user
/users/{id}/follows |GET     | return list of user follows
/users/{id}/follows |PUT     | follow user
/users/{id}/follows/tweets |GET     | return list of tweets for user follow

