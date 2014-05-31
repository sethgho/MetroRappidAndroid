package co.createch.MetroRappid.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class ResponseEnvelope
{
    @Element(name="Body")
    public StopResponseBody body;

    @Attribute
    public String encodingStyle;
}
