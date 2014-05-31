package co.createch.MetroRappid.data;

import co.createch.MetroRappid.model.RouteDirection;
import co.createch.MetroRappid.model.RoutePath;

/**
 * Created by Seth Gholson on 5/31/14.
 */
public interface RouteRepository {

    public RoutePath getShapesForRoute(String routeId, RouteDirection direction);
}
