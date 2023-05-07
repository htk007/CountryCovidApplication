package com.heka.countrycovidapp.view;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.heka.countrycovidapp.R;
import com.heka.countrycovidapp.model.CovidData;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapDataFragment extends Fragment {
    private CovidData covidData;
    private MapView mapView;
    private MapController mapController;

    public static MapDataFragment newInstance(CovidData covidData) {
        MapDataFragment fragment = new MapDataFragment();
        Bundle args = new Bundle();
        args.putParcelable("covidData", covidData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            covidData = getArguments().getParcelable("covidData");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.country_map_fragment, container, false);
        Configuration.getInstance().setUserAgentValue(getActivity().getPackageName());
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        mapController = (MapController) mapView.getController();
        try {
            Log.d("HKLOGF","SELECTED COUNTRY FOR SHOW MAP: "+covidData.getCountry());
            List<Address> addresses = geocoder.getFromLocationName(covidData.getCountry(), 1);
            Address address = addresses.get(0);
            Log.d("HKLOGF","SELECTED COUNTRY FOR SHOW MAP address: "+address.getLatitude() +" :: " + address.getLongitude());
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(address.getLatitude(), address.getLongitude()));
            mapView.getOverlays().add(marker);
            GeoPoint startPoint = new GeoPoint(address.getLatitude(), address.getLongitude());
            mapController.setCenter(startPoint);
            mapController.setZoom(7);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }
}
