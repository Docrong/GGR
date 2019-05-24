

create table EQUIPMENTSECURITYRESPONSE_LINK
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
  LINKAUDITRESULT			VARCHAR2(1000) ,
  LINKAUDITOPINION			VARCHAR2(1000) ,
  LINKUPSTUTS			VARCHAR2(1000) ,
  LINKUPREASON			VARCHAR2(1000) ,
  LINKOPERATION			VARCHAR2(1000) ,
  LINKRELIEVESTATE			VARCHAR2(1000) ,
  MAINDEALTIME			DATE,
  LINKEXTEND1			VARCHAR2(2000) ,
  LINKEXTEND2			VARCHAR2(2000) ,
  LINKEXTEND3			VARCHAR2(2000) ,
  LINKEXTEND4			VARCHAR2(2000) ,
  LINKEXTEND5			VARCHAR2(2000) ,
  LINKREPLYOBJ			VARCHAR2(1000) ,
  LINKREPLYEXP			VARCHAR2(1000) ,
  LINKMEASURESTIME			DATE,
  LINKREMOVETIME			DATE,
  LINKFAULTCAUSE			VARCHAR2(1000) ,
  LINKDEALMEASURES			VARCHAR2(1000) ,
  LINKIFSOLUTION			VARCHAR2(1000) ,
  LINKREMARK			VARCHAR2(1000) ,
  LINKQUALITYRESULT			VARCHAR2(1000) ,
  LINKQUALITYVIEW			VARCHAR2(1000) ,
  LINKREJECT			VARCHAR2(1000) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table EQUIPMENTSECURITYRESPONSE_LINK
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





create table EQUIPMENTSECURITYRESPONSE_MAIN
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
  MAINISMPSHEETID			VARCHAR2(2000) ,
  MAINUSERID			VARCHAR2(2000) ,
  MAINACCEPTTIME			DATE,
  MAINDEALTIME			DATE,
  MAINPROVINCE			VARCHAR2(2000) ,
  MAINCITY			VARCHAR2(2000) ,
  MAINSENDWAY			VARCHAR2(2000) ,
  MAINALARMTITLE			VARCHAR2(2000) ,
  MAINALARMTIME			DATE,
  MAINALARMTYPE			VARCHAR2(2000) ,
  MAINALARMNAME			VARCHAR2(2000) ,
  MAINALARMID			VARCHAR2(2000) ,
  MAINORIGSOURCEID			VARCHAR2(2000) ,
  MAINORIGPURPOSEID			VARCHAR2(2000) ,
  MAINNOWSOURCEID			VARCHAR2(2000) ,
  MAINNOWPURPOSEID			VARCHAR2(2000) ,
  MAINALARMCONTENT			VARCHAR2(2000) ,
  MAINEXTEND1			VARCHAR2(2000) ,
  MAINEXTEND2			VARCHAR2(2000) ,
  MAINEXTEND3			VARCHAR2(2000) ,
  MAINEXTEND4			VARCHAR2(2000) ,
  MAINEXTEND5			VARCHAR2(2000) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table EQUIPMENTSECURITYRESPONSE_MAIN add constraint EQUIPMENTSECURITYRESPONSE_MAIN_PK primary key (id)  using index;








create table EQUIPMENTSECURITYRESPONSE_TASK
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
alter table EQUIPMENTSECURITYRESPONSE_TASK add constraint EQUIPMENTSECURITYRESPONSE_TASK_PK primary key (id)  using index;




--创建索引
create index i_equipmentsecurityresponse_a on equipmentsecurityresponse_task (taskowner,createtime) TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_b on equipmentsecurityresponse_task (createtime)TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_c on equipmentsecurityresponse_task (taskstatus)TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_d on equipmentsecurityresponse_task (sheetkey)TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_e on equipmentsecurityresponse_main (sendtime,senduserid)TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_f on equipmentsecurityresponse_main (sheetid)TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_g on equipmentsecurityresponse_link (aiid)TABLESPACE eomsdata ;
create index i_equipmentsecurityresponse_h on equipmentsecurityresponse_link (mainid)TABLESPACE  eomsdata ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('44','EquipmentSecurityResponse','44','44','设备互联核查安全告警响应工单流程','iEquipmentSecurityResponseMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('32', '设备互联核查安全告警响应工单流程', '设备互联核查安全告警响应工单流程', 32);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'equipmentsecurityresponse', '设备互联核查安全告警响应工单流程', 1024, '/sheet/equipmentsecurityresponse', 32, '40287d8c5ba336af015ba336af820000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4401','0','0','建单人','1','44','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4402','0','0','审批人','1','44','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4403','0','0','处理人','1','44','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4404','0','0','质检员','1','44','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820001', '4601', '1', '新增工单', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820002', '4603', '0', '待处理工单', '46', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820003', '460301', '1', '待办工单', '4603', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820004', '4607', '1', '工单查询', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820005', '4608', '1', '工单处理模板', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=getDealTemplatesByUserId&&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820006', '4609', '1', '工单模板管理', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=getTemplatesByUserId&&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820007', '4610', '1', '管理者工单', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820008', '46', '0', '设备互联核查安全告警响应工单流程', '10', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af820009', '4602', '1', '草稿列表', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af82000a', '4604', '0', '已处理工单', '46', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af82000b','460402','1','建立工单','4604', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af82000c', '460401', '1', '处理工单', '4604', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af82000d', '4605', '1', '已归档工单', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af82000e', '4606', '1', '已作废工单', '46', null, '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af82000f', '4611', '1', '超时时间设置', '46', null, '/sheet/overtimetip/overtimetip.do?flowName=EquipmentSecurityResponse', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af830010', '4612', '1', '工单处理时限配置', '46', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&&flowName=EquipmentSecurityResponse', '0', '0', 30);