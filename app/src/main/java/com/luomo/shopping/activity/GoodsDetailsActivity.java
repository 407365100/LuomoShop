package com.luomo.shopping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.utils.ViewUtils;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-29 22:23
 */
public class GoodsDetailsActivity extends BaseActivity {

    private TextView mTvRight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_details);
        initViews();
    }

    @Override
    protected void initViews() {
        mTvRight = ViewUtils.f(this, R.id.tv_right);
        ((TextView) ViewUtils.f(this, R.id.tv_title)).setText("商品详情");

        mTvRight.setText("加入购物车");
        initData();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}