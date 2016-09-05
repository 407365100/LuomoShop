package com.luomo.shopping.view;

import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;
import com.luomo.shopping.view.base.IBaseView;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.view
 * @date :2016-05-31 16:15
 * @description:
 */
public interface IGoodsAddView extends IBaseView{
    /**
     * 加载类别ui
     * @param parentList
     * @param childrenList
     */
    void showDialogSelectedKinds(List<KindDomain> parentList, List<KindDomain> childrenList);
    /**
     * 加载计量单位ui
     * @param list
     */
    void showDialogSelectedUnits(List<MeasuringUnitDomain> list);
}
