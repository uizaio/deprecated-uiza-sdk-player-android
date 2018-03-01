package vn.loitp.app.uiza.home.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.Item;
import vn.loitp.uiza.R;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Click;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Layout;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Resolve;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.View;

/**
 * Created by janisharali on 19/08/16.
 */
@Layout(R.layout.uiza_drawer_item)
public class UizaDrawerMenuItem {
    //@Position
    // private int mMenuPosition;

    private Context mContext;

    @View(R.id.tv_name)
    private TextView tvName;

    @View(R.id.iv_press)
    private ImageView ivPress;

    private List<Item> itemList;
    private int mPos;
    private Callback mCallback;

    public UizaDrawerMenuItem(Context context, List<Item> itemList, int pos, Callback callback) {
        this.mContext = context;
        this.itemList = itemList;
        this.mPos = pos;
        this.mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        tvName.setText(itemList.get(mPos).getName());
        if (mPos == HomeData.getInstance().getCurrentPosition()) {
            ivPress.setVisibility(android.view.View.VISIBLE);
        } else {
            ivPress.setVisibility(android.view.View.INVISIBLE);
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick() {
        if (mCallback != null) {
            mCallback.onMenuItemClick(mPos);
        }
    }

    public interface Callback {
        public void onMenuItemClick(int pos);
    }
}