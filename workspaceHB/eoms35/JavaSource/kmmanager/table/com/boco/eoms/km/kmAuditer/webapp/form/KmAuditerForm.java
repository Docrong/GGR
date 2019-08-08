package com.boco.eoms.km.kmAuditer.webapp.form;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.webapp.action.bo.TawSystemSubRoleBO;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

/**
 * <p>
 * Title:知识管理审核人配置表
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 戴志刚
 * @moudle.getVersion() 1.0
 */
public class KmAuditerForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 锟斤拷锟�
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 新增时间
     */
    private java.lang.String createTime;

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getCreateTime() {
        return this.createTime;
    }


    /**
     * 审核类型
     */
    private java.lang.String auditType;

    public void setAuditType(java.lang.String auditType) {
        this.auditType = auditType;
    }

    public java.lang.String getAuditType() {
        return this.auditType;
    }

    /**
     * 文件分类
     */
    private java.lang.String nodeId;

    public void setNodeId(java.lang.String nodeId) {
        this.nodeId = nodeId;
    }

    public java.lang.String getNodeId() {
        return this.nodeId;
    }

    /**
     * 知识库分类
     */
    private java.lang.String tableId;

    public void setTableId(java.lang.String tableId) {
        this.tableId = tableId;
    }

    public java.lang.String getTableId() {
        return this.tableId;
    }

    /**
     * 知识分类
     */
    private java.lang.String themeId;

    public void setThemeId(java.lang.String themeId) {
        this.themeId = themeId;
    }

    public java.lang.String getThemeId() {
        return this.themeId;
    }

    /**
     * 审核角色
     */
    private java.lang.String roleId;

    public void setRoleId(java.lang.String roleId) {
        this.roleId = roleId;
    }

    public java.lang.String getRoleId() {
        return this.roleId;
    }

    /**
     * 审核角色data 串
     */
    private java.lang.String roleIdData;

    public java.lang.String getRoleIdData() {
        if (roleId == null || roleId.equals("")) {
            roleIdData = "";
        } else {
            ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemSubRoleManager");
            TawSystemSubRole subrole = mgr.getTawSystemSubRole(roleId);
            String roleName = subrole.getSubRoleName();
            roleIdData = "[{id:'" + roleId + "',nodeType:'subrole',categoryId:'role',name:'" + roleName + "',text:'" + roleName + "'}]";
        }
        return roleIdData;
    }

    /**
     * 审核主管
     */
    private java.lang.String masterId;

    public void setMasterId(java.lang.String masterId) {
        this.masterId = masterId;
    }

    public java.lang.String getMasterId() {
        return this.masterId;
    }

    /**
     * 审核主管data 串
     */
    private java.lang.String masterIdData;

    public java.lang.String getMasterIdData() {
        if (masterId == null || masterId.equals("")) {
            masterIdData = "";
        } else {
            TawSystemUserDao dao = (TawSystemUserDao) ApplicationContextHolder.getInstance().getBean("tawSystemUserDao");
            String userName = dao.getTawSystemUserByuserid(masterId).getUsername();
            masterIdData = "[{id:'" + masterId + "',nodeType:'user',categoryId:'master',name:'" + userName + "',text:'" + userName + "'}]";
        }
        return masterIdData;
    }

    /**
     * 是否需要主管审核
     */
    private java.lang.Integer masterAudit;

    public void setMasterAudit(java.lang.Integer masterAudit) {
        this.masterAudit = masterAudit;
    }

    public java.lang.Integer getMasterAudit() {
        return this.masterAudit;
    }

    /**
     * 是否需要会审
     */
    private java.lang.Integer isSign;

    public void setIsSign(java.lang.Integer isSign) {
        this.isSign = isSign;
    }

    public java.lang.Integer getIsSign() {
        return this.isSign;
    }

    /**
     * 备注
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        if (remark != null)
            remark = remark.trim();
        return this.remark;
    }


}