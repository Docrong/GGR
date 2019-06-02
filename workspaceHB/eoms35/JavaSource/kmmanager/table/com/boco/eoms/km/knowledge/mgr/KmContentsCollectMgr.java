package com.boco.eoms.km.knowledge.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsCollect;

public interface KmContentsCollectMgr {
	
	/**
	 * 查询当前人的所收藏的知识集合
	 * @param subscribeUser
	 * @return
	 */
	public List getKmContentsCollectList(final String collectUser);
	
	/**
	 * 分页查询收藏信息
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map getKmContentsCollect(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	/**
	 * 根据订阅人 知识id 去查询知识收藏表中的唯一记录
	 * @param collectUser
	 * @param contentId
	 * @return
	 */
	public KmContentsCollect getKmContentsCollect(final String collectUser,final String contentId); 
	
	/**
	 * 根据知识id查询
	 * @param id
	 * @return
	 */
	public KmContentsCollect getKmContentsCollect(final String contentId);
	
	/**
	 * 根据id删除收藏记录
	 * @param id
	 */
	public void removeKmContentsCollect(final String contentId);
	
	/**
	 * 添加收藏
	 * @param kmContentsCollect
	 */
	public void savaKmContentsCollect(final KmContentsCollect kmContentsCollect);
}
