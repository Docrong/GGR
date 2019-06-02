package com.boco.eoms.sheet.offlineData.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.offlineData.model.OfflineDataInfoList;

public interface IOfflineDataInfoListDAO {
	public HashMap getListByHqlBy(final Integer curPage,final  Integer pageSize, final String hql,final String condictions, final String modelname) throws Exception;
	public void updateModelByHql(final String hql) throws Exception;
	/***
	 * 新增或者修改
	 * @param obj
	 * @throws Exception
	 */
	public void saveOrupdate(Object obj) throws Exception;
	/**
	 * 删除一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	public OfflineDataInfoList getBusinessupport(final String id) throws Exception;
	public HashMap getAllNumberApplyInfoidByMainid(final String mainid ,final  Integer pageSize, final Integer curPage)	throws HibernateException;
}
