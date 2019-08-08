package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:工作经验
 * </p>
 * <p>
 * Description:工作经验
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 */
public class KmExpertExpForm extends BaseForm implements java.io.Serializable {

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
     * 工作经验
     */
    private String expertStart;

    public void setExpertStart(String expertStart) {
        this.expertStart = expertStart;
    }

    public String getExpertStart() {
        if (this.expertStart != null) {
            if (expertStart.length() == 10) {
                this.expertStart = this.expertStart + " 00:00:00";
            } else if (expertStart.length() == 19) {
                this.expertStart = this.expertStart.substring(0, 10);
            }
        }
        return this.expertStart;
    }

    /**
     * 结束时间
     */
    private String expertEnd;

    public void setExpertEnd(String expertEnd) {
        this.expertEnd = expertEnd;
    }

    public String getExpertEnd() {
        if (this.expertEnd != null) {
            if (expertEnd.length() == 10) {
                this.expertEnd = this.expertEnd + " 00:00:00";
            } else if (expertEnd.length() == 19) {
                this.expertEnd = this.expertEnd.substring(0, 10);
            }
        }
        return this.expertEnd;
    }

    /**
     * 职位名称
     */
    private String expertPosition;

    public void setExpertPosition(String expertPosition) {
        this.expertPosition = expertPosition;
    }

    public String getExpertPosition() {
        return this.expertPosition;
    }

    /**
     * 工作地点
     */
    private String expertAddress;

    public void setExpertAddress(String expertAddress) {
        this.expertAddress = expertAddress;
    }

    public String getExpertAddress() {
        return this.expertAddress;
    }

    /**
     * 工作职责
     */
    private String expertResponsiblitily;

    public void setExpertResponsiblitily(String expertResponsiblitily) {
        this.expertResponsiblitily = expertResponsiblitily;
    }

    public String getExpertResponsiblitily() {
        return this.expertResponsiblitily;
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
     * 公司名称
     */
    private String expertCompany;

    public void setExpertCompany(String expertCompany) {
        this.expertCompany = expertCompany;
    }

    public String getExpertCompany() {
        return this.expertCompany;
    }

}