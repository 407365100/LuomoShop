package com.luomo.shopping.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.luomo.shopping.R;
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
public class GoodsSelectedAdapter extends CommonAdapter<GoodsDomain>{
    public GoodsSelectedAdapter(Context context, List<GoodsDomain> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final GoodsDomain item) {

    }
}
