package cp.model.mappers;

import cp.dto.PersonDto;
import cp.model.Person;

public class PersonMapper {
    public static PersonDto mapToDto(Person person) {
        return PersonDto.builder().phoneNumber(person.getPhoneNumber()).profile(person.getProfile()).build();
    }

    public static Person mapFromDto(PersonDto personDto) {
        return Person.builder().phoneNumber(personDto.getPhoneNumber()).profile(personDto.getProfile()).build();
    }
}
