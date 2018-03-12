package vn.loitp.app.uiza.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etId;
    private EditText etPw;
    private TextView tvForgotPw;
    private TextView tvDontHaveAcc;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etId = (EditText) findViewById(R.id.et_id);
        etPw = (EditText) findViewById(R.id.et_pw);
        tvForgotPw = (TextView) findViewById(R.id.tv_forgot_pw);
        tvDontHaveAcc = (TextView) findViewById(R.id.tv_dont_have_acc);
        tvLogin = (TextView) findViewById(R.id.tv_login);

        tvForgotPw.setOnClickListener(this);
        tvDontHaveAcc.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

        LUIUtil.setTextViewUnderLine(tvForgotPw);
        LUIUtil.setTextViewUnderLine(tvDontHaveAcc);

        findViewById(R.id.iv_close_activity).setOnClickListener(this);
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
        return R.layout.uiza_login_activity;
    }

    private void login() {
        //TODO
        ToastUtils.showShort("Click");
        /*LSService service = RestClientV2.createService(LSService.class);
        String id = "fuck";
        String pw = "fuck";
        subscribe(service.login(id, pw), new ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object result) {
                LLog.d(TAG, "onSuccess " + LSApplication.getInstance().getGson().toJson(result));
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forgot_pw:
                ToastUtils.showShort("Click");
                break;
            case R.id.tv_dont_have_acc:
                ToastUtils.showShort("Click");
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.iv_close_activity:
                onBackPressed();
                break;
        }
    }
}
