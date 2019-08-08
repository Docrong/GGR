package com.boco.eoms.partner.baseinfo.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:��Ա�������
 * </p>
 * <p>
 * Description:��Ա�������
 * </p>
 * <p>
 * Tue Mar 10 16:24:31 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public class PartnerUserAndArea extends BaseObject {

    /**
     * ���
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * ��Ա����
     */
    private java.lang.String name;

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return this.name;
    }

    /**
     * ����ID
     */
    private java.lang.String userId;

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    /**
     * ����˾
     */
    private java.lang.String areaNames;

    public void setAreaNames(java.lang.String areaName) {
        this.areaNames = areaName;
    }

    public java.lang.String getAreaNames() {
        return this.areaNames;
    }

    /**
     * 区域类型：省公司、地市
     */
    private String areaType;

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public boolean equals(Object o) {
        if (o instanceof PartnerUserAndArea) {
            PartnerUserAndArea partnerUserAndArea = (PartnerUserAndArea) o;
            if (this.id != null || this.id.equals(partnerUserAndArea.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}