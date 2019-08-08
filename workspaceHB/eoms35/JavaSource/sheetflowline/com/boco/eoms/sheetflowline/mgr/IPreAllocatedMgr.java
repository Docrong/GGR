package com.boco.eoms.sheetflowline.mgr;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheetflowline.model.PreAllocated;


public interface IPreAllocatedMgr {

    /**
     * 保存字段
     *
     * @author liendan
     */
    public void saveObject(PreAllocated object) throws Exception;

    /**
     * 物理删除
     *
     * @param object
     */
    public void deleteObject(PreAllocated object) throws Exception;

    /**
     * 更新对象
     *
     * @param object
     * @throws HibernateException
     */
    public void updateObject(PreAllocated object) throws Exception;

    /**
     * 获取所有预分配集合
     *
     * @return
     * @throws HibernateException
     */
    public Map listPreAllocated(Integer startIndex, Integer length) throws Exception;

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @throws HibernateException
     */
    public PreAllocated getPreAllocated(String id) throws Exception;

    public Integer executeHsql(String hsql) throws Exception;

    /**
     * 条件查询
     *
     * @param object
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Map listPreAllocated(Map object, Integer pageIndex, Integer pageSize) throws Exception;

    public List search(String mainNetSortOne, String mainNetSortTwo, String mainNetSortThree, String mainEquipmentFactory, String mainFaultResponseLevel, String currentTime) throws Exception;

    public List getLists(String hsql) throws Exception;

}
