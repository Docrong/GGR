/*
 * Created on 2008-1-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.kpi.dao.ICollectColumnQuery;
import com.boco.eoms.sheet.base.kpi.util.CollectColumn;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CollectColumnQueryImpl extends BaseDaoHibernate implements ICollectColumnQuery{
	private CollectColumn column;
	
	/**
	 * @return Returns the column.
	 */
	public CollectColumn getColumn() {
		return column;
	}
	/**
	 * @param column The column to set.
	 */
	public void setColumn(CollectColumn column) {
		this.column = column;
	}
	public String getValue(CollectColumn column,String mainName,String linkName,String mainId){
		String hql = column.getHql();
		hql = hql.replaceAll("BaseMain",mainName);
		hql = hql.replaceAll("BaseLink",linkName);
		hql = hql.replaceAll(":mainId","'"+mainId+"'");
		List list = this. getHibernateTemplate().find(hql);
		int count = list.size();
		
		if(count>column.getCompareValue())
			return column.getColumnValue();
		else
			return column.getDefaultValue();
	}
}
