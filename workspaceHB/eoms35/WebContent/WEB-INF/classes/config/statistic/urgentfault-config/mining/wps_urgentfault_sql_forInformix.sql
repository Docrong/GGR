select userid,deptid,password from taw_system_user

select * from urgentfault_main
      
select * from urgentfault_link

select * from urgentfault_task


--已处理
select 
----est_stat---
2::integer est_data_status,
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
,main.MAINSENDCONTENTTYPE                              
,main.MAINIFGREATFAULT                                  
,main.MAINGREATFAULTID                                   
,main.MAINFAULTSHEETID                                     
,main.MAINFAULTGENERANTTIME                         
,main.MAINFAULTGENERANTPLACE                              
,main.MAINEQUIPMENTFACTORY                              
                                
,main.MAINFAULTREASON                                     
,main.MAINTRAFFICLOSS                                     
,main.MAINIMPACTUSERNUM                                   
,main.MAINIFAFFECTOPERATION                               
,main.MAINAFFECTAREA                                      
                           
,main.MAINAFFECTSTARTTIME                                
,main.MAINAFFECTOPERATIONLEVEL                            
,main.MAINAFFECTOPERATIONSORT                             
,main.MAINTRIGGERUSERCOMPLAINT                           
,main.MAINIFREPORT                                         
,main.MAINIFACHIEVEPROJECTSTARTUP                          
,main.MAINURGENTLEVE                                     
,main.MAINFAULTSPECIALITY                               
,main.MAINEQUIPINTEGRATOR                                
,main.MAINFAULTAVOIDTIME                                  
,main.MAINAFFECTTIMELENGTH                                                      
,main.MAINNETSORTONE                                      
,main.MAINNETSORTTWO                                     
,main.MAINNETSORTTHREE                                    
,main.MAINFAULTSUBREASON                              
,main.MAINFAULTLOCATION                                  
,main.MAINREPORTLIMIT                                    
,main.SENDYEAR                                 
,main.SENDMONTH                                  
,main.SENDDAY                             
,main.MAINIFRECORD                      
--bussines link
,link.LINKIFGREATFAULT                               
,link.LINKFAULTGENERANTPLACE                         
,link.LINKFAULTAVOIDTIME                               
,link.LINKAFFECTTIMELENGTH                            
                         
                            
,link.LINKGREATFAULTID                              
,link.LINKIFAFFIRM                                   
                                                    
,link.LINKFAULTREASON                              
,link.LINKFAULTSUBREASON                         
,link.OPERATEYEAR                                     
,link.OPERATEMONTH                                  
,link.OPERATEDAY                
from urgentfault_main main

join urgentfault_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join urgentfault_task task on link.aiid=task.id
join taw_system_sub_role orole on link.operateroleid=orole.id
join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid

select * from urgentfault_task



--select * from commonfault_link link , commonfault_task task where link.aiid=task.id

--select * from commonfault_task


--未处理
select 
----est_stat---
case when task.taskstatus=2 then 0::integer when task.taskstatus=8 then 1::integer end est_data_status,
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
to_char(null) linkid,
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
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINSENDCONTENTTYPE                              
,main.MAINIFGREATFAULT                                  
,main.MAINGREATFAULTID                                   
,main.MAINFAULTSHEETID                                     
,main.MAINFAULTGENERANTTIME                         
,main.MAINFAULTGENERANTPLACE                              
,main.MAINEQUIPMENTFACTORY                              
                                
,main.MAINFAULTREASON                                     
,main.MAINTRAFFICLOSS                                     
,main.MAINIMPACTUSERNUM                                   
,main.MAINIFAFFECTOPERATION                               
,main.MAINAFFECTAREA                                      
                           
