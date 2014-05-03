package co.createch.MetroRappid.data;

import java.util.List;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public interface StopRepository {

    public List<CapStop> getStopsForRoute(int routeId, RouteDirection direction);
}
