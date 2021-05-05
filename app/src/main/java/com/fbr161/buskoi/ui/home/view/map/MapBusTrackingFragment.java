package com.fbr161.buskoi.ui.home.view.map;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fbr161.buskoi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapBusTrackingFragment extends Fragment implements OnMapReadyCallback {

    Context context;
    //25.122436, 88.589658
    GoogleMap gMap;
    MarkerOptions markerOptions = new MarkerOptions();
    LatLng latLng = new LatLng(25.122436, 88.589658);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_bus_tracking, container, false);
        context = getContext();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment_map);

        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;
        markerOptions.position(latLng);

        markerOptions.title("Current location of bus");
        gMap.clear();
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        //first_lnch = false;

        gMap.addMarker(markerOptions);
    }
}