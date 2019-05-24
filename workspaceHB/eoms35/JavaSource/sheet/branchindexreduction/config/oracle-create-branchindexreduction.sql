

create table BRANCHINDEXREDUCTION_LINK
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
  LINKMAN			VARCHAR2(20) ,
  LINKMANTELEPHONE			VARCHAR2(30) ,
  LINKTRIALILLUSTRATE			VARCHAR2(500) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table BRANCHINDEXREDUCTION_LINK
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





create table BRANCHINDEXREDUCTION_MAIN
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
  MAINTITLE			VARCHAR2(100) ,
  MAINSUBTRACTINDEXNAME			VARCHAR2(20) ,
  SUBTRACTPROFESSIONAL			VARCHAR2(30) ,
  MAINCITY			VARCHAR2(10) ,
  MAINCOUNTY			VARCHAR2(30) ,
  MAINCONFIRMINGPERSON			VARCHAR2(20) ,
  MAINCONFIRMINGTELEPHONE			VARCHAR2(20) ,
  MAINILLUSTRATE			VARCHAR2(500) ,
  MAINRESERVEA			VARCHAR2(100) ,
  MAINRESERVEB			VARCHAR2(100) ,
  MAINRESERVEC			VARCHAR2(100) ,
  MAINRESERVED			VARCHAR2(100) ,
  MAINRESERVEE			VARCHAR2(100) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table BRANCHINDEXREDUCTION_MAIN add constraint BRANCHINDEXREDUCTION_MAIN_PK primary key (id)  using index;








create table BRANCHINDEXREDUCTION_TASK
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
alter table BRANCHINDEXREDUCTION_TASK add constraint BRANCHINDEXREDUCTION_TASK_PK primary key (id)  using index;




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
values ('xls,txt,doc,jpg,gif', 'branchindexreduction', '分公司指标核减流程流程', 1024, '/sheet/branchindexreduction', 9221, '4028811e5d8829ff015d882a00230000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('99501','0','0','地市人员','1','995','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('99502','0','0','初审人','1','995','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230001', '99999191401', '1', '新增工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230002', '99999191403', '0', '待处理工单', '999991914', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230003', '9999919140301', '1', '待办工单', '99999191403', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230004', '99999191407', '1', '工单查询', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230005', '99999191408', '1', '工单处理模板', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230006', '99999191409', '1', '工单模板管理', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230007', '99999191410', '1', '管理者工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230008', '999991914', '0', '分公司指标核减流程流程', '10', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230009', '99999191402', '1', '草稿列表', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a0023000a', '99999191404', '0', '已处理工单', '999991914', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a0023000b','9999919140402','1','建立工单','99999191404', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a0023000c', '9999919140401', '1', '处理工单', '99999191404', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a0023000d', '99999191405', '1', '已归档工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a0023000e', '99999191406', '1', '已作废工单', '999991914', null, '/sheet/branchindexreduction/branchindexreduction.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a0023000f', '99999191411', '1', '超时时间设置', '999991914', null, '/sheet/overtimetip/overtimetip.do?flowName=BranchIndexReduction', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('4028811e5d8829ff015d882a00230010', '99999191412', '1', '工单处理时限配置', '999991914', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=BranchIndexReduction', '0', '0', 30);