package co.createch.MetroRappid.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import co.createch.MetroRappid.model.BusLocationResponseEnvelope;

public class BusLocationResponseTest extends TestCase {

    public void test_response_deserialization() throws Exception {
        Serializer serializer = new Persister();
        BusLocationResponseEnvelope response = serializer.read(BusLocationResponseEnvelope.class, "<?xml version=\"1.0\"?>\n" +
                "<soap:Envelope \n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                "    xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" \n" +
                "    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" \n" +
                "    xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soap:Body>\n" +
                "        <BuslocationResponse \n" +
                "            xmlns=\"AT_WEB\">\n" +
                "            <Version>1.0</Version>\n" +
                "            <Responsecode>111</Responsecode>\n" +
                "            <Input>\n" +
                "                <Route>801</Route>\n" +
                "                <Direction>N</Direction>\n" +
                "            </Input>\n" +
                "            <Vehicles>\n" +
                "                <Vehicle>\n" +
                "                    <Route>801</Route>\n" +
                "                    <Direction>N</Direction>\n" +
                "                    <Updatetime>09:42 PM</Updatetime>\n" +
                "                    <Vehicleid>5007</Vehicleid>\n" +
                "                    <Block>801-06</Block>\n" +
                "                    <Adherance>-2</Adherance>\n" +
                "                    <Adhchange>S</Adhchange>\n" +
                "                    <Reliable>Y</Reliable>\n" +
                "                    <Offroute>N</Offroute>\n" +
                "                    <Stopped>N</Stopped>\n" +
                "                    <Inservice>Y</Inservice>\n" +
                "                    <Speed>20.61</Speed>\n" +
                "                    <Heading> 3</Heading>\n" +
                "                    <Routeid>44916</Routeid>\n" +
                "                    <Positions>\n" +
                "                        <Position>30.221222,-97.765007</Position>\n" +
                "                        <Position>30.218363,-97.766747</Position>\n" +
                "                        <Position>30.215282,-97.768715</Position>\n" +
                "                        <Position>30.212505,-97.770485</Position>\n" +
                "                        <Position>30.204943,-97.774765</Position>\n" +
                "                        <Position>30.204035,-97.775078</Position>\n" +
                "                    </Positions>\n" +
                "                </Vehicle>\n" +
                "                <Vehicle>\n" +
                "                    <Route>801</Route>\n" +
                "                    <Direction>N</Direction>\n" +
                "                    <Updatetime>09:42 PM</Updatetime>\n" +
                "                    <Vehicleid>5008</Vehicleid>\n" +
                "                    <Block>801-50</Block>\n" +
                "                    <Adherance>-3</Adherance>\n" +
                "                    <Adhchange>I</Adhchange>\n" +
                "                    <Reliable>Y</Reliable>\n" +
                "                    <Offroute>N</Offroute>\n" +
                "                    <Stopped>N</Stopped>\n" +
                "                    <Inservice>Y</Inservice>\n" +
                "                    <Speed>3.49</Speed>\n" +
                "                    <Heading> 0</Heading>\n" +
                "                    <Routeid>44916</Routeid>\n" +
                "                    <Positions>\n" +
                "                        <Position>30.290550,-97.741325</Position>\n" +
                "                        <Position>30.289286,-97.741417</Position>\n" +
                "                        <Position>30.286177,-97.741638</Position>\n" +
                "                        <Position>30.280842,-97.740753</Position>\n" +
                "                        <Position>30.279137,-97.741287</Position>\n" +
                "                        <Position>30.275480,-97.742653</Position>\n" +
                "                    </Positions>\n" +
                "                </Vehicle>\n" +
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
                "                </Vehicle>\n" +
                "            </Vehicles>\n" +
                "            <Mapextents>30.204035,-97.775078,30.371565,-97.692177</Mapextents>\n" +
                "            <Requestor>192.168.10.91</Requestor>\n" +
                "            <Host>cmtaatisweb2</Host>\n" +
                "            <Copyright>XML schema Copyright (c) 2003-2013 Trapeze Software ULC, its subsidiaries and affiliates.  All rights reserved.</Copyright>\n" +
                "            <Soapversion>2.6.3 - 09/23/13</Soapversion>\n" +
                "        </BuslocationResponse>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>\n");

        Assert.assertNotNull(response);
        Assert.assertEquals(111,response.body.response.responseCode);
        Assert.assertNotNull(response.body.response.vehicles);
        Assert.assertTrue(response.body.response.vehicles.size() > 0);

    }
}


