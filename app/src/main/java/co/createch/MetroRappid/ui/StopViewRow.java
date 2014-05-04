package co.createch.MetroRappid.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import co.createch.MetroRappid.R;
import co.createch.MetroRappid.model.CapStop;

/**
 * Created by Seth Gholson on 5/4/14.
 */
public class StopViewRow extends RelativeLayout {

    private TextView mStopName;
    private TextView mBusTime1;
    private TextView mBusTime2;
    private TextView mBusTime3;
    private TextView mDistance;

    public StopViewRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static StopViewRow inflate(View parent){
        StopViewRow v = (StopViewRow)LayoutInflater.from(parent.getContext()).inflate(R.layout.stop_list_row_tall, null);
        v.setupChildren();
        return v;
    }

    private void setupChildren(){
        mStopName = (TextView)findViewById(R.id.lblStopName);
        mBusTime1 = (TextView)findViewById(R.id.lblBusTime1);
        mBusTime2 = (TextView)findViewById(R.id.lblBusTime2);
        mBusTime3 = (TextView)findViewById(R.id.lblBusTime3);
        mDistance = (TextView)findViewById(R.id.lblDistance);
    }

    public void loadStop(CapStop stop){
        mStopName.setText(stop.name);
        mBusTime1.setText("5m\nlast seen\n3m ago");
        mBusTime2.setText("14m\nlast seen\n1m ago");
        mBusTime3.setText("32m\nlast seen\n2m ago");
        mDistance.setText(String.format("%d m", (int)stop.distance));
        mDistance.setVisibility(stop.knowsDistance() ? View.VISIBLE : View.GONE);
    }
}
