package com.heka.countrycovidapp.api;




import com.heka.countrycovidapp.model.SummaryResponse;
import com.heka.countrycovidapp.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiService {

    @GET(Constants.GET_COMMAND_SUMMARY)
    Call<SummaryResponse> getSummary();

}
