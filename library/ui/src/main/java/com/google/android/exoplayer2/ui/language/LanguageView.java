package com.google.android.exoplayer2.ui.language;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ui.data.UizaData;
import com.google.android.exoplayer2.ui.util.UizaScreenUtil;

import io.uiza.sdk.ui.R;

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

    private final String SUB_ON = "Subtitle ON";
    private final String SUB_OFF = "Subtitle OFF";
    private final String EN = "English";
    private final String VI = "Vietnamese";

    private LanguageObject languageObject;

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

        rowSubtitleOn.setTvDescription(SUB_ON);
        rowSubtitleOff.setTvDescription(SUB_OFF);
        english.setTvDescription(EN);
        vietnamese.setTvDescription(VI);

        languageObject = UizaData.getInstance().getLanguageObject();
        if (languageObject == null) {
            languageObject = new LanguageObject();
            languageObject.setSubOn(false);
            languageObject.setEn(true);
            UizaData.getInstance().setLanguageObject(languageObject);

            rowSubtitleOn.setCheck(false);
            rowSubtitleOff.setCheck(true);
            english.setCheck(true);
            vietnamese.setCheck(false);
        } else {
            if (languageObject.isSubOn()) {
                rowSubtitleOn.setCheck(true);
                rowSubtitleOff.setCheck(false);
            } else {
                rowSubtitleOn.setCheck(false);
                rowSubtitleOff.setCheck(true);
            }
            if (languageObject.isEn()) {
                english.setCheck(true);
                vietnamese.setCheck(false);
            } else {
                english.setCheck(false);
                vietnamese.setCheck(true);
            }
        }

        rowSubtitleOn.setCanDoubleClick(false);
        rowSubtitleOff.setCanDoubleClick(false);
        english.setCanDoubleClick(false);
        vietnamese.setCanDoubleClick(false);

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
                rowSubtitleOff.setCheck(false);
                languageObject.setSubOn(true);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (callback != null) {
                    callback.onClickSubOn();
                }
            }
        });
        rowSubtitleOff.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                rowSubtitleOn.setCheck(false);
                languageObject.setSubOn(false);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (callback != null) {
                    callback.onClickSubOff();
                }
            }
        });
        english.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                vietnamese.setCheck(false);
                languageObject.setEn(true);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (callback != null) {
                    callback.onClickEN();
                }
            }
        });
        vietnamese.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                english.setCheck(false);
                languageObject.setEn(false);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (callback != null) {
                    callback.onClickVI();
                }
            }
        });

        if (!UizaData.getInstance().isLandscape()) {
            int width = UizaScreenUtil.getScreenWidth() * 3 / 2;
            ViewGroup.LayoutParams layoutParams = llControl.getLayoutParams();
            layoutParams.width = width;
            llControl.setLayoutParams(layoutParams);
        }
    }

    public interface Callback {
        public void onClickClose();

        public void onClickSubOn();

        public void onClickSubOff();

        public void onClickEN();

        public void onClickVI();
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}