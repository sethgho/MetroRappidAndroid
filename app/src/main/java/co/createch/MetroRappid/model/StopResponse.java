package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class StopResponse {
    @Element(name = "Description")
    public String description;

    @Element(name = "Area")
    public String area;

    @Element(name = "Stopid")
    public String stopId;

    @Element(name = "Lat")
    public double latitude;

    @Element(name = "Long")
    public double longitude;

    @Element(name = "Service")
    public StopService service;
}


