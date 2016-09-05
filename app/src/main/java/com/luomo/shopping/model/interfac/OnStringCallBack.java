package com.luomo.shopping.model.interfac;

import org.json.JSONException;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.model.interfac
 * @date :2016-05-30 9:35
 * @description:
 */
public interface OnStringCallBack {
    /**
     * 访问网络出错
     * @param e
     */
    void onError(Exception e);

    /**
     * 访问网络成功
     * @param s
     */
    void onResponse(String s) throws JSONException;
}
