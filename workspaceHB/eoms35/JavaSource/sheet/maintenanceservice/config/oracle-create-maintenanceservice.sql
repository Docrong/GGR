create table MAINTENANCESERVICE_LINK
(
    ID                       VARCHAR2(32) not null,
    MAINID                   VARCHAR2(50),
    NODEACCEPTLIMIT          DATE,
    NODECOMPLETELIMIT        DATE,
    OPERATETYPE              NUMBER,
    OPERATETIME              DATE,
    OPERATEUSERID            VARCHAR2(30),
    OPERATEORGTYPE           VARCHAR2(10),
    OPERATERCONTACT          VARCHAR2(50),
    TOORGTYPE                NUMBER,
    TOORGUSERID              VARCHAR2(50),
    TOROLEID                 NUMBER,
    ACCEPTFLAG               NUMBER,
    ACCEPTTIME               DATE,
    COMPLETEFLAG             NUMBER,
    COMPLETETIME             DATE,
    PRELINKID                VARCHAR2(50),
    PARENTLINKID             VARCHAR2(50),
    FIRSTLINKID              VARCHAR2(50),
    PIID                     VARCHAR2(50),
    AIID                     VARCHAR2(50),
    ACTIVETEMPLATEID         VARCHAR2(50),
    NODETEMPLATENAME         VARCHAR2(50),
    NODEACCESSORIES          VARCHAR2(50),
    TOORGDEPTID              VARCHAR2(35),
    TOORGROLEID              VARCHAR2(35),
    OPERATEROLEID            VARCHAR2(35),
    OPERATEDEPTID            VARCHAR2(35),
    CORRELATIONKEY           VARCHAR2(50),
    TEMPLATEFLAG             VARCHAR2(20),
    TEMPLATENAME             VARCHAR2(100),
    TEMPLATECREATEUSERID     VARCHAR2(100),
    TRANSFERREASON           VARCHAR2(2000),
    REMARK                   VARCHAR2(2000),
    OPERATEYEAR              NUMBER,
    OPERATEMONTH             NUMBER,
    OPERATEDAY               NUMBER,
    LINKEXPLAIN              VARCHAR2(2000),
    LINKDESCRIBE             VARCHAR2(2000),
    LINKSPAREONE             VARCHAR2(2000),
    LINKSPARETWO             VARCHAR2(2000),
    LINKSPARETHREE           VARCHAR2(2000),
    LINKSPAREFOUR            VARCHAR2(2000),
    LINKSPAREFIVE            VARCHAR2(2000),
    LINKFILINGREASON         VARCHAR2(2000),
    LINKTYPE                 VARCHAR2(2000),
    LINKSERIALNUMBER         VARCHAR2(2000),
    LINKDEVICENAME           VARCHAR2(2000),
    LINKMANUFACTURERS        VARCHAR2(2000),
    LINKSYSTEMNAME           VARCHAR2(2000),
    LINKEQUIPMENTTYPE        VARCHAR2(2000),
    LINKDEVICENUMBER         VARCHAR2(2000),
    LINKACCESSTIME           VARCHAR2(2000),
    LINKOUTDATE              VARCHAR2(2000),
    LINKINTERFACETYPE        VARCHAR2(2000),
    LINKSOFTWARE             VARCHAR2(2000),
    LINKINTEGRATORNAME       VARCHAR2(2000),
    LINKLEVELSERVICE         VARCHAR2(2000),
    LINKPHYSICALLOCATION     VARCHAR2(2000),
    LINKREMARKS              VARCHAR2(2000),
    LINKCONTACTS             VARCHAR2(2000),
    LINKCONTACTINFORMATION   VARCHAR2(2000),
    LINKCOMPANY              VARCHAR2(2000),
    LINKREASONREJECTION      VARCHAR2(2000),
    LINKRESULT               VARCHAR2(2000),
    LINKAPPROVALOPINION      VARCHAR2(2000),
    LINKSERVICECONTENT       VARCHAR2(2000),
    LINKSCORE                VARCHAR2(2000),
    LINKTIMELYPROCESSING     VARCHAR2(2000),
    LINKMAINTENANCESTARTTIME VARCHAR2(2000),
    LINKMAINTENANCEENDTIME   VARCHAR2(2000),
    LINKSENDOBJECT           VARCHAR2(2000)
);
alter table MAINTENANCESERVICE_LINK
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



