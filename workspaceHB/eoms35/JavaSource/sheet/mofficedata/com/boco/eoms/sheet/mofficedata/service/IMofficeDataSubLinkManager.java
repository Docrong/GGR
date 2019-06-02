package com.boco.eoms.sheet.mofficedata.service;

import java.util.List;

import com.boco.eoms.sheet.mofficedata.model.MofficeDataSubLink;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public interface IMofficeDataSubLinkManager {
	public void saveOrUpdate(MofficeDataSubLink obj) throws Exception;

	public MofficeDataSubLink getSubLinkObject()throws Exception;

	public void updateOthers(String liId, String preLinkId)throws Exception;
	/**
	 * 根据linkId获取同步的局数据的处理信息
	 * @param parentLinkId
	 * @return
	 * @throws Exception
	 * @date 2016-4-5下午02:03:57
	 * @author weichao
	 */
	public List getSubLinks(String parentLinkId)throws Exception;
}