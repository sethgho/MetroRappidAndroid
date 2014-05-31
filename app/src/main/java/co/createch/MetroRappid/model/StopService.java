package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class StopService {
    @Element(name = "Route")
    public String route;

    @Element(name = "Sign")
    public String sign;

    @Element(name = "Direction")
    public String direction;

    @ElementList(inline=true, entry="Tripinfo")
    public List<TripInfo> trips;

}
