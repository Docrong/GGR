package com.boco.eoms.commons.system.priv.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivRegionDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;

/**
 * panlong 2008 9:45:29 AM
 */
public class TawSystemPrivRegionDaoHibernate extends BaseDaoHibernate implements
		TawSystemPrivRegionDao {
	/**
	 * 查询所有的域
	 * 
	 * @param tawSytemPrivRegion
	 * @return
	 */
	public List getTawSytemPrivRegion(
			final TawSystemPrivRegion tawSytemPrivRegion) {

		return getHibernateTemplate().find("from TawSystemPrivRegion");
	}

	/**
	 * 查询某域
	 * 
	 * @param tawSytemPrivRegion
	 * @return
	 */
	public TawSystemPrivRegion getTawSytemPrivRegion(final Integer id) {

		TawSystemPrivRegion tawSytemPrivRegion = (TawSystemPrivRegion) getHibernateTemplate()
				.get(TawSystemPrivRegion.class, id);
		if (tawSytemPrivRegion == null) {
			throw new ObjectRetrievalFailureException(
					TawSystemPrivRegion.class, id);
		}

		return tawSytemPrivRegion;
	}

	/**
	 * 保存域
	 * 
	 * @param tawSytemPrivRegion
	 */
	public void saveTawSytemPrivRegion(TawSystemPrivRegion tawSytemPrivRegion) {
		Integer id = getTawSystemPrivRegion(tawSytemPrivRegion.getObjectid(),
				tawSytemPrivRegion.getObjecttype(),
				tawSytemPrivRegion.getRegionid(), tawSytemPrivRegion.getIsapp())
				.getId();

		if ((id == null) || (id.equals("")))
			getHibernateTemplate().save(tawSytemPrivRegion);
		else {
			tawSytemPrivRegion.setId(id);
			getHibernateTemplate().saveOrUpdate(tawSytemPrivRegion);
		}
	}

	/**
	 * 删除某域
	 * 
	 * @param id
	 */
	public void removeTawSytemPrivRegion(final Integer id) {
		getHibernateTemplate().delete(getTawSytemPrivRegion(id));
	}

	/**
	 * 查询某对象包含的所有域
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectRegion(String objectid) {
		String hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
				+ objectid + "'";
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 查询某对象 某种类型的域
	 * 
	 * @param objectid
	 * @param type
	 * @return
	 */
	public List getObjectRegionByType(String objectid, String objecttype,
			String type) {
		String hql = "";
		if (type.equals(StaticVariable.PRIV_TYPE_REGION_ALL)) {
			hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
					+ objectid
					+ "' and privregion.objecttype='"
					+ objecttype
					+ "'";
		} else {
			hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
					+ objectid
					+ "' and privregion.isapp='"
					+ type
					+ "' and privregion.objecttype='" + objecttype + "'";
		}

		return getHibernateTemplate().find(hql);
	}
	public List getObjectRegionByType(String objectid, String objecttype,String region,
			String type) {
		String hql = "";
		if (type.equals(StaticVariable.PRIV_TYPE_REGION_ALL)) {
			hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
					+ objectid
					+ "' and privregion.objecttype='"
					+ objecttype
					+ "' and privregion.regionid='"+region+"'";
		} else {
			hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
					+ objectid
					+ "' and privregion.isapp='"
					+ type
					+ "' and privregion.objecttype='" + objecttype + "' and privregion.regionid='"+region+"'";
		}

		return getHibernateTemplate().find(hql);
	}

	/**
	 * 根据URL查询某对象的部门域或者机房域 或者全部域
	 * 
	 * @param objectid
	 * @param url
	 * @param type
	 * @return
	 */
	public List getObjectRegionByUrl(String objectid, String url, String type) {
		String hql = "";
		if (type.equals(StaticVariable.PRIV_TYPE_REGION_ALL)) {
			hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
					+ objectid + "'";
		} else {
			hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
					+ objectid
					+ "' and privregion.isapp='"
					+ type
					+ "' and privregion.url='" + url + "'";
		}
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 判断某对象是否有某权限域
	 * 
	 * @param objectid
	 * @param objecttype
	 * @return
	 */
	public boolean hasObjectRegions(String objectid, String objecttype,
			String regionid, String regiontype) {

		boolean flag = false;
		String hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
				+ objectid
				+ "' and privregion.objecttype='"
				+ objecttype
				+ "' and privregion.regionid='"
				+ regionid
				+ "' and privregion.isapp='" + regiontype + "'";
		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询某记录
	 * 
	 * @param objectid
	 * @param objecttype
	 * @param regionid
	 * @param regiontype
	 * @return
	 */
	public TawSystemPrivRegion getTawSystemPrivRegion(String objectid,
			String objecttype, String regionid, String regiontype) {
		String hql = " from TawSystemPrivRegion privregion where privregion.objectid='"
				+ objectid
				+ "' and privregion.objecttype='"
				+ objecttype
				+ "' and privregion.regionid='"
				+ regionid
				+ "' and privregion.isapp='" + regiontype + "'";
		List list = getHibernateTemplate().find(hql);
		TawSystemPrivRegion region = new TawSystemPrivRegion();
		if (list != null && list.size() > 0) {
			region = (TawSystemPrivRegion) list.get(0);
		}
		return region;
	}
}
