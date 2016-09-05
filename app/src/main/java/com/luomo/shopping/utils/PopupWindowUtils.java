package com.luomo.shopping.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.luomo.shopping.R;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-28 10:46
 */
public class PopupWindowUtils {
    private static PopupWindow popupWindow = null;
    private static int layoutId = -1;//有默认布局
    /*
     * 获取PopupWindow实例
     */
    public static PopupWindow newInstance(Context context, OnLoadPopupWindowView onLoadPopupWindowView) {
        return newInstance(context,onLoadPopupWindowView,-1);
    }

    /*
     * 获取PopupWindow实例
     */
    public static PopupWindow newInstance(Context context, OnLoadPopupWindowView onLoadPopupWindowView,int layoutId) {
        PopupWindowUtils.layoutId = layoutId==-1?R.layout.popupwindow_kind:layoutId;//popupwindow_kind为默认布局
        if (popupWindow == null) {
            synchronized (PopupWindowUtils.class) {
                if (null == popupWindow) {
                    popupWindow = initPopupWindow(context, onLoadPopupWindowView);
                }
            }
        }
        return popupWindow;
    }

    /*
     * 创建PopupWindow
     */
    private static PopupWindow initPopupWindow(Context context, OnLoadPopupWindowView onLoadPopupWindowView) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId, null);
        onLoadPopupWindowView.initWindowView(context, view);//view可以在调用时，重新设置布局文件
        // 创建一个PopupWindow
        // 参数1：contentView 指定PopupWindow的内容
        // 参数2：width 指定PopupWindow的width
        // 参数3：height 指定PopupWindow的height
        //popupWindow = new PopupWindow(view, 400, 500);
        popupWindow = new PopupWindow(view);
        popupWindow.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        //popupWindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 获取屏幕和PopupWindow的width和height
        /*int mScreenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        mScreenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        int mPopupWindowWidth = popupWindow.getWidth();
        int mPopupWindowHeight = popupWindow.getHeight();*/
        return popupWindow;
    }

    public static void dismiss() {
        if (null != popupWindow && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    /**
     * 设置popup window的view信息
     */
    public interface OnLoadPopupWindowView {
        /**
         * @param context
         * @param view
         */
        public void initWindowView(Context context, View view);
    }
}
