package com.luomo.shopping.db.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理拼接sql字符串
 */
public class DBUtils {
    /**
     * 获取某个类不为空，且为Object的所有字段
     * 反射问题：非string的其他基本类型；比如数组,在ReflectListener中判断出是数组的key（变量名），可直接用相应类型强制转换
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> void getBeanMapObject(T t, ReflectListener listener) {
        Map<String, Object> map = new HashMap<String, Object>();
        /*反射所有的变量包括private*/
        for (Field field : t.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);//暴力反射
                if (null == field.get(t)|| Modifier.isFinal(field.getModifiers())||Modifier.isProtected(field.getModifiers())) {//判断字段是不是final标识的
                    continue;
                }
                listener.dealNameAndValue(field.getName(), field.get(t), field.getType());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反射的监听类
     */
    public interface ReflectListener {
        /**
         * 处理name和value的值
         *
         * @param key
         * @param value
         */
        void dealNameAndValue(String key, Object value, Class valClass);
    }
}
