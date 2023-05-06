package com.heka.countrycovidapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.model.Country;
import com.heka.countrycovidapp.model.CovidData;
import com.heka.countrycovidapp.view.CountryDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CovidDataAdapter  extends RecyclerView.Adapter<CovidDataAdapter.ViewHolder> implements Filterable {
    private List<CovidData> countryList;
    private List<CovidData> filteredList;
    private Context context;

    public CovidDataAdapter(List<CovidData> countryList, Context context) {
        this.countryList = countryList;
        this.filteredList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CovidDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CovidDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidData country = filteredList.get(position);
        holder.countryTextView.setText(country.getCountry());
        holder.totalCasesTextView.setText(String.valueOf(country.getTotalConfirmed()));
        holder.totalDeathsTextView.setText(String.valueOf(country.getTotalDeaths()));
        holder.totalRecoveredTextView.setText(String.valueOf(country.getTotalRecovered()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryDetailActivity.class);
                Log.d("HKLOG", "seçilen country:   "+ country.getCountry());
                intent.putExtra("selectedCountry", country.getCountry());
                intent.putExtra("selectedCountryData", country);
                context.startActivity(intent);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryTextView;
        TextView totalCasesTextView;
        TextView totalDeathsTextView;
        TextView totalRecoveredTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
            totalCasesTextView = itemView.findViewById(R.id.totalCasesTextView);
            totalDeathsTextView = itemView.findViewById(R.id.totalDeathsTextView);
            totalRecoveredTextView = itemView.findViewById(R.id.totalRecoveredTextView);
        }
    }

    @Override
    public int getItemCount() {
        return filteredList == null ? 0 : filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString().toLowerCase();
                if (searchText.isEmpty()) {
                    filteredList = countryList;
                } else {
                    ArrayList<CovidData> tempList = new ArrayList<>();
                    for (CovidData country : countryList) {
                        if (country.getCountry().toLowerCase().contains(searchText)) {
                            tempList.add(country);
                        }
                    }
                    filteredList = tempList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<CovidData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
