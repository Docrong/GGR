package com.boco.eoms.partdata.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:LAC的BSC分配
 * </p>
 * <p>
 * Description:LAC的BSC分配
 * </p>
 * <p>
 * Mon Jul 12 17:27:34 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartLacBsc extends BaseObject {

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
     * 地市名称
     */
    private String areaName;

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return this.areaName;
    }

    /**
     * bsc地市
     */
    private String bsc;

    public void setBsc(String bsc) {
        this.bsc = bsc;
    }

    public String getBsc() {
        return this.bsc;
    }

    /**
     * LAC
     */
    private String lac;

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getLac() {
        return this.lac;
    }

    /**
     * MSCID
     */
    private String mscId;

    public void setMscId(String mscId) {
        this.mscId = mscId;
    }

    public String getMscId() {
        return this.mscId;
    }

    /**
     * MSCNAME
     */
    private String mscName;

    public void setMscName(String mscName) {
        this.mscName = mscName;
    }

    public String getMscName() {
        return this.mscName;
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

    private String partRangeId;


    public String getPartRangeId() {
        return partRangeId;
    }

    public void setPartRangeId(String partRangeId) {
        this.partRangeId = partRangeId;
    }

    public boolean equals(Object o) {
        if (o instanceof TawpartLacBsc) {
            TawpartLacBsc tawpartLacBsc = (TawpartLacBsc) o;
            if (this.id != null || this.id.equals(tawpartLacBsc.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}