package co.createch.MetroRappid.test;

import android.test.InstrumentationTestCase;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.ui.StopListAdapter;

/**
 * Created by Seth Gholson on 5/4/14.
 */
public class StopListAdapterTest extends InstrumentationTestCase {

    CapStopCollection mStops;
    StopListAdapter mAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mStops = new CapStopCollection();
        CapStop stop1 = new CapStop();
        stop1.stopId = "stopid1";
        stop1.distance = 1;
        stop1.name = "Stop 1";

        CapStop stop2 = new CapStop();
        stop2.stopId = "stopid2";
        stop2.distance = 2;
        stop2.name = "Stop 2";

        mStops.add(stop1);
        mStops.add(stop2);

//        mAdapter = new StopListAdapter(getInstrumentation().getTargetContext(), mStops);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
//
//    public void testAdapter() {
//        int count = mAdapter.getCount();
//        assertEquals(2,count);
//
//        CapStop firstStop = mAdapter.getItem(0);
//        assertEquals("stopid1", firstStop.stopId);
//    }
}
