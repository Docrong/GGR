package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;
import java.util.Date;
import com.boco.eoms.base.webapp.form.BaseForm;

public class TawExpertSubForm extends BaseForm implements Serializable {

	/**
	 * author 冯少宏
	 */
  
  private String id;
  private String malfunctionName;//故障名称
  private String malfunctionDeal;//故障解决结果
  private String score;//评价
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
 * @return malfunctionDeal
 */
public String getMalfunctionDeal() {
	return malfunctionDeal;
}
/**
 * @param malfunctionDeal 要设置的 malfunctionDeal
 */
public void setMalfunctionDeal(String malfunctionDeal) {
	this.malfunctionDeal = malfunctionDeal;
}
/**
 * @return malfunctionName
 */
public String getMalfunctionName() {
	return malfunctionName;
}
/**
 * @param malfunctionName 要设置的 malfunctionName
 */
public void setMalfunctionName(String malfunctionName) {
	this.malfunctionName = malfunctionName;
}
/**
 * @return score
 */
public String getScore() {
	return score;
}
/**
 * @param score 要设置的 score
 */
public void setScore(String score) {
	this.score = score;
}
}
