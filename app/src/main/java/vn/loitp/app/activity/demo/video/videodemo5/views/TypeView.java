package vn.loitp.app.activity.demo.video.videodemo5.views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.Collapse;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.Expand;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.Parent;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ParentPosition;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.SingleTop;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.Toggle;
import vn.uiza.app.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@Parent
@SingleTop
@Layout(R.layout.row_type_uiza_video)
public class TypeView {

    @View(R.id.headingTxt)
    private TextView headingTxt;

    @View(R.id.toggleIcon)
    private ImageView toggleIcon;

    @Toggle(R.id.toggleView)
    private LinearLayout toggleView;

    @ParentPosition
    private int mParentPosition;

    private Context mContext;
    private String mHeading;

    public TypeView(Context context, String heading) {
        mContext = context;
        mHeading = heading;
    }

    @Resolve
    private void onResolved() {
        toggleIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_expand_more_black_48dp));
        headingTxt.setText(mHeading);
    }

    @Expand
    private void onExpand() {
        toggleIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_expand_less_black_48dp));
    }

    @Collapse
    private void onCollapse() {
        toggleIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_expand_more_black_48dp));
    }
}