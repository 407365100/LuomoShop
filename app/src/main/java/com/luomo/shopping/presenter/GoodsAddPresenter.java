package com.luomo.shopping.presenter;

import com.luomo.shopping.LuomoApplication;
import com.luomo.shopping.R;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.model.GoodsAddModel;
import com.luomo.shopping.model.interfac.IGoodsAddModel;
import com.luomo.shopping.presenter.base.BasePresenter;
import com.luomo.shopping.presenter.interfac.IGoodsAddPresenter;
import com.luomo.shopping.view.IGoodsAddView;

import java.util.List;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.presenter
 * @date :2016-05-31 16:30
 * @description:
 */
public class GoodsAddPresenter extends BasePresenter implements IGoodsAddPresenter{
    private IGoodsAddView mGoodsAddView;
    private IGoodsAddModel mGoodsAddModel;

    public GoodsAddPresenter(IGoodsAddView goodsAddView) {
        this.mGoodsAddView = goodsAddView;
        mGoodsAddModel = new GoodsAddModel(mContext);
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.rl_kind://分类
                List<KindDomain> parentList = mGoodsAddModel.loadFirstLevelKinds();
                List<KindDomain> childrenList = mGoodsAddModel.loadKinds(parentList.get(0).getId());
                mGoodsAddView.showDialogSelectedKinds(parentList, childrenList);
                break;
            case R.id.rl_unit://计量单位
                mGoodsAddView.showDialogSelectedUnits(mGoodsAddModel.loadUnits());
                break;
        }
    }

    @Override
    public List<KindDomain> loadChildrenKinds(String parentId) {
        return mGoodsAddModel.loadKinds(parentId);
    }
}
