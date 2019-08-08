create table WIDENCOMPLAINT_LINK
(
    ID                       VARCHAR(32) not null,
    MAINID                   VARCHAR(50),
    NODEACCEPTLIMIT          datetime year to second,
    NODECOMPLETELIMIT        datetime year to second,
    OPERATETYPE              INTEGER,
    OPERATETIME              datetime year to second,
    OPERATEUSERID            VARCHAR(30),
    OPERATEORGTYPE           VARCHAR(10),
    OPERATERCONTACT          VARCHAR(50),
    TOORGTYPE                INTEGER,
    TOORGUSERID              VARCHAR(50),
    TOROLEID                 INTEGER,
    ACCEPTFLAG               INTEGER,
    ACCEPTTIME               datetime year to second,
    COMPLETEFLAG             INTEGER,
    COMPLETETIME             datetime year to second,
    PRELINKID                VARCHAR(50),
    PARENTLINKID             VARCHAR(50),
    FIRSTLINKID              VARCHAR(50),
    PIID                     VARCHAR(50),
    AIID                     VARCHAR(50),
    ACTIVETEMPLATEID         VARCHAR(50),
    NODETEMPLATENAME         VARCHAR(50),
    NODEACCESSORIES          VARCHAR(50),
    TOORGDEPTID              VARCHAR(35),
    TOORGROLEID              VARCHAR(35),
    OPERATEROLEID            VARCHAR(35),
    OPERATEDEPTID            VARCHAR(35),
    CORRELATIONKEY           VARCHAR(50),
    TEMPLATEFLAG             VARCHAR(20),
    TEMPLATENAME             VARCHAR(100),
    TEMPLATECREATEUSERID     VARCHAR(100),
    TRANSFERREASON           VARCHAR(200),
    REMARK                   VARCHAR(200),
    OPERATEYEAR              INTEGER,
    OPERATEMONTH             INTEGER,
    OPERATEDAY               INTEGER,
    LINKSENDOBJECT           CHAR(4000),
    LINKTRANSMITREASON       VARCHAR(2000),
    LINKIFINVOKECHANGE       VARCHAR(2000),
    LINKFAULTSTARTTIME       datetime year to second,
    LINKFAULTENDTIME         datetime year to second,
    LINKFAULTGENERANTPLACE   VARCHAR(2000),
    LINKPLACEDESC            VARCHAR(2000),
    NDEPTCONTACT             VARCHAR(2000),
    NDEPTCONTACTPHONE        VARCHAR(2000),
    DEALRESULT               VARCHAR(2000),
    DEALDESC                 VARCHAR(2000),
    ISSUEELIMINATTIME        datetime year to second,
    ISSUEELIMINATREASON      VARCHAR(2000),
    LINKCHECKRESULT          VARCHAR(2000),
    LINKCHECKIDEA            VARCHAR(2000),
    LINKUNTREADREASON        VARCHAR(2000),
    LINKTRANSMITCONTENT      VARCHAR(2000),
    LINKEXAMINECONTENT       VARCHAR(2000),
    LINKIFDEFERRESOLVE       VARCHAR(2000),
    LINKIFINVOKECASEDATABASE VARCHAR(2000),
    LINKCHANGESHEETID        VARCHAR(2000),
    ISSUBTRANSMIT            VARCHAR(2000),
    PARLINKID                VARCHAR(2000),
    ISREPLIED                VARCHAR(2000),
    COMPPROP                 VARCHAR(2000),
    ISDEFERREPLOY            VARCHAR(2000),
    ISSUEREASONTYPEONE       VARCHAR(2000),
    ISSUEREASONTYPETWO       VARCHAR(2000),
    ISSUEREASONTYPETHREE     VARCHAR(2000),
    ISSUEREASONTYPEFOUR      VARCHAR(2000),
    LINKREPLYPERSON          VARCHAR(2000),
    LINKREPLAYPHONE          VARCHAR(2000),
    LINKDEALDESC             VARCHAR(2000),
    LINKBUSINESSTYPE         VARCHAR(2000),
    LINKFAULTTYPE            VARCHAR(2000),
    LINKREASONTYPE           VARCHAR(2000),
    LINKISRECIVEFAULTID      VARCHAR(2000),
    LINKRECIVEFAULTID        VARCHAR(2000),
    LINKISCOMPLAINTSOLVE     VARCHAR(2000),
    LINKCOMPLAINTSOLVEDATE   datetime year to second,
    LINKPLANSOLVETYPEPARENT  VARCHAR(2000),
    LINKPLANSOLVETYPE        VARCHAR(2000),
    LINKPLANSOLVEDATE        datetime year to second,
    LINKISUSERCONFIRM        VARCHAR(2000),
    LINKNOCONFIRMREASON      VARCHAR(2000),
    LINKISREPEATCOMPLAINT    VARCHAR(2000),
    LINKREPEATCOMPLAINTTYPE  VARCHAR(2000),
    LINKISUSERSTATISFIED     VARCHAR(2000),
    LINKUSERNOSTATISFIED     VARCHAR(2000),
    LINKISCONTACTUSER        VARCHAR(2000),
    LINKCONTACTDATE          datetime year to second,
    LINKCONTACTSHIP          VARCHAR(2000),
    LINKCONTACTUSER          VARCHAR(2000),
    LINKCONTACTPHONE         VARCHAR(2000),
    LINKNOCONTACTREASON      VARCHAR(2000),
    LINKADDRESSCI            VARCHAR(2000),
    LINKADDRESSNAME          VARCHAR(2000),
    LINKEQUIPMENTTYPE        VARCHAR(2000),
    LINKEQUIPMENTFACTORY     VARCHAR(2000),
    LINKISALARM              VARCHAR(2000),
    LINKALARMDETAIL          VARCHAR(2000),
    LINKCOMMONFAULTNUMBER    VARCHAR(2000),
    LINKCOVERDIAN            VARCHAR(2000),
    LINKCOVERLIAN            VARCHAR(2000),
    LINKSPECIALTY            VARCHAR(2000),
    LINKQUALITY              VARCHAR(2000),
    AINETREASONDESC          VARCHAR(2000),
    AINETRESULT              VARCHAR(2000),
    SELECTAINETREASON        VARCHAR(2000),
    LINKIFAINET              VARCHAR(2000),
    primary key (id)
);


