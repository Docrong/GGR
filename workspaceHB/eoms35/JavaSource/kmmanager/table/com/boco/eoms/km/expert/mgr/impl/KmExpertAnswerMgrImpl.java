package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertAnswer;
import com.boco.eoms.km.expert.mgr.KmExpertAnswerMgr;
import com.boco.eoms.km.expert.dao.KmExpertAnswerDao;

/**
 * <p>
 * Title:知识库专家问答
 * </p>
 * <p>
 * Description:知识库专家问答
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 * 
 * @author daizhigang
 * @version 1.0
 * 
 */

public class KmExpertAnswerMgrImpl implements KmExpertAnswerMgr {

	private KmExpertAnswerDao kmExpertAnswerDao;

	public KmExpertAnswerDao getKmExpertAnswerDao() {
		return this.kmExpertAnswerDao;
	}

	public void setKmExpertAnswerDao(KmExpertAnswerDao kmExpertAnswerDao) {
		this.kmExpertAnswerDao = kmExpertAnswerDao;
	}

	

	/**
	 * 取所有问题记录列表
	 * 
	 * @return 返回所有问题记录列表
	 */
	public List getKmExpertAnswers(){
		return kmExpertAnswerDao.getKmExpertAnswers();
	}

	/**
	 * 分页取所有问题记录列表
	 * 
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswers(final Integer curPage, final Integer pageSize){
		return kmExpertAnswerDao.getKmExpertAnswers(curPage, pageSize);
	}

	/**
	 * 分页取所有问题记录列表
	 * 
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswers(final Integer curPage, final Integer pageSize, final String whereSql){
		return kmExpertAnswerDao.getKmExpertAnswers(curPage, pageSize, whereSql);
	}
	
	/**
	 * 根据主键查询问题记录
	 * 
	 * @param id 主键
	 * 
	 * @return 返回某id的问题记录
	 */
	public KmExpertAnswer getKmExpertAnswer(final String id){
		return kmExpertAnswerDao.getKmExpertAnswer(id);
	}

	/**
	 * 保存问题记录
	 * 
	 * @param kmExpertAnswer 问题记录
	 */
	public void saveKmExpertAnswer(KmExpertAnswer kmExpertAnswer){
		kmExpertAnswerDao.saveKmExpertAnswer(kmExpertAnswer);
	}

	/**
	 * 
	 * 根据主键删除问题记录
	 * 
	 * @param id 主键
	 * 
	 */
	public void removeKmExpertAnswer(final String id){
		kmExpertAnswerDao.removeKmExpertAnswer(id);
	}

	/**
	 * 根据用户ID查询该用户提出的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswerBySendUserId(final String userId){
		return kmExpertAnswerDao.getKmExpertAnswerBySendUserId(userId);
	}

	/**
	 * 根据用户ID分页查询该用户提出的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswerBySendUserId(final Integer curPage, final Integer pageSize, final String userId, final String state){
		return kmExpertAnswerDao.getKmExpertAnswerBySendUserId(curPage, pageSize, userId,state);
	}
	/**
	 * 根据用户ID查询该用户回答的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户回答的问题记录
	 */
	public Map getKmExpertAnswerByAnswerUserId(final String userId){
		return kmExpertAnswerDao.getKmExpertAnswerByAnswerUserId(userId);
	}
	/**
	 * 根据用户ID分页查询该用户回答的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户回答的问题记录
	 */
	public Map getKmExpertAnswerByAnswerUserId(final Integer curPage, final Integer pageSize, final String userId, final String state){
		return kmExpertAnswerDao.getKmExpertAnswerByAnswerUserId(curPage, pageSize, userId, state);
	}
}