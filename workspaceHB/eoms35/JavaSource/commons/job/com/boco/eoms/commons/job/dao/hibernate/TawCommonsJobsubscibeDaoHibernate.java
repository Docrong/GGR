package com.boco.eoms.commons.job.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务订阅DAO实现类
 * </p>
 * <p>Apr 10, 2007 10:34:29 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsJobsubscibeDaoHibernate extends BaseDaoHibernate
		implements TawCommonsJobsubscibeDao {

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao#getTawCommonsJobsubscibes(com.boco.eoms.commons.job.model.TawCommonsJobsubscibe)
	 */
	public List getTawCommonsJobsubscibes() {
		return getHibernateTemplate().find("from TawCommonsJobsubscibe as tawCommonsJobsubscibe where tawCommonsJobsubscibe.deleted=0");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonsJobsubscibe ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonsJobsubscibe"); } else { // filter on properties set in the
		 * tawCommonsJobsubscibe HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonsJobsubscibe).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonsJobsubscibe.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao#getTawCommonsJobsubscibe(String
	 *      id)
	 */
	public TawCommonsJobsubscibe getTawCommonsJobsubscibe(final String id) {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = (TawCommonsJobsubscibe) getHibernateTemplate()
				.get(TawCommonsJobsubscibe.class, id);
		if (tawCommonsJobsubscibe == null) {
			BocoLog.warn(this,"uh oh, tawCommonLogDeploy with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonsJobsubscibe.class, id);
		}

		return tawCommonsJobsubscibe;
	}
	
	public TawCommonsJobsubscibe getTawCommonsJobsubscibeForSubID(final String subId) {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = null;
		String Hsql = "from TawCommonsJobsubscibe  as tawCommonsJobsubscibe where tawCommonsJobsubscibe.subId='"
				+ subId + "'";
		List list = getHibernateTemplate().find(Hsql);
		if (!list.isEmpty()) {
			tawCommonsJobsubscibe = (TawCommonsJobsubscibe) list.get(0);
		}
		return tawCommonsJobsubscibe;
	}

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao#saveTawCommonsJobsubscibe(TawCommonsJobsubscibe
	 *      tawCommonsJobsubscibe)
	 */
	public void saveTawCommonsJobsubscibe(
			final TawCommonsJobsubscibe tawCommonsJobsubscibe) {
		if ((tawCommonsJobsubscibe.getId() == null)
				|| (tawCommonsJobsubscibe.getId().equals("")))
			getHibernateTemplate().save(tawCommonsJobsubscibe);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonsJobsubscibe);
	}

	/**
	 * @see com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao#removeTawCommonsJobsubscibe(String
	 *      id)
	 */
	public void removeTawCommonsJobsubscibe(final String id) {
		getHibernateTemplate().delete(getTawCommonsJobsubscibe(id));
	}
	/**
     * 根据订阅号获取指定的订阅信息
     * @param subId 订阅号
     * @return
     * @author 秦敏
     */
	public TawCommonsJobsubscibe getSubscibeJobById(String subId) {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = null;
		String Hsql = "from TawCommonsJobsubscibe  as tawCommonsJobsubscibe where tawCommonsJobsubscibe.subId='"
				+ subId + "'";
		List list = getHibernateTemplate().find(Hsql);
		if (!list.isEmpty()) {
			tawCommonsJobsubscibe = (TawCommonsJobsubscibe) list.get(0);
		}
		return tawCommonsJobsubscibe;
	}
	/**
	 * 根据订阅号获取与该订阅号雷同的订阅号数目
	 * @param subId 订阅号 （只有前面三部分，例如：JOB-1-070410）
	 * @return
	 * @author 秦敏
	 */
	public int getCountNum(String subId) {
		int countNum = 0;
		List list = new ArrayList();
		String hsql = "select count(*) from TawCommonsJobsubscibe  as tawCommonsJobsubscibe "
				+ "where tawCommonsJobsubscibe.subId like '" + subId + "%'";
		list = getHibernateTemplate().find(hsql);
		if (!list.isEmpty()) {
			countNum = ((Integer) list.get(0)).intValue();
		}
		return countNum;
	}
   /**
    * 根据任务类型ID号查询任务订阅信息
    * @param jobSortId 任务类型ID号
    * @return
    * @author 秦敏
    */
   public List getSubscibeListBySortId(Integer jobSortId) {
	   List list=new ArrayList();
	   String hsql="from TawCommonsJobsubscibe  as tawCommonsJobsubscibe "+
	               "where tawCommonsJobsubscibe.jobSortId="+jobSortId;
	   list=getHibernateTemplate().find(hsql);
	   return list;
	}
}
