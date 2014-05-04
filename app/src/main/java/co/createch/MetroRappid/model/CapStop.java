package co.createch.MetroRappid.model;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public class CapStop implements Comparable { //extend Maps marker?
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

    public float distance;
    private boolean isDistanceKnown;

    @Override
    public int hashCode() {
        return stopId.hashCode();
    }

    public void calculateDistanceFromLocation(Location location) {
        float[] results = new float[3];
        Location.distanceBetween(location.getLatitude(),location.getLongitude(),this.latitude,this.longitude, results);
        if(results.length > 0){
            isDistanceKnown = true;
            this.distance = results[0];
        }
    }

    @Override
    public int compareTo(Object another) {
        CapStop other = (CapStop)another;
        if(this.distance > other.distance) {
            return 1;
        }else if(this.distance < other.distance) {
            return -1;
        }else {
            return 0;
        }
    }

    public boolean knowsDistance() {
        return isDistanceKnown;
    }
}
