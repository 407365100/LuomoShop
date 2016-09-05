package com.luomo.shopping.db.dao;


import android.content.Context;
import android.database.Cursor;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;
import com.luomo.shopping.db.domain.MeasuringUnitDomain;
import com.luomo.shopping.db.helper.DBManager;
import com.luomo.shopping.db.helper.DBScript;
import com.luomo.shopping.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *计量单位
 */
public class MeasuringUnitDao extends BaseDao<MeasuringUnitDomain>{
    StringBuffer sqlOne = new StringBuffer();
    StringBuffer sqlTwo = new StringBuffer();
    private HashMap<String,String> domainReflectTable = new HashMap<>();//类变量与表字段的映射关系
    private final static MeasuringUnitDao dao = new MeasuringUnitDao();

    public static synchronized MeasuringUnitDao getInstance(){
        return dao;
    }

    private MeasuringUnitDao() {
        domainReflectTable.put(MeasuringUnitDomain.ID,DBScript.FIELD_MEASURING_UNIT_ID);
        domainReflectTable.put(MeasuringUnitDomain.NAME,DBScript.FIELD_MEASURING_UNIT_NAME);
    }
	@Override
	public void insert(Context context,MeasuringUnitDomain department) {
        sqlOne = new StringBuffer();
        sqlTwo = new StringBuffer();
		sqlOne.append("insert into " + DBScript.TABLE_MEASURING_UNIT_NAME + "(");
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
        LogUtils.i(TAG,"MeasuringUnitDao.insert:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
	}

    @Override
	public void update(Context context,MeasuringUnitDomain domain) {
        sqlOne= new StringBuffer();
        sqlTwo=new StringBuffer();
		sqlOne.append("update " + DBScript.TABLE_MEASURING_UNIT_NAME + " set ");
        sqlTwo.append(" where ");
		//反射拼接sql语句
		DBUtils.getBeanMapObject(domain, new DBUtils.ReflectListener() {
            /**
             * @param name
             * @param value 可以是数组等其他类型；但是必须对这些非string类型单独处理，否则会报转换异常
             * @param valType
             */
            @Override
            public void dealNameAndValue(String name, Object value, Class valType) {
                if (domainReflectTable.get(name).equals(DBScript.FIELD_MEASURING_UNIT_ID)) {
                    sqlTwo.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "' and ");
                    return;
                }
                if (valType == String.class) {
                    sqlOne.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "',");
                }
            }
        });
        LogUtils.i(TAG,"MeasuringUnitDao.update:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
	}

	@Override
	public void delete(Context context,String... st) {
        LogUtils.i(TAG,"MeasuringUnitDao.delete:"+"delete from "+DBScript.TABLE_MEASURING_UNIT_NAME +" where "+DBScript.FIELD_MEASURING_UNIT_ID+" ='"+st[0]+"'");
        DBManager.getInstance(context).executeSql("delete from "+DBScript.TABLE_MEASURING_UNIT_NAME +" where "+DBScript.FIELD_MEASURING_UNIT_ID+" ='"+st[0]+"'");
	}

	@Override
	public void deleteAll(Context context) {
        DBManager.getInstance(context).executeSql("delete from " + DBScript.TABLE_MEASURING_UNIT_NAME);
	}

	@Override
	public MeasuringUnitDomain one(Context context,String... st) {
        String sql = "select * from "+DBScript.TABLE_MEASURING_UNIT_NAME +" where "+DBScript.FIELD_MEASURING_UNIT_ID+" ='"+st[0]+"'" ;
        Cursor cursor = DBManager.getInstance(context).query(sql);
        MeasuringUnitDomain domain = null;
        if (cursor.moveToNext()) {
            domain = new MeasuringUnitDomain();
            domain.setId(st[0]);
            domain.setName(getString(cursor,DBScript.FIELD_MEASURING_UNIT_NAME));
        }
        closeCursor(cursor);
        return domain;
	}

	@Override
	public List<MeasuringUnitDomain> list(Context context,String... st) {
        List<MeasuringUnitDomain> list = new ArrayList<MeasuringUnitDomain>();
        String sql = "select * from "+DBScript.TABLE_MEASURING_UNIT_NAME;
        Cursor cursor = DBManager.getInstance(context).query(sql);
        MeasuringUnitDomain domain = null;
        while (cursor.moveToNext()) {
            domain = new MeasuringUnitDomain();
            domain.setId(getString(cursor,DBScript.FIELD_MEASURING_UNIT_ID));
            domain.setName(getString(cursor,DBScript.FIELD_MEASURING_UNIT_NAME));
            list.add(domain);
        }
        closeCursor(cursor);
        LogUtils.i(TAG,"MeasuringUnitDao.list:"+sql);
        return list;
	}
}