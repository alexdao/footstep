
package com.calliemao.gasmeter.clients.mapsresponse;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Step_ {

    @SerializedName("distance")
    @Expose
    private Distance__ distance;
    @SerializedName("duration")
    @Expose
    private Duration__ duration;
    @SerializedName("end_location")
    @Expose
    private EndLocation__ endLocation;
    @SerializedName("html_instructions")
    @Expose
    private String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    private Polyline_ polyline;
    @SerializedName("start_location")
    @Expose
    private StartLocation__ startLocation;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("maneuver")
    @Expose
    private String maneuver;

    /**
     * 
     * @return
     *     The distance
     */
    public Distance__ getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Distance__ distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Duration__ getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Duration__ duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The endLocation
     */
    public EndLocation__ getEndLocation() {
        return endLocation;
    }

    /**
     * 
     * @param endLocation
     *     The end_location
     */
    public void setEndLocation(EndLocation__ endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * 
     * @return
     *     The htmlInstructions
     */
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    /**
     * 
     * @param htmlInstructions
     *     The html_instructions
     */
    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    /**
     * 
     * @return
     *     The polyline
     */
    public Polyline_ getPolyline() {
        return polyline;
    }

    /**
     * 
     * @param polyline
     *     The polyline
     */
    public void setPolyline(Polyline_ polyline) {
        this.polyline = polyline;
    }

    /**
     * 
     * @return
     *     The startLocation
     */
    public StartLocation__ getStartLocation() {
        return startLocation;
    }

    /**
     * 
     * @param startLocation
     *     The start_location
     */
    public void setStartLocation(StartLocation__ startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * 
     * @return
     *     The travelMode
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * 
     * @param travelMode
     *     The travel_mode
     */
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * 
     * @return
     *     The maneuver
     */
    public String getManeuver() {
        return maneuver;
    }

    /**
     * 
     * @param maneuver
     *     The maneuver
     */
    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

}
