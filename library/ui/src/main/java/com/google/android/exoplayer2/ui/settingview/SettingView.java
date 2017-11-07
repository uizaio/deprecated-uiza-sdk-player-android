package com.google.android.exoplayer2.ui.settingview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class SettingView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;

    private List<SettingObject> settingObjectList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SettingAdapter mAdapter;

    public SettingView(Context context) {
        super(context);
        init();
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SettingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.setting_view, this);

        this.tv = (TextView) findViewById(R.id.tv);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new SettingAdapter(settingObjectList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        SettingObject settingObject = new SettingObject("Auto", true);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject("560p", false);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject("720p", false);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject("1080p", false);
        settingObjectList.add(settingObject);

        mAdapter.notifyDataSetChanged();
    }
}