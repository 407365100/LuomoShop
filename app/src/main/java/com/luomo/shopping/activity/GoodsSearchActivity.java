package com.luomo.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.widget.pulltorefresh.XListView;
import com.luomo.shopping.widget.view.TextWatcherWrap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
  * @author :renpan
  * @version :v1.0
  * @class :GoodsSearchActivity
  * @date :2016-09-01 15:36
  * @description:搜索商品界面
  */
public class GoodsSearchActivity extends BaseActivity implements XListView.IXListViewListener {

    /**
     * 搜索输入框
     */
    private EditText mEtSearchContent;
    /**
     * 搜索框右侧删除按钮
     */
    private ImageView mIvSearchContentDelete;
    /**
     * 搜索按钮
     */
    private TextView mTvSearch;
    private XListView mListView;

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler mHandler;
    private int mIndex = 0;
    private int mRefreshIndex = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search_result);
        initViews();
        geneItems();
    }

    private void geneItems() {
        for (int i = 0; i != 3; ++i) {
            items.add("Test XListView item " + (++mIndex));
        }
    }

    @Override
    protected void initViews() {
        mHandler = new Handler();
        mEtSearchContent = ViewUtils.f(this, R.id.et_search_content);
        mIvSearchContentDelete = ViewUtils.f(this, R.id.iv_search_content_delete);
        mTvSearch = ViewUtils.f(this, R.id.tv_search);

        mListView = (XListView) findViewById(R.id.list_view);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());

        mIvSearchContentDelete.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        //搜索框的监听
        mEtSearchContent.addTextChangedListener(new TextWatcherWrap() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (TextUtils.isEmpty(charSequence)) {//为空
                    mTvSearch.setVisibility(View.GONE);
                    mIvSearchContentDelete.setVisibility(View.GONE);
                } else {
                    mTvSearch.setVisibility(View.VISIBLE);
                    mIvSearchContentDelete.setVisibility(View.VISIBLE);
                }
            }
        });

        mAdapter = new ArrayAdapter<String>(this, R.layout.pull_refresh_list_item, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            mListView.autoRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIndex = ++mRefreshIndex;
                items.clear();
                geneItems();
                mAdapter = new ArrayAdapter<String>(GoodsSearchActivity.this, R.layout.pull_refresh_list_item, items);
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2500);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2500);
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    @Override
    public void back(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search://搜索

                break;
            case R.id.iv_search_content_delete://删除搜索框中内容
                mEtSearchContent.getText().clear();
                break;
        }



    }

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GoodsSearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        context.startActivity(intent);
    }
}