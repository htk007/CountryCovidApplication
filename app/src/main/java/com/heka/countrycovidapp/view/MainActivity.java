package com.heka.countrycovidapp.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.adapter.CountryAdapter;
import com.heka.countrycovidapp.adapter.CovidDataAdapter;
import com.heka.countrycovidapp.api.ApiService;
import com.heka.countrycovidapp.model.Country;

import com.heka.countrycovidapp.model.CovidData;
import com.heka.countrycovidapp.model.SummaryResponse;
import com.heka.countrycovidapp.service.CovidApiService;
import com.heka.countrycovidapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private CovidDataAdapter covidDataAdapter;
    private int DATA_SOURCE_MODE = Constants.API_SOURCE_MODE_MAIN;

    EditText searchEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupView();
        setupRetrofit();
        manageFetchCovidDataSource(Constants.API_SOURCE_MODE_ALTERNATIVE);
       //getCovidList();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setupRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void manageFetchCovidDataSource(int sourceType){
        switch (sourceType){
            case Constants.API_SOURCE_MODE_MAIN:
                DATA_SOURCE_MODE = Constants.API_SOURCE_MODE_MAIN;
                getCovidList();
                break;
            case Constants.API_SOURCE_MODE_ALTERNATIVE:
                DATA_SOURCE_MODE = Constants.API_SOURCE_MODE_ALTERNATIVE;
                fetchAlternativeCovidData();
                break;
        }
    }

    private void getCovidList() {
        Call<SummaryResponse> call = apiService.getSummary();
        Log.d("HKLOG", "START FETCH COVID LIST SOURCE 1");
        call.enqueue(new Callback<SummaryResponse>() {
            @Override
            public void onResponse(Call<SummaryResponse> call, Response<SummaryResponse> response) {
                if (response.isSuccessful()) {
                    DATA_SOURCE_MODE = Constants.API_SOURCE_MODE_MAIN;
                    SummaryResponse summaryResponse = response.body();
                    List<Country> countries = summaryResponse.getCountryList();
                    ArrayList<Country> tempList = new ArrayList<>();
                    tempList.addAll(countries);
                    setupRecycleView(countries);
                    Log.d("HKLOG","countries size: " + countries.size() );
                    for(Country c: countries){
                        Log.d("HKLOG", "Country: " + c.getCountryName() +" data: " +  c.getNewDeaths());
                    }

                } else {
                    Log.e("HKLOG", "Error: " + response.code());
                    Log.e("HKLOG", "TROUBLE FETCH COVID LIST SOURCE 1 , TRYING TO FETCH SOURCE 2");
                    // ilk kaynaktan alırken sorun çıktı alternatif kaynaktan almaya başlıcak.
                    DATA_SOURCE_MODE = Constants.API_SOURCE_MODE_ALTERNATIVE;
                    fetchAlternativeCovidData();
                }
            }

            @Override
            public void onFailure(Call<SummaryResponse> call, Throwable t) {
                Log.e("HKLOG", "Error: " + t.getMessage());
            }
        });
    }

    private void setupRecycleView(List<Country> cList){
        adapter = new CountryAdapter( cList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupAlternativeResouceRecycleView(List<CovidData> covidDataList){
        covidDataAdapter = new CovidDataAdapter(covidDataList,this);
        recyclerView.setAdapter(covidDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupView(){
        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               switch (DATA_SOURCE_MODE){
                   case Constants.API_SOURCE_MODE_MAIN:
                       adapter.getFilter().filter(s.toString());
                       break;
                   case Constants.API_SOURCE_MODE_ALTERNATIVE:
                       covidDataAdapter.getFilter().filter(s.toString());
                       break;
               }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void fetchAlternativeCovidData(){
        CovidApiService covidApiService = new CovidApiService();
        Call<List<CovidData>> call = covidApiService.getCovidApi().getCovidData();
        call.enqueue(new Callback<List<CovidData>>() {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                if (response.isSuccessful()) {
                    // API'den başarılı bir yanıt aldık
                    List<CovidData> covidDataList = response.body();
                    // Listeyi işle ve görüntüle
                    setupAlternativeResouceRecycleView(covidDataList);
                    Log.d("HKLOG","covid data list: "+ covidDataList.size());
                    for(CovidData covidData: covidDataList){
                        Log.d("HKLOG",covidData.toString());
                    }
                } else {
                    // API'den hatalı bir yanıt aldık
                    // Hata kodunu ve mesajını alabiliz.
                    int errorCode = response.code();
                    String errorMessage = response.message();
                    // Hata durumunu işle
                }
            }

            @Override
            public void onFailure(Call<List<CovidData>> call, Throwable t) {
                // API isteği başarısız oldu
                // Hata durumunu işle
            }
        });

    }

}
