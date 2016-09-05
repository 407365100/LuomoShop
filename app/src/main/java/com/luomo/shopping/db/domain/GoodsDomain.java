package com.luomo.shopping.db.domain;

import java.io.Serializable;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-17 10:01
 */
public class GoodsDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 二维码
     */
    private String twoDimensionCode;
    /**
     * 商品名
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 规格如400g
     */
    private String specification;
    /**
     * 图片地址
     */
    private String imagePath;
    /**
     * 分类id(外键)
     */
    private String kindId;
    //以下为程序中使用到的
    protected int buyNumbers = 1;//购买的商品数量

    protected boolean isSelected = false;//购物车中，商品选中状态：true选中

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTwoDimensionCode() {
        return twoDimensionCode;
    }

    public void setTwoDimensionCode(String twoDimensionCode) {
        this.twoDimensionCode = twoDimensionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getBuyNumbers() {
        return buyNumbers;
    }

    public void setBuyNumbers(int buyNumbers) {
        this.buyNumbers = buyNumbers;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
