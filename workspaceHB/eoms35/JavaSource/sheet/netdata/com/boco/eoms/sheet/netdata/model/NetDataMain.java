
package com.boco.eoms.sheet.netdata.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="NetDataMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="NetDatamain"
 */
public class NetDataMain extends BaseMain {

    /**
     * @dicttype
     */
    private java.lang.String mainNetTypeOne;
    /**
     * @dicttype
     */
    private java.lang.String mainNetTypeTwo;

    /**
     * @dicttype
     */
    private java.lang.String mainNetTypeThree;

    /**
     * @dicttype
     */
    private java.lang.String mainIsSecurity;

    /**
     * @dicttype
     */
    private java.lang.String mainIsConnect;

    /**
     * @dicttype
     */
    private java.lang.String mainFactory;

    /**
     * @textarea
     */
    private java.lang.String mainCellInfo;

    /**
     * @dicttype
     */
    private java.lang.String mainChangeSource;

    /**
     * @texttype
     */
    private java.lang.String mainParentSheetId;

    /**
     * @dicttype
     */
    private java.lang.String mainIsNeedDesign;

    /**
     * @texttype
     */
    private java.lang.String mainDesignId;


    /**
     * @texttype
     */
    private java.lang.String mainNetDataCommont;

    /**
     * @texttype
     */
    private java.lang.Integer mainRejectTimes;

    /**
     * @texttype
     */
    private java.lang.Integer mainIfrecord;

    /**
     * @texttype
     */
    private java.util.Date mainExecuteEndDate;

    /**
     * @texttype
     */
    private java.lang.String mainAssortSpeciality;

    /**
     * @texttype
     */
    private java.lang.String mainIfDemonstrateCase;

    /**
     * @texttype
     */
    private java.lang.String mainCaseKeywords;

    //方案制定步骤的字段
    private java.util.Date linkCompleteLimitTime;
    private java.lang.String linkDesignKey;
    private java.lang.String linkDesignComment;
    private java.lang.String linkInvolvedProvince;
    private java.lang.String linkInvolvedCity;
    private java.lang.String linkRiskEstimate;
    private java.lang.String linkEffectAnalyse;
    private java.lang.String firstNodeAccessories;

    /**
     * @return the mainNetDataCommont
     */
    public java.lang.String getMainNetDataCommont() {
        return mainNetDataCommont;
    }

    /**
     * @param mainNetDataCommont the mainNetDataCommont to set
     */
    public void setMainNetDataCommont(java.lang.String mainNetDataCommont) {
        this.mainNetDataCommont = mainNetDataCommont;
    }

    /**
     * @return the mainParentSheetId
     */
    public java.lang.String getMainParentSheetId() {
        return mainParentSheetId;
    }

    /**
     * @param mainParentSheetId the mainParentSheetId to set
     */
    public void setMainParentSheetId(java.lang.String mainParentSheetId) {
        this.mainParentSheetId = mainParentSheetId;
    }

    /**
     * @return the mainRejectTimes
     */
    public java.lang.Integer getMainRejectTimes() {
        return mainRejectTimes;
    }

