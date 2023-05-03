package com.heka.countrycovidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidService {
    @GET("rds/api/query/covid19/jhu_country/select")
    Call<List<CovidData>> getCovidData(@Query("cols") String columns,
                                       @Query("where") String whereClause,
                                       @Query("format") String format,
                                       @Query("limit") int limit);
}
