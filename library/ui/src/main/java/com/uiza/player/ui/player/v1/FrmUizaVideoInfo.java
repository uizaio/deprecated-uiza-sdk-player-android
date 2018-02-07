package com.uiza.player.ui.player.v1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uiza.player.core.uiza.api.model.getentityinfo.Item;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    //private AVLoadingIndicatorView avLoadingIndicatorView;
    private TextView tvVideoName;
    private TextView tvVideoTime;
    private TextView tvVideoRate;
    private TextView tvVideoDescription;
    private TextView tvVideoStarring;
    private TextView tvVideoDirector;
    private TextView tvVideoGenres;
    private TextView tvDebugJson;

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
        LLog.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.uiza_video_info_frm, container, false);

        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        LUIUtil.setPullLikeIOSVertical(scrollView);

        //avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        //avLoadingIndicatorView.smoothToShow();

        tvVideoName = (TextView) view.findViewById(R.id.tv_video_name);
        tvVideoTime = (TextView) view.findViewById(R.id.tv_video_time);
        tvVideoRate = (TextView) view.findViewById(R.id.tv_video_rate);
        tvVideoDescription = (TextView) view.findViewById(R.id.tv_video_description);
        tvVideoStarring = (TextView) view.findViewById(R.id.tv_video_starring);
        tvVideoDirector = (TextView) view.findViewById(R.id.tv_video_director);
        tvVideoGenres = (TextView) view.findViewById(R.id.tv_video_genres);
        tvDebugJson = (TextView) view.findViewById(R.id.tv_debug_json);

        mInputModel = UizaData.getInstance().getInputModel();
        setup();
        return view;
    }

    private void setup() {
        if (mInputModel == null) {
            return;
        }
        if (mItem == null) {
            //getDetailEntity();
            if (mInputModel.getEntityInfo() != null) {
                mItem = mInputModel.getEntityInfo().getItem();
                updateUI();
            }
        }
    }

    private InputModel mInputModel;
    private Item mItem;

    private void updateUI() {
        String empty = "Empty";
        try {
            tvVideoName.setText(mItem.getName());
        } catch (NullPointerException e) {
            tvVideoName.setText(empty);
        }
        try {
            tvVideoTime.setText(mItem.getCreatedAt());
        } catch (NullPointerException e) {
            tvVideoTime.setText(empty);
        }

        tvVideoRate.setText("18+");
        try {
            tvVideoDescription.setText(mItem.getDescription().isEmpty() ? mItem.getShortDescription().isEmpty() ? empty : mItem.getShortDescription() : mItem.getDescription());
        } catch (NullPointerException e) {
            tvVideoDescription.setText(empty);
        }

        //TODO
        tvVideoStarring.setText(empty);
        tvVideoDirector.setText(empty);
        tvVideoGenres.setText(empty);

        LUIUtil.printBeautyJson(mItem, tvDebugJson);
    }
}