package com.google.android.exoplayer2.ui.language;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.R;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class RowView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private TextView tvDescription;
    private ImageView ivCheck;
    private LinearLayout rootView;
    private Callback callback;

    public RowView(Context context) {
        super(context);
        init();
    }

    public RowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_view, this);

        this.rootView = (LinearLayout) findViewById(R.id.root_view);
        this.tvDescription = (TextView) findViewById(R.id.tv_description);
        this.ivCheck = (ImageView) findViewById(R.id.iv_check);

        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    updateUI(true);
                    callback.onClickItem();
                }
            }
        });
    }

    public interface Callback {
        public void onClickItem();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void updateUI(boolean isCheck) {
        if (isCheck) {
            ivCheck.setImageResource(R.drawable.ic_checked);
        } else {
            ivCheck.setImageResource(R.drawable.ic_unchecked);
        }
    }

    public void setTvDescription(String description) {
        tvDescription.setText(description);
    }

    public void setTvDescription(int stringRes) {
        tvDescription.setText(getContext().getString(stringRes));
    }
}