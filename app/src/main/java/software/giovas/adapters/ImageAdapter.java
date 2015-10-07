package software.giovas.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import software.giovas.objects.Season;
import software.giovas.serieseassons.Episodes;
import software.giovas.serieseassons.MainActivity;
import software.giovas.serieseassons.R;

/**
 * Created by darkgeat on 10/6/15.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Season> seasons;
    private boolean isFirstTime;

    public ImageAdapter(Context context, ArrayList<Season> allSeasons) {
        mContext = context;
        seasons = allSeasons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.poster_seasson_item, null, false);
        viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Picasso.with(mContext).load(seasons.get(position).getPoster())
                .placeholder(mContext.getResources().getDrawable(R.drawable.serie_thumbnail_placeholder))
                .error(mContext.getResources().getDrawable(R.drawable.serie_thumbnail_placeholder))
                .into(viewHolder.posterSeason);
        viewHolder.posterSeason.setTag(seasons.get(position));
        int seasonNumber = seasons.get(position).getNumber();
        viewHolder.titleSeason.setText(mContext.getString(R.string.season_title, seasonNumber));
        final int pos = position;
        viewHolder.posterSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.two_views) {
                    //listener.onMovieSelected(seasons.get(pos));
                } else {
                    Bundle args = new Bundle();
                    args.putParcelable("seasonSelected", seasons.get(pos));
                    Intent intent = new Intent(mContext, Episodes.class);
                    intent.putExtras(args);
                    mContext.startActivity(intent);
                }
            }
        });
        viewHolder.episodesSeason.setText(mContext.getString(R.string.episodes, seasons.get(position).getEpisodes()));
        viewHolder.ratingSeason.setText(mContext.getString(R.string.rating, seasons.get(position).getRating()));
        /*if(MainActivity.two_views && position == 0 && isFirstTime){
            listener.onMovieSelected(seasons.get(position));
            isFirstTime = false;
        }*/
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView posterSeason;
        public final TextView titleSeason;
        public final TextView episodesSeason;
        public final TextView ratingSeason;

        public ViewHolder(View v){
            super(v);
            posterSeason = (ImageView)v.findViewById(R.id.imagePosterItem);
            titleSeason = (TextView)v.findViewById(R.id.textTitleMovie);
            episodesSeason = (TextView)v.findViewById(R.id.textEpisodes);
            ratingSeason = (TextView)v.findViewById(R.id.textRating);
        }
    }
}
