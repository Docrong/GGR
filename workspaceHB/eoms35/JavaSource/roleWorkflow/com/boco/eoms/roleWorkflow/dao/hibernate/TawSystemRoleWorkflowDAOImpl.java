// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TawSystemRoleWorkflowDAOImpl.java

package com.boco.eoms.roleWorkflow.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.roleWorkflow.dao.ITawSystemRoleWorkflowDAO;
import com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow;

import java.io.Serializable;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class TawSystemRoleWorkflowDAOImpl extends BaseDaoHibernate
        implements ITawSystemRoleWorkflowDAO {

    public TawSystemRoleWorkflowDAOImpl() {
    }

    public List getTawSystemWorkflows() {
        return getHibernateTemplate().find("from TawSystemRoleWorkflow order by remark");
    }

    public TawSystemRoleWorkflow getTawSystemWorkflow(long id) {
        TawSystemRoleWorkflow tawSystemWorkflow = (TawSystemRoleWorkflow) getHibernateTemplate().get(com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow.class, new Long(id));
        if (tawSystemWorkflow == null)
            throw new ObjectRetrievalFailureException(com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow.class, new Long(id));
        else
            return tawSystemWorkflow;
    }

    public void saveTawSystemWorkflow(TawSystemRoleWorkflow tawSystemWorkflow) {
        if (tawSystemWorkflow.getId() == new Long(0L))
            getHibernateTemplate().save(tawSystemWorkflow);
        else
            getHibernateTemplate().saveOrUpdate(tawSystemWorkflow);
    }

    public void removeTawSystemWorkflow(long id)
            throws Exception {
        TawSystemRoleWorkflow tawSystemWorkflow = getTawSystemWorkflow(id);
        getHibernateTemplate().delete(tawSystemWorkflow);
    }

    public List getObjects(Class clazz) {
        return null;
    }

    public Object getObject(Class clazz, Serializable id) {
        return null;
    }

    public void saveObject(Object obj) {
    }

    public void removeObject(Class class1, Serializable serializable) {
    }

    public TawSystemRoleWorkflow getTawSystemWorkflowByName(final String name)
            throws Exception {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemRoleWorkflow workflow where workflow.name=:name ";
                Query query = session.createQuery(queryStr);
                query.setString("name", name);
                if (!query.list().isEmpty())
                    return query.list().get(0);
                else
                    return null;
            }

        };
        return (TawSystemRoleWorkflow) getHibernateTemplate().execute(callback);
    }

    public TawSystemRoleWorkflow getTawSystemWorkflowByBeanId(final String mainBeanId)
            throws Exception {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemRoleWorkflow workflow where workflow.mainServiceBeanId=:mainServiceBeanId ";
                Query query = session.createQuery(queryStr);
                query.setString("mainServiceBeanId", mainBeanId);
                if (!query.list().isEmpty())
                    return query.list().get(0);
                else
                    return null;
            }

        };
        return (TawSystemRoleWorkflow) getHibernateTemplate().execute(callback);
    }
}
