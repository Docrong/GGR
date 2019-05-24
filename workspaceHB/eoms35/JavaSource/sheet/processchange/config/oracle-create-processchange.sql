
--ProcessChange-START--
drop table processchange_main;
create table processchange_main(
    ID                   	VARCHAR2(32) NOT NULL,
    SHEETID              	VARCHAR2(50) NULL,
    TITLE                	VARCHAR2(100) NULL,
    SHEETACCEPTLIMIT     	DATE NULL,
    SHEETCOMPLETELIMIT   	DATE NULL,
    SENDTIME             	DATE NULL,
    SENDORGTYPE          	VARCHAR2(25) NULL,
    SENDUSERID           	VARCHAR2(50) NULL,
    SENDCONTACT          	VARCHAR2(50) NULL,
    SHEETACCESSORIES     	VARCHAR2(100) NULL,
    ENDTIME              	DATE NULL,
    ENDRESULT            	VARCHAR2(255) NULL,
    STATUS               	NUMBER NULL,
    HOLDSTATISFIED       	NUMBER NULL,
    ENDUSERID            	VARCHAR2(50) NULL,
    DELETED              	NUMBER NULL,
    PIID                 	VARCHAR2(50) NULL,
    SHEETTEMPLATENAME    	VARCHAR2(50) NULL,
    PARENTSHEETNAME      	VARCHAR2(50) NULL,
    PARENTSHEETID        	VARCHAR2(50) NULL,
    CORRELATIONKEY       	VARCHAR2(50) NULL,
    PARENTCORRELATION    	VARCHAR2(50) NULL,
    TODEPTID             	VARCHAR2(50) NULL,
    SENDDEPTID           	VARCHAR2(35) NULL,
    SENDROLEID           	VARCHAR2(35) NULL,
    ENDROLEID            	VARCHAR2(35) NULL,
    ENDDEPTID            	VARCHAR2(35) NULL,
    TEMPLATEFLAG         	VARCHAR2(20) NULL,
    CANCELREASON         	VARCHAR2(255) NULL,
    SENDYEAR             	NUMBER DEFAULT 0 NULL,
    SENDMONTH            	NUMBER DEFAULT 0 NULL,
    SENDDAY              	NUMBER DEFAULT 0 NULL,
    PARENTPHASENAME      	VARCHAR2(50) NULL,
    INVOKEMODE           	VARCHAR2(50) NULL,

    mainProcessType           VARCHAR(50),

    mainProcess           VARCHAR(50),

    mainEditReason           VARCHAR(255),

    mainEditAdvice           VARCHAR(255),

    primary key(id)
);

