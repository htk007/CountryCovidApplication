package com.heka.countrycovidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BASE_URL = "https://public.richdataservices.com/";
    CovidService covidService;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpApi();
        fetchCovidData();
    }

    private void setUpApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        covidService = retrofit.create(CovidService.class);
    }

    private void fetchCovidData(){
        Call<List<CovidData>> call = covidService.getCovidData("date_stamp,cnt_confirmed,cnt_death,cnt_recovered",
                "(iso3166_1=TR)",
                "amcharts",
                5000);

        call.enqueue(new Callback<List<CovidData>>() {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                if (response.isSuccessful()) {
                    List<CovidData> covidDataList = response.body();
                    // Verileri işleyin...
                    for (CovidData covidData : covidDataList) {
                        Log.i("HASANLOG", "Date stamp: " + covidData.getData());

                    }
                } else {
                    // Hata durumunda işlemleri burada yapın...
                    Log.i("HASANLOG", "ON RESPONSE HATA!!!");
                }
            }

            @Override
            public void onFailure(Call<List<CovidData>> call, Throwable t) {
                // Hata durumunda işlemleri burada yapın...
                Log.i("HASANLOG", "ON FAILURE HATA!!! , " + t.getLocalizedMessage().toString());
            }
        });
    }


}
