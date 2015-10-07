package software.giovas.serieseassons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import software.giovas.fragments.Episodes_Fragment;

/**
 * Created by darkgeat on 10/5/15.
 */
public class Episodes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        Episodes_Fragment episodes = new Episodes_Fragment();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.episodesContainer, episodes).commit();
    }
}
