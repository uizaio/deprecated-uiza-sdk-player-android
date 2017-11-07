package com.google.android.exoplayer2.ui.settingview;

/**
 * Created by www.muathu@gmail.com on 11/7/2017.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.R;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingHolder> {
    private List<SettingObject> settingObjectList;

    public class SettingHolder extends RecyclerView.ViewHolder {
        public TextView tvDescription;
        public ImageView ivCheck;

        public SettingHolder(View view) {
            super(view);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            ivCheck = (ImageView) view.findViewById(R.id.iv_check);
        }
    }

    public SettingAdapter(List<SettingObject> settingObjectList) {
        this.settingObjectList = settingObjectList;
    }

    @Override
    public SettingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_setting, parent, false);
        return new SettingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SettingHolder holder, int position) {
        SettingObject settingObject = settingObjectList.get(position);
        holder.tvDescription.setText(settingObject.getDescription());
        if (settingObject.isCheck()) {
            holder.ivCheck.setBackgroundColor(Color.RED);
        } else {
            holder.ivCheck.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return settingObjectList == null ? 0 : settingObjectList.size();
    }
}