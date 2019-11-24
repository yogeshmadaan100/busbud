package com.busbud.autocomplete.cache;

import com.busbud.autocomplete.model.CityDTO;

import java.util.List;

/**
 * ICache is the implementation for caching
 */
public interface ICache {
    /**
     *
     * @param query
     * @param latitude
     * @param longitude
     * @return
     */
    List<CityDTO> getCache(String query, Double latitude, Double longitude);

    /**
     *
     * @param query
     * @param latitude
     * @param longitude
     * @param cityDTOList
     */
    void saveInCache(String query, Double latitude, Double longitude, List<CityDTO> cityDTOList);

    /**
     *
     * @param query
     * @param latitude
     * @param longitude
     * @return
     */
    boolean isCachePresent(String query, Double latitude, Double longitude);
}
