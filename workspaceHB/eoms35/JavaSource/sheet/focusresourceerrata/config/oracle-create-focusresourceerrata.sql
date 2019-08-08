create table FOCUSRESOURCEERRATA_LINK
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
    LINKCHECKSTATE       VARCHAR2(1000),
    LINKRESERVED1        VARCHAR2(1000),
    LINKRESERVED2        VARCHAR2(1000),
    LINKRESERVED3        VARCHAR2(1000),
    LINKRESERVED4        VARCHAR2(1000),
    LINKRESERVED5        VARCHAR2(1000),
    LINKISMYSEL          VARCHAR2(1000),
    LINKAPPROVALOPINION  VARCHAR2(1000),
    LINKRESULT           VARCHAR2(1000),
    LINKREMARK           VARCHAR2(1000),
    LINKSENDOBJECT       VARCHAR2(2000)
);
alter table FOCUSRESOURCEERRATA_LINK
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



create table FOCUSRESOURCEERRATA_MAIN
(
    ID                  VARCHAR2(32) not null,
    SHEETID             VARCHAR2(50),
    TITLE               VARCHAR2(100),
    SHEETACCEPTLIMIT    DATE,
    SHEETCOMPLETELIMIT  DATE,
    SENDTIME            DATE,
    SENDORGTYPE         VARCHAR2(25),
    SENDUSERID          VARCHAR2(30),
    SENDCONTACT         VARCHAR2(30),
    SHEETACCESSORIES    VARCHAR2(100),
    ENDTIME             DATE,
    ENDRESULT           VARCHAR2(30),
    STATUS              NUMBER,
    HOLDSTATISFIED      NUMBER,
    ENDUSERID           VARCHAR2(50),
    DELETED             NUMBER,
    PIID                VARCHAR2(50),
    SHEETTEMPLATENAME   VARCHAR2(50),
    PARENTSHEETNAME     VARCHAR2(50),
    PARENTSHEETID       VARCHAR2(50),
    CORRELATIONKEY      VARCHAR2(50),
    PARENTCORRELATION   VARCHAR2(50),
    TODEPTID            VARCHAR2(50),
    SENDDEPTID          VARCHAR2(35),
    SENDROLEID          VARCHAR2(35),
    ENDROLEID           VARCHAR2(35),
    ENDDEPTID           VARCHAR2(35),
    TEMPLATEFLAG        VARCHAR2(20),
    CANCELREASON        VARCHAR2(100),
    SENDYEAR            NUMBER,
    SENDMONTH           NUMBER,
    SENDDAY             NUMBER,
    PARENTPHASENAME     VARCHAR2(50),
    INVOKEMODE          VARCHAR2(50),
    MAINPRODUCTINSTANCE VARCHAR2(1000),
    MAINCUSTOMERNAME    VARCHAR2(1000),
    MAINBUSINESSTYPE    VARCHAR2(1000),
    MAINCIRCUITCODE     VARCHAR2(1000),
    MAINFAILCITY        VARCHAR2(1000),
    MAINCHECKSTATE      VARCHAR2(1000),
    MAINRESERVED1       VARCHAR2(1000),
    MAINRESERVED2       VARCHAR2(1000),
    MAINRESERVED3       VARCHAR2(1000),
    MAINRESERVED4       VARCHAR2(1000),
    MAINRESERVED5       VARCHAR2(1000),
    mainSheetNun        VARCHAR2(1000),
    mainLineArea        VARCHAR2(1000),
    mainCaller          VARCHAR2(1000),
    SENDOBJECT          VARCHAR2(2000)
);
alter table FOCUSRESOURCEERRATA_MAIN
    add constraint FOCUSRESOURCEERRATA_MAIN_PK primary key (id) using index;



create table FOCUSRESOURCEERRATA_TASK
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
alter table FOCUSRESOURCEERRATA_TASK
    add constraint FOCUSRESOURCEERRATA_TASK_PK primary key (id) using index;


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
        '2c9048ab6347a50d016347a50dab0000');

--插入角色
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
values ('2c9048ab6347a50d016347a50dab0001', '199901', '1', '新增工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dab0002', '199903', '0', '待处理工单', '1999', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0003', '19990301', '1', '待办工单', '199903', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0004', '199907', '1', '工单查询', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0005', '199908', '1', '工单处理模板', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=getDealTemplatesByUserId&&type=templateManage', '0',
        '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0006', '199909', '1', '工单模板管理', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=getTemplatesByUserId&&type=templateManage', '0', '0',
        9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0007', '199910', '1', '管理者工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0008', '1999', '0', '集客资源勘误流程', '10', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0009', '199902', '1', '草稿列表', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac000a', '199904', '0', '已处理工单', '1999', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac000b', '19990402', '1', '建立工单', '199904', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac000c', '19990401', '1', '处理工单', '199904', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac000d', '199905', '1', '已归档工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac000e', '199906', '1', '已作废工单', '1999', null,
        '/sheet/focusresourceerrata/focusresourceerrata.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac000f', '199911', '1', '超时时间设置', '1999', null,
        '/sheet/overtimetip/overtimetip.do?flowName=FocusResourceErrata', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('2c9048ab6347a50d016347a50dac0010', '199912', '1', '工单处理时限配置', '1999', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&&flowName=FocusResourceErrata', '0', '0', 30);