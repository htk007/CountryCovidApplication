package com.heka.countrycovidapp.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.model.Country;
import com.heka.countrycovidapp.model.CovidData;


public class CountryDetailActivity extends AppCompatActivity {

    private TextView countryNameTextView;
    private TextView totalConfirmedTextView;
    private TextView totalDeathsTextView;
    private TextView totalRecoveredTextView;
    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        // XML dosyasında bulunan viewleri initialize ediyoruz
        countryNameTextView = findViewById(R.id.countryNameTextView);
        totalConfirmedTextView = findViewById(R.id.totalConfirmedTextViewD);
        totalDeathsTextView = findViewById(R.id.totalDeathsTextViewD);
        totalRecoveredTextView = findViewById(R.id.totalRecoveredTextViewD);

        // MainActivity'den seçili ülkeyi alıyoruz
     //   country = getIntent().getParcelableExtra("selectedCountry");

        Intent intent = getIntent();
        if (intent != null) {
            String selectedCountry = intent.getStringExtra("selectedCountry");

            CovidData selectedCountryData = getIntent().getParcelableExtra("selectedCountryData");

            countryNameTextView.setText(selectedCountry);
            Log.d("HKLOG",":: " +selectedCountryData.toString());
            countryNameTextView.setText("Country Name: "+selectedCountryData.getCountry());
            totalConfirmedTextView.setText("Total Confirmed: "+selectedCountryData.getTotalConfirmed());
            totalDeathsTextView.setText("Total Deaths: "+selectedCountryData.getTotalDeaths());
            totalRecoveredTextView.setText("Total Recovered: " +selectedCountryData.getTotalRecovered());

        }

        if(country != null){
            countryNameTextView.setText(country.getCountryName());
        }else{
            Toast.makeText(this, "country boş: "+ country, Toast.LENGTH_SHORT).show();
        }


        // TabLayout ve ViewPager'ı initialize ediyoruz
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // PagerAdapter'ı oluşturuyoruz ve viewPager'a set ediyoruz
        //TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), country);
        //viewPager.setAdapter(pagerAdapter);

        // ViewPager'ı TabLayout'a bağlıyoruz
        tabLayout.setupWithViewPager(viewPager);
    }
}

