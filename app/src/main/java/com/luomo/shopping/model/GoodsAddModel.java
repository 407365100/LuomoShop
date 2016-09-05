package com.luomo.shopping.model;

import android.content.Context;
import com.luomo.shopping.db.dao.KindDao;
import com.luomo.shopping.db.dao.MeasuringUnitDao;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;
import com.luomo.shopping.model.interfac.IGoodsAddModel;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.model
 * @date :2016-06-01 10:29
 * @description:
 */
public class GoodsAddModel implements IGoodsAddModel{
    private Context mContext;

    public GoodsAddModel(Context context) {
        this.mContext = context;
    }

    @Override
    public List<KindDomain> loadFirstLevelKinds() {
        return KindDao.getInstance().list(mContext, KindDao.FLAG_TOP_KIND);
    }

    @Override
    public List<KindDomain> loadKinds(String id) {
        return KindDao.getInstance().list(mContext, id);
    }

    @Override
    public List<MeasuringUnitDomain> loadUnits() {
        return MeasuringUnitDao.getInstance().list(mContext);
    }
}