create table MAINTENANCESERVICE_MAIN
(
    ID                     VARCHAR2(32) not null,
    SHEETID                VARCHAR2(50),
    TITLE                  VARCHAR2(100),
    SHEETACCEPTLIMIT       DATE,
    SHEETCOMPLETELIMIT     DATE,
    SENDTIME               DATE,
    SENDORGTYPE            VARCHAR2(25),
    SENDUSERID             VARCHAR2(30),
    SENDCONTACT            VARCHAR2(30),
    SHEETACCESSORIES       VARCHAR2(100),
    ENDTIME                DATE,
    ENDRESULT              VARCHAR2(30),
    STATUS                 NUMBER,
    HOLDSTATISFIED         NUMBER,
    ENDUSERID              VARCHAR2(50),
    DELETED                NUMBER,
    PIID                   VARCHAR2(50),
    SHEETTEMPLATENAME      VARCHAR2(50),
    PARENTSHEETNAME        VARCHAR2(50),
    PARENTSHEETID          VARCHAR2(50),
    CORRELATIONKEY         VARCHAR2(50),
    PARENTCORRELATION      VARCHAR2(50),
    TODEPTID               VARCHAR2(50),
    SENDDEPTID             VARCHAR2(35),
    SENDROLEID             VARCHAR2(35),
    ENDROLEID              VARCHAR2(35),
    ENDDEPTID              VARCHAR2(35),
    TEMPLATEFLAG           VARCHAR2(20),
    CANCELREASON           VARCHAR2(100),
    SENDYEAR               NUMBER,
    SENDMONTH              NUMBER,
    SENDDAY                NUMBER,
    PARENTPHASENAME        VARCHAR2(50),
    INVOKEMODE             VARCHAR2(50),
    MAINTYPE               VARCHAR2(2000),
    MAINSERIALNUMBER       VARCHAR2(2000),
    MAINDEVICENAME         VARCHAR2(2000),
    MAINMANUFACTURERS      VARCHAR2(2000),
    MAINSYSTEMNAME         VARCHAR2(2000),
    MAINEQUIPMENTTYPE      VARCHAR2(2000),
    MAINDEVICENUMBER       VARCHAR2(2000),
    MAINACCESSTIME         VARCHAR2(2000),
    MAINOUTDATE            VARCHAR2(2000),
    MAININTERFACETYPE      VARCHAR2(2000),
    MAINSOFTWARE           VARCHAR2(2000),
    MAININTEGRATORNAME     VARCHAR2(2000),
    MAINLEVELSERVICE       VARCHAR2(2000),
    MAINPHYSICALLOCATION   VARCHAR2(2000),
    MAINREMARKS            VARCHAR2(2000),
    MAINCONTACTS           VARCHAR2(2000),
    MAINCONTACTINFORMATION VARCHAR2(2000),
    MAINCOMPANY            VARCHAR2(2000),
    MAINSPAREONE           VARCHAR2(2000),
    MAINSPARETWO           VARCHAR2(2000),
    MAINSPARETHREE         VARCHAR2(2000),
    MAINSPAREFOUR          VARCHAR2(2000),
    MAINSPAREFIVE          VARCHAR2(2000),
    SENDOBJECT             VARCHAR2(2000)
);
alter table MAINTENANCESERVICE_MAIN
    add constraint MAINTENANCESERVICE_MAIN_PK primary key (id) using index;



create table MAINTENANCESERVICE_TASK
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
alter table MAINTENANCESERVICE_TASK
    add constraint MAINTENANCESERVICE_TASK_PK primary key (id) using index;


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
        '2c9ba3865ad6167d015ad6167dec0000');

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
values ('2c9ba3865ad6167d015ad6167dec0001', '6001', '1', '新增工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0002', '6003', '0', '待处理工单', '60', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0003', '600301', '1', '待办工单', '6003', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0004', '6007', '1', '工单查询', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0005', '6008', '1', '工单处理模板', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0',
        8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0006', '6009', '1', '工单模板管理', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0007', '6010', '1', '管理者工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0008', '60', '0', '维保服务审批流程', '10', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0009', '6002', '1', '草稿列表', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec000a', '6004', '0', '已处理工单', '60', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec000b', '600402', '1', '建立工单', '6004', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec000c', '600401', '1', '处理工单', '6004', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec000d', '6005', '1', '已归档工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec000e', '6006', '1', '已作废工单', '60', null,
        '/sheet/maintenanceservice/maintenanceservice.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec000f', '6011', '1', '超时时间设置', '60', null,
        '/sheet/overtimetip/overtimetip.do?flowName=MaintenanceService', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9ba3865ad6167d015ad6167dec0010', '6012', '1', '工单处理时限配置', '60', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=MaintenanceService', '0', '0', 30);