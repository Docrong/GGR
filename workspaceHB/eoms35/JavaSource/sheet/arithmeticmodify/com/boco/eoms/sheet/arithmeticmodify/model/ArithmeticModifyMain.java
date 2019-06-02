
package com.boco.eoms.sheet.arithmeticmodify.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="arithmeticmodifyMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="arithmeticmodifymain"
 */
public class ArithmeticModifyMain extends BaseMain
{	
    
   

	private String mainYuLiuA; 
	private String mainYuLiuB; 
	private String mainYuLiuC; 
	private String mainYuLiuD; 
	private String mainYuLiuE; 
	//指标级别
	private String mainTargetLevel;
	//指标分类
	private String mainTargetType;


	public String getMainYuLiuA() {
		return mainYuLiuA;
	}
	public void setMainYuLiuA(String mainYuLiuA) {
		this.mainYuLiuA = mainYuLiuA;
	}
	public String getMainYuLiuB() {
		return mainYuLiuB;
	}
	public void setMainYuLiuB(String mainYuLiuB) {
		this.mainYuLiuB = mainYuLiuB;
	}

	public String getMainTargetLevel() {
		return mainTargetLevel;
	}
	public void setMainTargetLevel(String mainTargetLevel) {
		this.mainTargetLevel = mainTargetLevel;
	}
	public String getMainTargetType() {
		return mainTargetType;
	}
	public void setMainTargetType(String mainTargetType) {
		this.mainTargetType = mainTargetType;
	}
	public String getMainYuLiuC() {
		return mainYuLiuC;
	}
	public void setMainYuLiuC(String mainYuLiuC) {
		this.mainYuLiuC = mainYuLiuC;
	}
	public String getMainYuLiuD() {
		return mainYuLiuD;
	}
	public void setMainYuLiuD(String mainYuLiuD) {
		this.mainYuLiuD = mainYuLiuD;
	}
	public String getMainYuLiuE() {
		return mainYuLiuE;
	}
	public void setMainYuLiuE(String mainYuLiuE) {
		this.mainYuLiuE = mainYuLiuE;
	}

	
}
