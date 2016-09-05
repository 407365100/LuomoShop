package com.luomo.shopping.model;

import android.content.Context;
import com.luomo.shopping.model.interfac.ILoginModel;
import com.luomo.shopping.model.interfac.OnStringCallBack;
import com.luomo.shopping.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.model
 * @date :2016-05-27 16:35
 * @description:
 */
public class LoginModel implements ILoginModel{
    private Context mContext;

    public LoginModel(Context context) {
        this.mContext = context;
    }

    @Override
    public void login(JSONObject jsonObject, String url, OnStringCallBack callBack){
        try {
            callBack.onResponse("{'flag',success}");
        } catch (JSONException e) {
            LogUtils.e("LoginModel.login");
            e.printStackTrace();
        }
    }
}
