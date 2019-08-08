package com.boco.eoms.km.knowledge.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识申请
 * </p>
 * <p>
 * Description:知识申请
 * </p>
 * <p>
 * Tue Jul 14 10:27:17 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class KmContentsApplyForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

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
     * 申请人
     */
    private java.lang.String applyUser;

    public void setApplyUser(java.lang.String applyUser) {
        this.applyUser = applyUser;
    }

    public java.lang.String getApplyUser() {
        return this.applyUser;
    }

    /**
     * 申请部门
     */
    private java.lang.String applyDept;

    public void setApplyDept(java.lang.String applyDept) {
        this.applyDept = applyDept;
    }

    public java.lang.String getApplyDept() {
        return this.applyDept;
    }

    /**
     * 申请时间
     */
    private String applyDate;

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyDate() {
        return this.applyDate;
    }

    /**
     * 申请标题
     */
    private java.lang.String applyTitle;

    public java.lang.String getApplyTitle() {
        return applyTitle;
    }

    public void setApplyTitle(java.lang.String applyTitle) {
        this.applyTitle = applyTitle;
    }

    /**
     * 所属知识库
     */
    private java.lang.String applyTable;

    public java.lang.String getApplyTable() {
        return applyTable;
    }

    public void setApplyTable(java.lang.String applyTable) {
        this.applyTable = applyTable;
    }


    /**
     * 知识分类表
     */
    private java.lang.String applyTheme;

    public java.lang.String getApplyTheme() {
        return applyTheme;
    }

    public void setApplyTheme(java.lang.String applyTheme) {
        this.applyTheme = applyTheme;
    }

    /**
     * 申请内容
     */
    private java.lang.String applyContent;

    public void setApplyContent(java.lang.String applyContent) {
        this.applyContent = applyContent;
    }

    public java.lang.String getApplyContent() {
        return this.applyContent;
    }

    /**
     * 是否已有对应知识
     */
    private java.lang.String isAdd;

    public void setIsAdd(java.lang.String isAdd) {
        this.isAdd = isAdd;
    }

    public java.lang.String getIsAdd() {
        return this.isAdd;
    }

    /**
     * 知识id
     */
    private java.lang.String contentId;

    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }

    public java.lang.String getContentId() {
        return this.contentId;
    }

    /**
     * 附件
     */
    private java.lang.String fileName;

    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }

    public java.lang.String getFileName() {
        return this.fileName;
    }
}