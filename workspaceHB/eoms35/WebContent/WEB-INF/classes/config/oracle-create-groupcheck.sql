create table GROUPCHECK_LINK
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
    LINKAUTOFIND         VARCHAR2(1000),
    LINKEXPLAIN          VARCHAR2(1000),
    LINKCONTACT          VARCHAR2(1000),
    LINKCONTACTPHONE     VARCHAR2(1000),
    LINKSTAND1           VARCHAR2(1000),
    LINKSTAND2           VARCHAR2(1000),
    LINKSTAND3           VARCHAR2(1000),
    LINKSTAND4           VARCHAR2(1000),
    LINKSTAND5           VARCHAR2(1000),
    LINKSENDOBJECT       VARCHAR2(2000)
);
alter table GROUPCHECK_LINK
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



create table GROUPCHECK_MAIN
(
    ID                 VARCHAR2(32) not null,
    SHEETID            VARCHAR2(50),
    TITLE              VARCHAR2(100),
    SHEETACCEPTLIMIT   DATE,
    SHEETCOMPLETELIMIT DATE,
    SENDTIME           DATE,
    SENDORGTYPE        VARCHAR2(25),
    SENDUSERID         VARCHAR2(30),
    SENDCONTACT        VARCHAR2(30),
    SHEETACCESSORIES   VARCHAR2(100),
    ENDTIME            DATE,
    ENDRESULT          VARCHAR2(30),
    STATUS             NUMBER,
    HOLDSTATISFIED     NUMBER,
    ENDUSERID          VARCHAR2(50),
    DELETED            NUMBER,
    PIID               VARCHAR2(50),
    SHEETTEMPLATENAME  VARCHAR2(50),
    PARENTSHEETNAME    VARCHAR2(50),
    PARENTSHEETID      VARCHAR2(50),
    CORRELATIONKEY     VARCHAR2(50),
    PARENTCORRELATION  VARCHAR2(50),
    TODEPTID           VARCHAR2(50),
    SENDDEPTID         VARCHAR2(35),
    SENDROLEID         VARCHAR2(35),
    ENDROLEID          VARCHAR2(35),
    ENDDEPTID          VARCHAR2(35),
    TEMPLATEFLAG       VARCHAR2(20),
    CANCELREASON       VARCHAR2(100),
    SENDYEAR           NUMBER,
    SENDMONTH          NUMBER,
    SENDDAY            NUMBER,
    PARENTPHASENAME    VARCHAR2(50),
    INVOKEMODE         VARCHAR2(50),
    MAINGROUPSHEETID   VARCHAR2(1000),
    MAINPRODUCTINS     VARCHAR2(1000),
    MAINCIRCUITCODE    VARCHAR2(1000),
    MAINUSERAFFILIA    VARCHAR2(1000),
    MAINCOMPLAINTTIME  DATE,
    MAINSTANDTIME1     DATE,
    MAINSTANDTIME2     DATE,
    MAINSTAND1         VARCHAR2(1000),
    MAINSTAND2         VARCHAR2(1000),
    MAINSTAND31        VARCHAR2(1000),
    MAINSTAND4         VARCHAR2(1000),
    MAINSTAND5         VARCHAR2(1000),
    SENDOBJECT         VARCHAR2(2000)
);
alter table GROUPCHECK_MAIN
    add constraint GROUPCHECK_MAIN_PK primary key (id) using index;



create table GROUPCHECK_TASK
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
alter table GROUPCHECK_TASK
    add constraint GROUPCHECK_TASK_PK primary key (id) using index;


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
        '297e58995f9a7853015f9a7854000000');

--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('9701', '0', '0', '建单人', '1', '97', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('9702', '0', '0', '地市核查组', '1', '97', '1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000001', '14001', '1', '新增工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000002', '14003', '0', '待处理工单', '140', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000003', '1400301', '1', '待办工单', '14003', null,
        '/sheet/groupcheck/groupcheck.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000004', '14007', '1', '工单查询', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000005', '14008', '1', '工单处理模板', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=getDealTemplatesByUserId&&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000006', '14009', '1', '工单模板管理', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=getTemplatesByUserId&&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000007', '14010', '1', '管理者工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000008', '140', '0', '集客投诉核查工单流程', '10', null,
        '/sheet/groupcheck/groupcheck.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000009', '14002', '1', '草稿列表', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785400000a', '14004', '0', '已处理工单', '140', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785400000b', '1400402', '1', '建立工单', '14004', null,
        '/sheet/groupcheck/groupcheck.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785400000c', '1400401', '1', '处理工单', '14004', null,
        '/sheet/groupcheck/groupcheck.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785400000d', '14005', '1', '已归档工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785400000e', '14006', '1', '已作废工单', '140', null,
        '/sheet/groupcheck/groupcheck.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a785400000f', '14011', '1', '超时时间设置', '140', null,
        '/sheet/overtimetip/overtimetip.do?flowName=GroupCheck', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e58995f9a7853015f9a7854000010', '14012', '1', '工单处理时限配置', '140', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&&flowName=GroupCheck', '0', '0', 30);