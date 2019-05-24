/*
 * Created on 2008-1-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.base.model.BaseCollect;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IBaseKPIManager extends Manager{
	public abstract void setLink(BaseLink linkName);
	public abstract BaseLink getLink();
	public abstract void setMain(BaseMain mainName);
	public abstract BaseMain getMain();
	public abstract void setCollectPojo(BaseCollect collectPojoName);
	public abstract BaseCollect getCollectPojo();
	public abstract String getXmlKey();
	public abstract void setXmlKey(String xmlKey);
	
	public abstract List getBaseData(String startDate);
	public abstract List getCollects();
	public void saveOrUpdateAll(List list);
	public void deleteData(String startDate);
	public void run(String startTime) throws Exception;
}
