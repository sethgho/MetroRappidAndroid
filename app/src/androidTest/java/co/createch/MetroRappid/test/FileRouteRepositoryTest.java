package co.createch.MetroRappid.test;

import android.test.InstrumentationTestCase;

import junit.framework.Assert;

import co.createch.MetroRappid.model.FileRouteRepository;
import co.createch.MetroRappid.model.RouteDirection;
import co.createch.MetroRappid.model.RoutePath;

/**
 * Created by Seth Gholson on 5/31/14.
 */
public class FileRouteRepositoryTest extends InstrumentationTestCase {

    FileRouteRepository mRouteRepo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRouteRepo = new FileRouteRepository(getInstrumentation().getTargetContext());
    }

    public void testGet801RouteNorth() {
        RoutePath path = mRouteRepo.getShapesForRoute("801", RouteDirection.North);
        Assert.assertNotNull(path);
    }

    public void testGet801RouteSouth() {
        RoutePath path = mRouteRepo.getShapesForRoute("801", RouteDirection.South);
        Assert.assertNotNull(path);
    }

    public void testGet550RouteNorth() {
        RoutePath path = mRouteRepo.getShapesForRoute("550", RouteDirection.North);
        Assert.assertNotNull(path);
    }

    public void testGet550RouteSouth() {
        RoutePath path = mRouteRepo.getShapesForRoute("550", RouteDirection.South);
        Assert.assertNotNull(path);
    }

    public void testGetNonexistentRoute() {
        RoutePath path = mRouteRepo.getShapesForRoute("xyz", RouteDirection.North);
        Assert.assertNull(path);
    }
}
