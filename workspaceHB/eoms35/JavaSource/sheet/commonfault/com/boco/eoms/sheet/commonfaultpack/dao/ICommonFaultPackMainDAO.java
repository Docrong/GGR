
package com.boco.eoms.sheet.commonfaultpack.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;

public interface ICommonFaultPackMainDAO extends IMainDAO {
	public CommonFaultPackMain loadSinglePO(String id) throws HibernateException;
	public Integer getCountByMainId(String mainId) throws SheetException;
	public void deleteMain(String sheetKey) throws HibernateException;
	public List getListByMainId(final Integer curPage, final Integer pageSize, final String mainId)throws HibernateException;
	public List getListByMainId(final String mainId)throws HibernateException;
	public String getIfAlarmSolve(String alarmId)throws HibernateException;
	/**
	 * 通过告警号获取工单
	 * @param alarmId 告警号
	 * @return
	 * @throws HibernateException
	 */
	public CommonFaultPackMain getMainByAlarmId(String alarmId);
	public abstract List getMainsByMainId(String s);

}

