package pl.polsl.api;

import org.springframework.stereotype.Controller;

@Controller
public class NotesApiController implements NotesApi {

    private final NotesApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public NotesApiController(NotesApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public NotesApiDelegate getDelegate() {
        return delegate;
    }
}
