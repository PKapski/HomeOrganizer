package pl.polsl.calendar;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.CalendarEvent;

import java.util.List;

public interface CalendarMongoRepository extends MongoRepository<CalendarEvent,String> {
    List<CalendarEvent> findAllByHouseholdId(String householdId);
    CalendarEvent findById(String id);
}
