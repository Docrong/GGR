

create table COMMONFAULTCORRIGENDUM_LINK
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
  LINKMEASURES			VARCHAR2(255) ,
  LINKSPAREONE			VARCHAR2(255) ,
  LINKSPARETWO			VARCHAR2(255) ,
  LINKSPARETHREE			VARCHAR2(255) ,
  LINKSPAREFOUR			VARCHAR2(255) ,
  LINKSPAREFIVE			VARCHAR2(255) ,
  LINKSENDOBJECT           VARCHAR2(2000)
);
alter table COMMONFAULTCORRIGENDUM_LINK
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





create table COMMONFAULTCORRIGENDUM_MAIN
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
  MAINCOMMONFAULTSHEETID			VARCHAR2(2000) ,
  MAINCOMMONFAULTNETNAME			VARCHAR2(2000) ,
  MAINCORRIGENDUMTYPEONE			VARCHAR2(2000) ,
  MAINCORRIGENDUMTYPETWO			VARCHAR2(2000) ,
  MAINCORRIGENDUMTYPETHREE			VARCHAR2(2000) ,
  MAINFAULTGENERANTCITY			VARCHAR2(2000) ,
  MAINNETTYPE			VARCHAR2(2000) ,
  MAINCITY			VARCHAR2(2000) ,
  MAINCOUNTY			VARCHAR2(2000) ,
  MAINSAVETIME			DATE,
  MAINCREATEUSERID			VARCHAR2(2000) ,
  MAINCREATEDEPTID			VARCHAR2(2000) ,
  MAINIFAUTOTRAN			VARCHAR2(2000) ,
  MAINTEAMROLEID			VARCHAR2(2000) ,
  MAINCCOBJECT			VARCHAR2(2000) ,
  MAINTEAMROLEID			VARCHAR2(2000) ,
  MAINCCOBJECT			VARCHAR2(2000) ,
  MAINSPAREONE			VARCHAR2(255) ,
  MAINSPARETWO			VARCHAR2(255) ,
  MAINSPARETHREE			VARCHAR2(255) ,
  MAINSPAREFOUR			VARCHAR2(255) ,
  MAINSPAREFIVE			VARCHAR2(255) ,
  SENDOBJECT           VARCHAR2(2000)
);
alter table COMMONFAULTCORRIGENDUM_MAIN add constraint COMMONFAULTCORRIGENDUM_MAIN_PK primary key (id)  using index; 








create table COMMONFAULTCORRIGENDUM_TASK
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
alter table COMMONFAULTCORRIGENDUM_TASK add constraint COMMONFAULTCORRIGENDUM_TASK_PK primary key (id)  using index; 




--创建索引
create index i_commonfaultcorrigendum_a on commonfaultcorrigendum_task (taskowner,createtime) TABLESPACE EOMSDATA ;
create index i_commonfaultcorrigendum_b on commonfaultcorrigendum_task (createtime)TABLESPACE EOMSDATA ;    
create index i_commonfaultcorrigendum_c on commonfaultcorrigendum_task (taskstatus)TABLESPACE EOMSDATA ;    
create index i_commonfaultcorrigendum_d on commonfaultcorrigendum_task (sheetkey)TABLESPACE EOMSDATA ;
create index i_commonfaultcorrigendum_e on commonfaultcorrigendum_main (sendtime,senduserid)TABLESPACE EOMSDATA ;
create index i_commonfaultcorrigendum_f on commonfaultcorrigendum_main (sheetid)TABLESPACE EOMSDATA ;
create index i_commonfaultcorrigendum_g on commonfaultcorrigendum_link (aiid)TABLESPACE EOMSDATA ;
create index i_commonfaultcorrigendum_h on commonfaultcorrigendum_link (mainid)TABLESPACE  EOMSDATA ;



--插入流程SQL语句
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid)
 values('620','CommonfaultCorrigendum','620','620','故障工单勘误流程流程','iCommonfaultCorrigendumMainManager');
 
--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('105', '故障工单勘误流程流程', '故障工单勘误流程流程', 105);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'commonfaultcorrigendum', '故障工单勘误流程流程', 1024, '/sheet/commonfaultcorrigendum', 105, '402881be48bf6ff00148bf6ff1590000');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590001', '9701', '1', '新增工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590002', '9703', '0', '待处理工单', '97', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590003', '970301', '1', '待办工单', '9703', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590004', '9707', '1', '工单查询', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590005', '9708', '1', '工单处理模板', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590006', '9709', '1', '工单模板管理', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590007', '9710', '1', '管理者工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590008', '97', '0', '故障工单勘误流程流程', '10', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590009', '9702', '1', '草稿列表', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159000a', '9704', '0', '已处理工单', '97', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159000b','970402','1','建立工单','9704', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159000c', '970401', '1', '处理工单', '9704', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159000d', '9705', '1', '已归档工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159000e', '9706', '1', '已作废工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159000f', '9711', '1', '超时时间设置', '97', null, '/sheet/overtimetip/overtimetip.do?flowName=CommonfaultCorrigendum', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590010', '9712', '1', '工单处理时限配置', '97', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=CommonfaultCorrigendum', '0', '0', 30);