--SoftChange-START--
drop table softchange_main;
create table softchange_main
(
    id                    VARCHAR(32),
    sheetid               VARCHAR(500),
    title                 VARCHAR(500),
    sheetacceptlimit      DATE,
    sheetcompletelimit    DATE,
    sendtime              DATE,
    sendorgtype           VARCHAR2(500),
    senduserid            VARCHAR(500),
    sendcontact           VARCHAR(500),
    sheetaccessories      VARCHAR(500),
    endtime               DATE,
    endresult             VARCHAR(500),
    status                INTEGER,
    holdstatisfied        INTEGER,
    enduserid             VARCHAR(500),
    deleted               INTEGER,
    piid                  VARCHAR(500),
    sheettemplatename     VARCHAR(500),
    parentsheetname       VARCHAR(500),
    parentsheetid         VARCHAR(500),
    correlationkey        VARCHAR(500),
    parentcorrelation     VARCHAR(500),
    todeptid              VARCHAR(500),
    senddeptid            VARCHAR(500),
    sendroleid            VARCHAR(500),
    endroleid             VARCHAR(500),
    enddeptid             VARCHAR(500),
    templateFlag          VARCHAR(500),
    cancelReason          VARCHAR(500),

    mainNetType           VARCHAR(500),

    mainIsSecurity        VARCHAR(500),

    mainIsConnect         VARCHAR(500),

    mainFactory           VARCHAR(500),

    mainApplyReason       VARCHAR(500),

    mainCellInfo          VARCHAR(500),

    mainSoftCDKey         VARCHAR(500),

    mainSoftPatchKey      VARCHAR(500),

    mainSoftDetail        VARCHAR(500),

    mainIsAllow           VARCHAR(500),

    mainAllowKey          VARCHAR(500),

    mainIsBack            VARCHAR(500),

    mainChangeSource      VARCHAR(500),

    mainParentProcessName VARCHAR(500),

    mainIsNeedDesign      VARCHAR(500),

    mainDesignId          VARCHAR(500),

    primary key (id)
);

drop table softchange_link;
create table softchange_link
(
    id                    VARCHAR(32),
    mainid                VARCHAR(500),
    nodeacceptlimit       DATE,
    nodecompletelimit     DATE,
    operatetype           INTEGER,
    operatetime           DATE,
    operateuserid         VARCHAR(500),
    operateorgtype        INTEGER,
    toorgtype             INTEGER,
    toorguserid           VARCHAR(500),
    toroleid              INTEGER,
    acceptflag            INTEGER,
    accepttime            DATE,
    completeflag          INTEGER,
    completetime          DATE,
    prelinkid             VARCHAR(500),
    parentlinkid          VARCHAR(500),
    firstlinkid           VARCHAR(500),
    piid                  VARCHAR(500),
    aiid                  VARCHAR(500),
    activetemplateid      VARCHAR(500),
    nodetemplatename      VARCHAR(500),
    nodeaccessories       VARCHAR(500),
    toorgdeptid           VARCHAR(500),
    toorgroleid           VARCHAR(500),
    operateroleid         VARCHAR(500),
    operatedeptid         VARCHAR(500),
    correlationkey        VARCHAR(500),
    templateFlag          VARCHAR(500),
    templateName          VARCHAR(500),
    templateCreateUserId  VARCHAR(500),
    transferReason        VARCHAR(500),
    remark                VARCHAR(500),

    linkCheckModel        VARCHAR(500),

    linkCompleteLimitTime DATE,

    linkDesignComment     VARCHAR(500),

    linkInvolvedProvince  VARCHAR(500),

    linkInvolvedCity      VARCHAR(500),

    linkDesignKey         VARCHAR(500),

    linkRiskEstimate      VARCHAR(500),

    linkEffectAnalyse     VARCHAR(500),

    linkIsCheck           VARCHAR(500),

    linkCheckComment      VARCHAR(500),

    linkPermitResult      VARCHAR(500),

    linkPermitIdea        VARCHAR(500),

    linkManager           VARCHAR(500),

    linkContact           VARCHAR(32),

    linkPlanStartTime     DATE,

    linkPlanEndTime       DATE,

    linkCellInfo          VARCHAR(500),

    linkIsEffectBusiness  VARCHAR(500),

    linkEffectCondition   VARCHAR(500),

    linkNetManage         VARCHAR(500),

    linkBusinessDept      VARCHAR(500),

    linkIsSendToFront     VARCHAR(500),

    linkCutResult         VARCHAR(500),

    linkIsPlan            VARCHAR(500),

    linkCutComment        VARCHAR(500),

    linkBusinessComment   VARCHAR(500),

    linkTestResult        VARCHAR(500),

    linkAlertRecord       VARCHAR(500),

    linkUnnormalComment   VARCHAR(500),

    linkCutAnalyse        VARCHAR(500),

    linkIsUpdateWork      VARCHAR(500),

    linkWorkUpdateAdvice  VARCHAR(500),

    linkSoftVersionUpdate VARCHAR(500),

    linkNextPlan          VARCHAR(500),

    linkIsNeedProject     VARCHAR(500),

    linkProjectComment    VARCHAR(500),

    primary key (id)
);

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