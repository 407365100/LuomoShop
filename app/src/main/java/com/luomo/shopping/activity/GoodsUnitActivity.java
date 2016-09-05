package com.luomo.shopping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.luomo.shopping.Constant;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.dao.KindDao;
import com.luomo.shopping.db.dao.MeasuringUnitDao;
import com.luomo.shopping.db.domain.ManagementDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;
import com.luomo.shopping.utils.LogUtils;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.widget.dialog.CommonDialog;
import com.luomo.shopping.widget.dialog.ICustomDialog;
import com.luomo.shopping.widget.dialog.ICustomOnclickListener;

import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-25 12:37
 */
public class GoodsUnitActivity extends BaseActivity {

    private TextView mTvRight;
    private ListView mLvContent;//父级分类、子分类
    private CommonAdapter mAdapter;

    private List<MeasuringUnitDomain> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_unit);
        initViews();
    }

    @Override
    protected void initViews() {
        //标题栏
        ((TextView) ViewUtils.f(this, R.id.tv_title)).setText("计量单位管理");
        mTvRight = ViewUtils.f(this, R.id.tv_right);
        mTvRight.setText("添加");
        mTvRight.setOnClickListener(this);
        //显示顶级类别
        mLvContent = ViewUtils.f(this, R.id.lv_content);
        initData();
    }

    @Override
    protected void initData() {
        mList = MeasuringUnitDao.getInstance().list(mContext);
        mAdapter = new CommonAdapter<MeasuringUnitDomain>(mContext, mList, R.layout.layout_goods_unit_item) {
            @Override
            public void convert(ViewHolder helper, final MeasuringUnitDomain item) {
                helper.setText(R.id.tv_name, item.getName());
                //点击编辑按钮
                helper.getView(R.id.iv_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog("编辑单位", 2, mList.indexOf(item));
                    }
                });
                //点击删除按钮
                helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*new CommonDialog(mContext, "确定删除该单位").setTitle("提示").setOnClickListenerSubmit(new ICustomOnclickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                MeasuringUnitDao.getInstance().delete(mContext, item.getId());
                                mList.remove(item);
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setOnClickListenerCancel(new ICustomOnclickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();*/
                    }
                });
            }
        };

        mLvContent.setAdapter(mAdapter);
    }

    @Override
    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right://添加单位
                showDialog("添加单位", 1);
                break;
        }
    }

    /**
     * 添加、修改单位时弹出输入框
     *
     * @param title 弹出框的标题
     * @param value 添加时只传入常量1；修改时传入常量2、当前对象在list中的位置position
     */
    private void showDialog(final String title, final int... value) {
        /*new CommonDialog(mContext, R.layout.dialog_input_message, new ICustomDialog() {
            @Override
            public void inflateViewAndData(final CommonDialog dialog) {
                final EditText editText = (EditText) dialog.findViewById(R.id.et_content);
                if (value[0] == 2) {
                    editText.setText(mList.get(value[1]).getName());
                    editText.setSelection(mList.get(value[1]).getName().length());//输入框光标移动到最后一位
                }
                dialog.setTitle(title);
                //取消按钮
                dialog.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //确认按钮
                dialog.findViewById(R.id.bt_submit).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        LogUtils.i(TAG,"GoodsUnitActivity.onClick:" + TextUtils.isEmpty("  "));
                        if (TextUtils.isEmpty(editText.getText()) && TextUtils.isEmpty(editText.getText().toString().trim())) {
                            ToastUtils.show(mContext, "计量单位名称不能为空或空串");
                        } else {
                            MeasuringUnitDomain domain = new MeasuringUnitDomain();
                            domain.setName(editText.getText().toString());
                            if (value[0] == 1) {//添加单位
                                domain.setId(String.valueOf(System.currentTimeMillis()));
                                MeasuringUnitDao.getInstance().insert(mContext, domain);
                                mList.add(domain);
                            } else if (value[0] == 2) {
                                domain.setId(mList.get(value[1]).getId());
                                MeasuringUnitDao.getInstance().update(mContext, domain);
                                mList.get(value[1]).setName(domain.getName());
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });
            }
        }).show();*/
    }
}