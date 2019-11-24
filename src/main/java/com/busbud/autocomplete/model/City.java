package com.busbud.autocomplete.model;

import java.util.Objects;

public class City {
    private long id;
    private String name;
    private String asciiName;
    private String altName;
    private double lat;
    private double lng;
    private String featClass;
    private String featCode;
    private String country;
    private long population;
    private long elevation;
    private String timeZone;
    private String modifiedAt;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setFeatClass(String featClass) {
        this.featClass = featClass;
    }

    public void setFeatCode(String featCode) {
        this.featCode = featCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setElevation(long elevation) {
        this.elevation = elevation;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAsciiName() {
        return asciiName;
    }

    public String getAltName() {
        return altName;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getFeatClass() {
        return featClass;
    }

    public String getFeatCode() {
        return featCode;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }

    public long getElevation() {
        return elevation;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Double.compare(city.lat, lat) == 0 &&
                Double.compare(city.lng, lng) == 0 &&
                population == city.population &&
                elevation == city.elevation &&
                Objects.equals(name, city.name) &&
                Objects.equals(asciiName, city.asciiName) &&
                Objects.equals(altName, city.altName) &&
                Objects.equals(featClass, city.featClass) &&
                Objects.equals(featCode, city.featCode) &&
                Objects.equals(country, city.country) &&
                Objects.equals(timeZone, city.timeZone) &&
                Objects.equals(modifiedAt, city.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, asciiName, altName, lat, lng, featClass, featCode, country, population, elevation, timeZone, modifiedAt);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", asciiName='" + asciiName + '\'' +
                ", altName='" + altName + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", featClass='" + featClass + '\'' +
                ", featCode='" + featCode + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                ", elevation=" + elevation +
                ", timeZone='" + timeZone + '\'' +
                ", modifiedAt='" + modifiedAt + '\'' +
                '}';
    }
}
