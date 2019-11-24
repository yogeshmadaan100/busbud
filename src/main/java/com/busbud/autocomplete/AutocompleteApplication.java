package com.busbud.autocomplete;

import com.busbud.autocomplete.csv.DeserializeException;
import com.busbud.autocomplete.csv.ReaderImpl;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.parser.CityDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class AutocompleteApplication {

	public static void main(String[] args) throws NumberFormatException, IOException {
		SpringApplication app = new SpringApplication(AutocompleteApplication.class);
		app.run(args);
		loadCSVAndFillCitiesInformation(app);
	}

	// visible for testing
	static void loadCSVAndFillCitiesInformation(SpringApplication application) throws NullPointerException, IOException {
		ClassLoader classLoader = application.getClassLoader();

//		InputStream resource = application.getMainApplicationClass().getCla.getResource("classpath:data/sample.csv").getInputStream();
		// Load csv file
		Reader csvRequestFile = new InputStreamReader(Objects.requireNonNull(new ClassPathResource("/static/cities_canada-usa.tsv").getInputStream()));

		List<City> cities;
		try {
			cities = new ReaderImpl().readContent(csvRequestFile, new CityDeserializer(), true);
			System.out.println(cities);
		} catch (DeserializeException | NullPointerException | IOException e) {
			e.printStackTrace();
			return;
		}
	}


}
