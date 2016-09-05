package com.luomo.shopping.db.domain;

import java.io.Serializable;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.shopping.db.domain
 * @date :2016-03-29 23:33
 * @description:
 */
public class PriceDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    //数据库使用的字段
    public static final String ID="id";
    public static final String PRICE="price";
    public static final String GOODS_ID="goodsId";
    public static final String MEASURING_UNIT_ID="measuringUnitId";
    private String id;
    private String price;
    private String goodsId;
    private String measuringUnitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getMeasuringUnitId() {
        return measuringUnitId;
    }

    public void setMeasuringUnitId(String measuringUnitId) {
        this.measuringUnitId = measuringUnitId;
    }

    @Override
    public String toString() {
        return "PriceDomain{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", measuringUnitId='" + measuringUnitId + '\'' +
                '}';
    }
}
