package cp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/*
Example JSON
{
    "userid": "72f91809-a609-47ba-b521-a124d2d233a0",
    "poiId": "5fa116e0bbe7314c7a9d364e",
    "timeOfEntry": "2020-10-11 20:10:15",
    "timeOfExit": "2020-10-11 20:10:15"
}
 */
@Document(collection = "Traces")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Trace {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    public String userId;
    public String poiId;
    public POI visitedPOI;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime timeOfEntry;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime timeOfExit;
}
