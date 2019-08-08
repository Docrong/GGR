create table WIDENCOMPLAINT_LINK
(
    ID                       VARCHAR2(32) not null,
    MAINID                   VARCHAR2(50),
    NODEACCEPTLIMIT          DATE,
    NODECOMPLETELIMIT        DATE,
    OPERATETYPE              NUMBER,
    OPERATETIME              DATE,
    OPERATEUSERID            VARCHAR2(30),
    OPERATEORGTYPE           VARCHAR2(10),
    OPERATERCONTACT          VARCHAR2(50),
    TOORGTYPE                NUMBER,
    TOORGUSERID              VARCHAR2(50),
    TOROLEID                 NUMBER,
    ACCEPTFLAG               NUMBER,
    ACCEPTTIME               DATE,
    COMPLETEFLAG             NUMBER,
    COMPLETETIME             DATE,
    PRELINKID                VARCHAR2(50),
    PARENTLINKID             VARCHAR2(50),
    FIRSTLINKID              VARCHAR2(50),
    PIID                     VARCHAR2(50),
    AIID                     VARCHAR2(50),
    ACTIVETEMPLATEID         VARCHAR2(50),
    NODETEMPLATENAME         VARCHAR2(50),
    NODEACCESSORIES          VARCHAR2(50),
    TOORGDEPTID              VARCHAR2(35),
    TOORGROLEID              VARCHAR2(35),
    OPERATEROLEID            VARCHAR2(35),
    OPERATEDEPTID            VARCHAR2(35),
    CORRELATIONKEY           VARCHAR2(50),
    TEMPLATEFLAG             VARCHAR2(20),
    TEMPLATENAME             VARCHAR2(100),
    TEMPLATECREATEUSERID     VARCHAR2(100),
    TRANSFERREASON           VARCHAR2(2000),
    REMARK                   VARCHAR2(2000),
    OPERATEYEAR              NUMBER,
    OPERATEMONTH             NUMBER,
    OPERATEDAY               NUMBER,
    LINKTRANSMITREASON       VARCHAR2(2000),
    LINKIFINVOKECHANGE       VARCHAR2(2000),
    LINKFAULTSTARTTIME       DATE,
    LINKFAULTENDTIME         DATE,
    LINKFAULTGENERANTPLACE   VARCHAR2(2000),
    LINKPLACEDESC            VARCHAR2(2000),
    NDEPTCONTACT             VARCHAR2(2000),
    NDEPTCONTACTPHONE        VARCHAR2(2000),
    DEALRESULT               VARCHAR2(2000),
    DEALDESC                 VARCHAR2(2000),
    ISSUEELIMINATTIME        DATE,
    ISSUEELIMINATREASON      VARCHAR2(2000),
    LINKCHECKRESULT          VARCHAR2(2000),
    LINKCHECKIDEA            VARCHAR2(2000),
    LINKUNTREADREASON        VARCHAR2(2000),
    LINKTRANSMITCONTENT      VARCHAR2(2000),
    LINKEXAMINECONTENT       VARCHAR2(2000),
    LINKIFDEFERRESOLVE       VARCHAR2(2000),
    LINKIFINVOKECASEDATABASE VARCHAR2(2000),
    LINKCHANGESHEETID        VARCHAR2(2000),
    ISSUBTRANSMIT            VARCHAR2(2000),
    PARLINKID                VARCHAR2(2000),
    ISREPLIED                VARCHAR2(2000),
    COMPPROP                 VARCHAR2(2000),
    ISDEFERREPLOY            VARCHAR2(2000),
    ISSUEREASONTYPEONE       VARCHAR2(2000),
    ISSUEREASONTYPETWO       VARCHAR2(2000),
    ISSUEREASONTYPETHREE     VARCHAR2(2000),
    ISSUEREASONTYPEFOUR      VARCHAR2(2000),
    LINKREPLYPERSON          VARCHAR2(2000),
    LINKREPLAYPHONE          VARCHAR2(2000),
    LINKDEALDESC             VARCHAR2(2000),
    LINKBUSINESSTYPE         VARCHAR2(2000),
    LINKFAULTTYPE            VARCHAR2(2000),
    LINKREASONTYPE           VARCHAR2(2000),
    LINKISRECIVEFAULTID      VARCHAR2(2000),
    LINKRECIVEFAULTID        VARCHAR2(2000),
    LINKISCOMPLAINTSOLVE     VARCHAR2(2000),
    LINKCOMPLAINTSOLVEDATE   DATE,
    LINKPLANSOLVETYPEPARENT  VARCHAR2(2000),
    LINKPLANSOLVETYPE        VARCHAR2(2000),
    LINKPLANSOLVEDATE        DATE,
    LINKISUSERCONFIRM        VARCHAR2(2000),
    LINKNOCONFIRMREASON      VARCHAR2(2000),
    LINKISREPEATCOMPLAINT    VARCHAR2(2000),
    LINKREPEATCOMPLAINTTYPE  VARCHAR2(2000),
    LINKISUSERSTATISFIED     VARCHAR2(2000),
    LINKUSERNOSTATISFIED     VARCHAR2(2000),
    LINKISCONTACTUSER        VARCHAR2(2000),
    LINKCONTACTDATE          DATE,
    LINKCONTACTSHIP          VARCHAR2(2000),
    LINKCONTACTUSER          VARCHAR2(2000),
    LINKCONTACTPHONE         VARCHAR2(2000),
    LINKNOCONTACTREASON      VARCHAR2(2000),
    LINKADDRESSCI            VARCHAR2(2000),
    LINKADDRESSNAME          VARCHAR2(2000),
    LINKEQUIPMENTTYPE        VARCHAR2(2000),
    LINKEQUIPMENTFACTORY     VARCHAR2(2000),
    LINKISALARM              VARCHAR2(2000),
    LINKALARMDETAIL          VARCHAR2(2000),
    LINKCOMMONFAULTNUMBER    VARCHAR2(2000),
    LINKCOVERDIAN            VARCHAR2(2000),
    LINKCOVERLIAN            VARCHAR2(2000),
    LINKSPECIALTY            VARCHAR2(2000),
    LINKQUALITY              VARCHAR2(2000),
    AINETREASONDESC          VARCHAR2(2000),
    AINETRESULT              VARCHAR2(2000),
    SELECTAINETREASON        VARCHAR2(2000),
    LINKIFAINET              VARCHAR2(2000),
    LINKSENDOBJECT           VARCHAR2(2000)
);
alter table WIDENCOMPLAINT_LINK
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



