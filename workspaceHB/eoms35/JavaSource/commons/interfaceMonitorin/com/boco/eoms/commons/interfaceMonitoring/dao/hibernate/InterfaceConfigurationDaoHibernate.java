package com.boco.eoms.commons.interfaceMonitoring.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceConfigurationDao;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceMonitoringDao;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;

public class InterfaceConfigurationDaoHibernate extends BaseDaoHibernate
		implements InterfaceConfigurationDao {

	public Map getConfigurationList(final Integer curPage,
			final Integer pageSize, String direction) {
		String hql = "from InterfaceConfiguration interfaceConfiguration where interfaceConfiguration.direction='"
				+ direction + "'";

		return search(curPage, pageSize, hql);

	}

	public void save(InterfaceConfiguration interfaceConfiguration) {
		if (interfaceConfiguration.getId() == null
				|| "".equals(interfaceConfiguration.getId())) {
			getHibernateTemplate().save(interfaceConfiguration);
		} else {
			getHibernateTemplate().saveOrUpdate(interfaceConfiguration);
		}

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

	public InterfaceConfiguration getinterfaceConfiguration(String id) {
		InterfaceConfiguration interfaceConfiguration = (InterfaceConfiguration) getHibernateTemplate()
				.get(InterfaceConfiguration.class, id);
		if (interfaceConfiguration == null) {
			throw new ObjectRetrievalFailureException(
					InterfaceConfiguration.class, id);
		}

		return interfaceConfiguration;
	}

	public void removeInterfaceConfiguration(
			InterfaceConfiguration interfaceConfiguration) {
		getHibernateTemplate().delete(interfaceConfiguration);

	}
	public List getInterfaceConfigurationList(String nodeid) {
		String hql = " from InterfaceConfiguration interfaceConfiguration where interfaceConfiguration.moduleid='"
				+ nodeid + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public  List getInterfaceConfigurationModuleTree(String nodid) {
		String hql = "";
	
	
			hql = " from InterfaceConfiguration work where work.moduleid = '"+nodid+"'order by work.id";
	
		return (ArrayList) getHibernateTemplate().find(hql);
		
	}
}
