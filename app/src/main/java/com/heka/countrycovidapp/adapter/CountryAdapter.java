package com.heka.countrycovidapp.adapter;

import android.content.Context;
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
import com.heka.countrycovidapp.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {

    private List<Country> countryList;
    private List<Country> filteredList;
    private Context context;

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.filteredList = countryList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = filteredList.get(position);
        holder.countryTextView.setText(country.getCountryName());
        holder.totalCasesTextView.setText(String.valueOf(country.getTotalConfirmed()));
        holder.totalDeathsTextView.setText(String.valueOf(country.getTotalDeaths()));
        holder.totalRecoveredTextView.setText(String.valueOf(country.getTotalRecovered()));
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
                    ArrayList<Country> tempList = new ArrayList<>();
                    for (Country country : countryList) {
                        if (country.getCountryName().toLowerCase().contains(searchText)) {
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
                filteredList = (ArrayList<Country>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public void updateRecyclerView(List<Country> newList) {
        if (countryList == null) {
            countryList = new ArrayList<>();
        }
        countryList.clear();
        countryList.addAll(newList);
        notifyDataSetChanged();
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
}
