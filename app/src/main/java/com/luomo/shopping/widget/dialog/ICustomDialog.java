package com.luomo.shopping.widget.dialog;


/**
 * Created by renpan on 2015-12-21.
 * 辅助弹出框完成弹出界面的赋值问题
 */
public interface ICustomDialog {
    /**
     * 弹出框，处理弹出界面的赋值问题
     * @param dialog promptDialog在此处类似于view
     */
    public void inflateViewAndData(CommonDialog dialog);
}
