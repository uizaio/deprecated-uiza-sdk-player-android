package com.uiza.player.ui.views.view.language;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaUIUtil;

import io.uiza.sdk.ui.R;
import vn.loitp.core.utilities.LLog;

/**
 * Created by LENOVO on 3/27/2018.
 */

public class LanguageViewDialog extends Dialog {
    private final String TAG = getClass().getSimpleName();
    private RelativeLayout rootView;
    private Activity activity;
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

    public LanguageViewDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.language_view);
        init();
    }

    private void init() {
        LLog.d(TAG, "init");
        this.llControl = (LinearLayout) findViewById(R.id.ll_control);
        this.ivClose = (ImageView) findViewById(R.id.iv_close);
        this.rowSubtitleOn = (RowView) findViewById(R.id.row_subtitle_on);
        this.rowSubtitleOff = (RowView) findViewById(R.id.row_subtitle_off);
        this.english = (RowView) findViewById(R.id.english);
        this.vietnamese = (RowView) findViewById(R.id.vietnamese);
        this.rootView = (RelativeLayout) findViewById(R.id.root_view);

        UizaUIUtil.setUIUizaDialogPlayControlView(this, rootView, activity);

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

        ivClose.setOnClickListener(new View.OnClickListener() {
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

    private LanguageViewDialog.Callback callback;

    public void setCallback(LanguageViewDialog.Callback callback) {
        this.callback = callback;
    }
}