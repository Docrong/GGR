// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceMainDAOHibernate.java

package com.boco.eoms.sheet.agentmaintenance.dao.hibernate;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.agentmaintenance.dao.IAgentMaintenanceMainDAO;
import com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain;
import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.QuerySqlInit;
import java.util.HashMap;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AgentMaintenanceMainDAOHibernate extends MainDAO
	implements IAgentMaintenanceMainDAO
{

	public AgentMaintenanceMainDAOHibernate()
	{
	}

	public BaseMain getMainBySourceId(String sourceId, String operatedeptid)
	{
		AgentMaintenanceMain agentMaintenanceMain = null;
		String hql = "from AgentMaintenanceMain where sendDeptId='" + operatedeptid + "' and sourceId='" + sourceId + "' order by sendtime desc";
		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			agentMaintenanceMain = (AgentMaintenanceMain)list.get(0);
		return agentMaintenanceMain;
	}

	public HashMap getStarterList(String userId, Integer curPage, Integer pageSize, Object mainObject, HashMap condition)
		throws HibernateException
	{
		String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
		String variables = QuerySqlInit.getAllDictItemsName(beanName);
		String sqlstr = "select " + variables + " from " + mainObject.getClass().getName() + " main where main.templateFlag <> 1 and " + " main.sendUserId='" + userId + "' and status=0 and (title is not null or title='') and main.deleted<>'1'";
		if (!condition.get("type").toString().equals(""))
			sqlstr = sqlstr + " and main.type='" + condition.get("type").toString() + "'";
		String orderCondition = StaticMethod.nullObject2String(condition.get("orderCondition"));
		StringBuffer hql = new StringBuffer(sqlstr);
		if (pageSize.intValue() != -1)
			if (!orderCondition.equals(""))
				hql.append(" order by " + orderCondition);
			else
				hql.append(" order by main.sendTime desc");
		return getMainListBySql(hql.toString(), curPage, pageSize);
	}
}