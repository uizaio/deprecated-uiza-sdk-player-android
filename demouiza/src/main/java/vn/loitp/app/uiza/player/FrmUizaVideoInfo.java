package vn.loitp.app.uiza.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.data.UizaRepositoryObserver;
import com.uiza.player.ui.data.UizaSubject;
import com.uiza.player.ui.views.helper.InputModel;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.core.base.BaseFragment;
import vn.loitp.utils.util.ToastUtils;

import vn.loitp.app.uiza.player.view.InfoView;
import vn.loitp.app.uiza.player.view.MoreList;
import vn.loitp.app.uiza.player.view.MoreListItem;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;
import vn.loitp.views.placeholderview.lib.placeholderview.InfinitePlaceHolderView;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment implements UizaRepositoryObserver {
    private final String TAG = getClass().getSimpleName();
    private InfinitePlaceHolderView infinitePlaceHolderView;
    private UizaSubject mUserDataRepository;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mUserDataRepository.removeObserver(this);
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.uiza_video_info_frm, container, false);

        mUserDataRepository = UizaData.getInstance();
        mUserDataRepository.registerObserver(this);

        infinitePlaceHolderView = (InfinitePlaceHolderView) view.findViewById(R.id.place_holder_view);
        LUIUtil.setPullLikeIOSVertical(infinitePlaceHolderView);

        return view;
    }

    private InfoView infoView;
    private MoreList moreList;

    @Override
    public void onInputModelChange(InputModel inputModel) {
        if (inputModel == null) {
            return;
        }
        if (infoView != null || moreList != null) {
            return;
        }
        infoView = new InfoView(inputModel);
        infinitePlaceHolderView.addView(infoView);

        List<InputModel> inputModelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            inputModelList.add(inputModel);
        }
        moreList = new MoreList(getActivity(), inputModelList, new MoreListItem.Callback() {
            @Override
            public void onClick(InputModel inputModel, int position) {
                ToastUtils.showShort("onClick " + position);
            }
        });
        infinitePlaceHolderView.addView(moreList);
    }
}