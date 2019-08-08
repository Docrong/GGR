create table PLANNCONFIRM_LINK
(
    ID                    VARCHAR(32) not null,
    MAINID                VARCHAR(50),
    NODEACCEPTLIMIT       datetime year to second,
    NODECOMPLETELIMIT     datetime year to second,
    OPERATETYPE           INTEGER,
    OPERATETIME           datetime year to second,
    OPERATEUSERID         VARCHAR(30),
    OPERATEORGTYPE        VARCHAR(10),
    OPERATERCONTACT       VARCHAR(50),
    TOORGTYPE             INTEGER,
    TOORGUSERID           VARCHAR(50),
    TOROLEID              INTEGER,
    ACCEPTFLAG            INTEGER,
    ACCEPTTIME            datetime year to second,
    COMPLETEFLAG          INTEGER,
    COMPLETETIME          datetime year to second,
    PRELINKID             VARCHAR(50),
    PARENTLINKID          VARCHAR(50),
    FIRSTLINKID           VARCHAR(50),
    PIID                  VARCHAR(50),
    AIID                  VARCHAR(50),
    ACTIVETEMPLATEID      VARCHAR(50),
    NODETEMPLATENAME      VARCHAR(50),
    NODEACCESSORIES       VARCHAR(50),
    TOORGDEPTID           VARCHAR(35),
    TOORGROLEID           VARCHAR(35),
    OPERATEROLEID         VARCHAR(35),
    OPERATEDEPTID         VARCHAR(35),
    CORRELATIONKEY        VARCHAR(50),
    TEMPLATEFLAG          VARCHAR(20),
    TEMPLATENAME          VARCHAR(100),
    TEMPLATECREATEUSERID  VARCHAR(100),
    TRANSFERREASON        VARCHAR(200),
    REMARK                VARCHAR(200),
    OPERATEYEAR           INTEGER,
    OPERATEMONTH          INTEGER,
    OPERATEDAY            INTEGER,
    LINKSENDOBJECT        CHAR(4000),
    SPAREONE              VARCHAR(255),
    SPARETWO              VARCHAR(255),
    SPARETHREE            VARCHAR(255),
    VALIDATIONRESULT      VARCHAR(100),
    PERCENTAGREEMENT      VARCHAR(1000),
    GROUPDISCUSSION       VARCHAR(100),
    PLANNINGAZIMUTH       VARCHAR(1000),
    ELECTRONICANGLE       VARCHAR(1000),
    MECHANICALANGLE       VARCHAR(1000),
    PLANNINGCONFIGURATION VARCHAR(1000),
    SUPPLEMENTSCHEME      VARCHAR(100),
    PARTICULARCASE        VARCHAR(2000),
    CHECKPEOPLE           VARCHAR(2000),
    CONTACTINFORMATION    VARCHAR(2000),
    primary key (id)
);


create table PLANNCONFIRM_MAIN
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
    PROFESSIONAL       VARCHAR(100),
    NETWORKTYPEONE     VARCHAR(100),
    NETWORKTYPETWO     VARCHAR(100),
    NETWORKTYPETHREE   VARCHAR(100),
    TASKTYPE           VARCHAR(100),
    TASKDESCRIPTION    VARCHAR(2000),
    SELECTIONTIME      datetime year to second,
    PLANNINGNUMBER     VARCHAR(1000),
    TERRITORIALBRANCH  VARCHAR(100),
    STATIONSITE        VARCHAR(1000),
    SCENETYPE          VARCHAR(1000),
    LONGITUDE          VARCHAR(1000),
    LATITUDE           VARCHAR(1000),
    BUILDINGTYPE       VARCHAR(1000),
    BUILDINGNUMBER     VARCHAR(1000),
    ANTENNAHEIGHT      VARCHAR(1000),
    PLATFORMTYPE       VARCHAR(1000),
    ANTENNATYPE        VARCHAR(100),
    ANTENNAMODEL       VARCHAR(1000),
    REQUIREMENT        VARCHAR(100),
    HIGHREQUIREMENT    VARCHAR(100),
    EXISTENCEBARRIER   VARCHAR(100),
    OTHERCIRCUMSTANCE  VARCHAR(2000),
    SPAREONE           VARCHAR(255),
    SPARETWO           VARCHAR(255),
    SPARETHREE         VARCHAR(255),
    primary key (id)
);



create table PLANNCONFIRM_TASK
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
create index i_plannconfirm_a on plannconfirm_task (taskowner, createtime) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_b on plannconfirm_task (createtime) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_c on plannconfirm_task (taskstatus) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_d on plannconfirm_task (sheetkey) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_e on plannconfirm_main (sendtime, senduserid) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_f on plannconfirm_main (sheetid) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_g on plannconfirm_link (aiid) TABLESPACE EOMSDATAV35;
create index i_plannconfirm_h on plannconfirm_link (mainid) TABLESPACE EOMSDATAV35;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('617', 'PlannConfirm', '617', '617', '规划站址确认申请流程流程', 'iPlannConfirmMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('104', '规划站址确认申请流程流程', '规划站址确认申请流程流程', 104);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'plannconfirm', '规划站址确认申请流程流程', 1024, '/sheet/plannconfirm', 104,
        '402882e83f18c2fa013f18c2fa9f0011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1870', '0', '0', '建单人', '1', '617', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1871', '0', '0', '规划站址确认管理员', '1', '617', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1872', '0', '0', '规划站址确认审核人', '1', '617', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0012', '9601', '1', '新增工单', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0013', '9603', '0', '待处理工单', '96', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0014', '960301', '1', '待办工单', '9603', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0015', '9607', '1', '工单查询', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0016', '9608', '1', '工单处理模板', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0017', '9609', '1', '工单模板管理', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0018', '9610', '1', '管理者工单', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0019', '96', '0', '规划站址确认申请流程流程', '10', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f001a', '9602', '1', '草稿列表', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f001b', '9604', '0', '已处理工单', '96', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f001c', '960402', '1', '建立工单', '9604', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f001d', '960401', '1', '处理工单', '9604', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f001e', '9605', '1', '已归档工单', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f001f', '9606', '1', '已作废工单', '96', null,
        '/sheet/plannconfirm/plannconfirm.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0020', '9611', '1', '超时时间设置', '96', null,
        '/sheet/overtimetip/overtimetip.do?flowName=PlannConfirm', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('402882e83f18c2fa013f18c2fa9f0021', '9612', '1', '工单处理时限配置', '96', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=PlannConfirm', '0', '0', 30);