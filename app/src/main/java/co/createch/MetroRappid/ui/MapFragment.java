package co.createch.MetroRappid.ui;

import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by sean on 5/31/14.
 */
public class MapFragment extends SupportMapFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((RouteViewActivity) getActivity()).setMap(getMap());
    }
}
