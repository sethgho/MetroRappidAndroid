package co.createch.MetroRappid.service;

import co.createch.MetroRappid.model.ResponseEnvelope;
import co.createch.MetroRappid.model.StopResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public interface MetroRapidService {

    @GET("/planner/s_service.asp")
    public void getRealtimeInfo(@Query("route") String routeId,
                                @Query("stopid") String stopId,
                                @Query("output") String output,
                                @Query("opt") int opt,
                                @Query("tool") String tool,
                                retrofit.Callback<ResponseEnvelope> cb);
}
