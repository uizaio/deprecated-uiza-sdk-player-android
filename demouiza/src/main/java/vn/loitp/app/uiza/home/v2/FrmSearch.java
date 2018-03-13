package vn.loitp.app.uiza.home.v2;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.data.EventBusData;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmSearch extends BaseFragment implements IOnBackPressed {
    private final String TAG = getClass().getSimpleName();

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
        View view = inflater.inflate(R.layout.uiza_frm_search_v2, container, false);
        return view;
    }

    @Override
    public boolean onBackPressed() {
        LLog.d(TAG, TAG + " onBackPressed");
        getActivity().getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        LLog.d(TAG, TAG + " onFragmentResume");
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        LLog.d(TAG, TAG + " onFragmentPause");
    }
}