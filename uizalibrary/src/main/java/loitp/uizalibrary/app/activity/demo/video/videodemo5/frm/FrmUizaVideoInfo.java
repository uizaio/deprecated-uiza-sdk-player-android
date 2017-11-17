package loitp.uizalibrary.app.activity.demo.video.videodemo5.frm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.loitp.app.base.BaseFragment;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.uiza_video_info_view_frm, container, false);
        //TextView tv = (TextView) view.findViewById(R.id.tv);
        return view;
    }
}