    /**
     * @param mainRejectTimes the mainRejectTimes to set
     */
    public void setMainRejectTimes(java.lang.Integer mainRejectTimes) {
        this.mainRejectTimes = mainRejectTimes;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainIsSecurity() {
        return mainIsSecurity;
    }

    public void setMainIsSecurity(java.lang.String mainIsSecurity) {
        this.mainIsSecurity = mainIsSecurity;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainIsConnect() {
        return mainIsConnect;
    }

    public void setMainIsConnect(java.lang.String mainIsConnect) {
        this.mainIsConnect = mainIsConnect;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainFactory() {
        return mainFactory;
    }

    public void setMainFactory(java.lang.String mainFactory) {
        this.mainFactory = mainFactory;
    }

    /**
     * @return
     * @hibernate.property value="255"
     * @eoms.show
     */
    public java.lang.String getMainCellInfo() {
        return mainCellInfo;
    }

    public void setMainCellInfo(java.lang.String mainCellInfo) {
        this.mainCellInfo = mainCellInfo;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainChangeSource() {
        return mainChangeSource;
    }

    public void setMainChangeSource(java.lang.String mainChangeSource) {
        this.mainChangeSource = mainChangeSource;
    }


    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainIsNeedDesign() {
        return mainIsNeedDesign;
    }

    public void setMainIsNeedDesign(java.lang.String mainIsNeedDesign) {
        this.mainIsNeedDesign = mainIsNeedDesign;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainDesignId() {
        return mainDesignId;
    }

    public void setMainDesignId(java.lang.String mainDesignId) {
        this.mainDesignId = mainDesignId;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainNetTypeOne() {
        return mainNetTypeOne;
    }

    public void setMainNetTypeOne(java.lang.String mainNetTypeOne) {
        this.mainNetTypeOne = mainNetTypeOne;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */

    public java.lang.String getMainNetTypeThree() {
        return mainNetTypeThree;
    }

    public void setMainNetTypeThree(java.lang.String mainNetTypeThree) {
        this.mainNetTypeThree = mainNetTypeThree;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */

    public java.lang.String getMainNetTypeTwo() {
        return mainNetTypeTwo;
    }

    public void setMainNetTypeTwo(java.lang.String mainNetTypeTwo) {
        this.mainNetTypeTwo = mainNetTypeTwo;
    }

    /**
     * @return
     * @hibernate.property
     * @eoms.show
     */
    public java.util.Date getMainExecuteEndDate() {
        return mainExecuteEndDate;
    }

    public void setMainExecuteEndDate(java.util.Date mainExecuteEndDate) {
        this.mainExecuteEndDate = mainExecuteEndDate;
    }

    /**
     * @return
     * @hibernate.property
     * @eoms.show
     */
    public java.lang.Integer getMainIfrecord() {
        return mainIfrecord;
    }

    public void setMainIfrecord(java.lang.Integer mainIfrecord) {
        this.mainIfrecord = mainIfrecord;
    }

    public java.lang.String getMainAssortSpeciality() {
        return mainAssortSpeciality;
    }

    public void setMainAssortSpeciality(java.lang.String mainAssortSpeciality) {
        this.mainAssortSpeciality = mainAssortSpeciality;
    }

    public java.lang.String getMainCaseKeywords() {
        return mainCaseKeywords;
    }

    public void setMainCaseKeywords(java.lang.String mainCaseKeywords) {
        this.mainCaseKeywords = mainCaseKeywords;
    }

    public java.lang.String getMainIfDemonstrateCase() {
        return mainIfDemonstrateCase;
    }

    public void setMainIfDemonstrateCase(java.lang.String mainIfDemonstrateCase) {
        this.mainIfDemonstrateCase = mainIfDemonstrateCase;
    }

    public java.lang.String getFirstNodeAccessories() {
        return firstNodeAccessories;
    }

    public void setFirstNodeAccessories(java.lang.String firstNodeAccessories) {
        this.firstNodeAccessories = firstNodeAccessories;
    }

    public java.util.Date getLinkCompleteLimitTime() {
        return linkCompleteLimitTime;
    }

    public void setLinkCompleteLimitTime(java.util.Date linkCompleteLimitTime) {
        this.linkCompleteLimitTime = linkCompleteLimitTime;
    }

    public java.lang.String getLinkDesignComment() {
        return linkDesignComment;
    }

    public void setLinkDesignComment(java.lang.String linkDesignComment) {
        this.linkDesignComment = linkDesignComment;
    }

    public java.lang.String getLinkDesignKey() {
        return linkDesignKey;
    }

    public void setLinkDesignKey(java.lang.String linkDesignKey) {
        this.linkDesignKey = linkDesignKey;
    }

    public java.lang.String getLinkEffectAnalyse() {
        return linkEffectAnalyse;
    }

    public void setLinkEffectAnalyse(java.lang.String linkEffectAnalyse) {
        this.linkEffectAnalyse = linkEffectAnalyse;
    }

    public java.lang.String getLinkInvolvedCity() {
        return linkInvolvedCity;
    }

    public void setLinkInvolvedCity(java.lang.String linkInvolvedCity) {
        this.linkInvolvedCity = linkInvolvedCity;
    }

    public java.lang.String getLinkInvolvedProvince() {
        return linkInvolvedProvince;
    }

    public void setLinkInvolvedProvince(java.lang.String linkInvolvedProvince) {
        this.linkInvolvedProvince = linkInvolvedProvince;
    }

    public java.lang.String getLinkRiskEstimate() {
        return linkRiskEstimate;
    }

    public void setLinkRiskEstimate(java.lang.String linkRiskEstimate) {
        this.linkRiskEstimate = linkRiskEstimate;
    }


}
