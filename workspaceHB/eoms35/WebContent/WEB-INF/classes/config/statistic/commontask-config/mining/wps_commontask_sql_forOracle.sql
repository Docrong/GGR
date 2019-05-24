select userid,deptid,password from taw_system_user

select * from faultsheet_main

select * from faultsheet_link

select * from faultsheet_task



--已处理
select ----est_stat---
2 est_data_status,
case when task.operatetype='subrole' then r.roleid else to_number(null) end operate_bigrole_id,
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
link.OPERATETYPE,link.OPERATETIME,link.OPERATEUSERID,link.OPERATEDEPTID,link.OPERATEROLEID,link.TOORGTYPE,link.TOORGUSERID,
link.TOORGDEPTID,link.TOORGROLEID,link.ACCEPTFLAG,link.COMPLETEFLAG,link.PRELINKID,link.PARENTLINKID,link.FIRSTLINKID,
link.AIID,link.ACTIVETEMPLATEID,link.NODETEMPLATENAME,link.NODEACCESSORIES,link.OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week
--common task
task.SUBTASKFLAG,task.PARENTTASKID,task.taskstatus,task.operatetype taskoperatetype,
--bussines main
main.MAINNETSORT1 ,               
main.MAINNETSORT2   ,             
main.MAINNETSORT3   ,             
main.MAINTASKTYPE ,               
main.MAINTASKDESCRIPTION,         
main.MAINREMARK ,                 
main.SENDYEAR  ,                  
main.SENDMONTH  ,                 
main.SENDDAY ,            
--bussines link
link.LINKAUDITRESULT ,   
link.LINKAUDITIDEA ,     
link.LINKTASKCOMPLETE  , 
link.SENDYEAR   operateyear ,      
link.SENDMONTH  operatemonth   ,  
link.SENDDAY    operateday    

from commontask_main main

join commontask_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
join commontask_task task on link.aiid=task.id
left join taw_system_userrefrole refr on refr.subroleid = link.operateroleid
left join taw_system_sub_role r on refr.subroleid=r.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid


--select * from commontask_link link , commontask_task task where link.aiid=task.id

--select * from commontask_task

--未处理
select 
----est_stat---
case when task.taskstatus=2 then 0 when task.taskstatus=8 then 1 end est_data_status,
case when task.operatetype='subrole' then r.roleid else to_number(null) end operate_bigrole_id,
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

--bussines link

from commontask_main main

join commontask_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_sub_role r on refr.subroleid=r.id
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid



------------------------------VIEW----------------------------------------
create or replace view v_commontask as
(
--已处理
select ----est_stat---
2 est_data_status,
case when task.operatetype='subrole' then r.roleid else to_number(null) end operate_bigrole_id,
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
link.OPERATETYPE,link.OPERATETIME,link.OPERATEUSERID,link.OPERATEDEPTID,link.OPERATEROLEID,link.TOORGTYPE,link.TOORGUSERID,
link.TOORGDEPTID,link.TOORGROLEID,link.ACCEPTFLAG,link.COMPLETEFLAG,link.PRELINKID,link.PARENTLINKID,link.FIRSTLINKID,
link.AIID,link.ACTIVETEMPLATEID,link.NODETEMPLATENAME,link.NODEACCESSORIES,link.OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week
--common task
task.SUBTASKFLAG,task.PARENTTASKID,task.taskstatus,task.operatetype taskoperatetype,
--bussines main
main.MAINNETSORT1 ,
main.MAINNETSORT2   ,
main.MAINNETSORT3   ,
main.MAINTASKTYPE ,
main.MAINTASKDESCRIPTION,
main.MAINREMARK ,
main.SENDYEAR  ,
main.SENDMONTH  ,
main.SENDDAY ,
--bussines link
link.LINKAUDITRESULT ,
link.LINKAUDITIDEA ,
link.LINKTASKCOMPLETE  ,
link.SENDYEAR   operateyear ,
link.SENDMONTH  operatemonth   ,
link.SENDDAY    operateday

from commontask_main main

join commontask_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
join commontask_task task on link.aiid=task.id
left join taw_system_userrefrole refr on refr.subroleid = link.operateroleid
left join taw_system_sub_role r on refr.subroleid=r.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid

)
--select * from commontask_link link , commontask_task task where link.aiid=task.id

