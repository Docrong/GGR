----采集视图
create  view v_businessplan(
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
,MAINPRODUCTTYPE                          
,MAINPRODUCTNAME                         
,MAINPRODUCTCODE                          
,MAINREQTYPE                                                   
,MAINSTANDARD
,mainifrecord                            
,SENDYEAR                          
,SENDMONTH                          
,SENDDAY            
--link 
,LINKAUDITPER                                                           
,LINKREPORT                                    
,LINKISKX                                     
,LINKAUDITRESULT                                                               
,LINKOPIONREPORT                             
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
,main.MAINPRODUCTTYPE                             
,main.MAINPRODUCTNAME                            
,main.MAINPRODUCTCODE                            
,main.MAINREQTYPE                                        
,main.MAINSTANDARD     
,main.mainifrecord                       
,main.SENDYEAR                         
,main.SENDMONTH                       
,main.SENDDAY           

--bussines link
,link.LINKAUDITPER                                             
,link.LINKREPORT                             
,link.LINKISKX                        
,link.LINKAUDITRESULT                                         
,link.LINKOPIONREPORT                             
,link.operateyear                       
,link.operatemonth                 
,link.operateday           

from businessplan_main main

join businessplan_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join businessplan_task task on link.aiid=task.id
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
null::varchar(100) OPERATETYPE,
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
null::varchar(30) OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINPRODUCTTYPE                             
,main.MAINPRODUCTNAME                            
,main.MAINPRODUCTCODE                         
,main.MAINREQTYPE                                            
,main.MAINSTANDARD
,main.mainifrecord                         
,main.SENDYEAR                       
,main.SENDMONTH                       
,main.SENDDAY             
--bussines link
,null::varchar(255) LINKAUDITPER                                                      
,null::varchar(255) LINKREPORT                                
,null::varchar(255) LINKISKX                                     
,null::varchar(255) LINKAUDITRESULT                                                                                           
,null::varchar(255) LINKOPIONREPORT                              
,null::integer   operateyear                  
,null::integer  operatemonth                 
,null::integer   operateday  
from businessplan_main main

join businessplan_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid
)





--初始化新业务设计工单采集时间
insert into est_last_oper values (21,'2007-07-07 00:00:00'),'新业务设计工单基础表采集的上一次操作的时间');






--中间表
CREATE TABLE est_businessplan
(
  EST_DATA_STATUS integer
, send_bigrole_id integer
, operate_bigrole_id integer
, send_dept_level varchar(30)
, operate_dept_level  varchar(30)
, MAINID varchar(255)
, SHEETID varchar(255)
, TITLE varchar(255)
, SHEETACCEPTLIMIT datetime year to second
, SHEETCOMPLETELIMIT datetime year to second
, SENDTIME datetime year to second
, SENDUSERID varchar(255)
, SENDDEPTID varchar(255)
, SENDROLEID varchar(255)
, SENDCONTACT varchar(255)
, STATUS integer
, HOLDSTATISFIED integer
, ENDTIME datetime year to second
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
, NODEACCEPTLIMIT datetime year to second
, NODECOMPLETELIMIT datetime year to second
, OPERATETYPE varchar(100)
, OPERATETIME datetime year to second
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
, OPERATEORGTYPE  varchar(30)  
, SUBTASKFLAG varchar(10)
, PARENTTASKID varchar(50)
, TASKSTATUS varchar(255)
, taskoperatetype varchar(255)
, MAINPRODUCTTYPE    varchar(255)                        
, MAINPRODUCTNAME    varchar(255)                        
, MAINPRODUCTCODE    varchar(255)                       
, MAINREQTYPE           varchar(255) 
, MAINSTANDARD  varchar(255)                      
, mainifrecord integer                         
, SENDYEAR           integer  default 0                
, SENDMONTH          integer  default 0                
, SENDDAY            integer  default 0                   
, LINKAUDITPER    varchar(255)                                
, LINKREPORT      varchar(255)                                                        
, LINKISKX       varchar(255)                         
, LINKAUDITRESULT          varchar(255)                        
, LINKOPIONREPORT        varchar(50)                          
, operateYEAR             integer   default 0                
, operateMONTH            integer   default 0                
, operateDAY              integer   default 0
)extent size 102400 next size 51200 lock mode row;


---建采集相关索引
create index index_businessplantask_sheetkey on businessplan_task 
    (sheetkey) using btree  in indexdbs ;
create index index_businessplanlink_mainid on businessplan_link 
    (mainid) using btree  in indexdbs ;
create index index_businessplanlink_aiid on businessplan_link 
    (aiid) using btree  in indexdbs ;


 create index index_estbusinessplan_mainid on est_businessplan 
    (mainid) using btree  in indexdbs ;
create index index_estbusinessplan_sendtime on est_businessplan 
    (sendtime) using btree  in indexdbs ;
create index index_estbusinessplan_endtime on est_businessplan
    (endtime) using btree  in indexdbs ;
