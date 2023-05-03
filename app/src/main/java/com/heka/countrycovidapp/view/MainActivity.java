package com.heka.countrycovidapp.view;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.api.ApiService;
import com.heka.countrycovidapp.model.Country;

import com.heka.countrycovidapp.model.SummaryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        getCovidList();
    }

    private void getCovidList() {
        Call<SummaryResponse> call = apiService.getSummary();

        call.enqueue(new Callback<SummaryResponse>() {
            @Override
            public void onResponse(Call<SummaryResponse> call, Response<SummaryResponse> response) {
                if (response.isSuccessful()) {
                    SummaryResponse summaryResponse = response.body();
                    List<Country> countries = summaryResponse.getCountryList();

                    for(Country c: countries){
                        Log.d("MainActivity", "Country: " + c.getCountryName() +" data: " +  c.getNewDeaths());
                    }

                } else {
                    Log.e("MainActivity", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SummaryResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }
}
