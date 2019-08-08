create table MAINTENANCESERVICE_LINK
(
    ID                       VARCHAR(32) not null,
    MAINID                   VARCHAR(50),
    NODEACCEPTLIMIT          datetime year to second,
    NODECOMPLETELIMIT        datetime year to second,
    OPERATETYPE              INTEGER,
    OPERATETIME              datetime year to second,
    OPERATEUSERID            VARCHAR(30),
    OPERATEORGTYPE           VARCHAR(10),
    OPERATERCONTACT          VARCHAR(50),
    TOORGTYPE                INTEGER,
    TOORGUSERID              VARCHAR(50),
    TOROLEID                 INTEGER,
    ACCEPTFLAG               INTEGER,
    ACCEPTTIME               datetime year to second,
    COMPLETEFLAG             INTEGER,
    COMPLETETIME             datetime year to second,
    PRELINKID                VARCHAR(50),
    PARENTLINKID             VARCHAR(50),
    FIRSTLINKID              VARCHAR(50),
    PIID                     VARCHAR(50),
    AIID                     VARCHAR(50),
    ACTIVETEMPLATEID         VARCHAR(50),
    NODETEMPLATENAME         VARCHAR(50),
    NODEACCESSORIES          VARCHAR(50),
    TOORGDEPTID              VARCHAR(35),
    TOORGROLEID              VARCHAR(35),
    OPERATEROLEID            VARCHAR(35),
    OPERATEDEPTID            VARCHAR(35),
    CORRELATIONKEY           VARCHAR(50),
    TEMPLATEFLAG             VARCHAR(20),
    TEMPLATENAME             VARCHAR(100),
    TEMPLATECREATEUSERID     VARCHAR(100),
    TRANSFERREASON           VARCHAR(200),
    REMARK                   VARCHAR(200),
    OPERATEYEAR              INTEGER,
    OPERATEMONTH             INTEGER,
    OPERATEDAY               INTEGER,
    LINKSENDOBJECT           CHAR(4000),
    LINKEXPLAIN              VARCHAR(2000),
    LINKDESCRIBE             VARCHAR(2000),
    LINKSPAREONE             VARCHAR(2000),
    LINKSPARETWO             VARCHAR(2000),
    LINKSPARETHREE           VARCHAR(2000),
    LINKSPAREFOUR            VARCHAR(2000),
    LINKSPAREFIVE            VARCHAR(2000),
    LINKFILINGREASON         VARCHAR(2000),
    LINKTYPE                 VARCHAR(2000),
    LINKSERIALNUMBER         VARCHAR(2000),
    LINKDEVICENAME           VARCHAR(2000),
    LINKMANUFACTURERS        VARCHAR(2000),
    LINKSYSTEMNAME           VARCHAR(2000),
    LINKEQUIPMENTTYPE        VARCHAR(2000),
    LINKDEVICENUMBER         VARCHAR(2000),
    LINKACCESSTIME           VARCHAR(2000),
    LINKOUTDATE              VARCHAR(2000),
    LINKINTERFACETYPE        VARCHAR(2000),
    LINKSOFTWARE             VARCHAR(2000),
    LINKINTEGRATORNAME       VARCHAR(2000),
    LINKLEVELSERVICE         VARCHAR(2000),
    LINKPHYSICALLOCATION     VARCHAR(2000),
    LINKREMARKS              VARCHAR(2000),
    LINKCONTACTS             VARCHAR(2000),
    LINKCONTACTINFORMATION   VARCHAR(2000),
    LINKCOMPANY              VARCHAR(2000),
    LINKREASONREJECTION      VARCHAR(2000),
    LINKRESULT               VARCHAR(2000),
    LINKAPPROVALOPINION      VARCHAR(2000),
    LINKSERVICECONTENT       VARCHAR(2000),
    LINKSCORE                VARCHAR(2000),
    LINKTIMELYPROCESSING     VARCHAR(2000),
    LINKMAINTENANCESTARTTIME VARCHAR(2000),
    LINKMAINTENANCEENDTIME   VARCHAR(2000),
    primary key (id)
);


