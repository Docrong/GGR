
package com.boco.eoms.sheet.businessimplementyy.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="businessimplementyyMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="businessimplementyymain"
 */
public class BusinessImplementYYMain extends BaseMain {
    private String orderSheetId;

    private String parentPhaseName;

    private String mainGroupConnPerson;//网络部门联系人
    private String mainGroupConnType;//网络部门联系人电话

    private String mainSendSheetModule;     //
    private String mainNumber;                    //处理结果
    private String mainInterfaceAConnPerson;    //网络部门联系人
    private String mainAConnPersonPhone;        //网络部门联系人电话
    private String mainBusinessConnPerson;        //工程部门联系人
    private String mainBusinessConnPersonPhone;    //工程部门联系人电话

    private String mainTechConnPerson;        //备用


    public String getMainAConnPersonPhone() {
        return mainAConnPersonPhone;
    }

    public void setMainAConnPersonPhone(String mainAConnPersonPhone) {
        this.mainAConnPersonPhone = mainAConnPersonPhone;
    }

    public String getMainBusinessConnPerson() {
        return mainBusinessConnPerson;
    }

    public void setMainBusinessConnPerson(String mainBusinessConnPerson) {
        this.mainBusinessConnPerson = mainBusinessConnPerson;
    }

    public String getMainBusinessConnPersonPhone() {
        return mainBusinessConnPersonPhone;
    }

    public void setMainBusinessConnPersonPhone(String mainBusinessConnPersonPhone) {
        this.mainBusinessConnPersonPhone = mainBusinessConnPersonPhone;
    }

    public String getMainGroupConnPerson() {
        return mainGroupConnPerson;
    }

    public void setMainGroupConnPerson(String mainGroupConnPerson) {
        this.mainGroupConnPerson = mainGroupConnPerson;
    }

    public String getMainGroupConnType() {
        return mainGroupConnType;
    }

    public void setMainGroupConnType(String mainGroupConnType) {
        this.mainGroupConnType = mainGroupConnType;
    }

    public String getMainInterfaceAConnPerson() {
        return mainInterfaceAConnPerson;
    }

    public void setMainInterfaceAConnPerson(String mainInterfaceAConnPerson) {
        this.mainInterfaceAConnPerson = mainInterfaceAConnPerson;
    }

    public String getMainNumber() {
        return mainNumber;
    }

    public void setMainNumber(String mainNumber) {
        this.mainNumber = mainNumber;
    }

    public String getMainSendSheetModule() {
        return mainSendSheetModule;
    }

    public void setMainSendSheetModule(String mainSendSheetModule) {
        this.mainSendSheetModule = mainSendSheetModule;
    }

    public String getMainTechConnPerson() {
        return mainTechConnPerson;
    }

    public void setMainTechConnPerson(String mainTechConnPerson) {
        this.mainTechConnPerson = mainTechConnPerson;
    }

    public String getParentPhaseName() {
        return parentPhaseName;
    }

    public void setParentPhaseName(String parentPhaseName) {
        this.parentPhaseName = parentPhaseName;
    }

    public String getOrderSheetId() {
        return orderSheetId;
    }

    public void setOrderSheetId(String orderSheetId) {
        this.orderSheetId = orderSheetId;
    }

}
