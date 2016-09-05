package com.luomo.shopping.presenter;

import com.luomo.shopping.Constant;
import com.luomo.shopping.LuomoApplication;
import com.luomo.shopping.R;
import com.luomo.shopping.model.LoginModel;
import com.luomo.shopping.model.interfac.OnStringCallBack;
import com.luomo.shopping.presenter.interfac.ILoginPresenter;
import com.luomo.shopping.utils.LogUtils;
import com.luomo.shopping.utils.SharedPreferencesWrap;
import com.luomo.shopping.view.ILoginView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.presenter
 * @date :2016-05-27 16:16
 * @description:
 */
public class LoginPresenter implements ILoginPresenter {
    String TAG = getClass().getSimpleName();
    private final LoginModel mLoginMode;
    /**
     * 登陆的view
     */
    private ILoginView loginView;

    public LoginPresenter(ILoginView view) {
        this.loginView = view;//view层的关联
        mLoginMode = new LoginModel(LuomoApplication.getInstance().getApplicationContext());//model层的关联
    }

    /**
     * 登陆的方法
     */
    @Override
    public void login() {
        loginView.showProgress();
        JSONObject param = loginView.getParam();
        LogUtils.i(TAG, param.toString());
        mLoginMode.login(param, "", new OnStringCallBack() {
            @Override
            public void onError(Exception e) {
                loginView.dismissProgress();
            }

            @Override
            public void onResponse(String s) throws JSONException {
                loginView.dismissProgress();
                JSONObject jsonObject = new JSONObject(s);
                if ("success".equals(jsonObject)) {//登陆成功
                    responseSuccess();
                } else {
                    loginView.showToast(R.string.account_or_password_error);
                }
            }
        });
    }

    /**
     * 登陆成功
     */
    private void responseSuccess() {
        SharedPreferencesWrap.getInstance(LuomoApplication.getInstance()).setBoolean(Constant.LOGIN_STATUS, true);//登陆状态
        SharedPreferencesWrap.getInstance(LuomoApplication.getInstance()).setString(Constant.LOGIN_ACCOUNT, "admin");//用户名
        SharedPreferencesWrap.getInstance(LuomoApplication.getInstance()).setString(Constant.LOGIN_PASSWORD, "123456");//密码
        loginView.jump2HomePager();//登陆成功跳转到其他界面
    }
}
