package co.createch.MetroRappid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public class CapStop { //extend Maps marker?
    @SerializedName("route_id")
    public String routeId;

    @SerializedName("stop_id")
    public String stopId;

    @SerializedName("trip_id")
    public String tripId;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("stop_name")
    public String name;

    @SerializedName("stop_desc")
    public String description;

    @SerializedName("stop_sequence")
    public int stopSequence;

    @SerializedName("direction_id")
    public RouteDirection direction;

    @SerializedName("trip_headsign")
    public String headsign;

    @Override
    public int hashCode() {
        return stopId.hashCode();
    }
}
