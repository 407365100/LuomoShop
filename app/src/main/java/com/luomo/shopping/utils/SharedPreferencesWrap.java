package com.luomo.shopping.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * SharedPreferences的封装类
 */
public class SharedPreferencesWrap {

    private static SharedPreferencesWrap shareManager = null;
    private static SharedPreferences shared = null;
    private static Editor editor = null;

    private SharedPreferencesWrap(Context context) {
        shared = PreferenceManager.getDefaultSharedPreferences(context);
        editor = shared.edit();
    }

    public static SharedPreferencesWrap getInstance(Context context) {
        if (shareManager == null && context != null) {
            shareManager = new SharedPreferencesWrap(context);
        }
        return shareManager;
    }

    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return shared.getBoolean(key, defaultValue);
    }

    public void setInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defaultValue) {
        return shared.getInt(key, defaultValue);
    }

    public void setFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public Float getFloat(String key, float defaultValue) {
        return shared.getFloat(key, defaultValue);
    }

    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defalutValue) {
        return shared.getString(key, defalutValue);
    }

    public void setLong(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public Long getLong(String key, Long defaultValue) {
        return shared.getLong(key, defaultValue);
    }

    /**
     * 删除
     * @param key
     */
    public void remove(String key){
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除sp信息
     * @return
     */
    public boolean clearSharedPreferences() {
        editor.clear();
        return editor.commit();
    }
}
