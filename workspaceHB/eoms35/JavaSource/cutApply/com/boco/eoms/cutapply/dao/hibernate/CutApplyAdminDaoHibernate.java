package com.boco.eoms.cutapply.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.cutapply.dao.CutApplyAdminDao;
import com.boco.eoms.cutapply.model.CutApplyAdmin;

/**
 * <p>
 * Title:干线割接管理权限人员 dao的hibernate实现
 * </p>
 * <p>
 * Description:干线割接管理
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 *
 * @author wangsixuan
 * @version 3.5
 */
public class CutApplyAdminDaoHibernate extends BaseDaoHibernate implements CutApplyAdminDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#getCutApplys()
     */
    public List getCutApplyAdmins() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CutApplyAdmin cutApplyAdmin";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#getCutApply(java.lang.String)
     */
    public CutApplyAdmin getCutApplyAdmin(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CutApplyAdmin cutApplyAdmin where cutApplyAdmin.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (CutApplyAdmin) result.iterator().next();
                } else {
                    return new CutApplyAdmin();
                }
            }
        };
        return (CutApplyAdmin) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#saveCutApplys(com.boco.eoms.cutapply.CutApply)
     */
    public void saveCutApplyAdmin(final CutApplyAdmin cutApplyAdmin) {
        if ((cutApplyAdmin.getId() == null) || (cutApplyAdmin.getId().equals("")))
            getHibernateTemplate().save(cutApplyAdmin);
        else
            getHibernateTemplate().saveOrUpdate(cutApplyAdmin);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#removeCutApplys(java.lang.String)
     */
    public void removeCutApplyAdmin(final String id) {
        getHibernateTemplate().delete(getCutApplyAdmin(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        CutApplyAdmin cutApply = this.getCutApplyAdmin(id);
        if (cutApply == null) {
            return "";
        }
        //TODO 请修改代码
        return "";
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#getCutApplys(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getCutApplyAdmins(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CutApplyAdmin cutApplyAdmin";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#removeCutApplys(java.lang.String)
     */
    public List getCutApplyAdminsByCondition(final String hql) {
        String condition = "from CutApplyAdmin c where 1=1 ";
        condition += hql;
        condition += " order by c.id";
        return getHibernateTemplate().find(condition);
    }

}