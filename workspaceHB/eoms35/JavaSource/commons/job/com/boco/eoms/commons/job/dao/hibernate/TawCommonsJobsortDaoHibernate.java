package com.boco.eoms.commons.job.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * <p>Title:
 * </p>
 * <p>Description:任务类型DAO实现类
 * </p>
 * <p>Apr 10, 2007 10:33:56 AM</p>
 *
 * @author 秦敏
 * @version 1.0
 */
public class TawCommonsJobsortDaoHibernate extends BaseDaoHibernate implements
        TawCommonsJobsortDao {

    /**
     * @see com.boco.eoms.commons.job.dao.TawCommonsJobsortDao#getTawCommonsJobsorts(com.boco.eoms.commons.job.model.TawCommonsJobsort)
     */
    public List getTawCommonsJobsorts(final TawCommonsJobsort tawCommonsJobsort) {
        return getHibernateTemplate().find("from TawCommonsJobsort");

        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (tawCommonsJobsort == null) {
         * return getHibernateTemplate().find("from TawCommonsJobsort"); } else { //
         * filter on properties set in the tawCommonsJobsort HibernateCallback
         * callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex =
         * Example.create(tawCommonsJobsort).ignoreCase().enableLike(MatchMode.ANYWHERE);
         * return
         * session.createCriteria(TawCommonsJobsort.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }

    /**
     * @see com.boco.eoms.commons.job.dao.TawCommonsJobsortDao#getTawCommonsJobsort(Integer
     * id)
     */
    public TawCommonsJobsort getTawCommonsJobsort(final Integer id) {
        TawCommonsJobsort tawCommonsJobsort = (TawCommonsJobsort) getHibernateTemplate()
                .get(TawCommonsJobsort.class, id);
        if (tawCommonsJobsort == null) {
            BocoLog.warn(this, "uh oh, tawCommonLogDeploy with id '" + id + "' not found...");
//			throw new ObjectRetrievalFailureException(TawCommonsJobsort.class,
//					id);
        }

        return tawCommonsJobsort;
    }

    /**
     * @see com.boco.eoms.commons.job.dao.TawCommonsJobsortDao#saveTawCommonsJobsort(TawCommonsJobsort
     * tawCommonsJobsort)
     */
    public void saveTawCommonsJobsort(final TawCommonsJobsort tawCommonsJobsort) {
        if ((tawCommonsJobsort.getId() == null)
                || (tawCommonsJobsort.getId().equals("")))
            getHibernateTemplate().save(tawCommonsJobsort);
        else
            getHibernateTemplate().saveOrUpdate(tawCommonsJobsort);
    }

    /**
     * @see com.boco.eoms.commons.job.dao.TawCommonsJobsortDao#removeTawCommonsJobsort(Integer
     * id)
     */
    public void removeTawCommonsJobsort(final Integer id) {
        getHibernateTemplate().delete(getTawCommonsJobsort(id));
    }

    //2009-5-21 根据任务类型名称查询数据
    public List getTawCommonsJobsortByJobSortName(String jobSortName) {
        String hql = "from TawCommonsJobsort jobsort where jobsort.jobSortName = ?";
        List list = new ArrayList();
        list = getHibernateTemplate().find(hql, jobSortName);
        return list;
    }
}
