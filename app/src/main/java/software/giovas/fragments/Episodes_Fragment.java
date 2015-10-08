package software.giovas.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

import software.giovas.adapters.EpisodesAdapter;
import software.giovas.listeners.onNetworkDataListener;
import software.giovas.network.NetworkConnection;
import software.giovas.objects.EmptyRecyclerView;
import software.giovas.objects.Season;
import software.giovas.serieseassons.R;
import software.giovas.utils.JSONParser;

/**
 * Created by darkgeat on 10/7/15.
 */
public class Episodes_Fragment extends Fragment implements onNetworkDataListener {

    private EmptyRecyclerView list;
    private ImageView header,thumb;
    private Season season;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        season = getActivity().getIntent().getParcelableExtra("seasonSelected");
        NetworkConnection connection = new NetworkConnection(getActivity(),season.getNumber(),this);
        connection.execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_episodes, container, false);

        Toolbar toolbar = (Toolbar)v.findViewById(R.id.anim_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout)v.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.season_title, season.getNumber()));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.primary_dark_color));

        header = (ImageView)v.findViewById(R.id.header);
        thumb = (ImageView)v.findViewById(R.id.thumb);
        header.setDrawingCacheEnabled(true);

        Picasso.with(getActivity()).load(season.getThumbnail())
                .placeholder(getActivity().getResources().getDrawable(R.drawable.season_background_placeholder))
                .error(getActivity().getResources().getDrawable(R.drawable.season_background_placeholder))
                .into(header);

        Picasso.with(getActivity()).load(season.getPoster())
                .placeholder(getActivity().getResources().getDrawable(R.drawable.serie_thumbnail_placeholder))
                .error(getActivity().getResources().getDrawable(R.drawable.serie_thumbnail_placeholder))
                .into(thumb);
        list = (EmptyRecyclerView)v.findViewById(R.id.listEpisodes);
        View empty = v.findViewById(R.id.emptyView2);
        list.setEmptyView(empty);
        Animation animation = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0, (float) 1.0);
        animation.setDuration(500);
        LayoutAnimationController controller = new LayoutAnimationController(animation,0.7f);
        list.setLayoutAnimation(controller);
        list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int pos = recyclerView.getScrollY();
                thumb.setVisibility(dy > pos ? View.GONE : View.VISIBLE);
            }
        });

        return v;
    }

    @Override
    public void onReceivedData(JSONArray object) {
        ArrayList<String> data = JSONParser.getEpisodesData(object);
        EpisodesAdapter adapter = new EpisodesAdapter(getActivity(), data, season);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReceivedData(ArrayList<Season> seasons) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return true;
    }
}
