package cp.dto;

import cp.model.Profile;
import lombok.Builder;
import lombok.Data;

@Builder
public @Data class PersonDto {
    private String phoneNumber;
    private Profile profile;
}
