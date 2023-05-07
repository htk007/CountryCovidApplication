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
import com.heka.countrycovidapp.adapter.CovidDataPagerAdapter;
import com.heka.countrycovidapp.model.Country;
import com.heka.countrycovidapp.model.CovidData;


public class CountryDetailActivity extends AppCompatActivity {

    private TextView countryNameTextView;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_detail);

        countryNameTextView = findViewById(R.id.countryNameTextView);
        viewPager = findViewById(R.id.viewPagerCovidData);
        tabLayout = findViewById(R.id.tabLayoutCovidData);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        if (intent != null) {
            String selectedCountry = intent.getStringExtra("selectedCountry");

            CovidData selectedCountryData = getIntent().getParcelableExtra("selectedCountryData");
            viewPager.setAdapter(new CovidDataPagerAdapter(getSupportFragmentManager(), selectedCountryData));
            countryNameTextView.setText(selectedCountry);
        }
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}

