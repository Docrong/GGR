package com.boco.eoms.km.lesson.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.km.lesson.dao.KmLessonDao;
import com.boco.eoms.km.lesson.model.KmLesson;

/**
 * <p>
 * Title:课程创建 dao的hibernate实现
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:51 CST 2009
 * </p>
 *
 * @author mosquito
 * @version 1.0
 */
public class KmLessonDaoHibernate extends BaseDaoHibernate implements KmLessonDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.lesson.creat.CreatlessonDao#getCreatlessons()
     */
    public List getKmLessons() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmLesson kmLesson";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.lesson.creat.KmLessonDao#getKmLesson(java.lang.String)
     */
    public KmLesson getKmLesson(final String id) {
        KmLesson kmLesson = (KmLesson) getHibernateTemplate().get(KmLesson.class, id);
        if (kmLesson == null) {
            kmLesson = new KmLesson();
        }
        return kmLesson;
    }

    /**
     * @see com.boco.eoms.lesson.creat.KmLessonDao#saveKmLessons(com.boco.eoms.lesson.creat.KmLesson)
     */
    public void saveKmLesson(final KmLesson kmLesson) {
        if ((kmLesson.getId() == null) || (kmLesson.getId().equals("")))
            getHibernateTemplate().save(kmLesson);
        else
            getHibernateTemplate().saveOrUpdate(kmLesson);
    }

    /**
     *
     */
    public void updateKmLesson(final String id, final String subjectName,
                               final String businessType, final String theme,
                               final String subjectContents, final String attachment, final String totalTime) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "update KmLesson kmLesson set kmLesson.subjectName=?," +
                        " kmLesson.businessType=?, kmLesson.theme=?, kmLesson.subjectContents=?," +
                        "kmLesson.attachment=?, kmLesson.totalTime=? where kmLesson.id=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, subjectName);
                query.setString(1, businessType);
                query.setString(2, theme);
                query.setString(3, subjectContents);
                query.setString(4, attachment);
                query.setString(5, totalTime);
                query.setString(6, id);

                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.lesson.creat.KmLessonDao#removeKmLessons(java.lang.String)
     */
    public void removeKmLesson(final String id) {
        getHibernateTemplate().delete(getKmLesson(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmLesson kmLesson = this.getKmLesson(id);
        if (kmLesson == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.lesson.creat.KmLessonDao#getKmLessons(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmLessons(final Integer curPage, final Integer pageSize,
                            final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmLesson kmLesson";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(kmLesson.id) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    queryStr += " order by kmLesson.createTime desc";

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


}