package pl.polsl.calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.CalendarApiDelegate;
import pl.polsl.model.CalendarEvent;

import java.util.List;

@Service
public class CalendarApiDelegateImpl implements CalendarApiDelegate {

    private final CalendarService service;

    public CalendarApiDelegateImpl(CalendarService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<CalendarEvent>> getCalendarEvents(String householdId) {
        List<CalendarEvent> events = service.getHouseholdEvents(householdId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveCalendarEvent(String householdId, CalendarEvent event) {
        String id = service.saveEvent(event,householdId);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCalendarEvent(String id) {
        if (service.deleteEvent(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