create table MAINTENANCESERVICE_MAIN
(
    ID                     VARCHAR(32) not null,
    SHEETID                VARCHAR(50),
    TITLE                  VARCHAR(100),
    SHEETACCEPTLIMIT       datetime year to second,
    SHEETCOMPLETELIMIT     datetime year to second,
    SENDTIME               datetime year to second,
    SENDORGTYPE            VARCHAR(25),
    SENDUSERID             VARCHAR(30),
    SENDCONTACT            VARCHAR(30),
    SHEETACCESSORIES       VARCHAR(100),
    ENDTIME                datetime year to second,
    ENDRESULT              VARCHAR(30),
    STATUS                 INTEGER,
    HOLDSTATISFIED         INTEGER,
    ENDUSERID              VARCHAR(50),
    DELETED                INTEGER,
    PIID                   VARCHAR(50),
    SHEETTEMPLATENAME      VARCHAR(50),
    PARENTSHEETNAME        VARCHAR(50),
    PARENTSHEETID          VARCHAR(50),
    CORRELATIONKEY         VARCHAR(50),
    PARENTCORRELATION      VARCHAR(50),
    TODEPTID               VARCHAR(50),
    SENDDEPTID             VARCHAR(35),
    SENDROLEID             VARCHAR(35),
    ENDROLEID              VARCHAR(35),
    ENDDEPTID              VARCHAR(35),
    TEMPLATEFLAG           VARCHAR(20),
    CANCELREASON           VARCHAR(100),
    SENDYEAR               INTEGER,
    SENDMONTH              INTEGER,
    SENDDAY                INTEGER,
    PARENTPHASENAME        VARCHAR(50),
    INVOKEMODE             VARCHAR(50),
    SENDOBJECT             CHAR(4000),
    MAINTYPE               VARCHAR(2000),
    MAINSERIALNUMBER       VARCHAR(2000),
    MAINDEVICENAME         VARCHAR(2000),
    MAINMANUFACTURERS      VARCHAR(2000),
    MAINSYSTEMNAME         VARCHAR(2000),
    MAINEQUIPMENTTYPE      VARCHAR(2000),
    MAINDEVICENUMBER       VARCHAR(2000),
    MAINACCESSTIME         VARCHAR(2000),
    MAINOUTDATE            VARCHAR(2000),
    MAININTERFACETYPE      VARCHAR(2000),
    MAINSOFTWARE           VARCHAR(2000),
    MAININTEGRATORNAME     VARCHAR(2000),
    MAINLEVELSERVICE       VARCHAR(2000),
    MAINPHYSICALLOCATION   VARCHAR(2000),
    MAINREMARKS            VARCHAR(2000),
    MAINCONTACTS           VARCHAR(2000),
    MAINCONTACTINFORMATION VARCHAR(2000),
    MAINCOMPANY            VARCHAR(2000),
    MAINSPAREONE           VARCHAR(2000),
    MAINSPARETWO           VARCHAR(2000),
    MAINSPARETHREE         VARCHAR(2000),
    MAINSPAREFOUR          VARCHAR(2000),
    MAINSPAREFIVE          VARCHAR(2000),
    primary key (id)
);



create table MAINTENANCESERVICE_TASK
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
create index i_maintenanceservice_a on maintenanceservice_task (taskowner, createtime) TABLESPACE EOMSDATA;
create index i_maintenanceservice_b on maintenanceservice_task (createtime) TABLESPACE EOMSDATA;
create index i_maintenanceservice_c on maintenanceservice_task (taskstatus) TABLESPACE EOMSDATA;
create index i_maintenanceservice_d on maintenanceservice_task (sheetkey) TABLESPACE EOMSDATA;
create index i_maintenanceservice_e on maintenanceservice_main (sendtime, senduserid) TABLESPACE EOMSDATA;
create index i_maintenanceservice_f on maintenanceservice_main (sheetid) TABLESPACE EOMSDATA;
create index i_maintenanceservice_g on maintenanceservice_link (aiid) TABLESPACE EOMSDATA;
create index i_maintenanceservice_h on maintenanceservice_link (mainid) TABLESPACE EOMSDATA;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('60', 'MaintenanceService', '60', '60', '维保服务审批流程', 'iMaintenanceServiceMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('109', '维保服务审批流程', '维保服务审批流程', 109);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'maintenanceservice', '维保服务审批流程', 1024, '/sheet/maintenanceservice', 109,
        '2c9ba3865ad6167d015ad6167dfb0011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1760', '0', '0', '建单人', '1', '60', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1761', '0', '0', '设备维护人', '1', '60', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1762', '0', '0', '厂家', '1', '60', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1763', '0', '0', '报修人', '1', '60', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1764', '0', '0', '主管领导', '1', '60', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0012', '6001', '1', '新增工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0013', '6003', '0', '待处理工单', '60', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0014', '600301', '1', '待办工单', '6003', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0015', '6007', '1', '工单查询', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0016', '6008', '1', '工单处理模板', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0',
        8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0017', '6009', '1', '工单模板管理', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0018', '6010', '1', '管理者工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0019', '60', '0', '维保服务审批流程', '10', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb001a', '6002', '1', '草稿列表', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb001b', '6004', '0', '已处理工单', '60', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb001c', '600402', '1', '建立工单', '6004', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb001d', '600401', '1', '处理工单', '6004', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb001e', '6005', '1', '已归档工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb001f', '6006', '1', '已作废工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0020', '6011', '1', '超时时间设置', '60', null,
        '/sheet/overtimetip/overtimetip.do?flowName=MaintenanceService', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dfb0021', '6012', '1', '工单处理时限配置', '60', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=MaintenanceService', '0', '0', 30);