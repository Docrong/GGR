/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.complaint.model.ComplaintReturnHouse;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;

/**
 * @author
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IComplaintReturnHouseDAO extends Dao {
	
	public abstract void saveReturnHorse(ComplaintReturnHouse complaintreturnhouse);
	
	public abstract Map getReturnHorse(String userid,final String queryStr, 
	          final Integer curPage,final Integer pageSize);
	public ComplaintReturnHouse getReturnHouseByid(String id);


}
