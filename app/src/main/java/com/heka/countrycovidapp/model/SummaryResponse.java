package com.heka.countrycovidapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryResponse {
    @SerializedName("Global")
    private Global global;
    @SerializedName("Countries")
    private List<Country> countryList;

    public Global getGlobal() {
        return global;
    }

    public List<Country> getCountryList() {
        return countryList;
    }
}
