package pl.polsl.household;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.api.HouseholdsApiDelegate;
import pl.polsl.model.Household;

@Service
public class HouseholdsApiDelegateImpl implements HouseholdsApiDelegate {
    @Override
    public ResponseEntity<Void> createHousehold(Household household) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteHousehold(String householdId) {
        return null;
    }

    @Override
    public ResponseEntity<Household> getHousehold(String householdId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> modifyHousehold(String householdId, Household household) {
        return null;
    }
}
