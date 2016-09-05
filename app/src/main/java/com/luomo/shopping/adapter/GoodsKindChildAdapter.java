package com.luomo.shopping.adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.luomo.shopping.Constant;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.GoodsKindAddActivity;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.dao.KindDao;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.widget.dialog.CommonDialog;
import com.luomo.shopping.widget.dialog.ICustomOnclickListener;

import java.io.Serializable;
import java.util.List;

/**
  * @author :renpan
  * @version :v1.0
  * @class :com.luomo.shopping.adapter.GoodsKindChildAdapter
  * @date :2016-03-25 17:18
  * @description:
  */
public class GoodsKindChildAdapter extends CommonAdapter<KindDomain> {

    public GoodsKindChildAdapter(Context context, List<KindDomain> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final KindDomain item) {
        helper.setText(R.id.tv_name, item.getName());
        //点击编辑按钮
        helper.getView(R.id.iv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsKindAddActivity.class);
                intent.putExtra("kind_item",item);
                intent.putExtra("operation_flag",Constant.FLAG_1002);//修改
                ((Activity)mContext).startActivityForResult(intent,Constant.FLAG_REQUEST_CODE_1001);
            }
        });
        //点击删除按钮
        helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new CommonDialog(mContext,"确定删除该分类").setTitle("提示").setOnClickListenerSubmit(new ICustomOnclickListener() {
                    @Override
                    public void onClick(CommonDialog dialog) {
                        //TODO:删除前判断是否有商品，待开发
                        KindDao.getInstance().delete(mContext,item.getId());
                        mDatas.remove(item);
                        GoodsKindChildAdapter.this.notifyDataSetChanged();
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
}
