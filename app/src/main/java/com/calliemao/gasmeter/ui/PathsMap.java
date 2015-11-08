package com.calliemao.gasmeter.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.calliemao.gasmeter.R;
import com.calliemao.gasmeter.bus2.BusProvider;
import com.calliemao.gasmeter.bus2.GoogleGodLineEvent;
import com.calliemao.gasmeter.clients.MapsResponse;
import com.calliemao.gasmeter.ui.PolyUtil.PolyUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
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
    private Polyline selectedLine = null;

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
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.5, -122.02), 10));
        map.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng lat) {
                //TODO: One of the routes could get starved
                for (Polyline p : lineList) {
                    if (PolyUtil.isLocationOnPath(lat, p.getPoints(), true, 10)) {
                        Log.e("Selected!", "Selected!");
                        selectedLine = p;
                        break;
                    }
                }
                updateLineSelection();
            }
        });
        //map.setTrafficEnabled(true);
    }

    @Subscribe
    public void handleGodLineEvent(GoogleGodLineEvent event) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        PolylineOptions godLine = event.getGodLine();
        Polyline line = map.addPolyline(godLine.color(Color.BLUE));
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
            select();
            unselect();
            Log.e("size", String.valueOf(optionsList.size()));
        }
    }

    private void select(){
        selectedLine.setColor(Color.RED);
    }

    private void unselect(){
        for (Polyline l: lineList){
            if (l!=selectedLine){
                l.setColor(Color.GREEN);
                l.setWidth(4);
            }
        }
    }

   /* private void select(Polyline p){
        for (Polyline l: lineList)
            l.setColor(Color.RED);
        Log.e("Hello", "Hello");
    }

    private void unselect(Polyline p) {
        p.color(Color.GREEN);
        map.addPolyline(p);
        for (Polyline l: lineList)
                l.setColor(Color.RED);
        //PolylineOptions updated = new PolylineOptions().addAll(p.getPoints());
        //Polyline toAdd = map.addPolyline(updated.color(2376878));
        //lineList.remove(p);
        //lineList.add(toAdd);
        Log.e("Bye", "Bye");
    }*/
}
