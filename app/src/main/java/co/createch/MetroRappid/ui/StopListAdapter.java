package co.createch.MetroRappid.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import co.createch.MetroRappid.R;
import co.createch.MetroRappid.model.CapStop;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class StopListAdapter implements ListAdapter {

    private List<CapStop> mList;
    private Context mContext;

    public StopListAdapter(Context context, List<CapStop> stops) {
        mContext = context;
        mList = stops;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CapStop getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            v = LayoutInflater.from(mContext).inflate(R.layout.store_list_row,null);
        }

        final CapStop stop = getItem(position);
        TextView stopName = (TextView)v.findViewById(R.id.lblStopName);
        TextView busTimes = (TextView)v.findViewById(R.id.lblBusTimes);
        TextView distance = (TextView)v.findViewById(R.id.lblDistance);
        stopName.setText(stop.name);
        busTimes.setText("5m 22m 31m");
        distance.setText("1600m");
        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mList == null || mList.size() == 0;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
