package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class BusLocationResponse {

    @Element(name = "Responsecode")
//    @Path("Envelope/Body/BuslocationResponse")
    public int responseCode;

    @ElementList(name = "Vehicles", type=CapVehicle.class)
//    @Path("soap:Envelope/soap:Body/BuslocationResponse")
    public CapVehicleCollection vehicles;

}
