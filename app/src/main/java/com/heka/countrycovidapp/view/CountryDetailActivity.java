package com.heka.countrycovidapp.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.model.Country;


public class CountryDetailActivity extends AppCompatActivity {

    private TextView countryNameTextView;
    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        // XML dosyasında bulunan viewleri initialize ediyoruz
        countryNameTextView = findViewById(R.id.countryNameTextView);

        // MainActivity'den seçili ülkeyi alıyoruz
        country = getIntent().getParcelableExtra("selectedCountry");

        if(country != null){
            // Ülke adını textview'a set ediyoruz
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

