package com.heka.countrycovidapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.heka.countrycovidapp.model.CovidData;
import com.heka.countrycovidapp.util.Constants;

public class ChartDataFragment extends Fragment {

    private CovidData covidData;

    public static ChartDataFragment newInstance(CovidData covidData) {
        ChartDataFragment fragment = new ChartDataFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARCELABLE_KEY_NAME_COVID_DATA, covidData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            covidData = getArguments().getParcelable(Constants.PARCELABLE_KEY_NAME_COVID_DATA);
        }
    }


}
