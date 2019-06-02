package com.boco.eoms.base.service.impl;

import java.io.Serializable;
import java.util.List;


import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.base.service.Manager;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 * <p>
 * <a href="BaseManager.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseManager implements Manager {
	
	protected Dao dao = null;

	/**
	 * @see com.boco.eoms.base.service.Manager#setDao(com.boco.eoms.base.dao.Dao)
	 */
	public void setDao(Dao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.base.service.Manager#getObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	public Object getObject(Class clazz, Serializable id) {
		return dao.getObject(clazz, id);
	}

	/**
	 * @see com.boco.eoms.base.service.Manager#getObjects(java.lang.Class)
	 */
	public List getObjects(Class clazz) {
		return dao.getObjects(clazz);
	}

	/**
	 * @see com.boco.eoms.base.service.Manager#removeObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	public void removeObject(Class clazz, Serializable id) {
		dao.removeObject(clazz, id);
	}

	/**
	 * @see com.boco.eoms.base.service.Manager#saveObject(java.lang.Object)
	 */
	public void saveObject(Object o) {
		dao.saveObject(o);
	}
}
