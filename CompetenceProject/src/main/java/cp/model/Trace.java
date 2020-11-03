package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

/*
Example JSON
{
    "userid": "72f91809-a609-47ba-b521-a124d2d233a0",
    "poiId": "5fa116e0bbe7314c7a9d364e",
    "timeOfEntry": "1604393682",
    "timeOfExit": "1604393682"
}
 */
@Document(collection = "Traces")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Trace {
    public String userId;
    public String poiId;
    public POI visitedPOI;
    public Timestamp timeOfEntry;
    public Timestamp timeOfExit;
}
