select userid,deptid,password from taw_system_user

select * from netdata_main

select * from netdata_link

select * from netdata_task


--main

MAINISSECURITY     VARCHAR2(50)   Y                         
MAINISCONNECT      VARCHAR2(50)   Y                         
MAINFACTORY        VARCHAR2(50)   Y                         
MAINCELLINFO       VARCHAR2(50)   Y                         
MAINCHANGESOURCE   VARCHAR2(50)   Y                         
MAINPARENTSHEETID  VARCHAR2(50)   Y                         
MAINISNEEDDESIGN   VARCHAR2(50)   Y                         
MAINDESIGNID       VARCHAR2(50)   Y                         
MAINNETTYPETHREE   VARCHAR2(50)   Y                         
MAINNETTYPETWO     VARCHAR2(50)   Y                         
MAINNETTYPEONE     VARCHAR2(50)   Y                         
MAINREJECTTIMES    INTEGER        Y                         
SENDYEAR           INTEGER        Y                         
SENDMONTH          INTEGER        Y                         
SENDDAY            INTEGER        Y                         
MAINIFRECORD       INTEGER        Y        0                
MAINEXECUTEENDDATE DATE           Y                         
 
--link            
LINKCOMPLETELIMITTIME DATE           Y                        
LINKINVOLVEDPROVINCE  VARCHAR2(50)   Y                         
LINKINVOLVEDCITY      VARCHAR2(50)   Y                         
LINKDESIGNKEY         VARCHAR2(50)   Y                         
LINKISCHECK           VARCHAR2(50)   Y                         
LINKPERMITRESULT      VARCHAR2(50)   Y                         
LINKMANAGER           VARCHAR2(50)   Y                         
LINKCONTACT           VARCHAR2(50)   Y                         
LINKPLANSTARTTIME     DATE           Y                         
LINKPLANENDTIME       DATE           Y                         
LINKISEFFECTBUSINESS  VARCHAR2(50)   Y                         
LINKBUSINESSDEPT      VARCHAR2(50)   Y                         
LINKISSENDTOFRONT     VARCHAR2(50)   Y                         
LINKCUTRESULT         VARCHAR2(50)   Y                         
LINKISPLAN            VARCHAR2(50)   Y                         
                        
LINKISUPDATEWORK      VARCHAR2(50)   Y                         
LINKISNEEDPROJECT     VARCHAR2(50)   Y                         
OPERATERCONTACT       VARCHAR2(255)  Y                         
OPERATEYEAR           INTEGER        Y                         
OPERATEMONTH          INTEGER        Y                         
OPERATEDAY            INTEGER        Y                         
LINKFAILEDREASON      VARCHAR2(50) Y        





--已处理
select 
----est_stat---
2 est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else to_number(null) end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDRESULT,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
--common link
link.ID linkid,
task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
link.OPERATETYPE,
link.OPERATETIME,
link.OPERATEUSERID,
link.OPERATEDEPTID,
link.OPERATEROLEID,
link.TOORGTYPE,
link.TOORGUSERID,
link.TOORGDEPTID,
link.TOORGROLEID,
link.ACCEPTFLAG,
link.COMPLETEFLAG,
link.PRELINKID,
link.PARENTLINKID,
link.FIRSTLINKID,
link.AIID,
link.ACTIVETEMPLATEID,
link.NODETEMPLATENAME,
link.NODEACCESSORIES,
link.OPERATEORGTYPE,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
,main.SENDYEAR                             
,main.SENDMONTH                                
,main.SENDDAY                                
,main.MAINIFRECORD                    
,main.MAINEXECUTEENDDATE 
--bussines link
,link.LINKCOMPLETELIMITTIME                        
,link.LINKINVOLVEDPROVINCE                          
,link.LINKINVOLVEDCITY                               
,link.LINKDESIGNKEY                                
,link.LINKISCHECK                               
,link.LINKPERMITRESULT                            
,link.LINKMANAGER                                 
,link.LINKCONTACT                                 
,link.LINKPLANSTARTTIME                            
,link.LINKPLANENDTIME                            
,link.LINKISEFFECTBUSINESS                         
,link.LINKBUSINESSDEPT                              
,link.LINKISSENDTOFRONT                             
,link.LINKCUTRESULT                             
,link.LINKISPLAN                               
                              
