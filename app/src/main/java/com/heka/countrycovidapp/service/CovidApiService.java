package com.heka.countrycovidapp.service;

import com.heka.countrycovidapp.api.CovidApi;
import com.heka.countrycovidapp.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidApiService {


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.ALTERNATIVE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private CovidApi covidApi = retrofit.create(CovidApi.class);

    public CovidApi getCovidApi() {
        return covidApi;
    }
}