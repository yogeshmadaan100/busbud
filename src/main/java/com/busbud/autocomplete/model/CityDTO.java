package com.busbud.autocomplete.model;

import java.util.Objects;

public class CityDTO {
    private final String name;
    private final String latitude;
    private final String longitude;
    private final double score;

    public CityDTO(String name, String latitude, String longitude, double score) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return Double.compare(cityDTO.score, score) == 0 &&
                Objects.equals(name, cityDTO.name) &&
                Objects.equals(latitude, cityDTO.latitude) &&
                Objects.equals(longitude, cityDTO.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude, score);
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", score=" + score +
                '}';
    }
}
