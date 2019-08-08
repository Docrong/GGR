create table SECURITYOBJAUDIT_LINK
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
    LINKAUDITRESULT      VARCHAR(1000),
    LINKAUDITOPINION     VARCHAR(1000),
    LINKUPSTUTS          VARCHAR(1000),
    LINKUPREASON         VARCHAR(1000),
    LINKOPERATION        VARCHAR(1000),
    LINKRELIEVESTATE     VARCHAR(1000),
    MAINDEALTIME         datetime year to second,
    LINKEXTEND1          VARCHAR(2000),
    LINKEXTEND2          VARCHAR(2000),
    LINKEXTEND3          VARCHAR(2000),
    LINKEXTEND4          VARCHAR(2000),
    LINKEXTEND5          VARCHAR(2000),
    LINKREPLYOBJ         VARCHAR(1000),
    LINKREPLYEXP         VARCHAR(1000),
    LINKREMARK           VARCHAR(1000),
    LINKQUALITYRESULT    VARCHAR(1000),
    LINKQUALITYVIEW      VARCHAR(1000),
    LINKREJECT           VARCHAR(1000),
    primary key (id)
);


create table SECURITYOBJAUDIT_MAIN
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
    MAINISMPSHEETID    VARCHAR(2000),
    MAINUSERID         VARCHAR(2000),
    MAINACCEPTTIME     datetime year to second,
    MAINDEALTIME       datetime year to second,
    MAINPROVINCE       VARCHAR(2000),
    MAINCITY           VARCHAR(2000),
    MAINSENDWAY        VARCHAR(2000),
    MAINTASKNAME       VARCHAR(2000),
    MAINTASKID         VARCHAR(2000),
    MAINWORKID         VARCHAR(2000),
    MAINWORKNAME       VARCHAR(2000),
    MAINCHECKTIME      datetime year to second,
    MAINPRODES         VARCHAR(2000),
    MAINSECURITYIP     VARCHAR(2000),
    MAINSECURITYNAME   VARCHAR(2000),
    MAINSECURITYID     VARCHAR(2000),
    MAINOPERATENAME    VARCHAR(2000),
    MAINEXTEND1        VARCHAR(2000),
    MAINEXTEND2        VARCHAR(2000),
    MAINEXTEND3        VARCHAR(2000),
    MAINEXTEND4        VARCHAR(2000),
    MAINEXTEND5        VARCHAR(2000),
    primary key (id)
);



create table SECURITYOBJAUDIT_TASK
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
create index i_securityobjaudit_a on securityobjaudit_task (taskowner, createtime) TABLESPACE eomsdata;
create index i_securityobjaudit_b on securityobjaudit_task (createtime) TABLESPACE eomsdata;
create index i_securityobjaudit_c on securityobjaudit_task (taskstatus) TABLESPACE eomsdata;
create index i_securityobjaudit_d on securityobjaudit_task (sheetkey) TABLESPACE eomsdata;
create index i_securityobjaudit_e on securityobjaudit_main (sendtime, senduserid) TABLESPACE eomsdata;
create index i_securityobjaudit_f on securityobjaudit_main (sheetid) TABLESPACE eomsdata;
create index i_securityobjaudit_g on securityobjaudit_link (aiid) TABLESPACE eomsdata;
create index i_securityobjaudit_h on securityobjaudit_link (mainid) TABLESPACE eomsdata;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('43', 'SecurityObjAudit', '43', '43', '安全对象问题审批工单流程', 'iSecurityObjAuditMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('31', '安全对象问题审批工单流程', '安全对象问题审批工单流程', 31);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'securityobjaudit', '安全对象问题审批工单流程', 1024, '/sheet/securityobjaudit', 31,
        '40287d8c5ba332e8015ba332e8a70011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4301', '0', '0', '建单人', '1', '43', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4302', '0', '0', '审批人', '1', '43', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4303', '0', '0', '处理人', '1', '43', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4304', '0', '0', '质检员', '1', '43', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70012', '4501', '1', '新增工单', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70013', '4503', '0', '待处理工单', '45', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70014', '450301', '1', '待办工单', '4503', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70015', '4507', '1', '工单查询', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70016', '4508', '1', '工单处理模板', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70017', '4509', '1', '工单模板管理', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70018', '4510', '1', '管理者工单', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a70019', '45', '0', '安全对象问题审批工单流程', '10', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a7001a', '4502', '1', '草稿列表', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a7001b', '4504', '0', '已处理工单', '45', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a8001c', '450402', '1', '建立工单', '4504', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a8001d', '450401', '1', '处理工单', '4504', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a8001e', '4505', '1', '已归档工单', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a8001f', '4506', '1', '已作废工单', '45', null,
        '/sheet/securityobjaudit/securityobjaudit.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a80020', '4511', '1', '超时时间设置', '45', null,
        '/sheet/overtimetip/overtimetip.do?flowName=SecurityObjAudit', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba332e8015ba332e8a80021', '4512', '1', '工单处理时限配置', '45', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=SecurityObjAudit', '0', '0', 30);