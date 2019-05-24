package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertBasic;
import com.boco.eoms.km.expert.mgr.KmExpertBasicMgr;
import com.boco.eoms.km.expert.dao.KmExpertBasicDao;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertBasicMgrImpl implements KmExpertBasicMgr {

	private KmExpertBasicDao kmExpertBasicDao;

	public KmExpertBasicDao getKmExpertBasicDao() {
		return this.kmExpertBasicDao;
	}

	public void setKmExpertBasicDao(KmExpertBasicDao kmExpertBasicDao) {
		this.kmExpertBasicDao = kmExpertBasicDao;
	}

	/**
	 * 取基本信息 列表
	 * 
	 * @return 返回基本信息列表
	 */
	public List getKmExpertBasics() {
		return kmExpertBasicDao.getKmExpertBasics();
	}

	/**
	 * 根据主键查询模型字段定义表
	 * 
	 * @param id
	 *            主键
	 * @return 返回某id的模型字段定义表
	 */
	public KmExpertBasic getKmExpertBasic(final String id) {
		return kmExpertBasicDao.getKmExpertBasic(id);
	}

	/**
	 * 保存基本信息
	 * 
	 * @param kmExpertBasic
	 *            基本信息
	 */
	public void saveKmExpertBasic(KmExpertBasic kmExpertBasic) {
		kmExpertBasicDao.saveKmExpertBasic(kmExpertBasic);
	}

	/**
	 * 根据主键删除基本信息
	 * 
	 * @param id
	 *            主键
	 */
	public void removeKmExpertBasic(final String id, final String userId) {
		kmExpertBasicDao.removeKmExpertBasic(id, userId);
	}

	/**
	 * 根据条件分页查询基本信息
	 * 
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            每页包含记录条数
	 * @param whereStr
	 *            查询条件
	 * @return 返回基本信息的分页列表
	 */
	public Map getKmExpertBasics(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExpertBasicDao.getKmExpertBasics(curPage, pageSize, whereStr);
	}

	/**
	 * 根据用户ID查询基本信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回某用户的基本信息
	 */
	public KmExpertBasic getKmExpertBasicByUserId(final String userId) {
		return kmExpertBasicDao.getKmExpertBasicByUserId(userId);
	}

	public Map getKmExpertBasics(final Integer curPage, final Integer pageSize,
			final KmExpertBasic kmExpertBasic) {
		return kmExpertBasicDao.getKmExpertBasics(curPage, pageSize,
				kmExpertBasic);
	}
}