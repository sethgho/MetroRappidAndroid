package co.createch.MetroRappid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seth Gholson on 5/31/14.
 */
public class RouteShape {

    @SerializedName("shape_id")
    public String shapeId;

    @SerializedName("shape_pt_lat")
    public double latitude;

    @SerializedName("shape_pt_lon")
    public double longitude;

    @SerializedName("shape_pt_sequence")
    public int sequence;

    @SerializedName("shape_dist_traveled")
    public double distanceTraveled;

}
