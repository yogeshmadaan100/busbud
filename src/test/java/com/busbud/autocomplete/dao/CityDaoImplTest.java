package com.busbud.autocomplete.dao;

import com.busbud.autocomplete.model.City;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class CityDaoImplTest {

    String[] cityNames = new String[]{"London", "Singapore", "Lonavala", "USA", "India", "Malaysia", "Indonesia"};

    @Test
    public void testSave() {
        CityDaoImpl cityDao = new CityDaoImpl();
        List<City> cities = createRandomCities(5);
        cityDao.batchSaveCities(cities);
        Assert.assertEquals(4, cityDao.maxPopulation);
    }

    @Test
    public void testSave_whenEmptyQuery() {
        CityDaoImpl cityDao = new CityDaoImpl();
        List<City> cities = createRandomCities(5);
        cityDao.batchSaveCities(cities);
        Assert.assertEquals(5L, StreamSupport.stream(cityDao.searchByPrefix("").spliterator(), false).count());
    }


    @Test
    public void testSave_whenPrefixMatch() {
        CityDaoImpl cityDao = new CityDaoImpl();
        List<City> cities = createRandomCities(5);
        cityDao.batchSaveCities(cities);
        Assert.assertEquals(2L, StreamSupport.stream(cityDao.searchByPrefix("Lon").spliterator(), false).count());
    }

    @Test
    public void testSave_whenPrefixMatch_caseInSensitive() {
        CityDaoImpl cityDao = new CityDaoImpl();
        List<City> cities = createRandomCities(5);
        cityDao.batchSaveCities(cities);
        Assert.assertEquals(2L,StreamSupport.stream(cityDao.searchByPrefix("lon").spliterator(), false).count());
    }

    @Test
    public void testSave_whenNoMatch() {
        CityDaoImpl cityDao = new CityDaoImpl();
        List<City> cities = createRandomCities(5);
        cityDao.batchSaveCities(cities);
        Assert.assertEquals(0L, StreamSupport.stream(cityDao.searchByPrefix("lonfasf").spliterator(), false).count());
    }

    List<City> createRandomCities(int count) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            City city = new City();
            city.setId(count);
            city.setName(cityNames[i]);
            city.setAsciiName("ascii");
            city.setAltName("alt_name");
            city.setLat(1.124);
            city.setLng(2.34);
            city.setFeatClass("feat_class");
            city.setFeatCode("feat_code");
            city.setCountry("SG");
            city.setPopulation(i);
            cities.add(city);
        }
        return cities;
    }
}
