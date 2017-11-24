package vn.loitp.app.uiza.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ui.data.UizaData;
import com.google.android.exoplayer2.ui.data.UizaRepositoryObserver;
import com.google.android.exoplayer2.ui.data.UizaSubject;
import com.google.android.exoplayer2.ui.fragment.helper.InputModel;

import java.util.ArrayList;
import java.util.List;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.InfinitePlaceHolderView;
import vn.loitp.app.base.BaseFragment;
import vn.loitp.app.uiza.player.view.InfoView;
import vn.loitp.app.uiza.player.view.MoreList;
import vn.loitp.app.uiza.player.view.MoreListItem;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

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

    @Override
    public void onInputModelChange(InputModel inputModel) {
        if (inputModel == null) {
            return;
        }
        infinitePlaceHolderView.addView(new InfoView(inputModel));

        List<InputModel> inputModelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            inputModelList.add(inputModel);
        }
        infinitePlaceHolderView.addView(new MoreList(getActivity(), inputModelList, new MoreListItem.Callback() {
            @Override
            public void onClick(InputModel inputModel, int position) {
                ToastUtils.showShort("onClick " + position);
            }
        }));
    }
}