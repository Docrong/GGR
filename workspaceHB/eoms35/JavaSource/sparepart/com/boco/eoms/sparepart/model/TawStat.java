package com.boco.eoms.sparepart.model;

public class TawStat {
  public int getLoadnum() {
    return loadnum;
  }

  public int getEnlargnum() {
    return enlargnum;
  }

  public String getStorage() {
    return storage;
  }

  public int getStorageid() {
    return storageid;
  }

  public int getOutnum() {
    return outnum;
  }

  public int getUseablenum() {
    return useablenum;
  }

  public int getFaultnum() {
    return faultnum;
  }

  public int getRejectnum() {
    return rejectnum;
  }

  public void setSummation(int summation) {
    this.summation = summation;
  }

  public void setLoadnum(int loadnum) {
    this.loadnum = loadnum;
  }

  public void setEnlargnum(int enlargnum) {
    this.enlargnum = enlargnum;
  }

  public void setStorage(String storage) {
    this.storage = storage;
  }

  public void setStorageid(int storageid) {
    this.storageid = storageid;
  }

  public void setOutnum(int outnum) {
    this.outnum = outnum;
  }

  public void setUseablenum(int useablenum) {
    this.useablenum = useablenum;
  }

  public void setFaultnum(int faultnum) {
    this.faultnum = faultnum;
  }

  public void setRejectnum(int rejectnum) {
    this.rejectnum = rejectnum;
  }

  public int getSummation() {
    return summation;
  }

  public TawStat() {
  }
  private int useablenum;
  private String storage;
  private int storageid;
  private int faultnum;
  private int outnum;
  private int loadnum;
  private int rejectnum;
  private int enlargnum;
  private int summation;
  private int innum;
  private int allnum;
public int getAllnum() {
	return allnum;
}

public void setAllnum(int allnum) {
	this.allnum = allnum;
}

public int getInnum() {
	return innum;
}

public void setInnum(int innum) {
	this.innum = innum;
}
}
