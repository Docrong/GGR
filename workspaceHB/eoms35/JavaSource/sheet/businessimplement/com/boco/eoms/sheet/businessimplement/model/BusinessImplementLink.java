
package com.boco.eoms.sheet.businessimplement.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="businessimplementLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="businessimplementlink"
 */
public class BusinessImplementLink extends BaseLink
{
	
	private String linkAsinger     ;                        
	private String linkAsingConnPhone ;                    
	private String linkAsingDept     ;                    
	private Date linkAsingTime   ;         
	private String linkIsAsingment ;  
	
	private String linkNoOpinition ;                   
	private String linkDealMethod ;                      
	private String linkReturnOpinition ;                 
	private String linkArugmentlevel ;                    
	private Date linkNeedFinishTime ; 
	
	
	private String linkAcceptDept ;                       
	private String linkSenderOpinition ;                 
	private String linkArgument ;                      
    private String linkNetDeptConnPerson ;          
	private String linkNetDeptConnPersonPhone ; 
	
	private String linkProjectDeptPerson ;            
	private String linkProjectDeptPersonPhone ;  
	private String linkDealResourt ;                      
	private String linkDealDescription ;                   
	private String linkTrasitionEleNumber ;  
	
	private String linkGroupAddAndIp ; 
	private String linkRemark ;                             
	                                        
	private String linkDoResourt ;                       
	private String linkEleNeed ;                      
	private String linkStartA ; 
	
	private String linkACompteHome ;            
	private String linkABusinessDDF ;              
	private String linkADevHost ;                  
	private String linkATrasitionDDF ;            
	private String linkScopeTrasitionHost;   
	
	private String linkEndZ ;                      
	private String linkDevType ;                         
	private String linkFailureResson ;                    
                                     
	private String linkDesinResourt ;              
	private String linkDesinType ;  
	
	private String linkIPAddress ;                  
	private String linkSubNetNumber ;                   
	private String linkGetWay ;                             
	private String linkVLAN ;                              
	private String linkEleCallResourt ;          
	                                                 
	                                   
	private String linkGroupUserAddressZone ;   
	private String linkDealDescriptin ;                
	private String linkTestReport ;                   
	private String linkOne    ;
	
