package com.luomo.shopping.db.dao;

import android.content.Context;
import android.database.Cursor;
import com.luomo.shopping.db.helper.DBScript;
import com.luomo.shopping.utils.StringUtils;

import java.util.List;

/**
 * Created by renpan on 2015-12-29.
 */
public abstract class BaseDao<T> {
    protected String TAG = getClass().getSimpleName();
    /**
     * 插入
     * @param t
     */
    public abstract void insert(Context context,T t);

    /**
     * 更新
     * @param t
     */
    public abstract void update(Context context,T t);

    /**
     * 删除
     * @param st
     */
    public abstract void delete(Context context,String... st);

    /**
     * 删除所有
     */
    public abstract void deleteAll(Context context);

    /**
     * 获取一个
     * @param st
     */
    public abstract T one(Context context,String... st);

    /**
     * 获取一个列表
     * @param st
     * @return
     */
    public abstract List<T> list(Context context,String... st);
    /**
     * 获取固定列名的值
     * @param cursor
     * @param columnName
     * @return
     */
    public synchronized static String getString(Cursor cursor,String columnName){
        if(cursor == null|| StringUtils.isEmpty(columnName)){
            return null;
        }
        String st=cursor.getString(cursor.getColumnIndex(columnName));
        return StringUtils.isEmpty(st)?null:st;
    }

    /**
     * 查询数据库后关闭cursor
     *
     * @param cursor
     * @return
     */
    protected boolean closeCursor(Cursor cursor) {
        try {
            if (null != cursor&&!cursor.isClosed()) {
                cursor.close();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
