/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.boco.eoms.sheet.groupcomplaint.dao.IGroupComplaintMainDAO;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintMainDaoHibernate extends MainDAO implements
        IGroupComplaintMainDAO {

    /**
     * 通过告警号获取工单
     *
     * @param alarmId 告警号
     * @return
     * @throws HibernateException
     */
    public BaseMain getMainByAlarmId(String alarmId, Object mainObject)
            throws HibernateException {
        GroupComplaintMain main = new GroupComplaintMain();
        String sql = "from " + mainObject.getClass().getName() + " as main where main.mainAlarmNum ='"
                + alarmId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (GroupComplaintMain) list.get(0);
        }
        return main;
    }

    public List getMainByLink(Map map, Object mainObject, Object linkObject) {
        if (map == null)
            return null;
// 工单查询屏蔽后加下一条
//		String hql = "select distinct main from "+ mainObject.getClass().getName() +" main, "+ linkObject.getClass().getName() +" link where main.id=link.mainId ";
        String hql = "select distinct main from GroupComplaintMain main, GroupComplaintLink link where main.id=link.mainId ";
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

    public List getMainList(Map map, Object mainObject) {
        if (map == null)
            return null;
        boolean isfirst = true;

        String hql = "select distinct main from " + mainObject.getClass().getName() + " main ";
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
    public Integer getCountUndoForCheck(final Object mainObject) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                String queryStr = "from " + mainObject.getClass().getName() + " alias where "
                        + "alias.status=:status ";
                //取列表数量
                String queryCountStr = "select count(alias.id) from " + mainObject.getClass().getName()
                        + " alias where alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1)";
                Query query = session.createQuery(queryCountStr);
                //TODO 归档标记，需确认
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
    public List getListUndoForCheck(final Integer curPage, final Integer pageSize, final Object mainObject) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                String queryStr = "from " + mainObject.getClass().getName() + " alias where "
                        + "alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";

                Query query = session.createQuery(queryStr);
                //设置归档标记
                query.setInteger("status", Constants.SHEET_HOLD.intValue());
                query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

                //分页查询条
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));

                query.setMaxResults(pageSize.intValue());
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 已质检（后质检）的已归档工单数
     * mainIfCheck = '2'
     *
     * @return
     * @throws HibernateException
     */
    public Integer getCountDoneForCheck(final Object mainObject) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                String queryStr = "from " + mainObject.getClass().getName() + " alias where "
                        + "alias.status=:status ";
                //取列表数量
                String queryCountStr = "select count(alias.id) from " + mainObject.getClass().getName()
                        + " alias where alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1)";
                Query query = session.createQuery(queryCountStr);
                //TODO 归档标记，需确认
                query.setInteger("status", Constants.SHEET_HOLD.intValue());
                query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
                Integer total = (Integer) query.iterate().next();
                return total;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /**
     * 已质检（后质检）的已归档工单列表
     * mainIfCheck = '2'
     *
     * @return
     * @throws HibernateException
     */
    public List getListDoneForCheck(final Integer curPage, final Integer pageSize, final Object mainObject) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                String queryStr = "from " + mainObject.getClass().getName() + " alias where "
                        + "alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";

                Query query = session.createQuery(queryStr);
                //设置归档标记
                query.setInteger("status", Constants.SHEET_HOLD.intValue());
                query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

                //分页查询条
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));

                query.setMaxResults(pageSize.intValue());
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public Map getListQuery(final String sql, final Integer pageSize, final Integer curPage) throws HibernateException {
        // TODO Auto-generated method stub
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {

                String queryCountStr = "select count(*) from (" + sql + ")";
                Integer totalCount;
                Query totalQuery = session.createSQLQuery(queryCountStr);
                if (!totalQuery.list().isEmpty()) {
                    totalCount = new Integer(((BigDecimal) totalQuery.list().get(0)).intValue());
                } else {
                    totalCount = new Integer(0);
                }

                Query query = session.createSQLQuery(sql);

                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());

                ArrayList queryList = new ArrayList();
                String[] elementKey = new String[]{"id", "title", "sheetId", "sheetAcceptLimit", "sheetCompleteLimit", "taskdisplayname", "subrolename", "ls", "overtime"};
                ArrayList taskList = new ArrayList();
                queryList = (ArrayList) query.list();
                for (int i = 0; i < queryList.size(); i++) {
                    Object[] tmpObjArr = (Object[]) queryList.get(i);
                    Map tmptaskMap = new HashMap();
                    for (int j = 0; j < elementKey.length; j++) {
                        tmptaskMap.put(elementKey[j], tmpObjArr[j]);

                    }
                    taskList.add(tmptaskMap);
                }
                HashMap map = new HashMap();
                map.put("total", totalCount);
                map.put("result", taskList);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

}
