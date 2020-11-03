package cp.repositories;

import cp.model.Trace;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TraceRepository extends MongoRepository<Trace, String> {}
