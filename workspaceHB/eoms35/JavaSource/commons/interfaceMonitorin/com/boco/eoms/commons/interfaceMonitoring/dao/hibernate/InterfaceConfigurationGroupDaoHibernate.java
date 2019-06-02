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
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceConfigurationGroupDao;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceMonitoringDao;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;

public class InterfaceConfigurationGroupDaoHibernate extends BaseDaoHibernate
		implements InterfaceConfigurationGroupDao {
	
	
	public  List getModuleTree(String nodid) {
		String hql = "";
	
		
		 hql = " from InterfaceConfigurationGroup work  order by work.id";
	
		return (ArrayList) getHibernateTemplate().find(hql);
		
	}

	public Map getConfigurationList(final Integer curPage,
			final Integer pageSize, String direction) {
		String hql = "from InterfaceConfigurationGroup interfaceConfigurationGroup where interfaceConfiguration.direction='"
				+ direction + "'";

		return search(curPage, pageSize, hql);

	}

	public void save(InterfaceConfigurationGroup interfaceConfigurationGroup) {
		if (interfaceConfigurationGroup.getId() == null
				|| "".equals(interfaceConfigurationGroup.getId())||"-1".equals(interfaceConfigurationGroup.getId())) {
			getHibernateTemplate().save(interfaceConfigurationGroup);
		} else {
			getHibernateTemplate().saveOrUpdate(interfaceConfigurationGroup);
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

	public void removeInterfaceConfigurationGroup(InterfaceConfigurationGroup interfaceConfigurationGroup) {
		 
		        getHibernateTemplate().delete(interfaceConfigurationGroup);
		 
		
	}
	public InterfaceConfigurationGroup getInterfaceConfigurationGroup(String id) {
		 
		InterfaceConfigurationGroup interfaceConfigurationGroup = (InterfaceConfigurationGroup) getHibernateTemplate().get(InterfaceConfigurationGroup.class, id);
     

        return interfaceConfigurationGroup;
 

}




}
