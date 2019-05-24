// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceLinkDAOHibernate.java

package com.boco.eoms.sheet.agentmaintenance.dao.hibernate;

import com.boco.eoms.sheet.agentmaintenance.dao.IAgentMaintenanceLinkDAO;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AgentMaintenanceLinkDAOHibernate extends LinkDAO
	implements IAgentMaintenanceLinkDAO
{

	public AgentMaintenanceLinkDAOHibernate()
	{
	}

	public Map getLastLinkBeforeHold(final String sourceId, final String type, final String operatedeptid)
	{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String sql = "select l ";
				String queryStr = " from AgentMaintenanceMain m,AgentMaintenanceLink l where 1=1";
				queryStr = queryStr + " and m.id = l.mainId and m.sourceId='" + sourceId + "' and m.type='" + type + "' and m.sendDeptId='" + operatedeptid + "'";
				String queryCountStr = "select count(*) " + queryStr;
				int dwTotal = ((Integer)session.createQuery(queryCountStr).iterate().next()).intValue();
				String queryStr2 = sql + queryStr + "and (l.operateType='205' or l.operateType='18' or l.operateType='211') and l.operateTime=(" + "select max(l1.operateTime) from AgentMaintenanceLink l1 where m.id = l1.mainId and (l1.operateType='205' or l1.operateType='18' or l1.operateType='211'))";
				Query query = session.createQuery(queryStr2);
				List result = query.list();
				Map map = new HashMap();
				map.put("total", new Integer(result.size()));
				map.put("result", result);
				map.put("dwTotal", new Integer(dwTotal));
				return map;
			}
			};
			return (HashMap)getHibernateTemplate().execute(callback);
	}
}