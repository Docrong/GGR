package com.boco.eoms.partner.baseinfo.dao.hibernate;

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
import com.boco.eoms.partner.baseinfo.dao.TawPartnerOilDao;
import com.boco.eoms.partner.baseinfo.model.TawPartnerOil;

/**
 * <p>
 * Title:油机信息 dao的hibernate实现
 * </p>
 * <p>
 * Description:油机信息
 * </p>
 * <p>
 * Thu Feb 05 13:56:15 CST 2009
 * </p>
 *
 * @author fengshaohong
 * @version 3.5
 */
public class TawPartnerOilDaoHibernate extends BaseDaoHibernate implements TawPartnerOilDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.partner.TawPartnerOilDao#getTawPartnerOils()
     */
    public List getTawPartnerOils() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartnerOil tawPartnerOil";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partner.TawPartnerOilDao#getTawPartnerOil(java.lang.String)
     */
    public TawPartnerOil getTawPartnerOil(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartnerOil tawPartnerOil where tawPartnerOil.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawPartnerOil) result.iterator().next();
                } else {
                    return new TawPartnerOil();
                }
            }
        };
        return (TawPartnerOil) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partner.TawPartnerOilDao#saveTawPartnerOils(com.boco.eoms.partner.TawPartnerOil)
     */
    public void saveTawPartnerOil(final TawPartnerOil tawPartnerOil) {
        if ((tawPartnerOil.getId() == null) || (tawPartnerOil.getId().equals("")))
            getHibernateTemplate().save(tawPartnerOil);
        else
            this.getSession().merge(tawPartnerOil);
        //	getHibernateTemplate().saveOrUpdate(tawPartnerOil);
    }

    /**
     * @see com.boco.eoms.partner.TawPartnerOilDao#removeTawPartnerOils(java.lang.String)
     */
    public void removeTawPartnerOil(final String id) {
        getHibernateTemplate().delete(getTawPartnerOil(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TawPartnerOil tawPartnerOil = this.getTawPartnerOil(id);
        if (tawPartnerOil == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.partner.TawPartnerOilDao#getTawPartnerOils(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTawPartnerOils(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartnerOil tawPartnerOil where 1=1";
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
     * 判断油机编号是否唯一
     *
     * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
     */
    public Boolean isunique(final String oil_number) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawPartnerOil tawPartnerOil where tawPartnerOil.oil_number=:oil_number";
                Query query = session.createQuery(queryStr);
                query.setString("oil_number", oil_number);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return Boolean.valueOf(false);
                } else {
                    return Boolean.valueOf(true);
                }
            }
        };
        return (Boolean) getHibernateTemplate().execute(callback);
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
        String dd = dictType.getDictId();
        return dd;
    }
}