package com.boco.eoms.km.knowledge.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
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
import com.boco.eoms.km.knowledge.dao.KmContentsDao;
import com.boco.eoms.km.knowledge.model.KmContents;

/**
 * <p> Title:知识中间表 DAO 的 Hibernate 实现</p>
 * <p> Description:用于处理 KM_CONTENTS 表的 DAO </p>
 * <p> Tue Mar 24 10:32:12 CST 2009 </p>
 *
 * @author ZHANGXB
 * @since 1.0
 */

public class KmContentsDaoHibernate extends BaseDaoHibernate implements KmContentsDao,
        ID2NameDAO {

    /**
     * 根据知识主键和知识评价更新知识中间表对应的知识记录的知识评价
     *
     * @param contentId 知识主键
     * @param grade     知识评价
     * @author ZHANGXB
     * @since 1.0
     */
    public void updateKmContentsGradeFlagByContentId(final String contentId, final int grade) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("UPDATE KmContents kmContents ");
                switch (grade) {
                    case 1:
                        queryStr.append(" set kmContents.gradeOne = kmContents.gradeOne+1 ");
                        break;
                    case 2:
                        queryStr.append(" set kmContents.gradeTwo = kmContents.gradeTwo+1 ");
                        break;
                    case 3:
                        queryStr.append(" set kmContents.gradeThree = kmContents.gradeThree+1 ");
                        break;
                    case 4:
                        queryStr.append(" set kmContents.gradeFour = kmContents.gradeFour+1 ");
                        break;
                    case 5:
                        queryStr.append(" set kmContents.gradeFive = kmContents.gradeFive+1 ");
                }
                queryStr.append(" where kmContents.contentId=?");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, contentId);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);

    }

    /**
     * 根据知识主键更新知识中间表对应的知识记录的阅读次数
     *
     * @param contentId 知识主键
     * @author ZHANGXB
     * @since 1.0
     */
    public void updateKmContentsReadCountByContentId(final String contentId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //知识的状态：1草稿、2有效、3失效、4删除、5主管审核、6审核角色审核、7返回主管审核
                String queryStr = "update KmContents kmContents set kmContents.readCount=kmContents.readCount+1 where kmContents.contentId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, contentId);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 根据知识主键删知识中间表对应的知识记录
     *
     * @param contentId 知识主键
     * @author ZHANGXB
     * @since 1.0
     */
    public void removeKmContentsByContentId(final String contentId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //知识的状态：1草稿、2有效、3失效、4删除、5主管审核、6审核角色审核、7返回主管审核
                String queryStr = "update KmContents kmContents set kmContents.contentStatus=4 where kmContents.contentId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, contentId);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }


    /**
     * @see com.boco.eoms.km.knowledge.KmContentsDao#getKmContentss()
     */
    public List getKmContentss() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContents kmContents";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsDao#getKmContents(java.lang.String)
     */
    public KmContents getKmContents(final String id) {
        KmContents kmContents = (KmContents) getHibernateTemplate().get(KmContents.class, id);
        if (kmContents == null) {
            kmContents = new KmContents();
        }
        return kmContents;
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsDao#saveKmContentss(com.boco.eoms.km.knowledge.KmContents)
     */
    public void saveKmContents(final KmContents kmContents) {
        if ((kmContents.getId() == null) || (kmContents.getId().equals("")))
            getHibernateTemplate().save(kmContents);
        else
            getHibernateTemplate().saveOrUpdate(kmContents);
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsDao#removeKmContentss(java.lang.String)
     */
    public void removeKmContents(final String id) {
        getHibernateTemplate().delete(getKmContents(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmContents kmContents = this.getKmContents(id);
        if (kmContents == null) {
            return "";
        }
        //TODO 请修改代码
        return null;//kmContents.yourCode();
    }

    /**
     * @see com.boco.eoms.km.knowledge.KmContentsDao#getKmContentss(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmContentss(final Integer curPage, final Integer pageSize, final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from KmContents kmContents";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;

                String queryCountStr = "select count(*) " + queryStr;
                int total = ((Integer) session.createQuery(queryCountStr).iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    Query query = session.createQuery(queryStr);
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询某模型分类下的所有知识
     *
     * @param themeId
     * @return
     */
    public List getKmContentsListByThemeId(final String themeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContents kmContents where kmContents.contentStatus!='4' and kmContents.themeId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, themeId);
                query.setFirstResult(0);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询某模型分类（包括所有子节点)下的所有知识
     *
     * @param themeId
     * @return
     */
    public List getKmContentsListLikeThemeId(final String themeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContents kmContents where kmContents.contentStatus!='4' and kmContents.themeId like ?";
                Query query = session.createQuery(queryStr);
                query.setString(0, themeId + "%");
                query.setFirstResult(0);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询作者处于某状态的知识列表
     *
     * @param contentStatus 知识的状态
     * @param operateUserId 知识的作者
     * @return
     */
    public List getKmContentsByContentStatusAndCreateUser(final String contentStatus, final String operateUserId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContents kmContents where kmContents.contentStatus=? and createUser=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, contentStatus);
                query.setString(1, operateUserId);
                query.setFirstResult(0);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 知识订阅相关
     * 根据知识创建人查询知识信息 (KM_CONTENTS)
     *
     * @param createUser
     * @return
     */
    public List getKmContentsList(final String createUser) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContents kmContents where kmContents.contentStatus!='4' and kmContents.createUser=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, createUser);
                query.setFirstResult(0);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据知识id查询 订阅中间表中的知识记录 KM_CONTNETS
     *
     * @param contentId
     * @return
     */
    public KmContents getKmContentsByContentId(final String contentId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContents kmContents where kmContents.contentId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, contentId);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmContents) result.iterator().next();
                } else {
                    return new KmContents();
                }
            }
        };
        return (KmContents) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据知识主键更新订阅中间表的对应的知识状态 (KM_CONTENTS)
     *
     * @param contentId     知识主键
     * @param contentStatus 知识的状态
     */
    public void updateKmContentsContentStatus(final String contentId, final String contentStatus) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "update KmContents model set model.contentStatus=? where model.contentId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, contentStatus);
                query.setString(1, contentId);
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    public void revivifyAllKmContents() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "update KmContents model set model.contentStatus='2' where model.contentStatus='4'";
                Query query = session.createQuery(queryStr);
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    public void revivifyKmContents(final String themeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "update KmContents model set model.contentStatus='2' where model.themeId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, themeId);
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    //组合查询
    public Map complexQuery(final String sql, final Integer curPage, final Integer pageSize) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //int total =(Integer) session.createSQLQuery(sql).list().size();
                Query query = session.createSQLQuery(sql);
                List result1 = query.list();
                int total = result1.size();
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List queryList = query.list();
                int queryLength = queryList.size();

                List result = new ArrayList();
                for (int i = 0; i < queryLength; i++) {
                    Object[] obj = (Object[]) queryList.get(i);
                    String id = (String) obj[0];
                    String contentTitle = (String) obj[1];
                    String createUser = (String) obj[2];
                    String createDept = (String) obj[3];
                    String contentKeys = (String) obj[4];
                    String tableId = (String) obj[5];
                    String themeId = (String) obj[6];
                    Date createTime = (Date) obj[7];
                    KmContents kmContents = new KmContents();
                    kmContents.setId(id);
                    kmContents.setContentTitle(contentTitle);
                    kmContents.setCreateUser(createUser);
                    kmContents.setCreateDept(createDept);
                    kmContents.setContentKeys(contentKeys);
                    kmContents.setTableId(tableId);
                    kmContents.setThemeId(themeId);
                    kmContents.setCreateTime(createTime);
                    result.add(kmContents);
                }

                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据知识的分类删除订阅中间表的知识记录 (KM_CONTENTS)
     *
     * @param themeId 知识的分类
     */
    public void removeKmContentsByThemeId(final String themeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "update from KmContents kmContents set kmContents.contentStatus=4 where kmContents.themeId like ?";
                Query query = session.createQuery(queryStr);
                query.setString(0, themeId + '%');
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }


    /**
     * 完成回收站的删除工作
     *
     * @param themeId
     */
    public void deleteKmContentsByThemeId(final String themeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmContents model where model.themeId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, themeId);
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 回收站的清空
     */
    public void deleteKmContents() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "delete from KmContents model where model.contentStatus=4";
                Query query = session.createQuery(queryStr);
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }
}
