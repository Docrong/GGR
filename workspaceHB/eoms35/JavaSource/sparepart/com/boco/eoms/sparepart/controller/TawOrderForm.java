package com.boco.eoms.sparepart.controller;

import org.apache.struts.upload.*;
import org.apache.struts.action.*;

import com.boco.eoms.base.webapp.form.BaseForm;

import java.util.List;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawOrderForm extends BaseForm implements java.io.Serializable
{

    private int id;
    private String operater;
    private String operaterId;
    private String proposer;
    private String proposerId;
    private String prop_dept;
    private String prop_tel;
    private String startdate;
    private String overdate;
    private String note;
    private String state;
    private String storageid;
    private String orderType;
    private String orderId;
    private FormFile theFile;
    private String sheetid;
    private String overgay;//�ص���
    private List spList; //�����б�
    private String destStorageId;//���òֿ�
    //add by wqw 20070703
    private String reason;//����>�
    private String station;//ʹ��վ��
    private String fixe;//�豸����
    private String version;//�汾��
    private String serialno;//���к�
    private String ename;//ʵ�����
    private String objtype;//�豸�ͺ�
    private String sparepart_id;//����ID
    private int partType;

    

  private String newserialno;
  public String getStorageid()
    {
        return storageid;
    }

    public void setStorageid(String storageid)
    {
        this.storageid=storageid;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state=state;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getOperater()
    {
        return operater;
    }

    public void setOperater(String operater)
    {
        this.operater=operater;
    }

    public String getProposer()
    {
        return proposer;
    }

    public void setProposer(String proposer)
    {
        this.proposer=proposer;
    }

    public String getProp_dept()
    {
        return prop_dept;
    }

    public void setProp_dept(String prop_dept)
    {
        this.prop_dept=prop_dept;
    }

    public String getProp_tel()
    {
        return prop_tel;
    }

    public void setProp_tel(String prop_tel)
    {
        this.prop_tel=prop_tel;
    }

    public String getStartdate()
    {
        return startdate;
    }

    public void setStartdate(String startdate)
    {
        this.startdate=startdate;
    }

    public String getOverdate()
    {
        return overdate;
    }

    public void setOverdate(String overdate)
    {
        this.overdate=overdate;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note=note;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public FormFile getTheFile() {
      return theFile;
    }

  public String getSheetid() {
    return sheetid;
  }

  public String getOvergay() {
    return overgay;
  }

  public List getSpList() {
    return spList;
  }

  public String getDestStorageId() {
    return destStorageId;
  }

  public String getNewserialno() {
    return newserialno;
  }

  public void setTheFile(FormFile theFile) {
      this.theFile = theFile;
    }

  public void setSheetid(String sheetid) {
    this.sheetid = sheetid;
  }

  public void setOvergay(String overgay) {
    this.overgay = overgay;
  }

  public void setSpList(List spList) {
    this.spList = spList;
  }

  public void setDestStorageId(String destStorageId) {
    this.destStorageId = destStorageId;
  }

  public void setNewserialno(String newserialno) {
    this.newserialno = newserialno;
  }

public String getEname() {
	return ename;
}

public void setEname(String ename) {
	this.ename = ename;
}

public String getFixe() {
	return fixe;
}

public void setFixe(String fixe) {
	this.fixe = fixe;
}

public String getObjtype() {
	return objtype;
}

public void setObjtype(String objtype) {
	this.objtype = objtype;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}

public String getSerialno() {
	return serialno;
}

public void setSerialno(String serialno) {
	this.serialno = serialno;
}

public String getStation() {
	return station;
}

public void setStation(String station) {
	this.station = station;
}

public String getVersion() {
	return version;
}

public void setVersion(String version) {
	this.version = version;
}

public String getSparepart_id() {
	return sparepart_id;
}

public void setSparepart_id(String sparepart_id) {
	this.sparepart_id = sparepart_id;
}

public String getOperaterId() {
	return operaterId;
}

public void setOperaterId(String operaterId) {
	this.operaterId = operaterId;
}

public String getProposerId() {
	return proposerId;
}

public void setProposerId(String proposerId) {
	this.proposerId = proposerId;
}

public int getPartType() {
	return partType;
}

public void setPartType(int partType) {
	this.partType = partType;
}

}
