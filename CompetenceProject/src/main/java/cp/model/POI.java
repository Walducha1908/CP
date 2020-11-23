package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/*
Example JSON
{
    "name": "test2",
    "position": {
        "type": "Point",
        "coordinates": [10,10]
    },
    "description": "students",
    "type": "indoor"
    "studentA": 1.0,
    "studentB": 2.0,
    "teacherA": 3.0,
    "teacherB": 4.0,
    "stuffA": 5.0,
    "stuffB": 6.0
}
 */
/* Geo json point
https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/core/geo/GeoJsonPoint.html
studentA, studentB etc are weibull distribution probability density function https://en.wikipedia.org/wiki/Weibull_distribution
 */

@Document(collection = "POIs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class POI {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    public String name;
    public String description;
    public GeoJsonPoint position;
    public PlaceType type;
    public Double studentA;
    public Double studentB;
    public Double teacherA;
    public Double teacherB;
    public Double stuffA;
    public Double stuffB;
}
