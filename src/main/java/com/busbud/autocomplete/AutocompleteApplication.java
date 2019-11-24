package com.busbud.autocomplete;

import com.busbud.autocomplete.csv.DeserializeException;
import com.busbud.autocomplete.csv.ReaderImpl;
import com.busbud.autocomplete.dao.CityDaoImpl;
import com.busbud.autocomplete.dao.ICityDao;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.parser.CityDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class AutocompleteApplication {

	public static void main(String[] args) throws NumberFormatException, IOException {
		SpringApplication app = new SpringApplication(AutocompleteApplication.class);
		app.run(args);
		loadCSVAndFillCitiesInformation();
	}

	// visible for testing
	static void loadCSVAndFillCitiesInformation() throws NullPointerException, IOException {
		// Load csv file
		Reader csvRequestFile = new InputStreamReader(Objects.requireNonNull(new ClassPathResource("/static/cities_canada-usa.tsv").getInputStream()));

		List<City> cities;
		try {
			cities = new ReaderImpl().readContent(csvRequestFile, new CityDeserializer(), true);
			ICityDao cityDao = new CityDaoImpl();
			cityDao.batchSaveCities(cities);
			Iterator<City> searchResult = cityDao.searchByPrefix("Lon").iterator();
			while(searchResult.hasNext()) {
				System.out.println(searchResult.next());
			}
		} catch (DeserializeException | NullPointerException | IOException e) {
			e.printStackTrace();
			return;
		}
	}


}
