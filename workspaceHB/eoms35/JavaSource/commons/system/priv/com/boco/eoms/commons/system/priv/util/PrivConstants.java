package com.boco.eoms.commons.system.priv.util;

/**
 * 
 * <p>
 * Title:权限常量
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 20, 2008 5:19:21 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class PrivConstants {

	/**
	 * 管理员标识
	 */
	public final static String ADMIN_DEGREE = "1";

	/**
	 * 菜单隐藏
	 */
	public final static String MENU_HIDDEN = "1";

	/**
	 * 菜单不隐藏
	 */
	public final static String MENU_NO_HIDDEN = "0";

	/**
	 * 菜单有下级菜单
	 */
	public final static String OPERATION_APP_TYPE_MODULE = "1";

	/**
	 * 最下级菜单
	 */
	public final static String OPERATION_APP_TYPE_FUNCTION = "0";

	/**
	 * 不显示，仅为权限判断使用
	 */
	public final static String OPERATION_APP_TYPE_PRIV = "2";

	/**
	 * 查询所有菜单，如：MOUDLE,FUNCTION,BUTTON
	 */
	public final static String LIST_OPERATION_TYPE_ALL = "ALL";

	/**
	 * 仅查询MODULE,FUNCTION菜单
	 */
	public final static String LIST_OPERATION_TYPE_MOUDLE_FUNCTION = "MOUDLE_FUNCTION";
	
	/**
	 *  菜单登陆方式
	 *  eoms登陆
	 *  
	 */
	
	public final static String PRIV_OPERATION_EOMS_LOING = "0";
	/**
	 *  wap 登陆
	 */
	public final static String PRIV_OPERATION_WAP_LOING = "1";
}
