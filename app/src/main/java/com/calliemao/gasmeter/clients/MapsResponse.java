package com.calliemao.gasmeter.clients;

import android.util.Log;

import com.calliemao.gasmeter.bus2.BusProvider;
import com.calliemao.gasmeter.bus2.GoogleGodLineEvent;
import com.calliemao.gasmeter.clients.mapsresponse.Leg;
import com.calliemao.gasmeter.clients.mapsresponse.Step;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by myungoh on 11/8/15.
 */
public class MapsResponse {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/directions";
    private static final String API_KEY = "AIzaSyCjnY7m8hXu7jZv3eQw6EdZxm3Sd7uvOnY";
    private PolylineOptions theGodLine;
    private double duration;
    private double footPrint;
    private double distance;
    private static final MapsResponse restClient = new MapsResponse();

    private Bus eventBus;

    private MapsResponse() {

    }

    public static MapsResponse getInstance() {
        restClient.eventBus = BusProvider.getInstance();
        restClient.eventBus.register(restClient);
        return restClient;
    }
    public interface MapsService{
        @GET("/{method}")
        void getDirections(
                @Path("method") String method,
                @Query("origin") String origin,
                @Query("destination") String destination,
                @Query("mode") String mode,
                @Query("departure_time") Long departureTime,
                @Query("key") String key,
                Callback<com.calliemao.gasmeter.clients.mapsresponse.MapsResponse> mapsResponseCallback);
    }

    public double[] findDuration(String origin, String destination, String mode, long departureTime){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        MapsService service = restAdapter.create(MapsService.class);
        duration = 0; footPrint = 0; distance = 0;
        service.getDirections("json", origin, destination, mode, departureTime, API_KEY,
                new Callback<com.calliemao.gasmeter.clients.mapsresponse.MapsResponse>() {
                    @Override
                    public void success(com.calliemao.gasmeter.clients.mapsresponse.MapsResponse mapsResponse, Response response) {
                        List<Leg> legs = mapsResponse.getRoutes().get(0).getLegs();
                        for(Leg l: legs){
                            duration += l.getDuration().getValue();
                        }
                        for (Leg l: legs){
                            List<Step> steps = l.getSteps();
                            for(Step s: steps){
                                double dis = s.getDistance().getValue();
                                double averageVelocity = distance/s.getDuration().getValue() * 2.24;//this is m/s
                                double efficiency = findEfficiency(averageVelocity);
                                footPrint += distance * 1.6/efficiency;
                                distance += dis;
                            }
                        }
                        footPrint = footPrint * 17.9;
                    }

                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Polyline", error.getCause());
                    }
                }
        );
        return new double[]{duration, footPrint, distance};
    }

    public void makeRoute(String origin, String destination, String mode){
        theGodLine = new PolylineOptions();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        MapsService service = restAdapter.create(MapsService.class);
        service.getDirections("json", origin, destination, mode, null, API_KEY,
                new Callback<com.calliemao.gasmeter.clients.mapsresponse.MapsResponse>() {
                    @Override
                    public void success(com.calliemao.gasmeter.clients.mapsresponse.MapsResponse mapsResponse, Response response) {
                        List<Step> steps = mapsResponse.getRoutes().get(0).getLegs().get(0).getSteps();
                        for (Step s : steps) {
                            List<LatLng> points = decodePoly(s.getPolyline().getPoints());
                            Log.e(String.valueOf(points.size()), String.valueOf(points.size()));
                            for (LatLng p : points) {
                                theGodLine.add(p);
                            }
                            Log.e(String.valueOf(steps.size()), String.valueOf(steps.size()));
                        }
                        eventBus.post(new GoogleGodLineEvent(GoogleGodLineEvent.Type.COMPLETED, theGodLine));
                    }

                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Polyline", error.getCause());
                    }
                }
        );
    }

    private double findEfficiency(double speed){
        double efficiency = 0;
        if (speed < 28){
            efficiency = 11.25 + .7 * speed;
        }
        if (speed>=28 && speed <= 58){
            efficiency = 33;
        }
        if (speed >58){
            efficiency = 33 - .7 * (speed-58);
        }
        return efficiency;
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<>();
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
