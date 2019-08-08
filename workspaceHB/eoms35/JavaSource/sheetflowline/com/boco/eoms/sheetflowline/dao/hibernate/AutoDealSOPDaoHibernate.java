// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoDealSOPDaoHibernate.java

package com.boco.eoms.sheetflowline.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheetflowline.dao.IAutoDealSOPDao;
import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.model.AutoDealSopSheet;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AutoDealSOPDaoHibernate extends BaseDaoHibernate
        implements IAutoDealSOPDao {

    public AutoDealSOPDaoHibernate() {
    }

    public void deleteObject(AutoDealSOP object)
            throws HibernateException {
        getHibernateTemplate().delete(object);
    }

    public AutoDealSOP getSOP(String id)
            throws HibernateException {
        return (AutoDealSOP) getHibernateTemplate().get(com.boco.eoms.sheetflowline.model.AutoDealSOP.class, id);
    }

    public Map listSOP(Integer curPage, Integer pageSize, String lastMonths, String currentTime)
            throws HibernateException {
        return null;

    }

    public void saveObject(AutoDealSOP object)
            throws HibernateException {
        getHibernateTemplate().save(object);
    }

    public void updateObject(AutoDealSOP object)
            throws HibernateException {
        getHibernateTemplate().update(object);
    }

    public Integer executeHsql(String hsql)
            throws HibernateException {
        return null;

    }

    public Map listSOP(Map map, Integer pageIndex, Integer pageSize, String lastMonths, String currentTime)
            throws HibernateException {
        return map;

    }

    public List searchSOP(Map conditionMap)
            throws HibernateException {
        DetachedCriteria criteria = DetachedCriteria.forClass(com.boco.eoms.sheetflowline.model.AutoDealSOP.class);
        for (Iterator iter = conditionMap.keySet().iterator(); iter.hasNext(); ) {
            String key = StaticMethod.nullObject2String(iter.next());
            String value = StaticMethod.nullObject2String(conditionMap.get(key));
            if (!"".equals(value))
                criteria.add(Expression.eq(key, value));
        }

        return getHibernateTemplate().findByCriteria(criteria);
    }

    public void saveSopSheet(AutoDealSopSheet object) {
        getHibernateTemplate().save(object);
    }

    public HashMap listSopSheet(String ruleId, Integer pageIndex, Integer pageSize, String lastMonths, String currentTime)
            throws HibernateException {
        return null;

    }
}
