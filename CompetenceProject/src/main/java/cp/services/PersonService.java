package cp.services;

import cp.dto.PersonDto;
import cp.exceptions.PersonNotFoundException;
import cp.model.Person;
import cp.model.mappers.PersonMapper;
import cp.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;

    public void addPerson(Person person) {
        personRepository.insert(person);
    }

    public Person getPerson(String id) throws PersonNotFoundException {
        System.out.println(personRepository.findAll());
        if (personRepository.findById(id).isPresent()) {
            return personRepository.findById(id).get();
        }
        log.error("Person with id: " + id + " not found");
        throw new PersonNotFoundException("Person not found");
    }

    public Person updatePerson(String id, PersonDto personDto) throws PersonNotFoundException {
        if (personRepository.findById(id).isPresent()) {
            Person personToSave = PersonMapper.mapFromDto(personDto);
            personToSave.setId(id);
            return personRepository.save(personToSave);
        }
        log.error("Person with id: " + id + " not found");
        throw new PersonNotFoundException("Person not found");
    }
}
