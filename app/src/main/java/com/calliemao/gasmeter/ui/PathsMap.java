package com.calliemao.gasmeter.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.calliemao.gasmeter.R;
import com.calliemao.gasmeter.clients.mapsresponse.MapsResponse;
import com.calliemao.gasmeter.clients.mapsresponse.Polyline;
import com.calliemao.gasmeter.clients.mapsresponse.Step;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit.*;
import retrofit.client.Response;
import retrofit.http.*;

public class PathsMap extends AppCompatActivity implements OnMapReadyCallback {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/directions/json";
    private PolylineOptions theGodLine;
    public interface MapsService{
        @GET("/{method}&key=AIzaSyCjnY7m8hXu7jZv3eQw6EdZxm3Sd7uvOnY")
        void getDirections(
                @Path("method") String method,
                @Query("origin") String origin,
                @Query("destination") String destination,
                @Query("mode") String mode,
                Callback<MapsResponse> mapsResponseCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paths_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap map){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        makeRoute();
        map.addPolyline(theGodLine);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }

    private void makeRoute(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        MapsService service = restAdapter.create(MapsService.class);
        service.getDirections("", "37.316529, -122.025349", "Googleplex", "driving",
                new Callback<MapsResponse>() {
                    @Override
                    public void success(MapsResponse mapsResponse, Response response) {
                        List<Step> steps = mapsResponse.getRoutes().get(0).getLegs().get(0).getSteps();
                        for (Step s: steps){
                            List<LatLng> points = decodePoly(s.getPolyline().getPoints());
                            for (LatLng p : points){
                                theGodLine.add(p);
                            }
                        }
                    }
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Polyline", error.getCause());
                    }
                }
        );
    }
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng point = new LatLng((double) lat / 1E5,
                    (double) lng / 1E5);
            poly.add(point);
        }

        return poly;
    }

}
