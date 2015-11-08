
package com.calliemao.gasmeter.clients.mapsresponse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Line {

    @SerializedName("agencies")
    @Expose
    private List<Agency> agencies = new ArrayList<Agency>();
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("text_color")
    @Expose
    private String textColor;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;

    /**
     * 
     * @return
     *     The agencies
     */
    public List<Agency> getAgencies() {
        return agencies;
    }

    /**
     * 
     * @param agencies
     *     The agencies
     */
    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    /**
     * 
     * @return
     *     The color
     */
    public String getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 
     * @param shortName
     *     The short_name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 
     * @return
     *     The textColor
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * 
     * @param textColor
     *     The text_color
     */
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * 
     * @param vehicle
     *     The vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
