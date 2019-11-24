package com.busbud.autocomplete.dao;

import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.stats.Stats;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;

import java.util.List;

public class CityDaoImpl implements ICityDao {
    private final RadixTree<City> radixTree;
    private long maxPopulation;

    public CityDaoImpl() {
        radixTree = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());
    }

    @Override
    public Iterable<City> searchByPrefix(String query) {
        return radixTree.getValuesForKeysStartingWith(query.toLowerCase());
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