package pl.polsl.api;

import org.springframework.stereotype.Controller;

@Controller
public class CalendarApiController implements CalendarApi {

    private final CalendarApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public CalendarApiController(CalendarApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public CalendarApiDelegate getDelegate() {
        return delegate;
    }
}
