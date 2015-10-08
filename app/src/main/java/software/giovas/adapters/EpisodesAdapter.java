package software.giovas.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import software.giovas.objects.Season;
import software.giovas.serieseassons.R;

/**
 * Created by darkgeat on 10/7/15.
 */
public class EpisodesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;
    private ArrayList<String> episodes;
    private Season season;

    public EpisodesAdapter(Context context, ArrayList<String> data, Season season){
        mContext = context;
        episodes = data;
        this.season = season;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();

        if (viewType == TYPE_ITEM) {
            View convertView = inflater.inflate(R.layout.episode_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else if (viewType == TYPE_HEADER) {
            View convertView = inflater.inflate(R.layout.header_list, parent, false);
            viewHolder = new VHHeader(convertView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            //cast holder to ViewHolder and set data
            ((ViewHolder) holder).episodeName.setText(episodes.get(position));
            ((ViewHolder) holder).episodeNumber.setText(mContext.getString(R.string.episode_number,position+1));
        } else if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
            ((VHHeader) holder).tittleSeason.setText(mContext.getString(R.string.season_title,season.getNumber()));
            ((VHHeader) holder).ratingSeason.setText(mContext.getString(R.string.rating_value,season.getRating()));
            ((VHHeader) holder).votesSeason.setText(mContext.getString(R.string.votes,season.getVotes()));
            ((VHHeader) holder).episodesSeason.setText(mContext.getString(R.string.episodes,episodes.size()));
            ((VHHeader) holder).episodeName.setText(episodes.get(position));
            ((VHHeader) holder).episodeNumber.setText(mContext.getString(R.string.episode_number,position+1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView episodeNumber;
        public final TextView episodeName;

        public ViewHolder(View v){
            super(v);
            episodeNumber = (TextView)v.findViewById(R.id.epNumber);
            episodeName = (TextView)v.findViewById(R.id.epName);
        }
    }

    static class VHHeader extends RecyclerView.ViewHolder{
        public final TextView ratingSeason;
        public final TextView tittleSeason;
        public final TextView votesSeason;
        public final TextView episodesSeason;
        public final TextView episodeNumber;
        public final TextView episodeName;

        public VHHeader(View view){
            super(view);
            ratingSeason = (TextView)view.findViewById(R.id.textRatingHeader);
            tittleSeason = (TextView)view.findViewById(R.id.textTittleHeader);
            votesSeason = (TextView)view.findViewById(R.id.textVotesHeader);
            episodesSeason = (TextView)view.findViewById(R.id.textEpisodesHeader);
            episodeNumber = (TextView)view.findViewById(R.id.epNumber);
            episodeName = (TextView)view.findViewById(R.id.epName);
        }

    }
}
