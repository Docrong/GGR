package com.boco.eoms.sparepart.controller;


import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.sparepart.model.*;

/**
 * <p>Title: ��Ʒ����</p>
 * <p>Description: EOMS��ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class TawTreeForm extends BaseForm implements java.io.Serializable{
    private String cname;
    private String ename;
    private String code;
    private String note;
    private String layer;
    private int id;
    private int parentId;
    private int radix;
    private int deleted;
    private String dept;
    private String root;
    private String type;
    private String subType;
    public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public TawTreeForm(){
    }

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public String getEname(){
        return ename;
    }

    public void setEname(String ename){
        this.ename=ename;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code=code;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getLayer(){
        return layer;
    }

    public void setLayer(String layer){
        this.layer=layer;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getParentId(){
        return parentId;
    }

    public void setParentId(int parentId){
        this.parentId=parentId;
    }

    public int getRadix(){
        return radix;
    }

    public void setRadix(int radix){
        this.radix=radix;
    }

    public int getDeleted(){
        return deleted;
    }

    public void setDeleted(int deleted){
        this.deleted=deleted;
    }

    public String getDept(){
        return dept;
    }

    public void setDept(String dept){
        this.dept=dept;
    }

    public String getRoot(){
        return root;
    }

    public void setRoot(String root){
        this.root=root;
    }
  public String getType() {
    if(null==type||type.equals(""))
      type="%";
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

}