create table WIDENCOMPLAINT_MAIN
(
    ID                      VARCHAR(32) not null,
    SHEETID                 VARCHAR(50),
    TITLE                   VARCHAR(100),
    SHEETACCEPTLIMIT        datetime year to second,
    SHEETCOMPLETELIMIT      datetime year to second,
    SENDTIME                datetime year to second,
    SENDORGTYPE             VARCHAR(25),
    SENDUSERID              VARCHAR(30),
    SENDCONTACT             VARCHAR(30),
    SHEETACCESSORIES        VARCHAR(100),
    ENDTIME                 datetime year to second,
    ENDRESULT               VARCHAR(30),
    STATUS                  INTEGER,
    HOLDSTATISFIED          INTEGER,
    ENDUSERID               VARCHAR(50),
    DELETED                 INTEGER,
    PIID                    VARCHAR(50),
    SHEETTEMPLATENAME       VARCHAR(50),
    PARENTSHEETNAME         VARCHAR(50),
    PARENTSHEETID           VARCHAR(50),
    CORRELATIONKEY          VARCHAR(50),
    PARENTCORRELATION       VARCHAR(50),
    TODEPTID                VARCHAR(50),
    SENDDEPTID              VARCHAR(35),
    SENDROLEID              VARCHAR(35),
    ENDROLEID               VARCHAR(35),
    ENDDEPTID               VARCHAR(35),
    TEMPLATEFLAG            VARCHAR(20),
    CANCELREASON            VARCHAR(100),
    SENDYEAR                INTEGER,
    SENDMONTH               INTEGER,
    SENDDAY                 INTEGER,
    PARENTPHASENAME         VARCHAR(50),
    INVOKEMODE              VARCHAR(50),
    SENDOBJECT              CHAR(4000),
    DEALTIME1               datetime year to second,
    DEALTIME2               datetime year to second,
    URGENTDEGREE            VARCHAR(2000),
    BTYPE1                  VARCHAR(2000),
    BDEPTCONTACT            VARCHAR(2000),
    CUSTOMERNAME            VARCHAR(2000),
    CUSTOMPHONE             VARCHAR(2000),
    COMPLAINTTIME           datetime year to second,
    FAULTTIME               datetime year to second,
    COMPLAINTADD            VARCHAR(2000),
    COMPLAINTDESC           VARCHAR(2000),
    BDEPTCONTACTPHONE       VARCHAR(2000),
    REPEATCOMPLAINTTIMES    VARCHAR(2000),
    COMPLAINTTYPE1          VARCHAR(2000),
    COMPLAINTTYPE2          VARCHAR(2000),
    COMPLAINTTYPE           VARCHAR(2000),
    COMPLAINTREASON1        VARCHAR(2000),
    COMPLAINTREASON2        VARCHAR(2000),
    COMPLAINTREASON         VARCHAR(2000),
    CUSTOMTYPE              VARCHAR(2000),
    CUSTOMBRAND             VARCHAR(2000),
    CUSTOMLEVEL             VARCHAR(2000),
    CUSTOMATTRIBUTION       VARCHAR(2000),
    STARTDEALCITY           VARCHAR(2000),
    CALLERNO                VARCHAR(2000),
    CALLERREGISTVLR         VARCHAR(2000),
    CALLERDIALUPTYPE        VARCHAR(2000),
    CALLERFAULT             VARCHAR(2000),
    CALLERCALLOTHERDESC     VARCHAR(2000),
    AROUNDUSERDESC          VARCHAR(2000),
    CALLERISINTELLIGENTUSER VARCHAR(2000),
    CALLEDPARTYNO           VARCHAR(2000),
    CALLEDPARTYREGISTVLR    VARCHAR(2000),
    OTHERUSERDESC           VARCHAR(2000),
    CALLEDPARTYCALLC        VARCHAR(2000),
    DEALADVICE              VARCHAR(2000),
    MAINIFDEFERRESOLVE      VARCHAR(2000),
    MAINCOMPLETELIMITT1     datetime year to second,
    MAINCOMPLETELIMITT2     datetime year to second,
    MAINDELAYFLAG           VARCHAR(2000),
    MAINLASTREPEATTIME      datetime year to second,
    MAINIFCHECK             VARCHAR(2000),
    MAININTERFACESHEETTYPE  VARCHAR(2000),
    FAULTDESC               VARCHAR(2000),
    FAULTAREA               VARCHAR(2000),
    FAULTROAD               VARCHAR(2000),
    FAULTNO                 VARCHAR(2000),
    FAULTROAD1              VARCHAR(2000),
    FAULTROAD2              VARCHAR(2000),
    FAULTVILL               VARCHAR(2000),
    ISVISIT                 VARCHAR(2000),
    MAINCHECKRESULT         VARCHAR(2000),
    MAINCHECKIDEA           VARCHAR(2000),
    COMPLAINTTYPE4          VARCHAR(2000),
    COMPLAINTTYPE5          VARCHAR(2000),
    COMPLAINTTYPE6          VARCHAR(2000),
    COMPLAINTTYPE7          VARCHAR(2000),
    COMPLAINTTYPEKF         VARCHAR(2000),
    MAINIFMANUALCHECK       VARCHAR(2000),
    TERMINALTYPE            VARCHAR(2000),
    PREDEALRESULT           VARCHAR(2000),
    COMPLAINTNUM            VARCHAR(2000),
    FAULTSITE               VARCHAR(2000),
    IFAGENT                 VARCHAR(2000),
    REVERT                  VARCHAR(2000),
    MAINT2APPLYTIME         datetime year to second,
    MAINT1DEALTIME          datetime year to second,
    MAINACTIVATETIME        datetime year to second,
    MAINOLDCOMPLETELIMIT    datetime year to second,
    MAINACTIVATEDEALER      VARCHAR(2000),
    MAINSLEEPTIME           VARCHAR(2000),
    MAINSLEEPTKID           VARCHAR(2000),
    MAINT1DEALER            VARCHAR(2000),
    MAINSLEEPUSER           VARCHAR(2000),
    MAINSLEEPREASON         VARCHAR(2000),
    primary key (id)
);



