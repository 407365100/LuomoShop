package com.luomo.shopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.luomo.shopping.activity.LoginActivity;
import com.luomo.shopping.activity.MainActivity;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.utils.AnimationUtils;
import com.luomo.shopping.utils.SharedPreferencesWrap;

public class SplashActivity extends BaseActivity {

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        cancelSystemBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        mContext = this;
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        initViews();
    }

    @Override
    protected void initViews() {
        AnimationUtils.startAlphaAnimation(findViewById(R.id.iv_bg), 0.3f, 1.0f);
        initData();
    }


    @Override
    protected void initData() {
        //等待2s后跳转到主界面
        new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                //是否有保存登陆状态
                startActivity(new Intent(mContext, SharedPreferencesWrap.getInstance(mContext).getBoolean(Constant.LOGIN_STATUS,false)?MainActivity.class:LoginActivity.class));
                finish();
            }
        }.execute();
    }
}