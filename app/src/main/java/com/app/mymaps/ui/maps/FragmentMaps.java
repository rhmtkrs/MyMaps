package com.app.mymaps.ui.maps;
//10120150 - Rahmat - IF4

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.app.mymaps.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MapView mapView;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        setupMap();
    }

    private void setupMap() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        googleMap.setMyLocationEnabled(true);

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(requireActivity(), location -> {
            if (location != null) {
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

                // Pin additional locations
                pinLocations();
            }
        });
    }

    private void pinLocations() {
        List<PinData> pinDataList = new ArrayList<>();
        pinDataList.add(new PinData(new LatLng(-6.8892450,107.6175460), "Warung Nasi Ibu Iyus (Warung Nasi Langganan Best Seller)"));
        pinDataList.add(new PinData(new LatLng(-6.888940,107.618411), "Wakrop ADD (Warkop Langganan)"));
        pinDataList.add(new PinData(new LatLng(-6.898031,107.6135153), "Gacoan Dipatiukur (Best Gacoan)"));
        pinDataList.add(new PinData(new LatLng(-6.8965802,107.6129672), "Bebek Carok Bandung (Bebek Chuaks"));
        pinDataList.add(new PinData(new LatLng(-6.8865605,107.6151027), "Warung Nasi SPG ( Warung Nasi Langganan kalo di Kampus )"));

        for (PinData pinData : pinDataList) {
            googleMap.addMarker(new MarkerOptions().position(pinData.getLatLng()).title(pinData.getTitle()));
        }
    }

    private static class PinData {
        private LatLng latLng;
        private String title;

        public PinData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        public LatLng getLatLng() {
            return latLng;
        }

        public String getTitle() {
            return title;
        }
    }
}