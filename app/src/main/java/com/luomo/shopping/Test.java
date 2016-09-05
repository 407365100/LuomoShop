package com.luomo.shopping;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping
 * @date :2016-04-01 10:30
 * @description:测试类
 */
public class Test {
    public static void main(String[] args) {
        String st="123";
        //---调用一般方法
        new Test().stringParameter("0");
        new Test().stringParameter("1");
        new Test().stringParameter("1");
        new Test().stringParameter("2");
        new Test().stringParameter(st);
        new Test().stringParameter(st);
        //---调用静态方法
        staticStringParameter("0");
        staticStringParameter("1");
        staticStringParameter("1");
        staticStringParameter("2");
        staticStringParameter(st);
        staticStringParameter(st);
    }
    //---一般方法
    private String fs;
    private void stringParameter(String st) {
        System.out.println("Test.stringParameter----------传入的参数值："+st);
        System.out.println("Test.stringParameter 传入的参数地址:"+st.hashCode());
        String ps = st;
        System.out.println("Test.stringParameter 方法中变量地址："+ps.hashCode());
        fs = st;
        System.out.println("Test.stringParameter 全局变量地址:"+fs.hashCode());
    }
    //---静态方法
    private static String sfs;//static field string
    private static void staticStringParameter(String st) {
        System.out.println("Test.staticStringParameter----------传入的参数值："+st);
        System.out.println("Test.staticStringParameter 传入的参数地址:"+st.hashCode());
        String sps = st;//static parameter string
        System.out.println("Test.staticStringParameter 方法中变量地址：:"+sps.hashCode());
        sfs = st;
        System.out.println("Test.staticStringParameter 全局变量地址::"+sfs.hashCode());
    }
}
