
create table TRANSPROVINCIAL_LINK
(
    ID                 VARCHAR(32) not null,
  MAINID               VARCHAR(50),
  NODEACCEPTLIMIT      datetime year to second,
  NODECOMPLETELIMIT    datetime year to second,
  OPERATETYPE          INTEGER,
  OPERATETIME          datetime year to second,
  OPERATEUSERID        VARCHAR(30),
  OPERATEORGTYPE       VARCHAR(10),
  OPERATERCONTACT      VARCHAR(50),
  TOORGTYPE            INTEGER,
  TOORGUSERID          VARCHAR(50),
  TOROLEID             INTEGER,
  ACCEPTFLAG           INTEGER,
  ACCEPTTIME           datetime year to second,
  COMPLETEFLAG         INTEGER,
  COMPLETETIME         datetime year to second,
  PRELINKID            VARCHAR(50),
  PARENTLINKID         VARCHAR(50),
  FIRSTLINKID          VARCHAR(50),
  PIID                 VARCHAR(50),
  AIID                 VARCHAR(50),
  ACTIVETEMPLATEID     VARCHAR(50),
  NODETEMPLATENAME     VARCHAR(50),
  NODEACCESSORIES      VARCHAR(50),
  TOORGDEPTID          VARCHAR(35),
  TOORGROLEID          VARCHAR(35),
  OPERATEROLEID        VARCHAR(35),
  OPERATEDEPTID        VARCHAR(35),
  CORRELATIONKEY       VARCHAR(50),
  TEMPLATEFLAG         VARCHAR(20),
  TEMPLATENAME         VARCHAR(100),
  TEMPLATECREATEUSERID VARCHAR(100),
  TRANSFERREASON       VARCHAR(200),
  REMARK               VARCHAR(200),
  OPERATEYEAR          INTEGER,
  OPERATEMONTH         INTEGER,
  OPERATEDAY           INTEGER,
  LINKSENDOBJECT           CHAR(4000),
  ASSIGNMENTRESULT			VARCHAR(1000) ,
  DESGINRESULT			VARCHAR(1000) ,
  EXAMINERESULT			VARCHAR(1000) ,
  CONSTRACTCONDITION			VARCHAR(1000) ,
  COMPLETERESULT			VARCHAR(1000) ,
  ACCEPTANCECONDITION			VARCHAR(1000) ,
  ACCEPTANCERESULT			VARCHAR(1000) ,
  COMPLETIONRESULT			VARCHAR(1000) ,
  REPLYRESULT			VARCHAR(1000) ,
  REPLYRESULT			VARCHAR(1000) ,
  primary key(id)
);
 

create table TRANSPROVINCIAL_MAIN
(
   ID                 VARCHAR(32) not null,
  SHEETID             VARCHAR(50),
  TITLE               VARCHAR(100),
  SHEETACCEPTLIMIT    datetime year to second,
  SHEETCOMPLETELIMIT  datetime year to second,
  SENDTIME            datetime year to second,
  SENDORGTYPE         VARCHAR(25),
  SENDUSERID          VARCHAR(30),
  SENDCONTACT         VARCHAR(30),
  SHEETACCESSORIES    VARCHAR(100),
  ENDTIME             datetime year to second,
  ENDRESULT           VARCHAR(30),
  STATUS              INTEGER,
  HOLDSTATISFIED      INTEGER,
  ENDUSERID           VARCHAR(50),
  DELETED             INTEGER,
  PIID                VARCHAR(50),
  SHEETTEMPLATENAME   VARCHAR(50),
  PARENTSHEETNAME     VARCHAR(50),
  PARENTSHEETID       VARCHAR(50),
  CORRELATIONKEY      VARCHAR(50),
  PARENTCORRELATION   VARCHAR(50),
  TODEPTID            VARCHAR(50),
  SENDDEPTID          VARCHAR(35),
  SENDROLEID          VARCHAR(35),
  ENDROLEID           VARCHAR(35),
  ENDDEPTID           VARCHAR(35),
  TEMPLATEFLAG        VARCHAR(20),
  CANCELREASON        VARCHAR(100),
  SENDYEAR            INTEGER,
  SENDMONTH           INTEGER,
  SENDDAY             INTEGER,
  PARENTPHASENAME     VARCHAR(50),
  INVOKEMODE          VARCHAR(50),
  SENDOBJECT          CHAR(4000),
  MANAGER			VARCHAR(500) ,
  MANAGERNUM			VARCHAR(500) ,
  PRODOUCT			VARCHAR(500) ,
  GROUPNUM			VARCHAR(500) ,
  GROUPNAME			VARCHAR(500) ,
  CUSTOMGRADE			VARCHAR(500) ,
  BUSINESSGRADE			VARCHAR(500) ,
  TASKTYPE			VARCHAR(500) ,
  AREA			VARCHAR(500) ,
  BANDWIDTH			VARCHAR(500) ,
  LINENUM			VARCHAR(500) ,
  ATYPEAREA			VARCHAR(500) ,
  ZTYPEAREA			VARCHAR(500) ,
  RESOURCEREPORT			VARCHAR(64) ,
  primary key(id)
);




