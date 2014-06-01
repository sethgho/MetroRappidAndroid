package co.createch.MetroRappid.service;

import co.createch.MetroRappid.model.ResponseEnvelope;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Seth Gholson on 4/20/14.
 */
public interface MetroRapidService {

    @GET("/planner/s_service.asp")
    public void getRealtimeInfo(@Query("route") String routeId, @Query("stopid") String stopId,
                                @Query("output") String output, @Query("opt") String opt,
                                @Query("tool") String tool,
                                Callback<ResponseEnvelope> cb);
}
