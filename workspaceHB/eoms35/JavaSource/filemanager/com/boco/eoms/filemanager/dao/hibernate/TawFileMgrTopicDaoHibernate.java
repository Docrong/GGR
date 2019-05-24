package com.boco.eoms.filemanager.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.filemanager.dao.ITawFileMgrTopicDao;
import com.boco.eoms.filemanager.model.TawFileMgrTopic;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawFileMgrTopicDaoHibernate extends BaseDaoHibernate implements
		ITawFileMgrTopicDao {

	
	/**
	 * @see com.boco.eoms.commons.system.dept.dao.ITawSystemDeptDao#getTawSystemDepts(com.boco.eoms.commons.system.dept.model.TawFileMgrTopic)
	 */
	public List getTawFileMgrTopic(final TawFileMgrTopic tawFileMgrTopic) {
		return getHibernateTemplate().find("from TawFileMgrTopic");

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

	public TawFileMgrTopic getTawFileMgrTopic(final Integer topicid) {

		TawFileMgrTopic tawFileMgrTopic = (TawFileMgrTopic) getHibernateTemplate()
				.get(TawFileMgrTopic.class, topicid);
		if (tawFileMgrTopic == null) {
			BocoLog.warn(this, "uh oh, tawFileMgrTopic with topic_id '" + topicid
					+ "' not found...");
			throw new ObjectRetrievalFailureException(TawFileMgrTopic.class, topicid);
		}

		return tawFileMgrTopic;
	}

	public void saveTawFileMgrTopic(final TawFileMgrTopic tawFileMgrTopic) {
		if ((tawFileMgrTopic.getTopic_id() == null)
				|| (tawFileMgrTopic.getTopic_id().equals("")))
			getHibernateTemplate().save(tawFileMgrTopic);
		else
			getHibernateTemplate().saveOrUpdate(tawFileMgrTopic);
	}


	public void removeTawFileMgrTopic(final Integer topic_id) {
		getHibernateTemplate().delete(getTawFileMgrTopic(topic_id));
	}

	/**
	 * 根据专题id 查询专题信息
	 * 
	 * @param topic_id
	 * @return
	 */
	public TawFileMgrTopic getTopicinfobytopicid(String topic_id) {
		TawFileMgrTopic fileMgrTopic = null;
		String hql = " from TawFileMgrTopic filemgrtopic where filemgrtopic.topic_id='"
				+ topic_id + "'" ;
		List list = getHibernateTemplate().find(hql);
		if (list != null) {
			if (!list.isEmpty()) {
				fileMgrTopic = (TawFileMgrTopic) list.iterator().next();
			}
		}
		return fileMgrTopic;
	}

	/**
	 * 查询所有专题
	 * 
	 * @return
	 */
	public List getFileMgrTopic() {

		String hql = " from TawFileMgrTopic filemgrtopic ";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param partopicid
	 * @return
	 */
	public List getNextLevecTopic(String partopicid) {

		String hql = " from TawFileMgrTopic filemgrtopic where filemgrtopic.par_topic_id='"
				+ partopicid + "'  order by filemgrtopic.topic_order";
		List list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 是专题的叶子结点
	 */
	public boolean isLeaf(String topic_id) {
		String hql = " from TawFileMgrTopic filemgrtopic where filemgrtopic.par_topic_id='"
			+ topic_id + "'";
		List list = getHibernateTemplate().find(hql);
		if (list.size() == 0){
			return true;
		}
		return false;
	}

	/**
	 * 根据专题Id得到专题名称
	 */
	public String getNameById(String topic_id) {
		String hql = " from TawFileMgrTopic filemgrtopic where filemgrtopic.topic_id='"
			+ topic_id + "'";
		List list = getHibernateTemplate().find(hql);
		String topicName = null;
		if(list != null){
			TawFileMgrTopic tawFileMgrTopic = (TawFileMgrTopic)list.get(0);
			topicName = tawFileMgrTopic.getTopic_name();
		} else {
			topicName = null;
		}
		return topicName;
	}
	
}
