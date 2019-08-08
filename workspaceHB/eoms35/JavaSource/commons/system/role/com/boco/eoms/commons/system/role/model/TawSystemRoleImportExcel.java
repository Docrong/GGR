package com.boco.eoms.commons.system.role.model;

import java.io.Serializable;

/**
 * <p>
 * Title:角色导入，excel与pojo的映射pojo
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Sep 10, 2008 8:29:06 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public class TawSystemRoleImportExcel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5964548665585111138L;

    /**
     * 大角色id
     */
    private String roleId;

    /**
     * 子角色名称
     */
    private String subRoleName;

    /**
     * 区域
     */
    private String areaId;

    /**
     * 一级类别
     */
    private String firstType;

    /**
     * 二级类别
     */
    private String secondType;

    /**
     * 三级类别
     */
    private String thirdType;

    /**
     * 四级类别
     */
    private String fourthType;

    /**
     * 用户帐号
     */
    private String userId;

    /**
     * 虚拟组长帐号
     */
    private String groupId;

    /**
     * 虚拟组否
     */
    private String isGroup;

    /**
     * @return the areaId
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * @param areaId the areaId to set
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * @return the firstType
     */
    public String getFirstType() {
        return firstType;
    }

    /**
     * @param firstType the firstType to set
     */
    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    /**
     * @return the fourthType
     */
    public String getFourthType() {
        return fourthType;
    }

    /**
     * @param fourthType the fourthType to set
     */
    public void setFourthType(String fourthType) {
        this.fourthType = fourthType;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the isGroup
     */
    public String getIsGroup() {
        return isGroup;
    }

    /**
     * @param isGroup the isGroup to set
     */
    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the secondType
     */
    public String getSecondType() {
        return secondType;
    }

    /**
     * @param secondType the secondType to set
     */
    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    /**
     * @return the subRoleName
     */
    public String getSubRoleName() {
        return subRoleName;
    }

    /**
     * @param subRoleName the subRoleName to set
     */
    public void setSubRoleName(String subRoleName) {
        this.subRoleName = subRoleName;
    }

    /**
     * @return the thirdType
     */
    public String getThirdType() {
        return thirdType;
    }

    /**
     * @param thirdType the thirdType to set
     */
    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
