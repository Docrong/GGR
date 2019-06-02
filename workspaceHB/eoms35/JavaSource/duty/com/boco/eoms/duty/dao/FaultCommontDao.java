package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.FaultCommont;

/**
 * <p>
 * Title:通用故障记录
 * </p>
 * <p>
 * Description:通用故障记录功能
 * </p>
 * <p>
 * Mon Mar 23 15:39:20 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public interface FaultCommontDao extends Dao {

    /**
    *
    *取通用故障记录列表
    * @return 返回通用故障记录列表
    */
    public List getFaultCommonts();
    
    /**
    * 根据主键查询通用故障记录
    * @param id 主键
    * @return 返回某id的通用故障记录
    */
    public FaultCommont getFaultCommont(final String id);
    
    /**
    *
    * 保存通用故障记录    
    * @param faultCommont 通用故障记录
    * 
    */
    public void saveFaultCommont(FaultCommont faultCommont);
    
    /**
    * 根据id删除通用故障记录
    * @param id 主键
    * 
    */
    public void removeFaultCommont(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getFaultCommonts(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
    /**
     * 获取故障记录中最后一条记录数据
     * @return List 故障记录编号
     */
     public List getFaultSequenceNo();
     
     /**
      * 获取故障记录数量
      * @return String 数量
      */
      public String getNum(String condition);
     
     /**
     *
     *取通用故障记录统计数据
     * @return 返回通用故障记录统计列表
     */
     public List getStatList(String condition);
}