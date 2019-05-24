package com.boco.eoms.testcard.controller;
import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import java.util.*;


public class TawTestcardManagerForm extends ActionForm{
  public final static int ADD = 1;
  public final static int EDIT = 2;
  public final static int USED = 3;
  private int strutsAction;
  private Collection beanCollection;
  private Collection beanCollectionDN;
  private int id;
  private String iccid;
  private String msisdn;//---------------���Կ����ţ�Ψһֵ��20λ----------------
  private String accessory;//----����
  private int cardtype;
  private String returner;//�黹��
  private String manager;//��׼��
  private String renewer;//�����
  private String exes;//�������
  private String volumenum; //���
  private String pagenum;   //ҳ��
  
  private FormFile theFile;
  //---------------������---------------
  private String leave;
  //---------------��ŵ�---------------
  private String cardid;
  //---------------����(iccid)-----------------
  private String oldid;
  //---------------��ϵͳ����--------------
  private String dealer;
  //---------------������----------------
  private String lenddept;
  //---------------���ò���--------------
  private String relenddept;
  //---------------���ò���--------------
  private String postid;
  private String lender;
  //---------------������---------------
  private String contect;
  //---------------jϵ��ʽ--------------
  private String reason;
  //--------------����ԭ��---------------
  private String leantime;
  //--------------�������---------------
  private String belongtime;
  //--------------Ӧ�黹����--------------
  private String returntime;
  //------------- �黹���ڣ���������������
//------------- �黹���ţ���������������
  private String returndept;
  
//------------- �������ˣ���������������
  private String checker;
//------------- �������˵绰����������������
  private String checktel;
  private int renewlimit;
  //------------- ������ޣ���������������
  private String state;//--------------�����ֶ�---------------

  private String cardTypeNum;//���Կ�������:    0:4�ÿ�;1:��ÿ�.

  private String operation;  //���Կ��������� 0�����Կ��޸ģ�1�����Կ������Կ����ԡ����Կ�����
  
  private String sort1_deptid;
  private String sort1_userid;
  private String sort1_postid;
  private String sort1_userName;
  
  public String getSort1_userName() {
	return sort1_userName;
}
public void setSort1_userName(String sort1_userName) {
	this.sort1_userName = sort1_userName;
}
public int getId()
  {
    return id;
  }
  public void setId(int id)
  {
    this.id=id;
  }

  public int getCardtype()
  {
    return cardtype;
  }
  public void setCardtype(int cardtype)
  {
    this.cardtype=cardtype;
  }
  public String getLeave()
  {
    return leave;
  }
  public void setLeave(String leave)
  {
    this.leave=leave;
  }

  public Collection getBeanCollection(){
     return beanCollection;
   }
   public void setBeanCollection(List lists){
     this.beanCollection=lists;
  }
  public Collection getBeanCollectionDN(){
    return beanCollectionDN;
  }
  public void setBeanCollectionDN(List lists){
    this.beanCollectionDN=lists;
  }
  public int getStrutsAction()
  {
    return strutsAction;
  }
  public void setStrutsAction(int strutsAction)
  {
    this.strutsAction=strutsAction;
  }

  public String getCardid()
  {
    return cardid;
  }
  public void setCardid(String cardid)
  {
    this.cardid=cardid;
  }
  public String getOldid()
  {
    return oldid;
  }
  public void setOldid(String oldid)
  {
    this.oldid=oldid;
  }
  public String getDealer()
  {
    return dealer;
  }
  public void setDealer(String dealer)
  {
    this.dealer=dealer;
  }
  public String getLenddept()
  {
    return lenddept;
  }
  public void setLenddept(String lenddept)
  {
    this.lenddept=lenddept;
  }
  public String getLender()
  {
    return lender;
  }
  public  void setLender(String lender)
  {
    this.lender=lender;
  }
  public String getContect()
  {
    return contect;
  }
  public void setContect(String contect)
  {
    this.contect=contect;
  }
  public String getReason()
  {
    return reason;
  }
  public void setReason(String reason)
  {
    this.reason=reason;
  }
  public String getLeantime()
  {
    return leantime;
  }
  public void setLeantime(String leantime)
  {
    this.leantime=leantime;
  }
  public String getBelongtime()
  {
    return belongtime;
  }
  public void setBelongtime(String belongtime)
  {
    this.belongtime=belongtime;
  }
  public int getRenewlimit()
  {
    return renewlimit;
  }
  public void setRenewlimit(int renewlimit)
  {
    this.renewlimit=renewlimit;
  }
  public String getReturntime()
  {
    return returntime;
  }

  public String getAccessory() {
    return accessory;
  }

  public FormFile getTheFile() {
    return theFile;
  }

  public String getExes() {
    return exes;
  }

  public String getVolumenum() {
    return volumenum;
  }

  public String getPagenum() {
    return pagenum;
  }

  public String getRenewer() {
    return renewer;
  }

  public String getReturner() {
    return returner;
  }

  public String getState() {
    return state;
  }

  public String getIccid() {
    return iccid;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public String getCardTypeNum() {
    return cardTypeNum;
  }

  public String getOperation() {
    return operation;
  }
 
  public void setReturntime(String returntime)
  {
    this.returntime=returntime;
  }

  public void setAccessory(String accessory) {
    this.accessory = accessory;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }

  public void setExes(String exes) {
    this.exes = exes;
  }

  public void setVolumenum(String volumenum) {
    this.volumenum = volumenum;
  }

  public void setPagenum(String pagenum) {
    this.pagenum = pagenum;
  }

  public void setRenewer(String renewer) {
    this.renewer = renewer;
  }

  public void setReturner(String returner) {
    this.returner = returner;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public void setCardTypeNum(String cardTypeNum) {
    this.cardTypeNum = cardTypeNum;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }
public String getManager() {
	return manager;
}
public void setManager(String manager) {
	this.manager = manager;
}
public String getRelenddept() {
	return relenddept;
}
public void setRelenddept(String relenddept) {
	this.relenddept = relenddept;
}
	public String getReturndept() {
	    return returndept;
	  }
	public void setReturndept(String returndept)
	{
	this.returndept=returndept;
	} 
	public String getChecker() {
	    return checker;
	  }
	public void setChecker(String checker)
	{
	this.checker=checker;
	} 
	public String getChecktel() {
	    return checktel;
	  }
	public void setChecktel(String checktel)
	{
	this.checktel=checktel;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getSort1_deptid() {
		return sort1_deptid;
	}
	public void setSort1_deptid(String sort1_deptid) {
		this.sort1_deptid = sort1_deptid;
	}
	public String getSort1_postid() {
		return sort1_postid;
	}
	public void setSort1_postid(String sort1_postid) {
		this.sort1_postid = sort1_postid;
	}
	public String getSort1_userid() {
		return sort1_userid;
	}
	public void setSort1_userid(String sort1_userid) {
		this.sort1_userid = sort1_userid;
	} 
}
