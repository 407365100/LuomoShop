package com.luomo.shopping.db.dao;

import android.content.Context;

/**
 * 处理拼接sql字符串
 */
public class DaoUtils {
    /**
     * 清除数据表信息
     * @param context
     */
    public static void clearTables(Context context){
        KindDao.getInstance().deleteAll(context);
    }
}
