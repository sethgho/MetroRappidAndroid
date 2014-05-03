package co.createch.MetroRappid.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.createch.MetroRappid.R;
import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class FileStopRepository implements StopRepository {
    private static List<CapStop> mNorthStops;
    private static List<CapStop> mSouthStops;
    private Context mContext;

    public FileStopRepository(Context applicationContext)
    {
        mContext = applicationContext;
    }

    @Override
    public List<CapStop> getStopsForRoute(int routeId, RouteDirection direction) {
        switch(direction)
        {
            case North: return getNorthStops();
            case South: return getSouthStops();
            default:    return null;
        }
    }

    private List<CapStop> getSouthStops() {
        if(mSouthStops == null)
        {
            mSouthStops = loadStops(R.raw.stops_801_0);
        }
        return mSouthStops;
    }

    private List<CapStop> getNorthStops() {
        if(mNorthStops == null)
        {
            mNorthStops = loadStops(R.raw.stops_801_1);
        }
        return mNorthStops;
    }

    private List<CapStop> loadStops(int fileId)
    {
        ArrayList<CapStop> stopList = new ArrayList<CapStop>();
        InputStream raw = mContext.getResources().openRawResource(fileId);
        Reader reader = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CapStop>>(){}.getType();
        stopList = gson.fromJson(reader, listType);
        return stopList;
    }
}
