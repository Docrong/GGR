package com.boco.eoms.commons.interfaceMonitoring.model;

import com.boco.eoms.commons.system.dict.util.DictMgrLocator;

public class InterfaceMonitoring {
	private String id;
	private String callingTime;
	private String callingSide;
	private String callingSideName;
	private String provider;
	private String providerName;
	private String interFaceType;
	private String interFaceTypeName;
	private String interFaceMethod;
	private String success;
	private String successName;
	private String exceptionLog;
	private String keyword;
	private String text;
	private String callDirection;
	private String view="查看";
	private String serSupplier;
	private String serCaller;
	private String sheetKey;
	private String method;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSheetKey() {
		return sheetKey;
	}
	public void setSheetKey(String sheetKey) {
		this.sheetKey = sheetKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCallingTime() {
		return callingTime;
	}
	public void setCallingTime(String callingTime) {
		this.callingTime = callingTime;
	}
	public String getCallingSide() {
		return callingSide;
	}
	public void setCallingSide(String callingSide) {
		this.callingSide = callingSide;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getInterFaceType() {
		return interFaceType;
	}
	public void setInterFaceType(String interFaceType) {
		this.interFaceType = interFaceType;
	}
	public String getInterFaceMethod() {
		return interFaceMethod;
	}
	public void setInterFaceMethod(String interFaceMethod) {
		this.interFaceMethod = interFaceMethod;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getExceptionLog() {
		return exceptionLog;
	}
	public void setExceptionLog(String exceptionLog) {
		this.exceptionLog = exceptionLog;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCallDirection() {
		return callDirection;
	}
	public void setCallDirection(String callDirection) {
		this.callDirection = callDirection;
	}
	
	
	public String toString(){
		String str="调用方："+callingSide+"/n"+"调用方:"+provider+"/n"+"接口类型";
		return str;
		
	}
	public String getView() {
	
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getInterFaceTypeName() {
		interFaceTypeName=DictMgrLocator.getId2NameService().id2Name(this.interFaceType,"ItawSystemDictTypeDao");
		return interFaceTypeName;
	}
	public void setInterFaceTypeName(String interFaceTypeName) {
		this.interFaceTypeName = interFaceTypeName;
	}
	public String getSuccessName() {
		if(this.success.equals("Success")){
			successName="成功";
		}
		else{
			successName="失败";
		}
		return successName;
	}
	public void setSuccessName(String successName) {
		this.successName = successName;
	}
	public String getCallingSideName() {
		callingSideName=DictMgrLocator.getId2NameService().id2Name(this.callingSide,"ItawSystemDictTypeDao");
		return callingSideName;
	}
	public void setCallingSideName(String callingSideName) {
		this.callingSideName = callingSideName;
	}
	public String getProviderName() {
		providerName=DictMgrLocator.getId2NameService().id2Name(this.provider,"ItawSystemDictTypeDao");
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getSerSupplier() {
		return serSupplier;
	}
	public void setSerSupplier(String serSupplier) {
		this.serSupplier = serSupplier;
	}
	public String getSerCaller() {
		return serCaller;
	}
	public void setSerCaller(String serCaller) {
		this.serCaller = serCaller;
	}
	
	
	
}
