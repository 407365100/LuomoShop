package com.luomo.shopping.activity.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.luomo.shopping.widget.systembar.SystemBarTintManager;
import com.luomo.shopping.widget.systembar.SystemBarWrap;

public abstract class BaseActivity extends Activity implements View.OnClickListener {
    protected String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected boolean isSubmited;//是否已经提交请求
    /**
     * 是否加载自定义的SystemBar，实现了splash页面加载的时候，全屏显示
     * true 不加载；false 加载
     */
    protected boolean noLoadSystemBar = false;
    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.luomo.shopping.utils.ActivityManager.getActivityManager().addActivity(this);//添加当前activity到activity管理列表
        if(!noLoadSystemBar) {//加载自定义的SystemBar
            mTintManager = new SystemBarWrap(this).initSystemBar();
        }
    }

    /**
     * 设置状态栏颜色
     * @param res
     */
    public void setSystemBarColor(int res){
        if(mTintManager != null) {
            mTintManager.setStatusBarTintResource(res);//通知栏所需颜色
        }
    }

    /**
     * 不加载systemBar
     */
    public void cancelSystemBar(){
        noLoadSystemBar = true;
    }

    @Override
    protected void onDestroy() {
        mTintManager = null;
        super.onDestroy();
    }

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 点击返回按钮
     * @param view
     */
    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
