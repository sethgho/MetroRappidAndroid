package co.createch.MetroRappid.data;

import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public interface StopRepository {

    public CapStopCollection getStopsForRoute(int routeId, RouteDirection direction);
}
