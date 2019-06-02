package com.boco.eoms.km.kmAuditerStep.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;

/**
 * <p>
 * Title:知识管理审核步骤
 * </p>
 * <p>
 * Description:知识管理审核步骤
 * </p>
 * <p>
 * Mon May 04 14:42:01 CST 2009
 * </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public interface KmAuditerStepDao extends Dao {

    /**
    *
    *取知识管理审核步骤列表
    * @return 返回知识管理审核步骤列表
    */
    public List getKmAuditerSteps();
    
    /**
    * 根据主键查询知识管理审核步骤
    * @param id 主键
    * @return 返回某id的知识管理审核步骤
    */
    public KmAuditerStep getKmAuditerStep(final String id);
    
    /**
    *
    * 保存知识管理审核步骤    
    * @param kmAuditerStep 知识管理审核步骤
    * 
    */
    public void saveKmAuditerStep(KmAuditerStep kmAuditerStep);
    
    /**
    * 根据id删除知识管理审核步骤
    * @param id 主键
    * 
    */
    public void removeKmAuditerStep(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmAuditerSteps(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
	
    /**
    *
    *根据条件取知识内容管理待审核列表
    * @return 返回满足条件知识内容管理待审核列表
    */
	public Map getContentUnAuditList(final Integer curPage, final Integer pageSize,
			final String userId, final List subRoleList);
	
    /**
    *
    *根据条件取知识附件管理待审核列表
    * @return 返回满足条件知识附件管理待审核列表
    */
	public Map getFileUnAuditList(final Integer curPage, final Integer pageSize,
			final String userId, final List subRoleList);
	
    /**
    *
    *根据条件取知识管理审核步骤列表
    * @return 返回满足条件知识管理审核步骤列表
    */
    public List getKmAuditerSteps(final String whereStr);
    
}