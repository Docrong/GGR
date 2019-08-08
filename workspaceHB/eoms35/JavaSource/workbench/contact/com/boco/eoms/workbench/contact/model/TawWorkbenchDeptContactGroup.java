package com.boco.eoms.workbench.contact.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawWorkbenchContactGroup.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_workbench_contactgroup"
 */

/**
 * <p>
 * Title:部门通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 21:57:30 AM
 * </p>
 *
 * @author 孙圣泰
 * @version 3.5.1
 */
public class TawWorkbenchDeptContactGroup extends BaseObject implements Serializable {
    private String id; // 主键id

    private String deleted; // 删除标志（0表示实际存在的，1表示被删除的）

    private String groupName; // 分组名称

    private String remark; // 备注

    private String userId; // 所属用户

    private String deptId; //所属用户的部门

    private String groupId;

    private String allow;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return
     * @hibernate.property length="8"
     * @eoms.show
     * @eoms.cn name="删除标志"
     */
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="分组名称"
     */
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property length="255"
     * @eoms.show
     * @eoms.cn name="备注"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return
     * @hibernate.property length="25"
     * @eoms.show
     * @eoms.cn name="所属用户"
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return
     * @hibernate.property length="8"
     * @eoms.show
     * @eoms.cn name="允许下级部门"
     */

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    /**
     * @return
     * @hibernate.property length="32"
     * @eoms.show
     * @eoms.cn name="所属人员所在部门ID"
     */
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }


    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }


    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}
