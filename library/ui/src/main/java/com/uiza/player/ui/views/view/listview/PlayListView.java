package com.uiza.player.ui.views.view.listview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaScreenUtil;

import java.util.ArrayList;
import java.util.List;

import io.uiza.sdk.ui.R;
import vn.loitp.core.utilities.LLog;
import vn.loitp.utils.util.ToastUtils;

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
        LLog.d(TAG, "init");
        inflate(getContext(), R.layout.play_list_view, this);

        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        int widthRecyclerView;
        int heightRecyclerView;

        if (UizaData.getInstance().isLandscape()) {
            widthRecyclerView = UizaScreenUtil.getScreenWidth();
            heightRecyclerView = UizaScreenUtil.getScreenHeight() / 2;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = widthRecyclerView;
            recyclerViewParams.height = heightRecyclerView;
            recyclerView.setLayoutParams(recyclerViewParams);
        } else {
            widthRecyclerView = UizaScreenUtil.getScreenWidth();
            heightRecyclerView = UizaScreenUtil.getScreenHeight() / 5;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = widthRecyclerView;
            recyclerViewParams.height = heightRecyclerView;
            recyclerView.setLayoutParams(recyclerViewParams);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        playListAdapter = new PlayListAdapter(getContext(), playListObjectList, widthRecyclerView, heightRecyclerView, new PlayListAdapter.Callback() {
            @Override
            public void onClickItem(PlayListObject playListObject) {
                //TODO
                ToastUtils.showShort("Click " + playListObject.getName());
            }
        });
        recyclerView.setAdapter(playListAdapter);

        setupData();
    }

    private void setupData() {
        for (int i = 0; i < 30; i++) {
            PlayListObject playListObject = new PlayListObject();
            playListObject.setName("La la land - Movie " + i);
            playListObject.setDuration("2h 13min");
            playListObject.setTime("2017");
            playListObject.setRate(i);
            playListObject.setDesctiption("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, …");
            playListObject.setUrl("https://c2.staticflickr.com/8/7697/28048898565_9357845f8d.jpg");

            playListObjectList.add(playListObject);
        }

        playListAdapter.notifyDataSetChanged();
    }
}