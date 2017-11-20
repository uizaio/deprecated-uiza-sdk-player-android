package vn.loitp.app.uiza.player;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.UizaData;
import com.google.android.exoplayer2.ui.fragment.helper.InputModel;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.InfinitePlaceHolderView;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseFragment;
import vn.loitp.app.common.Constants;
import vn.loitp.app.uiza.home.model.ChannelObject;
import vn.loitp.app.uiza.home.model.PosterObject;
import vn.loitp.app.uiza.home.view.ChannelItem;
import vn.loitp.app.uiza.home.view.ChannelList;
import vn.loitp.app.uiza.home.view.PosterView;
import vn.loitp.app.uiza.player.view.InfoView;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment implements UizaData.CallbackInputModelChange {
    private final String TAG = getClass().getSimpleName();
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
        View view = inflater.inflate(R.layout.uiza_video_info_frm, container, false);

        infinitePlaceHolderView = (InfinitePlaceHolderView) view.findViewById(R.id.place_holder_view);
        LUIUtil.setPullLikeIOSVertical(infinitePlaceHolderView);

        UizaData.getInstance().setCallbackInputModelChange(this);
        return view;
    }

    @Override
    public void onInputModelChange(InputModel inputModel) {
        //LLog.d(TAG, "onInputModelChange " + LSApplication.getInstance().getGson().toJson(inputModel));
        if (inputModel == null) {
            return;
        }
        infinitePlaceHolderView.addView(new InfoView(inputModel));
    }
}