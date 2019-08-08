--SoftChange-START--
drop table softchange_main;
create table softchange_main
(
    id                    VARCHAR(32),
    sheetid               VARCHAR(50),
    title                 VARCHAR(100),
    sheetacceptlimit      DATETIME YEAR TO SECOND,
    sheetcompletelimit    DATETIME YEAR TO SECOND,
    sendtime              DATETIME YEAR TO SECOND,
    sendorgtype           VARCHAR2(25),
    senduserid            VARCHAR(30),
    sendcontact           VARCHAR(30),
    sheetaccessories      VARCHAR(100),
    endtime               DATETIME YEAR TO SECOND,
    endresult             VARCHAR(30),
    status                INTEGER,
    holdstatisfied        INTEGER,
    enduserid             VARCHAR(50),
    deleted               INTEGER,
    piid                  VARCHAR(50),
    sheettemplatename     VARCHAR(50),
    parentsheetname       VARCHAR(50),
    parentsheetid         VARCHAR(50),
    correlationkey        VARCHAR(50),
    parentcorrelation     VARCHAR(50),
    todeptid              VARCHAR(50),
    senddeptid            VARCHAR(35),
    sendroleid            VARCHAR(35),
    endroleid             VARCHAR(35),
    enddeptid             VARCHAR(35),
    templateFlag          VARCHAR(20),
    cancelReason          VARCHAR(100),

    mainNetType           VARCHAR(32),

    mainIsSecurity        VARCHAR(32),

    mainIsConnect         VARCHAR(32),

    mainFactory           VARCHAR(32),

    mainApplyReason       VARCHAR(32),

    mainCellInfo          VARCHAR(32),

    mainSoftCDKey         VARCHAR(32),

    mainSoftPatchKey      VARCHAR(32),

    mainSoftDetail        VARCHAR(32),

    mainIsAllow           VARCHAR(32),

    mainAllowKey          VARCHAR(32),

    mainIsBack            VARCHAR(32),

    mainChangeSource      VARCHAR(32),

    mainParentProcessName VARCHAR(32),

    mainIsNeedDesign      VARCHAR(32),

    mainDesignId          VARCHAR(32),

    primary key (id)
)extent size 10240 next size 5120 lock mode row;

drop table softchange_link;
create table softchange_link
(
    id                    VARCHAR(32),
    mainid                VARCHAR(50),
    nodeacceptlimit       DATETIME YEAR TO SECOND,
    nodecompletelimit     DATETIME YEAR TO SECOND,
    operatetype           INTEGER,
    operatetime           DATETIME YEAR TO SECOND,
    operateuserid         VARCHAR(30),
    operateorgtype        INTEGER,
    toorgtype             INTEGER,
    toorguserid           VARCHAR(50),
    toroleid              INTEGER,
    acceptflag            INTEGER,
    accepttime            DATETIME YEAR TO SECOND,
    completeflag          INTEGER,
    completetime          DATETIME YEAR TO SECOND,
    prelinkid             VARCHAR(50),
    parentlinkid          VARCHAR(50),
    firstlinkid           VARCHAR(50),
    piid                  VARCHAR(50),
    aiid                  VARCHAR(50),
    activetemplateid      VARCHAR(50),
    nodetemplatename      VARCHAR(50),
    nodeaccessories       VARCHAR(50),
    toorgdeptid           VARCHAR(35),
    toorgroleid           VARCHAR(35),
    operateroleid         VARCHAR(35),
    operatedeptid         VARCHAR(35),
    correlationkey        VARCHAR(50),
    templateFlag          VARCHAR(20),
    templateName          VARCHAR(100),
    templateCreateUserId  VARCHAR(100),
    transferReason        VARCHAR(200),
    remark                VARCHAR(200),

    linkCheckModel        VARCHAR(32),

    linkCompleteLimitTime DATETIME YEAR TO SECOND DEFAULT 0(32),

    linkDesignComment     VARCHAR(32),

    linkInvolvedProvince  VARCHAR(32),

    linkInvolvedCity      VARCHAR(32),

    linkDesignKey         VARCHAR(32),

    linkRiskEstimate      VARCHAR(32),

    linkEffectAnalyse     VARCHAR(32),

    linkIsCheck           VARCHAR(32),

    linkCheckComment      VARCHAR(32),

    linkPermitResult      VARCHAR(32),

    linkPermitIdea        VARCHAR(32),

    linkManager           VARCHAR(32),

    linkContact           VARCHAR(32),

    linkPlanStartTime     DATETIME YEAR TO SECOND DEFAULT 0(32),

    linkPlanEndTime       DATETIME YEAR TO SECOND DEFAULT 0(32),

    linkCellInfo          VARCHAR(32),

    linkIsEffectBusiness  VARCHAR(32),

    linkEffectCondition   VARCHAR(32),

    linkNetManage         VARCHAR(32),

    linkBusinessDept      VARCHAR(32),

    linkIsSendToFront     VARCHAR(32),

    linkCutResult         VARCHAR(32),

    linkIsPlan            VARCHAR(32),

    linkCutComment        VARCHAR(32),

    linkBusinessComment   VARCHAR(32),

    linkTestResult        VARCHAR(32),

    linkAlertRecord       VARCHAR(32),

    linkUnnormalComment   VARCHAR(32),

    linkCutAnalyse        VARCHAR(32),

    linkIsUpdateWork      VARCHAR(32),

    linkWorkUpdateAdvice  VARCHAR(32),

    linkSoftVersionUpdate VARCHAR(32),

    linkNextPlan          VARCHAR(32),

    linkIsNeedProject     VARCHAR(32),

    linkProjectComment    VARCHAR(32),

    primary key (id)
)extent size 10240 next size 5120 lock mode row;

create table softchange_task
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
    subTaskFlag       VARCHAR2(10),
    OPERATETYPE       VARCHAR2(25),
    CURRENTLINKID     VARCHAR2(255),
    PRIMARY KEY (ID)

)

insert into taw_system_application(app_name, notes, domain_type)
values ('软件变更流程', '软件变更流程', 1);
insert into taw_system_workflow(name, flowid, sheetid, remark, mainservicebeanid)
values ('SoftChangeProcess', '15', '01', '软件变更流程', 'iSoftChangeMainManager');

--SoftChange-END--