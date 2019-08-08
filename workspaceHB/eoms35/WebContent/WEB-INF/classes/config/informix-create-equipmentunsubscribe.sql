create table EQUIPMENTUNSUBSCRIBE_LINK
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
    LINKDESCR            VARCHAR(2000),
    LINKRESERVED1        VARCHAR(1000),
    LINKRESERVED2        VARCHAR(1000),
    LINKRESERVED3        VARCHAR(1000),
    LINKRESERVED4        VARCHAR(1000),
    LINKRESERVED5        VARCHAR(1000),
    LINKEQUIPMENTTYPE    VARCHAR(1000),
    LINKEQUIPMENTNUM     VARCHAR(1000),
    LINKEQUIPMENTTIME    datetime year to second,
    LINKSCENEPHOTO       VARCHAR(1000),
    LINKRETURNPHOTO      VARCHAR(1000),
    LINKPUTPHOTO         VARCHAR(1000),
    primary key (id)
);


create table EQUIPMENTUNSUBSCRIBE_MAIN
(
    ID                   VARCHAR(32) not null,
    SHEETID              VARCHAR(50),
    TITLE                VARCHAR(100),
    SHEETACCEPTLIMIT     datetime year to second,
    SHEETCOMPLETELIMIT   datetime year to second,
    SENDTIME             datetime year to second,
    SENDORGTYPE          VARCHAR(25),
    SENDUSERID           VARCHAR(30),
    SENDCONTACT          VARCHAR(30),
    SHEETACCESSORIES     VARCHAR(100),
    ENDTIME              datetime year to second,
    ENDRESULT            VARCHAR(30),
    STATUS               INTEGER,
    HOLDSTATISFIED       INTEGER,
    ENDUSERID            VARCHAR(50),
    DELETED              INTEGER,
    PIID                 VARCHAR(50),
    SHEETTEMPLATENAME    VARCHAR(50),
    PARENTSHEETNAME      VARCHAR(50),
    PARENTSHEETID        VARCHAR(50),
    CORRELATIONKEY       VARCHAR(50),
    PARENTCORRELATION    VARCHAR(50),
    TODEPTID             VARCHAR(50),
    SENDDEPTID           VARCHAR(35),
    SENDROLEID           VARCHAR(35),
    ENDROLEID            VARCHAR(35),
    ENDDEPTID            VARCHAR(35),
    TEMPLATEFLAG         VARCHAR(20),
    CANCELREASON         VARCHAR(100),
    SENDYEAR             INTEGER,
    SENDMONTH            INTEGER,
    SENDDAY              INTEGER,
    PARENTPHASENAME      VARCHAR(50),
    INVOKEMODE           VARCHAR(50),
    SENDOBJECT           CHAR(4000),
    MAINCOMPLAINTSHEETID VARCHAR(1000),
    MAINCOMPLAINTER      VARCHAR(1000),
    MAINCOMPLAINTNUM     VARCHAR(1000),
    MAINUSERSTAR         VARCHAR(1000),
    MAINEQUIPMENTTYPE    VARCHAR(1000),
    MAINCITY             VARCHAR(1000),
    MAINMINIID           VARCHAR(1000),
    MAINADDRESS          VARCHAR(1000),
    MAINCOMPLAINTREASONS VARCHAR(1000),
    MAINDISMANTLEREASONS VARCHAR(1000),
    MAINDESCR            VARCHAR(1000),
    MAINTYPICALSCENARIO  VARCHAR(1000),
    MAINCHILDSCENARIO    VARCHAR(1000),
    MAINISEQUIPMENTFEE   VARCHAR(1000),
    MAINSIGNPHOTO        VARCHAR(1000),
    MAINRESERVED1        VARCHAR(1000),
    MAINRESERVED2        VARCHAR(1000),
    MAINRESERVED3        VARCHAR(1000),
    MAINRESERVED4        VARCHAR(1000),
    MAINRESERVED5        VARCHAR(1000),
    primary key (id)
);



create table EQUIPMENTUNSUBSCRIBE_TASK
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
create index i_equipmentunsubscribe_a on equipmentunsubscribe_task (taskowner, createtime) TABLESPACE users;
create index i_equipmentunsubscribe_b on equipmentunsubscribe_task (createtime) TABLESPACE users;
create index i_equipmentunsubscribe_c on equipmentunsubscribe_task (taskstatus) TABLESPACE users;
create index i_equipmentunsubscribe_d on equipmentunsubscribe_task (sheetkey) TABLESPACE users;
create index i_equipmentunsubscribe_e on equipmentunsubscribe_main (sendtime, senduserid) TABLESPACE users;
create index i_equipmentunsubscribe_f on equipmentunsubscribe_main (sheetid) TABLESPACE users;
create index i_equipmentunsubscribe_g on equipmentunsubscribe_link (aiid) TABLESPACE users;
create index i_equipmentunsubscribe_h on equipmentunsubscribe_link (mainid) TABLESPACE users;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('25', 'EquipmentUnsubscribe', '25', '25', '设备退订流程流程', 'iEquipmentUnsubscribeMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('34', '设备退订流程流程', '设备退订流程流程', 34);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'equipmentunsubscribe', '设备退订流程流程', 1024, '/sheet/equipmentunsubscribe', 34,
        '297e589966577f7d0166577f7e100011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('149', '0', '0', '建单人', '1', '25', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('150', '0', '0', '分公司网优中心主任', '1', '25', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('151', '0', '0', '分公司代维人员', '1', '25', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100012', '199901', '1', '新增工单', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100013', '199903', '0', '待处理工单', '1999', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100014', '19990301', '1', '待办工单', '199903', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100015', '199907', '1', '工单查询', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100016', '199908', '1', '工单处理模板', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=getDealTemplatesByUserId&type=templateManage', '0',
        '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100017', '199909', '1', '工单模板管理', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=getTemplatesByUserId&type=templateManage', '0', '0',
        9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100018', '199910', '1', '管理者工单', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100019', '1999', '0', '设备退订流程流程', '10', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e10001a', '199902', '1', '草稿列表', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e10001b', '199904', '0', '已处理工单', '1999', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e10001c', '19990402', '1', '建立工单', '199904', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e10001d', '19990401', '1', '处理工单', '199904', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e10001e', '199905', '1', '已归档工单', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e10001f', '199906', '1', '已作废工单', '1999', null,
        '/sheet/equipmentunsubscribe/equipmentunsubscribe.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100020', '199911', '1', '超时时间设置', '1999', null,
        '/sheet/overtimetip/overtimetip.do?flowName=EquipmentUnsubscribe', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('297e589966577f7d0166577f7e100021', '199912', '1', '工单处理时限配置', '1999', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=EquipmentUnsubscribe', '0', '0', 30);