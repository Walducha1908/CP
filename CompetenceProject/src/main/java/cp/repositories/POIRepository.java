package cp.repositories;

import cp.model.POI;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface POIRepository extends MongoRepository<POI, String> {
}

