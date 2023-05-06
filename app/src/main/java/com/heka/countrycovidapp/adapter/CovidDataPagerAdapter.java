package com.heka.countrycovidapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.heka.countrycovidapp.model.CovidData;
import com.heka.countrycovidapp.util.Constants;
import com.heka.countrycovidapp.view.DetailDataFragment;
import com.heka.countrycovidapp.view.MapDataFragment;

public class CovidDataPagerAdapter  extends FragmentPagerAdapter {

    private static final int NUM_PAGES = Constants.TOTAL_FRAGMENT_PAGES;
    private CovidData covidData;

    public CovidDataPagerAdapter(@NonNull FragmentManager fm, CovidData covidData) {
        super(fm);
        this.covidData = covidData;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DetailDataFragment.newInstance(covidData);
            case 1:
                return new MapDataFragment();
           /* case 2:
                return new ThirdFragment();*/
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Detail Data";
            case 1:
                return "Map Data";
        /*case 2:
            return "Third Tab";*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
