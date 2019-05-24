package com.boco.eoms.base;

/**
 * Constant values used throughout the application.
 * 
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {
	// ~ Static fields/initializers
	// =============================================

	public static final String logBaseModelCode = "10";

	/** The name of the ResourceBundle used in this application */
	public static final String BUNDLE_KEY = "config/ApplicationResources";

	/** The encryption algorithm key to be used for passwords */
	public static final String ENC_ALGORITHM = "algorithm";

	/** A flag to indicate if passwords should be encrypted */
	public static final String ENCRYPT_PASSWORD = "encryptPassword";

	/** File separator from System properties */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/** User home from System properties */
	public static final String USER_HOME = System.getProperty("user.home")
			+ FILE_SEP;

	/** The name of the configuration hashmap stored in application scope. */
	public static final String CONFIG = "appConfig";

	/**
	 * Session scope attribute that holds the locale set by the user. By setting
	 * this key to the same one that Struts uses, we get synchronization in
	 * Struts w/o having to do extra work or have two session-level variables.
	 */
	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts.action.LOCALE";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_KEY = "userForm";

	/**
	 * The request scope attribute that holds the user list
	 */
	public static final String USER_LIST = "userList";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "admin";

	/**
	 * 超级管理�??
	 */
	public static final String ADMINISTRATOR = "admin";

	/**
	 * The name of the User role, as specified in web.xml
	 */
	public static final String USER_ROLE = "user";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * The name of the available roles list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String AVAILABLE_ROLES = "availableRoles";

	// TawPrefixTable-START
	/**
	 * The request scope attribute that holds the tawPrefixTable form.
	 */
	public static final String TAWPREFIXTABLE_KEY = "tawPrefixTableForm";

	/**
	 * The request scope attribute that holds the tawPrefixTable list
	 */
	public static final String TAWPREFIXTABLE_LIST = "tawPrefixTableList";

	// TawPrefixTable-END

	// TawCommonLogOperator-START
	/**
	 * The request scope attribute that holds the tawCommonLogOperator form.
	 */
	public static final String TAWCOMMONLOGOPERATOR_KEY = "tawCommonLogOperatorForm";

	/**
	 * The request scope attribute that holds the tawCommonLogOperator list
	 */
	public static final String TAWCOMMONLOGOPERATOR_LIST = "tawCommonLogOperatorList";

	// TawCommonLogOperator-END

	// TawCommonLogDeploy-START

	// TawCommonMessageServiceType-START
	/**
	 * The request scope attribute that holds the tawCommonMessageServiceType
	 * form.
	 */
	public static final String TAWCOMMONMESSAGESERVICETYPE_KEY = "tawCommonMessageServiceTypeForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageServiceType
	 * list
	 */
	public static final String TAWCOMMONMESSAGESERVICETYPE_LIST = "tawCommonMessageServiceTypeList";

	// TawCommonMessageServiceType-END

	// TawCommonMessageOpertype-START
	/**
	 * The request scope attribute that holds the tawCommonMessageOpertype form.
	 */
	public static final String TAWCOMMONMESSAGEOPERTYPE_KEY = "tawCommonMessageOpertypeForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageOpertype list
	 */
	public static final String TAWCOMMONMESSAGEOPERTYPE_LIST = "tawCommonMessageOpertypeList";

	// TawCommonMessageOpertype-END

	// TawCommonMessageModelType-START
	/**
	 * The request scope attribute that holds the tawCommonMessageModelType
	 * form.
	 */
	public static final String TAWCOMMONMESSAGEMODELTYPE_KEY = "tawCommonMessageModelTypeForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageModelType list
	 */
	public static final String TAWCOMMONMESSAGEMODELTYPE_LIST = "tawCommonMessageModelTypeList";

	// TawCommonMessageModelType-END

	// TawCommonMessageAddService-START
	/**
	 * The request scope attribute that holds the tawCommonMessageAddService
	 * form.
	 */
	public static final String TAWCOMMONMESSAGEADDSERVICE_KEY = "tawCommonMessageAddServiceForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageAddService
	 * list
	 */
	public static final String TAWCOMMONMESSAGEADDSERVICE_LIST = "tawCommonMessageAddServiceList";

	// TawCommonMessageAddService-END

	// TawCommonMessageSubscribe-START
	/**
	 * The request scope attribute that holds the tawCommonMessageSubscribe
	 * form.
	 */
	public static final String TAWCOMMONMESSAGESUBSCRIBE_KEY = "tawCommonMessageSubscribeForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageSubscribe list
	 */
	public static final String TAWCOMMONMESSAGESUBSCRIBE_LIST = "tawCommonMessageSubscribeList";

	// TawCommonMessageSubscribe-END

	// TawCommonMessageMonitor-START
	/**
	 * The request scope attribute that holds the tawCommonMessageMonitor form.
	 */
	public static final String TAWCOMMONMESSAGEMONITOR_KEY = "tawCommonMessageMonitorForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageMonitor list
	 */
	public static final String TAWCOMMONMESSAGEMONITOR_LIST = "tawCommonMessageMonitorList";

	// TawCommonMessageMonitor-END

	// TawCommonMessageMonitorRef-START
	/**
	 * The request scope attribute that holds the tawCommonMessageMonitorRef
	 * form.
	 */
	public static final String TAWCOMMONMESSAGEMONITORREF_KEY = "tawCommonMessageMonitorRefForm";

	/**
	 * The request scope attribute that holds the tawCommonMessageMonitorRef
	 * list
	 */
	public static final String TAWCOMMONMESSAGEMONITORREF_LIST = "tawCommonMessageMonitorRefList";

	// TawCommonMessageMonitorRef-END

	/**
	 * The request scope attribute that holds the tawCommonLogDeploy form.
	 */
	public static final String TAWCOMMONLOGDEPLOY_KEY = "tawCommonLogDeployForm";

	/**
	 * The request scope attribute that holds the tawCommonLogDeploy list
	 */
	public static final String TAWCOMMONLOGDEPLOY_LIST = "tawCommonLogDeployList";

	// TawCommonLogDeploy-END

	public static final String TAWCOMMONSACCESSORIES_LIST = "tawCommonsAccessoriesList";

	public static final String TAWCOMMONSACCESSORIESCONFIG_LIST = "tawCommonsAccessoriesConfigList";

	public static final String TAWCOMMONSJOBCONFIG_LIST = "tawCommonsJobConfigList";

	public static final String TAWCOMMONSJOBSORT_LIST = "tawCommonsJobSortList";

	public static final String TAWCOMMONSJOBMONITOR_LIST = "tawCommonsJobMonitorList";

	public static final String TAWCOMMONSJOBSUBSCIBE_LIST = "tawCommonsJobSubscibeList";

	public static final String TAWCOMMONSACCESSORIESCONFIG_KEY = "tawCommonsAccessoriesconfigForm";

	public static final String TAWCOMMONSACCESSORIES_KEY = "tawCommonsAccessoriesForm";

	public static final String TAWCOMMONSJOBCONFIG_KEY = "tawComonsJobConfigForm";

	public static final String TAWCOMMONSJOBMONITOR_KEY = "tawComonsJobMoniotrForm";

	public static final String TAWCOMMONSJOBSORT_KEY = "tawComonsJobSortForm";

	public static final String TAWCOMMONSJOBSUBSCIBE_KEY = "tawComonsJobSubscibeForm";

	// TawSystemUserRefRole-START
	/**
	 * The request scope attribute that holds the tawSystemUserRefRole form.
	 */
	public static final String TAWSYSTEMUSERREFROLE_KEY = "tawSystemUserRefRoleForm";

	/**
	 * The request scope attribute that holds the tawSystemUserRefRole list
	 */
	public static final String TAWSYSTEMUSERREFROLE_LIST = "tawSystemUserRefRoleList";

	// TawSystemUserRefRole-END

	// TawSystemUser-START
	/**
	 * The request scope attribute that holds the tawSystemUser form.
	 */
	public static final String TAWSYSTEMUSER_KEY = "tawSystemUserForm";

	/**
	 * The request scope attribute that holds the tawSystemUser list
	 */
	public static final String TAWSYSTEMUSER_LIST = "tawSystemUserList";

	// TawSystemUser-END

	// TawSystemUserPrivDom-START
	/**
	 * The request scope attribute that holds the tawSystemUserPrivDom form.
	 */
	public static final String TAWSYSTEMUSERPRIVDOM_KEY = "tawSystemUserPrivDomForm";

	/**
	 * The request scope attribute that holds the tawSystemUserPrivDom list
	 */
	public static final String TAWSYSTEMUSERPRIVDOM_LIST = "tawSystemUserPrivDomList";

	// TawSystemUserPrivDom-END

	// TawSystemDept-START
	/**
	 * The request scope attribute that holds the tawSystemDept form.
	 */
	public static final String TAWSYSTEMDEPT_KEY = "tawSystemDeptForm";

	/**
	 * The request scope attribute that holds the tawSystemDept list
	 */
	public static final String TAWSYSTEMDEPT_LIST = "tawSystemDeptList";

	// TawSystemDept-END

	// TawSystemDictType-START
	/**
	 * The request scope attribute that holds the tawSystemDictType form.
	 */
	public static final String TAWSYSTEMDICTTYPE_KEY = "tawSystemDictTypeForm";

	/**
	 * The request scope attribute that holds the tawSystemDictType list
	 */
	public static final String TAWSYSTEMDICTTYPE_LIST = "tawSystemDictTypeList";

	// TawSystemDictType-END

	// TawSystemDictItem-START
	/**
	 * The request scope attribute that holds the tawSystemDictItem form.
	 */
	public static final String TAWSYSTEMDICTITEM_KEY = "tawSystemDictItemForm";

	/**
	 * The request scope attribute that holds the tawSystemDictItem list
	 */
	public static final String TAWSYSTEMDICTITEM_LIST = "tawSystemDictItemList";

	// TawSystemDictItem-END

	// PortalUnifyUser-START
	/**
	 * The request scope attribute that holds the portalUnifyUser form.
	 */
	public static final String PORTALUNIFYUSER_KEY = "portalUnifyUserForm";

	/**
	 * The request scope attribute that holds the portalUnifyUser list
	 */
	public static final String PORTALUNIFYUSER_LIST = "portalUnifyUserList";

	// PortalUnifyUser-END

	// SysManage-START
	/**
	 * The request scope attribute that holds the sysManage form.
	 */
	public static final String SYSMANAGE_KEY = "sysManageForm";

	/**
	 * The request scope attribute that holds the sysManage list
	 */
	public static final String SYSMANAGE_LIST = "sysManageList";

	// SysManage-END

	// PerFormAnceMain-START
	/**
	 * The request scope attribute that holds the perFormAnceMain form.
	 */
	public static final String PERFORMANCEMAIN_KEY = "perFormAnceMainForm";

	/**
	 * The request scope attribute that holds the perFormAnceMain list
	 */
	public static final String PERFORMANCEMAIN_LIST = "perFormAnceMainList";

	// PerFormAnceMain-END

	// PerFormAnceLink-START
	/**
	 * The request scope attribute that holds the perFormAnceLink form.
	 */
	public static final String PERFORMANCELINK_KEY = "perFormAnceLinkForm";

	/**
	 * The request scope attribute that holds the perFormAnceLink list
	 */
	public static final String PERFORMANCELINK_LIST = "perFormAnceLinkList";

	// PerFormAnceLink-END

	// PortalSysManage-START
	/**
	 * The request scope attribute that holds the portalSysManage form.
	 */
	public static final String PORTALSYSMANAGE_KEY = "portalSysManageForm";

	/**
	 * The request scope attribute that holds the portalSysManage list
	 */
	public static final String PORTALSYSMANAGE_LIST = "portalSysManageList";

	// PortalSysManage-END

	// AppfuseDemo-START
	/**
	 * The request scope attribute that holds the appfuseDemo form.
	 */
	public static final String APPFUSEDEMO_KEY = "appfuseDemoForm";

	/**
	 * The request scope attribute that holds the appfuseDemo list
	 */
	public static final String APPFUSEDEMO_LIST = "appfuseDemoList";

	// AppfuseDemo-END

	// SampleTable-START
	/**
	 * The request scope attribute that holds the sampleTable form.
	 */
	public static final String SAMPLETABLE_KEY = "sampleTableForm";

	/**
	 * The request scope attribute that holds the sampleTable list
	 */
	public static final String SAMPLETABLE_LIST = "sampleTableList";

	// SampleTable-END

	// TawSystemPrivAssign-START
	/**
	 * The request scope attribute that holds the tawSystemPrivAssign form.
	 */
	public static final String TAWSYSTEMPRIVASSIGN_KEY = "tawSystemPrivAssignForm";

	/**
	 * The request scope attribute that holds the tawSystemPrivAssign list
	 */
	public static final String TAWSYSTEMPRIVASSIGN_LIST = "tawSystemPrivAssignList";

	// TawSystemPrivAssign-END

	// TawSystemPrivUserAssign-START
	/**
	 * The request scope attribute that holds the tawSystemPrivUserAssign form.
	 */
	public static final String TAWSYSTEMPRIVUSERASSIGN_KEY = "tawSystemPrivUserAssignForm";

	/**
	 * The request scope attribute that holds the tawSystemPrivUserAssign list
	 */
	public static final String TAWSYSTEMPRIVUSERASSIGN_LIST = "tawSystemPrivUserAssignList";

	// TawSystemPrivUserAssign-END

	// TawSystemPrivMenu-START
	/**
	 * The request scope attribute that holds the tawSystemPrivMenu form.
	 */
	public static final String TAWSYSTEMPRIVMENU_KEY = "tawSystemPrivMenuForm";

	/**
	 * The request scope attribute that holds the tawSystemPrivMenu list
	 */
	public static final String TAWSYSTEMPRIVMENU_LIST = "tawSystemPrivMenuList";

	// TawSystemPrivMenu-END

	// TawSystemPrivMenuItem-START
	/**
	 * The request scope attribute that holds the tawSystemPrivMenuItem form.
	 */
	public static final String TAWSYSTEMPRIVMENUITEM_KEY = "tawSystemPrivMenuItemForm";

	/**
	 * The request scope attribute that holds the tawSystemPrivMenuItem list
	 */
	public static final String TAWSYSTEMPRIVMENUITEM_LIST = "tawSystemPrivMenuItemList";

	// TawSystemPrivMenuItem-END
	// TawSystemPrivOperation-START
	/**
	 * The request scope attribute that holds the tawSystemPrivOperation form.
	 */
	public static final String TAWSYSTEMPRIVOPERATION_KEY = "tawSystemPrivOperationForm";

	/**
	 * The request scope attribute that holds the tawSystemPrivOperation list
	 */
	public static final String TAWSYSTEMPRIVOPERATION_LIST = "tawSystemPrivOperationList";

	// TawSystemPrivOperation-END
	// TawSystemOperation-START
	/**
	 * The request scope attribute that holds the tawSystemOperation form.
	 */
	public static final String TAWSYSTEMOPERATION_KEY = "tawSystemOperationForm";

	/**
	 * The request scope attribute that holds the tawSystemOperation list
	 */
	public static final String TAWSYSTEMOPERATION_LIST = "tawSystemOperationList";

	// TawSystemOperation-END
	// TawSystemPostType-START
	/**
	 * The request scope attribute that holds the tawSystemPostType form.
	 */
	public static final String TAWSYSTEMROLETYPE_KEY = "tawSystemPostTypeForm";

	/**
	 * The request scope attribute that holds the tawSystemPostType list
	 */
	public static final String TAWSYSTEMROLETYPE_LIST = "tawSystemPostTypeList";

	// TawSystemPostType-END

	// AppfuseTable-START
	/**
	 * The request scope attribute that holds the appfuseTable form.
	 */
	public static final String APPFUSETABLE_KEY = "appfuseTableForm";

	/**
	 * The request scope attribute that holds the appfuseTable list
	 */
	public static final String APPFUSETABLE_LIST = "appfuseTableList";

	// AppfuseTable-END

	// SampleAppfuseTable-START
	/**
	 * The request scope attribute that holds the sampleAppfuseTable form.
	 */
	public static final String SAMPLEAPPFUSETABLE_KEY = "sampleAppfuseTableForm";

	/**
	 * The request scope attribute that holds the sampleAppfuseTable list
	 */
	public static final String SAMPLEAPPFUSETABLE_LIST = "sampleAppfuseTableList";

	// SampleAppfuseTable-END

	// TawSystemRole-START
	/**
	 * The request scope attribute that holds the tawSystemPost form.
	 */
	public static final String TAWSYSTEMROLE_KEY = "tawSystemRoleForm";

	/**
	 * The request scope attribute that holds the tawSystemPost list
	 */
	public static final String TAWSYSTEMROLE_LIST = "tawSystemRoleList";

	// TawSystemRole-END

	// TawSystemPost-START
	/**
	 * The request scope attribute that holds the tawSystemPost form.
	 */
	public static final String TAWSYSTEMPOST_KEY = "tawSystemPostForm";

	/**
	 * The request scope attribute that holds the tawSystemPost list
	 */
	public static final String TAWSYSTEMPOST_LIST = "tawSystemPostList";

	// TawSystemPost-END

	// TawSystemSubPost-START
	/**
	 * The request scope attribute that holds the tawSystemSubPost form.
	 */
	public static final String TAWSYSTEMSUBROLE_KEY = "tawSystemSubRoleForm";

	/**
	 * The request scope attribute that holds the tawSystemSubPost list
	 */
	public static final String TAWSYSTEMSUBROLE_LIST = "tawSystemSubRoleList";

	// TawSystemSubPost-END

	// TawSystemOrganizationProxy-START
	/**
	 * The request scope attribute that holds the tawSystemOrganizationProxy
	 * form.
	 */
	public static final String TAWSYSTEMORGANIZATIONPROXY_KEY = "tawSystemOrganizationProxyForm";

	/**
	 * The request scope attribute that holds the tawSystemOrganizationProxy
	 * list
	 */
	public static final String TAWSYSTEMORGANIZATIONPROXY_LIST = "tawSystemOrganizationProxyList";

	// TawSystemOrganizationProxy-END
	public static final String TAWSYSTEMDEPTREFPOST_LIST = "tawSystemDeptRefPostList";

	public static final String TAWSYSTEMUSERREFPOST_LIST = "tawSystemUserRefPostList";

	// TawSystemArea-START
	/**
	 * The request scope attribute that holds the tawSystemArea form.
	 */
	public static final String TAWSYSTEMAREA_KEY = "tawSystemAreaForm";

	/**
	 * The request scope attribute that holds the tawSystemArea list
	 */
	public static final String TAWSYSTEMAREA_LIST = "tawSystemAreaList";

	// TawSystemArea-END

	// TawSheetSpecial-START
	/**
	 * The request scope attribute that holds the tawSheetSpecial form.
	 */
	public static final String TAWSHEETSPECIAL_KEY = "tawSheetSpecialForm";

	/**
	 * The request scope attribute that holds the tawSheetSpecial list
	 */
	public static final String TAWSHEETSPECIAL_LIST = "tawSheetSpecialList";

	// TawSheetSpecial-END

	// TawSupplierkpiInfo-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiInfo form.
	 */
	public static final String TAWSUPPLIERKPIINFO_KEY = "tawSupplierkpiInfoForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiInfo list
	 */
	public static final String TAWSUPPLIERKPIINFO_LIST = "tawSupplierkpiInfoList";

	// TawSupplierkpiInfo-END

	// TawSupplierkpiTemplate-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiTemplate form.
	 */
	public static final String TAWSUPPLIERKPITEMPLATE_KEY = "tawSupplierkpiTemplateForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiTemplate list
	 */
	public static final String TAWSUPPLIERKPITEMPLATE_LIST = "tawSupplierkpiTemplateList";

	// TawSupplierkpiTemplate-END

	// TawSupplierkpiItem-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiItem form.
	 */
	public static final String TAWSUPPLIERKPIITEM_KEY = "tawSupplierkpiItemForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiItem list
	 */
	public static final String TAWSUPPLIERKPIITEM_LIST = "tawSupplierkpiItemList";

	// TawSupplierkpiItem-END

	// TawsuCheckModule-START
	/**
	 * The request scope attribute that holds the tawsuCheckModule form.
	 */
	public static final String TAWSUCHECKMODULE_KEY = "tawsuCheckModuleForm";

	/**
	 * The request scope attribute that holds the tawsuCheckModule list
	 */
	public static final String TAWSUCHECKMODULE_LIST = "tawsuCheckModuleList";

	// TawsuCheckModule-END

	// TawSupplierkpiInstance-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiInstance form.
	 */
	public static final String TAWSUPPLIERKPIINSTANCE_KEY = "tawSupplierkpiInstanceForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiInstance list
	 */
	public static final String TAWSUPPLIERKPIINSTANCE_LIST = "tawSupplierkpiInstanceList";

	// TawSupplierkpiInstance-END

	// TawSupplierkpiAssessInstance-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiAssessInstance
	 * form.
	 */
	public static final String TAWSUPPLIERKPIASSESSINSTANCE_KEY = "tawSupplierkpiAssessInstanceForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiAssessInstance
	 * list
	 */
	public static final String TAWSUPPLIERKPIASSESSINSTANCE_LIST = "tawSupplierkpiAssessInstanceList";

	// TawSupplierkpiAssessInstance-END

	// TawSupplierkpiRelation-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiRelation form.
	 */
	public static final String TAWSUPPLIERKPIRELATION_KEY = "tawSupplierkpiRelationForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiRelation list
	 */
	public static final String TAWSUPPLIERKPIRELATION_LIST = "tawSupplierkpiRelationList";

	// TawSupplierkpiRelation-END

	// TawSupplierkpiTemplateAssess-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiTemplateAssess
	 * form.
	 */
	public static final String TAWSUPPLIERKPITEMPLATEASSESS_KEY = "tawSupplierkpiTemplateAssessForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiTemplateAssess
	 * list
	 */
	public static final String TAWSUPPLIERKPITEMPLATEASSESS_LIST = "tawSupplierkpiTemplateAssessList";

	// TawSupplierkpiTemplateAssess-END

	// TawSupplierkpiInstanceAss-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiInstanceAss
	 * form.
	 */
	public static final String TAWSUPPLIERKPIINSTANCEASS_KEY = "tawSupplierkpiInstanceAssForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiInstanceAss list
	 */
	public static final String TAWSUPPLIERKPIINSTANCEASS_LIST = "tawSupplierkpiInstanceAssList";

	// TawSupplierkpiInstanceAss-END

	// TawSupplierkpiLog-START
	/**
	 * The request scope attribute that holds the tawSupplierkpiLog form.
	 */
	public static final String TAWSUPPLIERKPILOG_KEY = "tawSupplierkpiLogForm";

	/**
	 * The request scope attribute that holds the tawSupplierkpiLog list
	 */
	public static final String TAWSUPPLIERKPILOG_LIST = "tawSupplierkpiLogList";

	// TawSupplierkpiLog-END

	/**
	 * 删除标记，为1即为删除
	 */
	public static final String DELETED_FLAG = "1";

	/**
	 * 删除标记，为0未删
	 */
	public static final String NOT_DELETED_FLAG = "0";

	/**
	 * 树的根结点标�???1�???7
	 */
	public static final String TREE_ROOT_FLAG = "-1";

	/**
	 * 树形展示是的叶子标记
	 */
	public static final String LEAF = "1";

	/**
	 * 转向failure页面，显示信�???1�???7
	 */
	public static final String FORWARD_FAILURE = "failure";

	// TawRmPlanContent-START
	/**
	 * The request scope attribute that holds the tawRmPlanContent form.
	 */
	public static final String TAWRMPLANCONTENT_KEY = "tawRmPlanContentForm";

	/**
	 * The request scope attribute that holds the tawRmPlanContent list
	 */
	public static final String TAWRMPLANCONTENT_LIST = "tawRmPlanContentList";

	// TawRmPlanContent-END

	// TawRmLoanRecord-START
	/**
	 * The request scope attribute that holds the tawRmLoanRecord form.
	 */
	public static final String TAWRMLOANRECORD_KEY = "tawRmLoanRecordForm";

	/**
	 * The request scope attribute that holds the tawRmLoanRecord list
	 */
	public static final String TAWRMLOANRECORD_LIST = "tawRmLoanRecordList";

	// TawRmLoanRecord-END

	// TawRmVisitRecord-START
	/**
	 * The request scope attribute that holds the tawRmVisitRecord form.
	 */
	public static final String TAWRMVISITRECORD_KEY = "tawRmVisitRecordForm";

	/**
	 * The request scope attribute that holds the tawRmVisitRecord list
	 */
	public static final String TAWRMVISITRECORD_LIST = "tawRmVisitRecordList";

	// TawRmVisitRecord-END

	// TawRmDispatchRecord-START
	/**
	 * The request scope attribute that holds the tawRmDispatchRecord form.
	 */
	public static final String TAWRMDISPATCHRECORD_KEY = "tawRmDispatchRecordForm";

	/**
	 * The request scope attribute that holds the tawRmDispatchRecord list
	 */
	public static final String TAWRMDISPATCHRECORD_LIST = "tawRmDispatchRecordList";

	// TawRmDispatchRecord-END

	// TawRmReliefRecord-START
	/**
	 * The request scope attribute that holds the tawRmReliefRecord form.
	 */
	public static final String TAWRMRELIEFRECORD_KEY = "tawRmReliefRecordForm";

	/**
	 * The request scope attribute that holds the tawRmReliefRecord list
	 */
	public static final String TAWRMRELIEFRECORD_LIST = "tawRmReliefRecordList";

	// TawRmReliefRecord-END

	// TawRmWorkorderRecord-START
	/**
	 * The request scope attribute that holds the tawRmWorkorderRecord form.
	 */
	public static final String TAWRMWORKORDERRECORD_KEY = "tawRmWorkorderRecordForm";

	/**
	 * The request scope attribute that holds the tawRmWorkorderRecord list
	 */
	public static final String TAWRMWORKORDERRECORD_LIST = "tawRmWorkorderRecordList";

	// TawRmWorkorderRecord-END

	// TawRmLogUnite-START
	/**
	 * The request scope attribute that holds the tawRmLogUnite form.
	 */
	public static final String TAWRMLOGUNITE_KEY = "tawRmLogUniteForm";

	/**
	 * The request scope attribute that holds the tawRmLogUnite list
	 */
	public static final String TAWRMLOGUNITE_LIST = "tawRmLogUniteList";

	// TawRmLogUnite-END

	// TawWorkdaySet-START
	/**
	 * The request scope attribute that holds the tawWorkdaySet form.
	 */
	public static final String TAWWORKDAYSET_KEY = "tawWorkdaySetForm";

	/**
	 * The request scope attribute that holds the tawWorkdaySet list
	 */
	public static final String TAWWORKDAYSET_LIST = "tawWorkdaySetList";

	// TawWorkdaySet-END

	// EmailMonitor-START
	/**
	 * The request scope attribute that holds the emailMonitor form.
	 */
	public static final String EMAILMONITOR_KEY = "emailMonitorForm";

	/**
	 * The request scope attribute that holds the emailMonitor list
	 */
	public static final String EMAILMONITOR_LIST = "emailMonitorList";

	// EmailMonitor-END

	// SmsContentTemplate-START
	/**
	 * The request scope attribute that holds the smsContentTemplate form.
	 */
	public static final String SMSCONTENTTEMPLATE_KEY = "smsContentTemplateForm";

	/**
	 * The request scope attribute that holds the smsContentTemplate list
	 */
	public static final String SMSCONTENTTEMPLATE_LIST = "smsContentTemplateList";

	// SmsContentTemplate-END

	// SmsMobilesTemplate-START
	/**
	 * The request scope attribute that holds the smsMobilesTemplate form.
	 */
	public static final String SMSMOBILESTEMPLATE_KEY = "smsMobilesTemplateForm";

	/**
	 * The request scope attribute that holds the smsMobilesTemplate list
	 */
	public static final String SMSMOBILESTEMPLATE_LIST = "smsMobilesTemplateList";

	// SmsMobilesTemplate-END

	/**
	 * 以单点登陆方式登�???
	 */
	public static final String LOGIN_SSO = "sso";

	/**
	 * 以acegi方式登陆
	 */
	public static final String LOGIN_ACEGI = "acegi";



	// TawSheetAccess-START
	/**
	 * The request scope attribute that holds the tawSheetAccess form.
	 */
	public static final String TAWSHEETACCESS_KEY = "tawSheetAccessForm";

	/**
	 * The request scope attribute that holds the tawSheetAccess list
	 */
	public static final String TAWSHEETACCESS_LIST = "tawSheetAccessList";

	// TawSheetAccess-END

	// TawBureauData-START
	/**
	 * The request scope attribute that holds the tawBureauData form.
	 */
	public static final String TAWBUREAUDATA_KEY = "tawBureauDataForm";

	/**
	 * The request scope attribute that holds the tawBureauData list
	 */
	public static final String TAWBUREAUDATA_LIST = "tawBureauDataList";
	// TawBureauData-END

 


