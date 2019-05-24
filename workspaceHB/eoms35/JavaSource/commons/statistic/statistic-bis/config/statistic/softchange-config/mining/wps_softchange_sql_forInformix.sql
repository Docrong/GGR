select userid,deptid,password from taw_system_user

select * from softchange_main

select * from softchange_link

select * from softchange_task

MAINNETTYPEONE        VARCHAR2(50)   Y                         
MAINISSECURITY        VARCHAR2(50)   Y                         
MAINISCONNECT         VARCHAR2(50)   Y                         
MAINFACTORY           VARCHAR2(50)   Y                         
                       
MAINCELLINFO          VARCHAR2(4000) Y --------                        
MAINSOFTCDKEY         VARCHAR2(50)   Y                         
MAINSOFTPATCHKEY      VARCHAR2(50)   Y                         
                       
MAINISALLOW           VARCHAR2(50)   Y                         
MAINALLOWKEY          VARCHAR2(50)   Y                         
MAINISBACK            VARCHAR2(50)   Y                         
MAINCHANGESOURCE      VARCHAR2(50)   Y                         
MAINPARENTPROCESSNAME VARCHAR2(50)   Y                         
MAINISNEEDDESIGN      VARCHAR2(50)   Y                         
MAINDESIGNID          VARCHAR2(4000) Y       -------                  
MAINREJECTTIMES       NUMBER         Y        0                
MAINNETTYPETWO        VARCHAR2(50)   Y                         
MAINNETTYPETHREE      VARCHAR2(50)   Y                         
SENDYEAR              NUMBER(15,5)   Y        0                
SENDMONTH             NUMBER(15,5)   Y        0                
SENDDAY               NUMBER(15,5)   Y        0                
MAINIFRECORD          NUMBER(15,5)   Y        0                
MAINEXECUTEENDDATE    VARCHAR2(50)   Y 

LINKCOMPLETELIMITTIME DATE           Y                         
                    
LINKINVOLVEDPROVINCE  VARCHAR2(50)   Y                         
LINKINVOLVEDCITY      VARCHAR2(50)   Y                         
LINKDESIGNKEY         VARCHAR2(50)   Y                         
             
                   
LINKISCHECK           VARCHAR2(50)   Y                         
                   
LINKPERMITRESULT      VARCHAR2(50)   Y                         
LINKPERMITIDEA        VARCHAR2(4000) Y    ------------                     
LINKMANAGER           VARCHAR2(50)   Y                         
LINKCONTACT           VARCHAR2(50)   Y                         
LINKPLANSTARTTIME     DATE           Y                         
LINKPLANENDTIME       DATE           Y                         
                     
LINKISEFFECTBUSINESS  VARCHAR2(50)   Y                         
LINKEFFECTCONDITION   VARCHAR2(4000) Y    ------------                     
                     
LINKBUSINESSDEPT      VARCHAR2(50)   Y                         
LINKISSENDTOFRONT     VARCHAR2(50)   Y                         
LINKCUTRESULT         VARCHAR2(50)   Y                         
LINKISPLAN            VARCHAR2(50)   Y                         
                     
       
                       
                  
                     
                   
LINKISUPDATEWORK      VARCHAR2(50)   Y                         
                 
LINKSOFTVERSIONUPDATE VARCHAR2(4000) Y           ----              
                   
LINKISNEEDPROJECT     VARCHAR2(50)   Y                         
            
OPERATERCONTACT       VARCHAR2(50)   Y                         
OPERATEDAY            NUMBER(15,5)   Y        0                
OPERATEYEAR           NUMBER(15,5)   Y        0                
OPERATEMONTH          NUMBER(15,5)   Y        0                
LINKFAILEDREASON      VARCHAR2(50)   Y 

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
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
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
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINNETTYPEONE                           
,main.MAINISSECURITY                        
,main.MAINISCONNECT                              
,main.MAINFACTORY                                    
,main.MAINSOFTCDKEY                               
,main.MAINSOFTPATCHKEY                              
,main.MAINISALLOW                              
,main.MAINALLOWKEY                                
,main.MAINISBACK                            
,main.MAINCHANGESOURCE                           
,main.MAINPARENTPROCESSNAME                         
,main.MAINISNEEDDESIGN                            
,main.MAINREJECTTIMES                 
,main.MAINNETTYPETWO                              
,main.MAINNETTYPETHREE                           
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
,link.OPERATEDAY                        
,link.OPERATEYEAR                       
,link.OPERATEMONTH                     
,link.LINKFAILEDREASON    

