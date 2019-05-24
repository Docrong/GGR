
create table COMMONFAULTCORRIGENDUM_LINK
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
  LINKMEASURES			VARCHAR(255) ,
  LINKSPAREONE			VARCHAR(255) ,
  LINKSPARETWO			VARCHAR(255) ,
  LINKSPARETHREE			VARCHAR(255) ,
  LINKSPAREFOUR			VARCHAR(255) ,
  LINKSPAREFIVE			VARCHAR(255) ,
  primary key(id)
);
 

create table COMMONFAULTCORRIGENDUM_MAIN
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
  MAINCOMMONFAULTSHEETID			VARCHAR(2000) ,
  MAINCOMMONFAULTNETNAME			VARCHAR(2000) ,
  MAINCORRIGENDUMTYPEONE			VARCHAR(2000) ,
  MAINCORRIGENDUMTYPETWO			VARCHAR(2000) ,
  MAINCORRIGENDUMTYPETHREE			VARCHAR(2000) ,
  MAINFAULTGENERANTCITY			VARCHAR(2000) ,
  MAINNETTYPE			VARCHAR(2000) ,
  MAINCITY			VARCHAR(2000) ,
  MAINCOUNTY			VARCHAR(2000) ,
  MAINSAVETIME			datetime year to second,
  MAINCREATEUSERID			VARCHAR(2000) ,
  MAINCREATEDEPTID			VARCHAR(2000) ,
  MAINIFAUTOTRAN			VARCHAR(2000) ,
  MAINTEAMROLEID			VARCHAR(2000) ,
  MAINCCOBJECT			VARCHAR(2000) ,
  MAINTEAMROLEID			VARCHAR(2000) ,
  MAINCCOBJECT			VARCHAR(2000) ,
  MAINSPAREONE			VARCHAR(255) ,
  MAINSPARETWO			VARCHAR(255) ,
  MAINSPARETHREE			VARCHAR(255) ,
  MAINSPAREFOUR			VARCHAR(255) ,
  MAINSPAREFIVE			VARCHAR(255) ,
  primary key(id)
);




create table COMMONFAULTCORRIGENDUM_TASK
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
values ('xls,txt,doc,jpg,gif', 'commonfaultcorrigendum', '故障工单勘误流程流程', 1024, '/sheet/commonfaultcorrigendum', 105, '402881be48bf6ff00148bf6ff1590011');


--插入角色
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('8005105','0','0','建单人','1','620','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('8005106','0','0','集中监控维护班组','1','620','1');
insert into taw_system_role(role_id,deleted,parent_id,role_name,roletype_id,workflow_flag,leaf) values('191','0','0','故障一级处理组','1','620','1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590012', '9701', '1', '新增工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590013', '9703', '0', '待处理工单', '97', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590014', '970301', '1', '待办工单', '9703', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590015', '9707', '1', '工单查询', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590016', '9708', '1', '工单处理模板', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590017', '9709', '1', '工单模板管理', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590018', '9710', '1', '管理者工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590019', '97', '0', '故障工单勘误流程流程', '10', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159001a', '9702', '1', '草稿列表', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159001b', '9704', '0', '已处理工单', '97', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159001c','970402','1','建立工单','9704', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showOwnStarterList','0','0',2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159001d', '970401', '1', '处理工单', '9704', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159001e', '9705', '1', '已归档工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff159001f', '9706', '1', '已作废工单', '97', null, '/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590020', '9711', '1', '超时时间设置', '97', null, '/sheet/overtimetip/overtimetip.do?flowName=CommonfaultCorrigendum', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402881be48bf6ff00148bf6ff1590021', '9712', '1', '工单处理时限配置', '97', null, '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=CommonfaultCorrigendum', '0', '0', 30);