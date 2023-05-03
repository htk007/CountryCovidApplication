package com.heka.countrycovidapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidData {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("date_stamp")
        private String dateStamp;

        @SerializedName("cnt_confirmed")
        private int confirmedCases;

        @SerializedName("cnt_death")
        private int deaths;

        @SerializedName("cnt_recovered")
        private int recovered;

        public String getDateStamp() {
            return dateStamp;
        }

        public void setDateStamp(String dateStamp) {
            this.dateStamp = dateStamp;
        }

        public int getConfirmedCases() {
            return confirmedCases;
        }

        public void setConfirmedCases(int confirmedCases) {
            this.confirmedCases = confirmedCases;
        }

        public int getDeaths() {
            return deaths;
        }

        public void setDeaths(int deaths) {
            this.deaths = deaths;
        }

        public int getRecovered() {
            return recovered;
        }

        public void setRecovered(int recovered) {
            this.recovered = recovered;
        }
    }
}
