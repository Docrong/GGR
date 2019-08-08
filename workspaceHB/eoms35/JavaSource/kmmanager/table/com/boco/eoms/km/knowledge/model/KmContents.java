package com.boco.eoms.km.knowledge.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableTheme;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:12 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmContents extends BaseObject {

    /**
     * 主键
     */
    private java.lang.String id;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 表中所对应的知识id
     */
    private java.lang.String contentId;

    public java.lang.String getContentId() {
        return contentId;
    }

    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }

    /**
     * 模型定义(外键)
     */
    private String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 知识分类表(外键)
     */
    private java.lang.String themeId;

    public void setThemeId(java.lang.String themeId) {
        this.themeId = themeId;
    }

    public java.lang.String getThemeId() {
        return this.themeId;
    }

    /**
     * 创建人
     */
    private java.lang.String createUser;

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }

    public java.lang.String getCreateUser() {
        return this.createUser;
    }

    /**
     * 创建人部门
     */
    private java.lang.String createDept;

    public void setCreateDept(java.lang.String createDept) {
        this.createDept = createDept;
    }

    public java.lang.String getCreateDept() {
        return this.createDept;
    }

    /**
     * 创建时间
     */
    private java.util.Date createTime;

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 修改人
     */
    private java.lang.String modifyUser;

    public void setModifyUser(java.lang.String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public java.lang.String getModifyUser() {
        return this.modifyUser;
    }

    /**
     * 修改人部门
     */
    private java.lang.String modifyDept;

    public void setModifyDept(java.lang.String modifyDept) {
        this.modifyDept = modifyDept;
    }

    public java.lang.String getModifyDept() {
        return this.modifyDept;
    }

    /**
     * 修改日期
     */
    private java.util.Date modifyTime;

    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public java.util.Date getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 知识标题
     */
    private java.lang.String contentTitle;

    public void setContentTitle(java.lang.String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public java.lang.String getContentTitle() {
        return this.contentTitle;
    }

    /**
     * 知识关键字
     */
    private java.lang.String contentKeys;

    public void setContentKeys(java.lang.String contentKeys) {
        this.contentKeys = contentKeys;
    }

    public java.lang.String getContentKeys() {
        return this.contentKeys;
    }

    /**
     * 知识状态
     */
    private java.lang.String contentStatus;

    public void setContentStatus(java.lang.String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public java.lang.String getContentStatus() {
        return this.contentStatus;
    }

    /**
     * 审核标识
     */
    private java.lang.String auditFlag;

    public void setAuditFlag(java.lang.String auditFlag) {
        this.auditFlag = auditFlag;
    }

    public java.lang.String getAuditFlag() {
        return this.auditFlag;
    }

    /**
     * 知识等级
     */
    private java.lang.String rolestrFlag;

    public void setRolestrFlag(java.lang.String rolestrFlag) {
        this.rolestrFlag = rolestrFlag;
    }

    public java.lang.String getRolestrFlag() {
        return this.rolestrFlag;
    }

    /**
     * 知识难易度
     */
    private java.lang.String levelFlag;

    public void setLevelFlag(java.lang.String levelFlag) {
        this.levelFlag = levelFlag;
    }

    public java.lang.String getLevelFlag() {
        return this.levelFlag;
    }

    /**
     * 是否推荐
     */
    private java.lang.String isBest;

    public void setIsBest(java.lang.String isBest) {
        this.isBest = isBest;
    }

    public java.lang.String getIsBest() {
        return this.isBest;
    }

    /**
     * 是否公开
     */
    private java.lang.String isPublic;

    public void setIsPublic(java.lang.String isPublic) {
        this.isPublic = isPublic;
    }

    public java.lang.String getIsPublic() {
        return this.isPublic;
    }

    /**
     * 知识评价：1星的次数
     */
    private java.lang.Integer gradeOne;

    public void setGradeOne(java.lang.Integer gradeOne) {
        this.gradeOne = gradeOne;
    }

    public java.lang.Integer getGradeOne() {
        return this.gradeOne;
    }

    /**
     * 知识评价：2星的次数
     */
    private java.lang.Integer gradeTwo;

    public void setGradeTwo(java.lang.Integer gradeTwo) {
        this.gradeTwo = gradeTwo;
    }

    public java.lang.Integer getGradeTwo() {
        return this.gradeTwo;
    }

    /**
     * 知识评价：3星的次数
     */
    private java.lang.Integer gradeThree;

    public void setGradeThree(java.lang.Integer gradeThree) {
        this.gradeThree = gradeThree;
    }

    public java.lang.Integer getGradeThree() {
        return this.gradeThree;
    }

    /**
     * 知识评价：4星的次数
     */
    private java.lang.Integer gradeFour;

    public void setGradeFour(java.lang.Integer gradeFour) {
        this.gradeFour = gradeFour;
    }

    public java.lang.Integer getGradeFour() {
        return this.gradeFour;
    }

    /**
     * 知识评价：5星的次数
     */
    private java.lang.Integer gradeFive;

    public void setGradeFive(java.lang.Integer gradeFive) {
        this.gradeFive = gradeFive;
    }

    public java.lang.Integer getGradeFive() {
        return this.gradeFive;
    }

    /**
     * 知识被阅读的次数
     */
    private java.lang.Integer readCount;

    public void setReadCount(java.lang.Integer readCount) {
        this.readCount = readCount;
    }

    public java.lang.Integer getReadCount() {
        return this.readCount;
    }

    /**
     * 知识被引用的次数
     */
    private java.lang.Integer useCount;

    public void setUseCount(java.lang.Integer useCount) {
        this.useCount = useCount;
    }

    public java.lang.Integer getUseCount() {
        return this.useCount;
    }

    /**
     * 知识内容
     */
    private java.lang.String contentXml;

    public void setContentXml(java.lang.String contentXml) {
        this.contentXml = contentXml;
    }

    public java.lang.String getContentXml() {
        return this.contentXml;
    }

    /**
     * 知识被修改的次数
     */
    private java.lang.Integer modifyCount;

    public void setModifyCount(java.lang.Integer modifyCount) {
        this.modifyCount = modifyCount;
    }

    public java.lang.Integer getModifyCount() {
        return this.modifyCount;
    }

    private java.lang.Integer version;

    public java.lang.Integer getVersion() {
        return version;
    }

    public void setVersion(java.lang.Integer version) {
        this.version = version;
    }


    /**
     * 创建人name
     */
    private java.lang.String createUserName;

    public java.lang.String getCreateUserName() {
        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");
        createUserName = userMgr.getUserByuserid(this.createUser).getUsername();
        return createUserName;
    }

    /**
     * 创建人部门name
     */
    private java.lang.String createDeptName;

    public java.lang.String getCreateDeptName() {

        ITawSystemDeptManager mgrdept = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        createDeptName = mgrdept.getTawSystemDept(Integer.valueOf(this.createDept)).getDeptName();

        return createDeptName;
    }

    /**
     * 知识分类名称
     */
    private java.lang.String themeName;

    public java.lang.String getThemeName() {
        KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) ApplicationContextHolder.getInstance().getBean("kmTableThemeMgr");
        KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(themeId);
        themeName = tableTheme.getThemeName();
        return themeName;
    }

    public boolean equals(Object o) {
        if (o instanceof KmContents) {
            KmContents kmContents = (KmContents) o;
            if (this.id != null || this.id.equals(kmContents.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}