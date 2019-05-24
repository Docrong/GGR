package com.boco.eoms.sheet.proxy.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;

import com.boco.eoms.sheet.proxy.dao.IProxyDAO;
import com.boco.eoms.sheet.proxy.model.Proxy;

public class ProxyDaoHibernate extends BaseDaoHibernate implements IProxyDAO {
	/**
	 * 保存
	 * @param Proxy the object to be saved
	 */
	public void saveProxy(final Proxy proxy) {
		if ((proxy.getId() == null) || proxy.getId().equals("")) {
			getHibernateTemplate().save(proxy);
		} else {
			
			getHibernateTemplate().saveOrUpdate(proxy);
		}
	}

	/**
	 * 删除
	 * @param Proxy the object to be removed
	 */
	public void removeProxy(final Integer Id) {
		getHibernateTemplate().delete(getProxy(Id));
	}

	/**
	 * 获取
	 * 
	 */
	public Proxy getProxy(final Integer Id) {
		Proxy temproxy = (Proxy) getHibernateTemplate().get(Proxy.class, Id);
		if (temproxy == null) {
			throw new ObjectRetrievalFailureException(Proxy.class, Id);
		}
		return temproxy;
	}

	/**
	 * 查询
	 * @param Proxy the object to be saved
	 */
	public List getProxyList(final Proxy proxy) {
		return getHibernateTemplate().find("from Proxy");
	}
}
