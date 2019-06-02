/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.job;

import java.util.List;

import com.boco.eoms.sheet.base.model.BaseCollect;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IBaseKPIJob{
	public abstract void setLink(BaseLink linkName);
	public abstract BaseLink getLink();
	public abstract void setMain(BaseMain mainName);
	public abstract BaseMain getMain();
	public abstract void setCollectPojo(BaseCollect collectPojoName);
	public abstract BaseCollect getCollectPojo();
	
	public abstract List getBaseData(String startDate);
	public abstract List getCollects();
	public void saveOrUpdateAll(List list);
	public void deleteData(String startDate);
}
