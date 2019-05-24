select userid,deptid,password from taw_system_user

select * from businesspilot_main
      
select * from businesspilot_link

select * from businesspilot_task


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
,main.MAINSTARTTIME                               
,main.MAINENDTIME                              
,main.MAINBUSDEPT                              
,main.MAINPRODUCTTYPE                           
,main.MAINPRODUCTNAME                           
,main.MAINPRODUCTCODE                          
,main.MAINISBUS                                  
,main.MAINBUSSHEETID                           
,main.MAINISPROJECT                                                         
,main.MAINISSUCCESS
,main.mainifrecord                           
,main.SENDYEAR                        
,main.SENDMONTH                       
,main.SENDDAY                                              
--bussines link                                   
,link.LINKOPERSTARTTIME                            
,link.LINKOPERENDTIME                           
,link.LINKTESTRESULT                                
,link.LINKNETTYPE                                                            
,link.LINKTGPOLICYACC                                                         
,link.LINKDEVICEVERIFY                                                       
,link.LINKISUPDATE                                                           
,link.LINKISSUCCESS                                                       
,link.LINKSDENDTIME                                
,link.LINKISFINISH                                  
,link.LINKISCHANGE                               
,link.operateyear                                 
,link.operatemonth                                          
,link.operateday                                 

from businesspilot_main main
join businesspilot_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join businesspilot_task task on link.aiid=task.id
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
------user for find template---
--main.TEMPLATEFLAG
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
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
to_char(null) OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINSTARTTIME                               
,main.MAINENDTIME                              
,main.MAINBUSDEPT                              
,main.MAINPRODUCTTYPE                           
,main.MAINPRODUCTNAME                           
,main.MAINPRODUCTCODE                          
,main.MAINISBUS                                  
,main.MAINBUSSHEETID                           
,main.MAINISPROJECT                                
,main.MAINISSUCCESS 
,main.mainifrecord                          
,main.SENDYEAR                        
,main.SENDMONTH                       
,main.SENDDAY                  
--bussines link                               
,to_date(null) LINKOPERSTARTTIME                         
,to_date(null) LINKOPERENDTIME                               
,to_char(null) LINKTESTRESULT                              
,to_char(null) LINKNETTYPE                                                              
,to_char(null) LINKTGPOLICYACC                                                             
,to_char(null) LINKDEVICEVERIFY                                                            
,to_char(null) LINKISUPDATE                                                             
,to_char(null) LINKISSUCCESS                                                            
,to_date(null) LINKSDENDTIME                               
,to_char(null) LINKISFINISH                                 
,to_char(null) LINKISCHANGE                              
,to_number(null) operateyear                           
,to_number(null) operatemonth                     
,to_number(null) operateday             
            
from businesspilot_main main

join businesspilot_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid





--临时操作
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
select * from v_businesspilot
select distinct * from v_businesspilot
drop view v_businesspilot
--drop view v_urgentfault

create or replace view v_businesspilot(
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
,MAINSTARTTIME                               
,MAINENDTIME                              
,MAINBUSDEPT                              
,MAINPRODUCTTYPE                           
,MAINPRODUCTNAME                           
,MAINPRODUCTCODE                          
,MAINISBUS                                  
,MAINBUSSHEETID                           
,MAINISPROJECT                                                                                       
,MAINISSUCCESS
,mainifrecord                           
,SENDYEAR                        
,SENDMONTH                       
,SENDDAY                  
--bussines link                                                                
,LINKOPERSTARTTIME                         
,LINKOPERENDTIME                               
,LINKTESTRESULT                              
,LINKNETTYPE                                                                                            
,LINKTGPOLICYACC                                                              
,LINKDEVICEVERIFY                                                          
,LINKISUPDATE                                                            
,LINKISSUCCESS                                                        
,LINKSDENDTIME                               
,LINKISFINISH                                 
,LINKISCHANGE                              
,operateyear                           
,operatemonth                     
,operateday   
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
,main.MAINSTARTTIME                               
,main.MAINENDTIME                              
,main.MAINBUSDEPT                              
,main.MAINPRODUCTTYPE                           
,main.MAINPRODUCTNAME                           
,main.MAINPRODUCTCODE                          
,main.MAINISBUS                                  
,main.MAINBUSSHEETID                           
,main.MAINISPROJECT                                                         
,main.MAINISSUCCESS
,main.mainifrecord                           
,main.SENDYEAR                        
,main.SENDMONTH                       
,main.SENDDAY                                              
--bussines link                                   
,link.LINKOPERSTARTTIME                            
,link.LINKOPERENDTIME                           
,link.LINKTESTRESULT                                
,link.LINKNETTYPE                                                            
,link.LINKTGPOLICYACC                                                         
,link.LINKDEVICEVERIFY                                                       
,link.LINKISUPDATE                                                           
,link.LINKISSUCCESS                                                       
,link.LINKSDENDTIME                                
,link.LINKISFINISH                                  
,link.LINKISCHANGE                               
,link.operateyear                                 
,link.operatemonth                                          
,link.operateday                                 

from businesspilot_main main
join businesspilot_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join businesspilot_task task on link.aiid=task.id
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
null::datetime year to second OPERATETIME,
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
null::varchar(255) OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINSTARTTIME                               
,main.MAINENDTIME                              
,main.MAINBUSDEPT                              
,main.MAINPRODUCTTYPE                           
,main.MAINPRODUCTNAME                           
,main.MAINPRODUCTCODE                          
,main.MAINISBUS                                  
,main.MAINBUSSHEETID                           
,main.MAINISPROJECT                                
,main.MAINISSUCCESS 
,main.mainifrecord                          
,main.SENDYEAR                        
,main.SENDMONTH                       
,main.SENDDAY                  
--bussines link                               
,null::datetime year to second LINKOPERSTARTTIME                         
,null::datetime year to second LINKOPERENDTIME                               
,null::varchar(255) LINKTESTRESULT                              
,null::varchar(255) LINKNETTYPE                                                              
,null::varchar(255) LINKTGPOLICYACC                                                             
,null::varchar(255) LINKDEVICEVERIFY                                                            
,null::varchar(255) LINKISUPDATE                                                             
,null::varchar(255) LINKISSUCCESS                                                            
,null::datetime year to second LINKSDENDTIME                               
,null::varchar(255) LINKISFINISH                                 
,null::varchar(255) LINKISCHANGE                              
,null::integer operateyear                           
,null::integer operatemonth                     
,null::integer operateday             
            
from businesspilot_main main

join businesspilot_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid
)



