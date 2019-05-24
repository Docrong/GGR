package com.boco.eoms.km.statics.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.statics.model.KnowledgeChangesStatistic;

/**
 * <p>
 * Title:知识库知识变更情况统计表
 * </p>
 * <p>
 * Description:知识库知识变更情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public interface KnowledgeChangesStatisticDao extends Dao {

    /**
    *
    *取知识库知识变更情况统计表列表
    * @return 返回知识库知识变更情况统计表列表
    */
    public List getKnowledgeChangesStatistics();
    
    /**
    * 根据主键查询知识库知识变更情况统计表
    * @param id 主键
    * @return 返回某id的知识库知识变更情况统计表
    */
    public KnowledgeChangesStatistic getKnowledgeChangesStatistic(final String id);
    
    /**
    *
    * 保存知识库知识变更情况统计表    
    * @param knowledgeChangesStatistic 知识库知识变更情况统计表
    * 
    */
    public void saveKnowledgeChangesStatistic(KnowledgeChangesStatistic knowledgeChangesStatistic);
    
    /**
    * 根据id删除知识库知识变更情况统计表
    * @param id 主键
    * 
    */
    public void removeKnowledgeChangesStatistic(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKnowledgeChangesStatistics(final Integer curPage, final Integer pageSize);
	
}