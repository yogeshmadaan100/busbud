package com.busbud.autocomplete.score;

import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.stats.Stats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LocationAlternateNamePopulationStrategyTest {
    private City london,singapore,delhi;
    private double userLat = 1.2498;
    private double userLong = 54.2123;
    private LocationAlternateNamePopulationStrategy strategy;

    @Before
    public void setup() {
        strategy = new LocationAlternateNamePopulationStrategy();
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

    @Test
    public void getLocationScore() {
        Assert.assertEquals(0.29468564096270644, strategy.getLocationScore(userLat, userLong, london), 0);
        Assert.assertEquals(0.21639442225638897, strategy.getLocationScore(userLat, userLong, singapore), 0);
        Assert.assertEquals(0.13506412546414315, strategy.getLocationScore(userLat, userLong, delhi), 0);
    }

    @Test
    public void getPopulationScore() {
        Assert.assertEquals(0D, strategy.getPopulationScore(london), 0.001);
        Assert.assertEquals(0D, strategy.getPopulationScore(singapore), 0.001);
        Assert.assertEquals(0.088D, strategy.getPopulationScore(delhi), 0.001);
    }

    @Test
    public void getAltNameScore() {
        Assert.assertEquals(0.35, strategy.getAlternateNameScore("uk", london), 0.1);
        Assert.assertEquals(0.35, strategy.getAlternateNameScore("asia", singapore), 0.1);
        Assert.assertEquals(0.35, strategy.getAlternateNameScore("bharat", delhi), 0.1);
        Assert.assertEquals(0, strategy.getAlternateNameScore("udfsk", london), 0.1);
        Assert.assertEquals(0, strategy.getAlternateNameScore("asfdfia", singapore), 0.1);
        Assert.assertEquals(0, strategy.getAlternateNameScore("fda", delhi), 0.1);
    }

    @Test
    public void getScore() {
        Assert.assertEquals(0.64, strategy.getScore("uk", userLat, userLong, london), 0.01);
        Assert.assertEquals(0.21, strategy.getScore("uk", userLat, userLong, singapore), 0.01);
        Assert.assertEquals(0.22, strategy.getScore("uk", userLat, userLong, delhi), 0.01);
    }
}
