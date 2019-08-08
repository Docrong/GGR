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
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.cutapply.dao.CutApplyDao;
import com.boco.eoms.cutapply.model.CutApply;

/**
 * <p>
 * Title:干线割接管理 dao的hibernate实现
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
public class CutApplyDaoHibernate extends BaseDaoHibernate implements CutApplyDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#getCutApplys()
     */
    public List getCutApplys() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CutApply cutApply";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#getCutApply(java.lang.String)
     */
    public CutApply getCutApply(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CutApply cutApply where cutApply.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (CutApply) result.iterator().next();
                } else {
                    return new CutApply();
                }
            }
        };
        return (CutApply) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#saveCutApplys(com.boco.eoms.cutapply.CutApply)
     */
    public void saveCutApply(final CutApply cutApply) {
        if ((cutApply.getId() == null) || (cutApply.getId().equals("")))
            getHibernateTemplate().save(cutApply);
        else
            getHibernateTemplate().saveOrUpdate(cutApply);
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#removeCutApplys(java.lang.String)
     */
    public void removeCutApply(final String id) {
        getHibernateTemplate().delete(getCutApply(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        CutApply cutApply = this.getCutApply(id);
        if (cutApply == null) {
            return "";
        }
        //TODO 请修改代码
        return "";
    }

    /**
     * @see com.boco.eoms.cutapply.CutApplyDao#getCutApplys(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getCutApplys(final Integer curPage, final Integer pageSize,
                            final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from CutApply cutApply";
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
    public List getCutApplysByCondition(final String hql) {
        String condition = "from CutApply c where 1=1 ";
        condition += hql;
        condition += " order by c.cutStartTime";
        return getHibernateTemplate().find(condition);
    }

    /*
     * name2Id，即字典id转为字典名称 added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String name2Id(final String dictName, final String parentDictId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以角色ID为条件查询
                String queryStr = " from TawSystemDictType dictType where dictType.dictName=:dictName and dictType.parentDictId=:parentDictId";

                Query query = session.createQuery(queryStr);
                // 角色ID号
                query.setString("dictName", dictName);
                query.setString("parentDictId", parentDictId);
                // 仅查一条
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSystemDictType dictType = null;

                if (list != null && !list.isEmpty()) {
                    // 不为空则取dept
                    dictType = (TawSystemDictType) list.iterator().next();
                } else {
                    // 为空，写入将部门名称设为未知联系人
                    dictType = new TawSystemDictType();
                    dictType.setDictName("");
                }
                return dictType;
            }
        };

        TawSystemDictType dictType = null;

        dictType = (TawSystemDictType) getHibernateTemplate().execute(
                callback);

        return dictType.getDictId();
    }

}