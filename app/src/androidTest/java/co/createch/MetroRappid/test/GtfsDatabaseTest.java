package co.createch.MetroRappid.test;

import android.test.InstrumentationTestCase;

import co.createch.MetroRappid.data.GtfsDatabase;

/**
 * Created by Seth Gholson on 4/19/14.
 */
public class GtfsDatabaseTest extends InstrumentationTestCase {

    public void testShouldSucceed() {
        GtfsDatabase db = new GtfsDatabase(getInstrumentation().getContext());
    }
}