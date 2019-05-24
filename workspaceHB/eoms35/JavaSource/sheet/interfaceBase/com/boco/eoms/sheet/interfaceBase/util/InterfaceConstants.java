package com.boco.eoms.sheet.interfaceBase.util;

public class InterfaceConstants {
	public static final int SERVIVETYPE_RESOURCEAFFIRM = 31;//资源确认
	public static final int SERVIVETYPE_BUSINESSDREDGE = 32;//业务开通
	public static final int SERVIVETYPE_BUSINESSCHANGE = 33;//业务变更
	public static final int SERVIVETYPE_BUSINESSBACKOUT = 34;//业务拆除
	public static final int SERVIVETYPE_COMPLAINT = 56;		 //个人投诉
	public static final int SERVIVETYPE_GROUPCOMPLAINT = 57;//集团投诉
	
	public static final String WORKSHEET_HOLD = "checkinWorkSheet";			//归档
	public static final String WORKSHEET_RENEW = "renewWorkSheet";			//重派
	public static final String WORKSHEET_NEW = "newWorkSheet";				//派单
	public static final String WORKSHEET_SUGGEST = "suggestWorkSheet";		//阶段通知
	public static final String WORKSHEET_UNTREAD = "untreadWorkSheet";		//退回
	public static final String WORKSHEET_CANCEL = "cancelWorkSheet";		//取消
	public static final String WORKSHEET_NEWINSTANCE = "createNewSheetInstance";		//取消
	
	public static final String UN_SEND = "0" ; 	//接口数据待发送
	public static final String IS_SENDED = "1" ;	//接口数据已发送
	public static final String IS_UNREADY = "2" ;	//接口数据未准备好，不能发送
}
