/*
 * Created on 2008-1-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.base.kpi.util.CollectColumn;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICollectColumnQuery extends Dao{
	public String getValue(CollectColumn column,String mainName,String linkName,String mainId);
}
