package com.luomo.shopping.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    public static DBManager dbManager = null;
    private Context context = null;
    private DBContainer dbContainer = null;
    private static SQLiteDatabase sqliteDatabase = null;

    private DBManager(Context context) {
        this.context = context;
    }

    public static synchronized DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        dbManager.dbContainer = DBContainer.getInstance(context);
        if (dbManager != null && dbManager.dbContainer != null) {
            if (dbManager.dbContainer.getWritableDatabase() != null) {
                dbManager.sqliteDatabase = dbManager.dbContainer.getWritableDatabase();
            }
        }
        return dbManager;
    }

    public void insert(String table, ContentValues values) {
        sqliteDatabase.insert(table, null, values);
    }

    public void insert(String sql, Object[] bindArgs) {
        sqliteDatabase.execSQL(sql, bindArgs);
    }

    public void update(String table, ContentValues values, String selection, String[] selectionArgs) {
        sqliteDatabase.update(table, values, selection, selectionArgs);
    }

    public void update(String sql, Object[] bindArgs) {
        sqliteDatabase.execSQL(sql, bindArgs);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs) {
        Cursor cursor = sqliteDatabase.query(table, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor cursor = sqliteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }

    public Cursor query(String sql, String[] selectionArgs) {
        return sqliteDatabase.rawQuery(sql, selectionArgs);
    }

    public Cursor query(String sql) {
        return sqliteDatabase.rawQuery(sql, null);
    }

    public void delete(String table, String whereClause, String[] whereArgs) {
        sqliteDatabase.delete(table, whereClause, whereArgs);
    }

    public void delete(String sql, Object[] bindArgs) {
        sqliteDatabase.execSQL(sql, bindArgs);
    }

    public void executeSql(String sql) {
        try {
            sqliteDatabase.beginTransaction();
            sqliteDatabase.execSQL(sql);
            sqliteDatabase.setTransactionSuccessful();
        } catch (Exception e) {

        } finally {
            sqliteDatabase.endTransaction();
        }
    }

    public void executeSqls(String[] sql, SQLiteDatabase database) {
        if (sql == null) return;
        database.beginTransaction();
        try {
            for (int i = 0; i < sql.length; i++) {
                database.execSQL(sql[i]);
            }
            database.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            database.endTransaction();
        }
    }
}
