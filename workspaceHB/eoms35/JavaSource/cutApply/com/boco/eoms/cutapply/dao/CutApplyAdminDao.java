package com.boco.eoms.cutapply.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.cutapply.model.CutApplyAdmin;

/**
 * <p>
 * Title:干线割接管理权限人员
 * </p>
 * <p>
 * Description:干线割接管理权限人员
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 * 
 * @author wangsixuan
 * @version 3.5
 * 
 */
public interface CutApplyAdminDao extends Dao {

    /**
    *
    *取干线割接管理列表
    * @return 返回干线割接管理列表
    */
    public List getCutApplyAdmins();
    
    /**
    * 根据主键查询干线割接管理
    * @param id 主键
    * @return 返回某id的干线割接管理
    */
    public CutApplyAdmin getCutApplyAdmin(final String id);
    
    /**
    *
    * 保存干线割接管理    
    * @param cutApply 干线割接管理
    * 
    */
    public void saveCutApplyAdmin(CutApplyAdmin cutApplyAdmin);
    
    /**
    * 根据id删除干线割接管理
    * @param id 主键
    * 
    */
    public void removeCutApplyAdmin(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getCutApplyAdmins(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
    /**
     * 根据条件查询
     * @param hql 查询SQL
     * @return 结果LIST
     */
    public List getCutApplyAdminsByCondition(final String hql);
	
}