create table WIDENCOMPLAINT_MAIN
(
    ID                      VARCHAR2(32) not null,
    SHEETID                 VARCHAR2(50),
    TITLE                   VARCHAR2(100),
    SHEETACCEPTLIMIT        DATE,
    SHEETCOMPLETELIMIT      DATE,
    SENDTIME                DATE,
    SENDORGTYPE             VARCHAR2(25),
    SENDUSERID              VARCHAR2(30),
    SENDCONTACT             VARCHAR2(30),
    SHEETACCESSORIES        VARCHAR2(100),
    ENDTIME                 DATE,
    ENDRESULT               VARCHAR2(30),
    STATUS                  NUMBER,
    HOLDSTATISFIED          NUMBER,
    ENDUSERID               VARCHAR2(50),
    DELETED                 NUMBER,
    PIID                    VARCHAR2(50),
    SHEETTEMPLATENAME       VARCHAR2(50),
    PARENTSHEETNAME         VARCHAR2(50),
    PARENTSHEETID           VARCHAR2(50),
    CORRELATIONKEY          VARCHAR2(50),
    PARENTCORRELATION       VARCHAR2(50),
    TODEPTID                VARCHAR2(50),
    SENDDEPTID              VARCHAR2(35),
    SENDROLEID              VARCHAR2(35),
    ENDROLEID               VARCHAR2(35),
    ENDDEPTID               VARCHAR2(35),
    TEMPLATEFLAG            VARCHAR2(20),
    CANCELREASON            VARCHAR2(100),
    SENDYEAR                NUMBER,
    SENDMONTH               NUMBER,
    SENDDAY                 NUMBER,
    PARENTPHASENAME         VARCHAR2(50),
    INVOKEMODE              VARCHAR2(50),
    DEALTIME1               DATE,
    DEALTIME2               DATE,
    URGENTDEGREE            VARCHAR2(2000),
    BTYPE1                  VARCHAR2(2000),
    BDEPTCONTACT            VARCHAR2(2000),
    CUSTOMERNAME            VARCHAR2(2000),
    CUSTOMPHONE             VARCHAR2(2000),
    COMPLAINTTIME           DATE,
    FAULTTIME               DATE,
    COMPLAINTADD            VARCHAR2(2000),
    COMPLAINTDESC           VARCHAR2(2000),
    BDEPTCONTACTPHONE       VARCHAR2(2000),
    REPEATCOMPLAINTTIMES    VARCHAR2(2000),
    COMPLAINTTYPE1          VARCHAR2(2000),
    COMPLAINTTYPE2          VARCHAR2(2000),
    COMPLAINTTYPE           VARCHAR2(2000),
    COMPLAINTREASON1        VARCHAR2(2000),
    COMPLAINTREASON2        VARCHAR2(2000),
    COMPLAINTREASON         VARCHAR2(2000),
    CUSTOMTYPE              VARCHAR2(2000),
    CUSTOMBRAND             VARCHAR2(2000),
    CUSTOMLEVEL             VARCHAR2(2000),
    CUSTOMATTRIBUTION       VARCHAR2(2000),
    STARTDEALCITY           VARCHAR2(2000),
    CALLERNO                VARCHAR2(2000),
    CALLERREGISTVLR         VARCHAR2(2000),
    CALLERDIALUPTYPE        VARCHAR2(2000),
    CALLERFAULT             VARCHAR2(2000),
    CALLERCALLOTHERDESC     VARCHAR2(2000),
    AROUNDUSERDESC          VARCHAR2(2000),
    CALLERISINTELLIGENTUSER VARCHAR2(2000),
    CALLEDPARTYNO           VARCHAR2(2000),
    CALLEDPARTYREGISTVLR    VARCHAR2(2000),
    OTHERUSERDESC           VARCHAR2(2000),
    CALLEDPARTYCALLC        VARCHAR2(2000),
    DEALADVICE              VARCHAR2(2000),
    MAINIFDEFERRESOLVE      VARCHAR2(2000),
    MAINCOMPLETELIMITT1     DATE,
    MAINCOMPLETELIMITT2     DATE,
    MAINDELAYFLAG           VARCHAR2(2000),
    MAINLASTREPEATTIME      DATE,
    MAINIFCHECK             VARCHAR2(2000),
    MAININTERFACESHEETTYPE  VARCHAR2(2000),
    FAULTDESC               VARCHAR2(2000),
    FAULTAREA               VARCHAR2(2000),
    FAULTROAD               VARCHAR2(2000),
    FAULTNO                 VARCHAR2(2000),
    FAULTROAD1              VARCHAR2(2000),
    FAULTROAD2              VARCHAR2(2000),
    FAULTVILL               VARCHAR2(2000),
    ISVISIT                 VARCHAR2(2000),
    MAINCHECKRESULT         VARCHAR2(2000),
    MAINCHECKIDEA           VARCHAR2(2000),
    COMPLAINTTYPE4          VARCHAR2(2000),
    COMPLAINTTYPE5          VARCHAR2(2000),
    COMPLAINTTYPE6          VARCHAR2(2000),
    COMPLAINTTYPE7          VARCHAR2(2000),
    COMPLAINTTYPEKF         VARCHAR2(2000),
    MAINIFMANUALCHECK       VARCHAR2(2000),
    TERMINALTYPE            VARCHAR2(2000),
    PREDEALRESULT           VARCHAR2(2000),
    COMPLAINTNUM            VARCHAR2(2000),
    FAULTSITE               VARCHAR2(2000),
    IFAGENT                 VARCHAR2(2000),
    REVERT                  VARCHAR2(2000),
    MAINT2APPLYTIME         DATE,
    MAINT1DEALTIME          DATE,
    MAINACTIVATETIME        DATE,
    MAINOLDCOMPLETELIMIT    DATE,
    MAINACTIVATEDEALER      VARCHAR2(2000),
    MAINSLEEPTIME           VARCHAR2(2000),
    MAINSLEEPTKID           VARCHAR2(2000),
    MAINT1DEALER            VARCHAR2(2000),
    MAINSLEEPUSER           VARCHAR2(2000),
    MAINSLEEPREASON         VARCHAR2(2000),
    SENDOBJECT              VARCHAR2(2000)
);
alter table WIDENCOMPLAINT_MAIN
    add constraint WIDENCOMPLAINT_MAIN_PK primary key (id) using index;



