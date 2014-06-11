package co.createch.MetroRappid.test;

import android.content.Context;
import android.test.InstrumentationTestCase;

import java.util.List;

import co.createch.MetroRappid.data.FileStopRepository;
import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class FileStopRepositoryTest extends InstrumentationTestCase {

    public void testNorth()
    {
        Context context = getInstrumentation().getTargetContext();
        FileStopRepository repo = new FileStopRepository(context);
        List<CapStop> stops = repo.getStopsForRoute("801", RouteDirection.North);
        assertNotNull(stops);

        CapStop firstStop = stops.get(0);
        assertEquals("SOUTHPARK MEADOWS STATION", firstStop.name);
        assertEquals("1335341",firstStop.tripId);
        assertEquals("5873", firstStop.stopId);
        assertEquals("Northeast corner of TURK and CULLEN - Mid-Block", firstStop.description);
        assertEquals(30.162951, firstStop.latitude);
        assertEquals(-97.790488, firstStop.longitude);
    }
}
