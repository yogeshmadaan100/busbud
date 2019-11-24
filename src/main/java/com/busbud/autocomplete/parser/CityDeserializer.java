package com.busbud.autocomplete.parser;

import com.busbud.autocomplete.csv.DeserializeException;
import com.busbud.autocomplete.csv.Deserializer;
import com.busbud.autocomplete.model.City;
import org.apache.commons.csv.CSVRecord;

public class CityDeserializer implements Deserializer<City> {
    @Override
    public City deserialize(CSVRecord line) throws DeserializeException {
        City city = new City();
        try {
            city.setId(Long.parseLong(line.get("id")));
            city.setName(line.get("name"));
            city.setAsciiName(line.get("ascii"));
            city.setAltName(line.get("alt_name"));
            city.setLat(Double.parseDouble(line.get("lat")));
            city.setLng(Double.parseDouble(line.get("long")));
            city.setFeatClass(line.get("feat_class"));
            city.setFeatCode("feat_code");
            city.setCountry("country cc2");
            city.setPopulation(Long.parseLong(line.get("population")));
//            city.setElevation(Long.parseLong(line.get("elevation")));
            city.setTimeZone(line.get("tz"));
            city.setModifiedAt(line.get("modified_at"));
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage() + " for record " + line);
        }

        return city;
    }
}
