# Simple game REST api

## Context

I created this project in order to play with spring boot to create a simple yet not useful REST api about video games. Moreover, I'm teaching Java to college students and they told me that it would be great to have an already existing application in order to play with VisualVM, Jmc or whatever, so here it is. 

I'm going to use this project in order to try some stuff as well and I know it is far from being perfect.

If you face any problem, do not hesitate to create an issue, you are most welcome. If you consider creating a PR, please create an issue first. Thanks

## The application

### Description

This application is a simple REST api to deal with games, publishers and platforms. It can return some very relevant information about a video game such as the released year, the sales in million, the scores it got from the users and critics on which platform and from which publisher.

Currently, the data are stored in a H2 database populated when the application starts using a simple sql file.

### Build and run it

You have several ways to run the application:

```
[0][2019-07-07 14:40:07][16][florentclarret@dana:~/Documents/Polytech/TP/VideoGameApi] (branch: master)  
 $ mvn spring-boot:run
```

OR 

```
[130][2019-07-07 14:53:44][0][florentclarret@dana:~/Documents/Polytech/TP/VideoGameApi] (branch: master)  
 $ mvn clean install && java -jar target/video-game-api-1.0.0-SNAPSHOT.jar
``` 

### The API

I provide three different webservices to manager video games, publishers and platforms. A swagger UI is also provided : [Swagger](http://localhost:8080/swagger-ui.html) 

#### Video games

* URL :  /api/v1.0/videogame/
    * Methods : GET / POST / PUT
* URL :  /api/v1.0/videogame/{gameId}
    * Methods : GET / POST / PUT / DELETE
* URL :  /api/v1.0/videogame/{gameId}/publisher
    * Methods : GET / POST / DELETE 
* URL :  /api/v1.0/videogame/{gameId}/platform
    * Methods : GET / POST / DELETE
    
#### Publishers

* URL :  /api/v1.0/publisher/
    * Methods : GET / POST / PUT
* URL :  /api/v1.0/publisher/{publisherId}
    * Methods : GET / POST / PUT / DELETE
* URL :  /api/v1.0/publisher/{publisherId}/videogame
    * Methods : GET

#### Platforms

* URL :  /api/v1.0/platform/
    * Methods : GET / POST / PUT
* URL :  /api/v1.0/platform/{platformId}
    * Methods : GET / POST / PUT / DELETE
* URL :  /api/v1.0/platform/{platformId}/videogame
    * Methods : GET 