package com.luomo.shopping.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.luomo.shopping.R;

public class ToastUtils {
    private static Toast instance = null;//toast实例
    private static Context context = null;
    private static synchronized Toast getInstance() {
        if (instance == null) {
            instance = new Toast(context);
        }
        return instance;
    }
    private static void newInstance(Context context, String msg) {
        ToastUtils.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView tvMsg = (TextView) view.findViewById(R.id.toast_msg);
        tvMsg.setText(msg);
        instance = getInstance();
        instance.setGravity(17, 0, 0);
        instance.setDuration(Toast.LENGTH_SHORT);
        instance.setView(view);
    }

    /**
     * show instance
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        if(null == instance) {
            newInstance(context, msg);
        }
        instance.show();
    }

    /**
     * dismiss instance
     */
    public static void dismiss() {
        if (null != instance) {
            instance.cancel();
            instance = null;
        }
    }
}
