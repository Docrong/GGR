package com.boco.eoms.km.knowledge.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.knowledge.model.KmContentsApply;

/**
 * <p>
 * Title:知识申请
 * </p>
 * <p>
 * Description:知识申请
 * </p>
 * <p>
 * Tue Jul 14 10:27:17 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public interface KmContentsApplyDao extends Dao {

    /**
    *
    *取知识申请列表
    * @return 返回知识申请列表
    */
    public List getKmContentsApplys();
    
    /**
    * 根据主键查询知识申请
    * @param id 主键
    * @return 返回某id的知识申请
    */
    public KmContentsApply getKmContentsApply(final String id);
    
    /**
    *
    * 保存知识申请    
    * @param kmContentsApply 知识申请
    * 
    */
    public void saveKmContentsApply(KmContentsApply kmContentsApply);
    
    /**
    * 根据id删除知识申请
    * @param id 主键
    * 
    */
    public void removeKmContentsApply(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
			final String applyTheme,final String startDate,final String endDate, final String orderStr);
    
    /**
     * 分页取列表
     * @param curPage 当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数,result(list) curPage页的记录
     */
     public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
 			final String whereStr, final String orderStr);
    
    /**
     * 取排行列表
     * @param curPage 当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数,result(list) curPage页的记录
     */
     public Map getKmContentsApplyRanks(final String startDate, final String endDate,
    		 final String userId, final String deptId, final String themeId, final String maxSize);
	
}