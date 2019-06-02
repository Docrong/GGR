package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.dao.KmContentsCollectDao;
import com.boco.eoms.km.knowledge.mgr.KmContentsCollectMgr;
import com.boco.eoms.km.knowledge.model.KmContentsCollect;

public class KmContentsCollectMgrImpl implements KmContentsCollectMgr {
	
	private KmContentsCollectDao kmContentsCollectDao;
	
	public void removeKmContentsCollect(String contentId) {
		kmContentsCollectDao.removeKmContentsCollect(contentId);
	}

	/**
	 * 查询当前人的所收藏的知识集合
	 * @param subscribeUser
	 * @return
	 */
	public List getKmContentsCollectList(final String collectUser){
		return kmContentsCollectDao.getKmContentsCollectList(collectUser);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public KmContentsCollect getKmContentsCollect(final String contentId){
		return kmContentsCollectDao.getKmContentsCollect(contentId);
	}
	
	public KmContentsCollect getKmContentsCollect(String collectUser,
			String contentId) {
		
		return null;
	}

	/**
	 * 分页查询收藏信息
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map getKmContentsCollect(final Integer curPage, final Integer pageSize,
			final String whereStr){
		return kmContentsCollectDao.getKmContentsCollect(curPage, pageSize, whereStr);
	}

	/**
	 * 保存知识收藏信息
	 */
	public void savaKmContentsCollect(KmContentsCollect kmContentsCollect) {
		kmContentsCollectDao.savaKmContentsCollect(kmContentsCollect);
	}

	public KmContentsCollectDao getKmContentsCollectDao() {
		return kmContentsCollectDao;
	}

	public void setKmContentsCollectDao(KmContentsCollectDao kmContentsCollectDao) {
		this.kmContentsCollectDao = kmContentsCollectDao;
	}

}
