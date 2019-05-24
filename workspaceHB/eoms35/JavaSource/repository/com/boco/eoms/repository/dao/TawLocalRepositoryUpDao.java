package com.boco.eoms.repository.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepositoryUp
 * </p>
 * <p>
 * Description:tawLocalRepositoryUp
 * </p>
 * <p>
 * Fri Oct 30 16:52:13 CST 2009
 * </p>
 * 
 * @author 李锋
 * @version 1.0
 * 
 */
public interface TawLocalRepositoryUpDao extends Dao {

    /**
    *
    *取tawLocalRepositoryUp列表
    * @return 返回tawLocalRepositoryUp列表
    */
    public List getTawLocalRepositoryUps();
    
    /**
    * 根据主键查询tawLocalRepositoryUp
    * @param id 主键
    * @return 返回某id的tawLocalRepositoryUp
    */
    public TawLocalRepositoryUp getTawLocalRepositoryUp(final String id);
    
    /**
    *
    * 保存tawLocalRepositoryUp    
    * @param tawLocalRepositoryUp tawLocalRepositoryUp
    * 
    */
    public void saveTawLocalRepositoryUp(TawLocalRepositoryUp tawLocalRepositoryUp);
    
    /**
    * 根据id删除tawLocalRepositoryUp
    * @param id 主键
    * 
    */
    public void removeTawLocalRepositoryUp(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTawLocalRepositoryUps(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}