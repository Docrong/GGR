package com.boco.eoms.sheet.netownershipwireless.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;

public interface INetOwnershipwirelessManager extends Manager {
    /**
     * 保存一个映射的对象
     *
     * @param common
     * @throws Exception
     */
    public void saveOrUpdate(NetOwnershipwireless common) throws Exception;

    /**
     * 根据主键id,查询映射对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    public NetOwnershipwireless getObjectById(String id) throws Exception;

    /**
     * 自定义条件,查询符合条件的对象集合
     *
     * @param condition
     * @return
     * @throws Exception
     */
    public List getObjectByCondition(String condition) throws Exception;

    /**
     * 根据id删除一个对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    public void deleteObjectById(String id) throws Exception;

    /**
     * 查询
     *
     * @param hsql      可以为空, 保留目的:sql语句可能从cache中得到, 作为引用参数返回值.
     * @param actionMap 查询条件的Map,用来给QO赋值,得到sql语句
     * @param total     查询结果总数,作为引用参数返回当前查询的结果总数
     * @return 返回List结果
     * @throws SheetException
     */
    public List getQueryResult(String[] sql, Map actionMap, Integer integer, Integer integer2, int[] total, String queryType);

    /**
     * 网元是否存在
     *
     * @param netId 网元id
     * @return
     * @throws Exception
     */
    public boolean ifNetExist(String netId, String netName) throws Exception;

    /**
     * 根据告警的condition上commonfaut_net_area表查询相关地域信息
     *
     * @param countyId
     * @return
     * @throws Exception
     */
    public List getAreabycountyId(String condition) throws Exception;

    /**
     * 根据告警的condition上commonfaut_net_area表查询相关地域信息
     *
     * @param countyId
     * @return
     * @throws Exception
     */
    public List getTuifuAlarmBynetId(String condition) throws Exception;

    public abstract List getNetType() throws Exception;

    public abstract String getSubroleId(String cityid) throws Exception;

    public abstract List ifNoneedAutotran(String neid) throws Exception;

    public NetOwnershipwireless getNetOwnershipByNetId(String netId) throws Exception;

    public abstract String getPerformanceAlarm(String toDeptId, String mainAlarmId) throws Exception;

    public NetOwnershipwireless getNetOwnershipByNetName(String netName) throws Exception;

    public abstract String getSubroleIdByEquipmentName(String s)
            throws Exception;

}

