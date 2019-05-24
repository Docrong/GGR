package com.boco.eoms.testcard.model;

import com.boco.eoms.testcard.dao.TawEventDicDAO;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl;

public class TawTestcardApply {

	TawEventDicDAO DicDAO = new TawEventDicDAO();

	ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
	.getInstance().getBean("itawSystemUserManager");

	ID2NameService dictMgr = (ID2NameService) ApplicationContextHolder
	.getInstance().getBean("id2nameService");

	private int id;

	private String userId;

	private String username;
	
	private String deptId;

	private String formName;

	private String cardtype;

	private String cardtypename;

	private String cardpackage;

	private String cardpackagename;

	private String leaveid;

	private String leaveidname;

	private String applyreason;

	private String insertTime;

	private String status;

	private String statusname;

	private String auditJson;
	
	private int taskId;
	
	private String auditOrgName;

	public String getAuditOrgName() {
		return auditOrgName;
	}

	public void setAuditOrgName(String auditOrgName) {
		this.auditOrgName = auditOrgName;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getAuditJson() {
		return auditJson;
	}

	public void setAuditJson(String auditJson) {
		this.auditJson = auditJson;
	}

	public String getApplyreason() {
		return applyreason;
	}

	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}

	public String getCardpackage() {
		return cardpackage;
	}

	public void setCardpackage(String cardpackage) {
		this.cardpackage = cardpackage;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getLeaveid() {
		return leaveid;
	}

	public void setLeaveid(String leaveid) {
		this.leaveid = leaveid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardpackagename() {
		try {
			if (!StaticMethod.null2String(cardpackage).equals("")) {
				this.cardpackagename = DicDAO.findName(StaticMethod
						.null2String(cardpackage));
			}else{
				this.cardpackagename="";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardpackagename;
	}

	public void setCardpackagename(String cardpackagename) {
		this.cardpackagename = cardpackagename;
	}

	public String getCardtypename() {
		if(StaticMethod.null2String(this.cardtype).equals("0")){
			this.cardtypename="国际出访卡";
		}
		if(StaticMethod.null2String(this.cardtype).equals("1")){
			this.cardtypename="国际来访卡";
		}
		if(StaticMethod.null2String(this.cardtype).equals("2")){
			this.cardtypename="省际来访卡";
		}
		if(StaticMethod.null2String(this.cardtype).equals("3")){
			this.cardtypename="省际出访卡";
		}
		if(StaticMethod.null2String(this.cardtype).equals("4")){
			this.cardtypename="本地测试卡";
		}
		if(StaticMethod.null2String(this.cardtype).equals("5")){
			this.cardtypename="省内来访卡";
		}
		if(StaticMethod.null2String(this.cardtype).equals("6")){
			this.cardtypename="省内出访卡";
		}
		return cardtypename;
	}

	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}

	public String getStatusname() {
    	if(StaticMethod.null2String(this.status).equals("9")){
    		this.statusname="草稿";
    	}
    	if(StaticMethod.null2String(this.status).equals("10")){
    		this.statusname="待审核";
    	}
    	if(StaticMethod.null2String(this.status).equals("11")){
    		this.statusname="驳回";
    	}
    	if(StaticMethod.null2String(this.status).equals("12")){
    		this.statusname="审核通过";
    	}
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getLeaveidname() {
		leaveidname = dictMgr.id2Name(leaveid,
		"ItawSystemDictTypeDao");
		return leaveidname;
	}

	public void setLeaveidname(String leaveidname) {
		this.leaveidname = leaveidname;
	}

	public String getUsername() {
		this.username = userMgr.getUserByuserid(userId).getUsername();
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
