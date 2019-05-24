package com.boco.eoms.businessupport.interfaces.transfer.service;



import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;

public interface ITransferInterfaceManager {
	/**
	 * 根据传输调单号得到设计完成的电路信息，并更新到业务开通系统中
	 * @param condition   main字段对应的map
	 * @param orderSheetId   订单号
	 * @param mainSpecifyType 专业类别
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功是否的标识
	 * @throws SheetException
	 */
	public abstract String dealTraphInfosResult(Map map, String orderSheetId, String mainSpecifyType,String userName,String password)
	throws SheetException;
}
