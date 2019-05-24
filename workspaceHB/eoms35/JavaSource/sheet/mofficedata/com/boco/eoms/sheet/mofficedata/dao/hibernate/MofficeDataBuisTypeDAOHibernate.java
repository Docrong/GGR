package com.boco.eoms.sheet.mofficedata.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataBuisTypeDAO;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataBuisType;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public class MofficeDataBuisTypeDAOHibernate extends BaseSheetDaoHibernate implements IMofficeDataBuisTypeDAO,ID2NameDAO {

	public List getBuisTypeObjects() throws HibernateException {
		String hql = "from MofficeDataBuisType";
		return this.getHibernateTemplate().find(hql);
	}

	public List getBuisTypeObjectsByHql(String hql) throws HibernateException {
		return this.getHibernateTemplate().find(hql);
	}

	public void saveOrUpdate(MofficeDataBuisType obj) throws HibernateException {
		getHibernateTemplate().saveOrUpdate(obj);

	}

	public void clearData() throws HibernateException {
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("delete from MofficeDataBuisType where 1=1 ").executeUpdate();
		
	}

	public String id2Name(final String id) throws DictDAOException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				String queryStr = " from MofficeDataBuisType mo where mo.buisValue=:buisValue";
				Query query = session.createQuery(queryStr);				
				query.setString("buisValue", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List list = query.list();
				MofficeDataBuisType mo = null;
				if (list != null && !list.isEmpty()) {
					mo = (MofficeDataBuisType) list.iterator().next();
				} else {
					mo = new MofficeDataBuisType();
					mo.setBuisName(Util.idNoName());
				}
				return mo;
			}
		};
		MofficeDataBuisType mo = null;
		try {
			mo = (MofficeDataBuisType) getHibernateTemplate().execute(callback);
		} catch (Exception e) {
			// 若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
			throw new DictDAOException(e);
		}
		return mo.getBuisName();
	
	}

}