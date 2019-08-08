package com.boco.eoms.sheet.agentmaintenance.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.agentmaintenance.dao.UserMadeDAO;
import com.boco.eoms.sheet.agentmaintenance.model.UserMade;

public class UserMadeDAOHibernate extends BaseDaoHibernate implements UserMadeDAO {

    public void remove(String id) {
        getHibernateTemplate().delete(getDataById(id));
    }

    public void save(UserMade useMade) {
        if (useMade.getId() == null || useMade.getId().equals("")) {
            getHibernateTemplate().save(useMade);
        } else {
            getHibernateTemplate().update(useMade);
        }
    }

    public List getAllUser() {
        return getHibernateTemplate().find("from UserMade userMade");
    }

    public Map getObjectByUser(final String user) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from UserMade userMade";
                if (user != null && user.length() > 0 && !user.equals(""))
                    queryStr += " where 1=1 and userMade.user_id='" + user + "'";

                Query query = session.createQuery(queryStr);
                List result = query.list();
                HashMap map = new HashMap();
                map.put("result", result);
                map.put("total", new Integer(result.size()));
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public UserMade getDataById(String id) {
        UserMade userMade = (UserMade) getHibernateTemplate().get(UserMade.class, id);
        if (userMade == null) {
            throw new ObjectRetrievalFailureException(UserMade.class, id);
        }
        return userMade;
    }


}
