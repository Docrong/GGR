package com.boco.eoms.testcard.model;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;

public class TawTestcard {

	ID2NameService dictMgr = (ID2NameService) ApplicationContextHolder
	.getInstance().getBean("id2nameService");
	
	private int id;

	private String exes;// �������

	private String volumenum; // ���

	private String pagenum; // ҳ��

	private String leave;
	
	private String leavename;

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

	// ---------------���Կ����кţ�Ψһֵ��25λ----------------
	private String msisdn1;

	// ---------------���Կ��绰���룬Ψһֵ��15λ----------------
	private String imsi;

	// ---------------���Կ��绰���룬Ψһֵ��15λ----------------
	private String imsi1;

	// ---------------���Կ����ţ�Ψһֵ��20λ----------------
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
	private String state;// 0:��;1:ͣ��;2:��ʧ;3:���;4:ʹ��;5:����

	// ---------------��ǰ״̬��0��1ͣ��2��ʧ��3���----------------
	private String oldNo;

	// ---------------��ϵͳ��ţ�������----------------
	private String offer;

	// ---------------�޸�״̬----------------
	private String editState;

	// -----------------�ṩ��--------------------
	private String msgcenterno = "";

	private String lasttesttime = "";

	private String testresult = "";

	private String dealresult = "";

	private String adder = "";

	private String telnum = "";

	private String position;

	private String cardpackage;

	private String isAlive;

	private String phoneNumber;

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

	public String getExes() {
		return exes;
	}

	public String getVolumenum() {
		return volumenum;
	}

	public String getPagenum() {
		return pagenum;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setCardpackage(String cardpackage) {
		this.cardpackage = cardpackage;
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

	public void setTelnum(String telnum) {
		this.telnum = telnum;
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

	public String getEditState() {
		return editState;
	}

	public void setEditState(String editState) {
		this.editState = editState;
	}

	public String getLeavename() {
		leavename = dictMgr.id2Name(leave,
		"ItawSystemDictTypeDao");
		return leavename;
	}

	public void setLeavename(String leavename) {
		this.leavename = leavename;
	}
}
