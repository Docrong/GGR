create table GROUPCHECK_LINK
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
    LINKAUTOFIND         VARCHAR(1000),
    LINKEXPLAIN          VARCHAR(1000),
    LINKCONTACT          VARCHAR(1000),
    LINKCONTACTPHONE     VARCHAR(1000),
    LINKSTAND1           VARCHAR(1000),
    LINKSTAND2           VARCHAR(1000),
    LINKSTAND3           VARCHAR(1000),
    LINKSTAND4           VARCHAR(1000),
    LINKSTAND5           VARCHAR(1000),
    primary key (id)
);


create table GROUPCHECK_MAIN
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
    MAINGROUPSHEETID   VARCHAR(1000),
    MAINPRODUCTINS     VARCHAR(1000),
    MAINCIRCUITCODE    VARCHAR(1000),
    MAINUSERAFFILIA    VARCHAR(1000),
    MAINCOMPLAINTTIME  datetime year to second,
    MAINSTANDTIME1     datetime year to second,
    MAINSTANDTIME2     datetime year to second,
    MAINSTAND1         VARCHAR(1000),
    MAINSTAND2         VARCHAR(1000),
    MAINSTAND3         VARCHAR(1000),
    MAINSTAND4         VARCHAR(1000),
    MAINSTAND5         VARCHAR(1000),
    primary key (id)
);



create table GROUPCHECK_TASK
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
create index i_groupcheck_a on groupcheck_task (taskowner, createtime) TABLESPACE users;
create index i_groupcheck_b on groupcheck_task (createtime) TABLESPACE users;
create index i_groupcheck_c on groupcheck_task (taskstatus) TABLESPACE users;
create index i_groupcheck_d on groupcheck_task (sheetkey) TABLESPACE users;
create index i_groupcheck_e on groupcheck_main (sendtime, senduserid) TABLESPACE users;
create index i_groupcheck_f on groupcheck_main (sheetid) TABLESPACE users;
create index i_groupcheck_g on groupcheck_link (aiid) TABLESPACE users;
create index i_groupcheck_h on groupcheck_link (mainid) TABLESPACE users;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('97', 'GroupCheck', '97', '97', '集客投诉核查工单流程', 'iGroupCheckMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('78', '集客投诉核查工单流程', '集客投诉核查工单流程', 78);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'groupcheck', '集客投诉核查工单流程', 1024, '/sheet/groupcheck', 78,
        '297e58995f9a7853015f9a7854100011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('9701', '0', '0', '建单人', '1', '97', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('9702', '0', '0', '地市核查组', '1', '97', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100012', '14001', '1', '新增工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100013', '14003', '0', '待处理工单', '140', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100014', '1400301', '1', '待办工单', '14003', null,
        '/sheet/groupcheck/groupcheck.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100015', '14007', '1', '工单查询', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100016', '14008', '1', '工单处理模板', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100017', '14009', '1', '工单模板管理', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100018', '14010', '1', '管理者工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100019', '140', '0', '集客投诉核查工单流程', '10', null,
        '/sheet/groupcheck/groupcheck.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785410001a', '14002', '1', '草稿列表', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785410001b', '14004', '0', '已处理工单', '140', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785410001c', '1400402', '1', '建立工单', '14004', null,
        '/sheet/groupcheck/groupcheck.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785410001d', '1400401', '1', '处理工单', '14004', null,
        '/sheet/groupcheck/groupcheck.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785410001e', '14005', '1', '已归档工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785410001f', '14006', '1', '已作废工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100020', '14011', '1', '超时时间设置', '140', null,
        '/sheet/overtimetip/overtimetip.do?flowName=GroupCheck', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854100021', '14012', '1', '工单处理时限配置', '140', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=GroupCheck', '0', '0', 30);