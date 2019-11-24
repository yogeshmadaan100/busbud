package com.busbud.autocomplete.dao;

import com.busbud.autocomplete.model.City;

import java.util.Iterator;
import java.util.List;

public interface ICityDao {
    /**
     *
     * @param query search terms to search
     * @return list of cities matching the criteria
     */
    Iterator<City> searchByPrefix(String query);

    /**
     *
     * @param cities cities that are needed to be saved
     */
    void batchSaveCities(List<City> cities);

}
