package cp.repositories;

import cp.model.PhoneNumberMask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhoneMaskRepository extends MongoRepository<PhoneNumberMask, String> {
    PhoneNumberMask findByFakePhoneNumber(String fakePhoneNumber);
}
