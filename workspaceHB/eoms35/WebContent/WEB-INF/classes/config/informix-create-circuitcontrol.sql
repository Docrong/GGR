create table CIRCUITCONTROL_LINK
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
    LINKGROUPCOMPLETE    VARCHAR(1000),
    primary key (id)
);


create table CIRCUITCONTROL_MAIN
(
    ID                 VARCHAR(32) not null,
    SHEETID            VARCHAR(50),
    TITLE              VARCHAR(100),
    SHEETACCEPTLIMIT   datetime year to second,
    SHEETCOMPLETELIMIT datetime year to second,
    SENDTIME           datetime year to second,
    SENDORGTYPE        VARCHAR(25),
    SENDUSERID         VARCHAR(30),
    SENDCONTACT        VARCHAR(30),
    SHEETACCESSORIES   VARCHAR(100),
    ENDTIME            datetime year to second,
    ENDRESULT          VARCHAR(30),
    STATUS             INTEGER,
    HOLDSTATISFIED     INTEGER,
    ENDUSERID          VARCHAR(50),
    DELETED            INTEGER,
    PIID               VARCHAR(50),
    SHEETTEMPLATENAME  VARCHAR(50),
    PARENTSHEETNAME    VARCHAR(50),
    PARENTSHEETID      VARCHAR(50),
    CORRELATIONKEY     VARCHAR(50),
    PARENTCORRELATION  VARCHAR(50),
    TODEPTID           VARCHAR(50),
    SENDDEPTID         VARCHAR(35),
    SENDROLEID         VARCHAR(35),
    ENDROLEID          VARCHAR(35),
    ENDDEPTID          VARCHAR(35),
    TEMPLATEFLAG       VARCHAR(20),
    CANCELREASON       VARCHAR(100),
    SENDYEAR           INTEGER,
    SENDMONTH          INTEGER,
    SENDDAY            INTEGER,
    PARENTPHASENAME    VARCHAR(50),
    INVOKEMODE         VARCHAR(50),
    SENDOBJECT         CHAR(4000),
    APPLYNUM           VARCHAR(1000),
    BUSINESSTYPE       VARCHAR(1000),
    SPAREONE           VARCHAR(1000),
    SPARETWO           VARCHAR(1000),
    SPARETHREE         VARCHAR(1000),
    SPAREFOUR          VARCHAR(1000),
    primary key (id)
);



create table CIRCUITCONTROL_TASK
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
create index i_circuitcontrol_a on circuitcontrol_task (taskowner, createtime) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_b on circuitcontrol_task (createtime) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_c on circuitcontrol_task (taskstatus) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_d on circuitcontrol_task (sheetkey) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_e on circuitcontrol_main (sendtime, senduserid) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_f on circuitcontrol_main (sheetid) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_g on circuitcontrol_link (aiid) TABLESPACE EOMSDATAV35;
create index i_circuitcontrol_h on circuitcontrol_link (mainid) TABLESPACE EOMSDATAV35;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('619', 'CircuitControl', '619', '619', '电路调度申请工单流程', 'iCircuitControlMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('106', '电路调度申请工单流程', '电路调度申请工单流程', 106);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'circuitcontrol', '电路调度申请工单流程', 1024, '/sheet/circuitcontrol', 106,
        '402882e54168ea1b014168ea1b980011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1940', '0', '0', '建单人', '1', '619', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1941', '0', '0', '有线科', '1', '619', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1942', '0', '0', '网管中心', '1', '619', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980012', '8601', '1', '新增工单', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980013', '8603', '0', '待处理工单', '86', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980014', '860301', '1', '待办工单', '8603', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1b980015', '8607', '1', '工单查询', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba80016', '8608', '1', '工单处理模板', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba80017', '8609', '1', '工单模板管理', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba80018', '8610', '1', '管理者工单', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba80019', '86', '0', '电路调度申请工单流程', '10', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba8001a', '8602', '1', '草稿列表', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba8001b', '8604', '0', '已处理工单', '86', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba8001c', '860402', '1', '建立工单', '8604', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba8001d', '860401', '1', '处理工单', '8604', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba8001e', '8605', '1', '已归档工单', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba8001f', '8606', '1', '已作废工单', '86', null,
        '/sheet/circuitcontrol/circuitcontrol.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba80020', '8611', '1', '超时时间设置', '86', null,
        '/sheet/overtimetip/overtimetip.do?flowName=CircuitControl', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e54168ea1b014168ea1ba80021', '8612', '1', '工单处理时限配置', '86', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=CircuitControl', '0', '0', 30);