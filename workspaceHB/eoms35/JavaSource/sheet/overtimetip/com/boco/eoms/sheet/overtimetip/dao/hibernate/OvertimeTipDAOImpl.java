package com.boco.eoms.sheet.overtimetip.dao.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;
import com.boco.eoms.sheet.overtimetip.dao.IOvertimeTipDAO;

public class OvertimeTipDAOImpl extends BaseDaoHibernate implements IOvertimeTipDAO {

    /**
     * 保存
     *
     * @param Proxy the object to be saved
     */
    public void saveOvertimeTip(OvertimeTip overtimeTip) throws HibernateException {
        getHibernateTemplate().saveOrUpdate(overtimeTip);
    }

    /**
     * 删除
     *
     * @param Proxy the object to be removed
     */
    public void removeOvertimeTip(String Id) throws HibernateException {
        getHibernateTemplate().delete(getOvertimeTip(Id));
    }

    /**
     * 获取
     */
    public OvertimeTip getOvertimeTip(String Id) throws HibernateException {
        OvertimeTip tempLimit = (OvertimeTip) getHibernateTemplate().get(OvertimeTip.class, Id);
        if (tempLimit == null) {
            throw new ObjectRetrievalFailureException(OvertimeTip.class, Id);
        }
        return tempLimit;
    }

    /**
     * 根据工单名查询该工单的overtimetip记录
     *
     * @param String flowName,String  special1,String  special2,String  special3,String  special4
     * @return List
     */
    public HashMap getOvertimeTipByAdmin(String flowName, Integer pageIndex, Integer pageSize) throws HibernateException {
        String hql = " from OvertimeTip overtime where overtime.flowName='" + flowName + "'"
                + " and userId='admin'"
                + " order by overtime.setTime desc";
        return getListByCondition(hql, pageIndex, pageSize);
    }

    /**
     * 根据工单名和用户名查询overtimetip记录
     *
     * @param flowName
     * @param userId
     * @return List
     */
    public HashMap getOvertimeTipByUserId(String flowName, String userId, Integer pageIndex, Integer pageSize) throws HibernateException {
        String hql = " from OvertimeTip overtime where overtime.flowName='" + flowName + "'"
                + " and(userId='" + userId + "' or userId='admin')"
                + " order by overtime.setTime desc";
        return getListByCondition(hql, pageIndex, pageSize);
    }

    /**
     * 根据工单名和用户名查询overtimetip记录
     *
     * @param flowName
     * @param userId
     * @return List
     */
    public List getEffectOvertimeTipByUserId(String flowName,
                                             String userId, String specialty1, String specialty2,
                                             String specialty3, String specialty4, String specialty5,
                                             String specialty6, String specialty7, String specialty8, String specialty9, String specialty10) throws HibernateException {
        StringBuffer hqlbuffer = new StringBuffer();
        hqlbuffer.append(" from OvertimeTip overtime where overtime.flowName='" + flowName + "'"
                + " and(userId='" + userId + "' or userId='admin')");
        if (specialty1 != null && specialty1.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty1='" + specialty1 + "'");
        }
        if (specialty2 != null && specialty2.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty2='" + specialty2 + "'");
        }
        if (specialty3 != null && specialty3.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty3='" + specialty3 + "'");
        }
        if (specialty4 != null && specialty4.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty4='" + specialty4 + "'");
        }
        if (specialty5 != null && specialty5.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty5='" + specialty5 + "'");
        }
        if (specialty6 != null && specialty6.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty6='" + specialty6 + "'");
        }
        if (specialty6 != null && specialty7.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty7='" + specialty7 + "'");
        }
        if (specialty6 != null && specialty8.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty8='" + specialty8 + "'");
        }
        if (specialty6 != null && specialty9.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty9='" + specialty9 + "'");
        }
        if (specialty6 != null && specialty10.equals("") == false) {
            hqlbuffer.append(" and overtime.specialty10='" + specialty10 + "'");
        }
        hqlbuffer.append(" order by overtime.setTime desc");
        return getHibernateTemplate().find(hqlbuffer.toString());
    }

    /**
     * 根据特定的条件查询Overtime记录
     *
     * @param condition
     * @return List
     */
    public List getOvertimeTipByCondition(String condition) throws HibernateException {

        String hql = "select distinct overtime from OvertimeTip overtime where " + condition + " order by overtime.setTime desc";

        return getHibernateTemplate().find(hql);
    }

    /**
     * 根据特定的条件查询Overtime记录
     *
     * @param condition
     * @return List
     */
    public List getOvertimeTipByCondition(HashMap condition) throws HibernateException {
        Iterator it = condition.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            String column = (String) it.next();
            String value = (String) condition.get(column);
            sb.append(" overtime." + column + value + " and");
        }
        String wherestr = sb.toString().substring(0, sb.length() - 4);
        String hql = " from OvertimeTip overtime where " + wherestr + " order by overtime.setTime desc";

        return getHibernateTemplate().find(hql);
    }

    /**
     * 根据查询条件查询超时提醒列表信息, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public HashMap getListByCondition(final String queryStr,
                                      final Integer curPage, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //获取任务总数的查询sql
                HashMap map = new HashMap();
                try {
                    int sql_distinct = queryStr.indexOf("distinct");

                    int sql_index = queryStr.indexOf("from");
                    int sql_orderby = queryStr.indexOf("order by");

                    String queryCountStr;
                    if (sql_distinct > 0)
                        queryCountStr = "select count("
                                + queryStr.substring(sql_distinct, sql_index) + ") ";
                    else
                        queryCountStr = "select count(*) ";

                    if (sql_orderby > 0)
                        queryCountStr += queryStr.substring(sql_index, sql_orderby);
                    else
                        queryCountStr += queryStr.substring(sql_index);

                    Integer totalCount;

                    Query totalQuery = session.createQuery(queryCountStr);
                    if (!totalQuery.list().isEmpty()) {
                        totalCount = (Integer) totalQuery.list().get(0);
                    } else
                        totalCount = new Integer(0);


                    Query query = session.createQuery(queryStr);
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                    List resultList = query.list();

                    map.put("timeTotal", totalCount);
                    map.put("timeList", resultList);
                } catch (Exception e) {
                    System.out.println("-------time list error!---------");
                    e.printStackTrace();
                    throw new HibernateException("time list error");
                }
                return map;
            }
        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }
}
