package com.uiza.player.ui.fragment.view.settingview;

/**
 * Created by www.muathu@gmail.com on 11/7/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.uiza.sdk.ui.R;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingHolder> {
    private List<SettingObject> settingObjectList;

    public class SettingHolder extends RecyclerView.ViewHolder {
        private TextView tvDescription;
        private ImageView ivCheck;
        private LinearLayout rootView;

        public SettingHolder(View view) {
            super(view);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            ivCheck = (ImageView) view.findViewById(R.id.iv_check);
            rootView = (LinearLayout) view.findViewById(R.id.root_view);
        }
    }

    public SettingAdapter(List<SettingObject> settingObjectList, Callback callback) {
        this.settingObjectList = settingObjectList;
        this.callback = callback;
    }

    @Override
    public SettingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_setting, parent, false);
        return new SettingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SettingHolder settingHolder, int position) {
        final SettingObject settingObject = settingObjectList.get(position);
        settingHolder.tvDescription.setText(settingObject.getDescription());
        if (settingObject.isCheck()) {
            settingHolder.ivCheck.setImageResource(R.drawable.ic_checked);
        } else {
            settingHolder.ivCheck.setImageResource(R.drawable.ic_unchecked);
        }
        settingHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClickItem(settingObject);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingObjectList == null ? 0 : settingObjectList.size();
    }

    public interface Callback {
        public void onClickItem(SettingObject settingObject);
    }

    private Callback callback;
}