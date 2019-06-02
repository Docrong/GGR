package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.Attemper;

/**
 * <p>
 * Title:网调信息
 * </p>
 * <p>
 * Description:网调信息管理
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public interface AttemperDao extends Dao {

    /**
    *
    *取网调信息列表
    * @return 返回网调信息列表
    */
    public List getAttempers();
    
    /**
    * 根据主键查询网调信息
    * @param id 主键
    * @return 返回某id的网调信息
    */
    public Attemper getAttemper(final String id);
    
    /**
    *
    * 保存网调信息    
    * @param attemper 网调信息
    * 
    */
    public void saveAttemper(Attemper attemper);
    
    /**
    * 根据id删除网调信息
    * @param id 主键
    * 
    */
    public void removeAttemper(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getAttempers(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
    /**
     * 分页取列表
     * @param curPage 当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数,result(list) curPage页的记录
     */
     public Map getAttemperAndSubs(final Integer curPage, final Integer pageSize,
 			final String whereStr);
    
    /**
     * 获取网调记录中当天最后一条记录数据
     * @return String 故障记录编号
     */
     public String getSheetId(String sheetId);
	
}