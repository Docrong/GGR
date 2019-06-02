package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.FaultEquipment;

/**
 * <p>
 * Title:设备故障记录
 * </p>
 * <p>
 * Description:设备故障记录
 * </p>
 * <p>
 * Sun Mar 29 09:02:44 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public interface FaultEquipmentDao extends Dao {

    /**
    *
    *取设备故障记录列表
    * @return 返回设备故障记录列表
    */
    public List getFaultEquipments();
    
    /**
    * 根据主键查询设备故障记录
    * @param id 主键
    * @return 返回某id的设备故障记录
    */
    public FaultEquipment getFaultEquipment(final String id);
    
    /**
    *
    * 保存设备故障记录    
    * @param faultEquipment 设备故障记录
    * 
    */
    public void saveFaultEquipment(FaultEquipment faultEquipment);
    
    /**
    * 根据id删除设备故障记录
    * @param id 主键
    * 
    */
    public void removeFaultEquipment(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getFaultEquipments(final Integer curPage, final Integer pageSize,
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