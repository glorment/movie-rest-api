
# Movie Rest API

## Table design
### actor 
|Field name|Type|is Empty| Index|
| --- | --- | ---| --- | 
|id |varchar(255)|N| PRI|
|first_name|varchar(255)|Y|---|
|last_name|varchar(255)|Y|---|
|gender|varchar(255)|Y|---|

### director
|Field name|Type|is Empty| Index|
| --- | --- | ---| --- | 
|id |varchar(255)|N| PRI|
|first_name|varchar(255)|Y|---|
|last_name|varchar(255)|Y|---|

### movie
|Field name|Type|is Empty| Index|
| --- | --- | ---| --- | 
|id|varchar(255)|N| PRI|
|lang|varchar(255)|Y|---|
|release_country|varchar(255)|Y|---|
|release_date|datetime|Y|---|
|time|int(11)|Y|---|
|title|varchar(255)|Y|---|
|year|int(11)|Y|---|
|director_id|varchar(255)|Y|FK|

### movie_cast
|Field name|Type|is Empty| Index|
| --- | --- | ---| --- |
|movie_id |varchar(255)|N| PRI|
|actor_id |varchar(255)|N| PRI|
|role|varchar(255)|Y|---|

## ER diagram
![ER diagram](https://github.com/glorment/movie-rest-api/blob/main/movie_db.png?raw=true)

## Database setup
1. Download MariaDB
2. create user exuser and create database movie_db
3. grant all privileges to exuser 

## Deploying the WAR to Tomcat
1. Download Apache Tomcat and unpackage it into a tomcat folder
2. Copy our WAR file from target/movie-api.war to the tomcat/webapps/ folder
3. Start tomcat

## Components
###  Movie 
- POST "/" create
- PATCH "/{id}" update
- DELETE "/{id}" detele
- "GET "/{id}" get by id
- POST "/search" search 

## Security
* Api use http basic auth
* 2 role Admin and User , update the user name and password at ENV file
* Create , Update and Delete only accessible by admin role user
* search and get by id can accessible by any role

