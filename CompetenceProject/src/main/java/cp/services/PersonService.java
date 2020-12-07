package cp.services;

import cp.dto.PersonDto;
import cp.exceptions.PersonNotFoundException;
import cp.model.Person;
import cp.model.PhoneNumberMask;
import cp.model.mappers.PersonMapper;
import cp.repositories.PersonRepository;
import cp.repositories.PhoneMaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;
    private final PhoneMaskRepository phoneMaskRepository;

    private static Random random = new Random();

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

    public String getRealPhoneNumber(String id) throws PersonNotFoundException {
        if (personRepository.findById(id).isPresent()) {
            Person person = personRepository.findById(id).get();

            return phoneMaskRepository.findByFakePhoneNumber(person.getPhoneNumber()).getPhoneNumber();
        }
        log.error("Person with id: " + id + " not found");
        throw new PersonNotFoundException("Person not found");
    }

    public String anonymizePhoneNumbers() {
        phoneMaskRepository.deleteAll();

        List<Person> persons = getAll();
        List<PhoneNumberMask> phoneNumberMaskList = new ArrayList<>();


        for (Person person : persons
        ) {
            String fakePhoneNumber = generateFakeUniquePhoneNumber(10, persons);
            phoneNumberMaskList.add(new PhoneNumberMask(UUID.randomUUID().toString(),
                    person.getPhoneNumber(),
                    fakePhoneNumber));
            person.setPhoneNumber(fakePhoneNumber);
        }

        phoneMaskRepository.saveAll(phoneNumberMaskList);
        personRepository.saveAll(persons);

        return "Phone numbers anonymized";
    }

    private String generateFakeUniquePhoneNumber(int digitCount, List<Person> persons) {
        //Keep trying until unique number is found
        while (true) {
            String randomNumber = generateRandomNumber(digitCount);

            boolean duplicateFound = false;

            for (Person person : persons
            ) {
                if(randomNumber.equals(person.getPhoneNumber())){
                    duplicateFound = true;
                    break;
                }
            }

            if(!duplicateFound)
                return randomNumber;
        }
    }

    private String generateRandomNumber(int digitCount) {
        StringBuilder sb = new StringBuilder(digitCount);

        for (int i = 0; i < digitCount; i++)
            sb.append((char) ('0' + random.nextInt(10)));
        return sb.toString();
    }
}
