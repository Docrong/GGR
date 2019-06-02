package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.FaultCircuit;

/**
 * <p>
 * Title:线路故障记录
 * </p>
 * <p>
 * Description:线路故障记录功能
 * </p>
 * <p>
 * Sun Mar 29 12:55:57 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public interface FaultCircuitDao extends Dao {

    /**
    *
    *取线路故障记录列表
    * @return 返回线路故障记录列表
    */
    public List getFaultCircuits();
    
    /**
    * 根据主键查询线路故障记录
    * @param id 主键
    * @return 返回某id的线路故障记录
    */
    public FaultCircuit getFaultCircuit(final String id);
    
    /**
    *
    * 保存线路故障记录    
    * @param faultCircuit 线路故障记录
    * 
    */
    public void saveFaultCircuit(FaultCircuit faultCircuit);
    
    /**
    * 根据id删除线路故障记录
    * @param id 主键
    * 
    */
    public void removeFaultCircuit(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getFaultCircuits(final Integer curPage, final Integer pageSize,
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
	
}