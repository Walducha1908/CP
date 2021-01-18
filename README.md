# Competence Project
Java spring boot app - TUL FTIMS Competence Project winter - 2020/2021.

# Running backend
You have to move file `application.properties` to `\CompetenceProject\src\main\resources` with appropriate secret content.

Connection string is an URI for MongoDB. You can setup one on [Mongo Atlas](https://www.mongodb.com/cloud/atlas).

Start data can be found in `CP_Persons_Data.json` or in a appropiate JSON in `\datasets\*`
```
CONNECTIONSTRING='secret'
DATABASENAME='secret'
```

# Actions
Application is developed with Spring Boot and has an REST API. All endpoints can be checked in `controlers` folder.

Path `/persons` is responsible for actions involving Persons object 

Path `/poi` is responsible for actions involving POI (points of interest) object

Path `/trace` is responsible for actions involving Trace object

Path `/simulation` is responsible for starting simulation

Path `/analysis` is responsible for performing analysis after simulation end. ( Or data loaded in db ).
