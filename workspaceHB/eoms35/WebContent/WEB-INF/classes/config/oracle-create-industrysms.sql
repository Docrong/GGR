create table INDUSTRYSMS_LINK
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
    APPROVERESULT        VARCHAR2(1000),
    APPROVEOPINION       VARCHAR2(1000),
    DATARESULT           VARCHAR2(1000),
    FAILUREINFO          VARCHAR2(1000),
    LINKSENDOBJECT       VARCHAR2(2000)
);
alter table INDUSTRYSMS_LINK
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



create table INDUSTRYSMS_MAIN
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
    REGIONAL               VARCHAR2(10),
    ECSITYPE               VARCHAR2(10),
    ABBREVIATION           VARCHAR2(64),
    ENTERPRISECODE         VARCHAR2(64),
    SERVICECODE            VARCHAR2(64),
    CILENTSERVERIPADDR     VARCHAR2(16),
    CILENTSERVERIPADDRS    VARCHAR2(255),
    USER                   VARCHAR2(40),
    PASSWORD               VARCHAR2(40),
    MAXCONNECTIONS         VARCHAR2(10),
    MAXINBYTES             VARCHAR2(10),
    MAXOUTBYTES            VARCHAR2(10),
    PROTOCOL               VARCHAR2(10),
    ISAUTHENTICATION       VARCHAR2(10),
    SEVICECONTACT          VARCHAR2(32),
    SEVICEPHONE            VARCHAR2(32),
    CUSTOMERCONTACT        VARCHAR2(32),
    CUSTOMERPHONE          VARCHAR2(32),
    UNIT                   VARCHAR2(128),
    APPLYDATE              DATE,
    HARDWAREMODEL          VARCHAR2(32),
    SOFTVERSION            VARCHAR2(32),
    INTEGRATOR             VARCHAR2(128),
    ISURGENT               VARCHAR2(10),
    GROUPNUMBER            VARCHAR2(64),
    MASID                  VARCHAR2(64),
    TIMELIMIT              DATE,
    ABBREVIATIONNEW        VARCHAR2(128),
    CILENTSERVERIPADDRNEW  VARCHAR2(16),
    CILENTSERVERIPADDRSNEW VARCHAR2(255),
    PASSWORDNEW            VARCHAR2(40),
    MAXCONNECTIONSNEW      VARCHAR2(10),
    MAXINBYTESNEW          VARCHAR2(10),
    MAXOUTBYTESNEW         VARCHAR2(10),
    PROTOCOLNEW            VARCHAR2(10),
    SPAREONE               VARCHAR2(255),
    SPARETWO               VARCHAR2(255),
    SPARETHREE             VARCHAR2(255),
    SENDOBJECT             VARCHAR2(2000)
);
alter table INDUSTRYSMS_MAIN
    add constraint INDUSTRYSMS_MAIN_PK primary key (id) using index;



create table INDUSTRYSMS_TASK
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
alter table INDUSTRYSMS_TASK
    add constraint INDUSTRYSMS_TASK_PK primary key (id) using index;


--创建索引
create index i_industrysms_a on industrysms_task (taskowner, createtime) TABLESPACE EOMSDATAV35;
create index i_industrysms_b on industrysms_task (createtime) TABLESPACE EOMSDATAV35;
create index i_industrysms_c on industrysms_task (taskstatus) TABLESPACE EOMSDATAV35;
create index i_industrysms_d on industrysms_task (sheetkey) TABLESPACE EOMSDATAV35;
create index i_industrysms_e on industrysms_main (sendtime, senduserid) TABLESPACE EOMSDATAV35;
create index i_industrysms_f on industrysms_main (sheetid) TABLESPACE EOMSDATAV35;
create index i_industrysms_g on industrysms_link (aiid) TABLESPACE EOMSDATAV35;
create index i_industrysms_h on industrysms_link (mainid) TABLESPACE EOMSDATAV35;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('616', 'IndustrySms', '616', '616', '行业短信开通删除工单流程', 'iIndustrySmsMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('103', '行业短信开通删除工单流程', '行业短信开通删除工单流程', 103);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'industrysms', '行业短信开通删除工单流程', 1024, '/sheet/industrysms', 103,
        '402882013d34b9c0013d34b9c0820000');

--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1860', '0', '0', '建单人', '1', '616', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1861', '0', '0', '审核人', '1', '616', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1862', '0', '0', '数据制作人', '1', '616', '1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820001', '6001', '1', '新增工单', '60', null,
        '/sheet/industrysms/industrysms.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820002', '6003', '0', '待处理工单', '60', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820003', '600301', '1', '待办工单', '6003', null,
        '/sheet/industrysms/industrysms.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820004', '6007', '1', '工单查询', '60', null,
        '/sheet/industrysms/industrysms.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820005', '6008', '1', '工单处理模板', '60', null,
        '/sheet/industrysms/industrysms.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820006', '6009', '1', '工单模板管理', '60', null,
        '/sheet/industrysms/industrysms.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820007', '6010', '1', '管理者工单', '60', null,
        '/sheet/industrysms/industrysms.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820008', '60', '0', '行业短信开通删除工单流程', '10', null,
        '/sheet/industrysms/industrysms.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820009', '6002', '1', '草稿列表', '60', null,
        '/sheet/industrysms/industrysms.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c082000a', '6004', '0', '已处理工单', '60', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c082000b', '600402', '1', '建立工单', '6004', null,
        '/sheet/industrysms/industrysms.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c082000c', '600401', '1', '处理工单', '6004', null,
        '/sheet/industrysms/industrysms.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c082000d', '6005', '1', '已归档工单', '60', null,
        '/sheet/industrysms/industrysms.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c082000e', '6006', '1', '已作废工单', '60', null,
        '/sheet/industrysms/industrysms.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c082000f', '6011', '1', '超时时间设置', '60', null,
        '/sheet/overtimetip/overtimetip.do?flowName=IndustrySms', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882013d34b9c0013d34b9c0820010', '6012', '1', '工单处理时限配置', '60', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=IndustrySms', '0', '0', 30);