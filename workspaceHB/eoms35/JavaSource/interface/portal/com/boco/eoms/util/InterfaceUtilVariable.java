/**
 * @see
 * <p>静态类，保存全局变量。</p>
 * @author 白强
 * @version 1.0
 */

package com.boco.eoms.util;

public interface InterfaceUtilVariable {
	public static final String BULLETION_THREADTYPE_URGENT = "紧急";
	public static final String BULLETION_REPLY_NAME = "是";
	public static final String BULLETION_CREATER_NAME = "管理员";// 公告接口的默认的发布人
	public static final String BULLETION_CREATER_ID = "admin";// 公告接口的默认的发布人ID
	public static final String BULLETION_DEPT_ID = "1";// 公告接口的发布范围的部门ID
	public static final String BULLETION_DEPT_NAME = "省公司";// 公告接口的发布范围的部门名称
	public static final String BULLETION_REPLYRESULT = "同意";
	public static final String BULLETION_FORUMS_ID = "8aa081631d747ecf011d74999b340005";// 公告的发布版块id
																						// 本ID为专题管理中版块id
	public static final String BulletinHttpPort_address = "http://localhost:8080/csp/services/si/bulletinService";
	public static final String BULLETION_SERSUPPLIER = "http://localhost:8080/csp/services/si/bulletinService";
	public static final String BULLETION_SERCALLER = "http://localhost:8080/csp/services/si/bulletinService";
}
