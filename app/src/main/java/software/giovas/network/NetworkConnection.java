package software.giovas.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import software.giovas.listeners.onNetworkDataListener;
import software.giovas.objects.Season;
import software.giovas.serieseassons.R;

/**
 * Created by darkgeat on 10/6/15.
 */
public class NetworkConnection extends AsyncTask<String,Void,Boolean> {

    private final String NETWORK_TAG = NetworkConnection.class.getSimpleName();
    private final Context mContext;
    private onNetworkDataListener listener;
    private String responseJsonStr = null;
    private Request typeRequest;
    private int seasonNumber;
    private ArrayList<Season> seasons = new ArrayList<>();

    public NetworkConnection(Context c, int seasonNumber, onNetworkDataListener networkDataListener){
        mContext = c;
        listener = networkDataListener;
        typeRequest = Request.EPISODES;
        this.seasonNumber = seasonNumber;
    }

    public NetworkConnection(Context c, Request type, onNetworkDataListener networkDataListener){
        mContext = c;
        listener = networkDataListener;
        typeRequest = type;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Uri requestURL = null;
        final String BASE_URL = mContext.getString(R.string.base_URL);
        final String SHOWS_PATH = "shows";
        final String SEASONS_PATH = "seasons";
        final String EXTENDED_PARAM = "extended";
        final String NAME_SEASON = "game-of-thrones";

        if(typeRequest == Request.SEASONS) {
            requestURL = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(SHOWS_PATH)
                    .appendPath(NAME_SEASON)
                    .appendPath(SEASONS_PATH)
                    .appendQueryParameter(EXTENDED_PARAM, "images,full")
                    .build();
        }else if(typeRequest == Request.EPISODES){
            requestURL = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(SHOWS_PATH)
                    .appendPath(NAME_SEASON)
                    .appendPath(SEASONS_PATH)
                    .appendPath(String.valueOf(seasonNumber))
                    .build();
        }
        Log.w(NETWORK_TAG, requestURL.toString());
        return retrieveData(requestURL);
    }

    private boolean retrieveData(Uri requestedURL){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try{

            //Final URL for request
            URL url = new URL(requestedURL.toString());

            //Creation for the request of seasons data
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(typeRequest.value);
            urlConnection.setRequestProperty(mContext.getString(R.string.request_api_key), mContext.getString(R.string.client_id));
            urlConnection.setRequestProperty(mContext.getString(R.string.request_api_version),mContext.getString(R.string.tracktv_api_version));
            urlConnection.connect();

            //Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                return false; //Nothing to do.
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                return false; //Has no lines. String empty.
            }

            responseJsonStr = buffer.toString();
            Log.w(NETWORK_TAG,responseJsonStr);
            return true;
        }catch (IOException e){
            Log.e(NETWORK_TAG,e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            try {
                JSONArray data = new JSONArray(responseJsonStr);
                listener.onReceivedData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public enum Request {
        EPISODES("GET"), SEASONS("GET");
        private String value;
        Request(String value){
            this.value = value;
        }
    }


}