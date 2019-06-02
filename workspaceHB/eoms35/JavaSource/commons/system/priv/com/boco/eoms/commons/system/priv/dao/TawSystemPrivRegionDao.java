package com.boco.eoms.commons.system.priv.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;

/**
 * panlong 2008 9:41:55 AM
 */
public interface TawSystemPrivRegionDao extends Dao {

	/**
	 * 查询所有的域
	 * 
	 * @param tawSytemPrivRegion
	 * @return
	 */
	public List getTawSytemPrivRegion(TawSystemPrivRegion tawSytemPrivRegion);

	/**
	 * 查询某域
	 * 
	 * @param tawSytemPrivRegion
	 * @return
	 */
	public TawSystemPrivRegion getTawSytemPrivRegion(final Integer id);

	/**
	 * 保存域
	 * 
	 * @param tawSytemPrivRegion
	 */
	public void saveTawSytemPrivRegion(TawSystemPrivRegion tawSytemPrivRegion);

	/**
	 * 删除某域
	 * 
	 * @param id
	 */
	public void removeTawSytemPrivRegion(final Integer id);

	/**
	 * 查询某对象包含的所有域
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectRegion(String objectid);

	/**
	 * 查询某对象 某种类型的域
	 * 
	 * @param objectid
	 * @param type
	 * @return
	 */
	public List getObjectRegionByType(String objectid, String objecttype,
			String type);
	public List getObjectRegionByType(String objectid, String objecttype,String region,
			String type);
	
	/**
	 * 根据URL查询某对象的部门域或者机房域 或者全部域
	 * 
	 * @param objectid
	 * @param url
	 * @param type
	 * @return
	 */
	public List getObjectRegionByUrl(String objectid, String url, String type);

}
