package com.heka.countrycovidapp.model;

import com.google.gson.annotations.SerializedName;

public class CovidData {
    @SerializedName("country")
    private String country;

    @SerializedName("cases")
    private int totalConfirmed;

    @SerializedName("deaths")
    private int totalDeaths;

    @SerializedName("recovered")
    private int totalRecovered;

    @SerializedName("active")
    private int totalActive;

    @SerializedName("todayCases")
    private int newConfirmed;

    @SerializedName("todayDeaths")
    private int newDeaths;

    @SerializedName("todayRecovered")
    private int newRecovered;

    @SerializedName("critical")
    private int critical;

    @SerializedName("tests")
    private int totalTests;

    @SerializedName("population")
    private int population;

    @SerializedName("continent")
    private String continent;

    @SerializedName("updated")
    private long lastUpdated;
    public int getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(int totalActive) {
        this.totalActive = totalActive;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    @Override
    public String toString() {
        return "Country: " + country + "\n" +
                "Total Confirmed: " + totalConfirmed + "\n" +
                "Total Deaths: " + totalDeaths + "\n" +
                "Total Recovered: " + totalRecovered;
    }
}

