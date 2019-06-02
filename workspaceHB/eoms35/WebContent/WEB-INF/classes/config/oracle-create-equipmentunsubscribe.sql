

create table EQUIPMENTUNSUBSCRIBE_LINK
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
  LINKDESCR			VARCHAR2(2000) ,
  LINKRESERVED1			VARCHAR2(1000) ,
  LINKRESERVED2			VARCHAR2(1000) ,
  LINKRESERVED3			VARCHAR2(1000) ,
  LINKRESERVED4			VARCHAR2(1000) ,
  LINKRESERVED5			VARCHAR2(1000) ,
  LINKEQUIPMENTTYPE			VARCHAR2(1000) ,
  LINKEQUIPMENTNUM			VARCHAR2(1000) ,
  LINKEQUIPMENTTIME			DATE,
  LINKSCENEPHOTO			VARCHAR2(1000) ,
  LINKRETURNPHOTO			VARCHAR2(1000) ,
  LINKPUTPHOTO			VARCHAR2(1000) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table EQUIPMENTUNSUBSCRIBE_LINK
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





create table EQUIPMENTUNSUBSCRIBE_MAIN
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
  MAINCOMPLAINTSHEETID			VARCHAR2(1000) ,
  MAINCOMPLAINTER			VARCHAR2(1000) ,
  MAINCOMPLAINTNUM			VARCHAR2(1000) ,
  MAINUSERSTAR			VARCHAR2(1000) ,
  MAINEQUIPMENTTYPE			VARCHAR2(1000) ,
  MAINCITY			VARCHAR2(1000) ,
  MAINMINIID			VARCHAR2(1000) ,
  MAINADDRESS			VARCHAR2(1000) ,
  MAINCOMPLAINTREASONS			VARCHAR2(1000) ,
  MAINDISMANTLEREASONS			VARCHAR2(1000) ,
  MAINDESCR			VARCHAR2(1000) ,
  MAINTYPICALSCENARIO			VARCHAR2(1000) ,
  MAINCHILDSCENARIO			VARCHAR2(1000) ,
  MAINISEQUIPMENTFEE			VARCHAR2(1000) ,
  MAINSIGNPHOTO			VARCHAR2(1000) ,
  MAINRESERVED1			VARCHAR2(1000) ,
  MAINRESERVED2			VARCHAR2(1000) ,
  MAINRESERVED3			VARCHAR2(1000) ,
  MAINRESERVED4			VARCHAR2(1000) ,
  MAINRESERVED5			VARCHAR2(1000) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table EQUIPMENTUNSUBSCRIBE_MAIN add constraint EQUIPMENTUNSUBSCRIBE_MAIN_PK primary key (id)  using index;








create table EQUIPMENTUNSUBSCRIBE_TASK
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
alter table EQUIPMENTUNSUBSCRIBE_TASK add constraint EQUIPMENTUNSUBSCRIBE_TASK_PK primary key (id)  using index;




--创建索引
create index i_equipmentunsubscribe_a on equipmentunsubscribe_task (taskowner,createtime) TABLESPACE users ;
create index i_equipmentunsubscribe_b on equipmentunsubscribe_task (createtime)TABLESPACE users ;
create index i_equipmentunsubscribe_c on equipmentunsubscribe_task (taskstatus)TABLESPACE users ;
create index i_equipmentunsubscribe_d on equipmentunsubscribe_task (sheetkey)TABLESPACE users ;
create index i_equipmentunsubscribe_e on equipmentunsubscribe_main (sendtime,senduserid)TABLESPACE users ;
create index i_equipmentunsubscribe_f on equipmentunsubscribe_main (sheetid)TABLESPACE users ;
create index i_equipmentunsubscribe_g on equipmentunsubscribe_link (aiid)TABLESPACE users ;
create index i_equipmentunsubscribe_h on equipmentunsubscribe_link (mainid)TABLESPACE  users ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('25','EquipmentUnsubscribe','25','25','设备退订流程流程','iEquipmentUnsubscribeMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('34', '设备退订流程流程', '设备退订流程流程', 34);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'equipmentunsubscribe', '设备退订流程流程', 1024, '/sheet/equipmentunsubscribe', 34, '297e589966577f7d0166577f7e000000');

--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('149','0','0','建单人','1','25','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('150','0','0','分公司网优中心主任','1','25','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('151','0','0','分公司代维人员','1','25','1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000001', '199901', '1', '新增工单', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000002', '199903', '0', '待处理工单', '1999', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000003', '19990301', '1', '待办工单', '199903', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000004', '199907', '1', '工单查询', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000005', '199908', '1', '工单处理模板', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=getDealTemplatesByUserId&&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000006', '199909', '1', '工单模板管理', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=getTemplatesByUserId&&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000007', '199910', '1', '管理者工单', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000008', '1999', '0', '设备退订流程流程', '10', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000009', '199902', '1', '草稿列表', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e00000a', '199904', '0', '已处理工单', '1999', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e00000b','19990402','1','建立工单','199904', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e00000c', '19990401', '1', '处理工单', '199904', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e00000d', '199905', '1', '已归档工单', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e00000e', '199906', '1', '已作废工单', '1999', null, '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e00000f', '199911', '1', '超时时间设置', '1999', null, '/sheet/overtimetip/overtimetip.do?flowName=EquipmentUnsubscribe', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e000010', '199912', '1', '工单处理时限配置', '1999', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&&flowName=EquipmentUnsubscribe', '0', '0', 30);