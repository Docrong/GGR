

create table SECURITYOBJATTRIBUTE_LINK
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
  LINKEXTEND1			VARCHAR2(2000) ,
  LINKEXTEND2			VARCHAR2(2000) ,
  LINKEXTEND3			VARCHAR2(2000) ,
  LINKEXTEND4			VARCHAR2(2000) ,
  LINKEXTEND5			VARCHAR2(2000) ,
  LINKREPLYOBJ			VARCHAR2(1000) ,
  LINKREPLYEXP			VARCHAR2(1000) ,
  LINKREMARK			VARCHAR2(1000) ,
  LINKQUALITYRESULT			VARCHAR2(1000) ,
  LINKQUALITYVIEW			VARCHAR2(1000) ,
  LINKREJECT			VARCHAR2(1000) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table SECURITYOBJATTRIBUTE_LINK
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





create table SECURITYOBJATTRIBUTE_MAIN
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
  MAINPROCESSTYPE			VARCHAR2(2000) ,
  MAINCHANGETIME			DATE,
  MAINOPERATEID			VARCHAR2(2000) ,
  MAINOPERATENAME			VARCHAR2(2000) ,
  MAINOBJECTID			VARCHAR2(2000) ,
  MAINOBJECTNAME			VARCHAR2(2000) ,
  MAINATTRNAME			VARCHAR2(2000) ,
  MAINATTRID			VARCHAR2(2000) ,
  MAINATTRBEFORE			VARCHAR2(2000) ,
  MAINATTRAFTER			VARCHAR2(2000) ,
  MAINEXTEND1			VARCHAR2(2000) ,
  MAINEXTEND2			VARCHAR2(2000) ,
  MAINEXTEND3			VARCHAR2(2000) ,
  MAINEXTEND4			VARCHAR2(2000) ,
  MAINEXTEND5			VARCHAR2(2000) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table SECURITYOBJATTRIBUTE_MAIN add constraint SECURITYOBJATTRIBUTE_MAIN_PK primary key (id)  using index;








create table SECURITYOBJATTRIBUTE_TASK
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
alter table SECURITYOBJATTRIBUTE_TASK add constraint SECURITYOBJATTRIBUTE_TASK_PK primary key (id)  using index;




--创建索引
create index i_securityobjattribute_a on securityobjattribute_task (taskowner,createtime) TABLESPACE eomsdata ;
create index i_securityobjattribute_b on securityobjattribute_task (createtime)TABLESPACE eomsdata ;
create index i_securityobjattribute_c on securityobjattribute_task (taskstatus)TABLESPACE eomsdata ;
create index i_securityobjattribute_d on securityobjattribute_task (sheetkey)TABLESPACE eomsdata ;
create index i_securityobjattribute_e on securityobjattribute_main (sendtime,senduserid)TABLESPACE eomsdata ;
create index i_securityobjattribute_f on securityobjattribute_main (sheetid)TABLESPACE eomsdata ;
create index i_securityobjattribute_g on securityobjattribute_link (aiid)TABLESPACE eomsdata ;
create index i_securityobjattribute_h on securityobjattribute_link (mainid)TABLESPACE  eomsdata ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('42','SecurityObjAttribute','42','42','安全对象属性信息变更审批工单流程','iSecurityObjAttributeMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('30', '安全对象属性信息变更审批工单流程', '安全对象属性信息变更审批工单流程', 30);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'securityobjattribute', '安全对象属性信息变更审批工单流程', 1024, '/sheet/securityobjattribute', 30, '40287d8c5ba33494015ba334944a0000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4201','0','0','建单人','1','42','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4202','0','0','审批人','1','42','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4203','0','0','处理人','1','42','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('4204','0','0','质检员','1','42','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0001', '4401', '1', '新增工单', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0002', '4403', '0', '待处理工单', '44', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0003', '440301', '1', '待办工单', '4403', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0004', '4407', '1', '工单查询', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0005', '4408', '1', '工单处理模板', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=getDealTemplatesByUserId&&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0006', '4409', '1', '工单模板管理', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=getTemplatesByUserId&&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0007', '4410', '1', '管理者工单', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0008', '44', '0', '安全对象属性信息变更审批工单流程', '10', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a0009', '4402', '1', '草稿列表', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a000a', '4404', '0', '已处理工单', '44', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a000b','440402','1','建立工单','4404', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944a000c', '440401', '1', '处理工单', '4404', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944b000d', '4405', '1', '已归档工单', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944b000e', '4406', '1', '已作废工单', '44', null, '/sheet/securityobjattribute/securityobjattribute.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944b000f', '4411', '1', '超时时间设置', '44', null, '/sheet/overtimetip/overtimetip.do?flowName=SecurityObjAttribute', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba33494015ba334944b0010', '4412', '1', '工单处理时限配置', '44', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&&flowName=SecurityObjAttribute', '0', '0', 30);