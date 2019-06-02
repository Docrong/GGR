package com.boco.eoms.km.statics.util;

import java.util.Calendar;

/**
 * <p>
 * Title:操作积分定义表
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateScoreConstants {
	
	/**
	 * list key
	 */
	public final static String KMOPERATESCORE_LIST = "kmOperateScoreList";
	
	/**
	 * 新增操作积分
	 */
	public final static Integer KMOPERATESCORE_ADD = new Integer(1);
	public final static String KMOPERATESCORE_ADD_NAME = "新增知识";
	public final static String KMOPERATESCORE_ADD_ID = "8aa082b32041ccd5012041ce612a0002";
	
	/**
	 * 修改操作分数
	 */
	public final static Integer KMOPERATESCORE_MODIFY = new Integer(2);
	public final static String KMOPERATESCORE_MODIFY_NAME = "修改知识";
	public final static String KMOPERATESCORE_MODIFY_ID = "8aa082b32041df57012041e070260002";
	
	/**
	 * 失效操作分数
	 */
	public final static Integer KMOPERATESCORE_OVER = new Integer(3);
	public final static String KMOPERATESCORE_OVER_NAME = "失效知识";
	public final static String KMOPERATESCORE_OVER_ID = "8aa082b32041df57012041e0f0b00003";
	
	/**
	 * 删除操作分数
	 */
	public final static Integer KMOPERATESCORE_DELETE = new Integer(4);
	public final static String KMOPERATESCORE_DELETE_NAME = "删除知识";
	public final static String KMOPERATESCORE_DELETE_ID = "8aa082b32041df57012041e138d50004";
	
	/**
	 * 上传文件分数
	 */
	public final static Integer KMOPERATESCORE_UP = new Integer(5);
	public final static String KMOPERATESCORE_UP_NAME = "上传文件";
	public final static String KMOPERATESCORE_UP_ID = "8aa082b32041df57012041e1883d0005";
	
	/**
	 * 下载文件分数
	 */
	public final static Integer KMOPERATESCORE_DOWN = new Integer(6);
	public final static String KMOPERATESCORE_DOWN_NAME = "下载文件";
	public final static String KMOPERATESCORE_DOWN_ID = "8aa082b32041df57012041e1c3cf0006";
	
	/**
	 * 引用知识分数
	 */
	public final static Integer KMOPERATESCORE_USE = new Integer(7);
	public final static String KMOPERATESCORE_USE_NAME = "引用知识";
	public final static String KMOPERATESCORE_USE_ID = "8aa082b32041df57012041e203490007";
	
	/**
	 * 评价知识分数
	 */
	public final static Integer KMOPERATESCORE_OPINION = new Integer(8);
	public final static String KMOPERATESCORE_OPINION_NAME = "评价知识";
	public final static String KMOPERATESCORE_OPINION_ID = "8aa082b32041df57012041e23d060008";
	
	/**
	 * 提出修改分数
	 */
	public final static Integer KMOPERATESCORE_ADVICE = new Integer(9);
	public final static String KMOPERATESCORE_ADVICE_NAME = "提出修改";
	public final static String KMOPERATESCORE_ADVICE_ID = "8aa082b32041df57012041e27a7d0009";
	
	/**
	 * 审核通过分数
	 */
	public final static Integer KMOPERATESCORE_AUDITOK = new Integer(10);
	public final static String KMOPERATESCORE_AUDITOK_NAME = "审核知识通过";
	public final static String KMOPERATESCORE_AUDITOK_ID = "8aa082b32041df57012041e2e3c6000a";
	
	/**
	 * 审核驳回分数
	 */
	public final static Integer KMOPERATESCORE_AUDITBACK = new Integer(11);
	public final static String KMOPERATESCORE_AUDITBACK_NAME = "审核知识驳回";
	public final static String KMOPERATESCORE_AUDITBACK_ID = "8aa082b32041df57012041e31b51000b";
	
	
	/**
	 * 取日期范围查询
	 * @param period: 日期范围 year--年；month--月；season--季度；week--周
	 * return : 查询起始日期
	 */
	
	public static String getStartDate(String period){
    	Calendar cal = Calendar.getInstance();
    	StringBuffer startDate = new StringBuffer();
    	if (period.equals("year")) startDate.append(cal.get(cal.YEAR)+"-01-01");
    	else if (period.equals("month")) startDate.append(""+cal.get(cal.YEAR)+"-"+(cal.get(cal.MONTH)+1)+"-01");
    	else if (period.equals("season")) {
    		switch (cal.get(cal.MONTH)/3){
    		case 0: {
    			startDate.append(""+cal.get(cal.YEAR)+"-01-01");
    			break;
    		}
    		case 1: {
    			startDate.append(""+cal.get(cal.YEAR)+"-04-01");
    			break;
    		}
    		case 2: {
    			startDate.append(""+cal.get(cal.YEAR)+"-07-01");
    		}
    		case 3: {
    			startDate.append(""+cal.get(cal.YEAR)+"-10-01");
    		}
    		}
    	}
    	else if (period.equals("week")) {
    		int day = (cal.get(cal.DAY_OF_MONTH)-(cal.get(cal.DAY_OF_WEEK)-1)+1);
    		if (day<0){
    			Calendar calLast = Calendar.getInstance();
    			calLast.add(calLast.MONTH, -1);
    			day = calLast.getActualMaximum(Calendar.DAY_OF_MONTH)+day;
    			startDate.append(""+cal.get(cal.YEAR)+"-"+(cal.get(cal.MONTH))+"-"+day);
    		}
    		else {
    			startDate.append(""+cal.get(cal.YEAR)+"-"+(cal.get(cal.MONTH)+1)+"-"+day);
    		}
    		
    	}
    	return startDate.toString();
	}
}