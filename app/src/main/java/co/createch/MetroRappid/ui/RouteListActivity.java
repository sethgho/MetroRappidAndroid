package co.createch.MetroRappid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.createch.MetroRappidAndroid.R;

/**
 * Created by Seth Gholson on 6/1/14.
 */
public class RouteListActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.list)
    public ListView mList;

    private String[] mValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_list_view);
        ButterKnife.inject(this);
        initList();
    }

    private void initList() {
        mValues = getResources().getStringArray(R.array.routes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mValues);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO: Be less dumb. #hackathon
        String routeId = "";
        switch(position)
        {
            case 0:
                routeId = "801";
                break;
            default:
                routeId = "550";
                break;
        }
        Intent i = new Intent(this,RouteViewActivity.class);
        i.putExtra(RouteViewActivity.ARG_ROUTE_ID, routeId);
        i.putExtra(RouteViewActivity.ARG_ROUTE_NAME,mValues[position]);
        startActivity(i);
    }
}
