package com.boco.eoms.commons.system.area.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.commons.system.area.dao.TawSystemAreaDao;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemAreaDaoHibernate extends BaseDaoHibernate implements
        TawSystemAreaDao, ID2NameDAO {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#getChildAreas(java.lang.String)
     */
    public List getChildAreas(String areaId) {
        return getHibernateTemplate().find(
                "from TawSystemArea area where area.parentAreaid='" + areaId
                        + "' and " + StaticMethod.noDeletedCon("area"));
    }

    /**
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#getTawSystemAreas(com.boco.eoms.commons.system.area.model.TawSystemArea)
     */
    public List getTawSystemAreas(final TawSystemArea tawSystemArea) {
        return getHibernateTemplate().find("from TawSystemArea");
        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (tawSystemArea == null) {
         * return getHibernateTemplate().find("from TawSystemArea"); } else { //
         * filter on properties set in the tawSystemArea HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex =
         * Example.create(tawSystemArea).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return session.createCriteria(TawSystemArea.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#getTawSystemArea(Integer
     * id)
     */
    public TawSystemArea getTawSystemArea(final Integer id) {
        TawSystemArea tawSystemArea = (TawSystemArea) getHibernateTemplate()
                .get(TawSystemArea.class, id);
        if (tawSystemArea == null) {
            throw new ObjectRetrievalFailureException(TawSystemArea.class, id);
        }
        return tawSystemArea;
    }

    /**
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#saveTawSystemArea(TawSystemArea
     * tawSystemArea)
     */
    public void saveTawSystemArea(final TawSystemArea tawSystemArea) {
        if ((tawSystemArea.getId() == null)
                || (tawSystemArea.getId().equals("")))
            getHibernateTemplate().save(tawSystemArea);
        else
            getHibernateTemplate().merge(tawSystemArea);
    }

    /**
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#removeTawSystemArea(Integer
     * id)
     */
    public void removeTawSystemArea(final Integer id) {
        getHibernateTemplate().delete(getTawSystemArea(id));
    }

    /**
     * 用于分页显示 curPage 当前页数 pageSize 每页显示数 whereStr where的条件语句，必须以"where"开头,可以为空
     */
    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        // filter on properties set in the tawSystemArea
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemArea";
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

    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize) {
        return this.getTawSystemAreas(curPage, pageSize, null);
    }

    /**
     * 根据地域ID查询地域信息
     *
     * @param areaid
     * @return
     */
    public TawSystemArea getAreaByAreaId(String areaid) {
        String hql = " from TawSystemArea sysarea where sysarea.areaid='"
                + areaid + "'";
        return (TawSystemArea) getHibernateTemplate().find(hql).get(
                TawSystemAreaUtil.AREA_DEFAULT_INTVAL);
    }

    /**
     * 查询某地域的下一级地域信息
     *
     * @param areaid
     * @return
     */
    public List getSonAreaByAreaId(String areaid) {
        String hql = " from TawSystemArea sysarea where sysarea.parentAreaid='"
                + areaid + "'";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * 查询同级地域信息
     *
     * @param parentareaid
     * @param ordercode
     * @return
     */
    public List getSameLevelArea(String parentareaid, Integer ordercode) {
        String hql = " from TawSystemArea sysarea where sysarea.parentAreaid='"
                + parentareaid + "' and sysarea.ordercode='" + ordercode + "'";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * 查询某地域名称是否存在
     *
     * @param areaname
     * @return
     */
    public boolean isExitAreaName(String areaname) {
        String hql = " from TawSystemArea sysarea where sysarea.areaname='"
                + areaname + "'";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询某地域的所有子地域信息
     *
     * @param areaid
     * @return
     */
    public List getAllSonAreaByAreaid(String areaid) {
        String hql = " from TawSystemArea sysarea where sysarea.areaid like '"
                + areaid + "%'";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from TawSystemArea area where area.areaid=:areaid";

                Query query = session.createQuery(queryStr);
                query.setString("areaid", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (TawSystemArea) result.iterator().next();
                }
                return new TawSystemArea();
            }
        };
        return ((TawSystemArea) getHibernateTemplate().execute(callback))
                .getAreaname();

    }

    /**
     * 查询某地域根据 areacode
     *
     * @param areacode
     * @return
     */
    public TawSystemArea getAreaByCode(String areacode) {
        String hql = " from TawSystemArea sysarea where sysarea.areacode='"
                + areacode + "'";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemArea tawSystemArea = new TawSystemArea();
        if (list != null && list.size() > 0) {
            tawSystemArea = (TawSystemArea) list.get(0);
        }
        return tawSystemArea;
    }

    /**
     * 查询某地域根据 areacode parentAreaid
     *
     * @param parentAreaid
     * @param areacode
     * @return
     */
    public TawSystemArea getAreaByCode(String areacode, String parentAreaid) {
        String hql = " from TawSystemArea sysarea where sysarea.areacode='"
                + areacode + "' and sysarea.parentAreaid='" + parentAreaid
                + "' ";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemArea tawSystemArea = new TawSystemArea();
        if (list != null && list.size() > 0) {
            tawSystemArea = (TawSystemArea) list.get(0);
        }
        return tawSystemArea;
    }

    /*
     *
     */
    public boolean getAreaNameByName(String areaName, String parentAreaid) {
        boolean bool = false;
        String sql = " from TawSystemArea where areaname = '" + areaName
                + "' and parentAreaid = '" + parentAreaid + "'";
        List list = (ArrayList) getHibernateTemplate().find(sql);
        if (list != null && list.size() > 0) {
            bool = true;
        }
        return bool;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#getAreaOfUser(java.lang.String)
     */
    public TawSystemArea getAreaOfUser(String userId) {

        List list = getHibernateTemplate()
                .find(
                        "select distinct area from TawSystemArea area ,TawSystemUser user,TawSystemDept dept where user.userid='"
                                + userId
                                + "' and user.deptid=dept.deptid and dept.areaid=area.areaid and "
                                + StaticMethod.noDeletedCon("area"));
        return (TawSystemArea) list.iterator().next();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.area.dao.TawSystemAreaDao#getAreaOfDept(java.lang.String)
     */
    public TawSystemArea getAreaOfDept(String deptId) {
        List list = getHibernateTemplate()
                .find(
                        "select distinct area from TawSystemArea area,TawSystemDept dept where area.areaid=dept.areaid and dept.deptId='" + deptId + "'");
        return (TawSystemArea) list.iterator().next();
    }

    public List getAreas() {
        return getHibernateTemplate().find("from TawSystemArea");
    }

}
