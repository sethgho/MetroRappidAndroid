package co.createch.MetroRappid.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class BusLocationResponseEnvelope {

    @Element(name = "Body")
    public BusLocationResponseBody body;
}
