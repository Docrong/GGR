package com.boco.eoms.partdata.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:LAC码号地市分配
 * </p>
 * <p>
 * Description:LAC码号地市分配
 * </p>
 * <p>
 * Mon Jul 12 09:21:06 CST 2010
 * </p>
 *
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.6
 */
public class TawpartlacRangeForm extends BaseForm implements java.io.Serializable {

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
     * 地市ID
     */
    private String areaId;

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    /**
     * 地域名称
     */
    private String areaName;

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return this.areaName;
    }

    /**
     * L1L2
     */
    private String loneLtwo;

    public void setLoneLtwo(String loneLtwo) {
        this.loneLtwo = loneLtwo;
    }

    public String getLoneLtwo() {
        return this.loneLtwo;
    }

    /**
     * 十进制开始
     */
    private String tenStart;

    public void setTenStart(String tenStart) {
        this.tenStart = tenStart;
    }

    public String getTenStart() {
        return this.tenStart;
    }

    /**
     * 十进制结束
     */
    private String tenEnd;

    public void setTenEnd(String tenEnd) {
        this.tenEnd = tenEnd;
    }

    public String getTenEnd() {
        return this.tenEnd;
    }

    /**
     * 十六进制开始
     */
    private String sixtStart;

    public void setSixtStart(String sixtStart) {
        this.sixtStart = sixtStart;
    }

    public String getSixtStart() {
        return this.sixtStart;
    }

    /**
     * 十六进制结束
     */
    private String sixtEnd;

    public void setSixtEnd(String sixtEnd) {
        this.sixtEnd = sixtEnd;
    }

    public String getSixtEnd() {
        return this.sixtEnd;
    }

    /**
     * 区码
     */
    private String areaNumber;

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getAreaNumber() {
        return this.areaNumber;
    }

    /**
     * 创建人
     */
    private String creator;

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

    /**
     * 创建时间
     */
    private String createTime;

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }


}