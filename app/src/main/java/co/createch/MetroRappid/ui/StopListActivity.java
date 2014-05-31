package co.createch.MetroRappid.ui;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.createch.MetroRappid.MetroRapidApp;
import co.createch.MetroRappid.R;
import co.createch.MetroRappid.data.FileStopRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;
import co.createch.MetroRappid.model.ResponseEnvelope;
import co.createch.MetroRappid.model.RouteDirection;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        if (servicesConnected()) {
            mLocationClient.connect();
        }
    }

    private void attachListeners() {
        mList.setOnItemClickListener(this);
    }


    @Override
    public void onConnected(Bundle dataBundle) {
        Location location = mLocationClient.getLastLocation();
        if (location != null) {
            ((StopListAdapter) mList.getAdapter()).updateLocation(location);
        }
        hideLoading();
        testGetRealtimeData();
    }

    private void testGetRealtimeData() {
        ((MetroRapidApp)getApplication()).getCapMetroService().getRealtimeInfo("801", "5867", "xml", 2, "NB", new Callback<ResponseEnvelope>() {
            @Override
            public void success(ResponseEnvelope stopResponse, Response response) {
                Log.d("TEST", "Success!'");
                Log.d("stopResponse", stopResponse.toString());
//                Log.d("Response", stopResponse.body.response.stop.service.list.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TEST Fail", error.getMessage());
            }
        });
    }

    private void initializeAdapter() {
        mStopRepo = new FileStopRepository(getApplicationContext());
        final CapStopCollection stops = mStopRepo.getStopsForRoute(801, RouteDirection.North);
        final StopListAdapter adapter = new StopListAdapter(stops);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(this);
    }


    private void showLoading(int labelId) {
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        StopViewRow row = (StopViewRow) view;
        Intent intent = new Intent(adapterView.getContext(),RouteViewActivity.class);
        intent.putExtra(RouteViewActivity.CAP_STOP, row.stop);
        startActivity(intent);
    }

}
