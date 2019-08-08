
package com.boco.eoms.sheet.businessimplementyy.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="businessimplementyyLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="businessimplementyylink"
 */
public class BusinessImplementYYLink extends BaseLink {

    //	签收时间
    private Date linkAppliedDate;
    //	是否签收
    private String linkIsAppled;
    //	不签收意见
    private String linkNoAppledOpinition;
    //	网络实施方案内容
    private String linkNetImplContext;
    //	集团产品编码
    private String linkGoupeProductCode;
    //	处理方式
    private String linkDealMethod;
    //	处理意见
    private String linkDealOpinition;


    //	信令方式
    private String linkInfoMethod;
    //	信令点编码
    private String linkInfoPointCode;
    //	信令协议
    private String linkInfoProtocol;
    //	退回原因分类
    private String linkBackResonType;
    //	传输电路编号
    private String linkCircuitCode;

    //	业务接入网元
    private String linkBusiInputNet;
    //	中继端口数量
    private String linkPointNumber;
    //	信令端口数量
    private String linkInfoPointNumber;
    //	数据是否完成
    private String linkIsDataOk;
    //	数据核查人
    private String linkDateAuditPeron;
    //	数据制作结果
    private String linkDateMakeResult;
    //	处理时间
    private Date linkDealDate;
    //	满意度
    private String linkIsOk;
    //	是否满足需求
    private String linkIsOkNeed;
    //	不满足原因
    private String linkNoOkResson;

    public Date getLinkAppliedDate() {
        return linkAppliedDate;
    }

    public void setLinkAppliedDate(Date linkAppliedDate) {
        this.linkAppliedDate = linkAppliedDate;
    }

    public String getLinkBackResonType() {
        return linkBackResonType;
    }

    public void setLinkBackResonType(String linkBackResonType) {
        this.linkBackResonType = linkBackResonType;
    }

    public String getLinkBusiInputNet() {
        return linkBusiInputNet;
    }

    public void setLinkBusiInputNet(String linkBusiInputNet) {
        this.linkBusiInputNet = linkBusiInputNet;
    }

    public String getLinkCircuitCode() {
        return linkCircuitCode;
    }

    public void setLinkCircuitCode(String linkCircuitCode) {
        this.linkCircuitCode = linkCircuitCode;
    }

    public String getLinkDateAuditPeron() {
        return linkDateAuditPeron;
    }

    public void setLinkDateAuditPeron(String linkDateAuditPeron) {
        this.linkDateAuditPeron = linkDateAuditPeron;
    }

    public String getLinkDateMakeResult() {
        return linkDateMakeResult;
    }

    public void setLinkDateMakeResult(String linkDateMakeResult) {
        this.linkDateMakeResult = linkDateMakeResult;
    }

    public Date getLinkDealDate() {
        return linkDealDate;
    }

    public void setLinkDealDate(Date linkDealDate) {
        this.linkDealDate = linkDealDate;
    }

    public String getLinkDealMethod() {
        return linkDealMethod;
    }

    public void setLinkDealMethod(String linkDealMethod) {
        this.linkDealMethod = linkDealMethod;
    }

    public String getLinkDealOpinition() {
        return linkDealOpinition;
    }

    public void setLinkDealOpinition(String linkDealOpinition) {
        this.linkDealOpinition = linkDealOpinition;
    }

    public String getLinkGoupeProductCode() {
        return linkGoupeProductCode;
    }

    public void setLinkGoupeProductCode(String linkGoupeProductCode) {
        this.linkGoupeProductCode = linkGoupeProductCode;
    }

    public String getLinkInfoMethod() {
        return linkInfoMethod;
    }

    public void setLinkInfoMethod(String linkInfoMethod) {
        this.linkInfoMethod = linkInfoMethod;
    }

    public String getLinkInfoPointCode() {
        return linkInfoPointCode;
    }

    public void setLinkInfoPointCode(String linkInfoPointCode) {
        this.linkInfoPointCode = linkInfoPointCode;
    }

    public String getLinkInfoPointNumber() {
        return linkInfoPointNumber;
    }

    public void setLinkInfoPointNumber(String linkInfoPointNumber) {
        this.linkInfoPointNumber = linkInfoPointNumber;
    }

    public String getLinkInfoProtocol() {
        return linkInfoProtocol;
    }

    public void setLinkInfoProtocol(String linkInfoProtocol) {
        this.linkInfoProtocol = linkInfoProtocol;
    }

    public String getLinkIsAppled() {
        return linkIsAppled;
    }

    public void setLinkIsAppled(String linkIsAppled) {
        this.linkIsAppled = linkIsAppled;
    }

    public String getLinkIsDataOk() {
        return linkIsDataOk;
    }

    public void setLinkIsDataOk(String linkIsDataOk) {
        this.linkIsDataOk = linkIsDataOk;
    }

    public String getLinkIsOk() {
        return linkIsOk;
    }

    public void setLinkIsOk(String linkIsOk) {
        this.linkIsOk = linkIsOk;
    }

    public String getLinkIsOkNeed() {
        return linkIsOkNeed;
    }

    public void setLinkIsOkNeed(String linkIsOkNeed) {
        this.linkIsOkNeed = linkIsOkNeed;
    }

    public String getLinkNetImplContext() {
        return linkNetImplContext;
    }

    public void setLinkNetImplContext(String linkNetImplContext) {
        this.linkNetImplContext = linkNetImplContext;
    }

    public String getLinkNoAppledOpinition() {
        return linkNoAppledOpinition;
    }

    public void setLinkNoAppledOpinition(String linkNoAppledOpinition) {
        this.linkNoAppledOpinition = linkNoAppledOpinition;
    }

    public String getLinkNoOkResson() {
        return linkNoOkResson;
    }

    public void setLinkNoOkResson(String linkNoOkResson) {
        this.linkNoOkResson = linkNoOkResson;
    }

    public String getLinkPointNumber() {
        return linkPointNumber;
    }

    public void setLinkPointNumber(String linkPointNumber) {
        this.linkPointNumber = linkPointNumber;
    }


}
