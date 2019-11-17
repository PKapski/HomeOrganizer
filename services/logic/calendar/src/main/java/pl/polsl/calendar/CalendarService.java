package pl.polsl.calendar;

import org.springframework.stereotype.Service;
import pl.polsl.model.CalendarEvent;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarMongoRepository repository;

    public CalendarService(CalendarMongoRepository repository) {
        this.repository = repository;
    }

    List<CalendarEvent> getHouseholdEvents(String householdId) {
        return repository.findAllByHouseholdId(householdId);
    }

    String saveEvent(CalendarEvent event, String householdId) {
        if (householdId!=null){
            event.setHouseholdId(householdId);
        }
        return repository.save(event).getId();
    }

    public boolean deleteEvent(String id) {
        CalendarEvent event = repository.findById(id);
        if (event!=null){
            repository.delete(event);
            return true;
        }
        return false;
    }
}
