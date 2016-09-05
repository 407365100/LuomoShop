package com.luomo.shopping.presenter.interfac;

import com.luomo.shopping.db.domain.KindDomain;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.presenter.interfac
 * @date :2016-05-31 16:27
 * @description:
 */
public interface IGoodsAddPresenter {
    /**
     * 处理view的部分点击事件
     */
    void onClick(int viewId);

    /**
     * 获取子分类集合
     * @param parentId
     * @return
     */
    List<KindDomain> loadChildrenKinds(String parentId);
}
