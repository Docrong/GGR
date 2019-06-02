package com.boco.eoms.commons.accessories.model;

public class TawCommonsApplication {
	 // properties
    private String appName;

    private String notes;

    private int appId;

    private int domainType;

    /**
     * default constructor
     */
    public TawCommonsApplication() {
   
    }

    // Getter and Setter functions for each properties
    public String getAppName() {
        return appName;
    }

    public String getNotes() {
        return notes;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getDomainType() {
        return domainType;
    }

    public void setDomainType(int domainType) {
        this.domainType = domainType;
    }
}
