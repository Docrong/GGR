package com.boco.eoms.workplan.model;


/**
 * <p>Title: 网元类</p>
 * <p>Description: 网元类信息，其中包括名称，部门，系统类型，网元类别等信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class
 * table="TAW_WP_NET"
 */


public class TawwpNet
    implements Serializable,DataObject {

  private String id; //标识
  private String name; //年度作业计划名称
  private String sysTypeId; //系统类别
  private String netTypeId; //网元类型
  private String mynettypeid;//专业代码
  private String deptId; //部门标识
  private String roomId; //机房标识
  private String serialNo; //序列号
  private String vendor;  //制造商
  private String deleted; //删除标志
  private String reportFlag;//0总部网元 1已上报 2已删除
  
  

  public TawwpNet() {
  }

  public TawwpNet(String _name, String _deptId, String _roomId,String _sysTypeId,
                       String _netTypeId, String _mynetTypeId, String _serialNo, String _vendor,String _deleted,String _reportFlag) {
    this.name = _name;
    this.deptId = _deptId;
    this.roomId = _roomId;
    this.sysTypeId = _sysTypeId;
    this.netTypeId = _netTypeId;
    this.mynettypeid = _mynetTypeId;
    this.serialNo = _serialNo;
    this.vendor = _vendor;
    this.deleted = _deleted;
    this.reportFlag = _reportFlag;
  }
  public String getReportFlag() {
	return reportFlag;
  }

  public void setReportFlag(String reportFlag) {
	this.reportFlag = reportFlag;
  }

  /**
   * @hibernate.id
   * column="ID"
   * length="32"
   * unsaved-value="null"
   * generator-class="uuid.hex"
   */
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @hibernate.property
   * column="DEPTID"
   * length="10"
   * not-null="true"
   * update="false"
   */
  public String getDeptId() {
    return deptId;
  }
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  /**
   * @hibernate.property
   * column="NAMES"
   * length="100"
   * not-null="true"
   * update="true"
   */
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @hibernate.property
   * column="ROOMID"
   * length="40"
   * not-null="true"
   * update="false"
   */
  public String getRoomId() {
    return roomId;
  }
  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  /**
   * @hibernate.property
   * column="SYSTYPEID"
   * length="10"
   * not-null="false"
   * update="false"
   */
  public String getSysTypeId() {
    return sysTypeId;
  }

  public void setSysTypeId(String sysTypeId) {
    this.sysTypeId = sysTypeId;
  }

  /**
   * @hibernate.property
   * column="NETTYPEID"
   * length="10"
   * not-null="false"
   * update="false"
   */
  public String getNetTypeId() {
    return netTypeId;
  }

  public void setNetTypeId(String netTypeId) {
    this.netTypeId = netTypeId;
  }


  /**
   * @hibernate.property
   * column="SERIALNO"
   * length="40"
   * not-null="true"
   * update="true"
   */
  public String getSerialNo() {
    return serialNo;
  }
  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  /**
   * @hibernate.property
   * column="VENDOR"
   * length="60"
   * not-null="true"
   * update="false"
   */
  public String getVendor() {
    return vendor;
  }
  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  /**
   * @hibernate.property column="DELETED" length="1" not-null="true"
   *                     update="true"
   */
  public String getDeleted() {
    return deleted;
  }

  public void setDeleted(String deleted) {
    this.deleted = deleted;
  }

  public String getMynettypeid() {
    return mynettypeid;
  }

  public void setMynettypeid(String mynettypeid) {
    this.mynettypeid = mynettypeid;
  }


}
