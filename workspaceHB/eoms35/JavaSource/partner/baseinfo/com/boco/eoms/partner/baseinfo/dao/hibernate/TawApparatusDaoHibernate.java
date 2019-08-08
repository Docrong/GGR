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
import com.boco.eoms.partner.baseinfo.dao.TawApparatusDao;
import com.boco.eoms.partner.baseinfo.model.TawApparatus;

/**
 * <p>
 * Title:仪器仪表 dao的hibernate实现
 * </p>
 * <p>
 * Description:仪器仪表
 * </p>
 * <p>
 * Wed Feb 04 16:31:56 CST 2009
 * </p>
 *
 * @author fengshaohong
 * @version 3.5
 */
public class TawApparatusDaoHibernate extends BaseDaoHibernate implements TawApparatusDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.partner.TawApparatusDao#getTawApparatuss()
     */
    public List getTawApparatuss() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawApparatus tawApparatus";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partner.TawApparatusDao#getTawApparatus(java.lang.String)
     */
    public TawApparatus getTawApparatus(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawApparatus tawApparatus where tawApparatus.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawApparatus) result.iterator().next();
                } else {
                    return new TawApparatus();
                }
            }
        };
        return (TawApparatus) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.partner.TawApparatusDao#saveTawApparatuss(com.boco.eoms.partner.TawApparatus)
     */
    public void saveTawApparatus(final TawApparatus tawApparatus) {
        if ((tawApparatus.getId() == null) || (tawApparatus.getId().equals("")))
            getHibernateTemplate().save(tawApparatus);
        else {
            //this.getSession().refresh(tawApparatus);
            this.getSession().merge(tawApparatus);
            //getHibernateTemplate().saveOrUpdate(tawApparatus);
        }
    }

    /**
     * @see com.boco.eoms.partner.TawApparatusDao#removeTawApparatuss(java.lang.String)
     */
    public void removeTawApparatus(final String id) {
        getHibernateTemplate().delete(getTawApparatus(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TawApparatus tawApparatus = this.getTawApparatus(id);
        if (tawApparatus == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.partner.TawApparatusDao#getTawApparatuss(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getTawApparatuss(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawApparatus tawApparatus where 1=1";
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
     * 判断仪表仪器编号是否唯一
     *
     * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
     */
    public Boolean isunique(final String apparatusId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawApparatus tawApparatus where tawApparatus.apparatusId=:apparatusId";
                Query query = session.createQuery(queryStr);
                query.setString("apparatusId", apparatusId);
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

        return dictType.getDictId();
    }

}