package software.giovas.listeners;

import org.json.JSONObject;
import java.util.ArrayList;
import software.giovas.objects.Season;

/**
 * Created by darkgeat on 10/6/15.
 */
public interface onNetworkDataListener {
    void onReceivedData(JSONObject object);
    void onReceivedData(ArrayList<Season> seasons);
}