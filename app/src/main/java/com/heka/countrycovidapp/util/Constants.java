package com.heka.countrycovidapp.util;

public final class Constants {
    public static final String BASE_URL ="https://api.covid19api.com/";
    public static final String ALTERNATIVE_BASE_URL ="https://disease.sh/v3/covid-19/";
    public static final int API_SOURCE_MODE_MAIN=0;
    public static final int API_SOURCE_MODE_ALTERNATIVE=1;
    public static final int TOTAL_FRAGMENT_PAGES=2;
    public static final int SPLASH_SCREEN_TIMEOUT=3000;
    public static final String PARCELABLE_KEY_NAME_COVID_DATA="covidData";
    public static final String GET_COMMAND_SUMMARY="summary";
    public static final String GET_COMMAND_COUNTRIES="countries";
    public static final String INTENT_KEY_SELECTED_COUNTRY_DATA="selectedCountryData";

}
