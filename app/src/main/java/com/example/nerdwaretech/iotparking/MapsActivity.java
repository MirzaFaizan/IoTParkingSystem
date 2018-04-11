package com.example.nerdwaretech.iotparking;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(33.714095, 73.027872);
        mMap.addMarker(new MarkerOptions().position(sydney).title("BUIC Parking")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng airuni = new LatLng(33.713211, 73.023092);
        mMap.addMarker(new MarkerOptions().position(airuni).title("AIR University Parking"));

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("BUIC Parking")){
                    Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("title","BUIC Parking");
                    startActivity(intent);
                }
                if(marker.getTitle().equals("AIR University Parking")){
                    Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("title","AIR University Parking");
                    startActivity(intent);
                }
                return false;
            }

        });

    }


}