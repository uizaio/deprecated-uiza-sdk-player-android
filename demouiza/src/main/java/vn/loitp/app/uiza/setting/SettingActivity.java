package vn.loitp.app.uiza.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.uiza.player.ui.data.UizaData;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.uiza.R;

public class SettingActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radio1 = (RadioButton) findViewById(R.id.radio_1);
        radio2 = (RadioButton) findViewById(R.id.radio_2);
        radio3 = (RadioButton) findViewById(R.id.radio_3);

        String currentSkin = UizaData.getInstance().getPlayerId();
        switch (currentSkin) {
            case Constants.PLAYER_ID_SKIN_1:
                radio1.setChecked(true);
                break;
            case Constants.PLAYER_ID_SKIN_2:
                radio2.setChecked(true);
                break;
            case Constants.PLAYER_ID_SKIN_3:
                radio3.setChecked(true);
                break;
            default:
                radio1.setChecked(true);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                LLog.d(TAG, "selectedId " + selectedId);
                switch (selectedId) {
                    case R.id.radio_1:
                        UizaData.getInstance().setPlayerId(Constants.PLAYER_ID_SKIN_1);
                        break;
                    case R.id.radio_2:
                        UizaData.getInstance().setPlayerId(Constants.PLAYER_ID_SKIN_2);
                        break;
                    case R.id.radio_3:
                        UizaData.getInstance().setPlayerId(Constants.PLAYER_ID_SKIN_3);
                        break;
                    default:
                        UizaData.getInstance().setPlayerId(Constants.PLAYER_ID_SKIN_1);
                        break;
                }
            }
        });
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
        return R.layout.uiza_setting_activity;
    }
}
