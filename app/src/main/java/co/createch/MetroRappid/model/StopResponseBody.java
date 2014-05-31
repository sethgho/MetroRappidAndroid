package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;

public class StopResponseBody
{
    @Element(name="NextbusResponse")
    public NextBusResponse response;
}
