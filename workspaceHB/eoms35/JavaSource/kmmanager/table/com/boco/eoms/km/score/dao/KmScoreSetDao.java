package com.boco.eoms.km.score.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.score.model.KmScoreSet;

/**
 * <p>
 * Title:积分设定表
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public interface KmScoreSetDao extends Dao {

    /**
    *
    *取积分设定表列表
    * @return 返回积分设定表列表
    */
    public List getKmScoreSets();
    
    /**
    * 根据主键查询积分设定表
    * @param id 主键
    * @return 返回某id的积分设定表
    */
    public KmScoreSet getKmScoreSet(final String id);
    
    /**
    *
    * 保存积分设定表    
    * @param kmScoreSet 积分设定表
    * 
    */
    public void saveKmScoreSet(KmScoreSet kmScoreSet);
    
    /**
    * 根据id删除积分设定表
    * @param id 主键
    * 
    */
    public void removeKmScoreSet(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmScoreSets(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
    public void removeKmScoreSetByPowerName(final String powerName) ;
    
    public void removeKmScoreSetId(final String id);
}