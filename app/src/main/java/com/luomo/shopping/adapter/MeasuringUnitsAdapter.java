package com.luomo.shopping.adapter;

import android.content.Context;
import com.luomo.shopping.R;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;

import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-28 11:25
 */
public class MeasuringUnitsAdapter extends CommonAdapter<MeasuringUnitDomain> {

    public MeasuringUnitsAdapter(Context context, List<MeasuringUnitDomain> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, MeasuringUnitDomain item) {
        helper.setText(R.id.tv_content, item.getName());
    }
}
