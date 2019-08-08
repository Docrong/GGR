--SecurityEvaluate-START--
drop table securityevaluate_main;
create table securityevaluate_main
(
    id                  VARCHAR(32),
    sheetid             VARCHAR(50),
    title               VARCHAR(100),
    sheetacceptlimit    DATE,
    sheetcompletelimit  DATE,
    sendtime            DATE,
    sendorgtype         VARCHAR2(25),
    senduserid          VARCHAR(30),
    sendcontact         VARCHAR(30),
    sheetaccessories    VARCHAR(100),
    endtime             DATE,
    endresult           VARCHAR(30),
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
    cancelReason        VARCHAR(100),

    mainNetSortOne      VARCHAR(32),

    mainNetSortTwo      VARCHAR(32),

    mainNetSortThree    VARCHAR(32),

    mainCompleteTime    VARCHAR(32),

    mainSafetyStatement VARCHAR(32),

    primary key (id)
);

drop table securityevaluate_link;
create table securityevaluate_link
(
    id                   VARCHAR(32),
    mainid               VARCHAR(50),
    nodeacceptlimit      DATE,
    nodecompletelimit    DATE,
    operatetype          INTEGER,
    operatetime          DATE,
    operateuserid        VARCHAR(30),
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
    transferReason       VARCHAR(200),
    remark               VARCHAR(200),

    linkAuditObject      VARCHAR(32),

    linkAnalysisResult   VARCHAR(32),

    linkAnalysisReport   VARCHAR(32),

    linkAuditOpinion     VARCHAR(32),

    linkAuditResult      VARCHAR(32),

    linkIsStartProcess   VARCHAR(32),

    primary key (id)
);

create table securityevaluate_task
(
    ID                VARCHAR2(50) not null,
    TASKNAME          VARCHAR2(50),
    TASKDISPLAYNAME   VARCHAR2(50),
    CREATETIME        TIMESTAMP(6),
    TASKSTATUS        VARCHAR2(50),
    PROCESSID         VARCHAR2(50),
    SHEETKEY          VARCHAR2(50),
    SHEETID           VARCHAR2(50),
    TITLE             VARCHAR2(255),
    ACCEPTTIMELIMIT   TIMESTAMP(6),
    COMPLETETIMELIMIT TIMESTAMP(6),
    OPERATEROLEID     VARCHAR2(50),
    TASKOWNER         VARCHAR2(50),
    PRELINKID         VARCHAR2(50),
    FLOWNAME          VARCHAR2(50),
    subTaskFlag       VARCHAR2(10) default 'false',
    OPERATETYPE       VARCHAR2(25),
    CURRENTLINKID     VARCHAR2(255),
    parentTaskId      VARCHAR2(50),
    ifWaitForSubTask  VARCHAR2(50),
    subTaskDealFalg   VARCHAR2(50),
    PRIMARY KEY (ID)
)

insert into taw_system_application(app_name, notes, domain_type)
values ('安全评估流程', '安全评估流程', 1);
insert into taw_system_workflow(name, flowid, sheetid, remark, mainservicebeanid)
values ('SecurityEvaluateProcess', '071', '01', '安全评估流程', 'iSecurityEvaluateMainManager');

--SecurityEvaluate-END--