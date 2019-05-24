package com.boco.eoms.km.knowledge.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContents;

/**
 * <p> Title:知识中间表 DAO </p>
 * <p> Description:用于处理 KM_CONTENTS 表的 DAO 接口 </p>
 * <p> Tue Mar 24 10:32:12 CST 2009 </p>
 * 
 * @author ZHANGXB
 * @since 1.0
 */
public interface KmContentsDao extends Dao {

	/**
	 * 根据知识主键和知识评价更新知识中间表对应的知识记录的知识评价
	 * 
	 * @param contentId 知识主键
	 * @param grade 知识评价
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public void updateKmContentsGradeFlagByContentId(final String contentId, final int grade);
	
	/**
	 * 根据知识主键更新知识中间表对应的知识记录的阅读次数
	 * 
	 * @param contentId 知识主键
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public void updateKmContentsReadCountByContentId(final String contentId);
	
	/**
	 * 根据知识主键删知识中间表对应的知识记录
	 * 
	 * @param contentId 知识主键
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public void removeKmContentsByContentId(final String contentId);

    

    
	
	
	
	
	
	
	/**
	 *取知识管理列表
	 *
	 * @return 返回知识管理列表
	 */
	public List getKmContentss();

	/**
	 * 根据主键查询知识管理
	 * 
	 * @param id 主键
	 * @return 返回某id的知识管理
	 */
	public KmContents getKmContents(final String id);

	/**
	 * 保存知识管理
	 * 
	 * @param kmContents 知识管理
	 */
	public void saveKmContents(KmContents kmContents);

	/**
	 * 根据id删除知识管理
	 * 
	 * @param id 主键
	 */
	public void removeKmContents(final String id);

	/**
	 * 分页取列表
	 * 
	 * @param curPage 当前页
	 * @param pageSize 每页显示条数
	 * @param whereStr where条件
	 * @return map中total为条数,result(list) curPage页的记录
	 */
	public Map getKmContentss(final Integer curPage, final Integer pageSize,
			final String whereStr);

	/**
	 * 根据知识创建人查询知识信息 (KM_CONTENTS)
	 * 
	 * @param createUser
	 * @return
	 */
	public List getKmContentsList(final String createUser);

	/**
	 * 查询某模型下的所用知识
	 * 
	 * @param themeId
	 * @return
	 */
	public List getKmContentsListByThemeId(final String themeId);

	/**
	 * 查询作者处于某状态的知识列表
	 * 
	 * @param contentStatus 知识的状态
	 * @param operateUserId 知识的作者
	 * @return
	 */
	public List getKmContentsByContentStatusAndCreateUser(final String contentStatus, final String operateUserId);

	public List getKmContentsListLikeThemeId(final String themeId);

	/**
	 * 根据知识id查询 订阅中间表中的知识记录 KM_CONTNETS
	 * 
	 * @param contentId
	 * @return
	 */
	public KmContents getKmContentsByContentId(final String contentId);

	/**
	 * 根据知识的分类删除订阅中间表的知识记录 (KM_CONTENTS)
	 * 
	 * @param themeId 知识的分类
	 */
	public void removeKmContentsByThemeId(final String themeId);

	/**
	 * 根据知识主键更新订阅中间表的对应的知识状态 (KM_CONTENTS)
	 * 
	 * @param contentId 知识主键
	 * @param contentStatus 知识的状态
	 */
	public void updateKmContentsContentStatus(final String contentId, final String contentStatus);

	public Map complexQuery(String sql, Integer curPage, Integer pageSize);

	/**
	 * 回收站删除
	 * 
	 * @param themeId
	 */
	public void deleteKmContentsByThemeId(final String themeId);

	/**
	 * 清空回收站
	 */
	public void deleteKmContents();

	/**
	 * 还原所有项目
	 */
	public void revivifyAllKmContents();

	/**
	 * 还原选择的项目
	 */
	public void revivifyKmContents(final String themeId);
}