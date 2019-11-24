package com.busbud.autocomplete.dao;

import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.stats.Stats;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;

import java.util.Iterator;
import java.util.List;

public class CityDaoImpl implements ICityDao {
    private static ICityDao _instance;
    private final RadixTree<City> radixTree;
    // visible for testing
    long maxPopulation;

    // visible for testing
    CityDaoImpl() {
        radixTree = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());
    }

    public static ICityDao getInstance() {
        if(_instance == null)
            _instance = new CityDaoImpl();
        return _instance;
    }

    @Override
    public Iterator<City> searchByPrefix(String query) {
        return radixTree.getValuesForKeysStartingWith(query.toLowerCase()).iterator();
    }

    @Override
    public void batchSaveCities(List<City> cities) {
        for(City city : cities) {
            maxPopulation = Math.max(maxPopulation, city.getPopulation());
            radixTree.put(city.getName().toLowerCase(), city);
        }
        Stats.getInstance().setMaxPopulation(maxPopulation);
    }
}
