package com.luomo.shopping;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
import com.luomo.shopping.utils.ActivityManager;

/**
 * Created by renpan on 2015/11/19.
 */
public class LuomoApplication extends Application {

    private static LuomoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public LuomoApplication() {
        if (instance == null) {
            instance = this;
        }
    }

    /**
     * 获取当前 application 的实例
     */
    public static LuomoApplication getInstance() {
        return instance;
    }

    /**
     * 安全退出应用
     */
    public void exit() {
        ActivityManager.getActivityManager().AppExit(getApplicationContext());
    }
}
