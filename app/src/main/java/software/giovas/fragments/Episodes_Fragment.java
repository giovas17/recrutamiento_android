package software.giovas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import software.giovas.objects.EmptyRecyclerView;
import software.giovas.objects.Season;
import software.giovas.serieseassons.R;

/**
 * Created by darkgeat on 10/7/15.
 */
public class Episodes_Fragment extends Fragment {

    private EmptyRecyclerView list;
    private ImageView header,thumb;
    private Season season;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        season = getActivity().getIntent().getParcelableExtra("seasonSelected");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_episodes,null);

        header = (ImageView)v.findViewById(R.id.header);
        thumb = (ImageView)v.findViewById(R.id.thumb);

        Picasso.with(getActivity()).load(season.getThumbnail())
                .placeholder(getActivity().getResources().getDrawable(R.drawable.season_background_placeholder))
                .error(getActivity().getResources().getDrawable(R.drawable.season_background_placeholder))
                .into(header);
        Picasso.with(getActivity()).load(season.getPoster())
                .placeholder(getActivity().getResources().getDrawable(R.drawable.serie_thumbnail_placeholder))
                .error(getActivity().getResources().getDrawable(R.drawable.serie_thumbnail_placeholder))
                .into(thumb);
        list = (EmptyRecyclerView)v.findViewById(R.id.list);
        View empty = v.findViewById(R.id.emptyView);
        list.setEmptyView(empty);
        return v;
    }
}
