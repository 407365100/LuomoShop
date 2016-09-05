package com.luomo.shopping.utils;

import android.app.Activity;
import android.view.View;

/**
 * Created by renpan on 2015/11/18.
 */
public class ViewUtils {
        /**
         * activity.findViewById()
         * @param context
         * @param id
         * @return
         */
        public static <T extends View> T f(Activity context, int id) {
            return (T) context.findViewById(id);
        }
        /**
         * rootView.findViewById()
         * @param rootView
         * @param id
         * @return
         */
        public static <T extends View> T f(View rootView, int id) {
            return (T) rootView.findViewById(id);
        }
}