from softchange_main main

join softchange_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join softchange_task task on link.aiid=task.id
left join taw_system_sub_role orole on link.operateroleid=orole.id
left join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid


--select * from softchange_link link , softchange_task task where link.aiid=task.id

--select * from softchange_task


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
------user for find template---
--main.TEMPLATEFLAG
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
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
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINNETTYPEONE                           
,main.MAINISSECURITY                        
,main.MAINISCONNECT                              
,main.MAINFACTORY                                   
,main.MAINSOFTCDKEY                               
,main.MAINSOFTPATCHKEY                             
,main.MAINISALLOW                              
,main.MAINALLOWKEY                                
,main.MAINISBACK                            
,main.MAINCHANGESOURCE                           
,main.MAINPARENTPROCESSNAME                         
,main.MAINISNEEDDESIGN                            
,main.MAINREJECTTIMES                 
,main.MAINNETTYPETWO                              
,main.MAINNETTYPETHREE                           
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
,null::integer OPERATEDAY                        
,null::integer OPERATEYEAR                     
,null::integer OPERATEMONTH                    
,null::varchar(255) LINKFAILEDREASON      

from softchange_main main

join softchange_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid

select case when '2008-09-16 06:41:53'-current>0 then 1 else 0 end from dual

select * from common

create view v_test
select TOORGROLEID,ACCEPTFLAG,ACCEPTTIME from softchange_link

TOORGROLEID               VARCHAR2(510)  Y                         
ACCEPTFLAG                NUMBER(10)     Y                         
ACCEPTTIME                date

select 1 from dual

select to_char(null) from dual

select null from dual

select to_date(null) from dual

create table test1 as select null a from dual

drop table test1


------------------------------VIEW----------------------------------------
select * from v_softchange
select distinct * from v_softchange

--drop view v_softchange
create  view v_softchange(
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
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
SUBTASKFLAG,
PARENTTASKID,
taskstatus,
taskoperatetype
--bussines main
,MAINNETTYPEONE                           
,MAINISSECURITY                        
,MAINISCONNECT                              
,MAINFACTORY                                   
,MAINSOFTCDKEY                               
,MAINSOFTPATCHKEY                              
,MAINISALLOW                              
,MAINALLOWKEY                                
,MAINISBACK                            
,MAINCHANGESOURCE                           
,MAINPARENTPROCESSNAME                         
,MAINISNEEDDESIGN                            
,MAINREJECTTIMES                 
,MAINNETTYPETWO                              
,MAINNETTYPETHREE                           
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
,OPERATEDAY                        
,OPERATEYEAR                       
,OPERATEMONTH                     
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
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
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
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINNETTYPEONE                           
,main.MAINISSECURITY                        
,main.MAINISCONNECT                              
,main.MAINFACTORY                                    
,main.MAINSOFTCDKEY                               
,main.MAINSOFTPATCHKEY                              
,main.MAINISALLOW                              
,main.MAINALLOWKEY                                
,main.MAINISBACK                            
,main.MAINCHANGESOURCE                           
,main.MAINPARENTPROCESSNAME                         
,main.MAINISNEEDDESIGN                            
,main.MAINREJECTTIMES                 
,main.MAINNETTYPETWO                              
,main.MAINNETTYPETHREE                           
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
,link.OPERATEDAY                        
,link.OPERATEYEAR                       
,link.OPERATEMONTH                     
,link.LINKFAILEDREASON    

from softchange_main main

join softchange_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join softchange_task task on link.aiid=task.id
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
------user for find template---
--main.TEMPLATEFLAG
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
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
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINNETTYPEONE                           
,main.MAINISSECURITY                        
,main.MAINISCONNECT                              
,main.MAINFACTORY                                   
,main.MAINSOFTCDKEY                               
,main.MAINSOFTPATCHKEY                             
,main.MAINISALLOW                              
,main.MAINALLOWKEY                                
,main.MAINISBACK                            
,main.MAINCHANGESOURCE                           
,main.MAINPARENTPROCESSNAME                         
,main.MAINISNEEDDESIGN                            
,main.MAINREJECTTIMES                 
,main.MAINNETTYPETWO                              
,main.MAINNETTYPETHREE                           
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
,null::integer OPERATEDAY                        
,null::integer OPERATEYEAR                     
,null::integer OPERATEMONTH                    
,null::varchar(255) LINKFAILEDREASON      

from softchange_main main

join softchange_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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
last_operate_time date,
comments varchar2(128)--标注
)

