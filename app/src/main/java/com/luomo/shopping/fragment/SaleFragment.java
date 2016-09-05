package com.luomo.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.GoodsSearchActivity;
import com.luomo.shopping.adapter.CartGoodsAdapter;
import com.luomo.shopping.db.domain.GoodsDomain;
import com.luomo.shopping.fragment.base.BaseFragment;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.view.IFragmentSaleView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-15 15:30
 */
public class SaleFragment extends BaseFragment implements IFragmentSaleView {
    /**
     * mTvTitle 标题<br/>
     * mTvTitleRight 取消订单<br/>
     */
    private TextView mTvTitle, mTvTitleRight;
    /**
     * mIvSelect 全选按钮<br/>
     */
    private ImageView mIvSelect;
    /**
     * mLvCart 购物车信息<br/>
     */
    private ListView mLvCart;
    private Intent mIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = inflater.inflate(R.layout.fragment_sale, container, false);
        initViews();
        return rootView;
    }

    @Override
    protected void initViews() {
        mTvTitle = ViewUtils.f(rootView, R.id.tv_title);
        mTvTitleRight = ViewUtils.f(rootView, R.id.tv_right);
        mIvSelect = ViewUtils.f(rootView, R.id.iv_select);
        mLvCart = ViewUtils.f(rootView, R.id.lv_cart);

        ViewUtils.f(rootView, R.id.et_search).setOnClickListener(this);//点击搜索按钮
        ViewUtils.f(rootView, R.id.ll_select).setOnClickListener(this);//全选布局
        ViewUtils.f(rootView, R.id.tv_submit).setOnClickListener(this);//提交订单
        mTvTitleRight.setOnClickListener(this);
        initData();
    }

    @Override
    protected void initData() {
        mTvTitle.setText("销售");
        mTvTitleRight.setText("取消订单");
        mIntent = new Intent();
        //加载购物车中商品信息
        List<GoodsDomain> list = new ArrayList<GoodsDomain>();
        list.add(new GoodsDomain());
        list.add(new GoodsDomain());
        CartGoodsAdapter adapter = new CartGoodsAdapter(mContext, list, R.layout.fragment_sale_item, mLvCart);
        mLvCart.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.iv_two_code_one://二维码扫一件商品
                mIntent = new Intent(mContext, ScannerCaptureActivity.class);
                mIntent.putExtra(Constant.ARGUMENT_ONE, 1);
                startActivityForResult(mIntent, Constant.FLAG_1000);
                break;
            case R.id.iv_two_code_more://二维码连扫多件
                mIntent = new Intent(mContext, ScannerCaptureActivity.class);
                mIntent.putExtra(Constant.ARGUMENT_ONE,2);
                startActivityForResult(mIntent, Constant.FLAG_1000);
                break;*/
            case R.id.et_search://搜索商品
                mIntent.setClass(mContext, GoodsSearchActivity.class);
                break;
            case R.id.ll_select://全选布局
                mIvSelect.setSelected(!mIvSelect.isSelected());
                break;
            case R.id.tv_submit://提交订单
                ToastUtils.show(mContext, "敬请期待……");
                break;
            case R.id.tv_right://取消订单
                ToastUtils.show(mContext, "敬请期待……");
                break;
        }
    }

    @Override
    public JSONObject getParam(){
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showToast(int textId) {
        ToastUtils.show(mContext, getString(textId));
    }
}