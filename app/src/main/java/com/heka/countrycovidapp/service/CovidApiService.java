package com.heka.countrycovidapp.service;

import com.heka.countrycovidapp.api.CovidApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidApiService {
    private static final String BASE_URL = "https://disease.sh/v3/covid-19/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private CovidApi covidApi = retrofit.create(CovidApi.class);

    public CovidApi getCovidApi() {
        return covidApi;
    }
}