package com.luomo.shopping.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.adapter.GoodsSelectedAdapter;
import com.luomo.shopping.db.domain.GoodsDomain;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.widget.pulltorefresh.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-18 12:34
 */
public class GoodsSelectedActivity extends BaseActivity {
    private ListView mLvContent;
    private GoodsSelectedAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_selected);
        initViews();
    }

    @Override
    protected void initViews() {
        mLvContent = ViewUtils.f(this,R.id.lv_content);
        initData();
    }

    @Override
    protected void initData() {
        final List<GoodsDomain> list = new ArrayList<>();
        GoodsDomain domain1 = new GoodsDomain();
        domain1.setId("1");
        GoodsDomain domain2 = new GoodsDomain();
        domain2.setId("2");
        GoodsDomain domain3 = new GoodsDomain();
        domain3.setId("3");
        list.add(domain1);
        list.add(domain2);
        list.add(domain3);
        mAdapter = new GoodsSelectedAdapter(mContext,list,R.layout.layout_goods_selected_item);
        mLvContent.setAdapter(mAdapter);
        mLvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(mContext,list.get(position).getId());
                finish();
            }
        });
    }
    @Override
    public void back(View view) {

    }

    @Override
    public void onClick(View v) {

    }
}