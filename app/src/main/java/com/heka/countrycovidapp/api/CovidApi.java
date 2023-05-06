package com.heka.countrycovidapp.api;

import com.heka.countrycovidapp.model.CovidData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {
    @GET("countries")
    Call<List<CovidData>> getCovidData();
}

