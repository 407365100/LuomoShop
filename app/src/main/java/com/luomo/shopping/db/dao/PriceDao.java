package com.luomo.shopping.db.dao;


import android.content.Context;
import android.database.Cursor;
import com.luomo.shopping.db.domain.PriceDomain;
import com.luomo.shopping.db.helper.DBManager;
import com.luomo.shopping.db.helper.DBScript;
import com.luomo.shopping.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *种类信息
 */
public class PriceDao extends BaseDao<PriceDomain>{
    StringBuffer sqlOne = new StringBuffer();
    StringBuffer sqlTwo = new StringBuffer();
    private HashMap<String,String> domainReflectTable = new HashMap<>();//类变量与表字段的映射关系
    private final static PriceDao dao = new PriceDao();

    /**
     * @return
     */
    public static synchronized PriceDao getInstance(){
        return dao;
    }

    private PriceDao() {
        domainReflectTable.put(PriceDomain.ID,DBScript.FIELD_GOODS_PRICE_ID);
        domainReflectTable.put(PriceDomain.PRICE,DBScript.FIELD_GOODS_PRICE);
        domainReflectTable.put(PriceDomain.GOODS_ID,DBScript.FIELD_GOODS_PRICE_GOODS_ID);
        domainReflectTable.put(PriceDomain.MEASURING_UNIT_ID,DBScript.FIELD_GOODS_PRICE_MEASURING_UNIT_ID);
    }
	@Override
	public void insert(Context context,PriceDomain domain) {
        sqlOne = new StringBuffer();
        sqlTwo = new StringBuffer();
		sqlOne.append("insert into " + DBScript.TABLE_GOODS_PRICE_NAME + "(");
		sqlTwo.append(")values(");
				DBUtils.getBeanMapObject(domain, new DBUtils.ReflectListener() {
                    @Override
                    public void dealNameAndValue(String key, Object value, Class valClass) {
                        if (valClass == String.class) {
                            sqlOne.append(domainReflectTable.get(key) + ",");
                            sqlTwo.append("'" + String.valueOf(value) + "',");
                        }
                    }
                });
        LogUtils.i(TAG,"PriceDao.insert:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
	}

    @Override
	public void update(Context context,PriceDomain kind) {
        sqlOne= new StringBuffer();
        sqlTwo=new StringBuffer();
		sqlOne.append("update " + DBScript.TABLE_GOODS_PRICE_NAME + " set ");
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
                if (name.equals(PriceDomain.ID)) {
                    sqlTwo.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "' and ");
                    return;
                }
                if (valType == String.class) {
                    sqlOne.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "',");
                }
            }
        });
        LogUtils.i(TAG,"PriceDao.update:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
	}

    /**
     * @param context
     * @param st
     */
	@Override
	public void delete(Context context,String... st) {
        DBManager.getInstance(context).executeSql("delete from "+DBScript.TABLE_GOODS_PRICE_NAME +" where "+PriceDomain.ID +"='"+st[0]+"'");
	}

    /**
     * @param context
     */
	@Override
	public void deleteAll(Context context) {
        DBManager.getInstance(context).executeSql("delete from " + DBScript.TABLE_GOODS_PRICE_NAME);
	}

	@Override
	public PriceDomain one(Context context,String... st) {
        return null;
	}

    /**
     * 获取所有的部门信息
     * @param context
     * @param st 父类id
     * @return
     */
	@Override
	public List<PriceDomain> list(Context context,String... st) {
        List<PriceDomain> list = new ArrayList<PriceDomain>();
        String sql = "select * from "+DBScript.TABLE_GOODS_PRICE_NAME +" where "+DBScript.FIELD_GOODS_PRICE_GOODS_ID +"='"+st[0]+"'" ;
        LogUtils.i(TAG,"PriceDao.list:"+sql);
        Cursor cursor = DBManager.getInstance(context).query(sql);
        PriceDomain domain = null;
        while (cursor.moveToNext()) {
            domain = new PriceDomain();
            domain.setId(st[0]);
            domain.setMeasuringUnitId(getString(cursor,DBScript.FIELD_GOODS_PRICE_MEASURING_UNIT_ID));
            domain.setPrice(getString(cursor,DBScript.FIELD_GOODS_PRICE));
            domain.setGoodsId(getString(cursor,DBScript.FIELD_GOODS_PRICE_GOODS_ID));
            list.add(domain);
        }
        closeCursor(cursor);
        return list;
	}
}