,main.MAINAFFECTSTARTTIME                                
,main.MAINAFFECTOPERATIONLEVEL                            
,main.MAINAFFECTOPERATIONSORT                             
,main.MAINTRIGGERUSERCOMPLAINT                           
,main.MAINIFREPORT                                         
,main.MAINIFACHIEVEPROJECTSTARTUP                          
,main.MAINURGENTLEVE                                     
,main.MAINFAULTSPECIALITY                               
,main.MAINEQUIPINTEGRATOR                                
,main.MAINFAULTAVOIDTIME                                  
,main.MAINAFFECTTIMELENGTH                                                      
,main.MAINNETSORTONE                                      
,main.MAINNETSORTTWO                                     
,main.MAINNETSORTTHREE                                    
,main.MAINFAULTSUBREASON                              
,main.MAINFAULTLOCATION                                  
,main.MAINREPORTLIMIT                                    
,main.SENDYEAR                                 
,main.SENDMONTH                                  
,main.SENDDAY                             
,main.MAINIFRECORD                  
--bussines link
,null::varchar(255) LINKIFGREATFAULT                               
,null::varchar(255) LINKFAULTGENERANTPLACE                         
,null::datetime year to second LINKFAULTAVOIDTIME                               
,null::varchar(255) LINKAFFECTTIMELENGTH                            
                         
                           
,null::varchar(255) LINKGREATFAULTID                              
,null::varchar(255) LINKIFAFFIRM                                   
                                                   
,null::varchar(255) LINKFAULTREASON                              
,null::varchar(255) LINKFAULTSUBREASON                         
,null::integer OPERATEYEAR                               
,null::integer OPERATEMONTH                               
,null::integer OPERATEDAY            
                 
from urgentfault_main main

join urgentfault_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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

select null::varchar(255) from dual

select null from dual

select null::datetime year to second from dual

create table test1 as select null a from dual

drop table test1





