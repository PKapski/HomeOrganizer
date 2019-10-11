package pl.polsl.household;

import org.springframework.stereotype.Service;
import pl.polsl.model.Household;

@Service
public class HouseholdsService {

    private final HouseholdsMongoRepository repository;

    public HouseholdsService(HouseholdsMongoRepository repository) {
        this.repository = repository;
    }

    void saveHousehold(Household household){
        repository.save(household);
    }

    boolean deleteHousehold(String householdId){
        Household household = repository.findById(householdId);
        if (household!=null) {
            repository.delete(household);
            return true;
        }
        return false;
    }

    Household getHousehold(String householdId){
        return repository.findById(householdId);
    }

    boolean modifyHousehold(String householdId, Household household){
        Household oldHousehold = repository.findById(householdId);
        if (oldHousehold==null) {
            return false;
        }
        household.setId(oldHousehold.getId());
        repository.save(household);
        return true;
    }
}
