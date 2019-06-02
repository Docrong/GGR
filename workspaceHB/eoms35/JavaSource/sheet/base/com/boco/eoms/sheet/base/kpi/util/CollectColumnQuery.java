/*
 * Created on 2008-1-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.util;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.kpi.dao.ICollectColumnQuery;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CollectColumnQuery extends BaseDaoHibernate{
	private ICollectColumnQuery columnQuery = null;
	private static CollectColumnQuery instance= null;
	
	public static CollectColumnQuery getInstance(){
		if(instance==null)
			instance = new CollectColumnQuery();
		return instance;
	}
	private CollectColumnQuery(){
		columnQuery = (ICollectColumnQuery) ApplicationContextHolder.getInstance().getBean("ICollectColumnQuery");
	}
	
	public String getValue(CollectColumn column,String mainName,String linkName,String mainId){
		return columnQuery.getValue(column,mainName,linkName,mainId);
	}
}
