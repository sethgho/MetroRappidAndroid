package co.createch.MetroRappid.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public class CapStop { //extend Maps marker?

    public String routeId;
    public String stopId;
    public String tripId;
    public String shapeId;
    public float latitude;
    public float longitude;
    public String name;
    public String headSing;
    public String description;
    public int stopSequence;
    public int directionId;
    public List<CapRun> trips;
    public Date lastUpdated;
}