//TawRmReplace-START
    /**
     * The request scope attribute that holds the tawRmReplace form.
     */
    public static final String TAWRMREPLACE_KEY = "tawRmReplaceForm";

    /**
     * The request scope attribute that holds the tawRmReplace list
     */
    public static final String TAWRMREPLACE_LIST = "tawRmReplaceList";
//TawRmReplace-END
 
//TawSystemRoleImport-START
    /**
     * The request scope attribute that holds the tawSystemRoleImport form.
     */
    public static final String TAWSYSTEMROLEIMPORT_KEY = "tawSystemRoleImportForm";

    /**
     * The request scope attribute that holds the tawSystemRoleImport list
     */
    public static final String TAWSYSTEMROLEIMPORT_LIST = "tawSystemRoleImportList";
//TawSystemRoleImport-END
 
//TawSystemCode-START
    /**
     * The request scope attribute that holds the tawSystemCode form.
     */
    public static final String TAWSYSTEMCODE_KEY = "tawSystemCodeForm";

    /**
     * The request scope attribute that holds the tawSystemCode list
     */
    public static final String TAWSYSTEMCODE_LIST = "tawSystemCodeList";
//TawSystemCode-END
    
    public static final String EOMS_SECURITY_EXCEPTION_KEY = "eoms_security_exception_key";
    
    public static final String LOGIN_EOMS = "eoms";

}

 
 
