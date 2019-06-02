package com.boco.eoms.km.rule.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.rule.model.KmRule;

/**
 * <p>
 * Title:规则库
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public interface KmRuleDao extends Dao {

    /**
    *
    *取规则库列表
    * @return 返回规则库列表
    */
    public List getKmRules();
    
    /**
    * 根据主键查询规则库
    * @param id 主键
    * @return 返回某id的规则库
    */
    public KmRule getKmRule(final String id);
    
    /**
    *
    * 保存规则库    
    * @param kmRule 规则库
    * 
    */
    public void saveKmRule(KmRule kmRule);
    
    /**
    * 根据id删除规则库
    * @param id 主键
    * 
    */
    public void removeKmRule(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmRules(final Integer curPage, final Integer pageSize,
			final String whereStr);
    public List getNextLevelKmRules(String parentNodeId);
    public String checkSql(String sql);
	
}