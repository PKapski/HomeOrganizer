package pl.polsl.household;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.Household;

public interface HouseholdsMongoRepository extends MongoRepository<Household,String> {
    Household findById(String id);
}
