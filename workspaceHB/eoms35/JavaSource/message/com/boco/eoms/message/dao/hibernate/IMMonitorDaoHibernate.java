package com.boco.eoms.message.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.dao.IIMMonitorDao;

import com.boco.eoms.message.model.IMMonitor;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.util.MsgStaticVariable;
import com.boco.eoms.commons.system.user.model.*;
/****
 * 
 * @author 卓璐
 *@version 1.0
 */
public class IMMonitorDaoHibernate extends BaseDaoHibernate implements IIMMonitorDao {

	public void closeIM(String serviceId, String buizId, String userId) {
		//暂缓
	}
  
	public void closeIM(String serviceId, String buizId) {
	
		//暂缓
	}

	public ArrayList getChildList(String parentId) {
	
		return null;
	}

	public IMMonitor getIMMonitor(String id) {
		IMMonitor iMMonitor = (IMMonitor) getHibernateTemplate().get(
				IMMonitor.class, id);
		if (iMMonitor == null) {
			throw new ObjectRetrievalFailureException(IMMonitor.class, id);
		}

		return iMMonitor;
		
		
	}

	public List getIMMonitors(final IMMonitor iMMonitor) {
		return getHibernateTemplate().find("from IMMonitor");
	}

	public Map getIMMonitors(Integer curPage, Integer pageSize) {
		return this.getIMMonitors(curPage, pageSize, null);
	
	}

	public Map getIMMonitors(final Integer curPage,final Integer pageSize,final String whereStr) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
		String queryStr = "from IMMonitors";
		if (whereStr != null && whereStr.length() > 0)
			queryStr += whereStr;
		String queryCountStr = "select count(*) " + queryStr;

		Integer total = (Integer) session.createQuery(queryCountStr)
				.iterate().next();
		Query query = session.createQuery(queryStr);
		query.setFirstResult(pageSize.intValue()
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

	public List listNeedSendIM() {
		String curTime = StaticMethod.getCurrentDateTime();
		List monitorList = new ArrayList();
		String hql = "";
		// 目前只考虑按加急发送排序，还要考虑按发送时间排序
//		if (MsgStaticVariable.DB_ORACLE.equals(MsgHelp.dbType)) {
//			// oracle版本语句
//			hql = "from IMMonitor	where dispatch_time<to_date('" + curTime
//					+ "','yyyy-mm-dd hh24:mi:ss')  order by is_send_imediat desc";// 排序使紧急发送的排在前面
//		} else {
			// informix版本语句
			hql = "from IMMonitor	where dispatch_time<'"+curTime+"' order by is_send_imediat desc";// 排序使紧急发送的排在前面

	//	}
		monitorList = this.getHibernateTemplate().find(hql);
		return monitorList;
	}

	public void removeIMMonitor(String id) {
		getHibernateTemplate().delete(getIMMonitor(id));

	}

	public void saveIMMonitor(IMMonitor monitor) {
		if ((monitor.getId() == null) || (monitor.getId().equals("")))
		{
			getHibernateTemplate().save(monitor);
		}
		else{
			getHibernateTemplate().saveOrUpdate(monitor);
		}
	}




	public Object getObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getObjects(Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub

	}

	public void saveObject(Object o) {
		// TODO Auto-generated method stub

	}

	public String getUserPwdByUserid(String userid) {
		//待测试
		SessionFactory sf=getHibernateTemplate().getSessionFactory();
		Session session=sf.openSession();
	
		session.beginTransaction();
		Query q=session.createSQLQuery("select password from taw_system_user where userid='"+userid+"'");
		List list=q.list();
		if(list!=null&&list.size()!=0)
		{
			return (String)list.get(0);
		}else
		{
			return null;
		}
	}

	public List selectUserListByUserList(List userList) {
		SessionFactory sf=getHibernateTemplate().getSessionFactory();
		Session session=sf.openSession();
	
		session.beginTransaction();
//		Query q=session.createSQLQuery("select userid from taw_system_user ");
//		List list=q.list();
//		List outList=new ArrayList();
//		for(int i=0;i<list.size();i++)
//		{
//			for(int i2=0;i2<userList.size();i2++)
//			{
//				
//				
//			}
//		}
		Criteria c=session.createCriteria(TawSystemUser.class);
		c.add(Restrictions.not(Restrictions.in("userid",userList.toArray())));
		
		
		
		return c.list();
	}

}
