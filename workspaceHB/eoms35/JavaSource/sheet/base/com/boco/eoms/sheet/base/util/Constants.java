/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:55:03
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class Constants {
	
    //工单运行状态
	public static final Integer SHEET_RUN = new Integer(0);
	public static final Integer SHEET_HOLD = new Integer(1);
	public static final Integer SHEET_CANCEL = new Integer("-1");
	public static final Integer SHEET_DELETE = new Integer("-2");
	public static final Integer SHEET_RECORD  = new Integer(1);
	public static final Integer SHEET_NONRECORD  = new Integer(0);
	public static final Integer SHEET_DRAFT  = new Integer(3);
	public static final Integer SHEET_TEMPSAVE  = new Integer(4); //暂存 add by yangliangliang
	
	//Task状态值,仅适用于WPS流程引擎
	public static final String TASK_STATUS_INACTIVE="1";
	public static final String TASK_STATUS_READY="2";//未接单
	public static final String TASK_STATUS_RUNNING="3";
	public static final String TASK_STATUS_CLAIMED="8";//接单状态
	public static final String TASK_STATUS_FINISHED="5";//一条任务完成
	
    /**流程动作 任务的操作类型 小于60 BEGIN**/
	public static final int ACTION_SEND = 0;//新建派发
	public static final int ACTION_RESEND = 54;//重新派发
	public static final int ACTION_TRANSMIT = 1;//转派
	public static final int ACTION_DRAFT_TO_SEND = 3;//草稿派发
	public static final int ACTION_REJECTDONE = 4;//驳回
	public static final int ACTION_BACKTOUP = 5;//回复上一级
	public static final int ACTION_BACKTODOWNACCEPT = 6;//处理回复通过
	public static final int ACTION_BACKTODOWNREJECT = 7;//处理回复不通过
	public static final int ACTION_PHASE_BACKTOUP=9;  //阶段回复
	public static final int ACTION_SUBTASK=10;  //任务分派
	public static final int ACTION_SUBTASK_REPLY=11;  //任务分派
	public static final int ACTION_AUDITACCEPT = 12;//审核通过
	public static final int ACTION_AUDITREJECT = 13;//审核不通过
	public static final int ACTION_SIGNATUREACCEPT = 14;//会签通过
	public static final int ACTION_SIGNATUREREJECT = 15;//会签不通过
	public static final int ACTION_HOLD = 18;//归档
	public static final int ACTION_OKB = 19; //入经验库
	public static final int ACTION_SENDTOAUDIT = 20;//送审
	public static final int ACTION_SENDTOSIGN = 21;//送会签
	public static final int ACTION_DRAFT = 22; //草稿保存
	public static final int ACTION_DRAFT_TO_AUDIT = 23;//草稿送审
	public static final int ACTION_ACCEPT_SEND = 24;//审核通过派发
	public static final int ACTION_NEW_SEND_TO_AUDIT = 25;//新建送审
	public static final int ACTION_FAULT_CANCEL = 27;//重大故障上报工单，派发已销障工单通知
	public static final int ACTION_FAULT_ACCEPT_CAN = 28;//重大故障上报工单，确认销障
	public static final int ACTION_FAULT_HOLD = 29;//重大故障上报工单，通知归档
	public static final int ACTION_REPLY_SENDTOAUDIT = 30;	//回复送审
	public static final int ACTION_REPLY_AUDITACCEPT = 31;	// 回复审核通过
	public static final int ACTION_REPLY_AUDITREJECT = 32;	// 回复审核不通过
	public static final int ACTION_TRANSMIT_SENDTOAUDIT = 33;	//转派送审
	public static final int ACTION_TRANSMIT_AUDITACCEPT = 34;	//转派审核通过
	public static final int ACTION_TRANSMIT_AUDITREJECT = 35;	//转派审核不通过
	public static final int ACTION_DEALAUDTI=36;       //处理审核意见
	public static final int ACTIION_APPLYTOUP = 37;   //提出申请
	public static final int ACTIION_READAPPLY = 38;   //查看申请审核意见
	public static final int ACTIION_WRITEAPPLY = 39;   //审核申请	
	public static final int ACTION_CHECK_QUALITY_SEND = 40;
	public static final int ACTION_CHECK_QUALITY = 41;
	public static final int ACTION_CHECK_QUALITY_ACCEPT = 42;
	public static final int ACTION_CHECK_QUALITY_REJECT = 43;
	public static final int ACTION_DEAL_AFTER_REPLY = 44;
	public static final int ACTION_DEAL_AFTER_HOLD = 45;
	public static final int ACTION_BACKTOTOP = 46;//回复顶级
    public static final int ACTION_JOIN_AUDIT = 30; //会审
	/**流程动作 非完成任务的操作类型 大于60 BEGIN**/
	public static final int ACTION_ACCEPT = 61;//接单确认受理 抢单
	public static final int ACTION_LOCALDONE = 62;//本地处理
	public static final int ACTION_DRIVERFORWARDACCEPT = 63;//接受催办
	public static final int ACTION_CANCEL = 64;//撤消
	public static final int ACTION_RECORD = 65;//记录
	public static final int ACTION_UPDATE = 66;//修改工单
	public static final int ACTION_PHASE_ADVICE_ACCEPT = 67;//接受阶段建议
	
	/**非流程动作 全部小于0 BEGIN**/
	public static final int ACTION_MAKECOPYFOR = -10;//抄送
	public static final int ACTION_DRIVERFORWARD = -11;//催办 阶段通知
	public static final int ACTION_NULLITY = -12;//作废 
	public static final int ACTION_FORCE_HOLD = -13;//强制归档
	public static final int ACTION_FORCE_NULLITY = -14;//强制作废
	public static final int ACTION_READCOPY = -15;//阅读抄送	
	public static final int ACTION_PHASE_ADVICE = -24;//阶段建议
	
	
	/**流程的处理标志 begin**/
	public static final int MAKE_COMPLETEFLAG_INTIME=1;//及时
	public static final int MAKE_COMPLETEFLAG_DEFER=2;//不及时
	public static final int MAKE_ACCEPTFLAG_FINISH=3;//处理完成
	public static final int MAKE_ACCEPTFLAG_ING=4;//处理未完成 流程正在处理

    //操作类型
	public static final int OPER_SEND = 1; //派发
	public static final int OPER_AUDIT = 3; //审核
	public static final int OPER_COPY = 2; //抄送
	
	//操作者类型-operateOrgType,add by qinmin
	public static final int OPER_ORG_USER=1; //人员
	public static final int OPER_ORG_DEPT=2; //部门
	public static final int OPER_ORG_ROLE=3; //角色
	
	//template status
	public static final int TEMPLATE_STATUS_DELETE = -1;
	// is template
	public static final int TEMPLATE_STATUS_FLAG = 1;
	
	//短信派发专属常量  
	public static final String SMS_CONFIG="worksheet"; //短信配置信息头
	public static final String SMS_PROCESS_CN_NAME="processCnMame"; //流程中文名称
	public static final String SMS_SERVICE_INSTANT="smsServiceId.instant"; // 即时提醒
	public static final String SMS_SERVICE_PREOVERTIME_ACCEPT="smsServiceId.preOverTime.accept"; //接单超时前提醒
	public static final String SMS_SERVICE_PREOVERTIME_DEAL="smsServiceId.preOverTime.deal"; //处理超时前提醒
	public static final String SMS_SERVICE_POSTOVERTIME_ACCPT="smsServiceId.postOverTime.accept"; //处理超时后提醒
	public static final String SMS_SERVICE_POSTOVERTIME_DEAL="smsServiceId.postOverTime.deal"; //处理超时后提醒
	public static final String VOICE_SERVICE_INSTANT="voiceServiceId.instant"; // 即时提醒
	public static final String VOICE_SERVICE_PREOVERTIME_ACCEPT="voiceServiceId.preOverTime.accept"; //接单超时前提醒
	public static final String VOICE_SERVICE_PREOVERTIME_DEAL="voiceServiceId.preOverTime.deal"; //处理超时前提醒
	public static final String VOICE_SERVICE_POSTOVERTIME_ACCPT="voiceServiceId.postOverTime.accept"; //处理超时后提醒
	public static final String VOICE_SERVICE_POSTOVERTIME_DEAL="voiceServiceId.postOverTime.deal"; //处理超时后提醒
	/**是否改变发送对象标示，add by 秦敏 20090915 begin**/
	public static final String SMS_CHANGE_SENDER="smsServiceId.changeSender"; 
	public static final String VOICE_CHANGE_SENDER="voiceServiceId.changeSender";
	/**是否改变发送对象标示，add by 秦敏 20090915 end**/
	//短信接收者类型
	public static final int SMS_RECEIVE_TYPE_USER=1;
	public static final int SMS_RECEIVE_TYPE_DEPT=2;
	public static final int SMS_RECEIVE_TYPE_ROLE=3;
	
	//短信类型
	public static final int SMS_TYPE_INSTANT=1;
	public static final int SMS_TYPE_ACCEPT=2;
	public static final int SMS_TYPE_DEAL=3;
	
	
	//是否存在子任务  
    public static final String SUB_TASK_FLAG_F="false"; 
    public static final String SUB_TASK_FLAG_T="true";  
    
    //非流程动作名称
    public static final String TASK_NAME_COPY="cc";
    public static final String TASK_NAME_ADVICE="advice";
    public static final String TASK_NAME_REPLY="reply";
    public static final String TASK_NAME_DRAFT="DraftTask";
    
   
    //操作类型值OperateType
    //驳回上一级
    public static final int OPERATEYPE_NAME_REJECT = 4;
    //延期申请
    public static final int OPERATEYPE_NAME_DEFER_APPLY = 5;
    //分派回复
    public static final int OPERATEYPE_NAME_SPLIT_REPLY = 11;
    

    
    //流程互调状态，add by qinmin
    public static final int INVOKE_STATE_RUNNING=1; //调用开始
    public static final int INVOKE_STATE_FINISH=2;  //调用结束
    public static final int INVOKE_STATE_ASYNCHRONISM=3;  //异步调用
    
    public static final String WHOLE_TASK_REPLY = "AffirmHumTask"; //处理回复中
    public static final int ACTION_AUDIT_PASS = 201; //审批通过
    
    public static final String pageColumnName = "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,"
		+ "dealPerformerType@java.lang.String,copyPerformer@java.lang.String,"
		+ "copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,"
		+ "auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,"
		+ "phaseId@java.lang.String,beanId@java.lang.String,"
		+ "mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,"
		+ "subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,"
		+ "linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,"
		+ "extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String";

}
