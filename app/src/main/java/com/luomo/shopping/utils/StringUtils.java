package com.luomo.shopping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.ourfuture.dataopen.utils
 * @date :2016-03-29 11:06
 * @description:字符串工具类
 */
public class StringUtils {
    /**
     * 字符串是否为空
     * @param str
     * @return true 空
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0 ||str.toString().trim().length()==0||"null".equals(str.toString())) {//为空判断
            return true;
        }else {
            return false;
        }
    }

    /**
     * 邮箱的正则校验
     * @return
     */
    public static boolean isValidatedEmail(String email){
        //电子邮件
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
}
