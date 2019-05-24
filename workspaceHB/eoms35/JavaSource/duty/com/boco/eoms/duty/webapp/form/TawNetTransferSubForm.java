package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;
import java.util.Date;
import com.boco.eoms.base.webapp.form.BaseForm;

public class TawNetTransferSubForm extends BaseForm implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 3916348163825037788L;
	private String id;
	  private String mainId;//主事件id
	  private String mainTitle;//主事件主题
	  private String preDate;//预计开始时间
	  private String last;//预计持续时间
	  private String finishDate;//完成时间
	  private String state;//状态
	  private String addMan;//添加人
	  private String addTime;//添加时间
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
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime 要设置的 addTime
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	/**
	 * @return finishDate
	 */
	public String getFinishDate() {
		return finishDate;
	}
	/**
	 * @param finishDate 要设置的 finishDate
	 */
	public void setFinishDate(String finishDate) {
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
	/**
	 * @return preDate
	 */
	public String getPreDate() {
		return preDate;
	}
	/**
	 * @param preDate 要设置的 preDate
	 */
	public void setPreDate(String preDate) {
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
}
