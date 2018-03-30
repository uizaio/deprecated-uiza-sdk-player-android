package com.uiza.player.ui.views.view.language;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class LanguageViewDialog extends DialogFragment {
    private final String TAG = getClass().getSimpleName();
    private RelativeLayout rootView;
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

    private void init() {
        LLog.d(TAG, "init");

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
                if (languageCallback != null) {
                    languageCallback.onClickClose();
                }
            }
        });
        rowSubtitleOn.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                rowSubtitleOff.setCheck(false);
                languageObject.setSubOn(true);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (languageCallback != null) {
                    languageCallback.onClickSubOn();
                }
            }
        });
        rowSubtitleOff.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                rowSubtitleOn.setCheck(false);
                languageObject.setSubOn(false);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (languageCallback != null) {
                    languageCallback.onClickSubOff();
                }
            }
        });
        english.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                vietnamese.setCheck(false);
                languageObject.setEn(true);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (languageCallback != null) {
                    languageCallback.onClickEN();
                }
            }
        });
        vietnamese.setCallback(new RowView.Callback() {
            @Override
            public void onClickItem() {
                english.setCheck(false);
                languageObject.setEn(false);
                UizaData.getInstance().setLanguageObject(languageObject);
                if (languageCallback != null) {
                    languageCallback.onClickVI();
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

    private LanguageCallback languageCallback;

    public void setCallback(LanguageCallback callback) {
        this.languageCallback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.language_view, null);
        this.rootView = (RelativeLayout) view.findViewById(R.id.root_view);
        this.llControl = (LinearLayout) view.findViewById(R.id.ll_control);
        this.ivClose = (ImageView) view.findViewById(R.id.iv_close);
        this.rowSubtitleOn = (RowView) view.findViewById(R.id.row_subtitle_on);
        this.rowSubtitleOff = (RowView) view.findViewById(R.id.row_subtitle_off);
        this.english = (RowView) view.findViewById(R.id.english);
        this.vietnamese = (RowView) view.findViewById(R.id.vietnamese);
        this.rootView = (RelativeLayout) view.findViewById(R.id.root_view);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        init();
        return alertDialog;
    }

    public void showImmersive(Activity activity) {
        // Show the dialog.
        show(activity.getFragmentManager(), null);
        // It is necessary to call executePendingTransactions() on the FragmentManager
        // before hiding the navigation bar, because otherwise getWindow() would raise a
        // NullPointerException since the window was not yet created.
        getFragmentManager().executePendingTransactions();
        // Hide the navigation bar. It is important to do this after show() was called.
        // If we would do this in onCreateDialog(), we would get a requestFeature()
        // error.
        getDialog().getWindow().getDecorView().setSystemUiVisibility(
                getActivity().getWindow().getDecorView().getSystemUiVisibility()
        );
        // Make the dialogs window focusable again.
        getDialog().getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        );

        UizaUIUtil.setUIUizaDialogPlayControlView(getDialog(), rootView, activity);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        LLog.d(TAG, "onDismiss");
        if (languageCallback != null) {
            languageCallback.onDismiss();
        }
    }
}