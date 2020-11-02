package cp.services;

import cp.model.Person;
import cp.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public void addPerson(Person person) {
        personRepository.insert(person);
    }
}
