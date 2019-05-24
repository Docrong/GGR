package com.boco.eoms.commons.statistic.customstat.model;
import com.boco.eoms.base.model.BaseObject;
import java.sql.Timestamp;

/**
* <p>
 * <a href="StSubscription.java.html"><i>View Source</i></a>
 * 
 * @author 
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="st_subscription"
 */
public class StSubscription extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
    private String subId;
    private String  jobId;
   
    private int statMode;
   
   
    private String cycle;

   
    private String item;

  
    private String type;

    
    private String className;

   
    private String subscriber;

   
    private int subscribeDept;

   
    private Timestamp subscribeTime;

   
    private Timestamp statfromdate;

    
    private Timestamp stattodate;

   
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

	public Timestamp getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Timestamp subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	/**
	 * @hibernate.property length="50" not-null="false" 
	 */		

	public Timestamp getStatfromdate() {
		return statfromdate;
	}
	public void setStatfromdate(Timestamp statfromdate) {
		this.statfromdate = statfromdate;
	}
	/**
	 * @hibernate.property length="50" not-null="false" 
	 */		

	public Timestamp getStattodate() {
		return stattodate;
	}
	public void setStattodate(Timestamp stattodate) {
		this.stattodate = stattodate;
	}
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
	
	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
}