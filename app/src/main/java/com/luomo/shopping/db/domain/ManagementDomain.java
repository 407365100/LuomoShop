package com.luomo.shopping.db.domain;

import java.io.Serializable;

/**
 * @AUTHOR:renpan
 * @VERSION:v1.0
 * @DATE:2016-02-24 17:20
 */
public class ManagementDomain  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int drawableId;

    public ManagementDomain(int drawableId, String name) {
        this.drawableId = drawableId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
