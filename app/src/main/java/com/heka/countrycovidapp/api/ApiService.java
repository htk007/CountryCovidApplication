package com.heka.countrycovidapp.api;




import com.heka.countrycovidapp.model.SummaryResponse;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiService {

    @GET("summary")
    Call<SummaryResponse> getSummary();

}
