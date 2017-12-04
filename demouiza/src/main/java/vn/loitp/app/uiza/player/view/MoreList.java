package vn.loitp.app.uiza.player.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.uiza.player.ui.fragment.helper.InputModel;

import java.util.List;

import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Layout;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.NonReusable;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Resolve;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.View;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

//@Animate(Animation.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.uiza_more_list)
public class MoreList {

    @View(R.id.place_holder_view)
    private PlaceHolderView mPlaceHolderView;

    private Context mContext;
    private List<InputModel> mInputModelList;

    private MoreListItem.Callback mCallback;

    public MoreList(Context context, List<InputModel> inputModelList, MoreListItem.Callback callback) {
        mContext = context;
        mInputModelList = inputModelList;
        mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        mPlaceHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new GridLayoutManager(mContext, 3));
        LUIUtil.setPullLikeIOSHorizontal(mPlaceHolderView);

        for (int i = 0; i < mInputModelList.size(); i++) {
            mPlaceHolderView.addView(new MoreListItem(mContext, mPlaceHolderView, mInputModelList.get(i), i, mCallback));
        }
    }
}