package com.luomo.shopping.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.SettingActivity;
import com.luomo.shopping.fragment.base.BaseFragment;
import com.luomo.shopping.utils.ViewUtils;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-15 15:32
 */
public class ProfileFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
        return rootView;
    }

    @Override
    protected void initViews() {
        ViewUtils.f(rootView,R.id.rl_setting).setOnClickListener(this);
        initData();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_setting://设置
                startActivity(new Intent(mContext,SettingActivity.class));
                break;
        }
    }
}