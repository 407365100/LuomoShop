package com.luomo.shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.luomo.shopping.LuomoApplication;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.utils.ViewUtils;

/**
  * @author :renpan
  * @version :v1.0
  * @class :com.luomo.shopping.activity.SettingActivity
  * @date :2016-05-27 15:06
  * @description:设置
  */
public class SettingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_setting);
        initViews();
    }

    @Override
    protected void initViews() {
        ViewUtils.f(this,R.id.tv_exit).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void back(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit://退出
                startActivity(new Intent(mContext,LoginActivity.class));
                LuomoApplication.getInstance().exit();
                break;
        }
    }
}