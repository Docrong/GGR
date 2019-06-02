package com.boco.eoms.km.expert.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.expert.model.KmExpertAnswer;

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

public interface KmExpertAnswerDao extends Dao {

	/**
	 * 取所有问题记录列表
	 * 
	 * @return 返回所有问题记录列表
	 */
	public List getKmExpertAnswers();

	/**
	 * 根据主键查询问题记录
	 * 
	 * @param id 主键
	 * 
	 * @return 返回某id的问题记录
	 */
	public KmExpertAnswer getKmExpertAnswer(final String id);

	/**
	 * 分页取所有问题记录列表
	 * 
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswers(final Integer curPage, final Integer pageSize);

	/**
	 * 分页取所有问题记录列表
	 * 
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswers(final Integer curPage, final Integer pageSize, final String whereSql);

	/**
	 * 保存问题记录
	 * 
	 * @param kmExpertAnswer 问题记录
	 */
	public void saveKmExpertAnswer(KmExpertAnswer kmExpertAnswer);

	/**
	 * 
	 * 根据主键删除问题记录
	 * 
	 * @param id 主键
	 * 
	 */
	public void removeKmExpertAnswer(final String id);

	/**
	 * 根据用户ID查询该用户提出的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswerBySendUserId(final String userId);

	/**
	 * 根据用户ID分页查询该用户提出的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户提出的问题记录
	 */
	public Map getKmExpertAnswerBySendUserId(final Integer curPage, final Integer pageSize, final String userId, final String state);

	/**
	 * 根据用户ID分页查询该用户回答的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户回答的问题记录
	 */
	public Map getKmExpertAnswerByAnswerUserId(final Integer curPage, final Integer pageSize, final String userId, final String state);

	/**
	 * 根据用户ID查询该用户回答的问题记录
	 * 
	 * @param userId 用户ID
	 * 
	 * @return 返回某用户回答的问题记录
	 */
	public Map getKmExpertAnswerByAnswerUserId(final String userId);

}