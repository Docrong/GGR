/*
 * Created on 2008-5-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;

/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConditionParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4105923405469818397L;
	//private String  dbtype;
	private String  pageName;
	private String  queryName;
	private String  operate;
//	dbtype="String" page-name="" query-name="" operate=">="/><!-- dbtype={String,int,datetime} -->

	private boolean  markflg = false;//是否需要加'单引号 where id = 12345a 转换为 where id = '12345a'
	
	public ConditionParam()
	{}
	
	public ConditionParam(String pageName)
	{
		this.pageName = pageName;
	}
	
	public ConditionParam(String pageName,boolean markflg)
	{
		this.pageName = pageName;
		this.markflg = markflg;
	}
	
	public boolean isMarkflg() {
		return markflg;
	}
	public void setMarkflg(boolean markflg) {
		this.markflg = markflg;
	}
	/**
	 * @return Returns the operate.
	 */
	public String getOperate() {
		return operate;
	}
	/**
	 * @param operate The operate to set.
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}
	/**
	 * @return Returns the pageName.
	 */
	public String getPageName() {
		return pageName;
	}
	/**
	 * @param pageName The pageName to set.
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	/**
	 * @return Returns the queryName.
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @param queryName The queryName to set.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
}
