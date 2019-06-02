package com.boco.eoms.commons.failureRecord.dao.hibernate;

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
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.failureRecord.dao.FailureRecordDao;
import com.boco.eoms.commons.failureRecord.model.FailureRecord;
import com.boco.eoms.commons.failureRecord.webapp.form.FailureRecordForm;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceMonitoringDao;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;

public class FailureRecordDaoHibernate extends BaseDaoHibernate implements
FailureRecordDao {

	public Map getMonitoringLog(final Integer curPage, final Integer pageSize,
			FailureRecordForm failureRecordForm) {
		// String callingTime = form.getCallingTime();
		String hql = "from FailureRecord failureRecord";
		String shql="";
		if (failureRecordForm != null) {
			
		if (!"".equals(failureRecordForm.getTitle()) && failureRecordForm.getTitle() != null) {
			shql += " and failureRecord.title='" + failureRecordForm.getTitle()
						+ "'";
			}
			if (!"".equals(failureRecordForm.getFaulttype1()) && failureRecordForm.getFaulttype1() != null) {
				shql += " and failureRecord.faulttype1='" + failureRecordForm.getFaulttype1() + "'";
			}
			if (!"".equals(failureRecordForm.getFaulttype2()) && failureRecordForm.getFaulttype2() != null) {

				shql += " and failureRecord.faulttype2='"
						+ failureRecordForm.getFaulttype2() + "'";

			}
			if (!"".equals(failureRecordForm.getFaulttype3()) && failureRecordForm.getFaulttype3() != null) {
				shql += " and failureRecord.faulttype3='"
						+ failureRecordForm.getFaulttype3() + "'";

			}
			if (!"".equals(failureRecordForm.getFaulttype4()) && failureRecordForm.getFaulttype4() != null) {

				shql += " and  failureRecord.faulttype4='"
						+ failureRecordForm.getFaulttype4() + "'";

			}
			if (!"".equals(failureRecordForm.getFaultjudge()) && failureRecordForm.getFaultjudge()!= null) {
				shql += " and failureRecord.faultjudge='"
						+ failureRecordForm.getFaultjudge() + "'";

			}
			if (!"".equals(failureRecordForm.getFaultregion()) && failureRecordForm.getFaultregion()!= null) {
				shql += " and failureRecord.faultregion='"
						+ failureRecordForm.getFaultregion() + "'";

			}
			if (!"".equals(failureRecordForm.getFaultstatus()) && failureRecordForm.getFaultstatus()!= null) {
				shql += " and failureRecord.faultstatus='"
						+ failureRecordForm.getFaultstatus() + "'";

			}
			if (!"".equals(failureRecordForm.getFaultstarttime()) && failureRecordForm.getFaultstarttime()!= null) {
				shql += " and failureRecord.faultstarttime>'"
						+ failureRecordForm.getFaultstarttime() + "'";

			}
			if (!"".equals(failureRecordForm.getFaultendtime()) && failureRecordForm.getFaultendtime()!= null) {
				shql += " and failureRecord.faultendtime<'"
						+ failureRecordForm.getFaultendtime() + "'";

			}
			if(!"".equals(shql)){
				
				shql=" where "+shql.substring(4,shql.length());	
				hql+=shql;
			}
			
		}
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
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public FailureRecord getFailureRecord(final String id) {
		FailureRecord failureRecord = (FailureRecord) getHibernateTemplate()
				.get(FailureRecord.class, id);
		if (failureRecord == null) {
			throw new ObjectRetrievalFailureException(Forums.class, id);
		}

		return failureRecord;
	}

	public void save(FailureRecord failureRecord) {
		if (failureRecord.getId()== null
				|| "".equals(failureRecord.getId())) {
			getHibernateTemplate().save(failureRecord);
		} else {
			getHibernateTemplate().saveOrUpdate(failureRecord);
		}

	}

	public void removeFailureRecord(FailureRecord failureRecord) {
		 getHibernateTemplate().delete(failureRecord);
		
	}

	public FailureRecord findFailureRecord(String id) {
		String hql = "from FailureRecord failureRecord where failureRecord.alarmed='"+id+"'";
		List list=getHibernateTemplate().find(hql);
		FailureRecord failureRecord=new FailureRecord();
		if(list.size()>0){
			failureRecord=(FailureRecord) list.get(1);	
		}
		return failureRecord;
	}


}
