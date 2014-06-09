package co.createch.MetroRappid.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public class CapStop implements Comparable, Parcelable { //extend Maps marker?
    @SerializedName("route_id")
    public String routeId;

    @SerializedName("stop_id")
    public String stopId;

    @SerializedName("trip_id")
    public String tripId;

    @SerializedName("stop_lat")
    public double latitude;

    @SerializedName("stop_lon")
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
        if (location == null) this.distance = Float.POSITIVE_INFINITY;
        float[] results = new float[3];
        Location.distanceBetween(
            location.getLatitude(),
            location.getLongitude(),
            this.latitude,
            this.longitude,
            results);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.routeId);
        dest.writeString(this.stopId);
        dest.writeString(this.tripId);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.stopSequence);
        dest.writeInt(this.direction == null ? -1 : this.direction.ordinal());
        dest.writeString(this.headsign);
        dest.writeFloat(this.distance);
        dest.writeByte(isDistanceKnown ? (byte) 1 : (byte) 0);
    }

    public CapStop() {
    }

    private CapStop(Parcel in) {
        this.routeId = in.readString();
        this.stopId = in.readString();
        this.tripId = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.name = in.readString();
        this.description = in.readString();
        this.stopSequence = in.readInt();
        int tmpDirection = in.readInt();
        this.direction = tmpDirection == -1 ? null : RouteDirection.values()[tmpDirection];
        this.headsign = in.readString();
        this.distance = in.readFloat();
        this.isDistanceKnown = in.readByte() != 0;
    }

    public static Parcelable.Creator<CapStop> CREATOR = new Parcelable.Creator<CapStop>() {
        public CapStop createFromParcel(Parcel source) {
            return new CapStop(source);
        }

        public CapStop[] newArray(int size) {
            return new CapStop[size];
        }
    };

    public MarkerOptions getMarker() {
        MarkerOptions option = new MarkerOptions()
                .position(new LatLng(latitude,longitude))
                .draggable(false)
                .title(name)
                .snippet(description);
        return option;
    }
}

