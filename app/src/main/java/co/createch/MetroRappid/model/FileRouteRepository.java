package co.createch.MetroRappid.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import co.createch.MetroRappid.data.RouteDirectionDeserializer;
import co.createch.MetroRappid.data.RouteRepository;

/**
 * Created by Seth Gholson on 5/31/14.
 */
public class FileRouteRepository implements RouteRepository {

    private Context mContext;

    public FileRouteRepository(Context context)
    {
        mContext = context;
    }

    @Override
    public RoutePath getShapesForRoute(String routeId, RouteDirection direction) {
        int resourceId = getResourceIdForRoute(routeId, direction);
        return getRoutePath(resourceId);
    }

    private int getResourceIdForRoute(String routeId, RouteDirection direction)
    {
        String resourceName = String.format("shapes_%s_%d", routeId, direction.getKey());
        return mContext.getResources().getIdentifier(resourceName, "raw", mContext.getPackageName());
    }

    private RoutePath getRoutePath(int resource)
    {
        if(resource == 0)
        {
            return null;
        }

        RoutePath routePath;
        InputStream raw = mContext.getResources().openRawResource(resource);
        Reader reader = new BufferedReader(new InputStreamReader(raw));
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(RouteDirection.class, new RouteDirectionDeserializer())
                .create();
        Type listType = new TypeToken<RoutePath>(){}.getType();
        routePath = gson.fromJson(reader, listType);
        return routePath;
    }

}
