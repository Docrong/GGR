package com.boco.eoms.partdata.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:MSCID码号管理
 * </p>
 * <p>
 * Description:MSCID码号管理
 * </p>
 * <p>
 * Mon Jul 05 09:11:48 CST 2010
 * </p>
 *
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.6
 */
public class TawpartMscidForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 前四位字冠信息
     */
    private String headNumber;

    public void setHeadNumber(String headNumber) {
        this.headNumber = headNumber;
    }

    public String getHeadNumber() {
        return this.headNumber;
    }

    /**
     * M0
     */
    private String numberM0;

    public void setNumberM0(String numberM0) {
        this.numberM0 = numberM0;
    }

    public String getNumberM0() {
        return this.numberM0;
    }

    /**
     * M1
     */
    private String numberM1;

    public void setNumberM1(String numberM1) {
        this.numberM1 = numberM1;
    }

    public String getNumberM1() {
        return this.numberM1;
    }

    /**
     * M2（省内自己分配）
     */
    private String numberM2;

    public void setNumberM2(String numberM2) {
        this.numberM2 = numberM2;
    }

    public String getNumberM2() {
        return this.numberM2;
    }

    /**
     * 整体显示（HEADNUMBER+M0+M1+M2）
     */
    private String numberFree;

    public void setNumberFree(String numberFree) {
        this.numberFree = numberFree;
    }

    public String getNumberFree() {
        return this.numberFree;
    }

    /**
     * 用户设备类型
     */
    private String equipmentType;

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType() {
        return this.equipmentType;
    }

    /**
     * 用户设备名称
     */
    private String equipmentName;

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName() {
        return this.equipmentName;
    }

    /**
     * 是否分配
     */
    private String distributed = "0";

    public void setDistributed(String distributed) {
        this.distributed = distributed;
    }

    public String getDistributed() {
        return this.distributed;
    }

    /**
     * 分配地市ID
     */
    private String areaId;

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    /**
     * 分配地市名称
     */
    private String areaName;

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return this.areaName;
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

    /**
     * 分配人
     */
    private String assigner;

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }

    public String getAssigner() {
        return this.assigner;
    }

    /**
     * 分配时间
     */
    private String assignTime;

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public String getAssignTime() {
        return this.assignTime;
    }

    /**
     * 城市
     */
    private String cityname;

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCityname() {
        return this.cityname;
    }

    /**
     * 信令点编码
     */
    private String signalname;

    public void setSignalname(String signalname) {
        this.signalname = signalname;
    }

    public String getSignalname() {
        return this.signalname;
    }

    /**
     * 设备工厂
     */
    private String equipmentfactory;

    public void setEquipmentfactory(String equipmentfactory) {
        this.equipmentfactory = equipmentfactory;
    }

    public String getEquipmentfactory() {
        return this.equipmentfactory;
    }

}