/*
 * Created on 2007-11-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.qo;

import java.util.Map;

/**
 * @author liuxy 工单查询的QO TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */

public interface IWorkSheetQO {

	/**
	 * 根据QO的条件拼装查询语句
	 * @return 可执行的sql
	 */
	public abstract String getClauseSql(Map map,Map condition);
	
	/**
	 * 根据QO的条件拼装查询语句，是有权限的查询
	 * @return 可执行的sql
	 */
	public abstract String getAclClauseSql(Map map, Map condition);
	/**
	 * Main表PO名称
	 * main poName setter getter
	 * @param PoName
	 */
	public abstract void setPoMain(String PoName);
	public abstract String getPoMain();
	/**
	 * Link表PO名称
	 * link poName setter getter
	 * @param PoName
	 */
	public abstract void setPoLink(String PoName);
	
	public abstract String getPoLink();
	
	
	/**
	 * Main表的别名名称
	 * main poName setter getter
	 * @param PoName
	 */
	public abstract void setAliasMain(String aliasMain);
	public abstract String getAliasMain();
	/**
	 * Link表别名名称
	 * link poName setter getter
	 * @param PoName
	 */
	public abstract void setAliasLink(String aliasLink);	
	public abstract String getAliasLink();
	
	
	public String getAliasTask();
	public void setAliasTask(String aliasTask);
	
	public String getPoTask();
	public void setPoTask(String poTask);

}
