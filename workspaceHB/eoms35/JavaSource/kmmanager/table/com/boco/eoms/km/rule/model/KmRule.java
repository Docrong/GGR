package com.boco.eoms.km.rule.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:规则库
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmRule extends BaseObject {

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
     * 创建部门
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
     * 规则名称
     */
    private java.lang.String ruleName;

    public void setRuleName(java.lang.String ruleName) {
        this.ruleName = ruleName;
    }

    public java.lang.String getRuleName() {
        return this.ruleName;
    }

    /**
     * 规则脚本
     */
    private java.lang.String ruleScript;

    public void setRuleScript(java.lang.String ruleScript) {
        this.ruleScript = ruleScript;
    }

    public java.lang.String getRuleScript() {
        return this.ruleScript;
    }

    /**
     * 规则参数说明
     */
    private java.lang.String ruleParameter;

    public void setRuleParameter(java.lang.String ruleParameter) {
        this.ruleParameter = ruleParameter;
    }

    public java.lang.String getRuleParameter() {
        return this.ruleParameter;
    }

    /**
     * 是否已删除
     */
    private java.lang.String isDeleted;

    public void setIsDeleted(java.lang.String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.String getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 备注
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 知识库id(外键)
     */

    private java.lang.String contentId;

    public java.lang.String getContentId() {
        return contentId;
    }

    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }


    public boolean equals(Object o) {
        if (o instanceof KmRule) {
            KmRule kmRule = (KmRule) o;
            if (this.id != null || this.id.equals(kmRule.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}