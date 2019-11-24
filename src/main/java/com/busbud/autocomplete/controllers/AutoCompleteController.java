package com.busbud.autocomplete.controllers;

import com.busbud.autocomplete.dao.CityDaoImpl;
import com.busbud.autocomplete.model.CityDTO;
import com.busbud.autocomplete.score.LocationAlternateNamePopulationStrategy;
import com.busbud.autocomplete.service.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutoCompleteController {
//    @Autowired
//    private AutoCompleteService autoCompleteService;

    @RequestMapping(method = RequestMethod.GET, value = "suggestions")
    public List<CityDTO> getSuggestions(@RequestParam(value = "q", required = false) String query, @RequestParam(value = "latitude", required = false) Double latitude, @RequestParam(value = "longitude", required = false) Double longitude) {
        System.out.println("controller called");
        AutoCompleteService autoCompleteService = new AutoCompleteService(CityDaoImpl.getInstance(), new LocationAlternateNamePopulationStrategy());
        return autoCompleteService.getSuggestions(query, latitude, longitude);
    }
}
