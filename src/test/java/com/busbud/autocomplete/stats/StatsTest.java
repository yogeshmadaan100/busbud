package com.busbud.autocomplete.stats;

import org.junit.Assert;
import org.junit.Test;

public class StatsTest {

    @Test
    public void testStats() {
        Stats stats = Stats.getInstance();
        Assert.assertEquals(0, stats.getMaxPopulation());
        stats.setMaxPopulation(1000);
        Assert.assertEquals(1000, stats.getMaxPopulation());
    }
}
