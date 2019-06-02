

create table COMMONCHANGEDEPLOY_LINK
(

   ID                  VARCHAR2(32) not null,
  MAINID               VARCHAR2(50),
  NODEACCEPTLIMIT      datetime year to second,
  NODECOMPLETELIMIT    datetime year to second,
  OPERATETYPE          INTEGER,
  OPERATETIME          datetime year to second,
  OPERATEUSERID        VARCHAR2(30),
  OPERATEORGTYPE       VARCHAR2(10),
  OPERATERCONTACT      VARCHAR2(50),
  TOORGTYPE            INTEGER,
  TOORGUSERID          VARCHAR2(50),
  TOROLEID             INTEGER,
  ACCEPTFLAG           INTEGER,
  ACCEPTTIME           datetime year to second,
  COMPLETEFLAG         INTEGER,
  COMPLETETIME         datetime year to second,
  PRELINKID            VARCHAR2(50),
  PARENTLINKID         VARCHAR2(50),
  FIRSTLINKID          VARCHAR2(50),
  PIID                 VARCHAR2(50),
  AIID                 VARCHAR2(50),
  ACTIVETEMPLATEID     VARCHAR2(50),
  NODETEMPLATENAME     VARCHAR2(50),
  NODEACCESSORIES      VARCHAR2(50),
  TOORGDEPTID          VARCHAR2(35),
  TOORGROLEID          VARCHAR2(35),
  OPERATEROLEID        VARCHAR2(35),
  OPERATEDEPTID        VARCHAR2(35),
  CORRELATIONKEY       VARCHAR2(50),
  TEMPLATEFLAG         VARCHAR2(20),
  TEMPLATENAME         VARCHAR2(100),
  TEMPLATECREATEUSERID VARCHAR2(100),
  TRANSFERREASON       VARCHAR2(200),
  REMARK               VARCHAR2(200),
  OPERATEYEAR          INTEGER,
  OPERATEMONTH         INTEGER,
  OPERATEDAY           INTEGER,
  SENDOBJECT           CHAR2(2000),


  LINKISCHECK			VARCHAR2(32) ,		
		

  LINKMANAGERCONTACT			VARCHAR2(32) ,		

  LINKCOPYOBJECT			VARCHAR2(32) ,		

  LINKPLANSTARTTIME			datetime year to second,		

  LINKPLANENDTIME			datetime year to second,		

  LINKEFFECTCELLSCOPE			VARCHAR2(32) ,		

  LINKEFFECTBUSINESS			VARCHAR2(32) ,		

  LINKEFFECTCONDITION			VARCHAR2(32) ,		
		

  LINKBUSINESSDEPT			VARCHAR2(32) ,		

  LINKISSENDTOFRONT			VARCHAR2(32) ,		

  LINKEXECUTEACCESSORIES			VARCHAR2(32) ,		

  LINKCUTRESULT			VARCHAR2(32) ,		

  LINKFAILEDREASON			VARCHAR2(32) ,		

  LINKISPLAN			VARCHAR2(32) ,		
		
		

  LINKTESTREPORT			VARCHAR2(32) ,		
		
		

  MAINIFDEMONSTRATECASE			VARCHAR2(32) ,		

  LINKCASESMAINKEY			VARCHAR2(32) ,		
		

  primary key(id)
);;





