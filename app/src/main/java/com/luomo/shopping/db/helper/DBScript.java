package com.luomo.shopping.db.helper;

/**
 * 数据库脚本
 */
public class DBScript {
    //-----表名---------
    public static final String TABLE_GOODS_NAME = "goods";//商品信息
    public static final String TABLE_GOODS_PRICE_NAME = "goodsPrice";//商品价格信息
    public static final String TABLE_GOODS_KIND_NAME = "goodsKind";//商品分类信息
    public static final String TABLE_MEASURING_UNIT_NAME = "measuringUnit";//商品计量单位
    //-----商品分类信息字段名---------
    public static final String FIELD_GOODS_KIND_ID = "id";//分类id
    public static final String FIELD_GOODS_KIND_PARENT_ID = "parent_id";//父类id
    public static final String FIELD_GOODS_KIND_NAME = "name";//商品名 最多20个字
    //-----商品计量单位字段名---------
    public static final String FIELD_MEASURING_UNIT_ID = "id";//计量单位id
    public static final String FIELD_MEASURING_UNIT_NAME = "name";//计量单位名
    //-----商品信息字段名
    public static final String FIELD_GOODS_ID = "id";//价格id
    public static final String FIELD_GOODS_TWO_DIMENSION_CODE = "two_dimension_code";//二维码
    public static final String FIELD_GOODS_NAME = "name";//商品名
    public static final String FIELD_GOODS_MEMO = "memo";//备注
    public static final String FIELD_GOODS_SPECIFICATION = "specification";//规格如400g
    public static final String FIELD_GOODS_IMAGE_PATH = "image_path";//图片地址
    public static final String FIELD_GOODS_GOODS_KIND_ID = "kind_id";//分类外键
    //-----商品价格信息字段名
    public static final String FIELD_GOODS_PRICE_ID = "id";//价格id
    public static final String FIELD_GOODS_PRICE_GOODS_ID = "goods_id";//商品id
    public static final String FIELD_GOODS_PRICE_MEASURING_UNIT_ID = "measuring_unit_id";//计量单位id
    public static final String FIELD_GOODS_PRICE = "price";//价格
    /**
     * 商品信息表
     */
    public static final String TABLE_GOODS = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_NAME + "(" +
            FIELD_GOODS_ID+" TEXT PRIMARY KEY NOT NULL ," +
            FIELD_GOODS_TWO_DIMENSION_CODE+" TEXT," +
            FIELD_GOODS_NAME+" TEXT," +
            FIELD_GOODS_IMAGE_PATH+" TEXT," +
            FIELD_GOODS_MEMO+" TEXT," +
            FIELD_GOODS_SPECIFICATION+" TEXT," +
            FIELD_GOODS_GOODS_KIND_ID+" TEXT," +
            "foreign key ("+ FIELD_GOODS_GOODS_KIND_ID +") references "+ TABLE_GOODS_KIND_NAME +"("+FIELD_GOODS_KIND_ID+"))"; //商品分类外键
    /**
     * 商品价格信息
     */
    public static final String TABLE_GOODS_PRICE = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_PRICE_NAME + "(" +
            FIELD_GOODS_PRICE_ID +" TEXT PRIMARY KEY NOT NULL," +
            FIELD_GOODS_PRICE +" TEXT," +
            FIELD_GOODS_PRICE_GOODS_ID +" TEXT," +//商品编号
            FIELD_GOODS_PRICE_MEASURING_UNIT_ID +" TEXT,"+ //计量单位编号
            "foreign key ("+FIELD_GOODS_PRICE_GOODS_ID+") references "+ TABLE_GOODS_NAME +"("+FIELD_GOODS_ID+")," + //商品外键
            "foreign key ("+ FIELD_GOODS_PRICE_MEASURING_UNIT_ID +") references "+ TABLE_MEASURING_UNIT_NAME +"("+FIELD_MEASURING_UNIT_ID+"))"; //计量单位外键

    /**
     * 商品分类表
     */
    public static final String TABLE_GOODS_KIND = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_KIND_NAME + "(" +
            FIELD_GOODS_KIND_ID +" TEXT PRIMARY KEY NOT NULL," +
            FIELD_GOODS_KIND_PARENT_ID +" TEXT," +
            FIELD_GOODS_KIND_NAME +" TEXT)";

    /**
     * 商品计量单位表
     */
    public static final String TABLE_MEASURING_UNIT = "CREATE TABLE IF NOT EXISTS " + TABLE_MEASURING_UNIT_NAME + "(" +
            FIELD_MEASURING_UNIT_ID+" TEXT PRIMARY KEY NOT NULL," + //计量单位id
            FIELD_MEASURING_UNIT_NAME+" TEXT)";
}