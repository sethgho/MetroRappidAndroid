package co.createch.MetroRappid.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import co.createch.MetroRappid.model.StopResponse;
import co.createch.MetroRappid.model.StopService;

public class StopResponseTest extends TestCase {

    public void test_valid_response_deserialization() throws Exception {
        Serializer serializer = new Persister();
        StopResponse response = serializer.read(StopResponse.class, "<Atstop>\n" +
            "    <Walkdist>0.45</Walkdist>\n" +
            "    <Walkdir>SE</Walkdir>\n" +
            "    <Description>DOWNTOWN STATION</Description>\n" +
            "    <Area>Austin</Area>\n" +
            "    <Atisstopid>54097</Atisstopid>\n" +
            "    <Stopid>5534</Stopid>\n" +
            "    <Lat>30.265011</Lat>\n" +
            "    <Long>-97.739297</Long>\n" +
            "    <Stopposition>N</Stopposition>\n" +
            "    <Heading>NB</Heading>\n" +
            "    <Side>Near</Side>\n" +
            "    <Stopstatustype>N</Stopstatustype>\n" +
            "    <Service>\n" +
            "        <Route>550</Route>\n" +
            "        <Publicroute>550</Publicroute>\n" +
            "        <Sign>550-MetroRail Red Line-NB</Sign>\n" +
            "        <Operator>CM</Operator>\n" +
            "        <Publicoperator>CM</Publicoperator>\n" +
            "        <Direction>N</Direction>\n" +
            "        <Status>N</Status>\n" +
            "        <Servicetype>0</Servicetype>\n" +
            "        <Routetype>T</Routetype>\n" +
            "        <Times>12:03 AM</Times>\n" +
            "        <Tripinfo>\n" +
            "            <Triptime>12:03 AM</Triptime>\n" +
            "            <Tripid>44629-4</Tripid>\n" +
            "            <Skedtripid>\n" +
            "            </Skedtripid>\n" +
            "            <Adherence>0</Adherence>\n" +
            "            <Estimatedtime>12:03 AM</Estimatedtime>\n" +
            "            <Realtime>\n" +
            "                <Valid>N</Valid>\n" +
            "                <Adherence>0</Adherence>\n" +
            "                <Estimatedtime>12:03 AM</Estimatedtime>\n" +
            "                <Estimatedminutes>\n" +
            "                </Estimatedminutes>\n" +
            "                <Polltime></Polltime>\n" +
            "                <Trend>\n" +
            "                </Trend>\n" +
            "                <Speed>0.00</Speed>\n" +
            "                <Reliable>\n" +
            "                </Reliable>\n" +
            "                <Stopped>\n" +
            "                </Stopped>\n" +
            "                <Vehicleid></Vehicleid>\n" +
            "                <Lat>0.000000</Lat>\n" +
            "                <Long>0.000000</Long>\n" +
            "            </Realtime>\n" +
            "            <Block>550-91</Block>\n" +
            "            <Exception>N</Exception>\n" +
            "        </Tripinfo>\n" +
            "    </Service>\n" +
            "    <Service>\n" +
            "        <Route>550</Route>\n" +
            "        <Publicroute>550</Publicroute>\n" +
            "        <Sign>550-MetroRail Red Line-NB</Sign>\n" +
            "        <Operator>CM</Operator>\n" +
            "        <Publicoperator>CM</Publicoperator>\n" +
            "        <Direction>N</Direction>\n" +
            "        <Status>N</Status>\n" +
            "        <Servicetype>0</Servicetype>\n" +
            "        <Routetype>T</Routetype>\n" +
            "        <Times>12:03 AM</Times>\n" +
            "        <Tripinfo>\n" +
            "            <Triptime>12:03 AM</Triptime>\n" +
            "            <Tripid>44629-4</Tripid>\n" +
            "            <Skedtripid>\n" +
            "            </Skedtripid>\n" +
            "            <Adherence>0</Adherence>\n" +
            "            <Estimatedtime>12:03 AM</Estimatedtime>\n" +
            "            <Realtime>\n" +
            "                <Valid>N</Valid>\n" +
            "                <Adherence>0</Adherence>\n" +
            "                <Estimatedtime>12:03 AM</Estimatedtime>\n" +
            "                <Estimatedminutes>\n" +
            "                </Estimatedminutes>\n" +
            "                <Polltime></Polltime>\n" +
            "                <Trend>\n" +
            "                </Trend>\n" +
            "                <Speed>0.00</Speed>\n" +
            "                <Reliable>\n" +
            "                </Reliable>\n" +
            "                <Stopped>\n" +
            "                </Stopped>\n" +
            "                <Vehicleid></Vehicleid>\n" +
            "                <Lat>0.000000</Lat>\n" +
            "                <Long>0.000000</Long>\n" +
            "            </Realtime>\n" +
            "            <Block>550-91</Block>\n" +
            "            <Exception>N</Exception>\n" +
            "        </Tripinfo>\n" +
            "    </Service>\n" +
            "</Atstop>\n"
        );

        Assert.assertNotNull(response);
        Assert.assertTrue(response.services.size() == 2);
        Assert.assertEquals(response.stopId, "5534");
        Assert.assertEquals(response.area, "Austin");
        Assert.assertEquals(response.latitude, 30.265011);
        Assert.assertEquals(response.longitude, -97.739297);

        StopService stopService = response.services.get(0);
        Assert.assertEquals(stopService.route, "550");
    }
}


