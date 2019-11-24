package com.busbud.autocomplete.cache;

import com.busbud.autocomplete.model.CityDTO;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CityCacheImpl implements ICache {
    // visible for testing
    final static String cacheName = "cityListCache";
    private final Cache<CacheKey, List<CityDTO>> listCache;

    public CityCacheImpl() {
        listCache = new Cache2kBuilder<CacheKey, List<CityDTO>>() {}.name(cacheName).expireAfterWrite(1, TimeUnit.HOURS).build();
    }

    @Override
    public List<CityDTO> getCache(String query, Double latitude, Double longitude) {
        return listCache.get(new CacheKey(query, latitude, longitude));
    }

    @Override
    public void saveInCache(String query, Double latitude, Double longitude, List<CityDTO> cityDTOList) {
        CacheKey cacheKey = new CacheKey(query, latitude, longitude);
        listCache.put(cacheKey, cityDTOList);
    }

    @Override
    public boolean isCachePresent(String query, Double latitude, Double longitude) {
        return listCache.containsKey(new CacheKey(query, latitude, longitude));
    }


}
