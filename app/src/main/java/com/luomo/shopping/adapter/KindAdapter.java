package com.luomo.shopping.adapter;

import android.content.Context;
import com.luomo.shopping.R;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.domain.KindDomain;

import java.util.List;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-28 11:25
 */
public class KindAdapter extends CommonAdapter<KindDomain> {
    private int selectedPosition = 0;

    public KindAdapter(Context context, List<KindDomain> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, KindDomain item) {
        helper.getView(R.id.tv_content).setSelected(helper.getPosition() == selectedPosition);
        helper.setText(R.id.tv_content, item.getName());
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
