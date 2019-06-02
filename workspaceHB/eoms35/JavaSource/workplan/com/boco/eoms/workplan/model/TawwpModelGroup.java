package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划模板组织结构类</p>
 * <p>Description: 作业计划模板组织结构信息，其中包括模板的组织结构关系</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class
 * table="TAW_WP_MODELGROUP"
 */

public class TawwpModelGroup
    implements Serializable,DataObject {

  private String id; //标识
  private String name; //作业计划模板组织名称
  private String deleted; //删除标志
  private TawwpModelGroup parentModelGroup; //父作业计划组织形式
  private Set childModelGroups = new HashSet(); //子作业计划组织形式
  private TawwpModelPlan tawwpModelPlan; //作业计划模板对象
  private Set tawwpModelExecutes = new HashSet(); //作业计划模版执行内容

  public TawwpModelGroup() {
  }

  public TawwpModelGroup(String _name, TawwpModelGroup _parentModelGroup,
                         Set _childModelGroups, Set tawwpModelExecutes,TawwpModelPlan _tawwpModelPlan) {
    this.name = _name;
    this.parentModelGroup = _parentModelGroup;
    this.childModelGroups = _childModelGroups;
    this.tawwpModelExecutes = tawwpModelExecutes;
    this.tawwpModelPlan = _tawwpModelPlan;
  }

  public TawwpModelGroup(String _name, TawwpModelGroup _parentModelGroup,
                         TawwpModelPlan _tawwpModelPlan) {
    this.name = _name;
    this.parentModelGroup = _parentModelGroup;
    this.tawwpModelPlan = _tawwpModelPlan;
    this.deleted = "0";
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
   * column="DELETED"
   * length="1"
   * not-null="true"
   * update="true"
   */
  public String getDeleted() {
    return deleted;
  }
  public void setDeleted(String deleted) {
    this.deleted = deleted;
  }


  /**
   * @hibernate.many-to-one
   * column="PARENT_MODEL_GROUP_ID"
   * cascade="none"
   * not-null="false"
   */
  public TawwpModelGroup getParentModelGroup() {
    return parentModelGroup;
  }

  public void setParentModelGroup(TawwpModelGroup parentModelGroup) {
    this.parentModelGroup = parentModelGroup;
  }

  /**
   * @hibernate.set
   * inverse="true"
   * lazy="true"
   * cascade="all-delete-orphan"
   * @hibernate.collection-key
   * column="PARENT_MODEL_GROUP_ID"
   * @hibernate.collection-one-to-many
   * class="com.boco.eoms.workplan.model.TawwpModelGroup"
   */
  public Set getChildModelGroups() {
    return childModelGroups;
  }

  public void setChildModelGroups(Set childModelGroups) {
    this.childModelGroups = childModelGroups;
  }

  /**
   * @hibernate.set
   * inverse="true"
   * lazy="true"
   * cascade="all-delete-orphan"
   * @hibernate.collection-key
   * column="MODEL_GROUP_ID"
   * @hibernate.collection-one-to-many
   * class="com.boco.eoms.workplan.model.TawwpModelExecute"
   */
  public Set getTawwpModelExecutes() {
    return tawwpModelExecutes;
  }
  public void setTawwpModelExecutes(Set tawwpModelExecutes) {
    this.tawwpModelExecutes = tawwpModelExecutes;
  }


  /**
   * @hibernate.many-to-one
   * column="MODEL_PLAN_ID"
   * cascade="none"
   * not-null="false"
   */
  public TawwpModelPlan getTawwpModelPlan() {
    return tawwpModelPlan;
  }

  public void setTawwpModelPlan(TawwpModelPlan tawwpModelPlan) {
    this.tawwpModelPlan = tawwpModelPlan;
  }



}
