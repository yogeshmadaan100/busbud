package com.busbud.autocomplete.controller;

import com.busbud.autocomplete.controllers.AutoCompleteController;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.model.CityDTO;
import com.busbud.autocomplete.service.AutoCompleteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteControllerTest {
    @Mock
    private AutoCompleteService autoCompleteService;

    @InjectMocks
    private AutoCompleteController autoCompleteController;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testController() {
        List<CityDTO> cityDTOList = new ArrayList<>();
        Mockito.doReturn(new ResponseEntity<>(cityDTOList, HttpStatus.OK)).when(autoCompleteService).getSuggestions("lon", null, null);

        ResponseEntity result = autoCompleteController.getSuggestions("lon", null, null);
        Assert.assertEquals(new ResponseEntity<>(cityDTOList, HttpStatus.OK), result);
    }

    @Test
    public void testController_error() {
        List<CityDTO> cityDTOList = new ArrayList<>();
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST)).when(autoCompleteService).getSuggestions("lon", null, null);

        ResponseEntity result = autoCompleteController.getSuggestions("lon", null, null);
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }
}
