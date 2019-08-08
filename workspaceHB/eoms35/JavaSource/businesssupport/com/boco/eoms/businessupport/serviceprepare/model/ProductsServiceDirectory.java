package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;

/**
 * 产品服务目录
 *
 * @author yangliangliang
 */
public class ProductsServiceDirectory implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 产品规格表id
     */
    private String b_id;
    /**
     * 删除标示
     */
    private String deleted;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
