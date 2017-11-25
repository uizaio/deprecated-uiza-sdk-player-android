package vn.loitp.app.uiza.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.exoplayer2.ui.data.UizaData;

import vn.loitp.app.base.BaseActivity;
import vn.loitp.livestar.R;

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

        int currentSkin = UizaData.getInstance().getSkinNo();
        switch (currentSkin) {
            case UizaData.SKIN_1:
                radio1.setChecked(true);
                break;
            case UizaData.SKIN_2:
                radio2.setChecked(true);
                break;
            case UizaData.SKIN_3:
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
                Log.d(TAG, "selectedId " + selectedId);
                switch (selectedId) {
                    case R.id.radio_1:
                        UizaData.getInstance().setSkinNo(UizaData.SKIN_1);
                        break;
                    case R.id.radio_2:
                        UizaData.getInstance().setSkinNo(UizaData.SKIN_2);
                        break;
                    case R.id.radio_3:
                        UizaData.getInstance().setSkinNo(UizaData.SKIN_3);
                        break;
                    default:
                        UizaData.getInstance().setSkinNo(UizaData.SKIN_1);
                        break;
                }
            }
        });
    }

    @Override
    protected boolean setFullScreen() {
        return true;
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
