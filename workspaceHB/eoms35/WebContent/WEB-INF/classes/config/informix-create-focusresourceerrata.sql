create table FOCUSRESOURCEERRATA_LINK
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
    LINKCHECKSTATE       VARCHAR(1000),
    LINKRESERVED1        VARCHAR(1000),
    LINKRESERVED2        VARCHAR(1000),
    LINKRESERVED3        VARCHAR(1000),
    LINKRESERVED4        VARCHAR(1000),
    LINKRESERVED5        VARCHAR(1000),
    LINKISMYSEL          VARCHAR(1000),
    LINKAPPROVALOPINION  VARCHAR(1000),
    LINKRESULT           VARCHAR(1000),
    LINKREMARK           VARCHAR(1000),
    primary key (id)
);


create table FOCUSRESOURCEERRATA_MAIN
(
    ID                  VARCHAR(32) not null,
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
    MAINPRODUCTINSTANCE VARCHAR(1000),
    MAINCUSTOMERNAME    VARCHAR(1000),
    MAINBUSINESSTYPE    VARCHAR(1000),
    MAINCIRCUITCODE     VARCHAR(1000),
    MAINFAILCITY        VARCHAR(1000),
    MAINCHECKSTATE      VARCHAR(1000),
    MAINRESERVED1       VARCHAR(1000),
    MAINRESERVED2       VARCHAR(1000),
    MAINRESERVED3       VARCHAR(1000),
    MAINRESERVED4       VARCHAR(1000),
    MAINRESERVED5       VARCHAR(1000),
    primary key (id)
);



create table FOCUSRESOURCEERRATA_TASK
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
create index i_focusresourceerrata_a on focusresourceerrata_task (taskowner, createtime) TABLESPACE users;
create index i_focusresourceerrata_b on focusresourceerrata_task (createtime) TABLESPACE users;
create index i_focusresourceerrata_c on focusresourceerrata_task (taskstatus) TABLESPACE users;
create index i_focusresourceerrata_d on focusresourceerrata_task (sheetkey) TABLESPACE users;
create index i_focusresourceerrata_e on focusresourceerrata_main (sendtime, senduserid) TABLESPACE users;
create index i_focusresourceerrata_f on focusresourceerrata_main (sheetid) TABLESPACE users;
create index i_focusresourceerrata_g on focusresourceerrata_link (aiid) TABLESPACE users;
create index i_focusresourceerrata_h on focusresourceerrata_link (mainid) TABLESPACE users;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('100', 'FocusResourceErrata', '100', '100', '集客资源勘误流程', 'iFocusResourceErrataMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('1001', '集客资源勘误流程', '集客资源勘误流程', 1001);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'focusresourceerrata', '集客资源勘误流程', 1024, '/sheet/focusresourceerrata', 1001,
        '2c9048ab6347a50d016347a50daf0011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1012', '0', '0', '建单人', '1', '100', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1013', '0', '0', '申请人', '1', '100', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1003', '0', '0', '传输网部', '1', '100', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1001', '0', '0', '申请人', '1', '100', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1004', '0', '0', '地市', '1', '100', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1002', '0', '0', '监控部', '1', '100', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00012', '199901', '1', '新增工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00013', '199903', '0', '待处理工单', '1999', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00014', '19990301', '1', '待办工单', '199903', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00015', '199907', '1', '工单查询', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00016', '199908', '1', '工单处理模板', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=getDealTemplatesByUserId&type=templateManage', '0',
        '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00017', '199909', '1', '工单模板管理', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=getTemplatesByUserId&type=templateManage', '0', '0',
        9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00018', '199910', '1', '管理者工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00019', '1999', '0', '集客资源勘误流程', '10', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db0001a', '199902', '1', '草稿列表', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db0001b', '199904', '0', '已处理工单', '1999', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db0001c', '19990402', '1', '建立工单', '199904', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db0001d', '19990401', '1', '处理工单', '199904', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db0001e', '199905', '1', '已归档工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db0001f', '199906', '1', '已作废工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00020', '199911', '1', '超时时间设置', '1999', null,
        '/sheet/overtimetip/overtimetip.do?flowName=FocusResourceErrata', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50db00021', '199912', '1', '工单处理时限配置', '1999', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=FocusResourceErrata', '0', '0', 30);