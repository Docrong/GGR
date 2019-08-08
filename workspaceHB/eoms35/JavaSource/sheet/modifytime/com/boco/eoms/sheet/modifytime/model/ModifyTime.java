package com.boco.eoms.sheet.modifytime.model;


/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="ModifyTime.java.do"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ModifyTime"
 */
public class ModifyTime {

    private String id;

    private String beginTime;

    private String endTime;

    private String type;

    private String specialtyOne;

    private String specialtyTwo;

    private String specialtyThree;

    private String local;

    private String metNet;

    private String functionary;

    private String title;

    private String introduction;

    private String remarks;

    private String deleted;

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="变更开始时间"
     */
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="变更结束时间"
     */
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="负责人"
     */
    public String getFunctionary() {
        return functionary;
    }

    public void setFunctionary(String functionary) {
        this.functionary = functionary;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="255"
     * @eoms.show
     * @eoms.cn name="简介"
     */
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="地域"
     */
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="网元"
     */
    public String getMetNet() {
        return metNet;
    }

    public void setMetNet(String metNet) {
        this.metNet = metNet;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="专业"
     */
    public String getSpecialtyOne() {
        return specialtyOne;
    }

    public void setSpecialtyOne(String specialtyOne) {
        this.specialtyOne = specialtyOne;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="专业1"
     */
    public String getSpecialtyTwo() {
        return specialtyTwo;
    }

    public void setSpecialtyTwo(String specialtyTwo) {
        this.specialtyTwo = specialtyTwo;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="专业2"
     */
    public String getSpecialtyThree() {
        return specialtyThree;
    }

    public void setSpecialtyThree(String specialtyThree) {
        this.specialtyThree = specialtyThree;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="变更主题"
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="变更类别"
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="255"
     * @eoms.show
     * @eoms.cn name="备注"
     */
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="删除标志"
     */

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        deleted = deleted;
    }
}
