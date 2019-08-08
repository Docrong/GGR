/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.dao.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.complaint.dao.IComplaintReturnHouseDAO;
import com.boco.eoms.sheet.complaint.model.ComplaintReturnHouse;

/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintReturnHouseDaoHibernate extends BaseSheetDaoHibernate implements
        IComplaintReturnHouseDAO {
    public ComplaintReturnHouseDaoHibernate() {
    }

    public Map getReturnHorse(String userid, final String queryStr,
                              final Integer curPage, final Integer pageSize) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //获取任务总数的查询sql
                HashMap map = new HashMap();
                try {
                    int sql_distinct = queryStr.indexOf("distinct");

                    int sql_index = queryStr.indexOf("from");
                    int sql_orderby = queryStr.indexOf("order by");

                    String queryCountStr;
                    if (sql_distinct > 0) {
                        int sql_comma = (queryStr.substring(sql_distinct, sql_index)).indexOf(",");
                        if (sql_comma > 0) {
                            queryCountStr = "select count("
                                    + queryStr.substring(sql_distinct, sql_distinct + sql_comma) + ") ";
                        } else {
                            queryCountStr = "select count("
                                    + queryStr.substring(sql_distinct, sql_index) + ") ";
                        }
                    } else {
                        queryCountStr = "select count(*) ";
                    }
                    if (sql_orderby > 0)
                        queryCountStr += queryStr.substring(sql_index, sql_orderby);
                    else
                        queryCountStr += queryStr.substring(sql_index);

                    Integer totalCount;

                    Query totalQuery = session.createQuery(queryCountStr);
                    List result = totalQuery.list();
                    if (result != null && !result.isEmpty()) {
                        totalCount = (Integer) result.get(0);
                    } else
                        totalCount = new Integer(0);


                    Query query = session.createQuery(queryStr);
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();

                    map.put("taskTotal", totalCount);
                    map.put("taskList", resultList);
                } catch (Exception e) {
                    System.out.println("-------task list error!---------");
                    e.printStackTrace();
                    throw new HibernateException("task list error");
                }
                return map;
            }
        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }


    public void saveReturnHorse(ComplaintReturnHouse complaintreturnhouse) {
        if (complaintreturnhouse.getId() == null || complaintreturnhouse.getId().equals(""))
            getHibernateTemplate().save(complaintreturnhouse);
        else
            getHibernateTemplate().saveOrUpdate(complaintreturnhouse);
    }

    public ComplaintReturnHouse getReturnHouseByid(final String id) {
        ComplaintReturnHouse complaintreturnhouse = (ComplaintReturnHouse) getHibernateTemplate().get(ComplaintReturnHouse.class, id);
        return complaintreturnhouse;
    }

    public void clearObjectOfCurrentSession() {
        // TODO Auto-generated method stub

    }

    public Integer count(String hsql) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public void deleteMain(String id, Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub

    }

    public List getAllAttachmentsBySheet(String where) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getCancelCount(Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public List getCancelList(Integer curPage, Integer pageSize, Object mainObject, HashMap condition) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public List getDraftListByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal, Object obj) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public List getHolds(Map condition, Integer curPage, Integer pageSize, Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getHoldsCount(Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public BaseMain getMain(String id, String className) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public BaseMain getMainBySheetId(String sheetId, Object object) {
        // TODO Auto-generated method stub
        return null;
    }

    public HashMap getMainListBySql(String hsql, Integer curPage, Integer pageSize) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public List getMainListBySql(String hsql) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public List getQuerySheetByCondition(String hsql, Integer curPage, Integer pageSize, int[] aTotal, String queryType) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer getStarterCount(String userId, Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public HashMap getStarterList(String userId, Integer curPage, Integer pageSize, Object mainObject, HashMap condition) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public List getTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal, Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public int getXYZ(String id, Object mainObject) throws HibernateException {
        // TODO Auto-generated method stub
        return 0;
    }

    public BaseMain loadSinglePO(String id, Object obj) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public BaseMain loadSinglePOByProcessId(String processId, Object obj) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public void mergeObject(Object obj) {
        // TODO Auto-generated method stub

    }

    public void removeMain(Object baseMain) {
        // TODO Auto-generated method stub

    }

    public void saveOrUpdateMain(Object main) throws HibernateException {
        // TODO Auto-generated method stub

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

//	
//	/**
//	 * 通过告警号获取工单
//	 * @param alarmId 告警号
//	 * @return
//	 * @throws HibernateException
//	 */
//	public BaseMain getMainByAlarmId(String alarmId, Object mainObject)
//	throws HibernateException {
//		BaseMain main = new BaseMain();
//		String sql = "from "+ mainObject.getClass().getName() +" as main where main.mainAlarmNum ='"
//				+ alarmId + "'";
//		List list = getHibernateTemplate().find(sql);
//		if (list.size() == 0) {
//			main = null;
//		} else {
//			main = (BaseMain) list.get(0);
//		}
//		return main;
//	} 
//	public List getMainByLink(Map map, Object mainObject, Object linkObject){
//		if(map==null)
//			return null;
//
//		String hql = "select distinct main from "+ mainObject.getClass().getName() 
//		+" main, "+ linkObject.getClass().getName() +" link where main.id=link.mainId ";
//		Set set = map.keySet();
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			String key = StaticMethod.nullObject2String(iterator.next());
//			String value = StaticMethod.nullObject2String(map.get(key));	
//			if(value.length()>0){
//				hql += " and ";
//				hql += " link."+key+" like '%"+value+"%'";
//			}
//		}
//		
//		List list = getHibernateTemplate().find(hql);
//		return list;
//	}
//	public List getMainList(Map map, Object mainObject){
//		if(map==null)
//			return null;
//		boolean isfirst = true;
//
//		String hql = "select distinct main from "+ mainObject.getClass().getName() +" main ";
//		Set set = map.keySet();
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			String key = StaticMethod.nullObject2String(iterator.next());
//			String value = StaticMethod.nullObject2String(map.get(key));	
//			if(value.length()>0){
//				if(isfirst){
//					hql += " where ";
//					isfirst = false;
//				}else{
//					hql += " and ";									
//				}
//				hql += " main."+key+" like '%"+value+"%'";	
//			}
//		}
//		if(isfirst)
//			return null;
//		
//		List list = getHibernateTemplate().find(hql);
//		return list;
//	}
//
//	
//	/**
//	 * 待质检（后质检）的已归档工单数
//	 * @return
//	 * @throws HibernateException
//	 */	
//	public Integer getCountUndoForCheck(final Object mainObject) throws HibernateException {
//		
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
//				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
//						+ "alias.status=:status ";
//				//取列表数量
//				String queryCountStr = "select count(alias.id) from " + mainObject.getClass().getName()
//						+ " alias where alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1)";
//				Query query = session.createQuery(queryCountStr);
//				//TODO 归档标记，需确认
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
//				Integer total = (Integer) query.iterate().next();
//				return total;
//			}
//		};
//		return (Integer) getHibernateTemplate().execute(callback);
//	}	
//
//	/**
//	 * 待质检（后质检）的已归档工单列表
//	 * @return
//	 * @throws HibernateException
//	 */	
//	public List getListUndoForCheck(final Integer curPage, final Integer pageSize, final Object mainObject)throws HibernateException {
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
//				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
//						+ "alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";
//		
//				Query query = session.createQuery(queryStr);
//				//设置归档标记
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
//				
//				//分页查询条
//				query.setFirstResult(pageSize.intValue()
//								* (curPage.intValue()));
//		
//				query.setMaxResults(pageSize.intValue());
//				return query.list();
//			}
//		};
//		return (List) getHibernateTemplate().execute(callback);
//	}	
//
//	/**
//	 * 已质检（后质检）的已归档工单数
//	 * mainIfCheck = '2'
//	 * @return
//	 * @throws HibernateException
//	 */	
//	public Integer getCountDoneForCheck(final Object mainObject) throws HibernateException {
//		
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
//				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
//						+ "alias.status=:status ";
//				//取列表数量
//				String queryCountStr = "select count(alias.id) from " + mainObject.getClass().getName()
//						+ " alias where alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1)";
//				Query query = session.createQuery(queryCountStr);
//				//TODO 归档标记，需确认
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
//				Integer total = (Integer) query.iterate().next();
//				return total;
//			}
//		};
//		return (Integer) getHibernateTemplate().execute(callback);
//	}	
//
//	/**
//	 * 已质检（后质检）的已归档工单列表
//	 * mainIfCheck = '2'
//	 * @return
//	 * @throws HibernateException
//	 */	
//	public List getListDoneForCheck(final Integer curPage, final Integer pageSize, final Object mainObject)throws HibernateException {
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
//				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
//						+ "alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";
//		
//				Query query = session.createQuery(queryStr);
//				//设置归档标记
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
//				
//				//分页查询条
//				query.setFirstResult(pageSize.intValue()
//								* (curPage.intValue()));
//		
//				query.setMaxResults(pageSize.intValue());
//				return query.list();
//			}
//		};
//		return (List) getHibernateTemplate().execute(callback);
//	}	
//	
//	/**
//     * 获取某段时间内归档的且可以上传的工单
//     */
//    public List getHoldedSheetListByTime(Object object,String startTime,String endTime){
//		String sql = "from " + object.getClass().getName() + " main where main.endTime between "
//				+ SheetStaticMethod.getDBDateToString(startTime) + " and " + SheetStaticMethod.getDBDateToString(endTime) + " and main.status='1' and main.reportFlag=1";
//		return getHibernateTemplate().find(sql);
//    }
}
