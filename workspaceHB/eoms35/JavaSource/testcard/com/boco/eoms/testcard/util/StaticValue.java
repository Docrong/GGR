package com.boco.eoms.testcard.util;

public class StaticValue {
  
	public static final String TawCard_ZHENGCHANG = "正常";
	
	public static final String TawCard_TINGJI = "停机";
	
	public static final String TawCard_YISHI= "遗失";
	
	
	public static final String TawCard_SHIBAI = "SIM卡注册失败";
	
	
	public static final String TawCard_JIECHU = "借出";
	
	public static final String TawCard_SHIYONG = "使用";
	
	public static final String TawCard_WEISHENQING = "未申请";

	public static final String TawCard_DAISHENHE = "待审核";

	public static final String TawCard_YISHENHE = "已审核";

	public static final String TawCard_BAOFEI = "报废";
	
	public static final String TawCard_ALIVE = "已激活"; 

	public static final String TawCard_NOALIVE = "未激活"; 
	/**
	 * 申请单状态-未申请
	 */
	
	public final static String STATUS_UNREQUEST = "-1";
	/**
	 * 申请单状态-草稿
	 */
	public final static String STATUS_DRAFT = "9";

	/**
	 * 申请单状态-待审核
	 */
	public final static String STATUS_WAIT = "10";

	/**
	 * 申请单状态-驳回
	 */
	public final static String STATUS_REJECT = "11";

	/**
	 * 申请单状态-审核通过
	 */
	public final static String STATUS_PASS = "12";
	/**
	 * 测试卡状态-未激活
	 */
	public final static String STATUS_UNALIVE = "0";
	/**
	 * 测试卡状态-已激活
	 */
	public final static String STATUS_ALIVE = "1";
	/**
	 * 单据审核任务状态-未执行
	 */
	public final static int STATUS_EXECUTE = 0;
	/**
	 * 单据审核任务状态-已执行
	 */
	public final static int STATUS_EXECUTEED = 1;

	/**
	 * 角色类型
	 */
	public static final String ORG_ROLE = "role";

	/**
	 * 部门类型
	 */
	public static final String ORG_DEPT = "dept";

	/**
	 * 用户类型
	 */
	public static final String ORG_USER = "user";

}
