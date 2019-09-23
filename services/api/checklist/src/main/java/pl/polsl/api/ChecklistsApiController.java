package pl.polsl.api;

import org.springframework.stereotype.Controller;

@Controller
public class ChecklistsApiController implements ChecklistsApi {

    private final ChecklistsApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public ChecklistsApiController(ChecklistsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public ChecklistsApiDelegate getDelegate() {
        return delegate;
    }
}
