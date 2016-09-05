package com.luomo.shopping.widget.systembar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import com.luomo.shopping.R;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.ristone.businessasso.widget.systembar
 * @date :2016-04-12 20:26
 * @description:
 */
public class SystemBarWrap {
    private Activity activity;
    private SystemBarTintManager tintManager;

    public SystemBarWrap(Activity activity) {
        this.activity = activity;
    }

    public SystemBarTintManager initSystemBar(){
        return initSystemBar(R.color.action_bar_bg);
    }

    /**
     * 设置状态栏颜色
     */
    public SystemBarTintManager initSystemBar(int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(res);//通知栏所需颜色
        }
        return tintManager;
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
