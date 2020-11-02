package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "POIs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class POI {
    public String name;
    public String description;
//    https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/core/geo/GeoJsonPoint.html
    public GeoJsonPoint position;
    public PlaceType type;
}
