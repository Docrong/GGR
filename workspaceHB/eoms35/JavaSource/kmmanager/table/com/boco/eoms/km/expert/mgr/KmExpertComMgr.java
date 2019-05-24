package com.boco.eoms.km.expert.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertCom;

/**
 * <p>
 * Title:技术交流竞赛表彰
 * </p>
 * <p>
 * Description:技术交流竞赛表彰
 * </p>
 * <p>
 * Mon Jun 15 18:07:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
 public interface KmExpertComMgr {
 
	/**
	 *
	 * 取技术交流竞赛表彰 列表
	 * @return 返回技术交流竞赛表彰列表
	 */
	public List getKmExpertComs();
    
	/**
	 * 根据主键查询技术交流竞赛表彰
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExpertCom getKmExpertCom(final String id);
    
	/**
	 * 保存技术交流竞赛表彰
	 * @param kmExpertCom 技术交流竞赛表彰
	 */
	public void saveKmExpertCom(KmExpertCom kmExpertCom);
    
	/**
	 * 根据主键删除技术交流竞赛表彰
	 * @param id 主键
	 */
	public void removeKmExpertCom(final String id);
    
    /**
     * 根据id批量删除技术交流竞赛表彰
     * @param id 主键
     * 
     */
     public void removeKmExpertComs(final String[] ids);
     
	/**
	 * 根据条件分页查询技术交流竞赛表彰
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回技术交流竞赛表彰的分页列表
	 */
	public Map getKmExpertComs(final Integer curPage, final Integer pageSize,
			final String whereStr);

	public Map getKmExpertComsByUserId(final Integer curPage, final Integer pageSize,
			final String userId);
}