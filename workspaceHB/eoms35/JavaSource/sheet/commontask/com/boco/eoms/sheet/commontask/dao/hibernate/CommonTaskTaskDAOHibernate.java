
package com.boco.eoms.sheet.commontask.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.TaskDAOImpl;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commontask.dao.ICommonTaskTaskDAO;
import com.boco.eoms.sheet.commontask.model.CommonTaskTask;
import com.boco.eoms.sheet.commontask.model.CommonTaskTaskUndo;

public class CommonTaskTaskDAOHibernate extends TaskDAOImpl implements ICommonTaskTaskDAO {

    public HashMap getSplitAndReplyList(Map map, String s, String s1, String s2, Integer integer, Integer integer1) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public HashMap getSplitNotReplyList(Map map, String s, String s1, String s2, Integer integer, Integer integer1) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 根据count SQL语句查询
     *
     * @param sql      查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public Integer getCountTaskBySQL(final String queryCountStr)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                // 获取任务总数的查询sql
                Integer totalCount;
                try {
                    Query totalQuery = session.createSQLQuery(queryCountStr);
                    if (!totalQuery.list().isEmpty()) {
                        System.out.println("is not empty!");
                        totalCount = new Integer(((BigDecimal) totalQuery.list().get(0)).intValue());
                        System.out.println(totalCount);
                    } else {
                        totalCount = new Integer(0);
                        System.out.println("is not empty!");
                    }
                } catch (Exception e) {
                    System.out.println("-------task list error!---------");
                    e.printStackTrace();
                    throw new HibernateException("task list error");
                }
                return totalCount;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据SQL语句查询任务信息, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public HashMap getTaskListBySQL(final String queryStr,
                                    final Integer curPage, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //获取任务总数的查询sql
                HashMap map = new HashMap();
                try {
                    Query query = session.createSQLQuery(queryStr).addEntity(CommonTaskTask.class).addScalar("revert").addScalar("title");
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();
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

}
