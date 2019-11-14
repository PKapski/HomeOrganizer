package pl.polsl.household;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.HouseholdsApiDelegate;
import pl.polsl.model.Household;

@Service
public class  HouseholdsApiDelegateImpl implements HouseholdsApiDelegate {

    private final HouseholdsService service;

    public HouseholdsApiDelegateImpl(HouseholdsService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<String> saveHousehold(Household household) {
        String householdId = service.saveHousehold(household);
        return new ResponseEntity<>(householdId,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteHousehold(String householdId) {
        if (service.deleteHousehold(householdId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Household> getHousehold(String householdId) {
        Household household = service.getHousehold(householdId);
        if (household==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(household,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyHousehold(String householdId, Household household) {
        if (service.modifyHousehold(householdId, household)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
