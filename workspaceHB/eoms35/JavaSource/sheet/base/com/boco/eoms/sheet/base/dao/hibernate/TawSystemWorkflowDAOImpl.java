/*
 * Created on 2007-11-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.dao.ITawSystemWorkflowDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemWorkflowDAOImpl extends BaseDaoHibernate implements ITawSystemWorkflowDAO {

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.ITawSystemWorkflowDAO#getTawSystemWorkflows()
     */
    public List getTawSystemWorkflows() {
        return getHibernateTemplate().find("from TawSystemWorkflow order by remark");
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.ITawSystemWorkflowDAO#getTawSystemWorkflow(long)
     */
    public TawSystemWorkflow getTawSystemWorkflow(long id) {
        TawSystemWorkflow tawSystemWorkflow = (TawSystemWorkflow) getHibernateTemplate().get(TawSystemWorkflow.class, new Long(id));
        if (tawSystemWorkflow == null) {
            throw new ObjectRetrievalFailureException(TawSystemWorkflow.class, new Long(id));
        }

        return tawSystemWorkflow;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.ITawSystemWorkflowDAO#saveTawSystemWorkflow(com.boco.eoms.sheet.base.model.TawSystemWorkflow)
     */
    public void saveTawSystemWorkflow(TawSystemWorkflow tawSystemWorkflow) {
        if (tawSystemWorkflow.getId() == new Long(0))
            getHibernateTemplate().save(tawSystemWorkflow);
        else
            getHibernateTemplate().saveOrUpdate(tawSystemWorkflow);

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.ITawSystemWorkflowDAO#removeTawSystemWorkflow(long)
     */
    public void removeTawSystemWorkflow(long id) throws Exception {
        TawSystemWorkflow tawSystemWorkflow = getTawSystemWorkflow(id);
        getHibernateTemplate().delete(tawSystemWorkflow);

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.base.dao.Dao#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.base.dao.Dao#getObject(java.lang.Class, java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void saveObject(Object o) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.base.dao.Dao#removeObject(java.lang.Class, java.io.Serializable)
     */
    public void removeObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub

    }

    /**
     * 根据流程名称查询流程信息
     *
     * @param name 流程名称
     * @return
     * @throws Exception
     */
    public TawSystemWorkflow getTawSystemWorkflowByName(final String name) throws SheetException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemWorkflow workflow where "
                        + "workflow.name=:name ";
                Query query = session.createQuery(queryStr);
                query.setString("name", name);
                if (!query.list().isEmpty()) {
                    return query.list().get(0);
                } else {
                    return null;
                }
            }
        };
        return (TawSystemWorkflow) getHibernateTemplate().execute(callback);
    }

    public TawSystemWorkflow getTawSystemWorkflowByBeanId(final String mainBeanId) throws SheetException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemWorkflow workflow where "
                        + "workflow.mainServiceBeanId=:mainServiceBeanId ";
                Query query = session.createQuery(queryStr);
                query.setString("mainServiceBeanId", mainBeanId);
                if (!query.list().isEmpty()) {
                    return query.list().get(0);
                } else {
                    return null;
                }
            }
        };
        return (TawSystemWorkflow) getHibernateTemplate().execute(callback);
    }
}
