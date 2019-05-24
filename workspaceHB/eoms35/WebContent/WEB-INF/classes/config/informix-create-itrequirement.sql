
--ITRequirement-START--
drop table itrequirement_main;
create table itrequirement_main(
    id                   VARCHAR(32),
    sheetid              VARCHAR(50) ,
	title                VARCHAR(100) ,
	sheetacceptlimit     DATETIME YEAR TO SECOND ,
	sheetcompletelimit   DATETIME YEAR TO SECOND ,
	sendtime             DATETIME YEAR TO SECOND ,
	sendorgtype          VARCHAR2(25) ,
	senduserid           VARCHAR(30) ,
	sendcontact          VARCHAR(30) ,
	sheetaccessories     VARCHAR(100) ,
	endtime              DATETIME YEAR TO SECOND ,
	endresult            VARCHAR(30) ,
	status               INTEGER ,
	holdstatisfied       INTEGER ,
	enduserid            VARCHAR(50) ,
	deleted              INTEGER ,
	piid                 VARCHAR(50) ,
	sheettemplatename    VARCHAR(50) ,
	parentsheetname      VARCHAR(50) ,
	parentsheetid        VARCHAR(50) ,
	correlationkey       VARCHAR(50) ,
	parentcorrelation    VARCHAR(50) ,
	todeptid             VARCHAR(50) ,
	senddeptid           VARCHAR(35) ,
	sendroleid           VARCHAR(35) ,
	endroleid            VARCHAR(35) ,
	enddeptid            VARCHAR(35) ,
	templateFlag            VARCHAR(20) ,
	cancelReason            VARCHAR(100) ,

    mainNetSystem           VARCHAR(32),

    mainSheetID           VARCHAR(32),

    mainUrgentDegree           VARCHAR(32),

    mainBusinessTarget           VARCHAR(32),

    mainUser           VARCHAR(32),

    mainCompleteTime           VARCHAR(32),

    mainRequirementDesc           VARCHAR(32),

    mainRequirementDetail           VARCHAR(32),

    primary key(id)
)extent size 10240 next size 5120 lock mode row;

drop table itrequirement_link;
create table itrequirement_link(
    id                   VARCHAR(32),
    mainid               VARCHAR(50) ,
	nodeacceptlimit      DATETIME YEAR TO SECOND ,
	nodecompletelimit    DATETIME YEAR TO SECOND ,
	operatetype          INTEGER ,
	operatetime          DATETIME YEAR TO SECOND ,
	operateuserid        VARCHAR(30) ,
	operateorgtype       INTEGER ,
	toorgtype            INTEGER ,
	toorguserid          VARCHAR(50) ,
	toroleid             INTEGER ,
	acceptflag           INTEGER ,
	accepttime           DATETIME YEAR TO SECOND ,
	completeflag         INTEGER ,
	completetime         DATETIME YEAR TO SECOND ,
	prelinkid            VARCHAR(50) ,
	parentlinkid         VARCHAR(50) ,
	firstlinkid          VARCHAR(50) ,
	piid                 VARCHAR(50) ,
	aiid                 VARCHAR(50) ,
	activetemplateid     VARCHAR(50) ,
	nodetemplatename     VARCHAR(50) ,
	nodeaccessories      VARCHAR(50) ,
	toorgdeptid          VARCHAR(35) ,
	toorgroleid          VARCHAR(35) ,
	operateroleid        VARCHAR(35) ,
	operatedeptid        VARCHAR(35) ,
	correlationkey       VARCHAR(50) ,
	templateFlag         VARCHAR(20) ,
	templateName        VARCHAR(100) ,
	templateCreateUserId        VARCHAR(100) ,
	transferReason       VARCHAR(200) ,
	remark       VARCHAR(200) ,

    linkAuditResult           VARCHAR(32),

    linkAuditDesc           VARCHAR(32),

    linkChangeType           VARCHAR(32),

    linkAnalysisResult           VARCHAR(32),

    linkAnalysisDesc           VARCHAR(32),

    linkTechnicalprogram           VARCHAR(32),

    linkDevAmount           VARCHAR(32),

    linkAuditPer           VARCHAR(32),

    linkHardWareExp           VARCHAR(32),

    linkSystemImpact           VARCHAR(32),

    linkOtherImpact           VARCHAR(32),

    linkRequirementDesc           VARCHAR(32),

    linkRequirementDetail           VARCHAR(32),

    linkCompleteDesc           VARCHAR(32),

    linkReplyDesc           VARCHAR(32),

    linkTestDesc           VARCHAR(32),

    linkTempSaveDesc           VARCHAR(32),

     primary key(id) 
)extent size 10240 next size 5120 lock mode row;

create table itrequirement_task(
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
  subTaskFlag       VARCHAR2(10) default "false",
  OPERATETYPE        VARCHAR2(25),
  CURRENTLINKID     VARCHAR2(255),
  parentTaskId		 VARCHAR2(50),
  ifWaitForSubTask    VARCHAR2(50),
  subTaskDealFalg	 VARCHAR2(50),
  PRIMARY KEY(ID)       
)extent size 10240 next size 5120 lock mode row;

insert into taw_system_application(app_name,notes,domain_type) values('IT�?求申请流�?','IT�?求申请流�?',1);
insert into taw_system_workflow(name,flowid,sheetid,remark,mainservicebeanid) values('ITRequirementProcess','091','01','IT�?求申请流�?','iITRequirementMainManager');

--ITRequirement-END--