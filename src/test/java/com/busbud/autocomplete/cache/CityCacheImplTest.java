package com.busbud.autocomplete.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CityCacheImplTest {
    private ICache cityCache;

    @Before
    public void setup() {
        cityCache = new CityCacheImpl();
    }

    @Test
    public void testCache() {
        Assert.assertFalse(cityCache.isCachePresent("lon", null, null));
        cityCache.saveInCache("lon", null, null, new ArrayList<>());
        Assert.assertTrue(cityCache.isCachePresent("lon", null, null));
        Assert.assertEquals(new ArrayList<>(), cityCache.getCache("lon", null, null));

        Assert.assertFalse(cityCache.isCachePresent("lon", 1.23, null));

    }
}
