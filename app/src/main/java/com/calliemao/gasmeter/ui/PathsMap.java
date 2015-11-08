package com.calliemao.gasmeter.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

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

    public TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paths_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textview = (TextView) findViewById(R.id.path_text);
        setSupportActionBar(toolbar);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap map){
        this.map = map;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MapsResponse client = MapsResponse.getInstance();
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.316529, -122.02534))
                .title("Marker"));
        map.addMarker(new MarkerOptions().position(new LatLng(37.421987, -122.084086)));
        client.makeRoute("37.316529, -122.025349", "place_id:ChIJj61dQgK6j4AR4GeTYWZsKWw", "driving");
        client.makeRoute("37.316529, -122.025349", "place_id:ChIJj61dQgK6j4AR4GeTYWZsKWw", "walking");
        double[] metrics = client.findDuration("37.316529, -122.025349", "place_id:ChIJj61dQgK6j4AR4GeTYWZsKWw", "driving", System.currentTimeMillis() / 1000L);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.37, -122.05), 9));
        map.animateCamera(CameraUpdateFactory.zoomTo(11), 2000, null);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng lat) {
                //TODO: One of the routes could get starved
                for (Polyline p : lineList) {
                    if (PolyUtil.isLocationOnPath(lat, p.getPoints(), true, 40)) {
                        Log.e("Selected!", "Selected!");
                        selectedLine = p;
                        textview.setText(R.string.new_path_text);
                        break;
                    }
                }
                updateLineSelection();
            }
        });
    }

    @Subscribe
    public void handleGodLineEvent(GoogleGodLineEvent event) {
        PolylineOptions godLine = event.getGodLine();
        Polyline line = map.addPolyline(godLine.color(Color.YELLOW));
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

    private long findOptimalTime(){
        long unixTime = System.currentTimeMillis() / 1000L;
        double minEmission = Integer.MAX_VALUE;
        long minTime = unixTime;
        for (int i = 0; i < 8; i++){
            double currentEmission = MapsResponse.getInstance().findDuration("37.316529, -122.025349", "place_id:ChIJj61dQgK6j4AR4GeTYWZsKWw", "driving",
                    i * 900 + unixTime)[1];
            if (currentEmission < minEmission){
                minTime = unixTime + i*900;
                minEmission = currentEmission;
            }
        }
        return minTime;
    }

    private void select(){
        selectedLine.setColor(Color.BLUE);
    }

    private void unselect(){
        for (Polyline l: lineList){
            if (l!=selectedLine){
                l.setColor(Color.GRAY);
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
