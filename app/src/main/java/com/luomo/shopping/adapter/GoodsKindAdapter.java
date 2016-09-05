package com.luomo.shopping.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import com.luomo.shopping.R;
import com.luomo.shopping.adapter.base.CommonAdapter;
import com.luomo.shopping.adapter.base.ViewHolder;
import com.luomo.shopping.db.dao.KindDao;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.utils.ToastUtils;
import com.luomo.shopping.widget.dialog.CommonDialog;
import com.luomo.shopping.widget.dialog.ICustomOnclickListener;

import java.util.List;

/**
  * @author :renpan
  * @version :v1.0
  * @class :com.luomo.shopping.adapter.GoodsKindAdapter
  * @date :2016-03-25 17:18
  * @description:
  */
public class GoodsKindAdapter extends CommonAdapter<KindDomain> {

    private List<KindDomain> childList;
    private ListView lvChildContent;
    private GoodsKindChildAdapter childAdapter;
    private boolean isDeletedModel = false;

    public GoodsKindAdapter(Context context, List<KindDomain> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(final ViewHolder helper, final KindDomain item) {
        helper.setText(R.id.tv_name, item.getName());
        //删除顶级分类模式
        if(isDeletedModel) {//是否在删除顶级分类模式下
            helper.getView(R.id.iv_delete).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (KindDao.getInstance().kindChildrenLength(mContext, item.getId()) > 0) {
                        ToastUtils.show(mContext,"不能删除，当前分类下有分类。请移除所有内容后，删除");
                    }else{
                        //弹出框
                        /*new CommonDialog(mContext,"确定删除该分类").setTitle("提示").setOnClickListenerSubmit(new ICustomOnclickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                KindDao.getInstance().delete(mContext,item.getId());
                                mDatas = KindDao.getInstance().list(mContext);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setOnClickListenerCancel(new ICustomOnclickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();*/
                    }
                }
            });
        }else{
            helper.getView(R.id.iv_delete).setVisibility(View.GONE);
        }
        //显示子分类
        helper.getView(R.id.rl_parent_kind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childList = KindDao.getInstance().list(mContext, item.getId());
                if (childList.size() > 0) {
                    lvChildContent = (ListView) helper.getView(R.id.lv_item_content);
                    if(lvChildContent.getVisibility() == View.GONE){//显示子分类
                        lvChildContent.setVisibility(View.VISIBLE);
                        childAdapter = new GoodsKindChildAdapter(mContext, childList, R.layout.layout_goods_kind_child_item);
                        lvChildContent.setAdapter(childAdapter);
                    }else{//隐藏子分类
                        lvChildContent.setVisibility(View.GONE);
                    }
                }
            }
        });
        //长按顶级分类，进入删除模式
        helper.getView(R.id.rl_parent_kind).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isDeletedModel()) {
                    notifyDeletedModelChanged(true);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * @param list
     */
    public void setList(List<KindDomain> list){
        this.mDatas = list;
    }
    public boolean isDeletedModel() {
        return isDeletedModel;
    }

    /**
     * 启动删除模式后更新界面
     * @param flag
     */
    public void notifyDeletedModelChanged(boolean flag) {
        isDeletedModel = flag;
        this.notifyDataSetChanged();
    }
}
