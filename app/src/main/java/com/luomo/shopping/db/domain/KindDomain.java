package com.luomo.shopping.db.domain;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
  * @class :com.luomo.shopping.db.domain.KindDomain
  * @description:商品种类
  * @author :renpan
  * @version :v1.0
  * @date :2016-03-23 22:50
  */
public class KindDomain implements Serializable {
	private static final long serialVersionUID = 1L;
    //数据库使用的字段
    public static final String ID="id";
    public static final String NAME="name";
    public static final String PARENT_ID="parentId";
	@SerializedName("kId")
	private String id;//分类id
	private String name;//分类名
	private String parentId;//父类id:父类id为000时，则为顶级父类id

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public KindDomain() {
    }

    public KindDomain(String parentId) {
        this.parentId = parentId;
    }

    public KindDomain(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "KindDomain{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
