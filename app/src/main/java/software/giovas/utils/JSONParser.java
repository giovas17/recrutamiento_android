package software.giovas.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import software.giovas.objects.Season;

/**
 * Created by DarkGeat on 06/10/2015.
 */
public class JSONParser {

    public static ArrayList<Season> getSeasonsData(JSONArray objectArray, Context context){

        // ----- Keys from JSON object -------
        final String NUMBER = "number";
        final String POSTER = "poster";
        final String IMAGES = "images";
        final String THUMB = "thumb";
        final String FULL = "full";

        ArrayList<Season> seasons = new ArrayList<>();
        for(int i = 0 ; i < objectArray.length() ; i++){
            try {
                JSONObject object = objectArray.getJSONObject(i);
                Season season = new Season();
                season.setNumber(object.getInt(NUMBER));
                JSONObject image = object.getJSONObject(IMAGES);
                JSONObject poster = image.getJSONObject(POSTER);
                season.setPoster(poster.getString(THUMB));
                JSONObject thumb = image.getJSONObject(THUMB);
                season.setThumbnail(thumb.getString(FULL));
                seasons.add(season);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return seasons;
    }



}
