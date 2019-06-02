package com.boco.eoms.testcard.model;

import com.boco.eoms.testcard.util.StaticValue;
import com.boco.eoms.testcard.util.TestCardMgrLocator;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: �����
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TawTestcardManager {
  private int id;
  private String msisdn;//---------------���Կ����ţ�Ψһֵ��20λ----------------
  private String exes;//�������
  private String accessory;//----����
  private String volumenum; //���
  private String pagenum;   //ҳ��
  private String returner;//�黹��
  private String renewer;//�����
  private String manager;//ִ����
  private int cardtype;
  //---------------������---------------
  private String leave;
  //---------------��ŵ�---------------
  private String cardid;
  //---------------����-----------------
  private String oldid;
  //---------------��ϵͳ����--------------
  private String dealer;
  //---------------������----------------
  private String lenddept;
  //---------------���ò���--------------
  private String relenddept;
  //---------------���ò���--------------
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
  private int renewlimit;
  //------------- ������ޣ���������������
  private String returndept;
//------------- �������ˣ���������������
  private String checker;
//------------- �������˵绰����������������
  private String checktel;
  private String state;
  private String stateName;
  private String fromcrit;

  //---------------���ڿ�����أ�����ʡid��----------------
  private String fromcity;
  
  private String lenderid;
  
  //--------------�����ֶ�---------------
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

  public String getExes() {
    return exes;
  }

  public String getPagenum() {
    return pagenum;
  }

  public String getVolumenum() {
    return volumenum;
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

  public String getMsisdn() {
    return msisdn;
  }

  public void setReturntime(String returntime)
  {
    this.returntime=returntime;
  }

  public void setAccessory(String accessory) {
    this.accessory = accessory;
  }

  public void setExes(String exes) {
    this.exes = exes;
  }

  public void setPagenum(String pagenum) {
    this.pagenum = pagenum;
  }

  public void setVolumenum(String volumenum) {
    this.volumenum = volumenum;
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

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }
public String getRelenddept() {
	return relenddept;
}
public void setRelenddept(String relenddept) {
	this.relenddept = relenddept;
}

public String getManager() {
	return manager;
}
public void setManager(String manager) {
	this.manager = manager;
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
public String getLenderid() {
	return lenderid;
}
public void setLenderid(String lenderid) {
	this.lenderid = lenderid;
}
/**
 * @return fromcity
 */
public String getFromcity() {
	return fromcity;
}
/**
 * @param fromcity Ҫ���õ� fromcity
 */
public void setFromcity(String fromcity) {
	this.fromcity = fromcity;
}
/**
 * @return fromcrit
 */
public String getFromcrit() {
	return fromcrit;
}
/**
 * @param fromcrit Ҫ���õ� fromcrit
 */
public void setFromcrit(String fromcrit) {
	this.fromcrit = fromcrit;
}
public String getStateName() {
	if (this.getState().equals("0")) {
		  stateName=TestCardMgrLocator.getAttributes().getNormalState();
	} else if (this.getState().equals("1")) {
		  stateName=TestCardMgrLocator.getAttributes().getDowntimeState();
	} else if (this.getState().equals("2")) {
		  stateName=TestCardMgrLocator.getAttributes().getLostState();
	} else if (this.getState().equals("3")) {
		  stateName=TestCardMgrLocator.getAttributes().getLendingState();
	} else if (this.getState().equals("4")) {
		  stateName=TestCardMgrLocator.getAttributes().getUseState();
	} else if (this.getState().equals("5")) {
		  stateName=TestCardMgrLocator.getAttributes().getScrappedState();
	} else if (this.getState().equals("6")) {
		  stateName=TestCardMgrLocator.getAttributes().getRegistrationState();
	} else if (this.getState().equals("8")) {
		  stateName=TestCardMgrLocator.getAttributes().getQuestionState();
	} else if (this.getState().equals(
			StaticValue.STATUS_WAIT)) {
		  stateName=TestCardMgrLocator.getAttributes().getPendingState();
	} else if (this.getState().equals(
			StaticValue.STATUS_PASS)) {
		  stateName=TestCardMgrLocator.getAttributes().getAuditedState();
	}

	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}

}
