package com.luomo.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.luomo.shopping.Constant;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.adapter.KindAdapter;
import com.luomo.shopping.adapter.MeasuringUnitsAdapter;
import com.luomo.shopping.db.domain.GoodsDomain;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;
import com.luomo.shopping.db.domain.PriceDomain;
import com.luomo.shopping.presenter.GoodsAddPresenter;
import com.luomo.shopping.utils.StringUtils;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.view.IGoodsAddView;
import com.luomo.shopping.widget.dialog.CommonDialog;
import com.luomo.shopping.widget.dialog.ICustomDialog;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 添加商品
 *
 * @author :renpan
 * @version :v1.0
 * @date :2016-03-21 23:28
 */
public class GoodsManagerAddActivity extends BaseActivity implements IGoodsAddView {
    private MeasuringUnitsAdapter mMeasuringUnitsAdapter;
    /**
     * 分类,产品单位
     */
    private TextView mTvKind,mTvUnit;
    /**
     * 完成按钮
     */
    private TextView mTvRight;
    private GoodsDomain mGoodsDomain;
    private PriceDomain mPriceDomain;
    /**
     * mEtTwoCode 二维码输入框
     * mEtName 商品名输入框
     * mEtSpec 商品规格
     */
    private EditText mEtTwoCode,mEtName,mEtSpec;
    private GoodsAddPresenter mGoodsAddPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_manger_add);
        mGoodsAddPresenter = new GoodsAddPresenter(this);
        initViews();
    }

    @Override
    protected void initViews() {

        ((TextView) ViewUtils.f(this, R.id.tv_title)).setText("添加商品");
        mTvRight = ViewUtils.f(this, R.id.tv_right);
        mEtTwoCode = ViewUtils.f(this, R.id.et_two_code);
        mEtName = ViewUtils.f(this, R.id.et_name);
        mEtSpec = ViewUtils.f(this, R.id.et_spec);
        mTvKind = ViewUtils.f(this, R.id.tv_kind);
        mTvUnit = ViewUtils.f(this, R.id.tv_unit);

        mTvRight.setOnClickListener(this);
        ViewUtils.f(this, R.id.rl_kind).setOnClickListener(this);//点击分类
        ViewUtils.f(this, R.id.rl_unit).setOnClickListener(this);//点击计量单位
        ViewUtils.f(this, R.id.iv_two_code_one).setOnClickListener(this);//二维码
        initData();
    }

    @Override
    protected void initData() {
        mGoodsDomain = new GoodsDomain();
        mPriceDomain = new PriceDomain();
    }

    @Override
    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_two_code_one://二维码
                Intent intentToScanner = new Intent(mContext, ScannerCaptureActivity.class);
                intentToScanner.putExtra(Constant.ARGUMENT_ONE, 3);
                startActivityForResult(intentToScanner, Constant.FLAG_REQUEST_CODE_1000);
                break;
            case R.id.rl_kind://分类
                mGoodsAddPresenter.onClick(R.id.rl_kind);
                break;
            case R.id.rl_unit://计量单位
                mGoodsAddPresenter.onClick(R.id.rl_unit);
                break;
            case R.id.tv_right://完成按钮


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.FLAG_REQUEST_CODE_1000:
                if (requestCode == Constant.FLAG_RESULT_CODE_1000) {
                    mGoodsDomain.setTwoDimensionCode(data.getStringExtra(Constant.ARGUMENT_ONE));
                    mEtTwoCode.setText(data.getStringExtra(Constant.ARGUMENT_ONE));
                }
                break;
        }
    }

    @Override
    public JSONObject getParam(){
        if(StringUtils.isEmpty(mEtTwoCode.getText())){
            showToast(R.string.add_goods_two_code_error);
        }else if(StringUtils.isEmpty(mEtName.getText())){
            showToast(R.string.add_goods_name_error);
        }else if(StringUtils.isEmpty(mEtSpec.getText())){
            showToast(R.string.add_goods_spec_error);
        }
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
        ToastUtils.show(this, getString(textId));
    }

    @Override
    public void showDialogSelectedKinds(final List<KindDomain> parentList, final List<KindDomain> childrenList) {
        new CommonDialog(mContext, R.layout.dialog_kind_selected, new ICustomDialog() {
            @Override
            public void inflateViewAndData(CommonDialog dialog) {
                initKindsLv(dialog,parentList,childrenList);
            }
        }, 1.0f, 0.4f, Gravity.BOTTOM).show();
    }

    @Override
    public void showDialogSelectedUnits(final List<MeasuringUnitDomain> list) {
        new CommonDialog(mContext, R.layout.dialog_kind_selected, new ICustomDialog() {
            @Override
            public void inflateViewAndData(CommonDialog dialog) {
                initUnitsLv(dialog,list);
            }
        }, 1.0f, 0.4f, Gravity.BOTTOM).show();
    }

    /**
     * @param list 第一个list是父分类list，第二个list是子分类
     */
    private void initKindsLv(final CommonDialog dialog, final List<KindDomain>... list) {
        /**
         * 显示选择分类、计量单位的lv布局
         */
        ListView lvKindRight = (ListView) dialog.findViewById(R.id.lv_right);
        ListView lvKindLeft = (ListView) dialog.findViewById(R.id.lv_left);
        //右边布局
        final KindAdapter mKindAdapterRight = new KindAdapter(mContext, list[1], R.layout.dialog_goods_kind_selected_right_item);
        lvKindRight.setAdapter(mKindAdapterRight);
        lvKindRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGoodsDomain.setKindId(list[1].get(position).getId());
                mTvKind.setText(list[1].get(position).getName());
            }
        });
        //左边布局
        final KindAdapter mKindAdapterLeft = new KindAdapter(mContext, list[0], R.layout.dialog_goods_kind_selected_left_item);
        lvKindLeft.setAdapter(mKindAdapterLeft);
        lvKindLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                list[1].clear();
                list[1].addAll(mGoodsAddPresenter.loadChildrenKinds(list[0].get(position).getId()));
                mKindAdapterLeft.setSelectedPosition(position);
                mKindAdapterLeft.notifyDataSetChanged();
                mKindAdapterRight.notifyDataSetChanged();
            }
        });
    }

    /**
     * @param dialog
     * @param list 第一个list是父分类list，第二个list是子分类
     */
    private void initUnitsLv(final CommonDialog dialog, final List<MeasuringUnitDomain> list) {
        //右边布局
        mMeasuringUnitsAdapter = new MeasuringUnitsAdapter(mContext,list,R.layout.layout_goods_units_selected_item);
        ListView lvUnitContent = (ListView) dialog.findViewById(R.id.lv_content);
        lvUnitContent.setAdapter(mMeasuringUnitsAdapter);
        lvUnitContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                mTvUnit.setText(list.get(position).getName());
                mPriceDomain.setMeasuringUnitId(list.get(position).getId());
            }
        });
    }
}