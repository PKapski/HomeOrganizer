package pl.polsl.api;

import org.springframework.stereotype.Controller;

@Controller
public class HouseholdsApiController implements HouseholdsApi {

    private final HouseholdsApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public HouseholdsApiController(HouseholdsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public HouseholdsApiDelegate getDelegate() {
        return delegate;
    }
}
