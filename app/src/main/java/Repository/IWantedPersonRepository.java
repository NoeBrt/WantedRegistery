package Repository;

import java.util.ArrayList;

import JsonParser.IJsonParserStrategy;
import Model.WantedPerson;
import Model.WantedPersonDetailed;

public interface IWantedPersonRepository {
    ArrayList<WantedPerson> findWanted();
    WantedPersonDetailed findWanted(String title);

}
