package co.createch.MetroRappid.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class FileStopRepository implements StopRepository {
    private Context mContext;

    public FileStopRepository(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public CapStopCollection getStopsForRoute(String routeId, RouteDirection direction) {
        return loadStops(getResourceIdForRoute(routeId,direction));
    }

    private int getResourceIdForRoute(String routeId, RouteDirection direction) {
        String resourceName = String.format("stops_%s_%d", routeId, direction.getKey());
        return mContext.getResources().getIdentifier(resourceName, "raw", mContext.getPackageName());
    }

    private CapStopCollection loadStops(int fileId) {
        CapStopCollection stopList = new CapStopCollection();
        InputStream raw = mContext.getResources().openRawResource(fileId);
        Reader reader = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RouteDirection.class, new RouteDirectionDeserializer())
                .create();
        Type listType = new TypeToken<CapStopCollection>() {
        }.getType();
        stopList = gson.fromJson(reader, listType);
        return stopList;
    }
}
