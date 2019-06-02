select userid,deptid,password from taw_system_user

select * from circuitdispatch_main t where t.mainisrecord=0 is null
select * from est_circuitdispatch
select * from circuitdispatch_link

select * from circuitdispatch_task
select * from v_circuitdispatch
--增加电路调动工单字段 main
alter   table   circuitdispatch_main   add   mainExecuteEndDate   varchar2(50);
alter   table   circuitdispatch_main   add   mainisrecord  NUMBER(10) default 0;
-- link  表
alter   table   circuitdispatch_link   add   linkrejectreason  varchar2(500);
--main

MAINIFSAFE                NUMBER(10)     Y                         
MAINIFMUTUALCOMMUNICATION NUMBER(10)     Y                         
MAINNETSORTONE            VARCHAR2(255)  Y                         
MAINEQUIPMENTFACTORY      VARCHAR2(255)  Y                         
                        
                       
MAINIFCHANGEPROJECT       NUMBER(10)     Y                         
MAINCHANGESOURCE          VARCHAR2(510)  Y                         
MAINTOUCHSHEETID          VARCHAR2(510)  Y                         
MAINRESOURCENO            VARCHAR2(510)  Y                         
MAINNETSORTTWO            VARCHAR2(255)  Y                         
MAINNETSORTTHREE          VARCHAR2(255)  Y                         
MAINREJECTTIMES           NUMBER(10)     Y                         
SENDYEAR                  INTEGER        Y        0                
SENDMONTH                 INTEGER        Y        0                
SENDDAY                   INTEGER        Y        0                
MAINIFRECORD              NUMBER(10)     Y        0                
MAINEXECUTEENDDATE        TIMESTAMP(6)
--link            
 
LINKEXECUTEENDDATE         TIMESTAMP(6)   Y                         
LINKPROGRAMMEEXPLAIN       VARCHAR2(4000) Y                         
LINKPROGRAMMEKEY           VARCHAR2(510)  Y                         
                       
                        
LINKPROGRAMMENO            VARCHAR2(510)  Y                         
LINKPERMITRESULT           NUMBER(10)     Y                         
                        
LINKEXCUTEPRINCIPAL        VARCHAR2(510)  Y                         
LINKCONTACT                VARCHAR2(510)  Y                         
LINKPLANSTARTDATE          TIMESTAMP(6)   Y                         
LINKPLANENDDATE            TIMESTAMP(6)   Y                         
                        
LINKIFAFFECTOPERATION      NUMBER(10)     Y                         
                       
                        
LINKREFEROPERATEDEPT       VARCHAR2(510)  Y                         
LINKIFNOTIFY               NUMBER(10)     Y                         
LINKEXCUTERESULT           NUMBER(10)     Y                         
LINKISACCORDANCEPROGRAMME  NUMBER(10)     Y                         
                      
                        
LINKTESTRESULT             NUMBER(10)     Y                         
                       
                        
                         
LINKIFUPDATEPLAN           NUMBER(10)     Y                         
                       
LINKCIRCUITUPDATE          NUMBER(10)     Y                         
                      
LINKISTOMAINTENANCE        NUMBER(10)     Y                         
                        
LINKFAILEDREASON           VARCHAR2(510)  Y                         
LINKINVOLVEDPROVINCE       VARCHAR2(510)  Y                         
LINKINVOLVEDCITY           VARCHAR2(510)  Y                         
OPERATEYEAR                INTEGER        Y        0                
OPERATEMONTH               INTEGER        Y        0                
OPERATEDAY                 INTEGER        Y        0                
LINKREJECTREASON           VARCHAR2(500)  Y   




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
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINIFSAFE                                    
,main.MAINIFMUTUALCOMMUNICATION                        
,main.MAINNETSORTONE                                  
,main.MAINEQUIPMENTFACTORY                              
                                    
                                  
,main.MAINIFCHANGEPROJECT                           
,main.MAINCHANGESOURCE                               
,main.MAINTOUCHSHEETID                                
,main.MAINRESOURCENO                                   
,main.MAINNETSORTTWO                                   
,main.MAINNETSORTTHREE                               
,main.MAINREJECTTIMES                          
,main.SENDYEAR                               
,main.SENDMONTH                       
,main.SENDDAY                      
,main.MAINIFRECORD                       
,main.MAINEXECUTEENDDATE               

