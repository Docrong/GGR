package com.boco.eoms.testcard.model;

public class TawTestcardTesting {
  private String iccid;
  private String msisdn;
  private String testtime;
  private String outcome;
  private String conner;
  private String leave;
  private String accessories;
  public String getConner() {
    return conner;
  }

  public String getOutcome() {
    return outcome;
  }

  public String getTesttime() {
    return testtime;
  }



  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public void setConner(String conner) {
    this.conner = conner;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  public void setTesttime(String testtime) {
    this.testtime = testtime;
  }



  public void setLeave(String leave) {
    this.leave = leave;
  }
  
  public String getAccessories() {
	    return accessories;
	  }

  public void setAccessories(String accessories) {
    this.accessories = accessories;
  }

  public void setMsisdn(String msisdn) {
	    this.msisdn = msisdn;
	  }

  
  public String getIccid() {
    return iccid;
  }

  public String getLeave() {
    return leave;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public TawTestcardTesting() {
  }
}
