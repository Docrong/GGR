package com.boco.eoms.workbench.infopub.util;

/**
 * <p>
 * Title:信息发布常量
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 5:39:32 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class InfopubConstants {
	/**
	 * The request scope attribute that holds the forums form.
	 */
	public static final String FORUMS_KEY = "forumsForm";

	/**
	 * The request scope attribute that holds the forums list
	 */
	public static final String FORUMS_LIST = "forumsList";

	/**
	 * 信息发布首页显示条数
	 */
	public static final Integer INDEX_RECORD_COUNT = new Integer(20);

	/**
	 * true标识符
	 */
	public static final String TRUE_STR = "true";

	/**
	 * false标识符
	 */
	public static final String FALSE_STR = "false";

	// Thread-START
	/**
	 * The request scope attribute that holds the thread form.
	 */
	public static final String THREAD_KEY = "threadForm";

	/**
	 * The request scope attribute that holds the thread list
	 */
	public static final String THREAD_LIST = "threadList";

	// Thread-END
	// ThreadHistory-START
	/**
	 * The request scope attribute that holds the threadHistory form.
	 */
	public static final String THREADHISTORY_KEY = "threadHistoryForm";

	/**
	 * The request scope attribute that holds the threadHistory list
	 */
	public static final String THREADHISTORY_LIST = "threadHistoryList";

	public static final String THREADCOUNTHISTORY_LIST = "threadHistoryCountList";

	public static final String THREADCOMMENTSHISTORY_LIST = "threadHistoryCommentsList";
// ThreadHistory-END

	// ThreadPermimissionOrg-START
	/**
	 * The request scope attribute that holds the threadPermimissionOrg form.
	 */
	public static final String THREADPERMIMISSIONORG_KEY = "threadPermimissionOrgForm";

	/**
	 * The request scope attribute that holds the threadPermimissionOrg list
	 */
	public static final String THREADPERMIMISSIONORG_LIST = "threadPermimissionOrgList";

	// ThreadPermimissionOrg-END

	// ThreadPermission-START
	/**
	 * The request scope attribute that holds the threadPermission form.
	 */
	public static final String THREADPERMISSION_KEY = "threadPermissionForm";

	/**
	 * The request scope attribute that holds the threadPermission list
	 */
	public static final String THREADPERMISSION_LIST = "threadPermissionList";

	// ThreadPermission-END

	// ThreadAuditHistory-START
	/**
	 * The request scope attribute that holds the threadAuditHistory form.
	 */
	public static final String THREADAUDITHISTORY_KEY = "threadAuditHistoryForm";

	/**
	 * The request scope attribute that holds the threadAuditHistory list
	 */
	public static final String THREADAUDITHISTORY_LIST = "threadAuditHistoryList";

	// ThreadAuditHistory-END

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

	// 信息发布权限
	/**
	 * 无发布范围限制权限，若有此权限则可以发布其所在部门以外的部门信息
	 */
	public static final String WORKBENCH_INFOPUB_PERMISSION_PUB_ALL = "/WORKBENCH/INFOPUB/PERMISSION/PUBALL";

	/**
	 * 版块管理，新增、修改版块
	 */
	public static final String WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR = "/WORKBENCH/INFOPUB/PERMISSION/FORUMSMGR";

	/**
	 * 信息管理，删除，归类，查询
	 */
	public static final String WORKBENCH_INFOPUB_PERMISSION_THREAD_MGR = "/WORKBENCH/INFOPUB/PERMISSION/THREADMGR";

	/**
	 * 信息审核，若发布其所在部门以外的部门，需要提交审核人审核
	 */
	public static final String WORKBENCH_INFOPUB_PERMISSION_THREAD_AUDIT = "/WORKBENCH/INFOPUB/PERMISSION/THREADAUDIT";

	/**
	 * 不需要审核
	 */
	public static final String NO_AUDIT = "1";

	/**
	 * 超出发布范围（需要审核），但未提交审核，一般为草稿
	 */
	public static final String AUDIT_WITHOUT_SUBMIT = "2";

	/**
	 * 正在审核中
	 */
	public static final String ADUITING = "3";

	/**
	 * 通过审核,正式发布
	 */
	public static final String AUDIT_PASS = "4";

	/**
	 * 审核不通过
	 */
	public static final String AUDIT_NO_PASS = "5";

	/**
	 * 传阅
	 */
	public static final String ROTATION = "6";
    
	/**
	 * 查询全部
	 */
	public static final String QUERY_ALL = "-1";

	/**
	 * 专题类型，用于树选择
	 */
	public static final String NODETYPE_INFOPUB_FORUMS = "forums";

	/**
	 * 专题父节点id
	 */
	public static final String FORUMS_ROOT_NODE_ID = "-1";
	
	public static final String WAP_INFOPUB_FILE_APPCODE = "9";
}
