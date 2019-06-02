package com.boco.eoms.commons.statistic.customstat.dao.hibernate;


import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务订阅service实现类
 * </p>
 * <p>Apr 10, 2007 10:50:36 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class StJob extends BaseManager implements
		ITawCommonsJobsubscibeManager {
	private TawCommonsJobsubscibeDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonsJobsubscibeDao(TawCommonsJobsubscibeDao dao) {
		this.dao = dao;
	}

	public TawCommonsJobsubscibeDao getDao() {
		return dao;
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager#getTawCommonsJobsubscibes(com.boco.eoms.commons.job.model.TawCommonsJobsubscibe)
	 */
	public List getTawCommonsJobsubscibes( ) {
		return dao.getTawCommonsJobsubscibes();
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager#getTawCommonsJobsubscibe(String
	 *      id)
	 */
	public TawCommonsJobsubscibe getTawCommonsJobsubscibe(final String id) {
		return dao.getTawCommonsJobsubscibe(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager#saveTawCommonsJobsubscibe(TawCommonsJobsubscibe
	 *      tawCommonsJobsubscibe)
	 */
	public void saveTawCommonsJobsubscibe(
			TawCommonsJobsubscibe tawCommonsJobsubscibe, boolean isNew)
	          throws ScheduleRunException {
		
		tawCommonsJobsubscibe.setSubscribeTime(StaticMethod.getLocalString());
		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager#removeTawCommonsJobsubscibe(String
	 *      id)
	 */
	public void removeTawCommonsJobsubscibe(final String id) throws ScheduleRunException  {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = dao
				.getTawCommonsJobsubscibe(id);
		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsJobmonitorManager");
		mgr.instance();
		mgr.deleteJob(tawCommonsJobsubscibe.getSubId());
		dao.removeTawCommonsJobsubscibe(new String(id));
	}

	/**
	 * 生成任务订阅号
	 * 
	 * @param jobSortId 任务类型ID
	 * @return
	 */
	public String newSubId(String jobSortId) {
		String subId = "JOB";
		String strYYMMDD = StaticMethod
				.getYYMMDD(StaticMethod.getLocalString());
		subId += "-" + jobSortId + "-" + strYYMMDD;
		int xyz = dao.getCountNum(subId) + 1;
		String strXYZ = String.valueOf(1000 + xyz);
		subId += "-" + strXYZ.substring(1);
		return subId;
	}
	
	/**
	  * 根据任务类型ID号查询任务订阅信息
	  * @param jobSortId 任务类型ID号
	  * @return
	  * @author 秦敏
	  */
	public List getSubscibeListBySortId(Integer jobSortId){
		List list=dao.getSubscibeListBySortId(jobSortId);
		return list;
	}

	public TawCommonsJobsubscibe getTawCommonsJobsubscibeForSubID(String subID) {
		// TODO Auto-generated method stub
		return null;
	}
}
