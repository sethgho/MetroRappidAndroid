package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Created by Seth Gholson on 5/31/14.
 */

@Root(strict = false)
public class CapVehicle {
    @Element(name = "Route")
    public String routeId;

    @Element(name = "Updatetime")
    public String updateTime;

    @Element(name = "Vehicleid")
    public String vehicleId;

    @Element(name = "Inservice")
    public String inService;

    @Element(name = "Offroute")
    public String offRoute;

    @Element(name = "Heading")
    public String heading;

    @ElementList(name = "Positions", type = CapVehiclePosition.class)
    public CapVehiclePositionCollection positions;
}


