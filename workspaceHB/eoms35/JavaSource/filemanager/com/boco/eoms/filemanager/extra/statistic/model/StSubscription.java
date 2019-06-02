package com.boco.eoms.filemanager.extra.statistic.model;

import java.io.Serializable;
import java.sql.Timestamp;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.boco.eoms.common.oo.DataObject;
/** @author Hibernate CodeGenerator */
public class StSubscription implements Serializable,DataObject {

    /** identifier field */
    private String id;

    /** nullable persistent field */
    private String subId;

    /** nullable persistent field */
    private int statMode;

    /** nullable persistent field */
    private String cycle;

    /** nullable persistent field */
    private String item;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String className;

    /** nullable persistent field */
    private String subscriber;

    /** nullable persistent field */
    private int subscribeDept;

    /** nullable persistent field */
    private Timestamp subscribeTime;

    /** nullable persistent field */
    private String sendDeptid;

    /** nullable persistent field */
    private String sendUserid;

    /** nullable persistent field */
    private String receiveDeptid;

    /** nullable persistent field */
    private String receiveUserid;

    /** nullable persistent field */
    private Timestamp statfromdate;

    /** nullable persistent field */
    private Timestamp stattodate;

    /** nullable persistent field */
    private int weekselectfrom;

    /** nullable persistent field */
    private int deleted;

    private String itemName;

    private String remark;

    /** full constructor */
    public StSubscription(String subId, int statMode, String cycle, String item, String type,String className,String itemName,String subscriber, int subscribeDept, Timestamp subscribeTime, String sendDeptid, String sendUserid, String receiveDeptid, String receiveUserid, Timestamp statfromdate, Timestamp stattodate, int weekselectfrom, int deleted,String remark) {
        this.subId = subId;
        this.statMode = statMode;
        this.cycle = cycle;
        this.item = item;
        this.type = type;
        this.className = className;
        this.itemName = itemName;
        this.subscriber = subscriber;
        this.subscribeDept = subscribeDept;
        this.subscribeTime = subscribeTime;
        this.sendDeptid = sendDeptid;
        this.sendUserid = sendUserid;
        this.receiveDeptid = receiveDeptid;
        this.receiveUserid = receiveUserid;
        this.statfromdate = statfromdate;
        this.stattodate = stattodate;
        this.weekselectfrom = weekselectfrom;
        this.deleted = deleted;
        this.remark=remark;
    }

    /** default constructor */
    public StSubscription() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubId() {
        return this.subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public int getStatMode() {
        return this.statMode;
    }

    public void setStatMode(int statMode) {
        this.statMode = statMode;
    }

    public String getCycle() {
        return this.cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubscriber() {
        return this.subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public int getSubscribeDept() {
        return this.subscribeDept;
    }

    public void setSubscribeDept(int subscribeDept) {
        this.subscribeDept = subscribeDept;
    }

    public Timestamp getSubscribeTime() {
        return this.subscribeTime;
    }

    public void setSubscribeTime(Timestamp subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getSendDeptid() {
        return this.sendDeptid;
    }

    public void setSendDeptid(String sendDeptid) {
        this.sendDeptid = sendDeptid;
    }

    public String getSendUserid() {
        return this.sendUserid;
    }

    public void setSendUserid(String sendUserid) {
        this.sendUserid = sendUserid;
    }

    public String getReceiveDeptid() {
        return this.receiveDeptid;
    }

    public void setReceiveDeptid(String receiveDeptid) {
        this.receiveDeptid = receiveDeptid;
    }

    public String getReceiveUserid() {
        return this.receiveUserid;
    }

    public void setReceiveUserid(String receiveUserid) {
        this.receiveUserid = receiveUserid;
    }

    public Timestamp getStatfromdate() {
        return this.statfromdate;
    }

    public void setStatfromdate(Timestamp statfromdate) {
        this.statfromdate = statfromdate;
    }

    public Timestamp getStattodate() {
        return this.stattodate;
    }

    public void setStattodate(Timestamp stattodate) {
        this.stattodate = stattodate;
    }

    public int getWeekselectfrom() {
        return this.weekselectfrom;
    }

    public void setWeekselectfrom(int weekselectfrom) {
        this.weekselectfrom = weekselectfrom;
    }

    public int getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StSubscription) ) return false;
        StSubscription castOther = (StSubscription) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
  public String getItemName() {
    return itemName;
  }
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }

}
