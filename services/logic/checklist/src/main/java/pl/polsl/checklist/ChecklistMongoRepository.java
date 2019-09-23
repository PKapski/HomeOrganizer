package pl.polsl.checklist;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.Checklist;

import java.util.Optional;

public interface ChecklistMongoRepository extends MongoRepository<Checklist,String> {
    Optional<Checklist> findById(String id);
}
