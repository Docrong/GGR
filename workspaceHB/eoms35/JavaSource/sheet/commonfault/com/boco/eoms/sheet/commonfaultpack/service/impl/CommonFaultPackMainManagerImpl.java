
package com.boco.eoms.sheet.commonfaultpack.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;
import com.boco.eoms.sheet.commonfaultpack.dao.ICommonFaultPackMainDAO;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;

public class CommonFaultPackMainManagerImpl extends MainService implements ICommonFaultPackMainManager {
   
	public List getListByMainId(final Integer curPage, final Integer pageSize,String mainId)throws HibernateException, SheetException{
		ICommonFaultPackMainDAO iCommonFaultPackMainDAO = (ICommonFaultPackMainDAO)this.getMainDAO();
		List list = null;
		try {
			list = iCommonFaultPackMainDAO.getListByMainId(curPage, pageSize, mainId); 
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return list; 
	}
	
	public List getListByMainId(String mainId)throws SheetException{
		ICommonFaultPackMainDAO iCommonFaultPackMainDAO = (ICommonFaultPackMainDAO)this.getMainDAO();
		List list = null;
		try {
			list = iCommonFaultPackMainDAO.getListByMainId(mainId); 
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return list; 
	}
	
	public Integer getCountByMainId(String mainId) throws SheetException {
		ICommonFaultPackMainDAO iCommonFaultPackMainDAO = (ICommonFaultPackMainDAO)this.getMainDAO();		
		Integer count = new Integer(0);
		try {
			count = iCommonFaultPackMainDAO.getCountByMainId(mainId);
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return count;
	}	
	 	/**
		 * 通过告警号获取工单
		 * 
		 * @param alarmId
		 *            告警号
		 * @return
		 * @throws HibernateException
		 */
		public CommonFaultPackMain getMainByAlarmId(String alarmId){
			ICommonFaultPackMainDAO icommonFaultMainDAO = (ICommonFaultPackMainDAO)this.getMainDAO();
			return icommonFaultMainDAO.getMainByAlarmId(alarmId);
		}
		public String getIfAlarmSolve(String alarmId)throws SheetException{
			String ifAlarmSolve ="";
			ICommonFaultPackMainDAO icommonFaultMainDAO = (ICommonFaultPackMainDAO)this.getMainDAO();
			try {
				ifAlarmSolve = icommonFaultMainDAO.getIfAlarmSolve(alarmId);
			} catch (Exception e) {
				throw new SheetException(e);
			}
			return ifAlarmSolve;
		}

		public boolean isAllHaveClearTime(String mainId)
		{


			ICommonFaultPackMainDAO icommonFaultMainDAO = (ICommonFaultPackMainDAO)getMainDAO();
			List list = icommonFaultMainDAO.getMainsByMainId(mainId);
			return list != null && list.size() > 0;
		}


}
