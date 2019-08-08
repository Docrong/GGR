
package com.boco.eoms.commons.sheet.special.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.util.Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author panlong
 * 下午05:38:59
 */
public class TawSheetSpecialDaoHibernate extends BaseDaoHibernate implements TawSheetSpecialDao, ID2NameDAO {

    /**
     * @see com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao#getTawSheetSpecials(com.boco.eoms.commons.sheet.special.model.TawSheetSpecial)
     */
    public List getTawSheetSpecials(final TawSheetSpecial tawSheetSpecial) {
        return getHibernateTemplate().find("from TawSheetSpecial");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSheetSpecial == null) {
            return getHibernateTemplate().find("from TawSheetSpecial");
        } else {
            // filter on properties set in the tawSheetSpecial
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSheetSpecial).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSheetSpecial.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao#getTawSheetSpecial(Integer id)
     */
    public TawSheetSpecial getTawSheetSpecial(final Integer id) {
        TawSheetSpecial tawSheetSpecial = (TawSheetSpecial) getHibernateTemplate().get(TawSheetSpecial.class, id);
        if (tawSheetSpecial == null) {
            throw new ObjectRetrievalFailureException(TawSheetSpecial.class, id);
        }

        return tawSheetSpecial;
    }

    /**
     * @see com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao#saveTawSheetSpecial(TawSheetSpecial tawSheetSpecial)
     */
    public void saveTawSheetSpecial(final TawSheetSpecial tawSheetSpecial) {
        if ((tawSheetSpecial.getId() == null) || (tawSheetSpecial.getId().equals("")))
            getHibernateTemplate().save(tawSheetSpecial);
        else
            getHibernateTemplate().saveOrUpdate(tawSheetSpecial);
    }

    /**
     * @see com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao#removeTawSheetSpecial(Integer id)
     */
    public void removeTawSheetSpecial(final Integer id) {
        getHibernateTemplate().delete(id);
    }

    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawSheetSpecials(final Integer curPage, final Integer pageSize, final String whereStr) {
        // filter on properties set in the tawSheetSpecial
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TawSheetSpecial";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr).iterate()
                        .next()).intValue();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize.intValue()
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

    public Map getTawSheetSpecials(final Integer curPage, final Integer pageSize) {
        return this.getTawSheetSpecials(curPage, pageSize, null);
    }

    /**
     * 根据专业ID查询专业信息
     *
     * @param areaid
     * @return
     */
    public TawSheetSpecial getSpecialByspecialId(String specialid) {
        String hql = " from TawSheetSpecial spe where spe.speid='" + specialid + "'";
        return (TawSheetSpecial) getHibernateTemplate().find(hql).get(0);
    }

    /**
     * 查询某专业的下一级专业信息
     *
     * @param specialid
     * @return
     */
    public List getSonspecialByspecialId(String specialid) {
        String hql = " from TawSheetSpecial spe where spe.parspeid='" + specialid + "'";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * 查询同级专业信息
     *
     * @param parentspecialid
     * @param ordercode
     * @return
     */
    public List getSameLevelspecial(String parentspecialid, Integer ordercode) {
        String hql = " from TawSheetSpecial spe where spe.parspeid='" + parentspecialid + "' and spe.ordercode='" + ordercode + "'";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * 查询某专业是否存在
     *
     * @param specialname
     * @return
     */
    public boolean isExitspecialName(String specialid) {
        String hql = " from TawSheetSpecial spe where spe.speid='" + specialid + "'";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询某专业的所有子专业信息
     *
     * @param specialid
     * @return
     */
    public List getAllSonspecialByspecialid(String specialid) {
        String hql = " from TawSheetSpecial spe where spe.speid like '" + specialid + "%' and spe.speid !='" + specialid + "'";
        return (ArrayList) getHibernateTemplate().find(hql);
    }


    /*
     * id2name，即字典id转为字典名称 added by zhangying
     *
     * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以角色ID为条件查询
                String queryStr = " from TawSheetSpecial spe where spe.speid=:speId";

                Query query = session.createQuery(queryStr);
                //角色ID号
                query.setString("speId", id);
                //仅查一条
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSheetSpecial tawSpecial = null;

                if (list != null && !list.isEmpty()) {
                    //不为空则取dept
                    tawSpecial = (TawSheetSpecial) list.iterator().next();
                } else {
                    //为空，写入将部门名称设为未知联系人
                    tawSpecial = new TawSheetSpecial();
                    tawSpecial.setSpecialname(Util.idNoName());
                }
                return tawSpecial;
            }
        };

        TawSheetSpecial special = null;
        try {
            special = (TawSheetSpecial) getHibernateTemplate().execute(callback);
        } catch (Exception e) {
            //若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
            throw new DictDAOException(e);
        }
        return special.getSpecialname();
    }

}
