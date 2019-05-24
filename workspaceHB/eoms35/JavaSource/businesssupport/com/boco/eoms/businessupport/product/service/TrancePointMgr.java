package com.boco.eoms.businessupport.product.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.product.model.TrancePoint;

/**
 * <p>
 * Title:业务接入点
 * </p>
 * <p>
 * Description:业务接入点
 * </p>
 * <p>
 * Sun May 16 14:18:55 CST 2010
 * </p>
 * 
 * @author lizhigang
 * @version 3.5
 * 
 */
 public interface TrancePointMgr {
 
	/**
	 *
	 * 取业务接入点 列表
	 * @return 返回业务接入点列表
	 */
	public List getBusinessupports();
    
	/**
	 * 根据主键查询业务接入点
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TrancePoint getBusinessupport(final String id);
    
	/**
	 * 保存业务接入点
	 * @param businessupport 业务接入点
	 */
	public void saveBusinessupport(TrancePoint businessupport);
    
	/**
	 * 根据主键删除业务接入点
	 * @param id 主键
	 */
	public void removeBusinessupport(final String id);
    
	/**
	 * 根据条件分页查询业务接入点
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回业务接入点的分页列表
	 */
	public Map getBusinessupports(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	public List getBusinessupportBySheetId(final String orderSheetId);
	/**
	 * 根据定单号和端点地址获取端点信息
	 * @param orderSheetId
	 * @param portDetailAdd
	 * @return
	 */
	public TrancePoint getTrancePointBySheetAndId(final String orderSheetId,final String portDetailAdd);
	/**
	 * 删除定单所有接入点信息
	 * @param orderId
	 * @throws Exception
	 */
	public void removeByOrderId(String orderId) throws Exception;
			
}