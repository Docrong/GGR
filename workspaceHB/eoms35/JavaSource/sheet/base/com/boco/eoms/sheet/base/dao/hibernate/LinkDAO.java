package com.boco.eoms.sheet.base.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.dao.ILinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.businessdredge.model.BusinessDredgeLink;

/**
 * <p>
 * Title:linkdao做适配
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-18 10:38:24
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public abstract class LinkDAO extends BaseSheetDaoHibernate implements ILinkDAO {
    public BaseLink loadSinglePO(String id, Object linkObject) throws HibernateException {
        BaseLink link = (BaseLink) getHibernateTemplate().get(linkObject.getClass(), id);
        return link;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
     */
    public List listAllLinkOfWorkSheet(String id, Object linkObject) throws HibernateException {
        String sql = "from " + linkObject.getClass().getName() + " as link where link.mainId ='" + id
                + "' order by link.operateTime";
        List list = getHibernateTemplate().find(sql);
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadLinkOfStep(java.lang.String,
     *      java.lang.String)
     */
    public List loadLinkOfStep(String processId, String stepId, Object linkObject) throws HibernateException {
        String sql = "from " + linkObject.getClass().getName() + " as link where link.piid ='"
                + processId + "' and link.activeTempleteId = '" + stepId + "'";
        List list = getHibernateTemplate().find(sql);
        return list;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#getDealTemplatesByUserIds
     */
    public List getDealTemplatesByUserIds(String userId, final Integer curPage, final Integer pageSize, int[] aTotal, String linkName, String codition) throws HibernateException {

        final String hql = "from " + linkName + " as link where link.templateFlag=1 "
                + " and link.templateCreateUserId='" + userId + "'" + codition + " order by operateTime desc";
        String countHql = "select count(*) from " + linkName + " as link where link.templateFlag=1 "
                + " and link.templateCreateUserId='" + userId + "'" + codition;

        aTotal[0] = ((Integer) getHibernateTemplate().find(countHql).listIterator().next()).intValue();

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                //分页查询条
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                return query.list();
            }
        };

        return (List) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#getDealTemplatesByUserIds
     */
    public List getSendToMeLink(String linkName, String mainId, String operateRoleId) throws HibernateException {

        final String hql = "from " + linkName + " as link where link.mainId='" + mainId + " and link.toOrgRoleId='" + operateRoleId + "' order by link.operateTime desc";

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                return query.list();
            }
        };

        return (List) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#getLinkOperateByAiid(java.lang.String, java.lang.String)
     */
    public List getLinkOperateByAiid(String aiid, String linkName) throws HibernateException {
        String hql = "from " + linkName + " as link where link.aiid='" + aiid + "'" +
                //增加排序方式，按照操作时间来排序，add by qinmin
                " order by link.operateTime";
        return getHibernateTemplate().find(hql);
    }

    /**
     * 保存或更新link对象
     *
     * @param aiid
     * @return
     * @throws HibernateException
     */
    public void saveOrUpdate(Object obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    /**
     * 删除link对象
     *
     * @param aiid
     * @return
     * @throws HibernateException
     */
    public void removeLink(Object obj) {
        this.getHibernateTemplate().delete(obj);
    }

    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }


}
