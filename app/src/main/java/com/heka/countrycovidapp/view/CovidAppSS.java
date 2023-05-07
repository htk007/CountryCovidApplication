package com.heka.countrycovidapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.heka.countrycovidapp.R;

public class CovidAppSS extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash ekran layout'unu ekle
        setContentView(R.layout.activity_covidapp_ss);

        // 3 saniye bekle ve MainActivity'ye geçiş yap
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CovidAppSS.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
