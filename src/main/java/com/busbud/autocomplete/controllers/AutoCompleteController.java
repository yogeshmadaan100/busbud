package com.busbud.autocomplete.controllers;

import com.busbud.autocomplete.model.CityDTO;
import com.busbud.autocomplete.service.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutoCompleteController {
    @Autowired
    private AutoCompleteService autoCompleteService;

    @RequestMapping(method = RequestMethod.GET, value = "suggestions")
    public ResponseEntity<List<CityDTO>> getSuggestions(@RequestParam(value = "q", required = false) String query, @RequestParam(value = "latitude", required = false) Double latitude, @RequestParam(value = "longitude", required = false) Double longitude) {
        return autoCompleteService.getSuggestions(query, latitude, longitude);
    }
}
