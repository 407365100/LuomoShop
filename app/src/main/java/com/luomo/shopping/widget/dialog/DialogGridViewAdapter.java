package com.luomo.shopping.widget.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

public abstract class DialogGridViewAdapter extends BaseAdapter {
    private int allCount;
    private int selectedPosition;
    protected Context context;
    private GridView gridView;
    private CommonDialog dialog;

    public void setRelatedGridView(GridView gridView) {
        this.gridView = gridView;
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogGridViewAdapter.this.onItemClick(parent,view,position,id,dialog);
            }
        });
    }

    public void setRelatedDialog(CommonDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * @param context
     * @param count     星星数量
     * @param position 选中的位置
     */
    public DialogGridViewAdapter(Context context, int count, int position) {
        this.context = context;
        this.allCount = count;
        this.selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public int getCount() {
        return allCount;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    /**
     * 条目点击事件，让客户端去做
     * @param view
     * @param position
     */
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id,CommonDialog dialog);
}
