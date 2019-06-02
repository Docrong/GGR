package com.boco.eoms.sheet.base.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public class BaseSheetDaoHibernate extends EomsSheetHibernateSupport {
	
	
//	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void saveObject(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	/**
	 * @see com.boco.eoms.base.dao.Dao#getObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	public Object getObject(Class clazz, Serializable id) {
		Object o = getHibernateTemplate().get(clazz, id);

		if (o == null) {
			throw new ObjectRetrievalFailureException(clazz, id);
		}

		return o;
	}

	/**
	 * @see com.boco.eoms.base.dao.Dao#getObjects(java.lang.Class)
	 */
	public List getObjects(Class clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}

	/**
	 * @see com.boco.eoms.base.dao.Dao#removeObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	public void removeObject(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getObject(clazz, id));
	}
	
	/**
	 * 清除当前session
	 */
   public void clearObjectOfCurrentSession() {
		getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
	}
	/**
	 * 当有两个相同标识不同实体时执行
	 */
  public void mergeObject(Object obj) {
		getHibernateTemplate().merge(obj);
	}
}
