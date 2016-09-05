package com.luomo.shopping.fragment.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by renpan on 2015/11/20.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected View rootView;

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

	/**
      * 监听Back键按下事件
      */
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event){
        return false;
    }

}