create table COMMONCHANGEDEPLOY_MAIN
(
   ID                 VARCHAR2(32) not null,
  SHEETID             VARCHAR2(50),
  TITLE               VARCHAR2(100),
  SHEETACCEPTLIMIT    datetime year to second,
  SHEETCOMPLETELIMIT  datetime year to second,
  SENDTIME            datetime year to second,
  SENDORGTYPE         VARCHAR2(25),
  SENDUSERID          VARCHAR2(30),
  SENDCONTACT         VARCHAR2(30),
  SHEETACCESSORIES    VARCHAR2(100),
  ENDTIME             datetime year to second,
  ENDRESULT           VARCHAR2(30),
  STATUS              INTEGER,
  HOLDSTATISFIED      INTEGER,
  ENDUSERID           VARCHAR2(50),
  DELETED             INTEGER,
  PIID                VARCHAR2(50),
  SHEETTEMPLATENAME   VARCHAR2(50),
  PARENTSHEETNAME     VARCHAR2(50),
  PARENTSHEETID       VARCHAR2(50),
  CORRELATIONKEY      VARCHAR2(50),
  PARENTCORRELATION   VARCHAR2(50),
  TODEPTID            VARCHAR2(50),
  SENDDEPTID          VARCHAR2(35),
  SENDROLEID          VARCHAR2(35),
  ENDROLEID           VARCHAR2(35),
  ENDDEPTID           VARCHAR2(35),
  TEMPLATEFLAG        VARCHAR2(20),
  CANCELREASON        VARCHAR2(100),
  SENDYEAR            INTEGER,
  SENDMONTH           INTEGER,
  SENDDAY             INTEGER,
  PARENTPHASENAME     VARCHAR2(50),
  INVOKEMODE          VARCHAR2(50),
  SENDOBJECT          VARCHAR2(4000),



  MAINNETTYPEONE			VARCHAR2(32) ,

  MAINNETTYPETWO			VARCHAR2(32) ,

  MAINNETTYPETHREE			VARCHAR2(32) ,

  MAINISSECURITY			VARCHAR2(32) ,

  MAINISCONNECT			VARCHAR2(32) ,

  MAINFACTORY			VARCHAR2(32) ,

  MAINAPPLYREASON			VARCHAR2(32) ,

  MAINCELLINFO			VARCHAR2(32) ,

  MAINISBACK			VARCHAR2(32) ,

  MAINCHANGESOURCE			VARCHAR2(32) ,

  MAINCHECKMODEL			VARCHAR2(32) ,

  MAINCOMPLETELIMITTIME			datetime year to second,

  MAININVOLVEDPROVINCE			VARCHAR2(32) ,

  MAININVOLVEDCITY			VARCHAR2(32) ,


  MAINDESIGNKEY			VARCHAR2(32) ,

  MAINRISKESTIMATE			VARCHAR2(32) ,


  MAINPROJECTACCESSORIES			VARCHAR2(32) ,

  primary key(id)
);







create table COMMONCHANGEDEPLOY_TASK
(
  ID                VARCHAR2(50) not null,
  TASKNAME          VARCHAR2(50),
  TASKDISPLAYNAME   VARCHAR2(50),
  CREATETIME        datetime year to second,
  TASKSTATUS        VARCHAR2(50),
  PROCESSID         VARCHAR2(50),
  SHEETKEY          VARCHAR2(50),
  SHEETID           VARCHAR2(50),
  TITLE             VARCHAR2(255),
  ACCEPTTIMELIMIT   datetime year to second,
  COMPLETETIMELIMIT datetime year to second,
  OPERATEROLEID     VARCHAR2(50),
  TASKOWNER         VARCHAR2(50),
  PRELINKID         VARCHAR2(50),
  FLOWNAME          VARCHAR2(50),
  SUBTASKFLAG       VARCHAR2(10) default 'false',
  OPERATETYPE       VARCHAR2(25),
  CURRENTLINKID     VARCHAR2(255),
  PARENTTASKID      VARCHAR2(50),
  IFWAITFORSUBTASK  VARCHAR2(50),
  SUBTASKDEALFALG   VARCHAR2(50),
  CREATEYEAR        INTEGER,
  CREATEMONTH       INTEGER,
  CREATEDAY         INTEGER,
  SENDTIME          datetime year to second,




  primary key(id)
);


--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('045','CommonChangeDeployProcess','045','045','通用变更配置工单流程','iCommonChangeDeployMainManager');
 
--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('127', '通用变更配置工单流程', '通用变更配置工单流程', 127);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'commonchangedeploy', '通用变更配置工单流程', 1024, '/sheet/commonchangedeploy', 127, '8aa0829921d201ca0121d201ca420012');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420013', '120301', '1', '新增工单', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowNewInputSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420014', '120303', '0', '待处理工单', '1203', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420015', '12030301', '1', '待办工单', '120303', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420016', '120307', '1', '工单查询', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420017', '120308', '1', '工单处理模板', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newGetDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420018', '120309', '1', '工单模板管理', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newGetTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420019', '120310', '1', '管理者工单', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca42001a', '1203', '0', '通用变更配置工单', '10', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca42001b', '120302', '1', '草稿列表', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca42001c', '120304', '0', '已处理工单', '1203', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca42001d','12030402','1','建立工单','120304', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca42001e', '12030401', '1', '处理工单', '120304', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca42001f', '120305', '1', '已归档工单', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420020', '120306', '1', '已作废工单', '1203', null, '/sheet/commonchangedeploy/commonchangedeploy.do?method=newShowCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420021', '120311', '1', '超时时间设置', '1203', null, '/sheet/overtimetip/overtimetip.do?flowName=EmergencyDrillProcess', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420022', '120312', '1', '工单处理时限配置', '1203', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=EmergencyDrillProcess', '0', '0', 30);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('8aa0829921d201ca0121d201ca420023', '120313', '1', '配置后续处理对象', '1203', null, '/sheet/dealtypeconfig/dealtypeconfig.do?flowName=EmergencyDrillProcess', '0', '0', 12);