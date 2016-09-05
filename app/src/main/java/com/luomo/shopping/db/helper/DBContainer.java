package com.luomo.shopping.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBContainer extends SQLiteOpenHelper {

	private static DBContainer dbContainer = null;
	private Context context;
	private static SQLiteOpenHelper mOpenHelper;

	private DBContainer(Context context, String name, CursorFactory factory,int version) {
		super(context, DBContant.DBName, factory, DBContant.VERSION);
	}

	private DBContainer(Context context, String name, int version) {
		this(context, DBContant.DBName, null, DBContant.VERSION);
	}

	private DBContainer(Context context) {
		this(context, DBContant.DBName, DBContant.VERSION);
	}

	public static DBContainer getInstance(Context context) {
		if (dbContainer == null) {
			dbContainer = new DBContainer(context);
			dbContainer.context = context;
		}
		return dbContainer;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/** 创建表结构 */
		createTable(db);
		/** 初始化数据 */
		initData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		initData(db);
	}

	/**
	 * @description:初始化数据
	 * @author:baihe
	 * @return:void
	 * @param db
	 */
	private void initData(SQLiteDatabase db) {
		
		
	}

	/**
	 * @description:创建表
	 * @author:baihe
	 * @return:void
	 * @param db
	 */
	private void createTable(SQLiteDatabase db) {
		db.execSQL(DBScript.TABLE_GOODS);//商品
		db.execSQL(DBScript.TABLE_GOODS_KIND);//商品种类
		db.execSQL(DBScript.TABLE_MEASURING_UNIT);//计量单位
		db.execSQL(DBScript.TABLE_GOODS_PRICE);//商品价格
	}

	/**
	 * @description:删除表
	 * @author:baihe
	 * @return:void
	 * @param db
	 */
	private void deleteTable(SQLiteDatabase db) {
		
	}
}
