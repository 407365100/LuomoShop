package com.luomo.shopping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import com.luomo.shopping.R;
import com.luomo.shopping.adapter.ManagementAdapter;
import com.luomo.shopping.db.domain.ManagementDomain;
import com.luomo.shopping.fragment.base.BaseFragment;
import com.luomo.shopping.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-15 15:32
 */
public class ManagementFragment extends BaseFragment {
    private TextView mTvTitle;
    private GridView mGvContent;//信息管理
    private ManagementAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = inflater.inflate(R.layout.fragment_management, container, false);
        initViews();
        return rootView;
    }

    @Override
    protected void initViews() {
        mTvTitle = ViewUtils.f(rootView, R.id.tv_title);
        mGvContent = ViewUtils.f(rootView, R.id.gv_content);


        mTvTitle.setOnClickListener(this);

        mTvTitle.setText("信息管理");
        initData();
    }

    @Override
    protected void initData() {
        List<ManagementDomain> list = new ArrayList<>();
        list.add(new ManagementDomain(R.drawable.management_goods,"商品管理"));
        list.add(new ManagementDomain(R.drawable.management_kind,"商品类别"));
        list.add(new ManagementDomain(R.drawable.management_unit,"计量单位"));
        list.add(new ManagementDomain(R.drawable.management_accounts,"赊账记录"));
        mAdapter = new ManagementAdapter(mContext,list,R.layout.fragment_management_item,mGvContent);
        mGvContent.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}