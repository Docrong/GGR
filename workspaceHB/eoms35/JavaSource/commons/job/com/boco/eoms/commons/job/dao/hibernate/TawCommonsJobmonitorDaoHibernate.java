package com.boco.eoms.commons.job.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.util.JobStaticVariable;
import com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务监控DAO实现类
 * </p>
 * <p>Apr 10, 2007 10:33:16 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsJobmonitorDaoHibernate extends BaseDaoHibernate
		implements TawCommonsJobmonitorDao {

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao#getTawCommonsJobmonitors(com.boco.eoms.commons.job.model.TawCommonsJobmonitor)
	 */
	public List getTawCommonsJobmonitors(
			final TawCommonsJobmonitor tawCommonsJobmonitor) {
		return getHibernateTemplate().find("from TawCommonsJobmonitor order by executeStartTime desc");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonsJobmonitor ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonsJobmonitor"); } else { // filter on properties set in the
		 * tawCommonsJobmonitor HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonsJobmonitor).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonsJobmonitor.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao#getTawCommonsJobmonitor(String
	 *      id)
	 */
	public TawCommonsJobmonitor getTawCommonsJobmonitor(final String id) {
		TawCommonsJobmonitor tawCommonsJobmonitor = (TawCommonsJobmonitor) getHibernateTemplate()
				.get(TawCommonsJobmonitor.class, id);
		if (tawCommonsJobmonitor == null) {
			BocoLog.warn(this,"uh oh, tawCommonLogDeploy with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonsJobmonitor.class, id);
		}

		return tawCommonsJobmonitor;
	}

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao#saveTawCommonsJobmonitor(TawCommonsJobmonitor
	 *      tawCommonsJobmonitor)
	 */
	public void saveTawCommonsJobmonitor(
			final TawCommonsJobmonitor tawCommonsJobmonitor) {
		if ((tawCommonsJobmonitor.getId() == null)
				|| (tawCommonsJobmonitor.getId().equals("")))
			getHibernateTemplate().save(tawCommonsJobmonitor);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonsJobmonitor);
	}

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao#removeTawCommonsJobmonitor(String
	 *      id)
	 */
	public void removeTawCommonsJobmonitor(final String id) {
		getHibernateTemplate().delete(getTawCommonsJobmonitor(id));
	}

	/**
	 * 根据订阅号获取订阅信息
	 * @author 秦敏
	 * @param subId 订阅号
	 * @return
	 */
    public TawCommonsJobmonitor getJobMonitorBySubId(String subId) {
		TawCommonsJobmonitor tawCommonsJobmonitor = null;
		String Hsql = "from TawCommonsJobmonitor  as tawCommonsJobmonitor where "
				+ "tawCommonsJobmonitor.jobSubId='"
				+ subId
				+ "' and tawCommonsJobmonitor.status="
				+ JobStaticVariable.STATUS_RUNNING;
		List list = getHibernateTemplate().find(Hsql);
		if (list.size() > 0) {
			tawCommonsJobmonitor = (TawCommonsJobmonitor) list.get(0);
		}
		return tawCommonsJobmonitor;
	}

    /**
	 * 获取当前所有的正在运行的任务
	 * @return
	 * @author 秦敏
	 */
	public List getRunningJob() {
		String Hsql = "from TawCommonsJobmonitor  as tawCommonsJobmonitor where tawCommonsJobmonitor.status="
				+ JobStaticVariable.STATUS_RUNNING;
		List list = getHibernateTemplate().find(Hsql);
		return list;
	}
	/**
	 * 删除当前所有正在运行的任务
	 * 
	 * @author 秦敏
	 */
	public void removeRunningJob() {
		String Hsql = "from TawCommonsJobmonitor  as tawCommonsJobmonitor where tawCommonsJobmonitor.status="
				+ JobStaticVariable.STATUS_RUNNING;
		List list = getHibernateTemplate().find(Hsql);
		for (int i = 0; i < list.size(); i++) {
			TawCommonsJobmonitor tawCommonsJobmonitor = (TawCommonsJobmonitor) list
					.get(i);
			removeTawCommonsJobmonitor(tawCommonsJobmonitor.getId());
		}

	}
	/**
	 * 获取最近一次执行job的时间
	 * @param subId 订阅号
	 * @return
	 */
	public TawCommonsJobmonitor getLastJobmonitorBySubId(String subId){
		TawCommonsJobmonitor tawCommonsJobmonitor = null;
		String Hsql = "from TawCommonsJobmonitor  tawCommonsJobmonitor where tawCommonsJobmonitor.jobSubId='"+ subId +"' order by tawCommonsJobmonitor.executeEndTime desc";
		List list = getHibernateTemplate().find(Hsql);
		if(list.size()>0){
			tawCommonsJobmonitor = (TawCommonsJobmonitor)list.get(0);
		}
		return tawCommonsJobmonitor;
	}

}
