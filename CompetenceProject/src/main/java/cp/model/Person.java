package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
/*
Example JSON
{
    "phoneNumber": "test2",
    "profile": "students",
}
 */
@Document(collection = "Persons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Person {
    private String phoneNumber;
    private Profile profile;
}