drop table processchange_link;
create table processchange_link(
    ID                   	VARCHAR2(32) NOT NULL,
    MAINID               	VARCHAR2(50) NULL,
    NODEACCEPTLIMIT      	DATE NULL,
    NODECOMPLETELIMIT    	DATE NULL,
    OPERATETYPE          	NUMBER NULL,
    OPERATETIME          	DATE NULL,
    OPERATEUSERID        	VARCHAR2(30) NULL,
    OPERATERCONTACT      	VARCHAR2(50) NULL,
    OPERATEORGTYPE       	NUMBER NULL,
    TOORGTYPE            	NUMBER NULL,
    TOORGUSERID          	VARCHAR2(50) NULL,
    TOROLEID             	NUMBER NULL,
    ACCEPTFLAG           	NUMBER NULL,
    ACCEPTTIME           	DATE NULL,
    COMPLETEFLAG         	NUMBER NULL,
    COMPLETETIME         	DATE NULL,
    PRELINKID            	VARCHAR2(50) NULL,
    PARENTLINKID         	VARCHAR2(50) NULL,
    FIRSTLINKID          	VARCHAR2(50) NULL,
    PIID                 	VARCHAR2(50) NULL,
    AIID                 	VARCHAR2(50) NULL,
    ACTIVETEMPLATEID     	VARCHAR2(50) NULL,
    NODETEMPLATENAME     	VARCHAR2(50) NULL,
    NODEACCESSORIES      	VARCHAR2(50) NULL,
    TOORGDEPTID          	VARCHAR2(35) NULL,
    TOORGROLEID          	VARCHAR2(35) NULL,
    OPERATEROLEID        	VARCHAR2(35) NULL,
    OPERATEDEPTID        	VARCHAR2(35) NULL,
    CORRELATIONKEY       	VARCHAR2(50) NULL,
    TEMPLATEFLAG         	VARCHAR2(20) NULL,
    TEMPLATENAME         	VARCHAR2(100) NULL,
    TEMPLATECREATEUSERID 	VARCHAR2(100) NULL,
    TRANSFERREASON       	VARCHAR2(255) NULL,
    REMARK               	VARCHAR2(255) NULL,
    OPERATEYEAR          	NUMBER(15,5) DEFAULT 0 NULL,
    OPERATEMONTH         	NUMBER(15,5) DEFAULT 0 NULL,
    OPERATEDAY           	NUMBER(15,5) DEFAULT 0 NULL,

    linkProcessType           VARCHAR(50),

    linkProcess           VARCHAR(50),

    linkFrameDesc           VARCHAR(255),

    linkSchemeFrame           VARCHAR(255),

    linkSchemeDesc           VARCHAR(255),

    linkChangeScheme           VARCHAR(255),

    linkITChangeScheme           VARCHAR(255),

    linkAuditResult           VARCHAR(50),

    linkAuditDesc           VARCHAR(255),

    linkIfInvoke           VARCHAR(50),

    linkReleaseDesc           VARCHAR(255),

    linkOptimizeImpact           VARCHAR(50),

    linkEvaluateDesc           VARCHAR(255),

    linkEvaluateAttach           VARCHAR(255),

     primary key(id)
);

create table processchange_task(
    ID               	VARCHAR2(50) NOT NULL,
    TASKNAME         	VARCHAR2(50) NULL,
    TASKDISPLAYNAME  	VARCHAR2(50) NULL,
    CREATETIME       	TIMESTAMP(6) NULL,
    TASKSTATUS       	VARCHAR2(50) NULL,
    PROCESSID        	VARCHAR2(50) NULL,
    SHEETKEY         	VARCHAR2(50) NULL,
    SHEETID          	VARCHAR2(50) NULL,
    TITLE            	VARCHAR2(50) NULL,
    ACCEPTTIMELIMIT  	TIMESTAMP(6) NULL,
    COMPLETETIMELIMIT	TIMESTAMP(6) NULL,
    OPERATEROLEID    	VARCHAR2(50) NULL,
    TASKOWNER        	VARCHAR2(50) NULL,
    PRELINKID        	VARCHAR2(50) NULL,
    FLOWNAME         	VARCHAR2(50) NULL,
    SUBTASKFLAG      	VARCHAR2(50) NULL,
    OPERATETYPE      	VARCHAR2(50) NULL,
    CURRENTLINKID    	VARCHAR2(50) NULL,
    PARENTTASKID     	VARCHAR2(50) NULL,
    IFWAITFORSUBTASK 	VARCHAR2(50) NULL,
    SUBTASKDEALFALG  	VARCHAR2(20) NULL,
    CREATEYEAR       	NUMBER(15,5) DEFAULT 0 NULL,
    CREATEMONTH      	NUMBER(15,5) DEFAULT 0 NULL,
    CREATEDAY        	NUMBER(15,5) DEFAULT 0 NULL,
    SENDTIME         	DATE NULL,
    PRIMARY KEY(ID)
)

insert into taw_system_application(app_id,app_name,notes,domain_type) values(37,'流程变更工单','流程变更工单',1)
insert into taw_system_workflow(id,name,flowid,sheetid,remark,mainservicebeanid) values(121,'ProcessChangeProcess','081','01','流程变更工单','iProcessChangeMainManager')

--ProcessChange-END--