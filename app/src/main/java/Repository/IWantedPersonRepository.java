package Repository;

import java.util.ArrayList;

import JsonParser.IJsonParserStrategy;
import Model.WantedPerson;

public interface IWantedPersonRepository {
    ArrayList<WantedPerson> getAllWantedPersons(IJsonParserStrategy<ArrayList<WantedPerson>> parser);
    WantedPerson getWantedPersonDetail(String name,IJsonParserStrategy<WantedPerson> parser);

}
