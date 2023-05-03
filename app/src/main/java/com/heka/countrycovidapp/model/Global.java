package com.heka.countrycovidapp.model;


import com.google.gson.annotations.SerializedName;

public class Global {
    @SerializedName("NewConfirmed")
    private int newConfirmed;
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;
    @SerializedName("NewDeaths")
    private int newDeaths;
    @SerializedName("TotalDeaths")
    private int totalDeaths;
    @SerializedName("NewRecovered")
    private int newRecovered;
    @SerializedName("TotalRecovered")
    private int totalRecovered;
    @SerializedName("Date")
    private String date;

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public String getDate() {
        return date;
    }
}
