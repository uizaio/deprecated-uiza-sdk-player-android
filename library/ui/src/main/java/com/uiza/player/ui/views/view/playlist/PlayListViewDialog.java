package com.uiza.player.ui.views.view.playlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaUIUtil;

import java.util.List;

import io.uiza.sdk.ui.R;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;
import vn.loitp.restapi.uiza.model.v2.listallentityrelation.ListAllEntityRelation;

/**
 * Created by LENOVO on 3/27/2018.
 */

public class PlayListViewDialog extends DialogFragment {
    private final String TAG = getClass().getSimpleName();
    //private Gson gson = new Gson();
    private List<Item> itemList;
    private RecyclerView recyclerView;
    private PlayListAdapter playListAdapter;
    private TextView tvMsg;
    private RelativeLayout rootView;

    private PlayListCallback playListCallback;

    public void setPlayListAdapterCallback(PlayListCallback playListCallback) {
        this.playListCallback = playListCallback;
    }

    private void init() {
        LLog.d(TAG, "init");
        if (UizaData.getInstance().getInputModel() == null || UizaData.getInstance().getInputModel().getEntityID() == null) {
            LLog.d(TAG, "UizaData.getInstance().getInputModel() == null || UizaData.getInstance().getInputModel().getEntityID() == null -> return");
            return;
        }
        String entityId = UizaData.getInstance().getInputModel().getEntityID();
        LLog.d(TAG, "entityId: " + entityId);

        ListAllEntityRelation listAllEntityRelation = UizaData.getInstance().getListAllEntityRelation(entityId);
        if (listAllEntityRelation == null) {
            LLog.d(TAG, "listAllEntityRelation == null -> only support api v2");
            tvMsg.setText(R.string.only_support_apiv2);
            tvMsg.setVisibility(View.VISIBLE);
            return;
        }
        itemList = listAllEntityRelation.getItemList();
        //LLog.d(TAG, "listAllEntityRelation: " + gson.toJson(listAllEntityRelation));
        if (itemList == null || itemList.isEmpty()) {
            LLog.d(TAG, "itemList == null || itemList.isEmpty() -> return");
            tvMsg.setVisibility(View.VISIBLE);
            return;
        } else {
            tvMsg.setVisibility(View.GONE);
        }
        LLog.d(TAG, "itemList size: " + itemList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        int widthRecyclerView;
        int heightRecyclerView;

        if (UizaData.getInstance().isLandscape()) {
            LLog.d(TAG, "isLandscape");
            widthRecyclerView = UizaScreenUtil.getScreenWidth();
            heightRecyclerView = UizaScreenUtil.getScreenHeight() / 2;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = widthRecyclerView;
            recyclerViewParams.height = heightRecyclerView;
            recyclerView.setLayoutParams(recyclerViewParams);
        } else {
            LLog.d(TAG, "!isLandscape");
            widthRecyclerView = UizaScreenUtil.getScreenWidth();
            heightRecyclerView = UizaScreenUtil.getScreenHeight() / 5;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = widthRecyclerView;
            recyclerViewParams.height = heightRecyclerView;
            recyclerView.setLayoutParams(recyclerViewParams);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LLog.d(TAG, "--------> " + widthRecyclerView + " x " + heightRecyclerView);
        playListAdapter = new PlayListAdapter(getActivity(), itemList, widthRecyclerView, heightRecyclerView, playListCallback);
        recyclerView.setAdapter(playListAdapter);
        LLog.d(TAG, "init done");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.play_list_view, null);
        this.rootView = (RelativeLayout) view.findViewById(R.id.root_view);
        this.tvMsg = (TextView) view.findViewById(R.id.tv_msg);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
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
        if (playListCallback != null) {
            playListCallback.onDismiss();
        }
    }
}