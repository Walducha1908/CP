package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
 */
/* Geo json point
https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/core/geo/GeoJsonPoint.html
 */

@Document(collection = "POIs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class POI {
    public String name;
    public String description;
    public GeoJsonPoint position;
    public PlaceType type;
}
