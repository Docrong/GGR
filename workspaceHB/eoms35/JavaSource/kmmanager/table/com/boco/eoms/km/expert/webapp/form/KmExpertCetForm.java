package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:证书管理
 * </p>
 * <p>
 * Description:证书管理
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 */
public class KmExpertCetForm extends BaseForm implements java.io.Serializable {

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
     * userid
     */
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    /**
     * 证书名称
     */
    private String expertCetName;

    public void setExpertCetName(String expertCetName) {
        this.expertCetName = expertCetName;
    }

    public String getExpertCetName() {
        return this.expertCetName;
    }

    /**
     * 获奖时间
     */
    private String expertCetDate;

    public void setExpertCetDate(String expertCetDate) {
        this.expertCetDate = expertCetDate;
    }

    public String getExpertCetDate() {
        if (this.expertCetDate != null) {
            if (expertCetDate.length() == 10) {
                this.expertCetDate = this.expertCetDate + " 00:00:00";
            } else if (expertCetDate.length() == 19) {
                this.expertCetDate = this.expertCetDate.substring(0, 10);
            }
        }
        return this.expertCetDate;
    }

    /**
     * 简介
     */
    private String expertCetDetail;

    public void setExpertCetDetail(String expertCetDetail) {
        this.expertCetDetail = expertCetDetail;
    }

    public String getExpertCetDetail() {
        return this.expertCetDetail;
    }

    /**
     * 证书地址
     */
    private String expertCetPath;

    public void setExpertCetPath(String expertCetPath) {
        this.expertCetPath = expertCetPath;
    }

    public String getExpertCetPath() {
        return this.expertCetPath;
    }

}