package com.busbud.autocomplete.score;

import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.stats.Stats;

public class LocationAlternateNamePopulationStrategy implements IScoreStrategy {
    private static final float locationWeightage = 0.5f;
    private static final float populationWeightage = 0.15f;
    private static final float alternateNameWeightage = 0.35f;
    private static final double earthRadius = 6371;

    @Override
    public double getScore(String query, double lat, double lng, City candidateCity) {
        return getLocationScore(lat, lng, candidateCity) + getPopulationScore(candidateCity) + getAlternateNameScore(query, candidateCity);

    }

    private double calculateDistanceBetweenPoints(double lat1, double lon1, double lat2, double lon2) {
        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    private Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    // visible for testing
    double getLocationScore(double lat, double lng, City candidateCity) {
        return locationWeightage * (calculateDistanceBetweenPoints(lat, lng, candidateCity.getLat(), candidateCity.getLng()) / earthRadius);
    }

    // visible for testing
    double getPopulationScore(City candidateCity) {
        return populationWeightage * ((double) (candidateCity.getPopulation()) / Stats.getInstance().getMaxPopulation());
    }

    // visible for testing
    double getAlternateNameScore(String query, City candidateCity) {
        return candidateCity.getAltName().toLowerCase().contains(query.toLowerCase()) ? alternateNameWeightage : 0;
    }

}
