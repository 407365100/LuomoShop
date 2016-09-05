package com.luomo.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
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
public class ManagerGoodsAdapter extends CommonAdapter<GoodsDomain>{
    private ListView listView;
    public ManagerGoodsAdapter(Context context, List<GoodsDomain> list, int itemLayoutId, ListView listView) {
        super(context, list, itemLayoutId);
        this.listView = listView;
        initData();
    }

    private void initData() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mContext.startActivity(new Intent(mContext, GoodsDetailsActivity.class));
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, final GoodsDomain item) {

    }
}
