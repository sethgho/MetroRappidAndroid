package co.createch.MetroRappid.ui;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.createch.MetroRappid.R;
import co.createch.MetroRappid.data.FileStopRepository;
import co.createch.MetroRappid.data.StopRepository;
import co.createch.MetroRappid.model.CapStop;
import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class StopListActivity extends BaseActivity {

    @InjectView(R.id.list)
    public ListView mList;

    private StopRepository mStopRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        ButterKnife.inject(this);
        mStopRepo = new FileStopRepository(getApplicationContext());

        final List<CapStop> stops = mStopRepo.getStopsForRoute(801, RouteDirection.North);
        StopListAdapter adapter = new StopListAdapter(getApplicationContext(), stops);
        mList.setAdapter(adapter);

    }
}
