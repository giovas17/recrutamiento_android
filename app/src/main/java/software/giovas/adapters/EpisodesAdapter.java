package software.giovas.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import software.giovas.serieseassons.R;

/**
 * Created by darkgeat on 10/7/15.
 */
public class EpisodesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;
    private ArrayList<String> episodes;

    public EpisodesAdapter(Context context, ArrayList<String> data){
        mContext = context;
        episodes = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();

        if (viewType == TYPE_ITEM) {
            View convertView = inflater.inflate(R.layout.episode_item, null, false);
            viewHolder = new ViewHolder(convertView);
        } else if (viewType == TYPE_HEADER) {
            View convertView = inflater.inflate(R.layout.header_list, null, false);
            viewHolder = new VHHeader(convertView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            //cast holder to ViewHolder and set data
        } else if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
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
        return 0;
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

        public VHHeader(View view){
            super(view);
            ratingSeason = (TextView)view.findViewById(R.id.textRatingHeader);
            tittleSeason = (TextView)view.findViewById(R.id.textTittleHeader);
        }

    }
}
