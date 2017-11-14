package vn.loitp.app.uiza.home;

import android.widget.ImageView;
import android.widget.TextView;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Click;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.NonReusable;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@NonReusable
@Layout(R.layout.uiza_drawer_header)
public class UizaDrawerHeader {
    @View(R.id.iv_avatar)
    private ImageView ivAvatar;

    @View(R.id.tv_name)
    private TextView tvName;

    @View(R.id.iv_log_out)
    private ImageView ivLogOut;

    @Resolve
    private void onResolved() {
        tvName.setText("Loitp");
    }

    @Click(R.id.iv_log_out)
    private void onClickLogOut() {
        ToastUtils.showShort("onClickLogOut");
    }
}
