package com.boco.eoms.testcard.model;

public class TawEventDic{
	  private int id;
	  //----------字典唯一标识-----------
	  private String type_id;
	  //----------类型ID，用于定义在哪里调用----------
	  private int parent_id;
	  //----------父ID，用于嵌套-----------
	  private String name;
	  //----------名称-------------
	  private String remark;
	  //----------备注-------------
	  private int base;
	  //----------是否为叶，1为叶-------------
	  public int getBase()
	  {
	    return base;
	  }
	  public void setBase(int base)
	  {
	    this.base=base;
	  }
	  public int getId()
	  {
	    return id;
	  }
	  public void setId(int id)
	  {
	    this.id=id;
	  }
	  public String getType_id()
	  {
	    return type_id;
	  }
	  public void setType_id(String type_id)
	  {
	    this.type_id=type_id;
	  }
	  public int getParent_id()
	  {
	    return parent_id;
	  }
	  public void setParent_id(int parent_id)
	  {
	    this.parent_id=parent_id;
	  }
	  public String getName()
	  {
	    return name;
	  }
	  public void setName(String name)
	  {
	    this.name=name;
	  }
	  public String getRemark()
	  {
	    return remark;
	  }
	  public void setRemark(String remark)
	  {
	    this.remark=remark;
	  }
	}
