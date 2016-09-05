package com.luomo.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.GoodsDetailsActivity;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.domain.GoodsDomain;
import com.luomo.shopping.utils.ToastUtils;

import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-17 10:00
 */
public class CartGoodsAdapter extends CommonAdapter<GoodsDomain>{
    private ListView listView;
    public CartGoodsAdapter(Context context, List<GoodsDomain> list, int itemLayoutId, ListView listView) {
        super(context, list, itemLayoutId);
        this.listView = listView;
        initData();
    }

    private void initData() {
       
    }

    @Override
    public void convert(ViewHolder helper, final GoodsDomain item) {
        final TextView tvNumber = helper.getView(R.id.tv_number);//商品数量条目
        //初始化数据
        tvNumber.setText(item.getBuyNumbers() + "");
        helper.getView(R.id.iv_select).setSelected(item.isSelected());//设置当前商品选中状态
        //选择按钮监听点击事件
        helper.getView(R.id.ll_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelected(!item.isSelected());
                notifyDataSetChanged();
            }
        });
        //商品名称点击事件
        helper.getView(R.id.tv_goods_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, GoodsDetailsActivity.class));
            }
        });
        //删除的监听
        helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = mDatas.indexOf(item);
                mDatas.remove(item);
                notifyDataSetChanged();
            }
        });
        //减号监听
        helper.getView(R.id.tv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tvNumber.getText()) && TextUtils.isDigitsOnly(tvNumber.getText())) {//不为空
                    int number = Integer.parseInt(tvNumber.getText().toString());
                    if (number > 1) {
                        item.setBuyNumbers(--number);
                        tvNumber.setText(item.getBuyNumbers() + "");
                    }
                }
            }
        });
        //加号监听
        helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(tvNumber.getText())&&TextUtils.isDigitsOnly(tvNumber.getText())){//不为空
                    item.setBuyNumbers(Integer.parseInt(tvNumber.getText().toString())+1);
                    tvNumber.setText(item.getBuyNumbers()+"");
                }
            }
        });
    }
}
