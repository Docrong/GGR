create table PLANNADJUST_LINK
(
    ID                   VARCHAR(32) not null,
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
    LINKSENDOBJECT       CHAR(4000),
    SPAREONE             VARCHAR(255),
    SPARETWO             VARCHAR(255),
    SPARETHREE           VARCHAR(255),
    CHECKRESULT          VARCHAR(100),
    REASONREJECTION      VARCHAR(2000),
    primary key (id)
);


create table PLANNADJUST_MAIN
(
    ID                    VARCHAR(32) not null,
    SHEETID               VARCHAR(50),
    TITLE                 VARCHAR(100),
    SHEETACCEPTLIMIT      datetime year to second,
    SHEETCOMPLETELIMIT    datetime year to second,
    SENDTIME              datetime year to second,
    SENDORGTYPE           VARCHAR(25),
    SENDUSERID            VARCHAR(30),
    SENDCONTACT           VARCHAR(30),
    SHEETACCESSORIES      VARCHAR(100),
    ENDTIME               datetime year to second,
    ENDRESULT             VARCHAR(30),
    STATUS                INTEGER,
    HOLDSTATISFIED        INTEGER,
    ENDUSERID             VARCHAR(50),
    DELETED               INTEGER,
    PIID                  VARCHAR(50),
    SHEETTEMPLATENAME     VARCHAR(50),
    PARENTSHEETNAME       VARCHAR(50),
    PARENTSHEETID         VARCHAR(50),
    CORRELATIONKEY        VARCHAR(50),
    PARENTCORRELATION     VARCHAR(50),
    TODEPTID              VARCHAR(50),
    SENDDEPTID            VARCHAR(35),
    SENDROLEID            VARCHAR(35),
    ENDROLEID             VARCHAR(35),
    ENDDEPTID             VARCHAR(35),
    TEMPLATEFLAG          VARCHAR(20),
    CANCELREASON          VARCHAR(100),
    SENDYEAR              INTEGER,
    SENDMONTH             INTEGER,
    SENDDAY               INTEGER,
    PARENTPHASENAME       VARCHAR(50),
    INVOKEMODE            VARCHAR(50),
    SENDOBJECT            CHAR(4000),
    TERRITORIALBRANCH     VARCHAR(100),
    ADMINISTRATIVEAREA    VARCHAR(2000),
    APPLICATIONTIME       datetime year to second,
    PLANNINGNUMBER        VARCHAR(1000),
    PLANNINGSCHEME        VARCHAR(2000),
    LONGITUDE             VARCHAR(1000),
    LATITUDE              VARCHAR(1000),
    NETTYPE               VARCHAR(100),
    PROFESSIONAL          VARCHAR(100),
    REASONADJUST          VARCHAR(100),
    ILLUSTRATE            VARCHAR(2000),
    PLANNINGSTARTTIME     datetime year to second,
    PLANNINGENDTIME       datetime year to second,
    BRIEFDESCRIPTION      VARCHAR(2000),
    PLANNINGAREACOMPLAINT VARCHAR(100),
    COVERAGEREQUIREMENT   VARCHAR(100),
    GSMSIGNALLEVEL        VARCHAR(200),
    WCDMASIGNALLEVEL      VARCHAR(200),
    CDMASIGNALLEVEL       VARCHAR(200),
    GSMSTATIONLOCATION    VARCHAR(200),
    WCDMASTATIONLOCATION  VARCHAR(200),
    CDMASTATIONLOCATION   VARCHAR(200),
    GSMPLANNINGAREA       VARCHAR(200),
    WCDMAPLANNINGAREA     VARCHAR(200),
    CDMAPLANNINGAREA      VARCHAR(200),
    SPAREONE              VARCHAR(255),
    SPARETWO              VARCHAR(255),
    SPARETHREE            VARCHAR(255),
    primary key (id)
);



create table PLANNADJUST_TASK
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
    preDealUserId     VARCHAR(50),
    correlationKey    VARCHAR(100),
    levelId           VARCHAR(100),
    parentLevelId     VARCHAR(100),
    primary key (id)
);


--创建索引
create index i_plannadjust_a on plannadjust_task (taskowner, createtime) TABLESPACE EOMSDATAV35;
create index i_plannadjust_b on plannadjust_task (createtime) TABLESPACE EOMSDATAV35;
create index i_plannadjust_c on plannadjust_task (taskstatus) TABLESPACE EOMSDATAV35;
create index i_plannadjust_d on plannadjust_task (sheetkey) TABLESPACE EOMSDATAV35;
create index i_plannadjust_e on plannadjust_main (sendtime, senduserid) TABLESPACE EOMSDATAV35;
create index i_plannadjust_f on plannadjust_main (sheetid) TABLESPACE EOMSDATAV35;
create index i_plannadjust_g on plannadjust_link (aiid) TABLESPACE EOMSDATAV35;
create index i_plannadjust_h on plannadjust_link (mainid) TABLESPACE EOMSDATAV35;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('618', 'PlannAdjust', '618', '618', '规划站址调整申请流程流程', 'iPlannAdjustMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('105', '规划站址调整申请流程流程', '规划站址调整申请流程流程', 105);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'plannadjust', '规划站址调整申请流程流程', 1024, '/sheet/plannadjust', 105,
        '402882f13f21c8b8013f21c8b8620011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1970', '0', '0', '建单人', '1', '618', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1971', '0', '0', '规划站址调整管理员', '1', '618', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1972', '0', '0', '规划站址调整审核人', '1', '618', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620012', '9701', '1', '新增工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620013', '9703', '0', '待处理工单', '97', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620014', '970301', '1', '待办工单', '9703', null,
        '/sheet/plannadjust/plannadjust.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620015', '9707', '1', '工单查询', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620016', '9708', '1', '工单处理模板', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620017', '9709', '1', '工单模板管理', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620018', '9710', '1', '管理者工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620019', '97', '0', '规划站址调整申请流程流程', '10', null,
        '/sheet/plannadjust/plannadjust.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862001a', '9702', '1', '草稿列表', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862001b', '9704', '0', '已处理工单', '97', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862001c', '970402', '1', '建立工单', '9704', null,
        '/sheet/plannadjust/plannadjust.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862001d', '970401', '1', '处理工单', '9704', null,
        '/sheet/plannadjust/plannadjust.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862001e', '9705', '1', '已归档工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862001f', '9706', '1', '已作废工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620020', '9711', '1', '超时时间设置', '97', null,
        '/sheet/overtimetip/overtimetip.do?flowName=PlannAdjust', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620021', '9712', '1', '工单处理时限配置', '97', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=PlannAdjust', '0', '0', 30);