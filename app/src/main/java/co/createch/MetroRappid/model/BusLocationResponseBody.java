package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class BusLocationResponseBody {

    @Element(name = "BuslocationResponse")
    public BusLocationResponse response;
}
