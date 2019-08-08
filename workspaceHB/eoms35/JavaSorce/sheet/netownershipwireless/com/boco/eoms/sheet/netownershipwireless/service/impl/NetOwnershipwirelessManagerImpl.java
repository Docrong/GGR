package com.boco.eoms.sheet.netownershipwireless.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.netownershipwireless.dao.INetOwnershipwirelessDAO;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;
import com.boco.eoms.sheet.netownershipwireless.qo.INetOwnershipwirelessQo;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;

public class NetOwnershipwirelessManagerImpl extends BaseManager implements INetOwnershipwirelessManager {

    private NetOwnershipwireless netObject;
    private INetOwnershipwirelessDAO netDAO;
    private INetOwnershipwirelessQo netQo;


    public INetOwnershipwirelessQo getNetQo() {
        return netQo;
    }

    public void setNetQo(INetOwnershipwirelessQo netQo) {
        this.netQo = netQo;
    }

    public INetOwnershipwirelessDAO getNetDAO() {
        return netDAO;
    }

    public void setNetDAO(INetOwnershipwirelessDAO netDAO) {
        this.netDAO = netDAO;
    }

    public NetOwnershipwireless getNetObject() {
        return netObject;
    }

    public void setNetObject(NetOwnershipwireless netObject) {
        this.netObject = netObject;
    }

    /**
     * 删除一个对象
     */
    public void deleteObjectById(String id) throws Exception {
        netDAO.deleteObjectById(id);

    }

    /**
     * 根据关键字查询
     */
    public List getObjectByCondition(String condition) throws Exception {
        return netDAO.getObjectByCondition(condition);
    }

    /**
     * 根据id查询
     */
    public NetOwnershipwireless getObjectById(String id) throws Exception {
        return netDAO.getObjectById(id);
    }

    /**
     * 保存
     */
    public void saveOrUpdate(NetOwnershipwireless common) throws Exception {
        netDAO.saveOrUpdate(common);
    }

    public List getQueryResult(String[] hsql, Map actionForm, Integer curPage,
                               Integer pageSize, int[] aTotal, String queryType) {
        String sql = null;
        if (hsql != null)
            sql = hsql[0];
        if (sql == null || sql.equals("")) {
            hsql[0] = netQo.getClauseSql(actionForm, queryType);
            sql = hsql[0];
        }
        System.out.println(sql + "===fengmin add =====sql=");
        List result = netDAO.getQueryByCondition(sql, curPage, pageSize, aTotal, queryType);
        System.out.println(aTotal[0]);
        return result;
    }

    /**
     * 网元是否存在
     */
    public boolean ifNetExist(String netId, String netName) throws Exception {
        return netDAO.ifNetExist(netId, netName);
    }

    /**
     * 根据告警的condition上commonfaut_net_area表查询相关地域信息
     *
     * @param condition
     * @return
     * @throws Exception
     */
    public List getAreabycountyId(String condition) throws Exception {
        return netDAO.getAreabycountyId(condition);
    }

    /**
     * 根据告警的condition上commonfaut_tuifu_alarm表查询是否为退服告警
     *
     * @param condition
     * @return
     * @throws Exception
     */
    public List getTuifuAlarmBynetId(String condition) throws Exception {
        return netDAO.getTuifuAlarmBynetId(condition);
    }

    public List getNetType() throws Exception {
        return netDAO.getNetType();
    }

    public String getSubroleId(String cityid) throws Exception {
        return netDAO.getSubroleId(cityid);
    }

    public List ifNoneedAutotran(String neid) throws Exception {
        return netDAO.ifNoneedAutotran(neid);
    }

    public NetOwnershipwireless getNetOwnershipByNetId(String netId) throws Exception {
        return netDAO.getNetOwnershipByNetId(netId);
    }

    public String getPerformanceAlarm(String toDeptId, String mainAlarmId) throws Exception {
        return netDAO.getPerformanceAlarm(toDeptId, mainAlarmId);
    }

    public NetOwnershipwireless getNetOwnershipByNetName(String netName) throws Exception {
        return netDAO.getNetOwnershipByNetName(netName);
    }

    public String getSubroleIdByEquipmentName(String equipmentName) throws Exception {
        return netDAO.getSubroleIdByEquipmentName(equipmentName);
    }
}
