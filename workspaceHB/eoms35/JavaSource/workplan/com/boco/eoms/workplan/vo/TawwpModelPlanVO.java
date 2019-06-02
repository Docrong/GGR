package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划模板数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class TawwpModelPlanVO {

  private String id; //标识
  private String name; //作业计划模板名称
  private String sysTypeId; //系统类别
  private String sysTypeIdName; //系统类别名称
  private String netTypeId; //网元类型
  private String netTypeIdName; //网元类型名称
  private String typeIndex;
  private String origin; //来源 0:内部  1:接口
  private String deleted; //删除标志
  private String cruser; //创建人
  private String crusername; //创建人
  private String crtime; //创建时间

  private TawwpModelGroupManageVO tawwpModelGroupManageVO; //组织结构管理信息
  private List modelExecuteList; //作业计划模版执行内容集合
  private List modelDispatchList; //作业计划派发信息集合

  public TawwpModelPlanVO() {
  }

  public TawwpModelPlanVO(boolean _flag) {
    if (_flag) {
      modelExecuteList = new ArrayList();
      modelDispatchList = new ArrayList();
    }
  }

  public String getId() {
    if (id == null) {
      id = "";
    }

    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    if (name == null) {
      name = "";
    }

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSysTypeId() {
    if (sysTypeId == null) {
      sysTypeId = "";
    }

    return sysTypeId;
  }

  public void setSysTypeId(String sysTypeId) {
    this.sysTypeId = sysTypeId;
  }

  public String getNetTypeId() {
    if (netTypeId == null) {
      netTypeId = "";
    }

    return netTypeId;
  }

  public void setNetTypeId(String netTypeId) {
    this.netTypeId = netTypeId;
  }
  public String getTypeIndex() {
    if (typeIndex == null) {
      typeIndex = "";
    }

    return typeIndex;
  }

  public void setTypeIndex(String typeIndex) {
    this.typeIndex = typeIndex;
  }

  public String getOrigin() {
    if (origin == null) {
      origin = "";
    }

    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDeleted() {
    if (deleted == null) {
      deleted = "";
    }

    return deleted;
  }

  public void setDeleted(String deleted) {
    this.deleted = deleted;
  }

  public String getCruser() {
    if (cruser == null) {
      cruser = "";
    }

    return cruser;
  }

  public void setCruser(String cruser) {
    this.cruser = cruser;
  }

  public String getCrtime() {
    if (crtime == null) {
      crtime = "";
    }

    return crtime;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  public List getModelExecuteList() {

    return modelExecuteList;
  }

  public void setModelExecuteList(List modelExecuteList) {
    this.modelExecuteList = modelExecuteList;
  }

  public List getModelDispatchList() {

    return modelDispatchList;
  }

  public void setModelDispatchList(List modelDispatchList) {
    this.modelDispatchList = modelDispatchList;
  }

  public TawwpModelGroupManageVO getTawwpModelGroupManageVO() {
    return tawwpModelGroupManageVO;
  }

  public void setTawwpModelGroupManageVO(TawwpModelGroupManageVO
                                         tawwpModelGroupManageVO) {
    this.tawwpModelGroupManageVO = tawwpModelGroupManageVO;
  }

  public String getCrusername() {
    if (crusername == null) {
      crusername = "";
    }

    return crusername;
  }

  public void setCrusername(String crusername) {
    this.crusername = crusername;
  }

  public String getSysTypeIdName() {
    if (sysTypeIdName == null) {
      sysTypeIdName = "";
    }

    return sysTypeIdName;
  }

  public void setSysTypeIdName(String sysTypeIdName) {
    this.sysTypeIdName = sysTypeIdName;
  }

  public String getNetTypeIdName() {
    if (netTypeIdName == null) {
      netTypeIdName = "";
    }

    return netTypeIdName;
  }

  public void setNetTypeIdName(String netTypeIdName) {
    this.netTypeIdName = netTypeIdName;
  }

}
