package com.boco.eoms.partdata.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:14位信令点
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 *
 * @author Josh
 * @version 3.5
 */
public class TawPartFourteenSignal extends BaseObject {

    /**
     * 主键字段
     */
    private java.lang.String id;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    /**
     * userid
     */
    private java.lang.String userid;

    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }

    public java.lang.String getUserid() {
        return this.userid;
    }

    /**
     * 更新时间
     */
    private java.lang.String updatedate;

    public void setUpdatedate(java.lang.String updatedate) {
        this.updatedate = updatedate;
    }

    public java.lang.String getUpdatedate() {
        return this.updatedate;
    }

    /**
     * signalnum
     */
    private java.lang.String signalnum;

    public void setSignalnum(java.lang.String signalnum) {
        this.signalnum = signalnum;
    }

    public java.lang.String getSignalnum() {
        return this.signalnum;
    }

    /**
     * signalvalue
     */
    private java.lang.String signalvalue;

    public void setSignalvalue(java.lang.String lacvalue) {
        this.signalvalue = lacvalue;
    }

    public java.lang.String getSignalvalue() {
        return this.signalvalue;
    }


    public boolean equals(Object o) {
        if (o instanceof TawPartFourteenSignal) {
            TawPartFourteenSignal tawPartFourteenSignal = (TawPartFourteenSignal) o;
            if (this.id != null || this.id.equals(tawPartFourteenSignal.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}