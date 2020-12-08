package cp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/*
Example JSON
{
    "phoneNumber": "123456789",
    "fakePhoneNumber": "987654321",
}
 */
@Document(collection = "PersonPhoneMasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class PhoneNumberMask {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String phoneNumber;
    private String fakePhoneNumber;
}
