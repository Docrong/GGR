

create table PLANNCONFIRM_LINK
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
  SPAREONE			VARCHAR2(255) ,
  SPARETWO			VARCHAR2(255) ,
  SPARETHREE			VARCHAR2(255) ,
  VALIDATIONRESULT			VARCHAR2(100) ,
  PERCENTAGREEMENT			VARCHAR2(1000) ,
  GROUPDISCUSSION			VARCHAR2(100) ,
  PLANNINGAZIMUTH			VARCHAR2(1000) ,
  ELECTRONICANGLE			VARCHAR2(1000) ,
  MECHANICALANGLE			VARCHAR2(1000) ,
  PLANNINGCONFIGURATION			VARCHAR2(1000) ,
  SUPPLEMENTSCHEME			VARCHAR2(100) ,
  PARTICULARCASE			VARCHAR2(2000) ,
  CHECKPEOPLE			VARCHAR2(2000) ,
  CONTACTINFORMATION			VARCHAR2(2000) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table PLANNCONFIRM_LINK
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





create table PLANNCONFIRM_MAIN
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
  PROFESSIONAL			VARCHAR2(100) ,
  NETWORKTYPEONE			VARCHAR2(100) ,
  NETWORKTYPETWO			VARCHAR2(100) ,
  NETWORKTYPETHREE			VARCHAR2(100) ,
  TASKTYPE			VARCHAR2(100) ,
  TASKDESCRIPTION			VARCHAR2(2000) ,
  SELECTIONTIME			DATE,
  PLANNINGNUMBER			VARCHAR2(1000) ,
  TERRITORIALBRANCH			VARCHAR2(100) ,
  STATIONSITE			VARCHAR2(1000) ,
  SCENETYPE			VARCHAR2(1000) ,
  LONGITUDE			VARCHAR2(1000) ,
  LATITUDE			VARCHAR2(1000) ,
  BUILDINGTYPE			VARCHAR2(1000) ,
  BUILDINGNUMBER			VARCHAR2(1000) ,
  ANTENNAHEIGHT			VARCHAR2(1000) ,
  PLATFORMTYPE			VARCHAR2(1000) ,
  ANTENNATYPE			VARCHAR2(100) ,
  ANTENNAMODEL			VARCHAR2(1000) ,
  REQUIREMENT			VARCHAR2(100) ,
  HIGHREQUIREMENT			VARCHAR2(100) ,
  EXISTENCEBARRIER			VARCHAR2(100) ,
  OTHERCIRCUMSTANCE			VARCHAR2(2000) ,
  SPAREONE			VARCHAR2(255) ,
  SPARETWO			VARCHAR2(255) ,
  SPARETHREE			VARCHAR2(255) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table PLANNCONFIRM_MAIN add constraint PLANNCONFIRM_MAIN_PK primary key (id)  using index; 








create table PLANNCONFIRM_TASK
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
alter table PLANNCONFIRM_TASK add constraint PLANNCONFIRM_TASK_PK primary key (id)  using index; 




--创建索引
create index i_plannconfirm_a on plannconfirm_task (taskowner,createtime) TABLESPACE EOMSDATAV35 ;
create index i_plannconfirm_b on plannconfirm_task (createtime)TABLESPACE EOMSDATAV35 ;    
create index i_plannconfirm_c on plannconfirm_task (taskstatus)TABLESPACE EOMSDATAV35 ;    
create index i_plannconfirm_d on plannconfirm_task (sheetkey)TABLESPACE EOMSDATAV35 ;
create index i_plannconfirm_e on plannconfirm_main (sendtime,senduserid)TABLESPACE EOMSDATAV35 ;
create index i_plannconfirm_f on plannconfirm_main (sheetid)TABLESPACE EOMSDATAV35 ;
create index i_plannconfirm_g on plannconfirm_link (aiid)TABLESPACE EOMSDATAV35 ;
create index i_plannconfirm_h on plannconfirm_link (mainid)TABLESPACE  EOMSDATAV35 ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('617','PlannConfirm','617','617','规划站址确认申请流程流程','iPlannConfirmMainManager');
 
--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('104', '规划站址确认申请流程流程', '规划站址确认申请流程流程', 104);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'plannconfirm', '规划站址确认申请流程流程', 1024, '/sheet/plannconfirm', 104, '402882e83f18c2fa013f18c2fa9f0000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('1870','0','0','建单人','1','617','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('1871','0','0','规划站址确认管理员','1','617','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('1872','0','0','规划站址确认审核人','1','617','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0001', '9601', '1', '新增工单', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0002', '9603', '0', '待处理工单', '96', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0003', '960301', '1', '待办工单', '9603', null, '/sheet/plannconfirm/plannconfirm.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0004', '9607', '1', '工单查询', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0005', '9608', '1', '工单处理模板', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0006', '9609', '1', '工单模板管理', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0007', '9610', '1', '管理者工单', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0008', '96', '0', '规划站址确认申请流程流程', '10', null, '/sheet/plannconfirm/plannconfirm.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0009', '9602', '1', '草稿列表', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f000a', '9604', '0', '已处理工单', '96', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f000b','960402','1','建立工单','9604', null, '/sheet/plannconfirm/plannconfirm.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f000c', '960401', '1', '处理工单', '9604', null, '/sheet/plannconfirm/plannconfirm.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f000d', '9605', '1', '已归档工单', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f000e', '9606', '1', '已作废工单', '96', null, '/sheet/plannconfirm/plannconfirm.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f000f', '9611', '1', '超时时间设置', '96', null, '/sheet/overtimetip/overtimetip.do?flowName=PlannConfirm', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0010', '9612', '1', '工单处理时限配置', '96', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=PlannConfirm', '0', '0', 30);