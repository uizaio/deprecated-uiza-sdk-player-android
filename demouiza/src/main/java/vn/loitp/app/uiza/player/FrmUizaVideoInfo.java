package vn.loitp.app.uiza.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.UizaData;
import com.google.android.exoplayer2.ui.fragment.helper.InputModel;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseFragment;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment implements UizaData.CallbackInputModelChange {
    private final String TAG = getClass().getSimpleName();
    private ScrollView scrollView;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvDuration;
    private TextView tvRate;
    private TextView tvDescription;
    private TextView tvStarringValue;
    private TextView tvDirector;
    private TextView tvGenresValue;

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
        scrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        LUIUtil.setPullLikeIOSVertical(scrollView);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvDuration = (TextView) view.findViewById(R.id.tv_duration);
        tvRate = (TextView) view.findViewById(R.id.tv_rate);
        tvDescription = (TextView) view.findViewById(R.id.tv_description);
        tvStarringValue = (TextView) view.findViewById(R.id.tv_starring_value);
        tvDirector = (TextView) view.findViewById(R.id.tv_director);
        tvGenresValue = (TextView) view.findViewById(R.id.tv_genres_value);

        UizaData.getInstance().setCallbackInputModelChange(this);
        return view;
    }

    @Override
    public void onInputModelChange(InputModel inputModel) {
        LLog.d(TAG, "onInputModelChange " + LSApplication.getInstance().getGson().toJson(inputModel));
        if (inputModel == null) {
            return;
        }
        tvName.setText(inputModel.getTitle());
        tvTime.setText(inputModel.getTime());
        tvDuration.setText(inputModel.getDuration());
        tvRate.setText(inputModel.getRate() + "");
        tvDescription.setText(inputModel.getDescription());
        tvStarringValue.setText(inputModel.getStarring());
        tvDirector.setText(inputModel.getDirector());
        tvGenresValue.setText(inputModel.getGenres());
    }
}