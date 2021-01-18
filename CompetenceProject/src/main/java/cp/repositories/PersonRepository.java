package cp.repositories;

import cp.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {}
