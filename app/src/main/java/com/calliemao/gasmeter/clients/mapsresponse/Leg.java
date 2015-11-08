
package com.calliemao.gasmeter.clients.mapsresponse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Leg {

    @SerializedName("arrival_time")
    @Expose
    private ArrivalTime arrivalTime;
    @SerializedName("departure_time")
    @Expose
    private DepartureTime departureTime;
    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("duration")
    @Expose
    private Duration duration;
    @SerializedName("end_address")
    @Expose
    private String endAddress;
    @SerializedName("end_location")
    @Expose
    private EndLocation endLocation;
    @SerializedName("start_address")
    @Expose
    private String startAddress;
    @SerializedName("start_location")
    @Expose
    private StartLocation startLocation;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = new ArrayList<Step>();
    @SerializedName("via_waypoint")
    @Expose
    private List<Object> viaWaypoint = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The arrivalTime
     */
    public ArrivalTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 
     * @param arrivalTime
     *     The arrival_time
     */
    public void setArrivalTime(ArrivalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 
     * @return
     *     The departureTime
     */
    public DepartureTime getDepartureTime() {
        return departureTime;
    }

    /**
     * 
     * @param departureTime
     *     The departure_time
     */
    public void setDepartureTime(DepartureTime departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The endAddress
     */
    public String getEndAddress() {
        return endAddress;
    }

    /**
     * 
     * @param endAddress
     *     The end_address
     */
    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    /**
     * 
     * @return
     *     The endLocation
     */
    public EndLocation getEndLocation() {
        return endLocation;
    }

    /**
     * 
     * @param endLocation
     *     The end_location
     */
    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * 
     * @return
     *     The startAddress
     */
    public String getStartAddress() {
        return startAddress;
    }

    /**
     * 
     * @param startAddress
     *     The start_address
     */
    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    /**
     * 
     * @return
     *     The startLocation
     */
    public StartLocation getStartLocation() {
        return startLocation;
    }

    /**
     * 
     * @param startLocation
     *     The start_location
     */
    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * 
     * @return
     *     The steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * 
     * @param steps
     *     The steps
     */
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    /**
     * 
     * @return
     *     The viaWaypoint
     */
    public List<Object> getViaWaypoint() {
        return viaWaypoint;
    }

    /**
     * 
     * @param viaWaypoint
     *     The via_waypoint
     */
    public void setViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }

}
