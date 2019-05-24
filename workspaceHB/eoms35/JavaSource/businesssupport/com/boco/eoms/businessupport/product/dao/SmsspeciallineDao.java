package com.boco.eoms.businessupport.product.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.businessupport.product.model.Smsspecialline;

/**
 * <p>
 * Title:短彩信
 * </p>
 * <p>
 * Description:彩信报工单
 * </p>
 * <p>
 * Wed Jun 02 19:47:25 CST 2010
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public interface SmsspeciallineDao extends Dao {

    /**
    *
    *取短彩信列表
    * @return 返回短彩信列表
    */
    public List getSmsspeciallines();
    
    /**
    * 根据主键查询短彩信
    * @param id 主键
    * @return 返回某id的短彩信
    */
    public Smsspecialline getSmsspecialline(final String id);
    
    /**
    *
    * 保存短彩信    
    * @param smsspecialline 短彩信
    * 
    */
    public void saveSmsspecialline(Smsspecialline smsspecialline);
    
    /**
    * 根据id删除短彩信
    * @param id 主键
    * 
    */
    public void removeSmsspecialline(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getSmsspeciallines(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}