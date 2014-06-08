package co.createch.MetroRappid.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import co.createch.MetroRappid.model.CapVehicle;
import co.createch.MetroRappid.model.CapVehiclePosition;

public class CapVehicleTest extends TestCase {

    public void test_deserialization_valid_xml() throws Exception {
        Serializer serializer = new Persister();
        CapVehicle vehicle = serializer.read(CapVehicle.class, "<?xml version=\"1.0\"?>\n" +
                "                <Vehicle>\n" +
                "                    <Route>801</Route>\n" +
                "                    <Direction>N</Direction>\n" +
                "                    <Updatetime>09:42 PM</Updatetime>\n" +
                "                    <Vehicleid>5016</Vehicleid>\n" +
                "                    <Block>801-91</Block>\n" +
                "                    <Adherance>-10</Adherance>\n" +
                "                    <Adhchange>S</Adhchange>\n" +
                "                    <Reliable>Y</Reliable>\n" +
                "                    <Offroute>N</Offroute>\n" +
                "                    <Stopped>N</Stopped>\n" +
                "                    <Inservice>Y</Inservice>\n" +
                "                    <Speed>15.07</Speed>\n" +
                "                    <Heading> 3</Heading>\n" +
                "                    <Routeid>44916</Routeid>\n" +
                "                    <Positions>\n" +
                "                        <Position>30.371565,-97.692177</Position>\n" +
                "                        <Position>30.366175,-97.695213</Position>\n" +
                "                        <Position>30.362627,-97.697182</Position>\n" +
                "                        <Position>30.354074,-97.704491</Position>\n" +
                "                        <Position>30.347145,-97.712601</Position>\n" +
                "                        <Position>30.349207,-97.711685</Position>\n" +
                "                    </Positions>\n" +
                "                </Vehicle>\n");
        Assert.assertEquals("801", vehicle.routeId);
        Assert.assertEquals("09:42 PM", vehicle.updateTime);
        Assert.assertEquals(6, vehicle.positions.size());
        CapVehiclePosition firstpos = vehicle.positions.get(0);
        Assert.assertEquals(30d, vehicle.positions.get(0).getLatLng().latitude);
    }
}