create table WIDENCOMPLAINT_TASK
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
alter table WIDENCOMPLAINT_TASK
    add constraint WIDENCOMPLAINT_TASK_PK primary key (id) using index;


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
        '40287d82529c186f01529c186fd90000');

--插入角色
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1740', '0', '0', '建单人', '1', '58', '1');
insert into taw_system_role(role_id, deleted, parent_id, role_name, roletype_id, workflow_flag, leaf)
values ('1741', '0', '0', '投诉处理组', '1', '58', '1');

--插入菜单
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90001', '5601', '1', '新增工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showNewSheetPage', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90002', '5603', '0', '待处理工单', '56', null, null, '0', '0', 3);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90003', '560301', '1', '待办工单', '5603', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showListsendundo', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90004', '5607', '1', '工单查询', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showQueryPage', '0', '0', 7);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90005', '5608', '1', '工单处理模板', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=getDealTemplatesByUserId&type=templateManage', '0', '0', 8);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90006', '5609', '1', '工单模板管理', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=getTemplatesByUserId&type=templateManage', '0', '0', 9);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90007', '5610', '1', '管理者工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showListForAdmin', '0', '0', 10);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90008', '56', '0', '加宽投诉处理工单流程', '10', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showDrawing', '0', '0', 20);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90009', '5602', '1', '草稿列表', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showDraftList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd9000a', '5604', '0', '已处理工单', '56', null, null, '0', '0', 4);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd9000b', '560402', '1', '建立工单', '5604', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showOwnStarterList', '0', '0', 2);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd9000c', '560401', '1', '处理工单', '5604', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showListsenddone', '0', '0', 1);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd9000d', '5605', '1', '已归档工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showHoldedList', '0', '0', 5);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd9000e', '5606', '1', '已作废工单', '56', null,
        '/sheet/widencomplaint/widencomplaint.do?method=showCancelList', '0', '0', 6);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd9000f', '5611', '1', '超时时间设置', '56', null,
        '/sheet/overtimetip/overtimetip.do?flowName=WidenComplaint', '0', '0', 11);
insert into TAW_SYSTEM_PRIV_OPERATION (ID, CODE, ISAPP, NAME, PARENTCODE, REMARK, URL, DELETED, HIDED, ORDERBY)
values ('40287d82529c186f01529c186fd90010', '5612', '1', '工单处理时限配置', '56', null,
        '/sheet/newSheetLimit/sheetLimit.do?method=getLevelLimitList&flowName=WidenComplaint', '0', '0', 30);