--bussines link
,link.LINKEXECUTEENDDATE                              
,link.LINKPROGRAMMEEXPLAIN                              
,link.LINKPROGRAMMEKEY                              
                            
                         
,link.LINKPROGRAMMENO                                  
,link.LINKPERMITRESULT                                
                                
,link.LINKEXCUTEPRINCIPAL                        
,link.LINKCONTACT                                    
,link.LINKPLANSTARTDATE                                  
,link.LINKPLANENDDATE                                    
                             
,link.LINKIFAFFECTOPERATION                            
                             
                               
,link.LINKREFEROPERATEDEPT                              
,link.LINKIFNOTIFY                                     
,link.LINKEXCUTERESULT                           
,link.LINKISACCORDANCEPROGRAMME                     
                           
                         
,link.LINKTESTRESULT                                      
                            
                             
,link.LINKIFUPDATEPLAN                                  
                                
,link.LINKCIRCUITUPDATE                            
                                
,link.LINKISTOMAINTENANCE                             
                           
,link.LINKFAILEDREASON                                 
,link.LINKINVOLVEDPROVINCE                               
,link.LINKINVOLVEDCITY                                
,link.OPERATEYEAR                             
,link.OPERATEMONTH                           
,link.OPERATEDAY                              
,link.LINKREJECTREASON                         


from circuitdispatch_main main

join circuitdispatch_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join circuitdispatch_task task on link.aiid=task.id
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
null::varchar(255) OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINIFSAFE                                    
,main.MAINIFMUTUALCOMMUNICATION                        
,main.MAINNETSORTONE                                  
,main.MAINEQUIPMENTFACTORY                              
                                  
                                  
,main.MAINIFCHANGEPROJECT                           
,main.MAINCHANGESOURCE                               
,main.MAINTOUCHSHEETID                                
,main.MAINRESOURCENO                                   
,main.MAINNETSORTTWO                                   
,main.MAINNETSORTTHREE                               
,main.MAINREJECTTIMES                          
,main.SENDYEAR                               
,main.SENDMONTH                       
,main.SENDDAY                      
,main.MAINIFRECORD                       
,main.MAINEXECUTEENDDATE                      

--bussines link
,null::DATETIME YEAR TO SECOND LINKEXECUTEENDDATE                                
,null::varchar(255) LINKPROGRAMMEEXPLAIN                             
,null::varchar(255) LINKPROGRAMMEKEY                                 
                               
                           
,null::varchar(255) LINKPROGRAMMENO                                
,null::integer LINKPERMITRESULT                                 
                             
,null::varchar(255) LINKEXCUTEPRINCIPAL                            
,null::varchar(255) LINKCONTACT                                    
,null::DATETIME YEAR TO SECOND LINKPLANSTARTDATE                               
,null::DATETIME YEAR TO SECOND LINKPLANENDDATE                                
                               
,null::integer LINKIFAFFECTOPERATION                            
                          
                          
,null::varchar(255) LINKREFEROPERATEDEPT                         
,null::integer LINKIFNOTIFY                                     
,null::integer LINKEXCUTERESULT                                 
,null::integer LINKISACCORDANCEPROGRAMME                       
                               
                       
,null::integer LINKTESTRESULT                                 
                                   
                        
                              
,null::integer LINKIFUPDATEPLAN                                   
                             
,null::integer LINKCIRCUITUPDATE                             
                            
,null::integer LINKISTOMAINTENANCE                           
                              
