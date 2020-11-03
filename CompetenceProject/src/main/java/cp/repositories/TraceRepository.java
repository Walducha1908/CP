package cp.repositories;

import cp.model.Trace;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TraceRepository extends MongoRepository<Trace, String> {
    List<Trace> findByUserId(String userId);
    List<Trace> findByPoiId(String poiId);
}
