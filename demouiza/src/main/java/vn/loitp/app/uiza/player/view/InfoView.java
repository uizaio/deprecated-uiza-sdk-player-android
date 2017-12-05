package vn.loitp.app.uiza.player.view;

import android.widget.TextView;

import com.uiza.player.ui.views.helper.InputModel;
import vn.loitp.livestar.R;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Layout;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.NonReusable;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.Resolve;
import vn.loitp.views.placeholderview.lib.placeholderview.annotations.View;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

//@Animate(Animation.CARD_LEFT_IN_ASC)
@NonReusable
@Layout(R.layout.uiza_info)
public class InfoView {
    private final String TAG = getClass().getSimpleName();

    @View(R.id.tv_name)
    private TextView tvName;

    @View(R.id.tv_time)
    private TextView tvTime;

    @View(R.id.tv_duration)
    private TextView tvDuration;

    @View(R.id.tv_rate)
    private TextView tvRate;

    @View(R.id.tv_description)
    private TextView tvDescription;

    @View(R.id.tv_starring_value)
    private TextView tvStarringValue;

    @View(R.id.tv_director)
    private TextView tvDirector;

    @View(R.id.tv_genres_value)
    private TextView tvGenresValue;

    private InputModel mInputModel;

    public InfoView(InputModel inputModel) {
        mInputModel = inputModel;
    }

    @Resolve
    private void onResolved() {
        tvName.setText(mInputModel.getTitle());
        tvTime.setText(mInputModel.getTime());
        tvDuration.setText(mInputModel.getDuration());
        tvRate.setText(mInputModel.getRate() + "");
        tvDescription.setText(mInputModel.getDescription());
        tvStarringValue.setText(mInputModel.getStarring());
        tvDirector.setText(mInputModel.getDirector());
        tvGenresValue.setText(mInputModel.getGenres());
    }
}