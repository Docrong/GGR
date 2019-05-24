package com.boco.eoms.testcard.controller;
import org.apache.struts.action.*;
import java.util.Collection;

public class TawTestcardTestingForm extends ActionForm{
  private String iccid;
  private String msisdn;
  private String testtime;
  private String outcome;
  private String conner;
  private String leave;
  private String fromtime;
  private String totime;
  private String accessories;
  private Collection beanCollectionDN;
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

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public void setTotime(String totime) {
    this.totime = totime;
  }

  public void setFromtime(String fromtime) {
    this.fromtime = fromtime;
  }

  public void setLeave(String leave) {
    this.leave = leave;
  }

  public void setBeanCollectionDN(Collection beanCollectionDN) {
    this.beanCollectionDN = beanCollectionDN;
  }

  public String getIccid() {
    return iccid;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public String getTotime() {
    return totime;
  }

  public String getFromtime() {
    return fromtime;
  }

  public String getLeave() {
    return leave;
  }

  public void setAccessories(String accessories) {
	    this.accessories = accessories;
	  }
  
  public String getAccessories() {
	    return accessories;
	  }
  
  public Collection getBeanCollectionDN() {
    return beanCollectionDN;
  }

  public TawTestcardTestingForm() {
  }
}
