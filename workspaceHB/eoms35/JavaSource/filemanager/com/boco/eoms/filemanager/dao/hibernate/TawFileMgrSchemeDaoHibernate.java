package com.boco.eoms.filemanager.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.filemanager.dao.ITawFileMgrSchemeDao;
import com.boco.eoms.filemanager.model.TawFileMgrScheme;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawFileMgrSchemeDaoHibernate extends BaseDaoHibernate implements
		ITawFileMgrSchemeDao {


	public List getTawFileMgrScheme(final TawFileMgrScheme tawFileMgrcheme) {
		return getHibernateTemplate().find("from TawFileMgrScheme");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemDept == null) {
		 * return getHibernateTemplate().find("from TawSystemDept"); } else { //
		 * filter on properties set in the tawSystemDept HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawSystemDept).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawSystemDept.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	public TawFileMgrScheme getTawFileMgrScheme(final Integer scheme_id) {

		TawFileMgrScheme tawFileMgrScheme = (TawFileMgrScheme) getHibernateTemplate()
				.get(TawFileMgrScheme.class, scheme_id);
		if (tawFileMgrScheme == null) {
			BocoLog.warn(this, "uh oh, tawFileMgrScheme with file_mgr_scheme_id '" + scheme_id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(TawFileMgrScheme.class, scheme_id);
		}

		return tawFileMgrScheme;
	}

	public void saveTawFileMgrScheme(final TawFileMgrScheme tawFileMgrScheme) {
		if ((tawFileMgrScheme.getFile_mgr_scheme_id() == null)
				|| (tawFileMgrScheme.getFile_mgr_scheme_id().equals("")))
			getHibernateTemplate().save(tawFileMgrScheme);
		else
			getHibernateTemplate().saveOrUpdate(tawFileMgrScheme);
	}


	public void removeTawFileMgrScheme(final Integer scheme_id) {
		getHibernateTemplate().delete(getTawFileMgrScheme(scheme_id));
	}

	/**
	 * 根据专题id 查询派发数据信息
	 * 
	 * @param topic_id
	 * @return
	 */
	public TawFileMgrScheme getSchemeinfobyschemeid(String scheme_id) {
		TawFileMgrScheme fileMgrScheme = null;
		String hql = " from TawFileMgrScheme filemgrscheme where filemgrscheme.file_mgr_scheme_id= '"
				+ scheme_id + "'" ;
		List list = getHibernateTemplate().find(hql);
		if (list != null) {
			if (!list.isEmpty()) {
				fileMgrScheme = (TawFileMgrScheme) list.iterator().next();
			}
		}
		return fileMgrScheme;
	}

	/**
	 * 查询所有派发内容
	 * 
	 * @return
	 */
	public List getFileMgrScheme() {

		String hql = " from TawFileMgrScheme filemgrscheme ";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 得到派发列表
	 * 
	 * @param partopicid
	 * @return
	 */
	public List getNextLevecScheme(String topic_id) {

		String hql = " from TawFileMgrScheme filemgrscheme where filemgrscheme.topic_id='"
				+ topic_id + "'";
		List list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 得到该用户能够看到的派发单
	 */
	public List getNextLevecScheme(String topic_id, String create_user, String accept_dept_id) {
		
		String hql = " from TawFileMgrScheme filemgrscheme where filemgrscheme.topic_id=" + topic_id +
				" and (filemgrscheme.create_user_id='" + create_user + "'"+
					"or (filemgrscheme.accept_dept_id like '" + accept_dept_id + ",%'" +
						" or filemgrscheme.accept_dept_id like '%," + accept_dept_id + ",%'" +
						" or filemgrscheme.accept_dept_id like '%," + accept_dept_id +"'" +
						")" +
					 ")";
		List list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 判断此结点下没有派发列表
	 */
	public boolean notScheme(String topic_id) {
		String hql = " from TawFileMgrScheme filemgrscheme where filemgrscheme.topic_id='"
				+ topic_id + "'";
		List list = getHibernateTemplate().find(hql);
		if (list.size() == 0){
			return true;
		}
		return false;
	}

	
}
