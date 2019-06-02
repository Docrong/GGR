package com.boco.eoms.taskplan.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.taskplan.dao.TaskplanqueryDao;


public class TaskplanqueryDaoHibernate extends BaseDaoHibernate
implements TaskplanqueryDao{
	
	
	public Map getTaskplanbycon(final Integer curPage, final Integer pageSize,
			final  String project_name, final String project_decompose,
			final String deptid,final String stakeholders,final String fenye){
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {

		StringBuffer hql = new StringBuffer();
		StringBuffer hq2 = new StringBuffer();
		hql.append("from Taskqueryplan tawqueryplan where 1=1 ");
		hq2.append("select count(distinct tawqueryplan.id) from Taskqueryplan tawqueryplan where 1=1 ");
		
		if(project_name!=null&&!project_name.equals("")){
			hql.append(" and tawqueryplan.project_name='" + project_name.trim() + "'");
			hq2.append(" and tawqueryplan.project_name='" + project_name.trim() + "'");
		}
		if(project_decompose!=null&&!project_decompose.equals("") ){
			hql.append(" and tawqueryplan.project_decompose='" + project_decompose.trim() + "'");
			hq2.append(" and tawqueryplan.project_decompose='" + project_decompose.trim() + "'");
		}
		if(deptid!=null&&!deptid.equals("")){
			hql.append(" and tawqueryplan.deptid='" + deptid.trim() + "'");
			hq2.append(" and tawqueryplan.deptid='" + deptid.trim() + "'");
		}
		if(stakeholders!=null&&!stakeholders.equals("")){
			hql.append(" and tawqueryplan.stakeholders='" + stakeholders.trim() + "'");
			hq2.append(" and tawqueryplan.stakeholders='" + stakeholders.trim() + "'");
		}
		hql.append(" and tawqueryplan.task_decompose != '' order by tawqueryplan.project_name,tawqueryplan.project_decompose,tawqueryplan.deptid");
		hq2.append(" and tawqueryplan.task_decompose != '' ");
		
		String hqls = hql.toString();
		String hqls2 = hq2.toString();
		Query countQuery = session.createQuery(hqls2);
	

		Integer total = (Integer) countQuery.iterate().next();
		Query query = session.createQuery(hqls);
	
		
		
//		if(fenye.equals("0")){
//			Integer a = (Integer) countQuery.iterate().next();
//			query.setFirstResult(a.intValue());
//		}
//		else{
			query.setFirstResult(pageSize.intValue()
					* (curPage.intValue()));
		//}
		if(fenye.equals("0")){
			Integer a = (Integer) countQuery.iterate().next();
			query.setMaxResults(a.intValue());	
		}else{
			query.setMaxResults(pageSize.intValue());	
		}
		
		
		
		List result = query.list();
		HashMap map = new HashMap();
		map.put("total", total);
		map.put("result", result);
		return map;
			}
		};
		//list = (ArrayList) getHibernateTemplate().find(hqls);
		return (Map) getHibernateTemplate().execute(callback);
	}
	
}
