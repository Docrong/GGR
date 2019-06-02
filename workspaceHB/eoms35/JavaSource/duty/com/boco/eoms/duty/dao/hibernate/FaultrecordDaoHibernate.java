package com.boco.eoms.duty.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.dao.IFaultrecordDao;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class FaultrecordDaoHibernate extends BaseDaoHibernate implements
		IFaultrecordDao {

    public List getFaultrecords(final Faultrecord faultrecord) {
        return getHibernateTemplate().find("from Faultrecord");
    }
	
    public void saveFaultrecord(Faultrecord faultrecord) {
		if ((faultrecord.getId() == null) || (faultrecord.getId().equals("")))
			getHibernateTemplate().save(faultrecord);
		else
			getHibernateTemplate().saveOrUpdate(faultrecord);
	}

	public Faultrecord getFaultrecord(String id) {
		Faultrecord faultrecord = (Faultrecord) getHibernateTemplate().get(
				Faultrecord.class, id);
		if (faultrecord == null) {
			throw new ObjectRetrievalFailureException(Faultrecord.class, id);
		}

		return faultrecord;
	}

	public void removeFaultrecord(final String id) {
		getHibernateTemplate().delete(getFaultrecord(id));
	}

    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#getTawRmDispatchRecords(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getFaultrecords(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmDispatchRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Faultrecord as faultrecord where faultrecord.delFlag=0";
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(whereStr);
							query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
}

