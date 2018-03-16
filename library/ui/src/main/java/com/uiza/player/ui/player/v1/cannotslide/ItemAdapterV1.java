package com.uiza.player.ui.player.v1.cannotslide;

/**
 * Created by www.muathu@gmail.com on 12/8/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;

import java.util.List;

import io.uiza.sdk.ui.R;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LAnimationUtil;
import vn.loitp.core.utilities.LImageUtil;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.uiza.model.v1.listallentity.Item;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

public class ItemAdapterV1 extends RecyclerView.Adapter<ItemAdapterV1.ItemViewHolder> {

    public interface Callback {
        public void onClick(Item movie, int position);

        public void onLoadMore();
    }

    private Callback callback;
    private Context mContext;
    private List<Item> itemList;
    private int mSizeW;
    private int mSizeH;

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public AVLoadingIndicatorView avi;
        private TextView tvName;

        public ItemViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
            tvName = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public ItemAdapterV1(Context context, List<Item> itemList, int sizeW, int sizeH, Callback callback) {
        this.mContext = context;
        this.itemList = itemList;
        this.mSizeW = sizeW;
        this.mSizeH = sizeH;
        this.callback = callback;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_more_like_this, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Item item = itemList.get(position);
        holder.imageView.getLayoutParams().width = mSizeW;
        holder.imageView.getLayoutParams().height = mSizeH;
        holder.imageView.requestLayout();

        if (item.getThumbnail() == null || item.getThumbnail().isEmpty()) {
            LImageUtil.load((Activity) mContext, Constants.URL_IMG_16x9, holder.imageView, holder.avi);
        } else {
            LImageUtil.load((Activity) mContext, Constants.PREFIXS + item.getThumbnail(), holder.imageView, holder.avi);
        }

        /*String[] urls = new String[2];
        urls[0] = item.getPoster();
        urls[1] = item.getThumbnail();
        LImageUtil.load((Activity) mContext, urls, imageView, avi);*/

        holder.tvName.setText(item.getName());
        LUIUtil.setTextShadow(holder.tvName);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    LAnimationUtil.play(v, Techniques.Pulse, new LAnimationUtil.Callback() {
                        @Override
                        public void onCancel() {
                            //do nothing
                        }

                        @Override
                        public void onEnd() {
                            callback.onClick(item, position);
                        }

                        @Override
                        public void onRepeat() {
                            //do nothing
                        }

                        @Override
                        public void onStart() {
                            //do nothing
                        }
                    });
                }
            }
        });
        if (position == itemList.size() - 1) {
            if (callback != null) {
                callback.onLoadMore();
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }
}