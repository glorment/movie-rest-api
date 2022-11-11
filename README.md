
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
![alt text](https://github.com/glorment/movie-rest-api/blob/[branch]/image.jpg?raw=true)
