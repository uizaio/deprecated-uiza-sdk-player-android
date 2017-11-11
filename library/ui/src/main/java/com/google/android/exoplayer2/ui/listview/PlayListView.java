package com.google.android.exoplayer2.ui.listview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ui.R;
import com.google.android.exoplayer2.ui.UizaData;
import com.google.android.exoplayer2.ui.util.LScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class PlayListView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private List<PlayListObject> playListObjectList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlayListAdapter playListAdapter;

    public PlayListView(Context context) {
        super(context);
        init();
    }

    public PlayListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.play_list_view, this);

        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        playListAdapter = new PlayListAdapter(playListObjectList, new PlayListAdapter.Callback() {
            @Override
            public void onClickItem(PlayListObject playListObject) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        if (UizaData.getInstance().isLandscape()) {
            int width = LScreenUtil.getScreenWidth();
            int height = LScreenUtil.getScreenHeight() / 2;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = width;
            recyclerViewParams.height = height;
            recyclerView.setLayoutParams(recyclerViewParams);
        } else {
            int width = LScreenUtil.getScreenWidth();
            int height = LScreenUtil.getScreenHeight() / 3;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = width;
            recyclerViewParams.height = height;
            recyclerView.setLayoutParams(recyclerViewParams);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(playListAdapter);

        setupData();
    }

    private void setupData() {
        for (int i = 0; i < 30; i++) {
            PlayListObject playListObject = new PlayListObject();
            playListObject.setName("La la land - Movie" + i);
            playListObject.setDuration("2h 13min");
            playListObject.setTime("2017");
            playListObject.setRate(i);
            playListObject.setDesctiption("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, …");

            playListObjectList.add(playListObject);
        }

        playListAdapter.notifyDataSetChanged();
    }
}