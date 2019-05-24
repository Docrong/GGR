package com.boco.eoms.commons.failureRecord.model;

import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
public class FailureRecord {
	private String id;
	private String alarmed;
	private String title;
	private String faultstarttime;
	private String faultendtime;
	private String faulttype1;
	private String faulttype2;
	private String faulttype3;
	private String faulttype4;
	private String faulttype1Name;
	private String faulttype2Name;
	private String faulttype3Name;
	private String faulttype4Name;
	private String faultregion;
	private String faultregionName;
	private String faultjudge;
	private String faultjudgeName;
	private String sheettemplatename;
	private String faulttype5;
	private String faultdetail;
	private String todeptid;
	private String todutyroom;
	private String odutyroomid;
	private String todutyroomen;
	private String faultsource;
	private String faultstatus;
	private String faultstatusName;
	private String view="查看";
	private String del="删除";
	private String news="生成故障工单";
	private String edit="修改";
	public String getAlarmed() {
		return alarmed;
	}
	public String getTitle() {
		return title;
	}
	public String getFaultstarttime() {
		return faultstarttime;
	}
	public String getFaultendtime() {
		return faultendtime;
	}
	public String getFaulttype1() {
		return faulttype1;
	}
	public String getFaulttype2() {
		return faulttype2;
	}
	public String getFaulttype3() {
		return faulttype3;
	}
	public String getFaulttype4() {
		return faulttype4;
	}
	public String getFaultregion() {
		return faultregion;
	}
	public String getFaultjudge() {
		return faultjudge;
	}
	public String getSheettemplatename() {
		return sheettemplatename;
	}
	public String getFaulttype5() {
		return faulttype5;
	}
	public String getFaultdetail() {
		return faultdetail;
	}
	public String getTodeptid() {
		return todeptid;
	}
	public String getTodutyroom() {
		return todutyroom;
	}
	public String getOdutyroomid() {
		return odutyroomid;
	}
	public String getTodutyroomen() {
		return todutyroomen;
	}
	public String getFaultsource() {
		return faultsource;
	}
	public String getFaultstatus() {
		return faultstatus;
	}
	public void setAlarmed(String alarmed) {
		this.alarmed = alarmed;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setFaultstarttime(String faultstarttime) {
		this.faultstarttime = faultstarttime;
	}
	public void setFaultendtime(String faultendtime) {
		this.faultendtime = faultendtime;
	}
	public void setFaulttype1(String faulttype1) {
		this.faulttype1 = faulttype1;
	}
	public void setFaulttype2(String faulttype2) {
		this.faulttype2 = faulttype2;
	}
	public void setFaulttype3(String faulttype3) {
		this.faulttype3 = faulttype3;
	}
	public void setFaulttype4(String faulttype4) {
		this.faulttype4 = faulttype4;
	}
	public void setFaultregion(String faultregion) {
		this.faultregion = faultregion;
	}
	public void setFaultjudge(String faultjudge) {
		this.faultjudge = faultjudge;
	}
	public void setSheettemplatename(String sheettemplatename) {
		this.sheettemplatename = sheettemplatename;
	}
	public void setFaulttype5(String faulttype5) {
		this.faulttype5 = faulttype5;
	}
	public void setFaultdetail(String faultdetail) {
		this.faultdetail = faultdetail;
	}
	public void setTodeptid(String todeptid) {
		this.todeptid = todeptid;
	}
	public void setTodutyroom(String todutyroom) {
		this.todutyroom = todutyroom;
	}
	public void setOdutyroomid(String odutyroomid) {
		this.odutyroomid = odutyroomid;
	}
	public void setTodutyroomen(String todutyroomen) {
		this.todutyroomen = todutyroomen;
	}
	public void setFaultsource(String faultsource) {
		this.faultsource = faultsource;
	}
	public void setFaultstatus(String faultstatus) {
		this.faultstatus = faultstatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getFaulttype1Name() {
		faulttype1Name=DictMgrLocator.getId2NameService().id2Name(this.faulttype1,"ItawSystemDictTypeDao");
		return faulttype1Name;
	}
	public void setFaulttype1Name(String faulttype1Name) {
		this.faulttype1Name = faulttype1Name;
	}
	public String getFaulttype2Name() {
		faulttype2Name=DictMgrLocator.getId2NameService().id2Name(this.faulttype2,"ItawSystemDictTypeDao");
		return faulttype2Name;
	}
	public void setFaulttype2Name(String faulttype2Name) {
		this.faulttype2Name = faulttype2Name;
	}
	public String getFaulttype3Name() {
		faulttype3Name=DictMgrLocator.getId2NameService().id2Name(this.faulttype3,"ItawSystemDictTypeDao");
		return faulttype3Name;
	}
	public void setFaulttype3Name(String faulttype3Name) {
		this.faulttype3Name = faulttype3Name;
	}
	public String getFaulttype4Name() {
		faulttype4Name=DictMgrLocator.getId2NameService().id2Name(this.faulttype4,"ItawSystemDictTypeDao");
		return faulttype4Name;
	}
	public void setFaulttype4Name(String faulttype4Name) {
		this.faulttype4Name = faulttype4Name;
	}
	public String getFaultregionName() {
		faultregionName=DictMgrLocator.getId2NameService().id2Name(this.faultregion,"ItawSystemDictTypeDao");
		return faultregionName;
	}
	public void setFaultregionName(String faultregionName) {
		this.faultregionName = faultregionName;
	}
	public String getFaultjudgeName() {
		faultjudgeName=DictMgrLocator.getId2NameService().id2Name(this.faultjudge,"ItawSystemDictTypeDao");
		return faultjudgeName;
	}
	public void setFaultjudgeName(String faultjudgeName) {
		this.faultjudgeName = faultjudgeName;
	}
	public String getFaultstatusName() {
		faultjudgeName=DictMgrLocator.getId2NameService().id2Name(this.faultstatus,"ItawSystemDictTypeDao");
		return faultstatusName;
	}
	public void setFaultstatusName(String faultstatusName) {
		this.faultstatusName = faultstatusName;
	}
	
	
	
	
}
