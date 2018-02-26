package com.uiza.player.ui.player.v1;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.uiza.model.getdetailentity.Item;
import vn.loitp.views.placeholderview.lib.placeholderview.InfinitePlaceHolderView;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;

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

    private InputModel mInputModel;
    private Item mItem;

    private PlaceHolderView placeHolderView;

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

        NestedScrollView nestedScrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);
        LUIUtil.setPullLikeIOSVertical(nestedScrollView);

        //avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        //avLoadingIndicatorView.smoothToShow();

        tvVideoName = (TextView) view.findViewById(R.id.tv_video_name);
        tvVideoTime = (TextView) view.findViewById(R.id.tv_video_time);
        tvVideoRate = (TextView) view.findViewById(R.id.tv_video_rate);
        tvVideoDescription = (TextView) view.findViewById(R.id.tv_video_description);
        tvVideoStarring = (TextView) view.findViewById(R.id.tv_video_starring);
        tvVideoDirector = (TextView) view.findViewById(R.id.tv_video_director);
        tvVideoGenres = (TextView) view.findViewById(R.id.tv_video_genres);

        placeHolderView = (PlaceHolderView) view.findViewById(R.id.place_holder_view);

        mInputModel = UizaData.getInstance().getInputModel();
        setup();
        return view;
    }

    private void setup() {
        if (mInputModel == null) {
            return;
        }
        if (mItem == null) {
            try {
                if (mInputModel.getDetailEntity() != null) {
                    mItem = mInputModel.getDetailEntity().getItem().get(0);
                    updateUI();
                }
            } catch (Exception e) {
                showDialogError("Setup Error\n" + e.toString());
            }
        }
    }

    private void updateUI() {
        final String emptyS = "Empty string";
        final String nullS = "Data is null";
        try {
            tvVideoName.setText(mItem.getName());
        } catch (NullPointerException e) {
            tvVideoName.setText(nullS);
        }
        try {
            //TODO
            tvVideoTime.setText("Dummy Time");
        } catch (NullPointerException e) {
            tvVideoTime.setText(nullS);
        }
        //TODO
        tvVideoRate.setText("Dummy 18+");
        try {
            tvVideoDescription.setText(mItem.getDescription().isEmpty() ? mItem.getShortDescription().isEmpty() ? emptyS : mItem.getShortDescription() : mItem.getDescription());
        } catch (NullPointerException e) {
            tvVideoDescription.setText(nullS);
        }

        //TODO
        tvVideoStarring.setText(emptyS);
        tvVideoDirector.setText(emptyS);
        tvVideoGenres.setText(emptyS);

        //list more like this
        placeHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        LUIUtil.setPullLikeIOSVertical(placeHolderView);

        getListAllEntityRelation();
    }

    private void getListAllEntityRelation(){
        //TODO http://dev-api.uiza.io/resource/index.html#api-Entity-List_All_Entity_Relation
    }
}