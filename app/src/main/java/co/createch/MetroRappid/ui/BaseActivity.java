package co.createch.MetroRappid.ui;

import android.support.v4.app.FragmentActivity;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
    }
}
