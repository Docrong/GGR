
--SecurityDeal-START--
drop table securitydeal_main;
create table securitydeal_main(
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

    mainNetSort1           VARCHAR(32),

    mainNetSort2           VARCHAR(32),

    mainNetSort3           VARCHAR(32),

    mainSecurityDealRequire           VARCHAR(32),

    primary key(id)
)extent size 10240 next size 5120 lock mode row;

drop table securitydeal_link;
create table securitydeal_link(
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

    linkSecurityInproveAdvice           VARCHAR(32),

    linkSecurityInproveProgram           VARCHAR(32),

    linkAuditViews           VARCHAR(32),

    linkAuditResult           VARCHAR(32),

    linkPerformResult           VARCHAR(32),

    linkIfStartChangeProcess           VARCHAR(32),

     primary key(id) 
)extent size 10240 next size 5120 lock mode row;

create table securitydeal_task(
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

insert into taw_system_application(app_name,notes,domain_type) values('安全问题处理流程','安全问题处理流程',1);
insert into taw_system_workflow(name,flowid,sheetid,remark,mainservicebeanid) values('SecurityDealProcess','073','073','安全问题处理流程','iSecurityDealMainManager');

--SecurityDeal-END--