package com.busbud.autocomplete.stats;

public class Stats {
    private static Stats _instance;
    private long maxPopulation;

    private Stats() {
        // to ensure singleton pattern
    }

    public static Stats getInstance() {
        if(_instance == null)
            _instance = new Stats();
        return _instance;
    }

    public long getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(long maxPopulation) {
        this.maxPopulation = maxPopulation;
    }
}
