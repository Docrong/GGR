package com.boco.eoms.commons.statistic.customstat.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class StSubscriptionForm  extends    BaseForm implements java.io.Serializable{

	

	/**
	 * 
	 */
	

	private String id;
	
    private String subId;

   
    private int statMode;

   
    private String cycle;

   
    private String item;

  
    private String type;

    
    private String className;

   
    private String subscriber;

   
    private int subscribeDept;

   
    private String subscribeTime;

   
    private String statfromdate;

    
    private String stattodate;

   
    private int weekselectfrom;

    
    private int deleted;

    private String itemName;

    private String remark;
   
	
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
    	 * @hibernate.property length="32" not-null="false" 
    	 */		

   public String getSubId() {
    		return subId;
    	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	/**
	 * @hibernate.property length="9" not-null="false" 
	 */		

	public int getStatMode() {
		return statMode;
	}
	public void setStatMode(int statMode) {
		this.statMode = statMode;
	}
	/**
	 * @hibernate.property length="50" not-null="false" 
	 */		

	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	/**
	 * @hibernate.property length="5" not-null="false" 
	 */		

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @hibernate.property length="10" not-null="false" 
	 */		

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @hibernate.property length="100" not-null="false" 
	 */		

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @hibernate.property length="20" not-null="false" 
	 */		

	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}
	/**
	 * @hibernate.property length="20" not-null="false" 
	 */		

	public int getSubscribeDept() {
		return subscribeDept;
	}
	public void setSubscribeDept(int subscribeDept) {
		this.subscribeDept = subscribeDept;
	}
	/**
	 * @hibernate.property length="50" not-null="false" 
	 */		

	
	/**
	 * @hibernate.property length="9" not-null="false" 
	 */		

	public int getWeekselectfrom() {
		return weekselectfrom;
	}
	public void setWeekselectfrom(int weekselectfrom) {
		this.weekselectfrom = weekselectfrom;
	}
	/**
	 * @hibernate.property length="9" not-null="false" 
	 */		

	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	/**
	 * @hibernate.property length="100" not-null="false" 
	 */		

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @hibernate.property length="255" not-null="false" 
	 */		

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getStatfromdate() {
		return statfromdate;
	}
	public void setStatfromdate(String statfromdate) {
		this.statfromdate = statfromdate;
	}
	public String getStattodate() {
		return stattodate;
	}
	public void setStattodate(String stattodate) {
		this.stattodate = stattodate;
	}
	
}