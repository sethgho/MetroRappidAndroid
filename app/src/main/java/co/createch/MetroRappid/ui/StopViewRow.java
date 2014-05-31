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
    private TextView mBusTimes;
    private TextView mDistance;
    public CapStop stop;

    public CapStop stop;

    public StopViewRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static StopViewRow inflate(View parent){
        StopViewRow v = (StopViewRow)LayoutInflater.from(parent.getContext()).inflate(R.layout.stop_list_row, null);
        v.setupChildren();
        return v;
    }

    private void setupChildren(){
        mStopName = (TextView)findViewById(R.id.lblStopName);
        mBusTimes = (TextView)findViewById(R.id.lblBusTimes);
        mDistance = (TextView)findViewById(R.id.lblDistance);
    }

    public void loadStop(CapStop stop){
        this.stop = stop;
        mStopName.setText(stop.name);
        mBusTimes.setText("5m 22m 31m");
        mDistance.setText(String.format("%d m", (int)stop.distance));
        mDistance.setVisibility(stop.knowsDistance() ? View.VISIBLE : View.INVISIBLE);
    }
}
