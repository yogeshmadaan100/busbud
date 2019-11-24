package com.busbud.autocomplete.model;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {

    @Test
    public void testToDTO() {
        CityDTO cityDTO = new CityDTO("name", "1.2546", "2.4567", 1.22);
        City city = new City();
        city.setName("name");
        city.setLat(1.2546);
        city.setLng(2.4567);
        Assert.assertEquals(cityDTO, city.ToDTO(1.224454));
    }
}
