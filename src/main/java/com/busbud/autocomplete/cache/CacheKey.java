package com.busbud.autocomplete.cache;

import java.util.Objects;

public class CacheKey {
    private final String query;
    private final Double latitude;
    private final Double longitude;

    public CacheKey(String query, Double latitude, Double longitude) {
        this.query = query;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(query, cacheKey.query) &&
                Objects.equals(latitude, cacheKey.latitude) &&
                Objects.equals(longitude, cacheKey.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, latitude, longitude);
    }

    @Override
    public String toString() {
        return "CacheKey{" +
                "query='" + query + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
