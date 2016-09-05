package com.luomo.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.luomo.shopping.R;
import com.luomo.shopping.activity.GoodsKindActivity;
import com.luomo.shopping.activity.GoodsManagerActivity;
import com.luomo.shopping.activity.GoodsUnitActivity;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.domain.ManagementDomain;

import java.util.HashMap;
import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-17 10:00
 */
public class ManagementAdapter extends CommonAdapter<ManagementDomain>{
    private List<ManagementDomain> list;
    private GridView gridView;

    public ManagementAdapter(Context context, List<ManagementDomain> list,int resourceId, GridView gridView) {
        super(context,list,resourceId);
        this.list = list;
        this.gridView = gridView;
        initData();
    }

    private void initData() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0://商品管理
                        intent = new Intent(mContext,GoodsManagerActivity.class);
                        break;
                    case 1://类别
                        intent = new Intent(mContext,GoodsKindActivity.class);
                        break;
                    case 2://计量单位
                        intent = new Intent(mContext,GoodsUnitActivity.class);
                        break;
                }
                if(null != intent) {
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, ManagementDomain item) {
        helper.setImageResource(R.id.iv_image, item.getDrawableId());
        helper.setText(R.id.tv_name,item.getName());
    }
}
