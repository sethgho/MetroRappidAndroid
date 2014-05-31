package co.createch.MetroRappid.service;

import co.createch.MetroRappid.model.StopResponse;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public interface MetroRapidService {

    public StopResponse getRealtimeInfo(String routeId, String stopId);

}
