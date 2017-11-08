package com.google.android.exoplayer2.ui.language;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ui.R;
import com.google.android.exoplayer2.ui.util.LScreenUtil;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class LanguageView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private ImageView ivClose;
    private RowView rowSubtitleOn;
    private RowView rowSubtitleOff;
    private RowView english;
    private RowView vietnamese;
    private LinearLayout llControl;

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

        this.llControl = (LinearLayout) findViewById(R.id.ll_control);
        this.ivClose = (ImageView) findViewById(R.id.iv_close);
        this.rowSubtitleOn = (RowView) findViewById(R.id.row_subtitle_on);
        this.rowSubtitleOff = (RowView) findViewById(R.id.row_subtitle_off);
        this.english = (RowView) findViewById(R.id.english);
        this.vietnamese = (RowView) findViewById(R.id.vietnamese);

        rowSubtitleOn.setTvDescription("Subtitle ON");
        rowSubtitleOff.setTvDescription("Subtitle OFF");
        english.setTvDescription("English");
        vietnamese.setTvDescription("Vietnamese");

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClickClose();
                }
            }
        });
        rowSubtitleOn.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {

            }
        });
        rowSubtitleOff.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {

            }
        });
        english.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {

            }
        });
        vietnamese.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {

            }
        });

        /*int width = LScreenUtil.getScreenWidth() / 2;
        ViewGroup.LayoutParams recyclerViewParams = llControl.getLayoutParams();
        recyclerViewParams.width = width;
        llControl.setLayoutParams(recyclerViewParams);*/
    }

    public interface Callback {
        public void onClickClose();
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}