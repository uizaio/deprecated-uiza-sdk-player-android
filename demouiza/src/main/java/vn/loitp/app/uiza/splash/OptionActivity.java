package vn.loitp.app.uiza.splash;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.uiza.player.ui.data.UizaData;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.uiza.R;

public class OptionActivity extends BaseActivity {
    public static final String KEY_SDK_VERSION = "KEY_SDK_VERSION";

    private RadioGroup radioGroupSkin;
    private RadioButton radioSkin1;
    private RadioButton radioSkin2;
    private RadioButton radioSkin3;
    private String currentPlayerId;

    private RadioGroup radioGroupSlide;
    private RadioButton radioCanSlide;
    private RadioButton radioCannotSlide;
    private boolean canSlide;

    private RadioGroup radioGroupApiEndPoint;
    private RadioButton radioApiEndPoint1;
    private String currentApiEndPoint;

    private RadioGroup radioGroupApiTrackingEndPoint;
    private RadioButton radioApiTrackingEndPoint1;
    private RadioButton radioApiTrackingEndPoint2;
    private RadioButton radioApiTrackingEndPoint3;
    private String currentApiTrackingEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.bt_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(activity, SplashActivity.class);
                intent.putExtra(KEY_SDK_VERSION, false);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);*/

                if (currentPlayerId == null) {
                    currentPlayerId = UizaData.PLAYER_ID_SKIN_1;
                }
                if (currentApiEndPoint == null) {
                    currentApiEndPoint = Constants.URL_DEV_UIZA2;
                }
                if (currentApiTrackingEndPoint == null) {
                    currentApiTrackingEndPoint = Constants.URL_TRACKING_DEV;
                }

                LLog.d(TAG, "currentPlayerId " + currentPlayerId);
                LLog.d(TAG, "canSlide " + canSlide);
                LLog.d(TAG, "currentApiEndPoint " + currentApiEndPoint);
                LLog.d(TAG, "currentApiTrackingEndPoint " + currentApiTrackingEndPoint);
            }
        });
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(activity, SplashActivity.class);
                intent.putExtra(KEY_SDK_VERSION, true);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);*/
            }
        });

        setupSkin();
        setupSlide();
        setupApiEndpoint();
        setupApiTrackingEndpoint();
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.uiza_option_activity;
    }

    private void setupSkin() {
        //setting theme
        radioGroupSkin = (RadioGroup) findViewById(R.id.radio_group_skin);
        radioSkin1 = (RadioButton) findViewById(R.id.radio_skin_1);
        radioSkin2 = (RadioButton) findViewById(R.id.radio_skin_2);
        radioSkin3 = (RadioButton) findViewById(R.id.radio_skin_3);

        //default skin1
        radioSkin1.setChecked(true);

        radioGroupSkin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selectedId = radioGroupSkin.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radio_1:
                        //UizaData.getInstance().setPlayerId(UizaData.PLAYER_ID_SKIN_1);
                        currentPlayerId = UizaData.PLAYER_ID_SKIN_1;
                        break;
                    case R.id.radio_2:
                        currentPlayerId = UizaData.PLAYER_ID_SKIN_2;
                        break;
                    case R.id.radio_3:
                        currentPlayerId = UizaData.PLAYER_ID_SKIN_3;
                        break;
                }
            }
        });
    }

    private void setupSlide() {
        //setting slide
        radioGroupSlide = (RadioGroup) findViewById(R.id.radio_group_slide);
        radioCanSlide = (RadioButton) findViewById(R.id.radio_can_slide);
        radioCannotSlide = (RadioButton) findViewById(R.id.radio_cannot_slide);

        //default cannot slide
        radioCannotSlide.setChecked(true);

        radioGroupSlide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selectedId = radioGroupSlide.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radio_can_slide:
                        canSlide = true;
                        break;
                    case R.id.radio_cannot_slide:
                        canSlide = false;
                        break;
                }
            }
        });
    }

    private void setupApiEndpoint() {
        radioGroupApiEndPoint = (RadioGroup) findViewById(R.id.radio_api_end_point);
        radioApiEndPoint1 = (RadioButton) findViewById(R.id.radio_api_end_point_1);

        //default
        radioApiEndPoint1.setChecked(true);

        radioGroupApiEndPoint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selectedId = radioGroupApiEndPoint.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radio_api_end_point_1:
                        currentApiEndPoint = Constants.URL_DEV_UIZA2;
                        break;
                }
            }
        });
    }

    private void setupApiTrackingEndpoint() {
        radioGroupApiTrackingEndPoint = (RadioGroup) findViewById(R.id.radio_api_tracking_end_point);
        radioApiTrackingEndPoint1 = (RadioButton) findViewById(R.id.radio_api_tracking_end_point_1);
        radioApiTrackingEndPoint2 = (RadioButton) findViewById(R.id.radio_api_tracking_end_point_2);
        radioApiTrackingEndPoint3 = (RadioButton) findViewById(R.id.radio_api_tracking_end_point_3);

        //default
        radioApiTrackingEndPoint1.setChecked(true);

        radioGroupApiTrackingEndPoint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selectedId = radioGroupApiTrackingEndPoint.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radio_api_tracking_end_point_1:
                        currentApiTrackingEndPoint = Constants.URL_TRACKING_DEV;
                        break;
                    case R.id.radio_api_tracking_end_point_2:
                        currentApiTrackingEndPoint = Constants.URL_TRACKING_STAG;
                        break;
                    case R.id.radio_api_tracking_end_point_3:
                        currentApiTrackingEndPoint = Constants.URL_TRACKING_PROD;
                        break;
                }
            }
        });
    }
}
