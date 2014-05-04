package co.createch.MetroRappid.test;

import android.location.Location;

import junit.framework.TestCase;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;

/**
 * Created by Seth Gholson on 5/4/14.
 */
public class CapStopCollectionTests extends TestCase {

    private CapStopCollection mStops;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mStops = new CapStopCollection();
        CapStop stop1 = new CapStop();
        stop1.stopId = "5873";
        stop1.latitude = 30.162951;
        stop1.longitude = -97.790488;
        stop1.name = "SOUTHPARK MEADOWS STATION";

        CapStop stop2 = new CapStop();
        stop2.stopId = "5872";
        stop1.latitude = 30.192391;
        stop1.longitude = -97.779203;
        stop2.name = "PLEASANT HILL STATION (SB)";

        mStops.add(stop1);
        mStops.add(stop2);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUpdateLocation(){
        Location mylocation = new Location("test");
        mylocation.setLatitude(30.163951);
        mylocation.setLongitude(-97.790288);
        mStops.setLocation(mylocation);
        assertEquals(3328.595f,mStops.get(0).distance);
        assertEquals(1.0764305e7f,mStops.get(1).distance);
    }
}
