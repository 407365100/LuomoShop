package com.luomo.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.presenter.LoginPresenter;
import com.luomo.shopping.utils.LogUtils;
import com.luomo.shopping.utils.StringUtils;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.view.ILoginView;
import com.luomo.shopping.widget.view.TextWatcherWrap;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements ILoginView {
    /**用户名输入框*/
    @BindView(R.id.et_account)
    private EditText mEtAccount;
    /**密码输入框*/
    @BindView(R.id.et_password)
    private EditText mEtPwd;
    /**用户名右侧删除按钮*/
    @BindView(R.id.iv_account_delete)
    private ImageView mIvAccountDelete;
    /**密码右侧删除按钮*/
    @BindView(R.id.iv_password_delete)
    private ImageView mIvPwdDelete;
    private JSONObject mParam;
    private LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setSystemBarColor(R.color.color_f7f7f7);
        setContentView(R.layout.layout_login);
        initViews();
    }

    @Override
    protected void initViews() {
        mLoginPresenter = new LoginPresenter(this);
        initData();
    }

    @Override
    protected void initData() {
        //输入框的监听
        mEtAccount.addTextChangedListener(new TextWatcherWrap() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                mIvAccountDelete.setVisibility(TextUtils.isEmpty(charSequence)?View.GONE:View.VISIBLE);
            }
        });
        mEtPwd.addTextChangedListener(new TextWatcherWrap() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                mIvPwdDelete.setVisibility(TextUtils.isEmpty(charSequence)?View.GONE:View.VISIBLE);
            }
        });
    }

    @Override
    @OnClick({R.id.tv_login, R.id.iv_account_delete, R.id.iv_password_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login://点击登陆
                mLoginPresenter.login();
                break;
            case R.id.iv_account_delete:
                mEtAccount.getText().clear();
                mIvAccountDelete.setVisibility(View.GONE);
                break;
            case R.id.iv_password_delete:
                mEtPwd.getText().clear();
                mIvAccountDelete.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void jump2HomePager() {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    @Override
    public JSONObject getParam(){
        mParam = new JSONObject();
        if (StringUtils.isEmpty(mEtAccount.getText())) {
            showToast(R.string.account_cannot_be_empty);
        }else if (StringUtils.isEmpty(mEtPwd.getText())) {
            showToast(R.string.password_cannot_be_empty);
        } else {
            try {
                mParam.put("name", mEtAccount.getText().toString().trim());
                mParam.put("password", mEtPwd.getText().toString().trim());
                return mParam;
            } catch (JSONException e) {
                LogUtils.e(TAG,"getParam()");
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showToast(int textId) {
        ToastUtils.show(this, getString(textId));
    }
}
