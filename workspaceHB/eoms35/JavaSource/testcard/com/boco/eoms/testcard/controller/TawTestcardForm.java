package com.boco.eoms.testcard.controller;

import org.apache.struts.validator.*;
import java.util.*;
import org.apache.struts.upload.*;

public class TawTestcardForm extends ValidatorForm {
	public final static int ADD = 1;

	public final static int EDIT = 2;

	public final static int LOANED = 3;

	public final static int USED = 4;

	public final static int SCRAP = 5;

	private int strutsAction;

	private Collection beanCollection;

	private Collection beanCollectionDN;

	private Collection beCollection;

	private Collection beCollectionDN;

	private Collection beCollec;

	private Collection beCollectt;

	private Collection beCollep;

	protected FormFile theFile;

	private String volumenum; // ���

	private String pagenum; // ҳ��

	private int filetype;// �ļ����� 0��4�ÿ�̨�� 1����ÿ�̨��

	private int id;

	private String exes;// �������

	private String leave;

	// ---------------���Կ���ŵ�-------------------------
	private String cardType;

	// ---------------�����ͣ�0Ϊ��ʿ���1Ϊ���ڿ�----------------
	private String fromCanton;

	// ---------------��ʿ�����أ������ݼ�id��----------------
	private String fromCountry;

	// ---------------��ʿ�����أ�������id��----------------
	private String fromOpe;

	// ---------------��ʿ�����أ�������Ӫ��id��----------------
	private String fromCrit;

	// ---------------���ڿ�����أ�����ʡid��----------------
	private String fromCity;

	// ---------------���ڿ�����أ��������id��----------------
	private String toCanton;

	// ---------------��ʿ��ݷõأ��ݷ��ݼ�id��----------------
	private String toCountry;

	// ---------------��ʿ��ݷõأ��ݷù��id��----------------
	private String toOpe;

	// ---------------��ʿ��ݷõأ��ݷ���Ӫ��id��----------------
	private String toCrit;

	// ---------------���ڿ��ݷõأ��ݷ�ʡid��----------------
	private String toCity;

	// ---------------���ڿ��ݷõأ��ݷõ���id��----------------
	private String iccid;

	// ---------------���Կ����кţ�Ψһֵ��25λ----------------
	private String msisdn;

	// ---------------���Կ����к�(˫ģ)��Ψһֵ��25λ----------------
	private String msisdn1;

	// ---------------���Կ��绰���룬Ψһֵ��15λ----------------
	private String imsi;// ---------------imsi��Ψһֵ��32λ----------------
	// ---------------���Կ��绰����(˫ģ)��Ψһֵ��15λ----------------

	private String imsi1;

	private String pin1;

	// ---------------���Կ�����ʶ����1��4λ----------------
	private String pin2;

	// ---------------���Կ�����ʶ����2��4λ----------------
	private String puk1;

	// ---------------���Կ�����ʶ���������1��8λ----------------
	private String puk2;

	// ---------------���Կ�����ʶ���������2��8λ----------------
	private String password;

	// ---------------�������룬6λ----------------
	private String operation;

	// ---------------��ͨҵ��10λ----------------
	private String begintime;

	// ---------------��ͨ���ڣ�19λ----------------
	private String endtime;

	// ---------------ע�����ڣ�19λ----------------
	private String intime;

	// ---------------������ڣ����뱾ϵͳʱ�䣬19λ----------------
	private String state; // 0:��;1:ͣ��;2:��ʧ;3:���;4:ʹ��;5:����

	private String newstate; // 0:��;1:ͣ��;2:��ʧ;3:���;4:ʹ��;5:����

	// ---------------��ǰ״̬��0��1ͣ��2��ʧ��3���----------------
	private String oldNo;

	// ---------------��ϵͳ��ţ�������----------------
	private String offer;

	// -----------------�ṩ��--------------------
	private String msgcenterno = "";

	private String lasttesttime = "";

	private String testresult = "";

	private String dealresult = "";

	private String adder = "";

	private String telnum = "";// /---------------���Կ����ţ�Ψһֵ��20λ--------------

