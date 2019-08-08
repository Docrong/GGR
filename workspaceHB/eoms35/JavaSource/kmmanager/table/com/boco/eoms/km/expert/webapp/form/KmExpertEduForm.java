package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:教育背景
 * </p>
 * <p>
 * Description:专家教育背景
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 */
public class KmExpertEduForm extends BaseForm implements java.io.Serializable {

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
     * 毕业院校
     */
    private String expertEduSch;

    public void setExpertEduSch(String expertEduSch) {
        this.expertEduSch = expertEduSch;
    }

    public String getExpertEduSch() {
        return this.expertEduSch;
    }

    /**
     * 学历
     */
    private String expertEduEdu;

    public void setExpertEduEdu(String expertEduEdu) {
        this.expertEduEdu = expertEduEdu;
    }

    public String getExpertEduEdu() {
        return this.expertEduEdu;
    }

    /**
     * 开始时间
     */
    private String expertEduStart;

    public void setExpertEduStart(String expertEduStart) {
        this.expertEduStart = expertEduStart;
    }

    public String getExpertEduStart() {
        if (this.expertEduStart != null) {
            if (expertEduStart.length() == 10) {
                this.expertEduStart = this.expertEduStart + " 00:00:00";
            } else if (expertEduStart.length() == 19) {
                this.expertEduStart = this.expertEduStart.substring(0, 10);
            }
        }
        return expertEduStart;
    }

    /**
     * 结束时间
     */
    private String expertEduEnd;

    public void setExpertEduEnd(String expertEduEnd) {
        this.expertEduEnd = expertEduEnd;
    }

    public String getExpertEduEnd() {
        if (this.expertEduEnd != null) {
            if (expertEduEnd.length() == 10) {
                this.expertEduEnd = this.expertEduEnd + " 00:00:00";
            } else if (expertEduEnd.length() == 19) {
                this.expertEduEnd = this.expertEduEnd.substring(0, 10);
            }
        }
        return expertEduEnd;
    }

    /**
     * 专业类别
     */
    private String expertEduPro;

    public void setExpertEduPro(String expertEduPro) {
        this.expertEduPro = expertEduPro;
    }

    public String getExpertEduPro() {
        return this.expertEduPro;
    }

    /**
     * 专业名称
     */
    private String expertEduProName;

    public void setExpertEduProName(String expertEduProName) {
        this.expertEduProName = expertEduProName;
    }

    public String getExpertEduProName() {
        return this.expertEduProName;
    }

    /**
     * 城市
     */
    private String expertEduCity;

    public void setExpertEduCity(String expertEduCity) {
        this.expertEduCity = expertEduCity;
    }

    public String getExpertEduCity() {
        return this.expertEduCity;
    }

    /**
     * 专业说明
     */
    private String expertEduDes;

    public void setExpertEduDes(String expertEduDes) {
        this.expertEduDes = expertEduDes;
    }

    public String getExpertEduDes() {
        return this.expertEduDes;
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

}