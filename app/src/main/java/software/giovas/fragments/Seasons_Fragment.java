package software.giovas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import java.util.ArrayList;

import software.giovas.adapters.ImageAdapter;
import software.giovas.listeners.onNetworkDataListener;
import software.giovas.network.NetworkConnection;
import software.giovas.objects.EmptyRecyclerView;
import software.giovas.objects.Season;
import software.giovas.serieseassons.R;
import software.giovas.utils.JSONParser;

/**
 * Created by darkgeat on 10/6/15.
 */
public class Seasons_Fragment extends Fragment implements onNetworkDataListener {

    private EmptyRecyclerView grid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seasons, container, false);
        grid = (EmptyRecyclerView)v.findViewById(R.id.grid);
        View empty = v.findViewById(R.id.emptyView);
        grid.setEmptyView(empty);
        NetworkConnection networkConnection = new NetworkConnection(getActivity(), NetworkConnection.Request.SEASONS,this);
        networkConnection.execute();
        return v;
    }

    @Override
    public void onReceivedData(JSONArray object) {
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        grid.setHasFixedSize(true);
        grid.setLayoutManager(glm);
        ArrayList<Season> seasons = JSONParser.getSeasonsData(object,getActivity());
        ImageAdapter adapter = new ImageAdapter(getActivity(),seasons);
        grid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReceivedData(ArrayList<Season> seasons) {

    }
}
