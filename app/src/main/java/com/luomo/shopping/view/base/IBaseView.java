package com.luomo.shopping.view.base;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.view.base
 * @date :2016-05-27 15:25
 * @description:mvp框架中的v
 */
public interface IBaseView {

    /**
     * 获取请求的参数
     * @return json格式的
     */
    JSONObject getParam();

    /**
     * 显示加载进度框
     */
    void showProgress();

    /**
     * 隐藏加载进度框
     */
    void dismissProgress();

    /**
     * 显示提示信息
     */
    void showToast(int textId);
}
