package com.boco.eoms.duty.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;

public interface ITawRmWorkOrderRecordJdbc extends Dao {
	
	/**
	 * 得到未处理任务工单
	 * @param userId
	 * @return List
	 */
	public List getUndoWorkOrderList(String userId,String roleIdList);
	
	/**
	 * 得到已处理任务工单
	 * @param userId
	 * @return List
	 */
	public List getFinishWorkOrderList(String userId,String userName);

	/** 
     * 通过prelinkId得到userId
	 * @param preLinkId
     */
	public String getUserIdByPrelinkId (String preLinkId);
	
	/** 
   * 通过prelinkId得到userId
 * @param preLinkId
   */
	public String getUserIdByMainId (String mainId);
}
