package com.luomo.shopping.widget.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public abstract class DialogListViewAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected Context context;
    protected ListView listView;

    public DialogListViewAdapter(Context context, List<T> list,ListView listView) {
        this.context = context;
        this.list = list;
        this.listView = listView;
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DialogListViewAdapter.this.onItemClick(adapterView,view,position,id);
            }
        });
    }

    public DialogListViewAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }
    private void init() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    /**
     * 条目点击事件，让客户端去做
     * @param view
     * @param position
     */
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
