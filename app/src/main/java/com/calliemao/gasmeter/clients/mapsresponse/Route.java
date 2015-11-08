
package com.calliemao.gasmeter.clients.mapsresponse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Route {

    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("copyrights")
    @Expose
    private String copyrights;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs = new ArrayList<Leg>();
    @SerializedName("overview_polyline")
    @Expose
    private OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("warnings")
    @Expose
    private List<String> warnings = new ArrayList<String>();
    @SerializedName("waypoint_order")
    @Expose
    private List<Object> waypointOrder = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * 
     * @param bounds
     *     The bounds
     */
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    /**
     * 
     * @return
     *     The copyrights
     */
    public String getCopyrights() {
        return copyrights;
    }

    /**
     * 
     * @param copyrights
     *     The copyrights
     */
    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    /**
     * 
     * @return
     *     The legs
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     * 
     * @param legs
     *     The legs
     */
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    /**
     * 
     * @return
     *     The overviewPolyline
     */
    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    /**
     * 
     * @param overviewPolyline
     *     The overview_polyline
     */
    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The warnings
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /**
     * 
     * @param warnings
     *     The warnings
     */
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    /**
     * 
     * @return
     *     The waypointOrder
     */
    public List<Object> getWaypointOrder() {
        return waypointOrder;
    }

    /**
     * 
     * @param waypointOrder
     *     The waypoint_order
     */
    public void setWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

}
