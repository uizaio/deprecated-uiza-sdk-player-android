package com.google.android.exoplayer2.ui.settingview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
    private SettingAdapter settingAdapter;

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
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        settingAdapter = new SettingAdapter(settingObjectList, new SettingAdapter.Callback() {
            @Override
            public void onClickItem(SettingObject settingObject) {
                for (int i = 0; i < settingObjectList.size(); i++) {
                    settingObjectList.get(i).setCheck(false);
                }
                settingObject.setCheck(true);
                settingAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(settingAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        SettingObject settingObject = new SettingObject(SettingObject.AUTO, true);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject(SettingObject.T560, false);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject(SettingObject.T720, false);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject(SettingObject.T1080, false);
        settingObjectList.add(settingObject);

        settingAdapter.notifyDataSetChanged();
    }
}