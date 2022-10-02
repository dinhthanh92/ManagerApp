package com.example.managerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.managerapp.ApiService.ApiService;
import com.example.managerapp.Model.ApiResponseResult;
import com.example.managerapp.Model.LatLngModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewActivity extends SetMenuOption implements OnMapReadyCallback {
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        Button btnBack = findViewById(R.id.back_screen_view_map);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intents = new Intent(MapViewActivity.this,
                        DestinationActivity.class);
                intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intents);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map_view);

        mapFragment.getMapAsync( MapViewActivity.this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
        loadLatLog(mMap);
    }

    public void loadLatLog(GoogleMap mMaps){
        ApiService.apiService.getLatLogByDestinationId(MainActivity.GetStoreDataTrip()).enqueue(new Callback<ApiResponseResult<List<LatLngModel>>>() {
            @Override
            public void onResponse(Call<ApiResponseResult<List<LatLngModel>>> call, Response<ApiResponseResult<List<LatLngModel>>> response) {

                if(response.code() == 200){
                    List<LatLngModel> result = response.body().results;
                    LatLng latLng;
                    if(result.size() > 0)
                    for (int i = 0; i < result.size(); i++){
                        latLng = new LatLng(result.get(i).getLatitude(), result.get(i).getLongitude());
                        if(i == 0) mMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                        mMaps.addMarker(new MarkerOptions().title("Test").snippet("123").position(latLng));
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponseResult<List<LatLngModel>>> call, Throwable t) {

            }
        });
    }
}