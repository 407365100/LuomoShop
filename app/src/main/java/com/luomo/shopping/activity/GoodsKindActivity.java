package com.luomo.shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.luomo.shopping.Constant;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.adapter.GoodsKindAdapter;
import com.luomo.shopping.db.dao.KindDao;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.utils.ViewUtils;

import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-25 12:37
 */
public class GoodsKindActivity extends BaseActivity {

    private TextView mTvRight;
    private ListView mLvParentContent;//父级分类、子分类
    private GoodsKindAdapter mParentAdapter;

    private List<KindDomain> mParentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_kind);
        initViews();
    }

    @Override
    protected void initViews() {
        //标题栏
        ((TextView) ViewUtils.f(this, R.id.tv_title)).setText("分类管理");
        mTvRight = ViewUtils.f(this, R.id.tv_right);
        mTvRight.setText("添加");
        mTvRight.setOnClickListener(this);
        //显示顶级类别
        mLvParentContent = ViewUtils.f(this, R.id.lv_content);
        initData();
    }

    @Override
    protected void initData() {
        mParentList = KindDao.getInstance().list(mContext);
        mParentAdapter = new GoodsKindAdapter(mContext, mParentList, R.layout.layout_goods_kind_parent_item);
        mLvParentContent.setAdapter(mParentAdapter);
    }

    @Override
    public void back(View view) {
        if (mParentAdapter.isDeletedModel()) {//删除模式
            mParentAdapter.notifyDeletedModelChanged(false);
            return;
        }
        finish();
    }

    /**
     * 监听Back键按下事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mParentAdapter.isDeletedModel()) {
            mParentAdapter.notifyDeletedModelChanged(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right://点击添加分类
                Intent intent = new Intent(mContext, GoodsKindAddActivity.class);
                intent.putExtra("operation_flag", Constant.FLAG_1001);//添加
                startActivityForResult(intent, Constant.FLAG_REQUEST_CODE_1000);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.FLAG_REQUEST_CODE_1000:
                mParentList = KindDao.getInstance().list(mContext);
                mParentAdapter.setList(mParentList);
                mParentAdapter.notifyDataSetChanged();
                break;
            case Constant.FLAG_REQUEST_CODE_1001:
                mLvParentContent.setAdapter(mParentAdapter);
                break;
        }
    }
}