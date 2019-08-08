--ITSoftChange-START--
drop table itsoftchange_main;
create table itsoftchange_main
(
    id                  VARCHAR(32),
    sheetid             VARCHAR(50),
    title               VARCHAR(100),
    sheetacceptlimit    DATE,
    sheetcompletelimit  DATE,
    sendtime            DATE,
    sendorgtype         VARCHAR2(25),
    senduserid          VARCHAR(50),
    sendcontact         VARCHAR(50),
    sheetaccessories    VARCHAR(300),
    endtime             DATE,
    endresult           VARCHAR(500),
    status              INTEGER,
    holdstatisfied      INTEGER,
    enduserid           VARCHAR(50),
    deleted             INTEGER,
    piid                VARCHAR(50),
    sheettemplatename   VARCHAR(50),
    parentsheetname     VARCHAR(50),
    parentsheetid       VARCHAR(50),
    correlationkey      VARCHAR(50),
    parentcorrelation   VARCHAR(50),
    todeptid            VARCHAR(50),
    senddeptid          VARCHAR(35),
    sendroleid          VARCHAR(35),
    endroleid           VARCHAR(35),
    enddeptid           VARCHAR(35),
    templateFlag        VARCHAR(20),
    cancelReason        VARCHAR(500),

    SENDYEAR            NUMBER DEFAULT 0 NULL,
    SENDMONTH           NUMBER DEFAULT 0 NULL,
    SENDDAY             NUMBER DEFAULT 0 NULL,
    PARENTPHASENAME     VARCHAR2(50)     NULL,
    INVOKEMODE          VARCHAR2(50)     NULL,

    mainITRequirementID VARCHAR(50),

    mainIsReferCost     VARCHAR(50),

    mainNetSystem       VARCHAR(50),

    mainChangeDesc      VARCHAR(500),

    mainCompleteTime    DATE,

    mainApplyer         VARCHAR(50),

    mainChangeDetail    VARCHAR(300),

    primary key (id)
);

drop table itsoftchange_link;
create table itsoftchange_link
(
    id                   VARCHAR(32),
    mainid               VARCHAR(50),
    nodeacceptlimit      DATE,
    nodecompletelimit    DATE,
    operatetype          INTEGER,
    operatetime          DATE,
    operateuserid        VARCHAR(30),
    operaterContact      VARCHAR(50),
    operateorgtype       INTEGER,
    toorgtype            INTEGER,
    toorguserid          VARCHAR(50),
    toroleid             INTEGER,
    acceptflag           INTEGER,
    accepttime           DATE,
    completeflag         INTEGER,
    completetime         DATE,
    prelinkid            VARCHAR(50),
    parentlinkid         VARCHAR(50),
    firstlinkid          VARCHAR(50),
    piid                 VARCHAR(50),
    aiid                 VARCHAR(50),
    activetemplateid     VARCHAR(50),
    nodetemplatename     VARCHAR(50),
    nodeaccessories      VARCHAR(50),
    toorgdeptid          VARCHAR(35),
    toorgroleid          VARCHAR(35),
    operateroleid        VARCHAR(35),
    operatedeptid        VARCHAR(35),
    correlationkey       VARCHAR(50),
    templateFlag         VARCHAR(20),
    templateName         VARCHAR(100),
    templateCreateUserId VARCHAR(100),
    transferReason       VARCHAR(500),
    remark               VARCHAR(500),
    OPERATERCONTACT      VARCHAR2(50)            NULL,
    OPERATEYEAR          NUMBER(15, 5) DEFAULT 0 NULL,
    OPERATEMONTH         NUMBER(15, 5) DEFAULT 0 NULL,
    OPERATEDAY           NUMBER(15, 5) DEFAULT 0 NULL,

    linkDevContacter     VARCHAR(50),

    linkCompleteDesc     VARCHAR(500),

    linkTestGuide        VARCHAR(300),

    linkTestResult       VARCHAR(50),

    linkTestSatisfaction VARCHAR(50),

    linkTestDesc         VARCHAR(500),

    linkTestNote         VARCHAR(300),

    linkOnlineTime       DATE,

    linkOnlineDesc       VARCHAR(50),

    linkUserNoteDesc     VARCHAR(300),

    linkAuditResult      VARCHAR(50),

    linkAnalysisCount    VARCHAR(50),

    linkTestCount        VARCHAR(50),

    linkDevCount         VARCHAR(50),

    primary key (id)
);

create table itsoftchange_task
(
    ID                VARCHAR2(50)            NOT NULL,
    TASKNAME          VARCHAR2(50)            NULL,
    TASKDISPLAYNAME   VARCHAR2(50)            NULL,
    CREATETIME        TIMESTAMP(6)            NULL,
    TASKSTATUS        VARCHAR2(50)            NULL,
    PROCESSID         VARCHAR2(50)            NULL,
    SHEETKEY          VARCHAR2(50)            NULL,
    SHEETID           VARCHAR2(50)            NULL,
    TITLE             VARCHAR2(50)            NULL,
    ACCEPTTIMELIMIT   TIMESTAMP(6)            NULL,
    COMPLETETIMELIMIT TIMESTAMP(6)            NULL,
    OPERATEROLEID     VARCHAR2(50)            NULL,
    TASKOWNER         VARCHAR2(50)            NULL,
    PRELINKID         VARCHAR2(50)            NULL,
    FLOWNAME          VARCHAR2(50)            NULL,
    SUBTASKFLAG       VARCHAR2(50)            NULL,
    OPERATETYPE       VARCHAR2(50)            NULL,
    CURRENTLINKID     VARCHAR2(50)            NULL,
    PARENTTASKID      VARCHAR2(50)            NULL,
    IFWAITFORSUBTASK  VARCHAR2(50)            NULL,
    SUBTASKDEALFALG   VARCHAR2(20)            NULL,
    CREATEYEAR        NUMBER(15, 5) DEFAULT 0 NULL,
    CREATEMONTH       NUMBER(15, 5) DEFAULT 0 NULL,
    CREATEDAY         NUMBER(15, 5) DEFAULT 0 NULL,
    PRIMARY KEY (ID)
)
insert into taw_system_application(APP_ID, app_name, notes, domain_type)
values (28, 'IT需求开发流程', 'IT需求开发流程', 1)
insert into taw_system_workflow(id, name, flowid, sheetid, remark, mainservicebeanid)
values (113, 'ITSoftChangeProcess', '092', '01', 'IT需求开发流程', 'iITSoftChangeMainManager')


--ITSoftChange-END--