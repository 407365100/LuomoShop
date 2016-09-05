package com.luomo.shopping.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.luomo.shopping.Constant;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.base.BaseActivity;
import com.luomo.shopping.db.dao.KindDao;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.utils.ViewUtils;
import com.luomo.shopping.widget.dialog.CommonDialog;
import com.luomo.shopping.widget.dialog.DialogListViewAdapter;
import com.luomo.shopping.widget.dialog.ICustomDialog;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.activity
 * @date :2016-03-25 12:49
 * @description:
 */
public class GoodsKindAddActivity extends BaseActivity {

    private TextView mTvRight;
    private CommonDialog mCbDialog;
    private EditText mEtKindName;
    private TextView mTvParentKindName;
    private DialogListViewAdapter mDialogAdapter;//父分类的adapter

    private List<KindDomain> mParentList;
    private KindDomain mKindDomain;
    private int mOperationFlag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_goods_kind_add);
        initViews();
    }

    @Override
    protected void initViews() {
        //标题栏
        ((TextView) ViewUtils.f(this, R.id.tv_title)).setText("分类添加");
        mTvRight = ViewUtils.f(this, R.id.tv_right);
        mTvRight.setText("完成");
        mTvRight.setOnClickListener(this);

        ViewUtils.f(this,R.id.rl_parent_kind).setOnClickListener(this);

        mEtKindName = ViewUtils.f(this, R.id.et_kind_name);
        mTvParentKindName = ViewUtils.f(this, R.id.tv_parent_kind);
        initData();
    }

    @Override
    protected void initData() {
        mOperationFlag = getIntent().getIntExtra("operation_flag",Constant.FLAG_1001);
        switch (mOperationFlag) {
            case Constant.FLAG_1001://添加分类
                mKindDomain = new KindDomain("000");
                break;
            case Constant.FLAG_1002://编辑分类
                mKindDomain = (KindDomain) getIntent().getSerializableExtra("kind_item");
                mEtKindName.setText(mKindDomain.getName());
                mEtKindName.setSelection(mKindDomain.getName().length());
                mTvParentKindName.setText(KindDao.getInstance().one(mContext,mKindDomain.getParentId()).getName());
                break;
        }
        mParentList = KindDao.getInstance().list(mContext);
        if(mParentList.size()>0){
            mParentList.add(0,new KindDomain("-1","无","000"));
        }
        final TextView tvParentKind = ViewUtils.f(this,R.id.tv_parent_kind);
        //初始化弹出框
        mCbDialog = new CommonDialog(mContext, R.layout.dialog_unit_selected, new ICustomDialog() {
            @Override
            public void inflateViewAndData(final CommonDialog dialog) {
                ListView listView = (ListView) dialog.findViewById(R.id.lv_content);
                mDialogAdapter = new DialogListViewAdapter<KindDomain>(mContext,mParentList,listView) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        convertView = View.inflate(context, R.layout.layout_goods_units_selected_item, null);
                        ((TextView) convertView.findViewById(R.id.tv_content)).setText(list.get(position).getName());
                        return convertView;
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvParentKind.setText(list.get(position).getName());
                        mKindDomain.setParentId(list.get(position).getId());
                        dialog.dismiss();
                    }
                };
                listView.setAdapter(mDialogAdapter);
            }
        });
    }

    @Override
    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right://点击提交
                if(TextUtils.isEmpty(mEtKindName.getText())||TextUtils.isEmpty(mEtKindName.getText().toString().trim())){
                    ToastUtils.show(mContext, "请输入类名");
                }else {//类名不为空
                    mKindDomain.setName(mEtKindName.getText().toString());
                    switch (mOperationFlag) {
                        case Constant.FLAG_1001://添加分类
                            mKindDomain.setId(String.valueOf(System.currentTimeMillis()));
                            //保存到数据库
                            KindDao.getInstance().insert(mContext, mKindDomain);
                            if (mKindDomain.getParentId().equals("000")) {
                                setResult(Constant.FLAG_RESULT_CODE_1000);
                            }
                            break;
                        case Constant.FLAG_1002://编辑分类
                            KindDao.getInstance().update(mContext,mKindDomain);//更新类名
                            setResult(Constant.FLAG_REQUEST_CODE_1001);
                            break;
                    }
                    finish();
                }
                break;
            case R.id.rl_parent_kind://点击父类
                if(mParentList.size()>0){//父类是有值的
                    mCbDialog.show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != mCbDialog&&mCbDialog.isShowing()){
            mCbDialog.dismiss();
        }
        ToastUtils.dismiss();
    }
}