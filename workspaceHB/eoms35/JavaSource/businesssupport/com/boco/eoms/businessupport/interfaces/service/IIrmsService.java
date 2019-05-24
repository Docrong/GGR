package com.boco.eoms.businessupport.interfaces.service;

public interface IIrmsService {
	/**
	 * 资源服务初始化接口：调用预占页面之前调用此接口
	 * @param opdetail
	 * @return
	 */
	public String createProService(String opdetail);
	/**
	 * 资源实占接口，归档时调用。返回实占的资源信息。
	 * @param opdetail
	 * @return
	 */
	public String occupyServiceRes(String opdetail);
	
	/**
	 * 资源取消
	 * @param opdetail
	 * @return
	 */
	public String undoServiceRes(String opdetail);
	
	/**
	 * 资源回滚
	 * @param opdetail
	 * @return
	 */
	public String undoTaskRes(String opdetail);
}
