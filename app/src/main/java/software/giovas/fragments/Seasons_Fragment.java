package software.giovas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;

import software.giovas.adapters.ImageAdapter;
import software.giovas.listeners.onNetworkDataListener;
import software.giovas.network.NetworkConnection;
import software.giovas.objects.EmptyRecyclerView;
import software.giovas.objects.Season;
import software.giovas.serieseassons.R;

/**
 * Created by darkgeat on 10/6/15.
 */
public class Seasons_Fragment extends Fragment implements onNetworkDataListener {

    private EmptyRecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seassons,container,false);
        list = (EmptyRecyclerView)v.findViewById(R.id.list);
        list.setEmptyView(inflater.inflate(R.layout.empty_view,null));
        NetworkConnection networkConnection = new NetworkConnection(getActivity(), NetworkConnection.Request.GET,this);
        networkConnection.execute();
        return v;
    }

    @Override
    public void onReceivedData(JSONObject object) {
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        list.setHasFixedSize(true);
        list.setLayoutManager(glm);
        list.setAdapter(new ImageAdapter());
    }

    @Override
    public void onReceivedData(ArrayList<Season> seasons) {

    }
}
