
create table BRANCHINDEXREDUCTION_LINK
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
  LINKMAN			VARCHAR(20) ,
  LINKMANTELEPHONE			VARCHAR(30) ,
  LINKTRIALILLUSTRATE			VARCHAR(500) ,
  primary key(id)
);
 

create table BRANCHINDEXREDUCTION_MAIN
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
  MAINTITLE			VARCHAR(100) ,
  MAINSUBTRACTINDEXNAME			VARCHAR(20) ,
  SUBTRACTPROFESSIONAL			VARCHAR(30) ,
  MAINCITY			VARCHAR(10) ,
  MAINCOUNTY			VARCHAR(30) ,
  MAINCONFIRMINGPERSON			VARCHAR(20) ,
  MAINCONFIRMINGTELEPHONE			VARCHAR(20) ,
  MAINILLUSTRATE			VARCHAR(500) ,
  MAINRESERVEA			VARCHAR(100) ,
  MAINRESERVEB			VARCHAR(100) ,
  MAINRESERVEC			VARCHAR(100) ,
  MAINRESERVED			VARCHAR(100) ,
  MAINRESERVEE			VARCHAR(100) ,
  primary key(id)
);




create table BRANCHINDEXREDUCTION_TASK
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
create index i_branchindexreduction_a on branchindexreduction_task (taskowner,createtime) TABLESPACE SYSTEM ;
create index i_branchindexreduction_b on branchindexreduction_task (createtime)TABLESPACE SYSTEM ;    
create index i_branchindexreduction_c on branchindexreduction_task (taskstatus)TABLESPACE SYSTEM ;    
create index i_branchindexreduction_d on branchindexreduction_task (sheetkey)TABLESPACE SYSTEM ;
create index i_branchindexreduction_e on branchindexreduction_main (sendtime,senduserid)TABLESPACE SYSTEM ;
create index i_branchindexreduction_f on branchindexreduction_main (sheetid)TABLESPACE SYSTEM ;
create index i_branchindexreduction_g on branchindexreduction_link (aiid)TABLESPACE SYSTEM ;
create index i_branchindexreduction_h on branchindexreduction_link (mainid)TABLESPACE  SYSTEM ;


--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('995','BranchIndexReduction','995','995','分公司指标核减流程流程','iBranchIndexReductionMainManager');
 
--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('9221', '分公司指标核减流程流程', '分公司指标核减流程流程', 9221);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'branchindexreduction', '分公司指标核减流程流程', 1024, '/sheet/branchindexreduction', 9221, '4028811e5d8829ff015d882a002a0011');


--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('99501','0','0','建单人','1','995','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('99502','0','0','初审人','1','995','1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002a0012', '99999191401', '1', '新增工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0013', '99999191403', '0', '待处理工单', '999991914', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0014', '9999919140301', '1', '待办工单', '99999191403', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0015', '99999191407', '1', '工单查询', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0016', '99999191408', '1', '工单处理模板', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0017', '99999191409', '1', '工单模板管理', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0018', '99999191410', '1', '管理者工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0019', '999991914', '0', '分公司指标核减流程流程', '10', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b001a', '99999191402', '1', '草稿列表', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b001b', '99999191404', '0', '已处理工单', '999991914', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b001c','9999919140402','1','建立工单','99999191404', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b001d', '9999919140401', '1', '处理工单', '99999191404', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b001e', '99999191405', '1', '已归档工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b001f', '99999191406', '1', '已作废工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0020', '99999191411', '1', '超时时间设置', '999991914', null, '/sheet/overtimetip/overtimetip.do?flowName=BranchIndexReduction', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a002b0021', '99999191412', '1', '工单处理时限配置', '999991914', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=BranchIndexReduction', '0', '0', 30);