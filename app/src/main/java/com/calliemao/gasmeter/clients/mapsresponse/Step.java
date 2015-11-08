
package com.calliemao.gasmeter.clients.mapsresponse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Step {

    @SerializedName("distance")
    @Expose
    private Distance_ distance;
    @SerializedName("duration")
    @Expose
    private Duration_ duration;
    @SerializedName("end_location")
    @Expose
    private EndLocation_ endLocation;
    @SerializedName("html_instructions")
    @Expose
    private String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    private Polyline polyline;
    @SerializedName("start_location")
    @Expose
    private StartLocation_ startLocation;
    @SerializedName("steps")
    @Expose
    private List<Step_> steps = new ArrayList<Step_>();
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("transit_details")
    @Expose
    private TransitDetails transitDetails;

    /**
     * 
     * @return
     *     The distance
     */
    public Distance_ getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Duration_ getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Duration_ duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The endLocation
     */
    public EndLocation_ getEndLocation() {
        return endLocation;
    }

    /**
     * 
     * @param endLocation
     *     The end_location
     */
    public void setEndLocation(EndLocation_ endLocation) {
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
    public Polyline getPolyline() {
        return polyline;
    }

    /**
     * 
     * @param polyline
     *     The polyline
     */
    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    /**
     * 
     * @return
     *     The startLocation
     */
    public StartLocation_ getStartLocation() {
        return startLocation;
    }

    /**
     * 
     * @param startLocation
     *     The start_location
     */
    public void setStartLocation(StartLocation_ startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * 
     * @return
     *     The steps
     */
    public List<Step_> getSteps() {
        return steps;
    }

    /**
     * 
     * @param steps
     *     The steps
     */
    public void setSteps(List<Step_> steps) {
        this.steps = steps;
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
     *     The transitDetails
     */
    public TransitDetails getTransitDetails() {
        return transitDetails;
    }

    /**
     * 
     * @param transitDetails
     *     The transit_details
     */
    public void setTransitDetails(TransitDetails transitDetails) {
        this.transitDetails = transitDetails;
    }

}