-----临时操作

create table est_last_oper(
id number(5) not null primary key,--对应工单类型
last_operate_time TIMESTAMP(6),
comments varchar2(128)--标注
)

delete from est_last_oper

--初始化任务工单采集时间
insert into est_last_oper values (22,'2007-7-7 00:00:00','新业务试点工单基础表采集的上一次操作的时间');

drop table est_last_oper
delete from est_last_oper
select * from est_last_oper

SELECT last_operate_time 
FROM est_last_oper  
WHERE 
id=51



delete from est_businesspilot;
select * from est_businesspilot;
drop table est_businesspilot;

--中间表

CREATE TABLE est_businesspilot
(
  EST_DATA_STATUS integer
, send_bigrole_id integer
, operate_bigrole_id integer
, send_dept_level VARCHAR(30)
, operate_dept_level  VARCHAR(30)
, MAINID VARCHAR(255)
, SHEETID VARCHAR(255)
, TITLE VARCHAR(255)
, SHEETACCEPTLIMIT datetime year to second 
, SHEETCOMPLETELIMIT datetime year to second 
, SENDTIME datetime year to second 
, SENDUSERID VARCHAR(255)
, SENDDEPTID VARCHAR(255)
, SENDROLEID VARCHAR(255)
, SENDCONTACT VARCHAR(255)
, STATUS integer
, HOLDSTATISFIED integer
, ENDTIME datetime year to second
, ENDRESULT VARCHAR(255)
, ENDUSERID VARCHAR(255)
, ENDDEPTID VARCHAR(255)
, ENDROLEID VARCHAR(255)
, DELETED integer
, PIID VARCHAR(255)
, PARENTSHEETNAME VARCHAR(255)
, PARENTSHEETID VARCHAR(255)
, SHEETTEMPLATENAME VARCHAR(255)
, SHEETACCESSORIES VARCHAR(255)
, TODEPTID VARCHAR(255)
, CANCELREASON VARCHAR(255)
, LINKID VARCHAR(255)
, NODEACCEPTLIMIT datetime year to second
, NODECOMPLETELIMIT datetime year to second
, OPERATETYPE integer
, OPERATETIME datetime year to second
, OPERATEUSERID VARCHAR(255)
, OPERATEDEPTID VARCHAR(255)
, OPERATEROLEID VARCHAR(255)
, TOORGTYPE integer
, TOORGUSERID VARCHAR(255)
, TOORGDEPTID VARCHAR(255)
, TOORGROLEID VARCHAR(255)
, ACCEPTFLAG integer
, COMPLETEFLAG integer
, PRELINKID VARCHAR(255)
, PARENTLINKID VARCHAR(255)
, FIRSTLINKID VARCHAR(255)
, AIID VARCHAR(255)
, ACTIVETEMPLATEID VARCHAR(255)
, NODETEMPLATENAME VARCHAR(255)
, NODEACCESSORIES VARCHAR(255)
, OPERATEORGTYPE VARCHAR(255)
, SUBTASKFLAG VARCHAR(50)
, PARENTTASKID VARCHAR(50)
, TASKSTATUS VARCHAR(255)
, taskoperatetype VARCHAR(255)
--bussines main             
,MAINSTARTTIME      datetime year to second                               
,MAINENDTIME        datetime year to second                               
,MAINBUSDEPT        VARCHAR(50)                               
,MAINPRODUCTTYPE    VARCHAR(255)                              
,MAINPRODUCTNAME    VARCHAR(255)                           
,MAINPRODUCTCODE    VARCHAR(255)                              
,MAINISBUS          VARCHAR(50)                               
,MAINBUSSHEETID     VARCHAR(25                             
,MAINISPROJECT      VARCHAR(50)                              
                           
                             
,MAINISSUCCESS      VARCHAR(20)
,mainifrecord       VARCHAR(6)                              
,SENDYEAR           integer                              
,SENDMONTH          integer                        
,SENDDAY            integer           
--bussines link                       
,LINKOPERSTARTTIME    datetime year to second                                
,LINKOPERENDTIME      datetime year to second                               
,LINKTESTRESULT       VARCHAR(255)                              
,LINKNETTYPE          VARCHAR(100)                                                             
,LINKTGPOLICYACC      VARCHAR(250)                                                            
,LINKDEVICEVERIFY     VARCHAR(255)                                                             
,LINKISUPDATE         VARCHAR(50)                                                              
,LINKISSUCCESS        VARCHAR(50)                                                               
,LINKSDENDTIME        datetime year to second                                
,LINKISFINISH         VARCHAR(50)                                
,LINKISCHANGE         VARCHAR(50)                             
,OPERATEYEAR                   integer                                
,OPERATEMONTH                  integer                          
,OPERATEDAY		       integer           
)




