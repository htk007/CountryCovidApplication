package com.heka.countrycovidapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Country implements Parcelable {
    @SerializedName("Country")
    private String countryName;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Slug")
    private String slug;
    @SerializedName("NewConfirmed")
    private int newConfirmed;
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;
    @SerializedName("NewDeaths")
    private int newDeaths;
    @SerializedName("TotalDeaths")
    private int totalDeaths;
    @SerializedName("NewRecovered")
    private int newRecovered;
    @SerializedName("TotalRecovered")
    private int totalRecovered;
    @SerializedName("Date")
    private String date;

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSlug() {
        return slug;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public String getDate() {
        return date;
    }

    protected Country(Parcel in) {
        countryName = in.readString();
        totalConfirmed = in.readInt();
        totalDeaths = in.readInt();
        totalRecovered = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(countryName);
        dest.writeInt(totalConfirmed);
        dest.writeInt(totalDeaths);
        dest.writeInt(totalRecovered);
    }


}
