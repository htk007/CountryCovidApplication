package com.heka.countrycovidapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.model.Country;
import com.heka.countrycovidapp.model.CovidData;
import com.heka.countrycovidapp.util.Constants;

public class DetailDataFragment extends Fragment {

    private CovidData covidData;

    private TextView totalConfirmedTextView;
    private TextView totalDeathsTextView;
    private TextView totalRecoveredTextView;

    public static DetailDataFragment newInstance(CovidData covidData) {
        DetailDataFragment fragment = new DetailDataFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.detail_data_fragment, container, false);

        totalConfirmedTextView = rootView.findViewById(R.id.totalConfirmedTextViewD);
        totalDeathsTextView = rootView.findViewById(R.id.totalDeathsTextViewD);
        totalRecoveredTextView = rootView.findViewById(R.id.totalRecoveredTextViewD);

        totalConfirmedTextView.setText("Total Confirmed: "+covidData.getTotalConfirmed());
        totalDeathsTextView.setText("Total Deaths: "+covidData.getTotalDeaths());
        totalRecoveredTextView.setText("Total Recovered: " +covidData.getTotalRecovered());

        return rootView;
    }
}
