package com.boco.eoms.workbench.infopub.dao.hibernate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.log4j.Logger;
import com.boco.eoms.workbench.infopub.dao.ThreadDao;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;

/**
 * 
 * <p>
 * Title:版块下信息发布（操作）dao的hibernate实现
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:01:32 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadDaoHibernate extends BaseDaoHibernate implements ThreadDao,
		ID2NameDAO {

	/**
	 * log4j
	 */
	private final static Logger logger = Logger
			.getLogger(ThreadDaoHibernate.class);

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#getThreads(com.boco.eoms.workbench.infopub.model.Thread)
	 */
	public List getThreads(final Thread thread) {
		return getHibernateTemplate().find("from Thread");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (thread == null) { return
		 * getHibernateTemplate().find("from Thread"); } else { // filter on
		 * properties set in the thread HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(thread).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(Thread.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/*
	 * public List getThreads(final String whereStr) { return
	 * getHibernateTemplate().find("from Thread where 1=1"+whereStr); }
	 */
	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#getThread(String id)
	 */
	public Thread getThread(final String id) {
		Thread thread = (Thread) getHibernateTemplate().get(Thread.class, id);
		if (thread == null) {
			throw new ObjectRetrievalFailureException(Thread.class, id);
		}

		return thread;
	}

	/*
	 * public List getThreads(final String whereStr) { HibernateCallback
	 * callback = new HibernateCallback() { public Object doInHibernate(Session
	 * session) throws HibernateException { String queryStr = "from Thread
	 * thread where 1=1 "+whereStr; Query query = session.createQuery(queryStr);
	 * return query.list(); } }; return (List)
	 * getHibernateTemplate().execute(callback); }
	 */

	public List getThreadList(final String whereStr) {
		String queryStr1 = "from Thread thread ,ThreadPermimissionOrg threadPermissionOrg"
				+ " where (thread.status='"
				+ InfopubConstants.AUDIT_PASS
				+ "' or thread.status='"
				+ InfopubConstants.NO_AUDIT
				+ "' or thread.status='" + InfopubConstants.ROTATION + "') ";
		String queryStr = "select distinct thread " + queryStr1;

		if (whereStr != null && whereStr.length() > 0) {
			queryStr += whereStr;
			queryStr1 += whereStr;
		}
		// select t.* from ifp_thread t,ifp_thread_permission_org o
		// where t.is_del<>'1' and o.org_id like '1%'
		// 带有排序的sql语句
		String queryWithOrderbyStr = queryStr
				+ " order by thread.editTime desc";
		return getHibernateTemplate().find(queryWithOrderbyStr);

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (thread == null) { return
		 * getHibernateTemplate().find("from Thread"); } else { // filter on
		 * properties set in the thread HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(thread).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(Thread.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#saveThread(Thread
	 *      thread)
	 */
	public void saveThread(final Thread thread) {
		if ((thread.getId() == null) || (thread.getId().equals("")))
			getHibernateTemplate().save(thread);
		else
			getHibernateTemplate().saveOrUpdate(thread);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#removeThread(String
	 *      id)
	 */
	public void removeThread(final String id) {
		getHibernateTemplate().delete(getThread(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getThreads(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		// filter on properties set in the thread
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// select t.* from ifp_thread t,ifp_thread_permission_org o
				// where t.is_del<>'1' and o.org_id like '1%'
				String queryStr1 = "from Thread thread ,ThreadPermimissionOrg threadPermissionOrg"
						+ " where (thread.status='"
						+ InfopubConstants.AUDIT_PASS
						+ "' or thread.status='"
						+ InfopubConstants.NO_AUDIT
						+ "' or thread.status='"
						+ InfopubConstants.ROTATION + "') ";
				String queryStr = "select distinct thread " + queryStr1;

				if (whereStr != null && whereStr.length() > 0) {
					queryStr += whereStr;
					queryStr1 += whereStr;
				}
				// select t.* from ifp_thread t,ifp_thread_permission_org o
				// where t.is_del<>'1' and o.org_id like '1%'
				// 带有排序的sql语句
				String queryWithOrderbyStr = queryStr
						+ " order by thread.editTime desc";
				String queryCountStr = "select count(distinct thread.id) "
						+ queryStr1;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryWithOrderbyStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getThreads(final String whereStr) {
		// filter on properties set in the thread
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// select t.* from ifp_thread t,ifp_thread_permission_org o
				// where t.is_del<>'1' and o.org_id like '1%'
				String queryStr1 = "from Thread thread ,ThreadPermimissionOrg threadPermissionOrg"
						+ " where (thread.status='"
						+ InfopubConstants.AUDIT_PASS
						+ "' or thread.status='"
						+ InfopubConstants.NO_AUDIT
						+ "' or thread.status='"
						+ InfopubConstants.ROTATION + "') ";
				String queryStr = "select distinct thread " + queryStr1;

				if (whereStr != null && whereStr.length() > 0) {
					queryStr += whereStr;
					queryStr1 += whereStr;
				}
				// select t.* from ifp_thread t,ifp_thread_permission_org o
				// where t.is_del<>'1' and o.org_id like '1%'
				// 带有排序的sql语句
				String queryWithOrderbyStr = queryStr
						+ " order by thread.editTime desc";
				String queryCountStr = "select count(distinct thread.id) "
						+ queryStr1;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryWithOrderbyStr);

				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getThreads(final Integer curPage, final Integer pageSize) {
		return this.getThreads(curPage, pageSize, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(String id) throws DictDAOException {
		Thread thread = this.getThread(id);
		return thread != null ? thread.getTitle() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#getUnreadThreads(java.lang.Integer,
	 *      java.lang.Integer,java.lang.String,java.lang.String)
	 */
	public Map getUnreadThreads(final Integer curPage, final Integer pageSize,
			final String userId, final String whereStr) {
		// filter on properties set in the thread
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Thread thread,ThreadPermimissionOrg threadPermissionOrg where (thread.status='"
						+ InfopubConstants.AUDIT_PASS
						+ "' or thread.status='"
						+ InfopubConstants.NO_AUDIT
						+ "') and (thread.isDel<>:isDel) ";

				// 若条件不为空，则查询语句加入条件
				if (null != whereStr) {
					queryStr += whereStr;
				}
				// 用于count的串
				String countStr = queryStr
						+ " and (thread.id not in (select h.threadId from ThreadHistory h where h.userId=:userId)) ";
				// 用于查询
				queryStr = "select distinct thread " + countStr
						+ " order by thread.threadTypeId,thread.editTime desc";

				String queryCountStr = "select count(distinct thread.id) "
						+ countStr;

				Query countQuery = session.createQuery(queryCountStr);
				countQuery.setString("isDel", Constants.DELETED_FLAG);
				countQuery.setString("userId", userId);

				Integer total = (Integer) countQuery.iterate().next();
				Query query = session.createQuery(queryStr);
				query.setString("isDel", Constants.DELETED_FLAG);
				query.setString("userId", userId);

				List list = query.list();

				Date currentTime = new Date();
				List inTimeResult = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Thread thread = (Thread) list.get(i);
//					String currentTimeAdd = StaticMethod
//							.getTimeBeginString(-StaticMethod.null2int(thread
//									.getValidity()) + 1);
//					Date dCurrentTimeAdd;
//					try {
//						dCurrentTimeAdd = new java.text.SimpleDateFormat(
//								"yyyy-MM-dd hh:mm:ss").parse(currentTimeAdd);
//						 if (thread.getCreateTime().after(dCurrentTimeAdd)) {
//						//if (currentTime.before(dCurrentTimeAdd)) {// modify by
//																	// sunshengtai
//																	// at
//																	// 09-2-24,getTimeBeginString这个方法的目地是要将时间提前有效期的天数，但是方法返回的却是创建时间之后的有效期天数，所以未读信息永远为空
//							inTimeResult.add(thread);
//						}
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
// wangsixuan:以上时间逻辑有问题，主要是如果有效期较大，比如33，getTimeBeginString(-StaticMethod.null2int(thread.getValidity()) + 1)方法异常
					Date date = thread.getCreateTime();	
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DAY_OF_MONTH, StaticMethod.null2int(thread.getValidity()));
					date = calendar.getTime();
					Date nowDate = new Date();
					if(nowDate.before(date)){
						inTimeResult.add(thread);
					}
				}
//				inTimeResult.subList(pageSize.intValue()*(curPage.intValue()), pageSize.intValue()*(curPage.intValue())+curPageSize);
//				query
//						.setFirstResult(pageSize.intValue()
//								* (curPage.intValue()));
//				query.setMaxResults(pageSize.intValue());
				
				int curPageSize = inTimeResult.size() - pageSize.intValue()*(curPage.intValue());
				if(curPageSize > pageSize.intValue())
					curPageSize = pageSize.intValue();

				List result = new ArrayList();
				int fromSize = pageSize.intValue()*(curPage.intValue());
				int toSize = pageSize.intValue()*(curPage.intValue())+curPageSize;
				for(int i=fromSize;i<toSize;i++){
					result.add(inTimeResult.get(i));
				}
				HashMap map = new HashMap();
				map.put("total", Integer.valueOf(inTimeResult.size()));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#sort(java.lang.String[],
	 *      java.lang.String)
	 */
	public void sort(String[] ids, String forumsId) {
		// 若要归类的ids不为空则将Thread（信息）对象的forumsId改写为新的分类forumsId
		if (null != ids && forumsId != null) {
			for (int i = 0; i < ids.length; i++) {
				Thread thread = this.getThread(ids[i]);
				thread.setForumsId(forumsId);
				this.getHibernateTemplate().update(thread);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#getThreads4audit(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String)
	 */
	public Map getThreads4audit(final Integer curPage, final Integer pageSize,
			final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Thread thread where thread.isDel<>:isDel and (thread.status<>:status or status is null) and thread.createrId=:createrId ";

				Query countQuery = session
						.createQuery("select count(thread.id) " + queryStr);
				countQuery.setString("isDel", Constants.DELETED_FLAG);
				countQuery.setString("createrId", userId);
				countQuery.setString("status", InfopubConstants.NO_AUDIT);
				Integer total = (Integer) countQuery.iterate().next();
				Query query = session.createQuery(queryStr
						+ "order by thread.threadTypeId ,thread.editTime desc");
				query.setString("isDel", Constants.DELETED_FLAG);
				query.setString("createrId", userId);
				query.setString("status", InfopubConstants.NO_AUDIT);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadDao#listThreads(java.lang.Integer,
	 *      java.lang.Integer,
	 *      com.boco.eoms.workbench.infopub.webapp.form.ThreadForm,
	 *      java.lang.String)
	 */
	public Map listThreads(final Integer curPage, final Integer pageSize,
			final ThreadForm threadForm, final String deptStr) {

		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				// 获取查询sql
				String sql = this.getSearchSql(threadForm, deptStr);
				Query countQuery = session
						.createQuery("select count(distinct thread.id) " + sql);
				try {
					// 按时间条件设置query
					setQuery(threadForm, countQuery);
				} catch (ParseException e) {
					logger.error(e);
					throw new SQLException(e.getMessage());
				}

				Integer total = (Integer) countQuery.iterate().next();
				Query query = session
						.createQuery("select distinct thread "
								+ sql
								+ " order by thread.threadTypeId ,thread.editTime desc");
				try {
					// 按时间条件设置query
					setQuery(threadForm, query);
				} catch (ParseException e) {
					logger.error(e);
					throw new SQLException(e.getMessage());
				}
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}

			/**
			 * 返回查询语句的sql
			 * 
			 * @param threadForm
			 *            信息form
			 * @param deptStr
			 *            部门列表 信息form
			 * @return 返回查询语句的sql
			 */
			private String getSearchSql(ThreadForm threadForm, String deptStr) {
				String sql = "from Thread thread ,ThreadPermimissionOrg threadPermissionOrg"
						+ " where thread.isDel<>'"
						+ Constants.DELETED_FLAG
						+ "' ";
				// 查询开始时间 结束时间
				if (threadForm.getStartDate() != null
						&& !"".equals(threadForm.getStartDate())
						&& threadForm.getEndDate() != null
						&& !"".equals(threadForm.getEndDate())) {
					sql += " and thread.createTime>=:startDate and thread.createTime<=:endDate ";
				}
				// 仅查询大于开始时间
				else if (threadForm.getStartDate() != null
						&& !"".equals(threadForm.getStartDate())) {
					sql += " and thread.createTime>=:startDate ";
				}
				// 仅查询小于某时间
				else if (threadForm.getEndDate() != null
						&& !"".equals(threadForm.getEndDate())) {
					sql += " and thread.createTime<=:endDate ";
				}

				// TODO 模糊查询%在前会造成索引失效
				// 查询标题
				if (threadForm.getTitle() != null
						&& !"".equals(threadForm.getTitle())) {
					sql += " and thread.title like '%" + threadForm.getTitle()
							+ "%'";
				}

				// 查询版块
				if (threadForm.getForumsId() != null
						&& !"".equals(threadForm.getForumsId())) {
					sql += " and thread.forumsId='" + threadForm.getForumsId()
							+ "'";
				}

				// 查询发布人
				if (threadForm.getCreaterId() != null
						&& !"".equals(threadForm.getCreaterId())) {
					sql += " and (thread.createrId like '%"
							+ threadForm.getCreaterId()
							+ "%' or thread.createrName like '%"
							+ threadForm.getCreaterId() + "%')";
				}
				// TODO 模糊查询%在前会造成索引失效
				// 查询内容
				if (threadForm.getContent() != null
						&& !"".equals(threadForm.getContent())) {
					sql += " and thread.content like '%"
							+ threadForm.getContent() + "%'";
				}

				// 查询审核状态
				if (threadForm.getStatus() != null
						&& !"".equals(threadForm.getStatus())) {
					sql += " and thread.status ='" + threadForm.getStatus()
							+ "'";
				}

				// 查询审核状态
				if (threadForm.getThreadTypeId() != null
						&& !"".equals(threadForm.getThreadTypeId())) {
					sql += " and thread.threadTypeId ='"
							+ threadForm.getThreadTypeId() + "'";
				}

				if (deptStr != null && !"".equals(deptStr)) {
					sql += deptStr;
				}

				return sql;
			}

			/**
			 * 设置查询条件
			 * 
			 * @param threadForm
			 *            信息form
			 * @param query
			 *            查询对象
			 * @return 返回hibernate查询
			 * @throws ParseException
			 *             转换日期出错抛出异常
			 */
			private Query setQuery(ThreadForm threadForm, Query query)
					throws ParseException {
				// 查询开始时间 结束时间
				if (threadForm.getStartDate() != null
						&& !"".equals(threadForm.getStartDate())
						&& threadForm.getEndDate() != null
						&& !"".equals(threadForm.getEndDate())) {
					query.setDate("startDate", DateUtil
							.convertStringToDate(threadForm.getStartDate()));

					Date endDate = DateUtil.convertStringToDate(threadForm
							.getEndDate());
					Calendar cal = Calendar.getInstance();
					cal.setTime(endDate);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					cal.add(Calendar.DATE, 1);
					query.setDate("endDate", cal.getTime());
				}
				// TODO 以下查询目前没用
				// 仅查询大于开始时间
				else if (threadForm.getStartDate() != null
						&& !"".equals(threadForm.getStartDate())) {
					query.setDate("startDate", DateUtil
							.convertStringToDate(threadForm.getStartDate()));
				}
				// 仅查询小于某时间
				else if (threadForm.getEndDate() != null
						&& !"".equals(threadForm.getEndDate())) {
					Date endDate = DateUtil.convertStringToDate(threadForm
							.getEndDate());
					Calendar cal = Calendar.getInstance();
					cal.setTime(endDate);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					cal.add(Calendar.DATE, 1);
					query.setDate("endDate", cal.getTime());
				}
				return query;
			}
		};

		return (Map) getHibernateTemplate().execute(callback);
	}

	public Thread getSerialNoToThread(String serialNo) {
		List list = this.getHibernateTemplate().find(
				"from Thread thread where thread.serialNo='" + serialNo + "'");
		Thread thread = new Thread();
		if (list != null && list.size() > 0) {

			thread = (Thread) list.get(0);
		}
		return thread;
	}

	public String getMaxSerialNo(String serialNo) {
		String serial = "";
		List max = (List) this.getHibernateTemplate().find(
				"from Thread thread where thread.serialNo like '" + serialNo
						+ "%'" + "order by thread.serialNo desc");
		if (max.size() > 0) {
			Thread thread = new Thread();
			thread = (Thread) max.get(0);
			serial = thread.getSerialNo();

		} else {
			serial = "10001";
		}
		return serial;
	}

}