--select * from commontask_task)
union all
(
--未处理
select
----est_stat---
case when task.taskstatus=2 then 0 when task.taskstatus=8 then 1 end est_data_status,
case when task.operatetype='subrole' then r.roleid else to_number(null) end operate_bigrole_id,
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
to_number(null) OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype,
--bussines main
main.MAINNETSORT1 ,
main.MAINNETSORT2   ,
main.MAINNETSORT3   ,
main.MAINTASKTYPE ,
main.MAINTASKDESCRIPTION,
main.MAINREMARK ,
main.SENDYEAR  ,
main.SENDMONTH  ,
main.SENDDAY ,
--bussines link
to_char(null) LINKAUDITRESULT ,
to_char(null) LINKAUDITIDEA ,
to_char(null) LINKTASKCOMPLETE  ,
to_number(null)   operateyear ,
to_number(null)   operatemonth ,
to_number(null) operateday

from commontask_main main

join commontask_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_sub_role r on refr.subroleid=r.id
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid
)




create table est_last_oper(
id number(5) not null primary key,--对应工单类型
last_operate_time TIMESTAMP(6),
comments varchar2(128)--标注
)

delete from est_last_oper

--初始化任务工单采集时间
insert into est_last_oper
values (1,to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'通用任务工单基础表采集的上一次操作的时间')


--中间表
CREATE TABLE est_commontask
(
  EST_DATA_STATUS NUMBER(5)
, send_bigrole_id number(5)
, operate_bigrole_id number(5)
, send_dept_level VARCHAR2(30)
, operate_dept_level  VARCHAR2(30)
, MAINID VARCHAR2(510)
, SHEETID VARCHAR2(510)
, TITLE VARCHAR2(510)
, SHEETACCEPTLIMIT  date
, SHEETCOMPLETELIMIT  date
, SENDTIME  date
, SENDUSERID VARCHAR2(510)
, SENDDEPTID VARCHAR2(510)
, SENDROLEID VARCHAR2(510)
, SENDCONTACT VARCHAR2(510)
, STATUS number(10)
, HOLDSTATISFIED number(10)
, ENDTIME  date
, ENDRESULT VARCHAR2(510)
, ENDUSERID VARCHAR2(510)
, ENDDEPTID VARCHAR2(510)
, ENDROLEID VARCHAR2(510)
, DELETED NUMBER(10)
, PIID VARCHAR2(510)
, PARENTSHEETNAME VARCHAR2(510)
, PARENTSHEETID VARCHAR2(510)
, SHEETTEMPLATENAME VARCHAR2(510)
, SHEETACCESSORIES VARCHAR2(510)
, TODEPTID VARCHAR2(510)
, CANCELREASON VARCHAR2(510)
, LINKID VARCHAR2(510)
, NODEACCEPTLIMIT  date
, NODECOMPLETELIMIT  date
, OPERATETYPE NUMBER(10)
, OPERATETIME  date
, OPERATEUSERID VARCHAR2(510)
, OPERATEDEPTID VARCHAR2(510)
, OPERATEROLEID VARCHAR2(510)
, TOORGTYPE NUMBER(10)
, TOORGUSERID VARCHAR2(510)
, TOORGDEPTID VARCHAR2(510)
, TOORGROLEID VARCHAR2(510)
, ACCEPTFLAG NUMBER(10)
, COMPLETEFLAG NUMBER(10)
, PRELINKID VARCHAR2(510)
, PARENTLINKID VARCHAR2(510)
, FIRSTLINKID VARCHAR2(510)
, AIID VARCHAR2(510)
, ACTIVETEMPLATEID VARCHAR2(510)
, NODETEMPLATENAME VARCHAR2(510)
, NODEACCESSORIES VARCHAR2(510)
, OPERATEORGTYPE INTEGER
, SUBTASKFLAG VARCHAR2(10)
, PARENTTASKID VARCHAR2(50)
, TASKSTATUS VARCHAR2(510)
, TASKOPERATETYPE VARCHAR2(25)
,MAINNETSORT1        VARCHAR2(100)                          
,MAINNETSORT2        VARCHAR2(100)                          
,MAINNETSORT3        VARCHAR2(100)                          
,MAINTASKTYPE        VARCHAR2(100)                         
,MAINTASKDESCRIPTION VARCHAR2(254)                         
,MAINREMARK          VARCHAR2(254)                         
,SENDYEAR            INTEGER                   
,SENDMONTH           INTEGER                    
,SENDDAY             INTEGER      
,LINKAUDITRESULT      VARCHAR2(100)                       
,LINKAUDITIDEA        VARCHAR2(254)                   
,LINKTASKCOMPLETE     VARCHAR2(254)                 
,operateyear             INTEGER                   
,operateMONTH            INTEGER          
,operateDAY              INTEGER    
)
