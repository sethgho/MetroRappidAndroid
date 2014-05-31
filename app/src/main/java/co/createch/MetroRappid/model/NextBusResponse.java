package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class NextBusResponse
{
    @Element(name="Version")
    public String version;

    @Element(name="Responsecode")
    public int responseCode;

    @Element(name="Atstop")
    public StopResponse stop;

}
