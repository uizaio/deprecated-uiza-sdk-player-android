package com.google.android.exoplayer2.ui.language;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.R;
import com.google.android.exoplayer2.ui.UizaData;
import com.google.android.exoplayer2.ui.settingview.SettingAdapter;
import com.google.android.exoplayer2.ui.settingview.SettingObject;
import com.google.android.exoplayer2.ui.util.LScreenUtil;
import com.google.android.exoplayer2.ui.util.LUIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class LanguageView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    //private TextView tv;

    public LanguageView(Context context) {
        super(context);
        init();
    }

    public LanguageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LanguageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.language_view, this);

        //this.tv = (TextView) findViewById(R.id.tv);
    }

}