,link.LINKISUPDATEWORK                           
,link.LINKISNEEDPROJECT                         
,link.OPERATERCONTACT                        
,link.OPERATEYEAR                                 
,link.OPERATEMONTH                             
,link.OPERATEDAY                                   
,link.LINKFAILEDREASON      


from netdata_main main

join netdata_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join netdata_task task on link.aiid=task.id
left join taw_system_sub_role orole on link.operateroleid=orole.id
left join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid


--select * from commonfault_link link , commonfault_task task where link.aiid=task.id

--select * from commonfault_task


--未处理
select 
----est_stat---
case when task.taskstatus=2 then 0 when task.taskstatus=8 then 1 end est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else to_number(null) end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDRESULT,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
--common link
to_char(null) linkid,
task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
to_number(null) OPERATETYPE,
to_date(null) OPERATETIME,
case when task.taskowner!=task.operateroleid then task.taskowner else to_char(null) end OPERATEUSERID,
case when task.taskowner!=task.operateroleid then ud.deptid else u.deptid end OPERATEDEPTID,
task.operateroleid OPERATEROLEID,
to_number(null) TOORGTYPE,
to_char(null) TOORGUSERID,
to_char(null)  TOORGDEPTID,
to_char(null) TOORGROLEID,
case when to_date(to_char(task.ACCEPTTIMELIMIT,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')>sysdate then 1 else 2 end ACCEPTFLAG,
case when to_date(to_char(task.COMPLETETIMELIMIT,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')>sysdate then 1 else 2 end COMPLETEFLAG,
task.PRELINKID,
to_char(null) PARENTLINKID,
to_char(null) FIRSTLINKID,
task.id AIID,
task.taskname ACTIVETEMPLATEID,
to_char(null) NODETEMPLATENAME,
to_char(null) NODEACCESSORIES,
to_number(null) OPERATEORGTYPE,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
,main.SENDYEAR                             
,main.SENDMONTH                                
,main.SENDDAY                                
,main.MAINIFRECORD                    
,main.MAINEXECUTEENDDATE   
--bussines link
,to_date(null) LINKCOMPLETELIMITTIME                       
,to_char(null) LINKINVOLVEDPROVINCE                          
,to_char(null) LINKINVOLVEDCITY                          
,to_char(null) LINKDESIGNKEY                            
,to_char(null) LINKISCHECK                                
,to_char(null) LINKPERMITRESULT                            
,to_char(null) LINKMANAGER                                
,to_char(null) LINKCONTACT                                
,to_date(null) LINKPLANSTARTTIME                            
,to_date(null) LINKPLANENDTIME                           
,to_char(null) LINKISEFFECTBUSINESS                         
,to_char(null) LINKBUSINESSDEPT                              
,to_char(null) LINKISSENDTOFRONT                            
,to_char(null) LINKCUTRESULT                                
,to_char(null) LINKISPLAN                                 
                               
,to_char(null) LINKISUPDATEWORK                            
,to_char(null) LINKISNEEDPROJECT                            
,to_char(null) OPERATERCONTACT                              
,to_number(null) OPERATEYEAR                                
,to_number(null) OPERATEMONTH                                
,to_number(null) OPERATEDAY                                 
,to_char(null) LINKFAILEDREASON       
  
from netdata_main main

join netdata_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid

select case when to_date('2008-09-16 06:41:53','YYYY-MM-DD HH24:MI:SS')-sysdate>0 then 1 else 0 end from dual

select * from common

create view v_test
select TOORGROLEID,ACCEPTFLAG,ACCEPTTIME from commonfault_link

TOORGROLEID               VARCHAR2(510)  Y                         
ACCEPTFLAG                NUMBER(10)     Y                         
ACCEPTTIME                TIMESTAMP(6)

select 1 from dual

select to_char(null) from dual

select null from dual

select to_date(null) from dual

create table test1 as select null a from dual

drop table test1


------------------------------VIEW----------------------------------------
select * from v_netdata
select distinct * from v_netdata

--drop view v_commomfault
create  view v_netdata(
----est_stat---
est_data_status,
send_bigrole_id,
operate_bigrole_id,
send_dept_level,
operate_dept_level,
--common main
MAINID,SHEETID,TITLE,SHEETACCEPTLIMIT,SHEETCOMPLETELIMIT,SENDTIME,SENDUSERID,SENDDEPTID,
SENDROLEID,SENDCONTACT,STATUS,HOLDSTATISFIED,ENDTIME,ENDRESULT,ENDUSERID,ENDDEPTID,ENDROLEID,
DELETED,PIID,PARENTSHEETNAME,PARENTSHEETID,SHEETTEMPLATENAME,SHEETACCESSORIES,
TODEPTID,CANCELREASON,
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
--common link
linkid,
NODEACCEPTLIMIT,
NODECOMPLETELIMIT,
OPERATETYPE,
OPERATETIME,
OPERATEUSERID,
OPERATEDEPTID,
OPERATEROLEID,
TOORGTYPE,
TOORGUSERID,
TOORGDEPTID,
TOORGROLEID,
ACCEPTFLAG,
COMPLETEFLAG,
PRELINKID,
PARENTLINKID,
FIRSTLINKID,
AIID,
ACTIVETEMPLATEID,
NODETEMPLATENAME,
NODEACCESSORIES,
OPERATEORGTYPE,
--common task
SUBTASKFLAG,
PARENTTASKID,
taskstatus,
taskoperatetype
--bussines main
,MAINISSECURITY                       
,MAINISCONNECT                          
,MAINFACTORY                               
,MAINCELLINFO                              
,MAINCHANGESOURCE                          
,MAINPARENTSHEETID                         
,MAINISNEEDDESIGN                          
,MAINDESIGNID                               
,MAINNETTYPETHREE                         
,MAINNETTYPETWO                             
,MAINNETTYPEONE                           
,MAINREJECTTIMES                       
,SENDYEAR                             
,SENDMONTH                                
,SENDDAY                                
,MAINIFRECORD                    
,MAINEXECUTEENDDATE 
--bussines link
,LINKCOMPLETELIMITTIME                        
,LINKINVOLVEDPROVINCE                          
,LINKINVOLVEDCITY                               
,LINKDESIGNKEY                                
,LINKISCHECK                               
,LINKPERMITRESULT                            
,LINKMANAGER                                 
,LINKCONTACT                                 
,LINKPLANSTARTTIME                            
,LINKPLANENDTIME                            
,LINKISEFFECTBUSINESS                         
,LINKBUSINESSDEPT                              
,LINKISSENDTOFRONT                             
,LINKCUTRESULT                             
,LINKISPLAN                               
                              
,LINKISUPDATEWORK                           
,LINKISNEEDPROJECT                         
,OPERATERCONTACT                        
,OPERATEYEAR                                 
,OPERATEMONTH                             
,OPERATEDAY                                   
,LINKFAILEDREASON
) as 
(
--已处理
select 
----est_stat---
2 est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else null::integer end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDRESULT,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
--common link
link.ID linkid,
task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
link.OPERATETYPE,
link.OPERATETIME,
link.OPERATEUSERID,
link.OPERATEDEPTID,
link.OPERATEROLEID,
link.TOORGTYPE,
link.TOORGUSERID,
link.TOORGDEPTID,
link.TOORGROLEID,
link.ACCEPTFLAG,
link.COMPLETEFLAG,
link.PRELINKID,
link.PARENTLINKID,
link.FIRSTLINKID,
link.AIID,
link.ACTIVETEMPLATEID,
link.NODETEMPLATENAME,
link.NODEACCESSORIES,
link.OPERATEORGTYPE,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
,main.SENDYEAR                             
,main.SENDMONTH                                
,main.SENDDAY                                
,main.MAINIFRECORD                    
,main.MAINEXECUTEENDDATE 
--bussines link
,link.LINKCOMPLETELIMITTIME                        
,link.LINKINVOLVEDPROVINCE                          
,link.LINKINVOLVEDCITY                               
,link.LINKDESIGNKEY                                
,link.LINKISCHECK                               
,link.LINKPERMITRESULT                            
,link.LINKMANAGER                                 
,link.LINKCONTACT                                 
,link.LINKPLANSTARTTIME                            
,link.LINKPLANENDTIME                            
,link.LINKISEFFECTBUSINESS                         
,link.LINKBUSINESSDEPT                              
,link.LINKISSENDTOFRONT                             
,link.LINKCUTRESULT                             
,link.LINKISPLAN                               
                               
,link.LINKISUPDATEWORK                           
,link.LINKISNEEDPROJECT                          
,link.OPERATERCONTACT                        
,link.OPERATEYEAR                                 
,link.OPERATEMONTH                             
,link.OPERATEDAY                                   
,link.LINKFAILEDREASON      


from netdata_main main

join netdata_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join netdata_task task on link.aiid=task.id
left join taw_system_sub_role orole on link.operateroleid=orole.id
left join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid
)
union all
(
--未处理
select 
----est_stat---
case when task.taskstatus=2 then 0 when task.taskstatus=8 then 1 end est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else null::integer end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDRESULT,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
--common link
null::varchar(255) linkid,
task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
null::integer OPERATETYPE,
null::DATETIME YEAR TO SECOND OPERATETIME,
case when task.taskowner!=task.operateroleid then task.taskowner else null::varchar(255) end OPERATEUSERID,
case when task.taskowner!=task.operateroleid then ud.deptid else u.deptid end OPERATEDEPTID,
task.operateroleid OPERATEROLEID,
null::integer TOORGTYPE,
null::varchar(255) TOORGUSERID,
null::varchar(255)  TOORGDEPTID,
null::varchar(255) TOORGROLEID,
case when task.ACCEPTTIMELIMIT>current then 1 else 2 end ACCEPTFLAG,
case when task.COMPLETETIMELIMIT>current then 1 else 2 end COMPLETEFLAG,
task.PRELINKID,
null::varchar(255) PARENTLINKID,
null::varchar(255) FIRSTLINKID,
task.id AIID,
task.taskname ACTIVETEMPLATEID,
null::varchar(255) NODETEMPLATENAME,
null::varchar(255) NODEACCESSORIES,
null::integer OPERATEORGTYPE,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
,main.SENDYEAR                             
,main.SENDMONTH                                
,main.SENDDAY                                
,main.MAINIFRECORD                    
,main.MAINEXECUTEENDDATE   
--bussines link
,null::DATETIME YEAR TO SECOND LINKCOMPLETELIMITTIME                       
,null::varchar(255) LINKINVOLVEDPROVINCE                          
,null::varchar(255) LINKINVOLVEDCITY                          
,null::varchar(255) LINKDESIGNKEY                            
,null::varchar(255) LINKISCHECK                                
,null::varchar(255) LINKPERMITRESULT                            
,null::varchar(255) LINKMANAGER                                
,null::varchar(255) LINKCONTACT                                
,null::DATETIME YEAR TO SECOND LINKPLANSTARTTIME                            
,null::DATETIME YEAR TO SECOND LINKPLANENDTIME                           
,null::varchar(255) LINKISEFFECTBUSINESS                         
,null::varchar(255) LINKBUSINESSDEPT                              
,null::varchar(255) LINKISSENDTOFRONT                            
,null::varchar(255) LINKCUTRESULT                                
,null::varchar(255) LINKISPLAN                                 
                              
,null::varchar(255) LINKISUPDATEWORK                            
,null::varchar(255) LINKISNEEDPROJECT                            
,null::varchar(255) OPERATERCONTACT                              
,null::integer OPERATEYEAR                                
,null::integer OPERATEMONTH                                
,null::integer OPERATEDAY                                 
,null::varchar(255) LINKFAILEDREASON       
  
from netdata_main main

join netdata_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid
)



create table est_last_oper(
id number(5) not null primary key,--对应工单类型
last_operate_time TIMESTAMP(6),
comments varchar2(128)--标注
)

--delete from est_last_oper

--初始化任务工单采集时间
insert into est_last_oper values (44,'2007-07-07 00:00:00','网络数据管理工单基础表采集的上一次操作的时间');

--drop table est_last_oper
--delete from est_last_oper
select * from est_last_oper

SELECT last_operate_time 
FROM est_last_oper  
WHERE 
id=51

select link.operatetime from commonfault_link link

SELECT count(distinct mainid) FROM commonfault_link HAVING max(operatetime) >= to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS') and max(operatetime) < sysdate

select mainid,operatetime from commonfault_link

--delete from est_commonfault
select * from est_netdata
--drop table est_circuitdispatch
--中间表


CREATE TABLE est_netdata
(
  EST_DATA_STATUS integer
, send_bigrole_id integer
, operate_bigrole_id integer
, send_dept_level varchar(30)
, operate_dept_level  varchar(30)
, MAINID varchar(255)
, SHEETID varchar(255)
, TITLE varchar(255)
, SHEETACCEPTLIMIT DATETIME YEAR TO SECOND
, SHEETCOMPLETELIMIT DATETIME YEAR TO SECOND
, SENDTIME DATETIME YEAR TO SECOND
, SENDUSERID varchar(255)
, SENDDEPTID varchar(255)
, SENDROLEID varchar(255)
, SENDCONTACT varchar(255)
, STATUS integer
, HOLDSTATISFIED integer
, ENDTIME DATETIME YEAR TO SECOND
, ENDRESULT varchar(255)
, ENDUSERID varchar(255)
, ENDDEPTID varchar(255)
, ENDROLEID varchar(255)
, DELETED integer
, PIID varchar(255)
, PARENTSHEETNAME varchar(255)
, PARENTSHEETID varchar(255)
, SHEETTEMPLATENAME varchar(255)
, SHEETACCESSORIES varchar(255)
, TODEPTID varchar(255)
, CANCELREASON varchar(255)
, LINKID varchar(255)
, NODEACCEPTLIMIT DATETIME YEAR TO SECOND
, NODECOMPLETELIMIT DATETIME YEAR TO SECOND
, OPERATETYPE integer
, OPERATETIME DATETIME YEAR TO SECOND
, OPERATEUSERID varchar(255)
, OPERATEDEPTID varchar(255)
, OPERATEROLEID varchar(255)
, TOORGTYPE integer
, TOORGUSERID varchar(255)
, TOORGDEPTID varchar(255)
, TOORGROLEID varchar(255)
, ACCEPTFLAG integer
, COMPLETEFLAG integer
, PRELINKID varchar(255)
, PARENTLINKID varchar(255)
, FIRSTLINKID varchar(255)
, AIID varchar(255)
, ACTIVETEMPLATEID varchar(255)
, NODETEMPLATENAME varchar(255)
, NODEACCESSORIES varchar(255)
, OPERATEORGTYPE integer
, SUBTASKFLAG varchar(10)
, PARENTTASKID varchar(50)
, TASKSTATUS varchar(255)
, TASKOPERATETYPE varchar(255)
,MAINISSECURITY     varchar(50),                           
MAINISCONNECT      varchar(50),                           
MAINFACTORY        varchar(50),                            
MAINCELLINFO       varchar(50),                            
MAINCHANGESOURCE   varchar(50),                            
MAINPARENTSHEETID  varchar(50),                            
MAINISNEEDDESIGN   varchar(50),                            
MAINDESIGNID       varchar(50),                            
MAINNETTYPETHREE   varchar(50),                            
MAINNETTYPETWO     varchar(50),                            
MAINNETTYPEONE     varchar(50),                           
MAINREJECTTIMES    INTEGER,                                 
SENDYEAR           INTEGER,                                 
SENDMONTH          INTEGER,                                 
SENDDAY            INTEGER,                                 
MAINIFRECORD       INTEGER,                             
MAINEXECUTEENDDATE DATETIME YEAR TO SECOND,       
LINKCOMPLETELIMITTIME DATETIME YEAR TO SECOND,                                   
LINKINVOLVEDPROVINCE  varchar(50),                         
LINKINVOLVEDCITY      varchar(50),                           
LINKDESIGNKEY         varchar(50),                            
LINKISCHECK           varchar(50),                            
LINKPERMITRESULT      varchar(50),                            
LINKMANAGER           varchar(50),                           
LINKCONTACT           varchar(50),                            
LINKPLANSTARTTIME     DATETIME YEAR TO SECOND ,                                   
LINKPLANENDTIME       DATETIME YEAR TO SECOND ,                                  
LINKISEFFECTBUSINESS  varchar(50),                           
LINKBUSINESSDEPT      varchar(50),                            
LINKISSENDTOFRONT     varchar(50),                            
LINKCUTRESULT         varchar(50),                            
LINKISPLAN            varchar(50),                            
                           
LINKISUPDATEWORK      varchar(50),                            
LINKISNEEDPROJECT     varchar(50),                            
OPERATERCONTACT       varchar(255),                           
OPERATEYEAR           INTEGER   ,                              
OPERATEMONTH          INTEGER    ,                             
OPERATEDAY            INTEGER    ,                            
LINKFAILEDREASON      varchar(50)                       
)




SELECT
  distinct
  EST_DATA_STATUS
, OPERATE_BIGROLE_ID
, SEND_DEPT_LEVEL
, OPERATE_DEPT_LEVEL
, MAINID
, SHEETID
, TITLE
, SHEETACCEPTLIMIT
, SHEETCOMPLETELIMIT
, SENDTIME
, SENDUSERID
, SENDDEPTID
, SENDROLEID
, SENDCONTACT
, STATUS
, HOLDSTATISFIED
, ENDTIME
, ENDRESULT
, ENDUSERID
, ENDDEPTID
, ENDROLEID
, DELETED
, PIID
, PARENTSHEETNAME
, PARENTSHEETID
, SHEETTEMPLATENAME
, SHEETACCESSORIES
, TODEPTID
, CANCELREASON
, LINKID
, NODEACCEPTLIMIT
, NODECOMPLETELIMIT
, OPERATETYPE
, OPERATETIME
, OPERATEUSERID
, OPERATEDEPTID
, OPERATEROLEID
, TOORGTYPE
, TOORGUSERID
, TOORGDEPTID
, TOORGROLEID
, ACCEPTFLAG
, COMPLETEFLAG
, PRELINKID
, PARENTLINKID
, FIRSTLINKID
, AIID
, ACTIVETEMPLATEID
, NODETEMPLATENAME
, NODEACCESSORIES
, OPERATEORGTYPE
, SUBTASKFLAG
, PARENTTASKID
, TASKSTATUS
, TASKOPERATETYPE
, MAINIFSAFE
, MAINIFMUTUALCOMMUNICATION
, MAINNETSORTONE
, MAINEQUIPMENTFACTORY
, MAINAPPLYGIST
, MAINDESCRIPTION
, MAINIFCHANGEPROJECT
, MAINCHANGESOURCE
, MAINTOUCHSHEETID
, MAINRESOURCENO
, MAINNETSORTTWO
, MAINNETSORTTHREE
, MAINREJECTTIMES
, MAINNETSORT
, SENDYEAR
, SENDMONTH
, SENDDAY
, LINKEXECUTEENDDATE
, LINKPROGRAMMEEXPLAIN
, LINKPROGRAMMEKEY
, LINKRISKEVALUATE
, LINKOPERATIONCONSTRUE
, LINKPROGRAMMENO
, LINKPERMITRESULT
, LINKPERMITIDEA
, LINKEXCUTEPRINCIPAL
, LINKCONTACT
, LINKPLANSTARTDATE
, LINKPLANENDDATE
, LINKNETAFFECTAREA
, LINKIFAFFECTOPERATION
, LINKAFFECTSITUATION
, LINKAFFECTNETMANAGE
, LINKREFEROPERATEDEPT
, LINKIFNOTIFY
, LINKEXCUTERESULT
, LINKISACCORDANCEPROGRAMME
, LINKEXCUTEEXPLAIN
, LINKAFFECTOPERATIONEXPLAIN

, LINKALARMRECORD
, LINKREPORTABNORMITYEXPLAIN
, LINKEXCUTECONSTRUE
, LINKIFUPDATEPLAN
, LINKPLANUPDATEIDEA
, LINKCIRCUITUPDATE
, LINKWORKPLAN
, LINKISTOMAINTENANCE
, LINKMAINTENANCEDES
, LINKFAILEDREASON
, LINKINVOLVEDPROVINCE
, LINKINVOLVEDCITY
, OPERATEYEAR
, OPERATEMONTH
, OPERATEDAY
FROM V_netdata
where 
mainid=？

select * from  est_netdata



