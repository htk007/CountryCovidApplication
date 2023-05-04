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
import com.heka.countrycovidapp.api.ApiService;
import com.heka.countrycovidapp.model.Country;

import com.heka.countrycovidapp.model.SummaryResponse;

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

    EditText searchEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
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
                    ArrayList<Country> tempList = new ArrayList<>();
                    tempList.addAll(countries);
                    setupRecycleView(countries);
                   // adapter.updateRecyclerView(countries);
                    Log.d("HEKALOG","countries size: " + countries.size() );
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

    private void setupRecycleView(List<Country> cList){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CountryAdapter( cList, this);
        recyclerView.setAdapter(adapter);
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
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
