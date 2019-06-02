package com.boco.eoms.commons.log.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;

import com.boco.eoms.commons.log.model.TawCommonLogDeploy;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.log.service.ITawCommonLogBzBO;
import com.boco.eoms.commons.log.service.TawCommonLogOperatorManager;
import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonLogBOImpl implements ITawCommonLogBzBO {

	TawCommonLogDeployDao tawlogdeoploydao;

	TawCommonLogOperatorManager logopermanager;

	public void saveLog(String classname, String userid, String operid,
			String remoteip, String loglevel, String message) {
		// TODO Auto-generated method stub

		tawlogdeoploydao = (TawCommonLogDeployDao) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogDeployDao");
		TawCommonLogOperator operm = new TawCommonLogOperator();
		TawCommonLogDeploy deploym = null;

		deploym = tawlogdeoploydao.getDeployByOperID(operid);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = String.valueOf(df.format(new Date()));

		operm.setBeginnotetime(str);
		operm.setBzremark(deploym.getNoteremark());
		operm.setCurrentday(getOperDay("days"));
		operm.setCurrentmonth(getOperDay("months"));
		operm.setModelid(deploym.getModelid());
		operm.setModelname(deploym.getModelname());
		operm.setNotemessage(message);
		operm.setOperid(deploym.getOperid());
		operm.setOpername(deploym.getOpername());
		operm.setOperremark(deploym.getOperdesc());
		operm.setRemoteip(remoteip);
		operm.setUrl(classname);
		operm.setUserid(userid);
		operm.setIssucess(loglevel);

		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		logopermanager.saveTawCommonLogOperator(operm);
	}
	
	
	public void saveLogging(String classname, String userid, String operid,
			String remoteip, String loglevel, String message) {
		// TODO Auto-generated method stub

		tawlogdeoploydao = (TawCommonLogDeployDao) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogDeployDao");
		TawCommonLogOperator operm = new TawCommonLogOperator();
		//TawCommonLogDeploy deploym = null;

		//deploym = tawlogdeoploydao.getDeployByOperID(operid);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = String.valueOf(df.format(new Date()));

		operm.setBeginnotetime(str);
		operm.setBzremark("");
		operm.setCurrentday(getOperDay("days"));
		operm.setCurrentmonth(getOperDay("months"));
		operm.setModelid("");
		operm.setModelname("");
		operm.setNotemessage(message);
		operm.setOperid(operid);
		operm.setOpername("");
		operm.setOperremark("");
		operm.setRemoteip(remoteip);
		operm.setUrl(classname);
		operm.setUserid(userid);
		operm.setIssucess(loglevel);

		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		logopermanager.saveTawCommonLogOperator(operm);
	}

	/**
	 * 获取日期
	 * 
	 * @param condition
	 * @return
	 */

	private static String getOperDay(String condition) {
		String strday = "";
		Calendar currentCalendars = Calendar.getInstance();

		String years = String.valueOf(currentCalendars.get(Calendar.YEAR));
		String months = String
				.valueOf(currentCalendars.get(Calendar.MONTH) + 1);
		String days = String.valueOf(currentCalendars
				.get(Calendar.DAY_OF_MONTH));

		if (condition.equalsIgnoreCase("years")) {
			strday = years;
		} else if (condition.equalsIgnoreCase("months")) {
			strday = months;
		} else if (condition.endsWith("days")) {
			strday = days;
		}
		return strday;
	}

	/**
	 * 通过用户ID 模块ID 操作ID 时间段进行日志查询
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllBymodelopertiems(HttpServletRequest request,final Integer curPage, 
			final Integer pageSize,final String userid,final String modelid,
			final String operid,final String starttime,final String endtime,final String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllBymodelopertiems( request , curPage, 
				  pageSize,userid, modelid, operid,
				starttime, endtime, issucess);
		return list;
	}

	/**
	 * 查询某个用户的所有操作日志
	 * 
	 * @param userid
	 * @return
	 */
	public List getAllByUserIDs(String userid, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllByUserIDs(userid, issucess);
		return list;
	}

	/**
	 * 查询某个模块的日志信息
	 * 
	 * @param modelid
	 * @return
	 */
	public List getAllBymodelids(String modelid, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllBymodelids(modelid, issucess);

		return list;

	}

	/**
	 * 查询某个用户 对某个模块的操作情况
	 * 
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getAllByuseridandModelids(String userid, String modelid,
			String issucess) {

		List list = new ArrayList();
		list = logopermanager.getAllByuseridandModelids(userid, modelid,
				issucess);
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		return list;
	}

	/**
	 * 查询某个用户对某个模块某个业务的操作情况
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getAllbyUseridModelidOperids(final Integer curPage, 
			final Integer pageSize,String userid, String modelid,
			String operid, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllbyUseridModelidOperids(  curPage, 
				  pageSize,userid, modelid,
				operid, issucess);
		return list;
	}

	/**
	 * 查询某个模块 的某个业务的操作情况
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getAllbyModelidAndOperids(String modelid, String operid,
			String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllbyModelidAndOperids(modelid, operid,
				issucess);
		return list;
	}

	/**
	 * 根据userid查询某个用户某个时间段的日志信息
	 * 
	 * @param userid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllByUseridAndTimes(String userid, String starttime,
			String endtime, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllByUseridAndTimes(userid, starttime,
				endtime, issucess);
		return list;
	}

	/**
	 * 更加模块ID 查询某个模块在某个时间段的日志信息
	 * 
	 * @param modelid
	 * @param starttiem
	 * @param endtime
	 * @return
	 */
	public List getAllByModelidAndTimes(String modelid, String starttiem,
			String endtime, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllByModelidAndTimes(modelid, starttiem,
				endtime, issucess);
		return list;
	}

	/**
	 * 根据用户ID 模块ID 查询某个时间段某个用户对某个模块的日志信息
	 * 
	 * @param userid
	 * @param modelid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllByUMIDandTimes(String userid, String modelid,
			String starttime, String endtime, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllByUMIDandTimes(userid, modelid, starttime,
				endtime, issucess);
		return list;
	}

	/**
	 * 根据模块ID 操作ID 查询某个时间段某个模块的某个操作的日志信息
	 * 
	 * @param modelid
	 * @param operid
	 * @param startime
	 * @param endtime
	 * @return
	 */
	public List getAllByMidAndOperidAndTimes(String modelid, String operid,
			String startime, String endtime, String issucess) {

		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllByMidAndOperidAndTimes(modelid, operid,
				startime, endtime, issucess);
		return list;
	}
	
	/**
	 * 根据时间段删除日志
	 */
	public void removeNoteBytimes(String modelid,String operid,String begintime,String endtime){
		try{
		List list = new ArrayList();
		logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorManager");
		list = logopermanager.getAllByMidAndOperidAndTimes(modelid, operid,
				begintime, endtime, "all");
		if( list != null){
			TawCommonLogOperator operm = new TawCommonLogOperator();
			for( int i=0;i<list.size();i++){
				operm = (TawCommonLogOperator)list.get(i);
				logopermanager.removeTawCommonLogOperator(operm.getId());
			}
			
		}
		}catch(Exception ex){
			BocoLog.error(this, "删除日志出错.");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public TawCommonLogDeployDao getTawlogdeoploydao() {
		return tawlogdeoploydao;
	}

	public void setTawlogdeoploydao(TawCommonLogDeployDao tawlogdeoploydao) {
		this.tawlogdeoploydao = tawlogdeoploydao;
	}

	public TawCommonLogOperatorManager getLogopermanager() {
		return logopermanager;
	}

	public void setLogopermanager(TawCommonLogOperatorManager logopermanager) {
		this.logopermanager = logopermanager;
	}

}
