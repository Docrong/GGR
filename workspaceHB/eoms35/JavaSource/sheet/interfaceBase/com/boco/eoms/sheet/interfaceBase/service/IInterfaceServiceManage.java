package com.boco.eoms.sheet.interfaceBase.service;

import java.util.HashMap;
import java.util.List;

public interface IInterfaceServiceManage {
	/**
	 * 派发
	 * @param interfaceMap 接口参数
	 * @param attach 附件信息
	 * @return
	 */
	public String newWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 重派
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String renewWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 阶段通知
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String suggestWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 退回
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String untreadWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 归档
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String checkinWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	
	public void confirmWorkSheet() throws Exception;
	public void notifyWorkSheet() throws Exception;
	public void replyWorkSheet() throws Exception;
	public void withdrawWorkSheet() throws Exception;
	public String cancelWorkSheet(HashMap interfaceMap) throws Exception;
}
