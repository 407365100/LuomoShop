package com.luomo.shopping.db.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 商品种类
 */
public class MeasuringUnitDomain implements Serializable {
	private static final long serialVersionUID = 1L;//数据库使用的字段
    public static final String ID="id";
    public static final String NAME="name";
	@SerializedName("mId")
	private String id;
	private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MeasuringUnitDomain{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
