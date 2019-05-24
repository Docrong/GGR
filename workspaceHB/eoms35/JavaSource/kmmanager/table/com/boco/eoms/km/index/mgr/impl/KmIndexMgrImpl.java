package com.boco.eoms.km.index.mgr.impl;

import java.util.Date;
import java.util.List;

import com.boco.eoms.km.index.dao.KmIndexDao;
import com.boco.eoms.km.index.mgr.KmIndexMgr;

/**
 * 知识管理首页信息处理 Mgr 实现类
 * 
 * @author ZHANGXB
 * @version 1.0
 * 
 */
public class KmIndexMgrImpl implements KmIndexMgr {
	
	/**
	 * 知识管理首页信息处理 Dao
	 * 该对象有 Spring 自动注入
	 */
	private KmIndexDao kmIndexDao;
	
	public KmIndexDao getKmIndexDao() {
		return kmIndexDao;
	}

	public void setKmIndexDao(KmIndexDao kmIndexDao) {
		this.kmIndexDao = kmIndexDao;
	}


	/**
	 * 根据专家ID查询专家头像
	 * 
	 * @param userId 专家登录ID
	 * @return 专家头像名称
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public String getExpertPhotoByUserId(final String userId){

		return kmIndexDao.getExpertPhotoByUserId(userId);
	}
	
	/**
	 * 根据专家ID查询专家总积分
	 * 
	 * @param userId 专家登录ID
	 * @return 专家总积分
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public Integer getExpertScoreByUserId(final String userId){

		return kmIndexDao.getExpertScoreByUserId(userId);
	}	

	/**
	 * 根据用户的登录ID查询用户的知识总积分
	 * 
	 * @param userId
	 * @return 用户的知识总积分
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public Integer getKnowledgeScoreByUserId(final String userId){
		
		return kmIndexDao.getKnowledgeScoreByUserId(userId);
	}

	/**
	 * 查询从某一日期到当前时间内的用户知识积分排行前几名
	 * 
	 * @param beginDate 开始时间，如果 beginDate=null 则不进行时间段限制
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 用户知识总积分排行
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listUsersKnowledgeScoreOrder(final Date beginDate, final int maxResults) {
		
		return kmIndexDao.listUsersKnowledgeScoreOrder(beginDate, maxResults);
	}

	/**
	 * 知识库有效知识量排行
	 * 
	 * @param beginDate 开始时间，如果 beginDate=null 则不进行时间段限制
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 知识库有效知识量排行
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listKnowledgeTableAmountOrder(Date beginDate, int maxResults) {

		return kmIndexDao.listKnowledgeTableAmountOrder(beginDate, maxResults);
	}

	/**
	 * 知识库知识阅读热度排行
	 * 
	 * @param beginDate 开始时间，如果 beginDate=null 则不进行时间段限制
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 按阅读次数倒序排列的有效知识
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listKnowledgeReadCountOrder(final Date beginDate, final int maxResults){
		
		return kmIndexDao.listKnowledgeReadCountOrder(beginDate, maxResults);
	}

	/**
	 * 读取知识列表
	 * 
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 知识列表
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listKmContents(int maxResults) {

		return kmIndexDao.listKmContents(maxResults);
	}

	/**
	 * 读取文件列表
	 * 
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 文件列表
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List listKmFiles(final int maxResults) {

		return kmIndexDao.listKmFiles(maxResults);
	}


	/**
	 * 读取当前时间内值班的专家信息
	 * 
	 * @param sysdate 当前时间
	 * @return 当前时间内值班的专家信息
	 * @author ZHANGXB
	 * @since 1.0
	 */	
	public List listOnDutyExperts(final String sysdate){

		return kmIndexDao.listOnDutyExperts(sysdate);
	}

	/**
	 * 根据知识分类父节点id查询下一级所有未删除的子节点
	 * 
	 * @param parentNodeId 父节点id
	 * @return 下级节点列表
	 * @author WANGZHIYONG
	 * @since 1.0
	 */
	public List listKmContentTree(final String parentNodeId) {
		return kmIndexDao.listKmContentTree(parentNodeId);
	}
	
	/**
	 * 根据文件分类父节点id查询下一级所有未删除的子节点
	 * 
	 * @param parentNodeId 父节点id
	 * @return 下级节点列表
	 * @author WANGZHIYONG
	 * @since 1.0
	 */
	public List listKmFileTree(final String parentNodeId) {
		return kmIndexDao.listKmFileTree(parentNodeId);
	}
	
	/**
	 * 读取课程列表
	 * 
	 * @param maxResults 最大行数，如果 maxResults>0 则返回总排行的前几名
	 * @return 知识列表
	 * @author WANGZHIYONG
	 * @since 1.0
	 */
	public List listKmLessonTree(final int maxResults) {
		return kmIndexDao.listKmLessonTree(maxResults);
	}

}
