package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Persons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Person {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String phoneNumber;
    private Profile profile;

}
