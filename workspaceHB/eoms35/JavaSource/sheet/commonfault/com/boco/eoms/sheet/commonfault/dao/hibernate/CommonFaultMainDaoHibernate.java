/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;

/**
 * @author
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultMainDaoHibernate extends MainDAO implements
		ICommonFaultMainDAO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	 */
	// public BaseMain loadSinglePO(String id, Object obj) throws
	// HibernateException {
	// BaseMain main = (BaseMain) getHibernateTemplate().get(obj.getClass(),id);
	// return main;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	 */
	// public BaseMain loadSinglePOByProcessId(String processId, Object obj)
	// throws HibernateException {
	// BaseMain main = new BaseMain();
	// String sql = "from "+ obj.getClass().getName() +" as main where main.piid
	// ='"
	// + processId + "'";
	// List list = getHibernateTemplate().find(sql);
	// if (list.size() == 0) {
	// main = null;
	// } else {
	// main = (BaseMain) list.get(0);
	// }
	// return main;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	 */
	// public void deleteMain(String id, Object mainObject) throws
	// HibernateException {
	// BaseMain main = (BaseMain)
	// getHibernateTemplate().get(mainObject.getClass(),id);
	// main.setDeleted(new Integer(1));
	// getHibernateTemplate().save(main);
	// }
	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	 */
	// public String getMainName() {
	// // TODO Auto-generated method stub
	// return "CommonFaultMain";
	// }
	public List showInvokeRelationShipList(String mainId) throws SheetException {
		String sql = "SELECT u from UrgentFaultMain as u , CommonFaultMain c where c.correlationKey = u.parentCorrelation and c.id ='"
				+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	public BaseLink getHasInvokeBaseLink(String mainId) throws SheetException {
		String sql = "SELECT c from CommonFaultLink as c where c.operateType = 19 and c.mainId ='"
				+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (BaseLink) list.iterator().next() : null;
	}

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(
			String flowTemplateName) throws SheetException {
		String sql = "from TawSystemWorkflow as t where t.name ='"
				+ flowTemplateName + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (TawSystemWorkflow) list.iterator().next()
				: null;
	}

	/**
	 * 通过告警号获取工单
	 * 
	 * @param alarmId
	 *            告警号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByAlarmId(String alarmId) throws HibernateException {
		CommonFaultMain main = new CommonFaultMain();
		String sql = "from CommonFaultMain as main where main.mainAlarmNum ='"
				+ alarmId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (CommonFaultMain) list.get(0);
		}
		return main;
	}

	// public BaseMain getMainBySheetId(String sheetId, Object object) throws
	// HibernateException{
	// CommonFaultMain main = new CommonFaultMain();
	// String sql = "from CommonFaultMain as main where main.sheetId ='"
	// + sheetId + "'";
	// List list = getHibernateTemplate().find(sql);
	// if (list.size() == 0) {
	// main = null;
	// } else {
	// main = (CommonFaultMain) list.get(0);
	// }
	// return main;
	// }

	public List getMainByLink(Map map) {
		if (map == null)
			return null;

		String hql = "select distinct main from CommonFaultMain main, CommonFaultLink link where main.id=link.mainId ";
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = StaticMethod.nullObject2String(iterator.next());
			String value = StaticMethod.nullObject2String(map.get(key));
			if (value.length() > 0) {
				hql += " and ";
				hql += " link." + key + " like '%" + value + "%'";
			}
		}

		List list = getHibernateTemplate().find(hql);
		return list;
	}

	public List getMainList(Map map) {
		if (map == null)
			return null;
		boolean isfirst = true;

		String hql = "select distinct main from CommonFaultMain main ";
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = StaticMethod.nullObject2String(iterator.next());
			String value = StaticMethod.nullObject2String(map.get(key));
			if (value.length() > 0) {
				if (isfirst) {
					hql += " where ";
					isfirst = false;
				} else {
					hql += " and ";
				}
				hql += " main." + key + " like '%" + value + "%'";
			}
		}
		if (isfirst)
			return null;

		List list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 待质检（后质检）的已归档工单数
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public Integer getCountUndoForCheck(final Object mainObject)
			throws HibernateException {

		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from " + mainObject.getClass().getName()
						+ " alias where " + "alias.status=:status ";
				// 取列表数量
				String queryCountStr = "select count(alias.id) from "
						+ mainObject.getClass().getName()
						+ " alias where alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1)";
				Query query = session.createQuery(queryCountStr);
				// TODO 归档标记，需确认
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}

	/**
	 * 待质检（后质检）的已归档工单列表
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public List getListUndoForCheck(final Integer curPage,
			final Integer pageSize, final Object mainObject)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from "
						+ mainObject.getClass().getName()
						+ " alias where "
						+ "alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";

				Query query = session.createQuery(queryStr);
				// 设置归档标记
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

				// 分页查询条
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));

				query.setMaxResults(pageSize.intValue());
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 已质检（后质检）的已归档工单数 mainIfCheck = '2'
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public Integer getCountDoneForCheck(final Object mainObject)
			throws HibernateException {

		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from " + mainObject.getClass().getName()
						+ " alias where " + "alias.status=:status ";
				// 取列表数量
				String queryCountStr = "select count(alias.id) from "
						+ mainObject.getClass().getName()
						+ " alias where alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1)";
				Query query = session.createQuery(queryCountStr);
				// TODO 归档标记，需确认
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}

	/**
	 * 已质检（后质检）的已归档工单列表 mainIfCheck = '2'
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public List getListDoneForCheck(final Integer curPage,
			final Integer pageSize, final Object mainObject)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from "
						+ mainObject.getClass().getName()
						+ " alias where "
						+ "alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";

				Query query = session.createQuery(queryStr);
				// 设置归档标记
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

				// 分页查询条
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));

				query.setMaxResults(pageSize.intValue());
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public void DeleteEarlyEmptyMain() {
		try {
			String sql = "from CommonFaultMain"
					+ " as main where main.piid is null or piid='' and templateflag<>1";
			List sheetList = getHibernateTemplate().find(sql);
			if (sheetList != null) {
				int i = sheetList.size();
				while (i > 0) {
					i--;
					BaseMain main = (BaseMain) sheetList.get(i);
					Date theDate = new Date();
					if (StaticMethod
							.diffDateToHour(theDate, main.getSendTime()) > 24) {// delete
						List list = getListAlarm(main.getId(), "0");
						for (int j = 0; j < list.size(); j++) {
							CommonFaultPackMain alarm = (CommonFaultPackMain) list
									.get(j);
							if (alarm != null) {
								getHibernateTemplate().delete(alarm);
								getHibernateTemplate().flush();
								getHibernateTemplate().clear();
							}
						}
						System.out.println("delete early main");
						getHibernateTemplate().delete(main);
						getHibernateTemplate().flush();
						getHibernateTemplate().clear();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getListAlarm(final String mainId, final String mainAlarmState)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from CommonFaultPackMain alias where "
						+ "alias.mainId=:mainId and alias.mainAlarmState=:mainAlarmState";
				Query query = session.createQuery(queryStr);
				query.setString("mainId", mainId);
				query.setString("mainAlarmState", mainAlarmState);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 获取某段时间内归档的工单
	 */
	public List getHoldedSheetListByTime(Object object, String startTime,
			String endTime) {
		String sql = "from " + object.getClass().getName()
				+ " main where main.endTime between "
				+ SheetStaticMethod.getDBDateToString(startTime) + " and "
				+ SheetStaticMethod.getDBDateToString(endTime)
				+ " and main.status='1' and main.reportFlag=1";
		return getHibernateTemplate().find(sql);
	}

	public List getListOverTime(final String residualHour,
			final String startTime) throws HibernateException {
		// hql锛屼互澶囪祫婧愬悕绉帮紜绯荤粺瑙掕壊锛嬭祫婧愮被鍒紜鏈満绠＄悊IP鍦板潃涓烘潯浠舵煡璀?
		String queryStr = "from CommonFaultMain alias where "
				+ "alias.urgeTime=0 "
				+ " and To_Number(alias.sendTime-CURRENT_DATE)*24<"
				+ residualHour + " and alias.sendTime>=To_date('" + startTime
				+ "','yyyy-mm-dd hh24:mi:ss')";
		return getHibernateTemplate().find(queryStr);
	}

	/**
	 * 张晓杰添加 查看接口说明
	 */
	public Integer getStatusById(final String id) {

		String sqlstr = "select alias.status from CommonFaultMain alias where alias.id = '"
				+ id + "'";
		Integer integer = new Integer(-100);
		List list = getHibernateTemplate().find(sqlstr);
		if (list != null) {
			integer = (Integer) list.get(0);
		}

		return integer;
	}

	/**
	 * 张晓杰添加 查看接口说明
	 */
	public Integer getStatusBySheetId(final String sheetId) {
		String sqlstr = "select alias.status from CommonFaultMain alias where alias.sheetId = '"
				+ sheetId + "'";
		Integer integer = new Integer(-100);
		List list = getHibernateTemplate().find(sqlstr);
		// 如何数据库存在多个相同的SHEETID，需要特殊处理
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toString().equals("0"))
				return new Integer(0);
			else
				integer = (Integer) list.get(i);
		}
		return integer;
	}

	/**
	 * 张晓杰添加 查看接口说明
	 */
	public CommonFaultMain getCommonFaultMainById(final String id) {
		String hql = " from CommonFaultMain alias where alias.id = '" + id
				+ "'";
		return (CommonFaultMain) getHibernateTemplate().find(hql).get(0);
	}

	/**
	 * 张晓杰添加 查看接口说明
	 */
	public List getCommonFaultMainBySheetId(final String sheetId) {
		String hql = " from CommonFaultMain alias where alias.sheetId = '"
				+ sheetId + "'";
		return getHibernateTemplate().find(hql);

	}
	

	public List getListByUserId(final String userId) {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr ="select * from commonfault_alarm_role where deleted ='0'";
				if (userId!=null && !"".equals(userId))
				  queryStr = queryStr + " and userId='" + userId +"' ";
				System.out.println("----------getListByUserId---------sql:"+queryStr);
				Query query = session.createSQLQuery(queryStr);
				ArrayList queryList = new ArrayList();
				queryList = (ArrayList)query.list();
				return queryList;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 通过ID获取T2处理人
	 * 
	 * @param alarmId
	 *            告警号
	 * @return
	 * @throws HibernateException
	 */
	public String getSecondExcuteHumTaskById(String id) throws HibernateException {
		String sqlstr = "SELECT taskowner FROM commonfault_task a WHERE  taskstatus =5 AND taskname = 'SecondExcuteHumTask' and " +
				"sheetid = (SELECT sheetid FROM commonfault_main WHERE id = '"+id+"') ORDER BY sendtime DESC";
	List list = getHibernateTemplate().find(sqlstr);
	// 如何数据库存在多个相同的SHEETID，需要特殊处理
	String username = (String)list.get(0);
	return username;
	}
}
