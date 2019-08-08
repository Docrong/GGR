--ResourceAffirm-START--
drop table resourceaffirm_main;
create table resourceaffirm_main
(
    id                 VARCHAR(32),
    sheetid            VARCHAR(50),
    title              VARCHAR(100),
    sheetacceptlimit   DATE,
    sheetcompletelimit DATE,
    sendtime           DATE,
    sendorgtype        VARCHAR2(25),
    senduserid         VARCHAR(30),
    sendcontact        VARCHAR(30),
    sheetaccessories   VARCHAR(100),
    endtime            DATE,
    endresult          VARCHAR(30),
    status             INTEGER,
    holdstatisfied     INTEGER,
    enduserid          VARCHAR(50),
    deleted            INTEGER,
    piid               VARCHAR(50),
    sheettemplatename  VARCHAR(50),
    parentsheetname    VARCHAR(50),
    parentsheetid      VARCHAR(50),
    correlationkey     VARCHAR(50),
    parentcorrelation  VARCHAR(50),
    todeptid           VARCHAR(50),
    senddeptid         VARCHAR(35),
    sendroleid         VARCHAR(35),
    endroleid          VARCHAR(35),
    enddeptid          VARCHAR(35),
    templateFlag       VARCHAR(20),
    cancelReason       VARCHAR(100),

    urgentDegree       VARCHAR(32),

    businesstype1      VARCHAR(32),

    bdeptContact       VARCHAR(32),

    bdeptContactPhone  VARCHAR(32),

    customNo           VARCHAR(32),

    customName         VARCHAR(32),

    customContact      VARCHAR(32),

    customContactPhone VARCHAR(32),

    customContactAdd   VARCHAR(32),

    customContactPost  VARCHAR(32),

    customTrade        VARCHAR(32),

    customContactEmail VARCHAR(32),

    customLevel        VARCHAR(32),

    bRequirementDesc   VARCHAR(32),

    cityA              VARCHAR(32),

    cityZ              VARCHAR(32),

    bandwidth          VARCHAR(32),

    Number             VARCHAR(32),

    portA              VARCHAR(32),

    portAInterfaceType VARCHAR(32),

    portADetailAdd     VARCHAR(32),

    portABDeviceName   VARCHAR(32),

    portABDevicePort   VARCHAR(32),

    portAContactPhone  VARCHAR(32),

    portZ              VARCHAR(32),

    portZInterfaceType VARCHAR(32),

    portZBDeviceName   VARCHAR(32),

    portZBDevicePort   VARCHAR(32),

    portZContactPhone  VARCHAR(32),

    isBeforehandOccupy VARCHAR(32),

    primary key (id)
);

drop table resourceaffirm_link;
create table resourceaffirm_link
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

    ndeptContact         VARCHAR(32),

    ndeptContactPhone    VARCHAR(32),

    dealResult           VARCHAR(32),

    dealDesc             VARCHAR(32),

    netResCapacity       VARCHAR(32),

    expectFinishdays     VARCHAR(32),

    circuitCode          VARCHAR(32),

    clientPgmCapacity    VARCHAR(32),

    primary key (id)
);
drop table resourceaffirm_task;
create table resourceaffirm_task
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
values ('资源确认流程', '资源确认流程', 1);
insert into taw_system_workflow(name, flowid, sheetid, remark, mainservicebeanid)
values ('ResourceAffirmProcess', '031', '01', '资源确认流程', 'iResourceAffirmMainManager');

--ResourceAffirm-END--