package com.boco.eoms.sheet.netownership.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.netownership.dao.INetOwnershipDAO;
import com.boco.eoms.sheet.netownership.model.NetOwnership;
import com.boco.eoms.sheet.netownership.qo.INetOwnershipQo;
import com.boco.eoms.sheet.netownership.service.INetOwnershipManager;

public class NetOwnershipManagerImpl extends BaseManager implements INetOwnershipManager {

	private NetOwnership netObject;
	private INetOwnershipDAO netDAO;
	private INetOwnershipQo netQo;
	
	
	public INetOwnershipQo getNetQo() {
		return netQo;
	}
	public void setNetQo(INetOwnershipQo netQo) {
		this.netQo = netQo;
	}
	public INetOwnershipDAO getNetDAO() {
		return netDAO;
	}
	public void setNetDAO(INetOwnershipDAO netDAO) {
		this.netDAO = netDAO;
	}
	public NetOwnership getNetObject() {
		return netObject;
	}
	public void setNetObject(NetOwnership netObject) {
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
	public NetOwnership getObjectById(String id) throws Exception {
		return netDAO.getObjectById(id);
	}
	/**
	 * 保存
	 */
	public void saveOrUpdate(NetOwnership common) throws Exception {
		netDAO.saveOrUpdate(common);	
	}
	
	public List getQueryResult(String[] hsql, Map actionForm, Integer curPage,
			Integer pageSize, int[] aTotal, String queryType) {
		String sql = null;
		if (hsql != null)
			sql = hsql[0];
		if (sql == null || sql.equals("")) {
			hsql[0] = netQo.getClauseSql(actionForm,queryType);
			sql = hsql[0];
			System.out.println("-----------------导出SQLph------------------------------------"+sql);
		}
		List result = netDAO.getQueryByCondition(sql, curPage, pageSize,aTotal, queryType);
		System.out.println(aTotal[0]);
		return result;
	}
	/**
	 * 网元是否存在
	 */
	public boolean ifNetExist(String netId,String netName) throws Exception {
		return netDAO.ifNetExist(netId,netName);
	}
	  /**
     * 根据告警的condition上commonfaut_net_area表查询相关地域信息
     * @param condition
     * @return
     * @throws Exception
     */
	public List getAreabycountyId(String condition) throws Exception {
		return netDAO.getAreabycountyId(condition);
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
	
	public List getListByAlarmId(String tableName ,String neid) throws Exception {
		return netDAO.getListByAlarmId(tableName,neid);
	}
	
	public NetOwnership getNetOwnershipByNetId(String netId) throws Exception{
		return netDAO.getNetOwnershipByNetId(netId);
	}
	
	public String getPerformanceAlarm(String toDeptId,String mainAlarmId) throws Exception {
		return netDAO.getPerformanceAlarm(toDeptId,mainAlarmId);
	}
	
	public NetOwnership getNetOwnershipByNetName(String netName) throws Exception{
		return netDAO.getNetOwnershipByNetName(netName);
	}
}
