
package com.boco.eoms.sheet.businessimplementyy.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.TaskDAOImpl;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.businessimplementyy.dao.IBusinessImplementYYTaskDAO;

public class BusinessImplementYYTaskDAOHibernate extends TaskDAOImpl implements IBusinessImplementYYTaskDAO {

    public Integer getCountOfBrother(final Object taskObject, final String sheetKey, final String parentLevelId) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //取列表数量
                String queryCountStr = "select count(distinct task.levelId) from " + taskObject.getClass().getName()
                        + " task where task.sheetKey = '" + sheetKey + "' and task.parentLevelId = '" + parentLevelId + "' ";
                Query query = session.createQuery(queryCountStr);

                Integer total = (Integer) query.iterate().next();
                return total;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }
}
