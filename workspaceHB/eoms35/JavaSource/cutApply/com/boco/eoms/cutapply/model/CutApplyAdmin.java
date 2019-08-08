package com.boco.eoms.cutapply.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:干线割接管理权限人员
 * </p>
 * <p>
 * Description:干线割接管理权限人员
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 *
 * @author wangsixuan
 * @version 3.5
 */
public class CutApplyAdmin extends BaseObject {

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 权限人员
     */
    private java.lang.String userId;

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }


    public boolean equals(Object o) {
        if (o instanceof CutApplyAdmin) {
            CutApplyAdmin cutApply = (CutApplyAdmin) o;
            if (this.id != null || this.id.equals(cutApply.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}