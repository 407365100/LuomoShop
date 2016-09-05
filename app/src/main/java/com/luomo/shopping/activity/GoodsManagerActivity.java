package com.luomo.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.adapter.ManagerGoodsAdapter;
import com.luomo.shopping.adapter.PopupWindowAdapter;
import com.luomo.shopping.db.domain.GoodsDomain;
import com.luomo.shopping.utils.PopupWindowUtils;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-25 12:37
 */
public class GoodsManagerActivity extends BaseActivity {

    private PopupWindow mPopupWindow;
    private ImageView mIvKind;//类别的箭头
    private ListView mLvGoods;
    private TextView mTvAddGoods;//新增商品

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_manager);
        initViews();
    }

    @Override
    protected void initViews() {
        ((TextView) ViewUtils.f(this, R.id.tv_title)).setText("商品管理");

        mTvAddGoods = ViewUtils.f(this, R.id.tv_right);
        mIvKind = ViewUtils.f(this, R.id.iv_kind);
        mLvGoods = ViewUtils.f(this, R.id.lv_goods);

        mTvAddGoods.setOnClickListener(this);
        ViewUtils.f(this, R.id.ll_kind).setOnClickListener(this);
        initData();
    }

    @Override
    protected void initData() {
        mTvAddGoods.setText("添加商品");
        List<GoodsDomain> list = new ArrayList<GoodsDomain>();
        list.add(new GoodsDomain());
        list.add(new GoodsDomain());
        ManagerGoodsAdapter adapter = new ManagerGoodsAdapter(mContext, list, R.layout.layout_goods_manager_item, mLvGoods);
        mLvGoods.setAdapter(adapter);
    }

    @Override
    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_kind://点击分类按钮
                loadPopupWindow();
                break;
            case R.id.tv_right://点击添加商品
                startActivity(new Intent(mContext,GoodsManagerAddActivity.class));
                break;
        }
    }

    /**
     * 加载分类的popupWindow
     */
    private void loadPopupWindow() {
        if(null != mPopupWindow && mPopupWindow.isShowing()){//如果正在显示则取消
            mPopupWindow.dismiss();
            return;
        }
        mIvKind.setBackgroundResource(R.drawable.goods_manager_up);
        mPopupWindow = PopupWindowUtils.newInstance(mContext, new PopupWindowUtils.OnLoadPopupWindowView() {
            @Override
            public void initWindowView(Context context, View view) {
                List<String> list = new ArrayList<>();
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                list.add("分类1");
                list.add("分类2");
                list.add("分类3");
                list.add("分类4");
                list.add("分类5");
                ListView listView = (ListView) view.findViewById(R.id.lv_content);
                PopupWindowAdapter adapter = new PopupWindowAdapter(context, list, R.layout.popupwindow_two_list_item);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PopupWindowUtils.dismiss();
                        ToastUtils.show(mContext, position + "");
                    }
                });
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mIvKind.setBackgroundResource(R.drawable.goods_manager_down);
            }
        });
        mPopupWindow.showAsDropDown(ViewUtils.f(this, R.id.v_bottom));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != mPopupWindow && mPopupWindow.isShowing()){//如果正在显示则取消
            mPopupWindow.dismiss();
        }
    }
}