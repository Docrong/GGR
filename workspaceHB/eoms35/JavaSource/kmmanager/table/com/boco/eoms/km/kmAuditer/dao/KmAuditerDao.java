package com.boco.eoms.km.kmAuditer.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;

/**
 * <p>
 * Title:知识管理审核人配置表
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public interface KmAuditerDao extends Dao {

    /**
    *
    *取知识管理审核人配置表列表
    * @return 返回知识管理审核人配置表列表
    */
    public List getKmAuditers();
    
    /**
    * 根据主键查询知识管理审核人配置表
    * @param id 主键
    * @return 返回某id的知识管理审核人配置表
    */
    public KmAuditer getKmAuditer(final String id);
    /**
     * 根据主键查询知识管理审核人配置表
     * @param themeId 知识分类
     * @return 返回某themeId的知识管理审核人配置表
     */
     public KmAuditer getKmAuditerByTheme(final String themeId);
 	/**
 	 * 根据文件分类审核人配置表
 	 * @param nodeId 主键
 	 * @return 返回KmAuditer对象
 	 */
      public KmAuditer getKmAuditerByNodeid(final String nodeId);
      
    /**
    *
    * 保存知识管理审核人配置表    
    * @param kmAuditer 知识管理审核人配置表
    * 
    */
    public void saveKmAuditer(KmAuditer kmAuditer);
    
    /**
    * 根据id删除知识管理审核人配置表
    * @param id 主键
    * 
    */
    public void removeKmAuditer(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmAuditers(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}