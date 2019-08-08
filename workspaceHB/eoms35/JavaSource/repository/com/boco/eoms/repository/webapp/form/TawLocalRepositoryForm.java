package com.boco.eoms.repository.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:tawLocalRepository
 * </p>
 * <p>
 * Description:tawLocalRepository
 * </p>
 * <p>
 * Fri Oct 30 09:14:26 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 李锋
 * @moudle.getVersion() 1.0
 */
public class TawLocalRepositoryForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    private String driverTpye;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * province
     */
    private java.lang.String province;

    public void setProvince(java.lang.String province) {
        this.province = province;
    }

    public java.lang.String getProvince() {
        return this.province;
    }

    /**
     * city
     */
    private java.lang.String city;

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public java.lang.String getCity() {
        return this.city;
    }

    /**
     * net
     */
    private java.lang.String net;

    public void setNet(java.lang.String net) {
        this.net = net;
    }

    public java.lang.String getNet() {
        return this.net;
    }

    /**
     * netType
     */
    private java.lang.String netType;

    public void setNetType(java.lang.String netType) {
        this.netType = netType;
    }

    public java.lang.String getNetType() {
        return this.netType;
    }

    /**
     * company
     */
    private java.lang.String company;

    public void setCompany(java.lang.String company) {
        this.company = company;
    }

    public java.lang.String getCompany() {
        return this.company;
    }

    /**
     * netModale
     */
    private java.lang.String netModale;

    public void setNetModale(java.lang.String netModale) {
        this.netModale = netModale;
    }

    public java.lang.String getNetModale() {
        return this.netModale;
    }

    /**
     * hardwareRepository
     */
    private java.lang.String hardwareRepository;

    public void setHardwareRepository(java.lang.String hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public java.lang.String getHardwareRepository() {
        return this.hardwareRepository;
    }

    /**
     * softwareRepository
     */
    private java.lang.String softwareRepository;

    public void setSoftwareRepository(java.lang.String softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    public java.lang.String getSoftwareRepository() {
        return this.softwareRepository;
    }

    /**
     * patch
     */
    private java.lang.String patch;

    public void setPatch(java.lang.String patch) {
        this.patch = patch;
    }

    public java.lang.String getPatch() {
        return this.patch;
    }

    /**
     * networkTime
     */
    private java.lang.String networkTime;

    public void setNetworkTime(java.lang.String networkTime) {
        this.networkTime = networkTime;
    }

    public java.lang.String getNetworkTime() {
        return this.networkTime;
    }

    /**
     * networkTimeFrom
     */
    private java.lang.String networkTimeFrom;

    public void setNetworkTimeFrom(java.lang.String networkTimeFrom) {
        this.networkTimeFrom = networkTimeFrom;
    }

    public java.lang.String getNetworkTimeFrom() {
        return this.networkTimeFrom;
    }

    /**
     * networkTimeTo
     */
    private java.lang.String networkTimeTo;

    public void setNetworkTimeTo(java.lang.String networkTimeTo) {
        this.networkTimeTo = networkTimeTo;
    }

    public java.lang.String getNetworkTimeTo() {
        return this.networkTimeTo;
    }

    /**
     * state
     */
    private java.lang.String state;

    public void setState(java.lang.String state) {
        this.state = state;
    }

    public java.lang.String getState() {
        return this.state;
    }

    /**
     * deleted
     */
    private java.lang.String deleted;

    public void setDeleted(java.lang.String deleted) {
        this.deleted = deleted;
    }

    public java.lang.String getDeleted() {
        return this.deleted;
    }

    public String getDriverTpye() {
        return driverTpye;
    }

    public void setDriverTpye(String driverTpye) {
        this.driverTpye = driverTpye;
    }

}