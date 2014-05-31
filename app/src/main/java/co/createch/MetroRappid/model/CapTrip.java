package co.createch.MetroRappid.model;

import com.google.gson.annotations.SerializedName;

/*
  Created by Seth Gholson on 4/20/14.
 */
public class CapTrip {
    @SerializedName("route_id")
    public String routeId;

    @SerializedName("service_id")
    public String serviceId;

    @SerializedName("trip_id")
    public String id;

    @SerializedName("trip_headsign")
    public String headsign;

    @SerializedName("trip_short_name")
    public String shortName;

    @SerializedName("direction_id")
    public RouteDirection directionId;

    @SerializedName("block_id")
    public String blockId;

    @SerializedName("shape_id")
    public String shapeId;

    @SerializedName("trip_type")
    public String tripType;

    @SerializedName("bikes_allowed")
    public int bikesAllowed;

    @SerializedName("wheelchair_accessible")
    public int wheelchairAccessible;

}
