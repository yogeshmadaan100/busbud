package com.busbud.autocomplete.service;

import com.busbud.autocomplete.cache.ICache;
import com.busbud.autocomplete.dao.ICityDao;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.model.CityDTO;
import com.busbud.autocomplete.score.IScoreStrategy;
import com.busbud.autocomplete.stats.Stats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteServiceTest {
    private AutoCompleteService autoCompleteService;
    @Mock
    private ICityDao cityDao;
    @Mock
    private IScoreStrategy scoreStrategy;
    @Mock
    private ICache cache;

    private City london, singapore, delhi;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        autoCompleteService = new AutoCompleteService(cityDao, scoreStrategy, cache);
        setupCities();
    }

    @Test
    public void getSuggestions_NullQuery_BadRequest() {
        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions(null, null, null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getSuggestions_EmptyQuery_BadRequest() {
        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions("", null, null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getSuggestions_LatitudeMissing_BadRequest() {
        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions("lon", null, 1.254);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getSuggestions_LongitudeMissing_BadRequest() {
        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions("lon", 12.43341, null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getSuggestions_NoLocation_ReturnPrefixResult() {
        List<City> cities = new ArrayList<>();
        cities.add(london);
        cities.add(singapore);

        Mockito.doReturn(false).when(cache).isCachePresent(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(cities.listIterator()).when(cityDao).searchByPrefix("lon");
        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions("lon", null, null);

        List<CityDTO> expectedResponse = new ArrayList<>();
        expectedResponse.add(london.ToDTO(1));
        expectedResponse.add(singapore.ToDTO(1));
        Assert.assertEquals(expectedResponse, responseEntity.getBody());
        Mockito.verify(cache).saveInCache("lon", null, null, expectedResponse);
    }

    @Test
    public void getSuggestions_HasLocation_SortByScore() {
        List<City> cities = new ArrayList<>();
        cities.add(london);
        cities.add(singapore);
        cities.add(delhi);

        Mockito.doReturn(false).when(cache).isCachePresent(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(cities.listIterator()).when(cityDao).searchByPrefix("lon");
        Mockito.doReturn(0.1).when(scoreStrategy).getScore("lon", 1.1, 1.1, london);
        Mockito.doReturn(0.59).when(scoreStrategy).getScore("lon", 1.1, 1.1, singapore);
        Mockito.doReturn(0.81).when(scoreStrategy).getScore("lon", 1.1, 1.1, delhi);
        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions("lon", 1.1, 1.1);

        List<CityDTO> expectedResponse = new ArrayList<>();
        expectedResponse.add(delhi.ToDTO(0.81));
        expectedResponse.add(singapore.ToDTO(.59));
        expectedResponse.add(london.ToDTO(.1));
        Assert.assertEquals(expectedResponse, responseEntity.getBody());
        Mockito.verify(cache).saveInCache("lon", 1.1, 1.1, expectedResponse);
    }

    @Test
    public void getSuggestions_CachePresent_Success() {
        List<CityDTO> cacheResponse = new ArrayList<>();
        cacheResponse.add(delhi.ToDTO(0.81));
        cacheResponse.add(singapore.ToDTO(.59));
        cacheResponse.add(london.ToDTO(.1));
        cache.saveInCache("lon", 1.1, 1.1, cacheResponse);



        Mockito.doReturn(true).when(cache).isCachePresent(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(cacheResponse).when(cache).getCache(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());


        ResponseEntity<List<CityDTO>> responseEntity = autoCompleteService.getSuggestions("lon", 1.1, 1.1);

        List<CityDTO> expectedResponse = new ArrayList<>();
        expectedResponse.add(delhi.ToDTO(0.81));
        expectedResponse.add(singapore.ToDTO(.59));
        expectedResponse.add(london.ToDTO(.1));
        Assert.assertEquals(expectedResponse, responseEntity.getBody());
    }

    private void setupCities() {
        london = new City();
        london.setName("London");
        london.setLat(51.5074);
        london.setLng(0.1278);
        london.setAltName("UK");
        london.setPopulation(5000000);

        singapore = new City();
        singapore.setName("Singapore");
        singapore.setLat(1.3521);
        singapore.setLng(103.8198);
        singapore.setAltName("South east, asia");
        singapore.setPopulation(500000);


        delhi = new City();
        delhi.setName("Delhi");
        delhi.setLat(20.5937);
        delhi.setLng(78.9629);
        delhi.setAltName("Bharat, Hindustan");
        delhi.setPopulation(500000000);
        Stats.getInstance().setMaxPopulation(847693867);
    }
}