,null::varchar(255) LINKFAILEDREASON                               
,null::varchar(255) LINKINVOLVEDPROVINCE                          
,null::varchar(255) LINKINVOLVEDCITY                                  
,null::integer OPERATEYEAR                            
,null::integer OPERATEMONTH                             
,null::integer OPERATEDAY                                
,null::varchar(255) LINKREJECTREASON        

from circuitdispatch_main main

join circuitdispatch_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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
select TOORGROLEID,ACCEPTFLAG,ACCEPTTIME from commonfault_link

TOORGROLEID               VARCHAR2(510)  Y                         
ACCEPTFLAG                NUMBER(10)     Y                         
ACCEPTTIME                TIMESTAMP(6)

select 1 from dual

select null::varchar(255) from dual

select null from dual

select null::DATETIME YEAR TO SECOND from dual

create table test1 as select null a from dual

drop table test1



------------------------------VIEW----------------------------------------
select * from v_circuitdispatch
select distinct * from v_circuitdispatch

--drop view v_commomfault
create  view v_circuitdispatch(
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
,MAINIFSAFE                                    
,MAINIFMUTUALCOMMUNICATION                        
,MAINNETSORTONE                                  
,MAINEQUIPMENTFACTORY                              
                                  
                                  
,MAINIFCHANGEPROJECT                           
,MAINCHANGESOURCE                               
,MAINTOUCHSHEETID                                
,MAINRESOURCENO                                   
,MAINNETSORTTWO                                   
,MAINNETSORTTHREE                               
,MAINREJECTTIMES                          
,SENDYEAR                               
,SENDMONTH                       
,SENDDAY                      
,MAINIFRECORD                       
,MAINEXECUTEENDDATE     
--bussines link
, LINKEXECUTEENDDATE                                
, LINKPROGRAMMEEXPLAIN                             
, LINKPROGRAMMEKEY                                 
                              
                           
, LINKPROGRAMMENO                                
, LINKPERMITRESULT                                 
                            
, LINKEXCUTEPRINCIPAL                            
, LINKCONTACT                                    
, LINKPLANSTARTDATE                               
, LINKPLANENDDATE                                
                              
, LINKIFAFFECTOPERATION                            
                         
                         
, LINKREFEROPERATEDEPT                         
, LINKIFNOTIFY                                     
, LINKEXCUTERESULT                                 
, LINKISACCORDANCEPROGRAMME                       
                              
                       
, LINKTESTRESULT                                 
                                
                      
                               
, LINKIFUPDATEPLAN                                   
                              
, LINKCIRCUITUPDATE                             
                           
, LINKISTOMAINTENANCE                           
                              
, LINKFAILEDREASON                               
, LINKINVOLVEDPROVINCE                          
, LINKINVOLVEDCITY                                  
, OPERATEYEAR                            
, OPERATEMONTH                             
, OPERATEDAY                                
, LINKREJECTREASON              
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
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINIFSAFE                                    
,main.MAINIFMUTUALCOMMUNICATION                        
,main.MAINNETSORTONE                                  
,main.MAINEQUIPMENTFACTORY                              
                                   
                                  
,main.MAINIFCHANGEPROJECT                           
,main.MAINCHANGESOURCE                               
,main.MAINTOUCHSHEETID                                
,main.MAINRESOURCENO                                   
,main.MAINNETSORTTWO                                   
,main.MAINNETSORTTHREE                               
,main.MAINREJECTTIMES                          
,main.SENDYEAR                               
,main.SENDMONTH                       
,main.SENDDAY                      
,main.MAINIFRECORD                       
,main.MAINEXECUTEENDDATE               

--bussines link
,link.LINKEXECUTEENDDATE                              
,link.LINKPROGRAMMEEXPLAIN                              
,link.LINKPROGRAMMEKEY                              
                              
                        
,link.LINKPROGRAMMENO                                  
,link.LINKPERMITRESULT                                
                                
,link.LINKEXCUTEPRINCIPAL                        
,link.LINKCONTACT                                    
,link.LINKPLANSTARTDATE                                  
,link.LINKPLANENDDATE                                    
                             
,link.LINKIFAFFECTOPERATION                            
                            
                              
,link.LINKREFEROPERATEDEPT                              
,link.LINKIFNOTIFY                                     
,link.LINKEXCUTERESULT                           
,link.LINKISACCORDANCEPROGRAMME                     
                            
                         
,link.LINKTESTRESULT                                      
                             
                       
                            
,link.LINKIFUPDATEPLAN                                  
                              
,link.LINKCIRCUITUPDATE                            
                                
,link.LINKISTOMAINTENANCE                             
                          
,link.LINKFAILEDREASON                                 
,link.LINKINVOLVEDPROVINCE                               
,link.LINKINVOLVEDCITY                                
,link.OPERATEYEAR                             
,link.OPERATEMONTH                           
,link.OPERATEDAY                              
,link.LINKREJECTREASON                         


from circuitdispatch_main main

join circuitdispatch_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join circuitdispatch_task task on link.aiid=task.id
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
null::varchar(255) OPERATEORGTYPE,
  ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINIFSAFE                                    
,main.MAINIFMUTUALCOMMUNICATION                        
,main.MAINNETSORTONE                                  
,main.MAINEQUIPMENTFACTORY                              
                                   
                                 
,main.MAINIFCHANGEPROJECT                           
,main.MAINCHANGESOURCE                               
,main.MAINTOUCHSHEETID                                
,main.MAINRESOURCENO                                   
,main.MAINNETSORTTWO                                   
,main.MAINNETSORTTHREE                               
,main.MAINREJECTTIMES                          
,main.SENDYEAR                               
,main.SENDMONTH                       
,main.SENDDAY                      
,main.MAINIFRECORD                       
,main.MAINEXECUTEENDDATE                      

--bussines link
,null::DATETIME YEAR TO SECOND LINKEXECUTEENDDATE                                
,null::varchar(255) LINKPROGRAMMEEXPLAIN                             
,null::varchar(255) LINKPROGRAMMEKEY                                 
                               
                           
,null::varchar(255) LINKPROGRAMMENO                                
,null::integer LINKPERMITRESULT                                 
                             
,null::varchar(255) LINKEXCUTEPRINCIPAL                            
,null::varchar(255) LINKCONTACT                                    
,null::DATETIME YEAR TO SECOND LINKPLANSTARTDATE                               
,null::DATETIME YEAR TO SECOND LINKPLANENDDATE                                
                              
,null::integer LINKIFAFFECTOPERATION                            
                           
                          
,null::varchar(255) LINKREFEROPERATEDEPT                         
,null::integer LINKIFNOTIFY                                     
,null::integer LINKEXCUTERESULT                                 
,null::integer LINKISACCORDANCEPROGRAMME                       
                              
                        
,null::integer LINKTESTRESULT                                 
                                   
                        
                              
,null::integer LINKIFUPDATEPLAN                                   
                             
,null::integer LINKCIRCUITUPDATE                             
                           
,null::integer LINKISTOMAINTENANCE                           
                              
,null::varchar(255) LINKFAILEDREASON                               
,null::varchar(255) LINKINVOLVEDPROVINCE                          
,null::varchar(255) LINKINVOLVEDCITY                                  
,null::integer OPERATEYEAR                            
,null::integer OPERATEMONTH                             
,null::integer OPERATEDAY                                
,null::varchar(255) LINKREJECTREASON        

from circuitdispatch_main main

join circuitdispatch_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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
insert into est_last_oper values (42,'2007-07-07 00:00:00','电路调度工单基础表采集的上一次操作的时间');

drop table est_last_oper
delete from est_last_oper
select * from est_last_oper

SELECT *-- last_operate_time 
FROM est_last_oper  
WHERE 
id=42 for update 

select link.operatetime from commonfault_link link

SELECT count(distinct mainid) FROM commonfault_link HAVING max(operatetime) >= '2007-07-07 00:00:00' and max(operatetime) < current

select mainid,operatetime from commonfault_link

--delete from est_commonfault
drop table est_circuitdispatch
select * from est_circuitdispatch
--drop table est_circuitdispatch
--中间表
select * from  est_circuitdispatch

CREATE TABLE est_circuitdispatch
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
, NODEACCEPTLIMIT  DATETIME YEAR TO SECOND
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
, OPERATEORGTYPE varchar(25)
, SUBTASKFLAG varchar(10)
, PARENTTASKID varchar(50)
, TASKSTATUS varchar(255)
, TASKOPERATETYPE varchar(255)
,MAINIFSAFE                integer                           
,MAINIFMUTUALCOMMUNICATION integer                              
,MAINNETSORTONE            varchar(255)                          
,MAINEQUIPMENTFACTORY      varchar(255)                           
                         
                         
,MAINIFCHANGEPROJECT       integer                             
,MAINCHANGESOURCE          varchar(255)                           
,MAINTOUCHSHEETID          varchar(255)                          
,MAINRESOURCENO            varchar(255)                          
,MAINNETSORTTWO            varchar(255)                          
,MAINNETSORTTHREE          varchar(255)                          
,MAINREJECTTIMES           integer                             
,SENDYEAR                  INTEGER                              
,SENDMONTH                 INTEGER                              
,SENDDAY                   INTEGER                               
,MAINIFRECORD              integer                        
,MAINEXECUTEENDDATE        DATETIME YEAR TO SECOND
,LINKEXECUTEENDDATE         DATETIME YEAR TO SECOND                         
,LINKPROGRAMMEEXPLAIN       varchar(255)                          
,LINKPROGRAMMEKEY           varchar(255)                           
                          
                           
,LINKPROGRAMMENO            varchar(255)                           
,LINKPERMITRESULT           integer                              
                         
,LINKEXCUTEPRINCIPAL        varchar(255)                          
,LINKCONTACT                varchar(255)                          
,LINKPLANSTARTDATE          DATETIME YEAR TO SECOND                            
,LINKPLANENDDATE            DATETIME YEAR TO SECOND                           
                         
,LINKIFAFFECTOPERATION      integer                              
                      
                          
,LINKREFEROPERATEDEPT       varchar(255)                           
,LINKIFNOTIFY               integer                              
,LINKEXCUTERESULT           integer                              
,LINKISACCORDANCEPROGRAMME  integer                              
                          
                         
,LINKTESTRESULT             integer                             
                          
                          
                        
,LINKIFUPDATEPLAN           integer                              
                          
,LINKCIRCUITUPDATE          integer                              
                         
,LINKISTOMAINTENANCE        integer                              
                        
,LINKFAILEDREASON           varchar(255)                          
,LINKINVOLVEDPROVINCE       varchar(255)                          
,LINKINVOLVEDCITY           varchar(255)                           
,OPERATEYEAR                integer                       
,OPERATEMONTH               integer                   
,OPERATEDAY                 integer                   
,LINKREJECTREASON           varchar(255)                          
)

select * from  est_last_oper where id=42 for update 




insert into est_last_oper values (42,'2007-7-7 00:00:00','电路调度工单基础表采集的上一次操作的时间');


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


, LINKPROGRAMMENO
, LINKPERMITRESULT

, LINKEXCUTEPRINCIPAL
, LINKCONTACT
, LINKPLANSTARTDATE
, LINKPLANENDDATE

, LINKIFAFFECTOPERATION


, LINKREFEROPERATEDEPT
, LINKIFNOTIFY
, LINKEXCUTERESULT
, LINKISACCORDANCEPROGRAMME


, LINKTESTRESULT



, LINKIFUPDATEPLAN

, LINKCIRCUITUPDATE

, LINKISTOMAINTENANCE

, LINKFAILEDREASON
, LINKINVOLVEDPROVINCE
, LINKINVOLVEDCITY
, OPERATEYEAR
, OPERATEMONTH
, OPERATEDAY
FROM V_CIRCUITDISPATCH
where 
mainid=？

select * from  est_circuitdispatch