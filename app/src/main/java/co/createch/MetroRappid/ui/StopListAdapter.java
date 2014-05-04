package co.createch.MetroRappid.ui;

import android.database.DataSetObserver;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.CapStopCollection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class StopListAdapter extends BaseAdapter implements ListAdapter {

    private CapStopCollection mList;

    public StopListAdapter(CapStopCollection stops) {
        mList = stops;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
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
        StopViewRow v = (StopViewRow)convertView;
        if(v == null)
        {
            v = StopViewRow.inflate(parent);
        }
        v.loadStop(getItem(position));
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
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public void updateLocation(Location location) {
        mList.setLocation(location);
        notifyDataSetChanged();
    }
}
