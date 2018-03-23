package vn.loitp.app.uiza.home.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.loitp.app.uiza.data.HomeDataV2;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.Datum;
import vn.loitp.uiza.R;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Click;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Layout;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Resolve;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.View;

/**
 * Created by janisharali on 19/08/16.
 */
@Layout(R.layout.uiza_drawer_item)
public class UizaDrawerMenuItemV2 {
    //@Position
    // private int mMenuPosition;

    private Context mContext;

    @View(R.id.tv_name)
    private TextView tvName;

    @View(R.id.mainView)
    private LinearLayout mainView;

    @View(R.id.iv_press)
    private ImageView ivPress;

    private List<Datum> datumList;
    private int mPos;
    private Callback mCallback;

    public UizaDrawerMenuItemV2(Context context, List<Datum> datumList, int pos, Callback callback) {
        this.mContext = context;
        this.datumList = datumList;
        this.mPos = pos;
        this.mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        tvName.setText(datumList.get(mPos).getName());
        if (mPos == HomeDataV2.getInstance().getCurrentPosition()) {
            ivPress.setVisibility(android.view.View.VISIBLE);
            mainView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.drawer_hightlight));
        } else {
            ivPress.setVisibility(android.view.View.INVISIBLE);
            mainView.setBackgroundColor(Color.TRANSPARENT);
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