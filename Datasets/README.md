# Datasets

Datasets folder contains exported data from database when running the project. There are 4 main sets.

1. `POIs` - contains data about Points Of Interest.
    
    * **name** - name of POI
    * **description** - description of POI
    * **position** - Geoposition of POI
    * **type** - place type
    * **studentA** - [Weibull](https://en.wikipedia.org/wiki/Weibull_distribution) first coefficient determining the probability of a visit for students
    * **studentB** - [Weibull](https://en.wikipedia.org/wiki/Weibull_distribution) second coefficient determining the probability of a visit for students
    * **teacherA** - [Weibull](https://en.wikipedia.org/wiki/Weibull_distribution) first coefficient determining the probability of a visit for teachers
    * **teacherB** - [Weibull](https://en.wikipedia.org/wiki/Weibull_distribution) second coefficient determining the probability of a visit for teachers
    * **stuffA** - [Weibull](https://en.wikipedia.org/wiki/Weibull_distribution) first coefficient determining the probability of a visit for stuff
    * **stuffB** - [Weibull](https://en.wikipedia.org/wiki/Weibull_distribution) second coefficient determining the probability of a visit for stuff

2. `Persons` - contains data about persons
    * **phoneNumber** - masked phoneNumber
    * **profile** - Type of person.

3. `Traces` - traces data 

    * **userId** - User Id
    * **poiId** - POI id
    * **timeOfEntry** - Time when person entered POI
    * **timeOfExit** - Time when person exited POI

Example JSON for trace
```json
{
    "userid": "72f91809-a609-47ba-b521-a124d2d233a0",
    "poiId": "5fa116e0bbe7314c7a9d364e",
    "timeOfEntry": "2020-10-11 20:10:15",
    "timeOfExit": "2020-10-11 20:10:15"
}
```

4. `PersonPhoneMasks` - mapper table with encrypted phone numbers for **persons**
