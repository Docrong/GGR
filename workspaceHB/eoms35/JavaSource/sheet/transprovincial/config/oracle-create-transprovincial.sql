

create table TRANSPROVINCIAL_LINK
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
  ASSIGNMENTRESULT			VARCHAR2(1000) ,
  DESGINRESULT			VARCHAR2(1000) ,
  EXAMINERESULT			VARCHAR2(1000) ,
  CONSTRACTCONDITION			VARCHAR2(1000) ,
  COMPLETERESULT			VARCHAR2(1000) ,
  ACCEPTANCECONDITION			VARCHAR2(1000) ,
  ACCEPTANCERESULT			VARCHAR2(1000) ,
  COMPLETIONRESULT			VARCHAR2(1000) ,
  REPLYRESULT			VARCHAR2(1000) ,
  REPLYRESULT			VARCHAR2(1000) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table TRANSPROVINCIAL_LINK
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





create table TRANSPROVINCIAL_MAIN
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
  MANAGER			VARCHAR2(500) ,
  MANAGERNUM			VARCHAR2(500) ,
  PRODOUCT			VARCHAR2(500) ,
  GROUPNUM			VARCHAR2(500) ,
  GROUPNAME			VARCHAR2(500) ,
  CUSTOMGRADE			VARCHAR2(500) ,
  BUSINESSGRADE			VARCHAR2(500) ,
  TASKTYPE			VARCHAR2(500) ,
  AREA			VARCHAR2(500) ,
  BANDWIDTH			VARCHAR2(500) ,
  LINENUM			VARCHAR2(500) ,
  ATYPEAREA			VARCHAR2(500) ,
  ZTYPEAREA			VARCHAR2(500) ,
  RESOURCEREPORT			VARCHAR2(64) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table TRANSPROVINCIAL_MAIN add constraint TRANSPROVINCIAL_MAIN_PK primary key (id)  using index; 








create table TRANSPROVINCIAL_TASK
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
alter table TRANSPROVINCIAL_TASK add constraint TRANSPROVINCIAL_TASK_PK primary key (id)  using index; 




--创建索引
create index i_transprovincial_a on transprovincial_task (taskowner,createtime) TABLESPACE EOMSDATAV35 ;
create index i_transprovincial_b on transprovincial_task (createtime)TABLESPACE EOMSDATAV35 ;    
create index i_transprovincial_c on transprovincial_task (taskstatus)TABLESPACE EOMSDATAV35 ;    
create index i_transprovincial_d on transprovincial_task (sheetkey)TABLESPACE EOMSDATAV35 ;
create index i_transprovincial_e on transprovincial_main (sendtime,senduserid)TABLESPACE EOMSDATAV35 ;
create index i_transprovincial_f on transprovincial_main (sheetid)TABLESPACE EOMSDATAV35 ;
create index i_transprovincial_g on transprovincial_link (aiid)TABLESPACE EOMSDATAV35 ;
create index i_transprovincial_h on transprovincial_link (mainid)TABLESPACE  EOMSDATAV35 ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('994','Transprovincial','994','994','跨省集客业务工单流程','iTransprovincialMainManager');
 
--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('101', '跨省集客业务工单流程', '跨省集客业务工单流程', 101);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'transprovincial', '跨省集客业务工单流程', 1024, '/sheet/transprovincial', 101, '402881043a066d0f013a066d0fe60000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2020','0','0','建单人','1','994','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2021','0','0','网管二级审核人','1','994','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2022','0','0','地市工建人','1','994','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2023','0','0','地市维护人','1','994','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60001', '5801', '1', '新增工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60002', '5803', '0', '待处理工单', '58', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60003', '580301', '1', '待办工单', '5803', null, '/sheet/transprovincial/transprovincial.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60004', '5807', '1', '工单查询', '58', null, '/sheet/transprovincial/transprovincial.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60005', '5808', '1', '工单处理模板', '58', null, '/sheet/transprovincial/transprovincial.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60006', '5809', '1', '工单模板管理', '58', null, '/sheet/transprovincial/transprovincial.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60007', '5810', '1', '管理者工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60008', '58', '0', '跨省集客业务工单流程', '10', null, '/sheet/transprovincial/transprovincial.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60009', '5802', '1', '草稿列表', '58', null, '/sheet/transprovincial/transprovincial.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe6000a', '5804', '0', '已处理工单', '58', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe6000b','580402','1','建立工单','5804', null, '/sheet/transprovincial/transprovincial.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe6000c', '580401', '1', '处理工单', '5804', null, '/sheet/transprovincial/transprovincial.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe6000d', '5805', '1', '已归档工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe6000e', '5806', '1', '已作废工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe6000f', '5811', '1', '超时时间设置', '58', null, '/sheet/overtimetip/overtimetip.do?flowName=Transprovincial', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60010', '5812', '1', '工单处理时限配置', '58', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=Transprovincial', '0', '0', 30);