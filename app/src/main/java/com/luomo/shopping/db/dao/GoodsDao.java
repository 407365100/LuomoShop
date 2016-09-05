package com.luomo.shopping.db.dao;


import android.content.Context;
import android.database.Cursor;
import com.luomo.shopping.db.domain.GoodsDomain;
import com.luomo.shopping.db.helper.DBManager;
import com.luomo.shopping.db.helper.DBScript;
import com.luomo.shopping.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *种类信息
 */
public class GoodsDao extends BaseDao<GoodsDomain>{
    StringBuffer sqlOne = new StringBuffer();
    StringBuffer sqlTwo = new StringBuffer();
    private HashMap<String,String> domainReflectTable = new HashMap<>();//类变量与表字段的映射关系
    private final static GoodsDao dao = new GoodsDao();

    /**
     * @return
     */
    public static synchronized GoodsDao getInstance(){
        return dao;
    }

    private GoodsDao() {
        domainReflectTable.put("id",DBScript.FIELD_GOODS_ID);
        domainReflectTable.put("twoDimensionCode",DBScript.FIELD_GOODS_TWO_DIMENSION_CODE);
        domainReflectTable.put("name",DBScript.FIELD_GOODS_NAME);
        domainReflectTable.put("memo",DBScript.FIELD_GOODS_MEMO);
        domainReflectTable.put("specification",DBScript.FIELD_GOODS_SPECIFICATION);
        domainReflectTable.put("imagePath",DBScript.FIELD_GOODS_IMAGE_PATH);
        domainReflectTable.put("kindId",DBScript.FIELD_GOODS_GOODS_KIND_ID);
    }
	@Override
	public void insert(Context context,GoodsDomain department) {
        sqlOne = new StringBuffer();
        sqlTwo = new StringBuffer();
		sqlOne.append("insert into " + DBScript.TABLE_GOODS_NAME + "(");
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
        LogUtils.i(TAG,"GoodsDao.insert:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf(","))).concat(")"));
	}

    @Override
	public void update(Context context,GoodsDomain kind) {
        sqlOne= new StringBuffer();
        sqlTwo=new StringBuffer();
		sqlOne.append("update " + DBScript.TABLE_GOODS_NAME + " set ");
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
                if (name.equals("id")) {
                    sqlTwo.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "' and ");
                    return;
                }
                if (valType == String.class) {
                    sqlOne.append(domainReflectTable.get(name) + "='" + String.valueOf(value) + "',");
                }
            }
        });
        LogUtils.i(TAG,"GoodsDao.update:"+sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
        DBManager.getInstance(context).executeSql(sqlOne.substring(0, sqlOne.lastIndexOf(",")).concat(sqlTwo.substring(0, sqlTwo.lastIndexOf("and"))));
	}

    /**
     * @param context
     * @param st
     */
	@Override
	public void delete(Context context,String... st) {
        DBManager.getInstance(context).executeSql("delete from "+DBScript.TABLE_GOODS_NAME +" where "+DBScript.FIELD_GOODS_ID +"='"+st[0]+"'");
	}

    /**
     * @param context
     */
	@Override
	public void deleteAll(Context context) {
        DBManager.getInstance(context).executeSql("delete from " + DBScript.TABLE_GOODS_NAME);
	}

	@Override
	public GoodsDomain one(Context context,String... st) {
        String sql = "select * from "+DBScript.TABLE_GOODS_NAME +" where "+DBScript.FIELD_GOODS_ID +"='"+st[0]+"'" ;
        Cursor cursor = DBManager.getInstance(context).query(sql);
        GoodsDomain domain = null;
        if (cursor.moveToNext()) {
            domain = new GoodsDomain();
            domain.setId(st[0]);
            domain.setName(getString(cursor,DBScript.FIELD_GOODS_NAME));
            domain.setMemo(getString(cursor,DBScript.FIELD_GOODS_MEMO));
            domain.setImagePath(getString(cursor,DBScript.FIELD_GOODS_IMAGE_PATH));
            domain.setKindId(getString(cursor,DBScript.FIELD_GOODS_KIND_ID));
            domain.setTwoDimensionCode(getString(cursor,DBScript.FIELD_GOODS_TWO_DIMENSION_CODE));
            domain.setSpecification(getString(cursor,DBScript.FIELD_GOODS_SPECIFICATION));
        }
        closeCursor(cursor);
        return domain;
	}

    /**
     * 获取所有的部门信息
     * @param context
     * @param st 父类id
     * @return
     */
	@Override
	public List<GoodsDomain> list(Context context,String... st) {
        List<GoodsDomain> list = new ArrayList<GoodsDomain>();
        String sql = "select * from "+DBScript.TABLE_GOODS_NAME +" where "+DBScript.FIELD_GOODS_KIND_PARENT_ID +"='"+st[0]+"' order by "+DBScript.FIELD_GOODS_KIND_ID ;
        LogUtils.i(TAG,"GoodsDao.list:"+sql);
        Cursor cursor = DBManager.getInstance(context).query(sql);
        GoodsDomain domain = null;
        while (cursor.moveToNext()) {
            domain = new GoodsDomain();
            domain.setId(getString(cursor,DBScript.FIELD_GOODS_KIND_ID));
            domain.setName(getString(cursor,DBScript.FIELD_GOODS_NAME));
            domain.setMemo(getString(cursor,DBScript.FIELD_GOODS_MEMO));
            domain.setImagePath(getString(cursor,DBScript.FIELD_GOODS_IMAGE_PATH));
            domain.setKindId(getString(cursor,DBScript.FIELD_GOODS_KIND_ID));
            domain.setTwoDimensionCode(getString(cursor,DBScript.FIELD_GOODS_TWO_DIMENSION_CODE));
            domain.setSpecification(getString(cursor,DBScript.FIELD_GOODS_SPECIFICATION));
            list.add(domain);
        }
        closeCursor(cursor);
        return list;
	}
}