--delete from est_last_oper

--初始化任务工单采集时间
insert into est_last_oper values (43,'2007-07-07 00:00:00','软件变更工单基础表采集的上一次操作的时间');

--drop table est_last_oper

select * from est_last_oper

SELECT last_operate_time 
FROM est_last_oper  
WHERE 
id=51

select link.operatetime from softchange_link link

SELECT count(distinct mainid) FROM softchange_link HAVING max(operatetime) >= to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS') and max(operatetime) < sysdate

select mainid,operatetime from softchange_link

delete from est_softchange
select * from est_softchange
drop table est_softchange
--中间表
CREATE TABLE est_softchange
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
, OPERATEORGTYPE INTEGER
, SUBTASKFLAG varchar(10)
, PARENTTASKID varchar(50)
, TASKSTATUS varchar(255)
, TASKOPERATETYPE varchar(255) 
,MAINNETTYPEONE        varchar(50),                             
MAINISSECURITY        varchar(50),                            
MAINISCONNECT         varchar(50),                            
MAINFACTORY           varchar(50),                          
MAINSOFTCDKEY         varchar(50),                            
MAINSOFTPATCHKEY      varchar(50),                          
MAINISALLOW           varchar(50),                           
MAINALLOWKEY          varchar(50),                           
MAINISBACK            varchar(50),                           
MAINCHANGESOURCE      varchar(50),                           
MAINPARENTPROCESSNAME varchar(50),                          
MAINISNEEDDESIGN      varchar(50),                          
MAINREJECTTIMES       integer ,                              
MAINNETTYPETWO        varchar(50),                           
MAINNETTYPETHREE      varchar(50),                           
SENDYEAR              integer,                 
SENDMONTH             integer,    
SENDDAY               integer,                 
MAINIFRECORD          integer,                  
MAINEXECUTEENDDATE    varchar(50),  
LINKCOMPLETELIMITTIME DATETIME YEAR TO SECOND    ,                              
LINKINVOLVEDPROVINCE  varchar(50),                           
LINKINVOLVEDCITY      varchar(50),                           
LINKDESIGNKEY         varchar(50),                          
LINKISCHECK           varchar(50),                           
LINKPERMITRESULT      varchar(50),                          
LINKMANAGER           varchar(50),                            
LINKCONTACT           varchar(50),                            
LINKPLANSTARTTIME     DATETIME YEAR TO SECOND    ,                                
LINKPLANENDTIME       DATETIME YEAR TO SECOND    ,                               
LINKISEFFECTBUSINESS  varchar(50),                           
LINKBUSINESSDEPT      varchar(50),                            
LINKISSENDTOFRONT     varchar(50),                            
LINKCUTRESULT         varchar(50),                            
LINKISPLAN            varchar(50),                           
                          
LINKISUPDATEWORK      varchar(50),                          
LINKISNEEDPROJECT     varchar(50),                          
OPERATERCONTACT       varchar(50),                           
OPERATEDAY            integer,                          
OPERATEYEAR           integer,                           
OPERATEMONTH          integer,               
LINKFAILEDREASON      varchar(50)    
)


