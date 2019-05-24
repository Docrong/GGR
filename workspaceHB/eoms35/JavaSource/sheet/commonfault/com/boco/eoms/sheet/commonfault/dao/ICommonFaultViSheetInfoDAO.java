/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.dao;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.commonfault.model.CommonFaultViSheetInfo;

/**
 * @author IBM_USER
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface ICommonFaultViSheetInfoDAO extends Dao {
	/**
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void saveOrUpdate(CommonFaultViSheetInfo obj)throws HibernateException;
	
	/**
	 * 根据子单的mainId找被修改的时间信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CommonFaultViSheetInfo getCommonFaultViSheetInfoBymainId(String id)throws HibernateException;

	public CommonFaultViSheetInfo getCommonFaultViSheetInfoByVisId(String mid, String vid)throws HibernateException;;
}
