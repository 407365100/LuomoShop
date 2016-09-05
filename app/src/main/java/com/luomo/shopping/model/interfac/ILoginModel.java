package com.luomo.shopping.model.interfac;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.model
 * @date :2016-05-27 16:34
 * @description:
 */
public interface ILoginModel {

    /**
     * 登陆
     */
    void login(JSONObject jsonObject, String url, OnStringCallBack callBack);
}
