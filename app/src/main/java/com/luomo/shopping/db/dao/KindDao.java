package com.luomo.shopping.db.dao;


import android.content.Context;
import android.database.Cursor;
import com.luomo.shopping.db.domain.KindDomain;
import com.luomo.shopping.db.helper.DBManager;
import com.luomo.shopping.db.helper.DBScript;
import com.luomo.shopping.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *种类信息
 */
public class KindDao extends BaseDao<KindDomain>{
    public static final String FLAG_TOP_KIND ="000";//顶级父类
    StringBuffer sqlOne = new StringBuffer();
    StringBuffer sqlTwo = new StringBuffer();
    private HashMap<String,String> domainReflectTable = new HashMap<>();//类变量与表字段的映射关系
    private final static KindDao dao = new KindDao();

    /**
     * @return
     */
    public static synchronized KindDao getInstance(){
        return dao;
    }

    private KindDao() {
        domainReflectTable.put(KindDomain.ID,DBScript.FIELD_GOODS_KIND_ID);
        domainReflectTable.put(KindDomain.NAME,DBScript.FIELD_GOODS_KIND_NAME);
        domainReflectTable.put(KindDomain.PARENT_ID,DBScript.FIELD_GOODS_KIND_PARENT_ID);
    }
	@Override
	public void insert(Context context,KindDomain department) {
        sqlOne = new StringBuffer();
        sqlTwo = new StringBuffer();
		sqlOne.append("insert into " + DBScript.TABLE_GOODS_KIND_NAME + "(");
		sqlTwo.append(")values(");
				DBUtils.getBeanMapObject(department, new DBUtils.ReflectListener() {
                    @Override
                    public void dealNameAndValue(String key, Object value, Class valClass) {
                        if (valClass == String.class) {
                            sqlOne.append(domainReflectTable.get(key) + ",");
                            sqlTwo.append("'" + String.valueOf(value) + "',");
                        }
                    }
                });
        LogUtils.i(TAG,"KindDao.insert:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
	}

    @Override
	public void update(Context context,KindDomain kind) {
        sqlOne= new StringBuffer();
        sqlTwo=new StringBuffer();
		sqlOne.append("update " + DBScript.TABLE_GOODS_KIND_NAME + " set ");
        sqlTwo.append(" where ");
		//反射拼接sql语句
		DBUtils.getBeanMapObject(kind, new DBUtils.ReflectListener() {
            /**
             * @param name
             * @param value 可以是数组等其他类型；但是必须对这些非string类型单独处理，否则会报转换异常
             * @param valType
             */
            @Override
            public void dealNameAndValue(String name, Object value, Class valType) {
                if (name.equals(KindDomain.ID)) {
                    sqlTwo.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "' and ");
                    return;
                }
                if (valType == String.class) {
                    sqlOne.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "',");
                }
            }
        });
        LogUtils.i(TAG,"KindDao.update:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
	}

    /**
     * @param context
     * @param st
     */
	@Override
	public void delete(Context context,String... st) {
        DBManager.getInstance(context).executeSql("delete from "+DBScript.TABLE_GOODS_KIND_NAME +" where "+KindDomain.ID +"='"+st[0]+"'");
	}

    /**
     * @param context
     */
	@Override
	public void deleteAll(Context context) {
        DBManager.getInstance(context).executeSql("delete from " + DBScript.TABLE_GOODS_KIND_NAME);
	}

    /**
     * @param context
     */
	public int kindChildrenLength(Context context,String... st) {
        String sql = "select count(1) from "+DBScript.TABLE_GOODS_KIND_NAME +" where "+DBScript.FIELD_GOODS_KIND_PARENT_ID +"='"+st[0]+"'" ;
        Cursor cursor = DBManager.getInstance(context).query(sql);
        //LogUtils.i(TAG,"KindDao.kindChildrenLength:"+(cursor.moveToNext()?cursor.getInt(0):0));
        return cursor.moveToNext()?cursor.getInt(0):0;
	}

	@Override
	public KindDomain one(Context context,String... st) {
        String sql = "select * from "+DBScript.TABLE_GOODS_KIND_NAME +" where "+DBScript.FIELD_GOODS_KIND_ID +"='"+st[0]+"'" ;
        Cursor cursor = DBManager.getInstance(context).query(sql);
        KindDomain domain = null;
        if (cursor.moveToNext()) {
            domain = new KindDomain();
            domain.setId(st[0]);
            domain.setParentId(getString(cursor,DBScript.FIELD_GOODS_KIND_PARENT_ID));
            domain.setName(getString(cursor,DBScript.FIELD_GOODS_KIND_NAME));
        }
        closeCursor(cursor);
        return domain;
	}

    /**
     * 获取top kind
     * @param context
     * @return
     */
	public List<KindDomain> list(Context context) {
        return list(context, FLAG_TOP_KIND);
	}

    /**
     * 获取所有的部门信息
     * @param context
     * @param st 父类id
     * @return
     */
	@Override
	public List<KindDomain> list(Context context,String... st) {
        List<KindDomain> list = new ArrayList<KindDomain>();
        String sql = "select * from "+DBScript.TABLE_GOODS_KIND_NAME +" where "+DBScript.FIELD_GOODS_KIND_PARENT_ID +"='"+st[0]+"' order by "+DBScript.FIELD_GOODS_KIND_ID ;
        LogUtils.i(TAG,"KindDao.list:"+sql);
        Cursor cursor = DBManager.getInstance(context).query(sql);
        KindDomain domain = null;
        while (cursor.moveToNext()) {
            domain = new KindDomain();
            domain.setId(getString(cursor,DBScript.FIELD_GOODS_KIND_ID));
            domain.setParentId(st[0]);
            domain.setName(getString(cursor,DBScript.FIELD_GOODS_KIND_NAME));
            list.add(domain);
        }
        closeCursor(cursor);
        return list;
	}
}
