package com.google.android.exoplayer2.ui.settingview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.R;
import com.google.android.exoplayer2.ui.UizaData;
import com.google.android.exoplayer2.ui.util.LScreenUtil;
import com.google.android.exoplayer2.ui.util.LUIUtil;

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
    private ImageView ivClose;
    private LinearLayout llTitle;

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
        this.ivClose = (ImageView) findViewById(R.id.iv_close);
        this.llTitle = (LinearLayout) findViewById(R.id.ll_title);

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClickClose();
                }
            }
        });

        settingAdapter = new SettingAdapter(settingObjectList, new SettingAdapter.Callback() {
            @Override
            public void onClickItem(final SettingObject settingObject) {
                for (int i = 0; i < settingObjectList.size(); i++) {
                    settingObjectList.get(i).setCheck(false);
                }
                settingObject.setCheck(true);
                settingAdapter.notifyDataSetChanged();

                UizaData.getInstance().setSettingObject(settingObject);

                LUIUtil.setDelay(300, new LUIUtil.DelayCallback() {
                    @Override
                    public void doAfter(int mls) {
                        if (callback != null) {
                            callback.onClickSettingObject(settingObject);
                        }
                    }
                });
            }
        });
        if (UizaData.getInstance().isLandscape()) {
            int width = LScreenUtil.getScreenWidth() / 2;

            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = width;
            recyclerView.setLayoutParams(recyclerViewParams);

            ViewGroup.LayoutParams params = llTitle.getLayoutParams();
            params.width = width;
            llTitle.setLayoutParams(params);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(settingAdapter);

        prepareMovieData();
    }

    public interface Callback {
        public void onClickSettingObject(SettingObject settingObject);

        public void onClickClose();
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void prepareMovieData() {
        SettingObject mCurrentSettingObject = UizaData.getInstance().getSettingObject();
        if (mCurrentSettingObject == null) {
            Log.d(TAG, "prepareMovieData mCurrentSettingObject null");
        } else {
            Log.d(TAG, "prepareMovieData mCurrentSettingObject " + mCurrentSettingObject.getDescription());
        }

        SettingObject settingObject = new SettingObject(SettingObject.AUTO, mCurrentSettingObject == null);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject(SettingObject.T560, false);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject(SettingObject.T720, false);
        settingObjectList.add(settingObject);

        settingObject = new SettingObject(SettingObject.T1080, false);
        settingObjectList.add(settingObject);

        if (mCurrentSettingObject != null) {
            for (int i = 0; i < settingObjectList.size(); i++) {
                if (settingObjectList.get(i).getDescription().equals(mCurrentSettingObject.getDescription())) {
                    settingObjectList.get(i).setCheck(true);
                    break;
                }
            }
        }

        settingAdapter.notifyDataSetChanged();
    }
}