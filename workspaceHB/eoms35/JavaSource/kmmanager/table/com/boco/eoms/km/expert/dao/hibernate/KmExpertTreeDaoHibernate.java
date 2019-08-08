package com.boco.eoms.km.expert.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.expert.dao.KmExpertTreeDao;

public class KmExpertTreeDaoHibernate extends BaseDaoHibernate implements KmExpertTreeDao {

    /**
     * 得到部门的所有USER（不包含专家身份的用户）
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptids(final String deptid) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("SELECT user FROM TawSystemUser user ");
                queryStr.append("WHERE user.deptid=? AND user.userid !='admin' AND user.deleted='0' AND NOT EXISTS (");
                queryStr.append("SELECT basic.userId FROM KmExpertBasic basic WHERE basic.userId=user.userid");
                queryStr.append(") ORDER BY user.username");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, deptid);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 得到部门的所有USER（不包含专家身份的用户）
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptidsNoSelf(final String deptid, final String userid) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("SELECT user FROM TawSystemUser user ");
                queryStr.append("WHERE user.deptid=? AND user.userid !=? AND user.userid !='admin' AND user.deleted='0' AND NOT EXISTS (");
                queryStr.append("SELECT basic.userId FROM KmExpertBasic basic WHERE basic.userId=user.userid");
                queryStr.append(") ORDER BY user.username");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, deptid);
                query.setString(1, userid);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getUserByKmExpertBasicField(final String fieldName, final String value) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("SELECT user FROM TawSystemUser user ");
                queryStr.append("WHERE user.deleted='0' AND EXISTS (");
                queryStr.append("SELECT basic.userId FROM KmExpertBasic basic WHERE basic.userId=user.userid");
                queryStr.append(" AND basic.");
                queryStr.append(fieldName);
                queryStr.append("=?) ORDER BY user.username");
                Query query = session.createQuery(queryStr.toString());
                query.setString(0, value);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}
