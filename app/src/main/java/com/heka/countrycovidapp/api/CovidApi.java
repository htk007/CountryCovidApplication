package com.heka.countrycovidapp.api;

import com.heka.countrycovidapp.model.CovidData;
import com.heka.countrycovidapp.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {
    @GET(Constants.GET_COMMAND_COUNTRIES)
    Call<List<CovidData>> getCovidData();
}

