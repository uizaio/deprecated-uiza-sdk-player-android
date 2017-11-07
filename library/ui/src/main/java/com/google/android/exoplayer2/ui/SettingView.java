package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class SettingView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;

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
        tv.setText("AAAAAAAAAAAAAAAAAAa");
    }
}