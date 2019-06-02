package com.boco.eoms.sheet.dealtypeconfig.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.dealtypeconfig.dao.IDealTypeConfigDAO;
import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;

public class DealTypeConfigDAOImpl extends BaseDaoHibernate implements IDealTypeConfigDAO {
	
	public void removeDealTypeConfig(String id) throws HibernateException {
		getHibernateTemplate().delete(getDealTypeConfig(id));
	}

	public void saveDealTypeConfig(DealTypeConfig config) throws HibernateException {
		getHibernateTemplate().saveOrUpdate(config);
	}
	
	public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId, String taskName) throws HibernateException {
		String hql = " from DealTypeConfig config where config.flowName='"+flowName+"'"
		+" and config.userId='admin'"
		+" and config.taskName='"+taskName+"'";
		List list = getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return (DealTypeConfig)list.get(0);
		}else{
			return null;
		}
	}

	public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId) throws HibernateException {
		String hql = " from DealTypeConfig config where config.flowName='"+flowName+"'"
		+" and config.userId='admin'";
		List list = getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return (DealTypeConfig)list.get(0);
		}else{
			return null;
		}
	}
	public DealTypeConfig getDealTypeConfig(String id) throws HibernateException {
		DealTypeConfig config = (DealTypeConfig) getHibernateTemplate().get(DealTypeConfig.class, id);
		if (config == null) {
			throw new ObjectRetrievalFailureException(DealTypeConfig.class, id);
		}
		return config;
	}

	public DealTypeConfig getDealTypeConfigByAdmin(String flowName, String taskName) throws HibernateException {
		String hql = " from DealTypeConfig config where config.flowName='"+flowName+"'"
		+" and config.userId='admin'"
		+" and config.taskName='"+taskName+"'";
		List list = getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return (DealTypeConfig)list.get(0);
		}else{
			return null;
		}
	}
	public DealTypeConfig getDealTypeConfigByAdmin(String flowName) throws HibernateException {
		String hql = " from DealTypeConfig config where config.flowName='"+flowName+"'"
		+" and config.userId='admin'";
		List list = getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return (DealTypeConfig)list.get(0);
		}else{
			return null;
		}
	}
	public List getDealTypeConfigByCondition(String condition) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public List getDealTypeConfigByCondition(HashMap condition) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public HashMap getListByCondition(String queryStr, Integer curPage, Integer pageSize) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}



}
