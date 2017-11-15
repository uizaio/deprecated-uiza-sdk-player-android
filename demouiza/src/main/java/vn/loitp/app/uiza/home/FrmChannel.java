package vn.loitp.app.uiza.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.InfinitePlaceHolderView;
import vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery.Image;
import vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery.Utils;
import vn.loitp.app.base.BaseFragment;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.uiza.home.model.PosterObject;
import vn.loitp.app.uiza.home.view.PosterView;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmChannel extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;
    private InfinitePlaceHolderView infinitePlaceHolderView;

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
        View view = inflater.inflate(R.layout.uiza_frm_channel, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(HomeData.getInstance().getData());

        infinitePlaceHolderView = (InfinitePlaceHolderView) view.findViewById(R.id.place_holder_view);

        LUIUtil.setPullLikeIOSVertical(infinitePlaceHolderView);
        setupData();
        return view;
    }

    private void setupData() {
        List<PosterObject> posterObjectList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PosterObject posterObject = new PosterObject();
            posterObject.setUrl("https://kenh14cdn.com/2017/photo-1-1508474775876.jpg");
            posterObjectList.add(posterObject);
        }
        infinitePlaceHolderView.addView(new PosterView(getActivity(), posterObjectList));

    }
}