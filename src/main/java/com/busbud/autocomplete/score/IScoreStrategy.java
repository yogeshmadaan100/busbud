package com.busbud.autocomplete.score;

import com.busbud.autocomplete.model.City;

public interface IScoreStrategy {
    /**
     *
     * @param query query by the user
     * @param lat user latitude
     * @param lng user longitude
     * @param candidateCity candidacy score of the city
     * @return
     */
    double getScore(String query, double lat, double lng, City candidateCity);
}
