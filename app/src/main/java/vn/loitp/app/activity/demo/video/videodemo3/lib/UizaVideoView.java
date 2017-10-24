package vn.loitp.app.activity.demo.video.videodemo3.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class UizaVideoView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();

    public UizaVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UizaVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_l_action_bar, this);

        //this.ivIconBack = (ImageView) findViewById(R.id.iv_icon_back);

    }
}