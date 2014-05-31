package co.createch.MetroRappid.ui;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.createch.MetroRappid.R;
import co.createch.MetroRappid.data.FileStopRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class StopListActivity extends BaseLocationActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.list)
    public ListView mList;

    @InjectView(R.id.loading)
    public View mLoadingView;

    @InjectView(R.id.text)
    public TextView mLoadingLabel;

    private StopRepository mStopRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        ButterKnife.inject(this);
        initializeAdapter();
        showLoading(R.string.getting_location);
        attachListeners();
        if(servicesConnected())
        {
            mLocationClient.connect();
        }
    }

    private void attachListeners() {
        mList.setOnItemClickListener(this);
    }


    @Override
    public void onConnected(Bundle dataBundle) {
        Location location = mLocationClient.getLastLocation();
        ((StopListAdapter)mList.getAdapter()).updateLocation(location);
        hideLoading();
    }

    private void initializeAdapter(){
        mStopRepo = new FileStopRepository(getApplicationContext());
        final CapStopCollection stops = mStopRepo.getStopsForRoute(801, RouteDirection.North);
        final StopListAdapter adapter = new StopListAdapter(stops);
        mList.setAdapter(adapter);
    }

    private void showLoading(int labelId){
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingLabel.setText(labelId);
    }

    private void hideLoading() {
        mLoadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        hideLoading();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        StopViewRow row = (StopViewRow) view;
        Intent i = new Intent(this, RouteViewActivity.class);
        CapStop stop = row.stop;
        i.putExtra(RouteViewActivity.CAP_STOP, stop);
        startActivity(i);

    }

}
