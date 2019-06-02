/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:55:20
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class SheetAttributes {
    /**
     * 工单列表分页长度
     */
    private Integer pageSize;
    /**
     * 工单区域编码，不同的省份编码有所不同
     */
    private String regionId;
    /**
     * 工单最大张数，固定值
     */
    private String sheetMaxSize;
    /**
     * 工单内存数组的长度值
     */
    private String flowMaxLength;
    /**
     * 服务器编码，以此避免从不同外部接口（不同主机）传递过来的工单流水号重复
     */
    private String serviceNum;
    
    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
      
	/**
	 * @return Returns the regionId.
	 */
	public String getRegionId() {
		return regionId;
	}
	/**
	 * @param regionId The regionId to set.
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	/**
	 * @return Returns the sheetMaxSize.
	 */
	public String getSheetMaxSize() {
		return sheetMaxSize;
	}
	/**
	 * @param sheetMaxSize The sheetMaxSize to set.
	 */
	public void setSheetMaxSize(String sheetMaxSize) {
		this.sheetMaxSize = sheetMaxSize;
	}
	
	/**
	 * @return Returns the flowMaxLength.
	 */
	public String getFlowMaxLength() {
		return flowMaxLength;
	}
	/**
	 * @param flowMaxLength The flowMaxLength to set.
	 */
	public void setFlowMaxLength(String flowMaxLength) {
		this.flowMaxLength = flowMaxLength;
	}
	/**
	 * @return Returns the serviceNum.
	 */
	public String getServiceNum() {
		return serviceNum;
	}
	/**
	 * @param serviceNum The serviceNum to set.
	 */
	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum;
	}
}
