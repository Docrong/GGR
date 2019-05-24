package com.boco.eoms.sheet.base.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public class EomsSheetHibernateTemplate extends HibernateTemplate {

	public Object get(Class arg0, Serializable arg1) throws DataAccessException {
		Object o = super.get(arg0, arg1);
		Object newObject = null;
		if (o != null) {
			try {
				newObject = o.getClass().newInstance();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (o != null) {
				SheetBeanUtils.eomsDB2Model(o, newObject);
				/***
				 * Remove this instance from the session cache. Changes to the instance 
				 * will not be synchronized with the database. This operation cascades to associated 
				 * instances if the association is mapped with cascade="evict"
				 * 以后可能会影响hibernate配置文件中的级联关系设置
				 */
				this.getSessionFactory().getCurrentSession().evict(o);
			}
			return newObject;
		} else {
			return o;
		}
	}

	public List find(String arg0) throws DataAccessException {
		List list = super.find(arg0);
		List returnList = new ArrayList();		
		for (int i = 0; list != null && i < list.size(); i++) {
			Object o = list.get(i);
			Object newObject = null;
			if (o != null) {
				if (o instanceof java.lang.Integer) {
					returnList.add(o);
				} else {
					try {
						newObject = o.getClass().newInstance();
					} catch (Exception e) {
						returnList.add(o);
						continue;
					}
					SheetBeanUtils.eomsDB2Model(o, newObject);
					/***
					 * Remove this instance from the session cache. Changes to the instance 
					 * will not be synchronized with the database. This operation cascades to associated 
					 * instances if the association is mapped with cascade="evict"
					 * 以后可能会影响hibernate配置文件中的级联关系设置
					 */
					this.getSessionFactory().getCurrentSession().evict(o);
					returnList.add(newObject);
				}
			}else {
				returnList.add(o);
				continue;
			}
		}
		return returnList;
	}
}
