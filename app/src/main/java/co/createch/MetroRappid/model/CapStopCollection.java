package co.createch.MetroRappid.model;

import android.location.Location;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Seth Gholson on 5/3/14.
 */
public class CapStopCollection extends ArrayList<CapStop> {

    public void setLocation(Location location) {
        for(CapStop stop : this)
        {
            stop.calculateDistanceFromLocation(location);
        }
        Collections.sort(this);
    }
}
