package com.calliemao.gasmeter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.calliemao.gasmeter.R;
import com.calliemao.gasmeter.bus2.BusProvider;
import com.calliemao.gasmeter.bus2.GoogleGodLineEvent;
import com.calliemao.gasmeter.clients.MapsResponse;
import com.calliemao.gasmeter.ui.PolyUtil.PolyUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class PathsMap extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    private List<Polyline> lineList= new ArrayList<>();
    private List<PolylineOptions> optionsList = new ArrayList<>();
    private PolylineOptions selectedLine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paths_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap map){
        this.map = map;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MapsResponse client = MapsResponse.getInstance();
        client.makeRoute("37.316529, -122.025349", "place_id:ChIJj61dQgK6j4AR4GeTYWZsKWw", "driving");
        client.makeRoute("37.316529, -122.025349", "place_id:ChIJj61dQgK6j4AR4GeTYWZsKWw", "transit");
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng lat) {
                //TODO: One of the routes could get starved
                for (PolylineOptions p : optionsList) {
                    if (PolyUtil.isLocationOnPath(lat, p.getPoints(), true, 100)) {
                        Log.e("Selected!", "Selected!");
                        selectedLine = p;
                    }
                }
                updateLineSelection();
            }
        });
        map.setTrafficEnabled(true);
    }

    @Subscribe
    public void handleGodLineEvent(GoogleGodLineEvent event) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        PolylineOptions godLine = event.getGodLine();
        Polyline line = map.addPolyline(godLine);
        lineList.add(line);
        optionsList.add(godLine);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    private void updateLineSelection() {
        if (selectedLine != null){
            select(selectedLine);
            for (PolylineOptions p : optionsList) {
                if (!p.equals(selectedLine)) {
                   unSelect(p);
               }
            }
        }

    }

    private void select(PolylineOptions p){
        p.color(0x376390);
        Log.e("Hello", "Hello");
    }

    private void unSelect(PolylineOptions p){
        p.color(0x376878);
        /*PolylineOptions updated = new PolylineOptions();
        for (LatLng point: p.getPoints())
            updated.add(point);
        Polyline toAdd = map.addPolyline(updated.color(0x376878));*/
        //lineList.remove(p);
        //lineList.add(toAdd);
        Log.e("Bye", "Bye");
    }
}
