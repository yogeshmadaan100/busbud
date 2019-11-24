package com.busbud.autocomplete.score;

import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.stats.Stats;

import java.sql.Time;


public class LocationAlternateNamePopulationStrategy implements IScoreStrategy {
    private static final float locationWeightage = 0.5f;
    private static final float populationWeightage = 0.15f;
    private static final float alternateNameWeightage = 0.35f;

    @Override
    public double getScore(String query, double lat, double lng, City candidateCity) {
        return getLocationScore(lat, lng, candidateCity) + getPopulationScore(candidateCity) + getAlternateNameScore(query, candidateCity);

    }

    private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    private double getLocationScore(double lat, double lng, City candidateCity) {
        return locationWeightage * calculateDistanceBetweenPoints(lat, lng, candidateCity.getLat(), candidateCity.getLng());
    }

    private double getPopulationScore(City candidateCity) {
        return populationWeightage * ((double)(candidateCity.getPopulation())/ Stats.getInstance().getMaxPopulation());
    }

    private double getAlternateNameScore(String query, City candidateCity) {
        return candidateCity.getAltName().contains(query) ? alternateNameWeightage : 0;
    }

}