------------------------------VIEW----------------------------------------
select * from v_urgentfault
select distinct * from v_urgentfault
drop view v_urgentfault
--drop view v_urgentfault
create view v_urgentfault(
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
,MAINSENDCONTENTTYPE                              
,MAINIFGREATFAULT                                  
,MAINGREATFAULTID                                   
,MAINFAULTSHEETID                                     
,MAINFAULTGENERANTTIME                         
,MAINFAULTGENERANTPLACE                              
,MAINEQUIPMENTFACTORY                              
                                 
,MAINFAULTREASON                                     
,MAINTRAFFICLOSS                                     
,MAINIMPACTUSERNUM                                   
,MAINIFAFFECTOPERATION                               
,MAINAFFECTAREA                                      
                            
,MAINAFFECTSTARTTIME                                
,MAINAFFECTOPERATIONLEVEL                            
,MAINAFFECTOPERATIONSORT                             
,MAINTRIGGERUSERCOMPLAINT                           
,MAINIFREPORT                                         
,MAINIFACHIEVEPROJECTSTARTUP                          
,MAINURGENTLEVE                                     
,MAINFAULTSPECIALITY                               
,MAINEQUIPINTEGRATOR                                
,MAINFAULTAVOIDTIME                                  
,MAINAFFECTTIMELENGTH                                                      
,MAINNETSORTONE                                      
,MAINNETSORTTWO                                     
,MAINNETSORTTHREE                                    
,MAINFAULTSUBREASON                              
,MAINFAULTLOCATION                                  
,MAINREPORTLIMIT                                    
,SENDYEAR                                  
,SENDMONTH                                
,SENDDAY 
,MAINIFRECORD                  
--bussines link
,LINKIFGREATFAULT                               
,LINKFAULTGENERANTPLACE                         
,LINKFAULTAVOIDTIME                               
,LINKAFFECTTIMELENGTH                            
                         
                           
,LINKGREATFAULTID                              
,LINKIFAFFIRM                                   
                                                   
,LINKFAULTREASON                              
,LINKFAULTSUBREASON                         
,OPERATEYEAR                                  
,OPERATEMONTH                                 
,OPERATEDAY          

) as 
(
--已处理
select 
----est_stat---
2::integer est_data_status,
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
,main.MAINSENDCONTENTTYPE                              
,main.MAINIFGREATFAULT                                  
,main.MAINGREATFAULTID                                   
,main.MAINFAULTSHEETID                                     
,main.MAINFAULTGENERANTTIME                         
,main.MAINFAULTGENERANTPLACE                              
,main.MAINEQUIPMENTFACTORY                              
                                 
,main.MAINFAULTREASON                                     
,main.MAINTRAFFICLOSS                                     
,main.MAINIMPACTUSERNUM                                   
,main.MAINIFAFFECTOPERATION                               
,main.MAINAFFECTAREA                                      
                            
,main.MAINAFFECTSTARTTIME                                
,main.MAINAFFECTOPERATIONLEVEL                            
,main.MAINAFFECTOPERATIONSORT                             
,main.MAINTRIGGERUSERCOMPLAINT                           
,main.MAINIFREPORT                                         
,main.MAINIFACHIEVEPROJECTSTARTUP                          
,main.MAINURGENTLEVE                                     
,main.MAINFAULTSPECIALITY                               
,main.MAINEQUIPINTEGRATOR                                
,main.MAINFAULTAVOIDTIME                                  
,main.MAINAFFECTTIMELENGTH                                                      
,main.MAINNETSORTONE                                      
,main.MAINNETSORTTWO                                     
,main.MAINNETSORTTHREE                                    
,main.MAINFAULTSUBREASON                              
,main.MAINFAULTLOCATION                                  
,main.MAINREPORTLIMIT                                    
,main.SENDYEAR                                 
,main.SENDMONTH                                  
,main.SENDDAY                             
,main.MAINIFRECORD                      
--bussines link
,link.LINKIFGREATFAULT                               
,link.LINKFAULTGENERANTPLACE                         
,link.LINKFAULTAVOIDTIME                               
,link.LINKAFFECTTIMELENGTH                            
                         
                            
,link.LINKGREATFAULTID                              
,link.LINKIFAFFIRM                                   
                                                   
,link.LINKFAULTREASON                              
,link.LINKFAULTSUBREASON                         
,link.OPERATEYEAR                                     
,link.OPERATEMONTH                                  
,link.OPERATEDAY                
from urgentfault_main main

join urgentfault_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join urgentfault_task task on link.aiid=task.id
join taw_system_sub_role orole on link.operateroleid=orole.id
join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid
)
union all
(
--未处理
select 
----est_stat---
case when task.taskstatus=2 then 0::integer when task.taskstatus=8 then 1::integer end est_data_status,
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
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINSENDCONTENTTYPE                              
,main.MAINIFGREATFAULT                                  
,main.MAINGREATFAULTID                                   
,main.MAINFAULTSHEETID                                     
,main.MAINFAULTGENERANTTIME                         
,main.MAINFAULTGENERANTPLACE                              
,main.MAINEQUIPMENTFACTORY                              
                                
,main.MAINFAULTREASON                                     
,main.MAINTRAFFICLOSS                                     
,main.MAINIMPACTUSERNUM                                   
,main.MAINIFAFFECTOPERATION                               
,main.MAINAFFECTAREA                                      
                            
,main.MAINAFFECTSTARTTIME                                
,main.MAINAFFECTOPERATIONLEVEL                            
,main.MAINAFFECTOPERATIONSORT                             
,main.MAINTRIGGERUSERCOMPLAINT                           
,main.MAINIFREPORT                                         
,main.MAINIFACHIEVEPROJECTSTARTUP                          
,main.MAINURGENTLEVE                                     
,main.MAINFAULTSPECIALITY                               
,main.MAINEQUIPINTEGRATOR                                
,main.MAINFAULTAVOIDTIME                                  
,main.MAINAFFECTTIMELENGTH                                                      
,main.MAINNETSORTONE                                      
,main.MAINNETSORTTWO                                     
,main.MAINNETSORTTHREE                                    
,main.MAINFAULTSUBREASON                              
,main.MAINFAULTLOCATION                                  
,main.MAINREPORTLIMIT                                    
,main.SENDYEAR                                 
,main.SENDMONTH                                  
,main.SENDDAY                             
,main.MAINIFRECORD                  
--bussines link
,null::varchar(255) LINKIFGREATFAULT                               
,null::varchar(255) LINKFAULTGENERANTPLACE                         
,null::datetime year to second LINKFAULTAVOIDTIME                               
,null::varchar(255) LINKAFFECTTIMELENGTH                            
                         
                           
,null::varchar(255) LINKGREATFAULTID                              
,null::varchar(255) LINKIFAFFIRM                                   
                                                    
,null::varchar(255) LINKFAULTREASON                              
,null::varchar(255) LINKFAULTSUBREASON                         
,null::integer OPERATEYEAR                               
,null::integer OPERATEMONTH                               
,null::integer OPERATEDAY            
                 
from urgentfault_main main

join urgentfault_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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
insert into est_last_oper values (53,'2007-07-07 00:00:00','紧急故障工单基础表采集的上一次操作的时间');

drop table est_last_oper
delete from est_last_oper
select * from est_last_oper

SELECT *
FROM est_last_oper  
WHERE 
id=51

select link.operatetime from commonfault_link link

SELECT count(distinct mainid) FROM commonfault_link HAVING max(operatetime) >= to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS') and max(operatetime) < sysdate

select mainid,operatetime from commonfault_link

delete from est_urgentfault;
select * from est_urgentfault;
drop table est_urgentfault;

--中间表
CREATE TABLE est_urgentfault
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
, OPERATETYPE integer
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
, OPERATEORGTYPE varchar(25)
, SUBTASKFLAG varchar(10)
, PARENTTASKID varchar(50)
, TASKSTATUS varchar(255)
, taskoperatetype varchar(255)
--bussines main
,MAINSENDCONTENTTYPE     varchar(255)                         
,MAINIFGREATFAULT         varchar(255)                          
,MAINGREATFAULTID          varchar(255)                          
,MAINFAULTSHEETID           varchar(255)                           
,MAINFAULTGENERANTTIME       datetime year to second                  
,MAINFAULTGENERANTPLACE      varchar(255)                         
,MAINEQUIPMENTFACTORY          varchar(255)                     
              
,MAINFAULTREASON                varchar(255)                      
,MAINTRAFFICLOSS               varchar(255)                       
,MAINIMPACTUSERNUM              varchar(255)                      
,MAINIFAFFECTOPERATION          varchar(255)                      
,MAINAFFECTAREA                 varchar(255)                      
                     
,MAINAFFECTSTARTTIME           datetime year to second                     
,MAINAFFECTOPERATIONLEVEL        varchar(255)                     
,MAINAFFECTOPERATIONSORT          varchar(255)                    
,MAINTRIGGERUSERCOMPLAINT         varchar(255)                   
,MAINIFREPORT                    varchar(255)                      
,MAINIFACHIEVEPROJECTSTARTUP      varchar(255)                     
,MAINURGENTLEVE                   varchar(255)                   
,MAINFAULTSPECIALITY                varchar(255)                
,MAINEQUIPINTEGRATOR              varchar(255)                   
,MAINFAULTAVOIDTIME                 date                  
,MAINAFFECTTIMELENGTH                 varchar(255)                                      
,MAINNETSORTONE                         varchar(255)              
,MAINNETSORTTWO                        varchar(255)              
,MAINNETSORTTHREE                       varchar(255)              
,MAINFAULTSUBREASON                    varchar(255)           
,MAINFAULTLOCATION                      varchar(255)             
,MAINREPORTLIMIT                       datetime year to second             
,SENDYEAR                             integer     
,SENDMONTH                            integer   
,SENDDAY			      integer
,MAINIFRECORD                integer
--bussines link
,LINKIFGREATFAULT                     varchar(255)          
,LINKFAULTGENERANTPLACE                varchar(255)         
,LINKFAULTAVOIDTIME                    datetime year to second         
,LINKAFFECTTIMELENGTH                 varchar(255)           
           
          
,LINKGREATFAULTID                     varchar(255)         
,LINKIFAFFIRM                        varchar(255)           
                                 
,LINKFAULTREASON                     varchar(35)         
,LINKFAULTSUBREASON                    varchar(35)     
,OPERATEYEAR                            integer     
,OPERATEMONTH                           integer      
,OPERATEDAY				integer
)
select * from est_last_oper