create table WIDENCOMPLAINT_TASK
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
create index i_widencomplaint_a on widencomplaint_task (taskowner, createtime) TABLESPACE EOMSDATA;
create index i_widencomplaint_b on widencomplaint_task (createtime) TABLESPACE EOMSDATA;
create index i_widencomplaint_c on widencomplaint_task (taskstatus) TABLESPACE EOMSDATA;
create index i_widencomplaint_d on widencomplaint_task (sheetkey) TABLESPACE EOMSDATA;
create index i_widencomplaint_e on widencomplaint_main (sendtime, senduserid) TABLESPACE EOMSDATA;
create index i_widencomplaint_f on widencomplaint_main (sheetid) TABLESPACE EOMSDATA;
create index i_widencomplaint_g on widencomplaint_link (aiid) TABLESPACE EOMSDATA;
create index i_widencomplaint_h on widencomplaint_link (mainid) TABLESPACE EOMSDATA;


--插入流程SQL语句
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values ('58', 'WidenComplaint', '58', '58', '加宽投诉处理工单流程', 'iWidenComplaintMainManager');

--插入附件
insert into TAW_SYSTEM_APPLICATION (APP_ID, APP_NAME, NOTES, DOMAIN_TYPE)
values ('106', '加宽投诉处理工单流程', '加宽投诉处理工单流程', 106);

insert into TAW_COMMONS_ACCESSORIESCONFIG (ALLOWFILETYPE, APPCODE, APPNAME, MAXSIZE, PATH, APPID, ID)
values ('xls,txt,doc,jpg,gif', 'widencomplaint', '加宽投诉处理工单流程', 1024, '/sheet/widencomplaint', 106,
        '40287d82529c186f01529c186fe00011');


--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1740', '0', '0', '建单人', '1', '58', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1741', '0', '0', '投诉处理组', '1', '58', '1');


--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00012', '5601', '1', '新增工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00013', '5603', '0', '待处理工单', '56', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00014', '560301', '1', '待办工单', '5603', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00015', '5607', '1', '工单查询', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00016', '5608', '1', '工单处理模板', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00017', '5609', '1', '工单模板管理', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00018', '5610', '1', '管理者工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00019', '56', '0', '加宽投诉处理工单流程', '10', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe0001a', '5602', '1', '草稿列表', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe0001b', '5604', '0', '已处理工单', '56', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe0001c', '560402', '1', '建立工单', '5604', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe0001d', '560401', '1', '处理工单', '5604', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe0001e', '5605', '1', '已归档工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe0001f', '5606', '1', '已作废工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00020', '5611', '1', '超时时间设置', '56', null,
        '/sheet/overtimetip/overtimetip.do?flowName=WidenComplaint', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fe00021', '5612', '1', '工单处理时限配置', '56', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=WidenComplaint', '0', '0', 30);