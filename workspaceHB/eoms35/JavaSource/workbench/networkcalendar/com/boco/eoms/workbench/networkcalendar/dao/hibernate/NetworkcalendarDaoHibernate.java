package com.boco.eoms.workbench.networkcalendar.dao.hibernate;

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
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.workbench.networkcalendar.dao.NetworkcalendarDao;
import com.boco.eoms.workbench.networkcalendar.model.Schedule;

public class NetworkcalendarDaoHibernate extends BaseDaoHibernate implements
		NetworkcalendarDao {

	public String save(Schedule schedule) {
		if (schedule.getId() == null || "".equals(schedule.getId())) {
			getHibernateTemplate().save(schedule);
		} else {
			getHibernateTemplate().saveOrUpdate(schedule);
		}
		String id=schedule.getId();
		return id;
	}

	public Map getNetworkcalendarList(final Integer curPage,
			final Integer pageSize, String date,String userId) {
		// String callingTime = form.getCallingTime();
		String dateTime =getLocalString();
		String hql = "from Schedule schedule where schedule.taskday='" + date + "' and schedule.userId='"+userId+"' and schedule.tasktime>'"+dateTime+"' or schedule.tasktime='"+dateTime+"'";

		return search(curPage, pageSize, hql);

	}
	
	public Map getNetworkcalendarHistoryList(final Integer curPage,
			final Integer pageSize, String date,String userId) {
		// String callingTime = form.getCallingTime();
		String dateTime =getLocalString();
		String hql = "from Schedule schedule where schedule.taskday='" + date + "' and schedule.userId='"+userId+"' and schedule.tasktime<'"+dateTime+"'";

		return search(curPage, pageSize, hql);

	}

	public Map search(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		// filter on properties set in the forums
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "";

				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + whereStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				if(queryStr.indexOf("schedule.tasktime>")>0){
					queryStr+=" order by schedule.tasktime asc";
				}else{
					queryStr+=" order by schedule.tasktime desc";
				}
					
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
//				if(pageSize.intValue()!=0){
//				query.setMaxResults(pageSize.intValue());
//				}
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public void removeSchedule(Schedule schedule) {

		getHibernateTemplate().delete(schedule);

	}

	public Schedule getSchedule(String id) {

		Schedule schedule = (Schedule) getHibernateTemplate().get(
				Schedule.class, id);

		return schedule;

	}
	public static String getLocalString() {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		String date = dateFormat.format(currentDate);

		return date;
	}
}
