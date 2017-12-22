package com.uiza.player.ui.views.view.settingview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import io.uiza.sdk.ui.R;

/**
 * Created by www.muathu@gmail.com on 12/22/2017.
 */

public class CustomDialogClass extends Dialog {
    public Context c;
    public Dialog d;
    public Button yes, no;

    public CustomDialogClass(Context a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);

    }

}