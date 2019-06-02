/*
 * Created on 2008-1-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.service;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HqlConstruct extends BaseDaoHibernate{
	private String hql;
	/**
	 * @return Returns the hql.
	 */
	public String getHql() {
		return hql;
	}
	/**
	 * @param hql The hql to set.
	 */
	public void setHql(String hql) {
		this.hql = hql;
	}
	
	public int getResult(String mainName,String linkName,String mainId){
		hql = hql.replaceAll("BaseMain",mainName);
		hql = hql.replaceAll("BaseLink",linkName);
		hql = hql.replaceAll(":mainId","'"+mainId+"'");
		List list = getHibernateTemplate().find(hql);
		return list.size();
	}
}
