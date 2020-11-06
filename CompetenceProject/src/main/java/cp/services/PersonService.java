package cp.services;

import cp.dto.PersonDto;
import cp.exceptions.PersonNotFoundException;
import cp.model.POI;
import cp.model.Person;
import cp.model.mappers.PersonMapper;
import cp.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;

    public Person add(Person person) {
        return personRepository.insert(person);
    }

    public Person get(String id) throws PersonNotFoundException {
        if (personRepository.findById(id).isPresent()) {
            return personRepository.findById(id).get();
        }
        log.error("Person with id: " + id + " not found");
        throw new PersonNotFoundException("Person not found");
    }

    public String addBatch(List<Person> persons) {
        persons.forEach(personRepository::insert);
        return "Added batch persons";
    }

    public Person update(String id, PersonDto personDto) throws PersonNotFoundException {
        Person personFromDB = get(id);
        Person personToSave = PersonMapper.mapFromDto(personDto);
        personToSave.setId(personFromDB.getId());
        return personRepository.save(personToSave);
    }

    public Person delete(String id) throws PersonNotFoundException {
        Person personFromDB = get(id);
        personRepository.delete(personFromDB);
        return personFromDB;
    }
    public String clearCollection() {
        personRepository.deleteAll();
        return "Deleted all from collection Person";
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
