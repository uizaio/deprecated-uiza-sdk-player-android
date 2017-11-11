package com.google.android.exoplayer2.ui.listview;

/**
 * Created by www.muathu@gmail.com on 11/7/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.R;
import com.google.android.exoplayer2.ui.settingview.SettingObject;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListHolder> {
    private List<PlayListObject> playListObjectList;

    public class PlayListHolder extends RecyclerView.ViewHolder {
        private TextView tvDuration;
        private TextView tvDuration2;
        private ImageView ivCover;
        private TextView tvName;
        private TextView tvYear;
        private TextView tvRate;
        private TextView tvDescription;

        public PlayListHolder(View view) {
            super(view);
            tvDuration = (TextView) view.findViewById(R.id.tv_duration);
            tvDuration2 = (TextView) view.findViewById(R.id.tv_duration_2);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvYear = (TextView) view.findViewById(R.id.tv_year);
            tvRate = (TextView) view.findViewById(R.id.tv_rate);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            ivCover = (ImageView) view.findViewById(R.id.iv_cover);
        }
    }

    public PlayListAdapter(List<PlayListObject> playListObjectList, Callback callback) {
        this.playListObjectList = playListObjectList;
        this.callback = callback;
    }

    @Override
    public PlayListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_playlist, parent, false);
        return new PlayListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayListHolder settingHolder, int position) {
        final PlayListObject playListObject = playListObjectList.get(position);
        settingHolder.ivCover.setImageResource(R.drawable.ic_unchecked);
        settingHolder.tvDuration.setText(playListObject.getDuration());
        settingHolder.tvName.setText(playListObject.getName());
        settingHolder.tvYear.setText(playListObject.getTime());
        settingHolder.tvDuration2.setText(playListObject.getDuration());
        settingHolder.tvRate.setText(playListObject.getRate() + "+");
        settingHolder.tvDescription.setText(playListObject.getDuration());
    }

    @Override
    public int getItemCount() {
        return playListObjectList == null ? 0 : playListObjectList.size();
    }

    public interface Callback {
        public void onClickItem(PlayListObject playListObject);
    }

    private Callback callback;
}