	private String cardpackage;

	private String sort1_deptid;

	private String sort1_userid;

	private String sort1_postid;

	private String clearresan;

	private String beginNumberPartOne;

	private String beginNumberPartTow;

	private String beginNumberPartThree;

	private String endNumberPartOne;

	private String endNumberPartTow;

	private String endNumberPartThree;
	
	private String checkBeginNumberPartOne;

	private String checkBeginNumberPartTow;

	private String checkBeginNumberPartThree;

	private String checkEndNumberPartOne;

	private String checkEndNumberPartTow;

	private String checkEndNumberPartThree;
	
	private String phoneNumber;
	
	private String isAlive;

	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}

	public String getMsgcenterno() {
		return msgcenterno;
	}

	public void setMsgcenterno(String msgcenterno) {
		this.msgcenterno = msgcenterno;
	}

	public String getLasttesttime() {
		return lasttesttime;
	}

	public void setLasttesttime(String lasttesttime) {
		this.lasttesttime = lasttesttime;
	}

	public String getTestresult() {
		return testresult;
	}

	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}

	public String getDealresult() {
		return dealresult;
	}

	public void setDealresult(String dealresult) {
		this.dealresult = dealresult;
	}

	private int ifIccid;

	private String position;

	private String cardTypeNum;// ���Կ�������: 0:4�ÿ�;1:��ÿ�.

	public int getIfIccid() {
		return ifIccid;
	}

	public void setIfIccid(int ifIccid) {
		this.ifIccid = ifIccid;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection getBeanCollection() {
		return beanCollection;
	}

	public void setBeanCollection(List lists) {
		this.beanCollection = lists;
	}

	public Collection getBeanCollectionDN() {
		return beanCollectionDN;
	}

	public void setBeanCollectionDN(List lists) {
		this.beanCollectionDN = lists;
	}

	public Collection getBeCollection() {
		return beCollection;
	}

	public void setBeCollection(List lists) {
		this.beCollection = lists;
	}

	public Collection getBeCollectionDN() {
		return beCollectionDN;
	}

	public Collection getBeCollec() {
		return beCollec;
	}

	public void setBeCollec(List lists) {
		this.beCollec = lists;
	}

	public Collection getBeCollectt() {
		return beCollectt;
	}

	public void setBeCollectt(List lists) {
		this.beCollectt = lists;
	}

	public void setBeCollectionDN(List lists) {
		this.beCollectionDN = lists;
	}

	public int getStrutsAction() {
		return strutsAction;
	}

	public void setStrutsAction(int strutsAction) {
		this.strutsAction = strutsAction;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getFromCanton() {
		return fromCanton;
	}

	public void setFromCanton(String fromCanton) {
		this.fromCanton = fromCanton;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getFromOpe() {
		return fromOpe;
	}

	public void setFromOpe(String fromOpe) {
		this.fromOpe = fromOpe;
	}

	public String getFromCrit() {
		return fromCrit;
	}

	public void setFromCrit(String fromCrit) {
		this.fromCrit = fromCrit;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCanton() {
		return toCanton;
	}

	public void setToCanton(String toCanton) {
		this.toCanton = toCanton;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public String getToOpe() {
		return toOpe;
	}

	public void setToOpe(String toOpe) {
		this.toOpe = toOpe;
	}

	public String getToCrit() {
		return toCrit;
	}

	public void setToCrit(String toCrit) {
		this.toCrit = toCrit;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getPin1() {
		return pin1;
	}

	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}

	public String getPuk2() {
		return puk2;
	}

	public void setPuk2(String puk2) {
		this.puk2 = puk2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNewstate() {
		return newstate;
	}

	public void setNewstate(String newstate) {
		this.newstate = newstate;
	}

	public String getOldNo() {
		return oldNo;
	}

	public void setOldNo(String oldNo) {
		this.oldNo = oldNo;
	}

	public String getPin2() {
		return pin2;
	}

	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	public String getPuk1() {
		return puk1;
	}

	public void setPuk1(String puk1) {
		this.puk1 = puk1;
	}

	public String getPosition() {
		return position;
	}

	public String getCardpackage() {
		return cardpackage;
	}

	public Collection getBeCollep() {
		return beCollep;
	}

	public FormFile getTheFile() {
		return theFile;
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

	public int getFiletype() {
		return filetype;
	}

	public String getTelnum() {
		return telnum;
	}

	public String getCardTypeNum() {
		return cardTypeNum;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setCardpackage(String cardpackage) {
		this.cardpackage = cardpackage;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
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

	public void setFiletype(int filetype) {
		this.filetype = filetype;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public void setCardTypeNum(String cardTypeNum) {
		this.cardTypeNum = cardTypeNum;
	}

	public void setBeCollep(List beCollep) {
		this.beCollep = beCollep;
	}

	public String getImsi1() {
		return imsi1;
	}

	public void setImsi1(String imsi1) {
		this.imsi1 = imsi1;
	}

	public String getMsisdn1() {
		return msisdn1;
	}

	public void setMsisdn1(String msisdn1) {
		this.msisdn1 = msisdn1;
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

	public String getClearresan() {
		return clearresan;
	}

	public void setClearresan(String clearresan) {
		this.clearresan = clearresan;
	}

	public String getBeginNumberPartOne() {
		return beginNumberPartOne;
	}

	public void setBeginNumberPartOne(String beginNumberPartOne) {
		this.beginNumberPartOne = beginNumberPartOne;
	}

	public String getBeginNumberPartThree() {
		return beginNumberPartThree;
	}

	public void setBeginNumberPartThree(String beginNumberPartThree) {
		this.beginNumberPartThree = beginNumberPartThree;
	}

	public String getBeginNumberPartTow() {
		return beginNumberPartTow;
	}

	public void setBeginNumberPartTow(String beginNumberPartTow) {
		this.beginNumberPartTow = beginNumberPartTow;
	}

	public String getEndNumberPartOne() {
		return endNumberPartOne;
	}

	public void setEndNumberPartOne(String endNumberPartOne) {
		this.endNumberPartOne = endNumberPartOne;
	}

	public String getEndNumberPartThree() {
		return endNumberPartThree;
	}

	public void setEndNumberPartThree(String endNumberPartThree) {
		this.endNumberPartThree = endNumberPartThree;
	}

	public String getEndNumberPartTow() {
		return endNumberPartTow;
	}

	public void setEndNumberPartTow(String endNumberPartTow) {
		this.endNumberPartTow = endNumberPartTow;
	}

	public String getCheckBeginNumberPartOne() {
		return checkBeginNumberPartOne;
	}

	public void setCheckBeginNumberPartOne(String checkBeginNumberPartOne) {
		this.checkBeginNumberPartOne = checkBeginNumberPartOne;
	}

	public String getCheckBeginNumberPartThree() {
		return checkBeginNumberPartThree;
	}

	public void setCheckBeginNumberPartThree(String checkBeginNumberPartThree) {
		this.checkBeginNumberPartThree = checkBeginNumberPartThree;
	}

	public String getCheckBeginNumberPartTow() {
		return checkBeginNumberPartTow;
	}

	public void setCheckBeginNumberPartTow(String checkBeginNumberPartTow) {
		this.checkBeginNumberPartTow = checkBeginNumberPartTow;
	}

	public String getCheckEndNumberPartOne() {
		return checkEndNumberPartOne;
	}

	public void setCheckEndNumberPartOne(String checkEndNumberPartOne) {
		this.checkEndNumberPartOne = checkEndNumberPartOne;
	}

	public String getCheckEndNumberPartThree() {
		return checkEndNumberPartThree;
	}

	public void setCheckEndNumberPartThree(String checkEndNumberPartThree) {
		this.checkEndNumberPartThree = checkEndNumberPartThree;
	}

	public String getCheckEndNumberPartTow() {
		return checkEndNumberPartTow;
	}

	public void setCheckEndNumberPartTow(String checkEndNumberPartTow) {
		this.checkEndNumberPartTow = checkEndNumberPartTow;
	}

	public String getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(String isAlive) {
		this.isAlive = isAlive;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
