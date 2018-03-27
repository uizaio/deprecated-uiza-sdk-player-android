package com.uiza.player.ui.views.view.listview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
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

public class PlayListViewDialog extends Dialog {
    private final String TAG = getClass().getSimpleName();
    //TODO remove gson later
    private Gson gson = new Gson();
    private List<Item> itemList;
    private RecyclerView recyclerView;
    private PlayListAdapter playListAdapter;
    private TextView tvMsg;
    private RelativeLayout rootView;
    private Activity activity;

    public PlayListViewDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.play_list_view);
        init();
    }

    private void init() {
        LLog.d(TAG, "init");
        if (UizaData.getInstance().getInputModel() == null || UizaData.getInstance().getInputModel().getEntityID() == null) {
            LLog.d(TAG, "UizaData.getInstance().getInputModel() == null || UizaData.getInstance().getInputModel().getEntityID() == null -> return");
            return;
        }
        String entityId = UizaData.getInstance().getInputModel().getEntityID();
        LLog.d(TAG, "entityId: " + entityId);
        this.rootView = (RelativeLayout) findViewById(R.id.root_view);
        this.tvMsg = (TextView) findViewById(R.id.tv_msg);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ListAllEntityRelation listAllEntityRelation = UizaData.getInstance().getListAllEntityRelation(entityId);
        if (listAllEntityRelation == null) {
            LLog.d(TAG, "listAllEntityRelation == null -> only support api v2");
            tvMsg.setVisibility(View.VISIBLE);
            return;
        }
        itemList = listAllEntityRelation.getItemList();
        LLog.d(TAG, "listAllEntityRelation: " + gson.toJson(listAllEntityRelation));
        if (itemList == null || itemList.isEmpty()) {
            tvMsg.setVisibility(View.VISIBLE);
            return;
        } else {
            tvMsg.setVisibility(View.GONE);
        }
        LLog.d(TAG, "itemList size: " + itemList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        int widthRecyclerView;
        int heightRecyclerView;

        if (UizaData.getInstance().isLandscape()) {
            widthRecyclerView = UizaScreenUtil.getScreenWidth();
            heightRecyclerView = UizaScreenUtil.getScreenHeight() / 2;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = widthRecyclerView;
            recyclerViewParams.height = heightRecyclerView;
            recyclerView.setLayoutParams(recyclerViewParams);
        } else {
            widthRecyclerView = UizaScreenUtil.getScreenWidth();
            heightRecyclerView = UizaScreenUtil.getScreenHeight() / 5;

            ViewGroup.LayoutParams recyclerViewParams = recyclerView.getLayoutParams();
            recyclerViewParams.width = widthRecyclerView;
            recyclerViewParams.height = heightRecyclerView;
            recyclerView.setLayoutParams(recyclerViewParams);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        playListAdapter = new PlayListAdapter(getContext(), itemList, widthRecyclerView, heightRecyclerView, new PlayListAdapter.Callback() {
            @Override
            public void onClickItem(Item item, int position) {
                //TODO
            }
        });
        recyclerView.setAdapter(playListAdapter);

        UizaUIUtil.setUIUizaDialogPlayControlView(this, rootView, activity);
    }
}