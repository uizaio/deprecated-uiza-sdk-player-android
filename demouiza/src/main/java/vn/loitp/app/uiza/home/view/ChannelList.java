package vn.loitp.app.uiza.home.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import java.util.List;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.PlaceHolderView;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.NonReusable;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.uiza.home.model.ChannelObject;
import vn.loitp.app.uiza.home.model.VideoObject;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

//@Animate(Animation.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.uiza_channel_list)
public class ChannelList {

    @View(R.id.placeholderview)
    private PlaceHolderView mPlaceHolderView;

    @View(R.id.tv_channel_name)
    private TextView tvChannelName;

    private Context mContext;
    private ChannelObject mChannelObject;

    private ChannelItem.Callback mCallback;

    public ChannelList(Context context, ChannelObject channelObject, ChannelItem.Callback callback) {
        mContext = context;
        mChannelObject = channelObject;
        mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        tvChannelName.setText(mChannelObject.getChannelName());

        mPlaceHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        LUIUtil.setPullLikeIOSHorizontal(mPlaceHolderView);

        List<VideoObject> videoObjectList = mChannelObject.getVideoObjectList();
        for (int i = 0; i < videoObjectList.size(); i++) {
            mPlaceHolderView.addView(new ChannelItem(mContext, mPlaceHolderView, videoObjectList.get(i), i, mCallback));
        }
    }
}