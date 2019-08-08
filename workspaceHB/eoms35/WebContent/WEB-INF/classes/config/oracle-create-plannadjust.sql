create table PLANNADJUST_LINK
(
    ID                   VARCHAR2(32) not null,
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
    SPAREONE             VARCHAR2(255),
    SPARETWO             VARCHAR2(255),
    SPARETHREE           VARCHAR2(255),
    CHECKRESULT          VARCHAR2(100),
    REASONREJECTION      VARCHAR2(2000),
    LINKSENDOBJECT       VARCHAR2(2000)
);
alter table PLANNADJUST_LINK
    add primary key (id)
        using index
            tablespace EXPLDB01
            pctfree 10
            initrans 2
            maxtrans 255
            storage
            (
            initial 1 M
            minextents 1
            maxextents unlimited
            );



create table PLANNADJUST_MAIN
(
    ID                    VARCHAR2(32) not null,
    SHEETID               VARCHAR2(50),
    TITLE                 VARCHAR2(100),
    SHEETACCEPTLIMIT      DATE,
    SHEETCOMPLETELIMIT    DATE,
    SENDTIME              DATE,
    SENDORGTYPE           VARCHAR2(25),
    SENDUSERID            VARCHAR2(30),
    SENDCONTACT           VARCHAR2(30),
    SHEETACCESSORIES      VARCHAR2(100),
    ENDTIME               DATE,
    ENDRESULT             VARCHAR2(30),
    STATUS                NUMBER,
    HOLDSTATISFIED        NUMBER,
    ENDUSERID             VARCHAR2(50),
    DELETED               NUMBER,
    PIID                  VARCHAR2(50),
    SHEETTEMPLATENAME     VARCHAR2(50),
    PARENTSHEETNAME       VARCHAR2(50),
    PARENTSHEETID         VARCHAR2(50),
    CORRELATIONKEY        VARCHAR2(50),
    PARENTCORRELATION     VARCHAR2(50),
    TODEPTID              VARCHAR2(50),
    SENDDEPTID            VARCHAR2(35),
    SENDROLEID            VARCHAR2(35),
    ENDROLEID             VARCHAR2(35),
    ENDDEPTID             VARCHAR2(35),
    TEMPLATEFLAG          VARCHAR2(20),
    CANCELREASON          VARCHAR2(100),
    SENDYEAR              NUMBER,
    SENDMONTH             NUMBER,
    SENDDAY               NUMBER,
    PARENTPHASENAME       VARCHAR2(50),
    INVOKEMODE            VARCHAR2(50),
    TERRITORIALBRANCH     VARCHAR2(100),
    ADMINISTRATIVEAREA    VARCHAR2(2000),
    APPLICATIONTIME       DATE,
    PLANNINGNUMBER        VARCHAR2(1000),
    PLANNINGSCHEME        VARCHAR2(2000),
    LONGITUDE             VARCHAR2(1000),
    LATITUDE              VARCHAR2(1000),
    NETTYPE               VARCHAR2(100),
    PROFESSIONAL          VARCHAR2(100),
    REASONADJUST          VARCHAR2(100),
    ILLUSTRATE            VARCHAR2(2000),
    PLANNINGSTARTTIME     DATE,
    PLANNINGENDTIME       DATE,
    BRIEFDESCRIPTION      VARCHAR2(2000),
    PLANNINGAREACOMPLAINT VARCHAR2(100),
    COVERAGEREQUIREMENT   VARCHAR2(100),
    GSMSIGNALLEVEL        VARCHAR2(200),
    WCDMASIGNALLEVEL      VARCHAR2(200),
    CDMASIGNALLEVEL       VARCHAR2(200),
    GSMSTATIONLOCATION    VARCHAR2(200),
    WCDMASTATIONLOCATION  VARCHAR2(200),
    CDMASTATIONLOCATION   VARCHAR2(200),
    GSMPLANNINGAREA       VARCHAR2(200),
    WCDMAPLANNINGAREA     VARCHAR2(200),
    CDMAPLANNINGAREA      VARCHAR2(200),
    SPAREONE              VARCHAR2(255),
    SPARETWO              VARCHAR2(255),
    SPARETHREE            VARCHAR2(255),
    SENDOBJECT            VARCHAR2(2000)
);
alter table PLANNADJUST_MAIN
    add constraint PLANNADJUST_MAIN_PK primary key (id) using index;



create table PLANNADJUST_TASK
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
    preDealUserId     VARCHAR2(50),
    correlationKey    VARCHAR2(100),
    levelId           VARCHAR2(100),
    parentLevelId     VARCHAR2(100)
);
alter table PLANNADJUST_TASK
    add constraint PLANNADJUST_TASK_PK primary key (id) using index;


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
        '402882f13f21c8b8013f21c8b8620000');

--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1970', '0', '0', '建单人', '1', '618', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1971', '0', '0', '规划站址调整管理员', '1', '618', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1972', '0', '0', '规划站址调整审核人', '1', '618', '1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620001', '9701', '1', '新增工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620002', '9703', '0', '待处理工单', '97', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620003', '970301', '1', '待办工单', '9703', null,
        '/sheet/plannadjust/plannadjust.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620004', '9707', '1', '工单查询', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620005', '9708', '1', '工单处理模板', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620006', '9709', '1', '工单模板管理', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620007', '9710', '1', '管理者工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620008', '97', '0', '规划站址调整申请流程流程', '10', null,
        '/sheet/plannadjust/plannadjust.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620009', '9702', '1', '草稿列表', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862000a', '9704', '0', '已处理工单', '97', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862000b', '970402', '1', '建立工单', '9704', null,
        '/sheet/plannadjust/plannadjust.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862000c', '970401', '1', '处理工单', '9704', null,
        '/sheet/plannadjust/plannadjust.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862000d', '9705', '1', '已归档工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862000e', '9706', '1', '已作废工单', '97', null,
        '/sheet/plannadjust/plannadjust.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b862000f', '9711', '1', '超时时间设置', '97', null,
        '/sheet/overtimetip/overtimetip.do?flowName=PlannAdjust', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882f13f21c8b8013f21c8b8620010', '9712', '1', '工单处理时限配置', '97', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=PlannAdjust', '0', '0', 30);