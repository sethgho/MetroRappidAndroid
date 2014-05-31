package co.createch.MetroRappid.service;

import co.createch.MetroRappid.model.StopResponse;

/**
 * Created by Seth Gholson on 5/31/14.
 */

public class FakeMetroRapidService implements MetroRapidService {

    @Override
    public StopResponse getRealtimeInfo(String routeId, String stopId) {
        //TODO: Read from fake file.
        return new StopResponse();
    }
}