create table TRANSPROVINCIAL_TASK
(
  ID                VARCHAR(50) not null,
  TASKNAME          VARCHAR(50),
  TASKDISPLAYNAME   VARCHAR(50),
  CREATETIME        datetime year to second,
  TASKSTATUS        VARCHAR(50),
  PROCESSID         VARCHAR(50),
  SHEETKEY          VARCHAR(50),
  SHEETID           VARCHAR(50),
  TITLE             VARCHAR(255),
  ACCEPTTIMELIMIT   datetime year to second,
  COMPLETETIMELIMIT datetime year to second,
  OPERATEROLEID     VARCHAR(50),
  TASKOWNER         VARCHAR(50),
  PRELINKID         VARCHAR(50),
  FLOWNAME          VARCHAR(50),
  SUBTASKFLAG       VARCHAR(10) default 'false',
  OPERATETYPE       VARCHAR(25),
  CURRENTLINKID     VARCHAR(255),
  PARENTTASKID      VARCHAR(50),
  IFWAITFORSUBTASK  VARCHAR(50),
  SUBTASKDEALFALG   VARCHAR(50),
  CREATEYEAR        INTEGER,
  CREATEMONTH       INTEGER,
  CREATEDAY         INTEGER,
  SENDTIME          datetime year to second,
  preDealDept       VARCHAR(50),
  preDealUserId 	VARCHAR(50),
  correlationKey 	VARCHAR(100),
  levelId           VARCHAR(100),
  parentLevelId     VARCHAR(100),   
  primary key(id)
);




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
values ('xls,txt,doc,jpg,gif', 'transprovincial', '跨省集客业务工单流程', 1024, '/sheet/transprovincial', 101, '402881043a066d0f013a066d0fe60011');


--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2020','0','0','建单人','1','994','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2021','0','0','网管二级审核人','1','994','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2022','0','0','地市工建人','1','994','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('2023','0','0','地市维护人','1','994','1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60012', '5801', '1', '新增工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60013', '5803', '0', '待处理工单', '58', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60014', '580301', '1', '待办工单', '5803', null, '/sheet/transprovincial/transprovincial.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0fe60015', '5807', '1', '工单查询', '58', null, '/sheet/transprovincial/transprovincial.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff60016', '5808', '1', '工单处理模板', '58', null, '/sheet/transprovincial/transprovincial.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff60017', '5809', '1', '工单模板管理', '58', null, '/sheet/transprovincial/transprovincial.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff60018', '5810', '1', '管理者工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff60019', '58', '0', '跨省集客业务工单流程', '10', null, '/sheet/transprovincial/transprovincial.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff6001a', '5802', '1', '草稿列表', '58', null, '/sheet/transprovincial/transprovincial.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff6001b', '5804', '0', '已处理工单', '58', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff6001c','580402','1','建立工单','5804', null, '/sheet/transprovincial/transprovincial.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff6001d', '580401', '1', '处理工单', '5804', null, '/sheet/transprovincial/transprovincial.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff6001e', '5805', '1', '已归档工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff6001f', '5806', '1', '已作废工单', '58', null, '/sheet/transprovincial/transprovincial.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff60020', '5811', '1', '超时时间设置', '58', null, '/sheet/overtimetip/overtimetip.do?flowName=Transprovincial', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881043a066d0f013a066d0ff60021', '5812', '1', '工单处理时限配置', '58', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=Transprovincial', '0', '0', 30);