

create table CIRCUITCONTROL_LINK
(
    ID                 VARCHAR2(32) not null,
  MAINID               VARCHAR2(50),
  NODEACCEPTLIMIT      DATE,
  NODECOMPLETELIMIT    DATE,
  OPERATETYPE          NUMBER,
  OPERATETIME          DATE,
  OPERATEUSERID        VARCHAR2(30),
  OPERATEORGTYPE       VARCHAR2(10),
  OPERATERCONTACT      VARCHAR2(50),
  TOORGTYPE            NUMBER,
  TOORGUSERID          VARCHAR2(50),
  TOROLEID             NUMBER,
  ACCEPTFLAG           NUMBER,
  ACCEPTTIME           DATE,
  COMPLETEFLAG         NUMBER,
  COMPLETETIME         DATE,
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
  TRANSFERREASON       VARCHAR2(2000),
  REMARK               VARCHAR2(2000),
  OPERATEYEAR          NUMBER,
  OPERATEMONTH         NUMBER,
  OPERATEDAY           NUMBER,
  LINKGROUPCOMPLETE			VARCHAR2(1000) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table CIRCUITCONTROL_LINK
  add primary key (id)
  using index 
  tablespace EXPLDB01
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    minextents 1
    maxextents unlimited
  ); 





create table CIRCUITCONTROL_MAIN
(
   ID                 VARCHAR2(32) not null,
  SHEETID             VARCHAR2(50),
  TITLE               VARCHAR2(100),
  SHEETACCEPTLIMIT    DATE,
  SHEETCOMPLETELIMIT  DATE,
  SENDTIME            DATE,
  SENDORGTYPE         VARCHAR2(25),
  SENDUSERID          VARCHAR2(30),
  SENDCONTACT         VARCHAR2(30),
  SHEETACCESSORIES    VARCHAR2(100),
  ENDTIME             DATE,
  ENDRESULT           VARCHAR2(30),
  STATUS              NUMBER,
  HOLDSTATISFIED      NUMBER,
  ENDUSERID           VARCHAR2(50),
  DELETED             NUMBER,
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
  SENDYEAR            NUMBER,
  SENDMONTH           NUMBER,
  SENDDAY             NUMBER,
  PARENTPHASENAME     VARCHAR2(50),
  INVOKEMODE          VARCHAR2(50),
  APPLYNUM			VARCHAR2(1000) ,
  BUSINESSTYPE			VARCHAR2(1000) ,
  SPAREONE			VARCHAR2(1000) ,
  SPARETWO			VARCHAR2(1000) ,
  SPARETHREE			VARCHAR2(1000) ,
  SPAREFOUR			VARCHAR2(1000) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table CIRCUITCONTROL_MAIN add constraint CIRCUITCONTROL_MAIN_PK primary key (id)  using index; 








create table CIRCUITCONTROL_TASK
(
  ID                VARCHAR2(50) not null,
  TASKNAME          VARCHAR2(50),
  TASKDISPLAYNAME   VARCHAR2(50),
  CREATETIME        DATE,
  TASKSTATUS        VARCHAR2(50),
  PROCESSID         VARCHAR2(50),
  SHEETKEY          VARCHAR2(50),
  SHEETID           VARCHAR2(50),
  TITLE             VARCHAR2(255),
  ACCEPTTIMELIMIT   DATE,
  COMPLETETIMELIMIT DATE,
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
  CREATEYEAR        NUMBER,
  CREATEMONTH       NUMBER,
  CREATEDAY         NUMBER,
  SENDTIME          DATE,
  preDealDept       VARCHAR2(50),
  preDealUserId 	VARCHAR2(50),
  correlationKey 	VARCHAR2(100),
  levelId           VARCHAR2(100),
 parentLevelId     VARCHAR2(100)
);
alter table CIRCUITCONTROL_TASK add constraint CIRCUITCONTROL_TASK_PK primary key (id)  using index; 




--创建索引
create index i_circuitcontrol_a on circuitcontrol_task (taskowner,createtime) TABLESPACE EOMSDATAV35 ;
create index i_circuitcontrol_b on circuitcontrol_task (createtime)TABLESPACE EOMSDATAV35 ;    
create index i_circuitcontrol_c on circuitcontrol_task (taskstatus)TABLESPACE EOMSDATAV35 ;    
create index i_circuitcontrol_d on circuitcontrol_task (sheetkey)TABLESPACE EOMSDATAV35 ;
create index i_circuitcontrol_e on circuitcontrol_main (sendtime,senduserid)TABLESPACE EOMSDATAV35 ;
create index i_circuitcontrol_f on circuitcontrol_main (sheetid)TABLESPACE EOMSDATAV35 ;
create index i_circuitcontrol_g on circuitcontrol_link (aiid)TABLESPACE EOMSDATAV35 ;
create index i_circuitcontrol_h on circuitcontrol_link (mainid)TABLESPACE  EOMSDATAV35 ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('619','CircuitControl','619','619','电路调度申请工单流程','iCircuitControlMainManager');
 
--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('106', '电路调度申请工单流程', '电路调度申请工单流程', 106);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'circuitcontrol', '电路调度申请工单流程', 1024, '/sheet/circuitcontrol', 106, '402882e54168ea1b014168ea1b980000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('1940','0','0','建单人','1','619','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('1941','0','0','有线科','1','619','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('1942','0','0','网管中心','1','619','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980001', '8601', '1', '新增工单', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980002', '8603', '0', '待处理工单', '86', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980003', '860301', '1', '待办工单', '8603', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980004', '8607', '1', '工单查询', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980005', '8608', '1', '工单处理模板', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980006', '8609', '1', '工单模板管理', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980007', '8610', '1', '管理者工单', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980008', '86', '0', '电路调度申请工单流程', '10', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980009', '8602', '1', '草稿列表', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b98000a', '8604', '0', '已处理工单', '86', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b98000b','860402','1','建立工单','8604', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b98000c', '860401', '1', '处理工单', '8604', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b98000d', '8605', '1', '已归档工单', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b98000e', '8606', '1', '已作废工单', '86', null, '/sheet/circuitcontrol/circuitcontrol.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b98000f', '8611', '1', '超时时间设置', '86', null, '/sheet/overtimetip/overtimetip.do?flowName=CircuitControl', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980010', '8612', '1', '工单处理时限配置', '86', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=CircuitControl', '0', '0', 30);