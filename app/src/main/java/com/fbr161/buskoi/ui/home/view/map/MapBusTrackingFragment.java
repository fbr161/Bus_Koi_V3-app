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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.model.BusLocation;
import com.fbr161.buskoi.ui.home.backed.viewmodel.ViewModel_BookedBus_Home;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapBusTrackingFragment extends Fragment implements OnMapReadyCallback {

    Context context;
    ViewModel_BookedBus_Home viewModel_bookedBus_home;


    //25.122436, 88.589658
    GoogleMap gMap;
    MarkerOptions markerOptions = new MarkerOptions();
    LatLng latLng = new LatLng(25.122436, 88.589658);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_bus_tracking, container, false);
        context = getContext();
        viewModel_bookedBus_home = ViewModelProviders.of(requireActivity()).get(ViewModel_BookedBus_Home.class);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment_map);

        mapFragment.getMapAsync(this);

        if(getArguments()!=null) {
            int i = getArguments().getInt("index");

            BookedBus bookedBus = viewModel_bookedBus_home.getBookedBusList_LiveData().getValue().get(i);
            Log.d("wtfff", bookedBus.getSchedule_id());

            viewModel_bookedBus_home.updateLocation(bookedBus.getSchedule_id());

            viewModel_bookedBus_home.getBusLocation_LiveData().observe(getViewLifecycleOwner(), new Observer<BusLocation>() {
                @Override
                public void onChanged(BusLocation busLocation) {

                    LatLng latLng = new LatLng(busLocation.getLast_Lat(), busLocation.getLast_Lon());

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("Current location of bus");

                    if(gMap != null) {
                        gMap.clear();
                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        gMap.addMarker(markerOptions);
                    }
                }
            });
        }

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

        //gMap.addMarker(markerOptions);
    }
}