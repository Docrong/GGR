package com.boco.eoms.sheet.commonfaultAlarm.service.impl;

import java.util.Date;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfaultAlarm.dao.hibernate.AlarmsolveDateConfigDaoHibernate;
import com.boco.eoms.sheet.commonfaultAlarm.dao.IAlarmsolveDateConfigDao;
import com.boco.eoms.sheet.commonfaultAlarm.dao.IAlarmsolveDateDao;
import com.boco.eoms.sheet.commonfaultAlarm.dao.hibernate.AlarmsolveDateDao;
import com.boco.eoms.sheet.commonfaultAlarm.model.AlarmsolvedateConfig;
import com.boco.eoms.sheet.commonfaultAlarm.service.*;

public class AlarmsolveDateManagerImpl implements IAlarmsolveDateManager {
	private IAlarmsolveDateDao alarmsolveDateDao;
	private IAlarmsolveDateConfigDao alarmsolveDateConfigDao;
	private ICommonFaultMainDAO mainDAO;
	
	
	
	public Date alarmsolveDate(String alarmId) throws Exception{
		/***
		 * 获取告警平台告警清除时间
		 * Date alarmsolveDate = alarmsolveDateDao.findbyid(alarmId); 
		 * 以后可以修改为接口方式调用同步数据
		 */
		Date alarmsolveDate = alarmsolveDateDao.findbyid(alarmId);
		return alarmsolveDate;
	}
	public void updateAlarmsolveDate(AlarmsolvedateConfig acf,CommonFaultMain main) throws Exception{
		/***
		 * 同步告警平台告警清除时间
		 * alarmsolveDateDao.update(acf.getAlarmnum(), StaticMethod.date2String(acf.getMainalarmsolvedate())); 
		 * 以后可以修改为接口方式调用同步数据
		 */
		alarmsolveDateDao.update(acf.getAlarmnum(), StaticMethod.date2String(acf.getMainalarmsolvedate()));
		alarmsolveDateConfigDao.saveorupdate(acf);
		mainDAO.saveOrUpdateMain(main);
		
	}
	public void updateEomsAlarmsolveDate(AlarmsolvedateConfig obj,CommonFaultMain main) throws Exception{
		alarmsolveDateConfigDao.saveorupdate(obj);
		mainDAO.saveOrUpdateMain(main);
	}
	public List findbyall(int issended)throws Exception{
		
		return alarmsolveDateConfigDao.findall(issended);
	}
	/***
	 * 修改告警平台告警清除时间，并且修改告轮询表状态值；
	 *
	 */
	public void updateAlarmsolveDate(AlarmsolvedateConfig acf)throws Exception{
		/***
		 * 同步告警平台告警清除时间
		 * alarmsolveDateDao.update(acf.getAlarmnum(), StaticMethod.date2String(acf.getMainalarmsolvedate())); 
		 * 以后可以修改为接口方式调用同步数据
		 */
		alarmsolveDateDao.update(acf.getAlarmnum(), StaticMethod.date2String(acf.getMainalarmsolvedate()));
		acf.setIssended("1");
		alarmsolveDateConfigDao.saveorupdate(acf);
	}
	
	public void updateCheckDate(String sheetid , String checkTime)throws Exception{
		/***
		 * 同步告警平台告警核实时间
		 * alarmsolveDateDao.update(acf.getAlarmnum(), StaticMethod.date2String(acf.getMainalarmsolvedate())); 
		 * 以后可以修改为接口方式调用同步数据
		 */
		alarmsolveDateDao.updateCheckTime(sheetid, checkTime);
	}
	
	public IAlarmsolveDateDao getAlarmsolveDateDao() {
		return alarmsolveDateDao;
	}
	public void setAlarmsolveDateDao(IAlarmsolveDateDao alarmsolveDateDao) {
		this.alarmsolveDateDao = alarmsolveDateDao;
	}
	public IAlarmsolveDateConfigDao getAlarmsolveDateConfigDao() {
		return alarmsolveDateConfigDao;
	}
	public void setAlarmsolveDateConfigDao(
			IAlarmsolveDateConfigDao alarmsolveDateConfigDao) {
		this.alarmsolveDateConfigDao = alarmsolveDateConfigDao;
	}
	public ICommonFaultMainDAO getMainDAO() {
		return mainDAO;
	}
	public void setMainDAO(ICommonFaultMainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}
}
