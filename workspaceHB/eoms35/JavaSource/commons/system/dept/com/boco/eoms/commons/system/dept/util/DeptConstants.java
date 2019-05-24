package com.boco.eoms.commons.system.dept.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 29, 2008 10:21:23 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class DeptConstants {

	/**
	 * 表示全部查询
	 */
	public static final String NO_DELETED = "0";

	/**
	 * 表示查询停职或被删除的
	 */
	public static final String DELETED = "1";

	/**
	 * 父部门id
	 */
	public static final String ROOT_PARENTDEPT_ID = "-1";

	/**
	 * 无部门deptId
	 */
	public static final String NODEPT_DEPTID = "-1111";

	/**
	 * user
	 */
	public static final String USER = "user";

	/**
	 * 流程角色
	 */
	public static final String EMS_ROLE = "1";

	/**
	 * 系统角色
	 */
	public static final String SYSTEM_ROLE = "2";

	/**
	 * 虚拟组角色
	 */
	public static final String XUNI_ROLE = "3";

	// 部门ID按照一定规律子ID跟父ID的length之差为2
	public static final int DEPTID_BETWEEN_LENGTH = 2;

	// 如果某个部门ID下没有子节点时需要追加
	public static final String DEPTID_NOSON = "01";

	// 判断部门ID是否为设置的最大值
	public static final String DEPTID_IF_MAXID = "99";

	// 最大部门ID值
	public static final String DEPTID_MAXID = "-10";

	// LINKID按照一定规律子ID跟父ID的length之差为2
	public static final int LINKID_BETWEEN_LENGTH = 2;

	// 如果某个LINKID下没有子节点时需要追加
	public static final String LINKID_NOSON = "01";

	// 判断LINKID是否为设置的最大值
	public static final String LINKID_IF_MAXID = "99";

	// 最LINKID值
	public static final String LINKID_MAXID = "-10";

}
