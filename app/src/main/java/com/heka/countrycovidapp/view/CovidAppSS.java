package com.heka.countrycovidapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.util.Constants;

public class CovidAppSS extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_covidapp_ss);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(CovidAppSS.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, Constants.SPLASH_SCREEN_TIMEOUT);
    }
}
