package com.boco.eoms.duty.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

public class TawNetTransferSub extends BaseObject{

  /**
	 * 
	 */
	private static final long serialVersionUID = -3516040609864960728L;
/**
	 * author 冯少宏
	 */
  
  private String id;
  private String mainId;//主事件id
  private String mainTitle;//主事件主题
  private Date preDate;//预计开始时间
  private String last;//预计持续时间
  private Date finishDate;//完成时间
  private String state;//状态
  private String addMan;//添加人
  private Date addTime;//添加时间
  
  
  
/**
 * @return addMan
 */
public String getAddMan() {
	return addMan;
}



/**
 * @param addMan 要设置的 addMan
 */
public void setAddMan(String addMan) {
	this.addMan = addMan;
}



/**
 * @return addTime
 */
public Date getAddTime() {
	return addTime;
}



/**
 * @param addTime 要设置的 addTime
 */
public void setAddTime(Date addTime) {
	this.addTime = addTime;
}



/**
 * @return finishDate
 */
public Date getFinishDate() {
	return finishDate;
}



/**
 * @param finishDate 要设置的 finishDate
 */
public void setFinishDate(Date finishDate) {
	this.finishDate = finishDate;
}



/**
 * @return id
 */
public String getId() {
	return id;
}



/**
 * @param id 要设置的 id
 */
public void setId(String id) {
	this.id = id;
}



/**
 * @return last
 */
public String getLast() {
	return last;
}



/**
 * @param last 要设置的 last
 */
public void setLast(String last) {
	this.last = last;
}



/**
 * @return mainId
 */
public String getMainId() {
	return mainId;
}



/**
 * @param mainId 要设置的 mainId
 */
public void setMainId(String mainId) {
	this.mainId = mainId;
}



/**
 * @return preDate
 */
public Date getPreDate() {
	return preDate;
}



/**
 * @param preDate 要设置的 preDate
 */
public void setPreDate(Date preDate) {
	this.preDate = preDate;
}



/**
 * @return state
 */
public String getState() {
	return state;
}



/**
 * @param state 要设置的 state
 */
public void setState(String state) {
	this.state = state;
}



public boolean equals(Object o) {
	// TODO 自动生成方法存根
	return false;
}



/**
 * @return mainTitle
 */
public String getMainTitle() {
	return mainTitle;
}



/**
 * @param mainTitle 要设置的 mainTitle
 */
public void setMainTitle(String mainTitle) {
	this.mainTitle = mainTitle;
}
  
}