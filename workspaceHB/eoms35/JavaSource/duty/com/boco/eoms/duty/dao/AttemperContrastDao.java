package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.AttemperContrast;

/**
 * <p>
 * Title:网调对比表
 * </p>
 * <p>
 * Description:网调对比表
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public interface AttemperContrastDao extends Dao {

    /**
    *
    *取网调对比表列表
    * @return 返回网调对比表列表
    */
    public List getAttemperContrasts();
    
    /**
    * 根据主键查询网调对比表
    * @param id 主键
    * @return 返回某id的网调对比表
    */
    public AttemperContrast getAttemperContrast(final String id);
    
    /**
    *
    * 保存网调对比表    
    * @param attemperContrast 网调对比表
    * 
    */
    public void saveAttemperContrast(AttemperContrast attemperContrast);
    
    /**
    * 根据id删除网调对比表
    * @param id 主键
    * 
    */
    public void removeAttemperContrast(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getAttemperContrasts(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}