package com.boco.eoms.sheetflowline.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheetflowline.dao.IPreAllocatedDao;
import com.boco.eoms.sheetflowline.model.PreAllocated;


public class PreAllocatedDaoHibernate extends BaseDaoHibernate implements IPreAllocatedDao {

    public void deleteObject(PreAllocated object) throws HibernateException {

        getHibernateTemplate().delete(object);

    }

    public PreAllocated getPreAllocated(String id) throws HibernateException {

        return (PreAllocated) getHibernateTemplate().get(PreAllocated.class, id);
    }

    public Map listPreAllocated(final Integer curPage, final Integer pageSize) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //获取任务总数的查询sql
                HashMap map = new HashMap();
                try {

                    String queryStr = "from PreAllocated order by createTime desc";
                    String queryCountStr = "select count(*) from PreAllocated";
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

    public void saveObject(PreAllocated object) throws HibernateException {
        getHibernateTemplate().save(object);

    }

    public void updateObject(PreAllocated object) throws HibernateException {
        getHibernateTemplate().update(object);

    }

    public Integer executeHsql(final String hsql) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query totalQuery = session.createQuery(hsql);
                return new Integer(totalQuery.executeUpdate());
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    public Map listPreAllocated(Map map, Integer pageIndex, Integer pageSize) throws HibernateException {
        DetachedCriteria criteria = DetachedCriteria.forClass(PreAllocated.class);
        Iterator iter = map.keySet().iterator();
        boolean flag = false;
        while (iter.hasNext()) {
            String key = StaticMethod.nullObject2String(iter.next());
            if ("count".equals(key)) {//工单数量
                flag = true;
                Integer intvalue = new Integer(StaticMethod.nullObject2String(map.get(key)));
                if (!new Integer("0").equals(intvalue)) {
                    criteria.add(Expression.eq(key, intvalue));
                }
            }
            if ("alreadyCount".equals(key)) {//已经
                flag = true;
                Integer intvalue = new Integer(StaticMethod.nullObject2String(map.get(key)));
                if (!new Integer("0").equals(intvalue)) {
                    criteria.add(Expression.eq(key, intvalue));
                }
            }
            if ("startTime".equals(key)) {//开始时间
                flag = true;
                if (map.get(key) != null) {
                    criteria.add(Expression.ge(key, (Date) map.get(key)));
                }
            }
            if ("endTime".equals(key)) {//结束时间
                flag = true;
                if (map.get(key) != null) {
                    criteria.add(Expression.le(key, (Date) map.get(key)));
                }
            }
            if (flag == false) {
                String value = StaticMethod.nullObject2String(map.get(key));
                if (!"".equals(value)) {
                    criteria.add(Expression.eq(key, value));
                }
            }
        }
        criteria.addOrder(Order.desc("createTime"));
        List list = getHibernateTemplate().findByCriteria(criteria);
        Integer totalCount;
        if (list != null && !list.isEmpty()) {
            totalCount = new Integer(list.size());
        } else {
            totalCount = new Integer(0);
        }
        List resultList = (List) getHibernateTemplate().findByCriteria(criteria, pageIndex.intValue(),
                pageSize.intValue());
        Map returnMap = new HashMap();
        returnMap.put("taskTotal", totalCount);
        returnMap.put("taskList", resultList);
        return returnMap;
    }

    public List search(String mainNetSortOne, String mainNetSortTwo, String mainNetSortThree, String mainEquipmentFactory, String mainFaultResponseLevel, String currentTime) throws HibernateException {
        String hsql = "from PreAllocated p where isopen=1 ";
        List list = getHibernateTemplate().find(hsql);
        if (!"".equals(mainNetSortOne)) {
            hsql = hsql + " and p.netTypeOne='" + mainNetSortOne + "' ";
        }
        if (!"".equals(mainNetSortTwo)) {
            hsql = hsql + " and p.netTypeTwo='" + mainNetSortTwo + "' ";
        }
        if (!"".equals(mainNetSortThree)) {
            hsql = hsql + " and (p.netTypeThree='" + mainNetSortThree + "' or p.netTypeThree is null) ";
        }
        if (!"".equals(mainEquipmentFactory)) {
            hsql = hsql + " and p.vendor='" + mainEquipmentFactory + "' ";
        }
        if (!"".equals(mainFaultResponseLevel)) {
            hsql = hsql + " and p.faultResponseLevel='" + mainFaultResponseLevel + "' ";
        }
        hsql = hsql + " and p.startTime<=to_date('" + currentTime + "','yyyy-MM-dd hh24:mi:ss') and p.endTime>=to_date('" + currentTime + "','yyyy-MM-dd hh24:mi:ss')";
        return list;
    }

    public List getLists(String hsql) throws HibernateException {

        return getHibernateTemplate().find(hsql);
    }


}
