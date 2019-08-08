create table EQUIPMENTSECURITYRESPONSE_LINK
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
    LINKMEASURESTIME     datetime year to second,
    LINKREMOVETIME       datetime year to second,
    LINKFAULTCAUSE       VARCHAR(1000),
    LINKDEALMEASURES     VARCHAR(1000),
    LINKIFSOLUTION       VARCHAR(1000),
    LINKREMARK           VARCHAR(1000),
    LINKQUALITYRESULT    VARCHAR(1000),
    LINKQUALITYVIEW      VARCHAR(1000),
    LINKREJECT           VARCHAR(1000),
    primary key (id)
);


create table EQUIPMENTSECURITYRESPONSE_MAIN
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
    MAINALARMTITLE     VARCHAR(2000),
    MAINALARMTIME      datetime year to second,
    MAINALARMTYPE      VARCHAR(2000),
    MAINALARMNAME      VARCHAR(2000),
    MAINALARMID        VARCHAR(2000),
    MAINORIGSOURCEID   VARCHAR(2000),
    MAINORIGPURPOSEID  VARCHAR(2000),
    MAINNOWSOURCEID    VARCHAR(2000),
    MAINNOWPURPOSEID   VARCHAR(2000),
    MAINALARMCONTENT   VARCHAR(2000),
    MAINEXTEND1        VARCHAR(2000),
    MAINEXTEND2        VARCHAR(2000),
    MAINEXTEND3        VARCHAR(2000),
    MAINEXTEND4        VARCHAR(2000),
    MAINEXTEND5        VARCHAR(2000),
    primary key (id)
);



create table EQUIPMENTSECURITYRESPONSE_TASK
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
create index i_equipmentsecurityresponse_a on equipmentsecurityresponse_task (taskowner, createtime) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_b on equipmentsecurityresponse_task (createtime) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_c on equipmentsecurityresponse_task (taskstatus) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_d on equipmentsecurityresponse_task (sheetkey) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_e on equipmentsecurityresponse_main (sendtime, senduserid) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_f on equipmentsecurityresponse_main (sheetid) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_g on equipmentsecurityresponse_link (aiid) TABLESPACE eomsdata;
create index i_equipmentsecurityresponse_h on equipmentsecurityresponse_link (mainid) TABLESPACE eomsdata;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('44', 'EquipmentSecurityResponse', '44', '44', '设备互联核查安全告警响应工单流程', 'iEquipmentSecurityResponseMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('32', '设备互联核查安全告警响应工单流程', '设备互联核查安全告警响应工单流程', 32);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'equipmentsecurityresponse', '设备互联核查安全告警响应工单流程', 1024,
        '/sheet/equipmentsecurityresponse', 32, '40287d8c5ba336af015ba336af8b0011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4401', '0', '0', '建单人', '1', '44', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4402', '0', '0', '审批人', '1', '44', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4403', '0', '0', '处理人', '1', '44', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('4404', '0', '0', '质检员', '1', '44', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8b0012', '4601', '1', '新增工单', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0013', '4603', '0', '待处理工单', '46', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0014', '460301', '1', '待办工单', '4603', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0015', '4607', '1', '工单查询', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0016', '4608', '1', '工单处理模板', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=getDealTemplatesByUserId&type=templateManage',
        '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0017', '4609', '1', '工单模板管理', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=getTemplatesByUserId&type=templateManage',
        '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0018', '4610', '1', '管理者工单', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0019', '46', '0', '设备互联核查安全告警响应工单流程', '10', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c001a', '4602', '1', '草稿列表', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c001b', '4604', '0', '已处理工单', '46', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c001c', '460402', '1', '建立工单', '4604', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c001d', '460401', '1', '处理工单', '4604', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c001e', '4605', '1', '已归档工单', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c001f', '4606', '1', '已作废工单', '46', null,
        '/sheet/equipmentsecurityresponse/equipmentsecurityresponse.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0020', '4611', '1', '超时时间设置', '46', null,
        '/sheet/overtimetip/overtimetip.do?flowName=EquipmentSecurityResponse', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d8c5ba336af015ba336af8c0021', '4612', '1', '工单处理时限配置', '46', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=EquipmentSecurityResponse', '0', '0', 30);