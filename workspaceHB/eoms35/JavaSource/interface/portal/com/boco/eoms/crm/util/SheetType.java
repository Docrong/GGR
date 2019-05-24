package com.boco.eoms.crm.util;

public class SheetType {
	private String typeid;
	private String name;
	private ServiceType[] serviceType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ServiceType[] getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType[] serviceType) {
		this.serviceType = serviceType;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	
}
