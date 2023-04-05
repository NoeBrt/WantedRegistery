package Repository;

import java.util.ArrayList;

import Model.WantedPerson;

public interface IWantedPersonRepository {
    ArrayList<WantedPerson> findWanted();
    WantedPerson findWanted(String title);

}
