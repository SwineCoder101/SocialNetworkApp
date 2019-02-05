//Scenarios
//        Posting
//        A user should be able to post a 140 character message.
//
//        Wall
//        A user should be able to see a list of the messages they've posted, in reverse chronological order.
//
//        Following
//        A user should be able to follow another user. Following doesn't have to be reciprocal: Alice can follow Bob without Bob having to follow Alice.
//
//        Timeline
//        A user should be able to see a list of the messages posted by all the people they follow, in reverse chronological order.


SETUP
To setup the restful service for SocialNetworkApp, the jar will be built using maven and it can be run using the java command

How to build
1) cd to project directory
2) run the following maven command: mvn clean package

How to run
1) cd to target path
2) run java -jar SocialNetworkApp-1.0-SNAPSHOT.jar

HOW TO USE
To use the API, the url will need to start off with "http://localhost:8080" this indicates the hostname for which the REST server is running on
For all requests the username must be specified, therefore all REST urls will start off with "http://localhost:8080/user/<enter username>/".
For PUT and POST requests a json body must be added with messageStr as the message key eg {"messageStr":"Enter message"}

How to post a message
POST http://localhost:8080/user/<Current User Name>/post {"messageStr":"enter message"}

How to view wall
GET http://localhost:8080/user/<Current User Name>/view

How to view users being followed
PUT http://localhost:8080/user/<Current User Name>/follow/<User Name to Follow >

How to view time line
GET http://localhost:8080/user/<Current User Name>/timeline