package com.boco.eoms.parter.baseinfo.contract.dao.hibernate;

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
import com.boco.eoms.parter.baseinfo.contract.dao.TawContractDao;
import com.boco.eoms.parter.baseinfo.contract.model.TawContract;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.parter.baseinfo.contract.dao.*;
import com.boco.eoms.parter.baseinfo.contract.model.TawContractPay;
import com.boco.eoms.parter.baseinfo.contract.util.*;
/**
 * <p>
 * Title:代维合同 dao的hibernate实现
 * </p>
 * <p>
 * Description:代维合同
 * </p>
 * <p>
 * Wed Apr 01 08:58:31 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawContractDaoHibernate extends BaseDaoHibernate implements TawContractDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.parter.baseinfo.contract.TawContractDao#getTawContracts()
	 * 
	 */
	public List getTawContracts() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawContract tawContract";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 
	 * @see com.boco.eoms.parter.baseinfo.contract.TawContractDao#getTawContract(java.lang.String)
	 * 
	 */
	public TawContract getTawContract(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawContract tawContract where tawContract.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawContract) result.iterator().next();
				} else {
					return new TawContract();
				}
			}
		};
		return (TawContract) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.parter.baseinfo.contract.TawContractDao#saveTawContracts(com.boco.eoms.parter.baseinfo.contract.TawContract)
	 * 
	 */
	public void saveTawContract(final TawContract tawContract) {
		if ((tawContract.getId() == null) || (tawContract.getId().equals("")))
			getHibernateTemplate().save(tawContract);
		else
			getHibernateTemplate().saveOrUpdate(tawContract);
	}

	/**
	 * 
	 * @see com.boco.eoms.parter.baseinfo.contract.TawContractDao#removeTawContracts(java.lang.String)
	 * 
	 */
    public void removeTawContract(final String id) {
		getHibernateTemplate().delete(getTawContract(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 * 
	 */
	public String id2Name(String id) throws DictDAOException {
		TawContract tawContract = this.getTawContract(id);
		if(tawContract==null){
			return "";
		}
		// TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.parter.baseinfo.contract.TawContractDao#getTawContracts(java.lang.Integer,java.lang.Integer,java.lang.String)
	 * 
	 */ 
	public Map getTawContracts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawContract tawContract where 1=1 ";
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
	public Boolean isunique(final String contractName) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawContract tawContract where tawContract.contractName=:contractName";
				Query query = session.createQuery(queryStr);
				query.setString("contractName", contractName);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return Boolean.valueOf(false);
				} else {
					return Boolean.valueOf(true);
				}
			}
		};
		return (Boolean) getHibernateTemplate().execute(callback);
	}
	
	
	/**
	 * 合同付款管理
	 */
	/**
	 * 
	 */
	public Map getTawContractPays(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawContractPay tawContractPay ";
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
	
	public TawContractPay getTawContractPay(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawContractPay tawContractPay where tawContractPay.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawContractPay) result.iterator().next();
				} else {
					return new TawContractPay();
				}
			}
		};
		return (TawContractPay) getHibernateTemplate().execute(callback);
	}
	
	public List getTawContractPayByContractid(final String contractid) {		
			String queryStr = "from TawContractPay tawContractPay where tawContractPay.contractid = '"+ contractid + "'";
			List list = getHibernateTemplate().find(queryStr);
			return list;
	}
    
	/**
	 * 
	 * @see com.boco.eoms.parter.baseinfo.contract.TawContractPayDao#saveTawContractPays(com.boco.eoms.parter.baseinfo.contract.TawContractPay)
	 * 
	 */
	public void saveTawContractPay(final TawContractPay tawContractPay) {
		if ((tawContractPay.getId() == null) || (tawContractPay.getId().equals("")))
			getHibernateTemplate().save(tawContractPay);
		else
			getHibernateTemplate().saveOrUpdate(tawContractPay);
	}
	

    
}