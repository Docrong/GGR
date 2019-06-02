/*
 * Created on 2007-9-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.accessories.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawCommonsAccessoriesConfigDaoHibernate extends BaseDaoHibernate
                   implements TawCommonsAccessoriesConfigDao{

	/**
	 * 
	 */
	public List getTawCommonsAccessoriesConfigs() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from TawCommonsAccessoriesConfig");
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao#getTawCommonsAccessoriesConfig(java.lang.Integer)
	 */
	public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(String id) {
		// TODO Auto-generated method stub
	  TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig=null;
	  tawCommonsAccessoriesConfig = 
	  	 (TawCommonsAccessoriesConfig) getHibernateTemplate().get(TawCommonsAccessoriesConfig.class, id);
	  if (tawCommonsAccessoriesConfig == null) {
        BocoLog.warn(this,"uh oh, tawCommonsAccessoriesConfig with id '" + id + "' not found...");
        throw new ObjectRetrievalFailureException(TawCommonsAccessoriesConfig.class, id);
       }
	  return tawCommonsAccessoriesConfig;
	 }
	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao#getTawCommonsAccessoriesConfig(java.lang.Integer)
	 */
	public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(Integer appId) {
		// TODO Auto-generated method stub
	  TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig=null; 
	  List list=getHibernateTemplate().find("from TawCommonsAccessoriesConfig where appId="+appId);
	  if(list.size()>0){
	  	tawCommonsAccessoriesConfig = 
		  	 (TawCommonsAccessoriesConfig)list.get(0);
	  }
	  return tawCommonsAccessoriesConfig;
	 }

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao#saveTawCommonsAccessoriesConfig(com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig)
	 */
	public void saveTawCommonsAccessoriesConfig(TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig) {
		// TODO Auto-generated method stub
		String id=tawCommonsAccessoriesConfig.getId();
		 
		
		if (id==null||"".equals(id)) {			   
			getHibernateTemplate().save(tawCommonsAccessoriesConfig);
		}
		else {		
			TawCommonsAccessoriesConfig tempConfig=getTawCommonsAccessoriesConfig(id);
			tawCommonsAccessoriesConfig.setId(tempConfig.getId());
			getHibernateTemplate().clear();
			getHibernateTemplate().update(tawCommonsAccessoriesConfig);
		}
			
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao#removeTawCommonsAccessoriesConfig(java.lang.Integer)
	 */
	public void removeTawCommonsAccessoriesConfig(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(getTawCommonsAccessoriesConfig(id));
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao#getTawCommonsAccessoriesConfig(java.lang.Integer)
	 */
	public TawCommonsAccessoriesConfig getAccessoriesConfigByAppcode(String appCode) {
		// TODO Auto-generated method stub
	  TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig=null;
	  List list=getHibernateTemplate().find("from TawCommonsAccessoriesConfig config where config.appCode='"+appCode+"'");
	  if(list.size()>0){
	  	tawCommonsAccessoriesConfig 
          = (TawCommonsAccessoriesConfig) list.get(0);
	  }
      if (tawCommonsAccessoriesConfig == null) {
	        BocoLog.warn(this,"uh oh, tawCommonsAccessoriesConfig with appCode '" + appCode + "' not found...");
	        throw new ObjectRetrievalFailureException(TawCommonsAccessoriesConfig.class, appCode);
       }
       return tawCommonsAccessoriesConfig;
	 }

	
}