	public String getLinkABusinessDDF() {
		return linkABusinessDDF;
	}
	public void setLinkABusinessDDF(String linkABusinessDDF) {
		this.linkABusinessDDF = linkABusinessDDF;
	}
	public String getLinkAcceptDept() {
		return linkAcceptDept;
	}
	public void setLinkAcceptDept(String linkAcceptDept) {
		this.linkAcceptDept = linkAcceptDept;
	}
	public String getLinkACompteHome() {
		return linkACompteHome;
	}
	public void setLinkACompteHome(String linkACompteHome) {
		this.linkACompteHome = linkACompteHome;
	}
	public String getLinkADevHost() {
		return linkADevHost;
	}
	public void setLinkADevHost(String linkADevHost) {
		this.linkADevHost = linkADevHost;
	}
	public String getLinkArgument() {
		return linkArgument;
	}
	public void setLinkArgument(String linkArgument) {
		this.linkArgument = linkArgument;
	}
	public String getLinkArugmentlevel() {
		return linkArugmentlevel;
	}
	public void setLinkArugmentlevel(String linkArugmentlevel) {
		this.linkArugmentlevel = linkArugmentlevel;
	}
	public String getLinkAsingConnPhone() {
		return linkAsingConnPhone;
	}
	public void setLinkAsingConnPhone(String linkAsingConnPhone) {
		this.linkAsingConnPhone = linkAsingConnPhone;
	}
	public String getLinkAsingDept() {
		return linkAsingDept;
	}
	public void setLinkAsingDept(String linkAsingDept) {
		this.linkAsingDept = linkAsingDept;
	}
	public String getLinkAsinger() {
		return linkAsinger;
	}
	public void setLinkAsinger(String linkAsinger) {
		this.linkAsinger = linkAsinger;
	}
	public Date getLinkAsingTime() {
		return linkAsingTime;
	}
	public void setLinkAsingTime(Date linkAsingTime) {
		this.linkAsingTime = linkAsingTime;
	}
	public String getLinkATrasitionDDF() {
		return linkATrasitionDDF;
	}
	public void setLinkATrasitionDDF(String linkATrasitionDDF) {
		this.linkATrasitionDDF = linkATrasitionDDF;
	}
	public String getLinkDealDescriptin() {
		return linkDealDescriptin;
	}
	public void setLinkDealDescriptin(String linkDealDescriptin) {
		this.linkDealDescriptin = linkDealDescriptin;
	}
	public String getLinkDealDescription() {
		return linkDealDescription;
	}
	public void setLinkDealDescription(String linkDealDescription) {
		this.linkDealDescription = linkDealDescription;
	}
	public String getLinkDealMethod() {
		return linkDealMethod;
	}
	public void setLinkDealMethod(String linkDealMethod) {
		this.linkDealMethod = linkDealMethod;
	}
	public String getLinkDealResourt() {
		return linkDealResourt;
	}
	public void setLinkDealResourt(String linkDealResourt) {
		this.linkDealResourt = linkDealResourt;
	}
	public String getLinkDesinResourt() {
		return linkDesinResourt;
	}
	public void setLinkDesinResourt(String linkDesinResourt) {
		this.linkDesinResourt = linkDesinResourt;
	}
	public String getLinkDesinType() {
		return linkDesinType;
	}
	public void setLinkDesinType(String linkDesinType) {
		this.linkDesinType = linkDesinType;
	}
	public String getLinkDevType() {
		return linkDevType;
	}
	public void setLinkDevType(String linkDevType) {
		this.linkDevType = linkDevType;
	}
	public String getLinkDoResourt() {
		return linkDoResourt;
	}
	public void setLinkDoResourt(String linkDoResourt) {
		this.linkDoResourt = linkDoResourt;
	}
	public String getLinkEleCallResourt() {
		return linkEleCallResourt;
	}
	public void setLinkEleCallResourt(String linkEleCallResourt) {
		this.linkEleCallResourt = linkEleCallResourt;
	}
	public String getLinkEleNeed() {
		return linkEleNeed;
	}
	public void setLinkEleNeed(String linkEleNeed) {
		this.linkEleNeed = linkEleNeed;
	}
	public String getLinkEndZ() {
		return linkEndZ;
	}
	public void setLinkEndZ(String linkEndZ) {
		this.linkEndZ = linkEndZ;
	}
	public String getLinkFailureResson() {
		return linkFailureResson;
	}
	public void setLinkFailureResson(String linkFailureResson) {
		this.linkFailureResson = linkFailureResson;
	}
	public String getLinkGetWay() {
		return linkGetWay;
	}
	public void setLinkGetWay(String linkGetWay) {
		this.linkGetWay = linkGetWay;
	}
	public String getLinkGroupAddAndIp() {
		return linkGroupAddAndIp;
	}
	public void setLinkGroupAddAndIp(String linkGroupAddAndIp) {
		this.linkGroupAddAndIp = linkGroupAddAndIp;
	}
	public String getLinkGroupUserAddressZone() {
		return linkGroupUserAddressZone;
	}
	public void setLinkGroupUserAddressZone(String linkGroupUserAddressZone) {
		this.linkGroupUserAddressZone = linkGroupUserAddressZone;
	}
	public String getLinkIPAddress() {
		return linkIPAddress;
	}
	public void setLinkIPAddress(String linkIPAddress) {
		this.linkIPAddress = linkIPAddress;
	}
	public String getLinkIsAsingment() {
		return linkIsAsingment;
	}
	public void setLinkIsAsingment(String linkIsAsingment) {
		this.linkIsAsingment = linkIsAsingment;
	}
	public Date getLinkNeedFinishTime() {
		return linkNeedFinishTime;
	}
	public void setLinkNeedFinishTime(Date linkNeedFinishTime) {
		this.linkNeedFinishTime = linkNeedFinishTime;
	}
	public String getLinkNetDeptConnPerson() {
		return linkNetDeptConnPerson;
	}
	public void setLinkNetDeptConnPerson(String linkNetDeptConnPerson) {
		this.linkNetDeptConnPerson = linkNetDeptConnPerson;
	}
	public String getLinkNetDeptConnPersonPhone() {
		return linkNetDeptConnPersonPhone;
	}
	public void setLinkNetDeptConnPersonPhone(String linkNetDeptConnPersonPhone) {
		this.linkNetDeptConnPersonPhone = linkNetDeptConnPersonPhone;
	}
	public String getLinkNoOpinition() {
		return linkNoOpinition;
	}
	public void setLinkNoOpinition(String linkNoOpinition) {
		this.linkNoOpinition = linkNoOpinition;
	}
	public String getLinkOne() {
		return linkOne;
	}
	public void setLinkOne(String linkOne) {
		this.linkOne = linkOne;
	}
	public String getLinkProjectDeptPerson() {
		return linkProjectDeptPerson;
	}
	public void setLinkProjectDeptPerson(String linkProjectDeptPerson) {
		this.linkProjectDeptPerson = linkProjectDeptPerson;
	}
	public String getLinkProjectDeptPersonPhone() {
		return linkProjectDeptPersonPhone;
	}
	public void setLinkProjectDeptPersonPhone(String linkProjectDeptPersonPhone) {
		this.linkProjectDeptPersonPhone = linkProjectDeptPersonPhone;
	}
	public String getLinkRemark() {
		return linkRemark;
	}
	public void setLinkRemark(String linkRemark) {
		this.linkRemark = linkRemark;
	}
	public String getLinkReturnOpinition() {
		return linkReturnOpinition;
	}
	public void setLinkReturnOpinition(String linkReturnOpinition) {
		this.linkReturnOpinition = linkReturnOpinition;
	}
	public String getLinkScopeTrasitionHost() {
		return linkScopeTrasitionHost;
	}
	public void setLinkScopeTrasitionHost(String linkScopeTrasitionHost) {
		this.linkScopeTrasitionHost = linkScopeTrasitionHost;
	}
	public String getLinkSenderOpinition() {
		return linkSenderOpinition;
	}
	public void setLinkSenderOpinition(String linkSenderOpinition) {
		this.linkSenderOpinition = linkSenderOpinition;
	}
	public String getLinkStartA() {
		return linkStartA;
	}
	public void setLinkStartA(String linkStartA) {
		this.linkStartA = linkStartA;
	}
	public String getLinkSubNetNumber() {
		return linkSubNetNumber;
	}
	public void setLinkSubNetNumber(String linkSubNetNumber) {
		this.linkSubNetNumber = linkSubNetNumber;
	}
	public String getLinkTestReport() {
		return linkTestReport;
	}
	public void setLinkTestReport(String linkTestReport) {
		this.linkTestReport = linkTestReport;
	}
	public String getLinkTrasitionEleNumber() {
		return linkTrasitionEleNumber;
	}
	public void setLinkTrasitionEleNumber(String linkTrasitionEleNumber) {
		this.linkTrasitionEleNumber = linkTrasitionEleNumber;
	}
	public String getLinkVLAN() {
		return linkVLAN;
	}
	public void setLinkVLAN(String linkVLAN) {
		this.linkVLAN = linkVLAN;
	}                                      


}
