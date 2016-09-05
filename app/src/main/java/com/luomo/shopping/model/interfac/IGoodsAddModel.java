package com.luomo.shopping.model.interfac;

import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.model.interfac
 * @date :2016-06-01 10:27
 * @description:
 */
public interface IGoodsAddModel {
    /**
     * 获取第一级的分类信息
     * @return
     */
    List<KindDomain> loadFirstLevelKinds();
    /**
     * 获取分类信息
     * @param id
     * @return
     */
    List<KindDomain> loadKinds(String id);
    /**
     * 获取计量单位信息
     * @return
     */
    List<MeasuringUnitDomain> loadUnits();
}
