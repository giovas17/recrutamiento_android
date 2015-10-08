package software.giovas.serieseassons;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import software.giovas.fragments.Seasons_Fragment;

public class MainActivity extends AppCompatActivity {

    public static boolean two_views = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Seasons -- " + getString(R.string.showName));

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Seasons_Fragment()).commit();

    }


}
