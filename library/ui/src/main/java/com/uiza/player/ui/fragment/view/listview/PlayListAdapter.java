package com.uiza.player.ui.fragment.view.listview;

/**
 * Created by www.muathu@gmail.com on 11/7/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uiza.player.ui.util.UizaImageUtil;

import java.util.List;

import io.uiza.sdk.ui.R;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListHolder> {
    private List<PlayListObject> playListObjectList;
    private Context context;
    private int sizeWRoot;
    private int sizeHRoot;

    public class PlayListHolder extends RecyclerView.ViewHolder {
        private TextView tvDuration;
        private TextView tvDuration2;
        private ImageView ivCover;
        private TextView tvName;
        private TextView tvYear;
        private TextView tvRate;
        private TextView tvDescription;
        private LinearLayout rootView;

        public PlayListHolder(View view) {
            super(view);
            rootView = (LinearLayout) view.findViewById(R.id.root_view);
            tvDuration = (TextView) view.findViewById(R.id.tv_duration);
            tvDuration2 = (TextView) view.findViewById(R.id.tv_duration_2);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvYear = (TextView) view.findViewById(R.id.tv_year);
            tvRate = (TextView) view.findViewById(R.id.tv_rate);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            ivCover = (ImageView) view.findViewById(R.id.iv_cover);
        }
    }

    public PlayListAdapter(Context context, List<PlayListObject> playListObjectList, int sizeWRoot, int sizeHRoot, Callback callback) {
        this.playListObjectList = playListObjectList;
        this.callback = callback;
        //sizeW = UizaScreenUtil.getScreenWidth() / 3;
        //sizeH = sizeW * 2 / 3;
        this.sizeWRoot = sizeWRoot;
        this.sizeHRoot = sizeHRoot;
        this.context = context;
    }

    @Override
    public PlayListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_playlist, parent, false);
        return new PlayListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayListHolder playListHolder, int position) {
        final PlayListObject playListObject = playListObjectList.get(position);

        playListHolder.tvDuration.setText(playListObject.getDuration());
        playListHolder.tvName.setText(playListObject.getName());
        playListHolder.tvYear.setText(playListObject.getTime());
        playListHolder.tvDuration2.setText(playListObject.getDuration());
        playListHolder.tvRate.setText(playListObject.getRate() + "+");
        playListHolder.tvDescription.setText(playListObject.getDesctiption());

        RelativeLayout.LayoutParams rootLayoutParams = new RelativeLayout.LayoutParams((int) (sizeWRoot / 3.5), sizeHRoot);
        playListHolder.rootView.setLayoutParams(rootLayoutParams);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (sizeWRoot / 3.5 / 2));
        playListHolder.ivCover.setLayoutParams(layoutParams);

        UizaImageUtil.load(context, playListObject.getUrl(), playListHolder.ivCover);

        playListHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClickItem(playListObject);
                }
            }
        });
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