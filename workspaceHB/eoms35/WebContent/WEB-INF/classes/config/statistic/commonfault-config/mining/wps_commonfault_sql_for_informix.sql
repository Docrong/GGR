select userid,deptid,password from taw_system_user

select * from commonfault_main

select * from commonfault_link

select * from commonfault_task


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
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
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
,main.MAINAPPLYSHEETID                                  
,main.MAINIFURGENTFAULT                                 
,main.MAINURGENTFAULTLOG                               
,main.MAINIFSAFE                                        
,main.MAINIFMUTUALCOMMUNICATION                       
,main.MAINFAULTRESPONSELEVEL                           
,main.MAINALARMNUM                                  
,main.MAINALARMSTATE                                    
,main.MAINALARMSOLVEDATE                              
,main.MAINNETSORTONE                                   
,main.MAINEQUIPMENTFACTORY                            
,main.MAINEQUIPMENTNAME                                
,main.MAINEQUIPMENTMODEL                               
,main.MAINFAULTGENERANTTIME                           
,main.MAINFAULTGENERANTPRIV                            
,main.MAINIFAFFECTOPERATION                          
                               
,main.MAINAFFECTSTARTTIME                           
,main.MAINFAULTDISCOVERABLEMODE                      
                         
                                
,main.MAINSENDMODE                                  
,main.MAINALARMID                                     
,main.MAINALARMSOURCE                                
,main.MAINALARMLOGICSORT                            
,main.MAINALARMLOGICSORTSUB                           
,main.MAINFAULTSPECIALTY                           
,main.MAINEQUIPMENTTYPE                             
,main.MAINNETNAME                                    
,main.MAINCOMPLETELIMITT1                               
,main.MAINCOMPLETELIMITT2                             
,main.MAINCOMPLETELIMITT3                               
,main.SENDORGTYPE                                      
,main.MAINFAULTGENERANTCITY                            
,main.MAINNETSORTTWO                                    
,main.MAINNETSORTTHREE                                 
,main.MAINREJECTCOUNT                         
,main.MAINDELAYFLAG                             
,main.SENDYEAR                                  
,main.SENDMONTH                            
,main.SENDDAY                                  
,main.MAINIFRECORD                            
--bussines link
,link.LINKFAULTRESPONSELEVEL                         
                              
,link.LINKIFMUTUALCOMMUNICATION                        
,link.LINKIFSAFE                                       
,link.LINKIFEXCUTENETCHANGE                            
                             
,link.LINKFAULTAVOIDTIME                               
,link.LINKOPERRENEWTIME                              
,link.LINKAFFECTTIMELENGTH                            
                                 
                                     
                                
,link.LINKIFGREATFAULT                                
,link.LINKIFURGENTFAULT                               
                              
,link.LINKFAULTREASONSORT                             
,link.LINKFAULTREASONSUBSECTION                       
,link.LINKIFFINALLYSOLVEPROJECT                       
,link.LINKIFADDCASEDATABASE                           
,link.LINKIFDEFERRESOLVE                            
,link.LINKFAULTDEALINFO                          
,link.OPERATEYEAR                        
,link.OPERATEMONTH                       
,link.OPERATEDAY             

from commonfault_main main

join commonfault_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join commonfault_task task on link.aiid=task.id
left join taw_system_sub_role orole on link.operateroleid=orole.id
left join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid


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
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
------user for find template---
--common link
null::varchar(64) linkid,
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
null::varchar(64) PARENTLINKID,
null::varchar(64) FIRSTLINKID,
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
,main.MAINAPPLYSHEETID                                  
,main.MAINIFURGENTFAULT                                 
,main.MAINURGENTFAULTLOG                               
,main.MAINIFSAFE                                        
,main.MAINIFMUTUALCOMMUNICATION                       
,main.MAINFAULTRESPONSELEVEL                           
,main.MAINALARMNUM                                  
,main.MAINALARMSTATE                                    
,main.MAINALARMSOLVEDATE                              
,main.MAINNETSORTONE                                   
,main.MAINEQUIPMENTFACTORY                            
,main.MAINEQUIPMENTNAME                                
,main.MAINEQUIPMENTMODEL                               
,main.MAINFAULTGENERANTTIME                           
,main.MAINFAULTGENERANTPRIV                            
,main.MAINIFAFFECTOPERATION                          
                                  
,main.MAINAFFECTSTARTTIME                           
,main.MAINFAULTDISCOVERABLEMODE                      
                         
                                
,main.MAINSENDMODE                                  
,main.MAINALARMID                                     
,main.MAINALARMSOURCE                                
,main.MAINALARMLOGICSORT                            
,main.MAINALARMLOGICSORTSUB                           
,main.MAINFAULTSPECIALTY                           
,main.MAINEQUIPMENTTYPE                             
,main.MAINNETNAME                                    
,main.MAINCOMPLETELIMITT1                               
,main.MAINCOMPLETELIMITT2                             
,main.MAINCOMPLETELIMITT3                               
,main.SENDORGTYPE                                      
,main.MAINFAULTGENERANTCITY                            
,main.MAINNETSORTTWO                                    
,main.MAINNETSORTTHREE                                 
,main.MAINREJECTCOUNT                         
,main.MAINDELAYFLAG                             
,main.SENDYEAR                                  
,main.SENDMONTH                            
,main.SENDDAY                                  
,main.MAINIFRECORD
--bussines link
,null::varchar(255) LINKFAULTRESPONSELEVEL                          
                             
,null::varchar(255) LINKIFMUTUALCOMMUNICATION                         
,null::varchar(255) LINKIFSAFE                                       
,null::varchar(255) LINKIFEXCUTENETCHANGE                            
                              
,null::datetime year to second LINKFAULTAVOIDTIME                              
,null::datetime year to second LINKOPERRENEWTIME                               
,null::varchar(255) LINKAFFECTTIMELENGTH                            
                                 
                                   
                               
,null::varchar(255) LINKIFGREATFAULT                                
,null::varchar(255) LINKIFURGENTFAULT                               
                              
,null::varchar(255) LINKFAULTREASONSORT                            
,null::varchar(255) LINKFAULTREASONSUBSECTION                        
,null::varchar(255) LINKIFFINALLYSOLVEPROJECT                        
,null::varchar(255) LINKIFADDCASEDATABASE                            
,null::varchar(255) LINKIFDEFERRESOLVE                             
,null::varchar(255) LINKFAULTDEALINFO                            
,null::integer OPERATEYEAR                          
,null::integer OPERATEMONTH                          
,null::integer OPERATEDAY          

from commonfault_main main

join commonfault_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5
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

TOORGROLEID               varchar(255)  Y                         
ACCEPTFLAG                integer     Y                         
ACCEPTTIME                TIMESTAMP(6)

select 1 from dual

select null::varchar(255) from dual

select null from dual

select null::datetime year to second from dual

create table test1 as select null a from dual

drop table test1


------------------------------VIEW----------------------------------------
select * from v_commomfault
select distinct * from v_commomfault

drop view v_commomfault
create view v_commomfault(
----est_stat---
est_data_status,
send_bigrole_id,
operate_bigrole_id,
send_dept_level,
operate_dept_level,
--common main
MAINID,SHEETID,TITLE,SHEETACCEPTLIMIT,SHEETCOMPLETELIMIT,SENDTIME,SENDUSERID,SENDDEPTID,
SENDROLEID,SENDCONTACT,STATUS,HOLDSTATISFIED,ENDTIME,ENDUSERID,ENDDEPTID,ENDROLEID,
DELETED,PIID,PARENTSHEETNAME,PARENTSHEETID,SHEETTEMPLATENAME,SHEETACCESSORIES,
TODEPTID,CANCELREASON,
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
,MAINAPPLYSHEETID          
,MAINIFURGENTFAULT         
,MAINURGENTFAULTLOG        
,MAINIFSAFE                
,MAINIFMUTUALCOMMUNICATION 
,MAINFAULTRESPONSELEVEL    
,MAINALARMNUM              
,MAINALARMSTATE            
,MAINALARMSOLVEDATE        
,MAINNETSORTONE            
,MAINEQUIPMENTFACTORY      
,MAINEQUIPMENTNAME         
,MAINEQUIPMENTMODEL        
,MAINFAULTGENERANTTIME     
,MAINFAULTGENERANTPRIV     
,MAINIFAFFECTOPERATION     
           
,MAINAFFECTSTARTTIME       
,MAINFAULTDISCOVERABLEMODE 
    
             
,MAINSENDMODE              
,MAINALARMID               
,MAINALARMSOURCE           
,MAINALARMLOGICSORT       
,MAINALARMLOGICSORTSUB    
,MAINFAULTSPECIALTY       
,MAINEQUIPMENTTYPE        
,MAINNETNAME               
,MAINCOMPLETELIMITT1       
,MAINCOMPLETELIMITT2       
,MAINCOMPLETELIMITT3       
,SENDORGTYPE               
,MAINFAULTGENERANTCITY     
,MAINNETSORTTWO            
,MAINNETSORTTHREE          
,MAINREJECTCOUNT           
,MAINDELAYFLAG           
,SENDYEAR                  
,SENDMONTH                             
,SENDDAY                                 
,MAINIFRECORD              
--bussines link
,LINKFAULTRESPONSELEVEL    
       
,LINKIFMUTUALCOMMUNICATION 
,LINKIFSAFE                
,LINKIFEXCUTENETCHANGE     
      
,LINKFAULTAVOIDTIME        
,LINKOPERRENEWTIME         
,LINKAFFECTTIMELENGTH      
          
             
        
,LINKIFGREATFAULT          
,LINKIFURGENTFAULT         
       
,LINKFAULTREASONSORT      
,LINKFAULTREASONSUBSECTION 
,LINKIFFINALLYSOLVEPROJECT 
,LINKIFADDCASEDATABASE     
,LINKIFDEFERRESOLVE        
,LINKFAULTDEALINFO         
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
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
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
,main.MAINAPPLYSHEETID                                  
,main.MAINIFURGENTFAULT                                 
,main.MAINURGENTFAULTLOG                               
,main.MAINIFSAFE                                        
,main.MAINIFMUTUALCOMMUNICATION                       
,main.MAINFAULTRESPONSELEVEL                           
,main.MAINALARMNUM                                  
,main.MAINALARMSTATE                                    
,main.MAINALARMSOLVEDATE                              
,main.MAINNETSORTONE                                   
,main.MAINEQUIPMENTFACTORY                            
,main.MAINEQUIPMENTNAME                                
,main.MAINEQUIPMENTMODEL                               
,main.MAINFAULTGENERANTTIME                           
,main.MAINFAULTGENERANTPRIV                            
,main.MAINIFAFFECTOPERATION                          
                                 
,main.MAINAFFECTSTARTTIME                           
,main.MAINFAULTDISCOVERABLEMODE                      
                        
                                 
,main.MAINSENDMODE                                  
,main.MAINALARMID                                     
,main.MAINALARMSOURCE                                
,main.MAINALARMLOGICSORT                            
,main.MAINALARMLOGICSORTSUB                           
,main.MAINFAULTSPECIALTY                           
,main.MAINEQUIPMENTTYPE                             
,main.MAINNETNAME                                    
,main.MAINCOMPLETELIMITT1                               
,main.MAINCOMPLETELIMITT2                             
,main.MAINCOMPLETELIMITT3                               
,main.SENDORGTYPE                                      
,main.MAINFAULTGENERANTCITY                            
,main.MAINNETSORTTWO                                    
,main.MAINNETSORTTHREE                                 
,main.MAINREJECTCOUNT                         
,main.MAINDELAYFLAG                             
,main.SENDYEAR                                  
,main.SENDMONTH                            
,main.SENDDAY                                  
,main.MAINIFRECORD                            
--bussines link
,link.LINKFAULTRESPONSELEVEL                         
                               
,link.LINKIFMUTUALCOMMUNICATION                        
,link.LINKIFSAFE                                       
,link.LINKIFEXCUTENETCHANGE                            
                             
,link.LINKFAULTAVOIDTIME                               
,link.LINKOPERRENEWTIME                              
,link.LINKAFFECTTIMELENGTH                            
                                 
                                    
                                
,link.LINKIFGREATFAULT                                
,link.LINKIFURGENTFAULT                               
                               
,link.LINKFAULTREASONSORT                             
,link.LINKFAULTREASONSUBSECTION                       
,link.LINKIFFINALLYSOLVEPROJECT                       
,link.LINKIFADDCASEDATABASE                           
,link.LINKIFDEFERRESOLVE                            
,link.LINKFAULTDEALINFO                          
,link.OPERATEYEAR                        
,link.OPERATEMONTH                       
,link.OPERATEDAY             

from commonfault_main main

join commonfault_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join commonfault_task task on link.aiid=task.id
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
case when task.taskstatus=2 then 0::integer when task.taskstatus=8 then 1::integer end est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else null::integer end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
------user for find template---
--common link
null::varchar(64) linkid,
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
null::varchar(64) PARENTLINKID,
null::varchar(64) FIRSTLINKID,
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
,main.MAINAPPLYSHEETID                                  
,main.MAINIFURGENTFAULT                                 
,main.MAINURGENTFAULTLOG                               
,main.MAINIFSAFE                                        
,main.MAINIFMUTUALCOMMUNICATION                       
,main.MAINFAULTRESPONSELEVEL                           
,main.MAINALARMNUM                                  
,main.MAINALARMSTATE                                    
,main.MAINALARMSOLVEDATE                              
,main.MAINNETSORTONE                                   
,main.MAINEQUIPMENTFACTORY                            
,main.MAINEQUIPMENTNAME                                
,main.MAINEQUIPMENTMODEL                               
,main.MAINFAULTGENERANTTIME                           
,main.MAINFAULTGENERANTPRIV                            
,main.MAINIFAFFECTOPERATION                          
                                  
,main.MAINAFFECTSTARTTIME                           
,main.MAINFAULTDISCOVERABLEMODE                      
                        
                                 
,main.MAINSENDMODE                                  
,main.MAINALARMID                                     
,main.MAINALARMSOURCE                                
,main.MAINALARMLOGICSORT                            
,main.MAINALARMLOGICSORTSUB                           
,main.MAINFAULTSPECIALTY                           
,main.MAINEQUIPMENTTYPE                             
,main.MAINNETNAME                                    
,main.MAINCOMPLETELIMITT1                               
,main.MAINCOMPLETELIMITT2                             
,main.MAINCOMPLETELIMITT3                               
,main.SENDORGTYPE                                      
,main.MAINFAULTGENERANTCITY                            
,main.MAINNETSORTTWO                                    
,main.MAINNETSORTTHREE                                 
,main.MAINREJECTCOUNT                         
,main.MAINDELAYFLAG                             
,main.SENDYEAR                                  
,main.SENDMONTH                            
,main.SENDDAY                                  
,main.MAINIFRECORD
--bussines link
,null::varchar(255) LINKFAULTRESPONSELEVEL                          
                             
,null::varchar(255) LINKIFMUTUALCOMMUNICATION                         
,null::varchar(255) LINKIFSAFE                                       
,null::varchar(255) LINKIFEXCUTENETCHANGE                            
                             
,null::datetime year to second LINKFAULTAVOIDTIME                              
,null::datetime year to second LINKOPERRENEWTIME                               
,null::varchar(255) LINKAFFECTTIMELENGTH                            
                                  
                                  
                               
,null::varchar(255) LINKIFGREATFAULT                                
,null::varchar(255) LINKIFURGENTFAULT                               
                             
,null::varchar(255) LINKFAULTREASONSORT                            
,null::varchar(255) LINKFAULTREASONSUBSECTION                        
,null::varchar(255) LINKIFFINALLYSOLVEPROJECT                        
,null::varchar(255) LINKIFADDCASEDATABASE                            
,null::varchar(255) LINKIFDEFERRESOLVE                             
,null::varchar(255) LINKFAULTDEALINFO                            
,null::integer OPERATEYEAR                          
,null::integer OPERATEMONTH                          
,null::integer OPERATEDAY          

from commonfault_main main

join commonfault_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid
)



create table est_last_oper(
id integer not null primary key,--对应工单类型
last_operate_time datetime year to second,
comments varchar(128)--标注
)

--初始化任务工单采集时间
insert into est_last_oper values (51,'2007-07-07 00:00:00','故障工单基础表采集的上一次操作的时间');

drop table est_last_oper
delete from est_last_oper
select * from est_last_oper

SELECT last_operate_time 
FROM est_last_oper  
WHERE 
id=51

select link.operatetime from commonfault_link link

SELECT count(distinct mainid) FROM commonfault_link HAVING max(operatetime) >= to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS') and max(operatetime) < sysdate

select mainid,operatetime from commonfault_link

delete from est_commonfault
select * from est_commonfault
drop table est_commonfault
CREATE TABLE est_commonfault
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
, taskoperatetype varchar(25)
, MAINAPPLYSHEETID          varchar(255)                         
, MAINIFURGENTFAULT         varchar(255)                          
, MAINURGENTFAULTLOG        varchar(255)                           
, MAINIFSAFE                varchar(255)                           
, MAINIFMUTUALCOMMUNICATION varchar(255)                           
, MAINFAULTRESPONSELEVEL    varchar(255)                           
, MAINALARMNUM              varchar(255)                          
, MAINALARMSTATE            varchar(255)                            
, MAINALARMSOLVEDATE        datetime year to second                                    
, MAINNETSORTONE            varchar(255)                           
, MAINEQUIPMENTFACTORY      varchar(255)                           
, MAINEQUIPMENTNAME         varchar(255)                          
, MAINEQUIPMENTMODEL        varchar(255)                           
, MAINFAULTGENERANTTIME     datetime year to second                                  
, MAINFAULTGENERANTPRIV     varchar(255)                           
, MAINIFAFFECTOPERATION     varchar(255)                          
                           
, MAINAFFECTSTARTTIME       datetime year to second                                   
, MAINFAULTDISCOVERABLEMODE varchar(255)                           
                           
                           
, MAINSENDMODE              varchar(255)                           
, MAINALARMID               varchar(50)                           
, MAINALARMSOURCE           varchar(50)                           
, MAINALARMLOGICSORT        varchar(50)                            
, MAINALARMLOGICSORTSUB     varchar(50)                           
, MAINFAULTSPECIALTY        varchar(50)                           
, MAINEQUIPMENTTYPE         varchar(50)                           
, MAINNETNAME               varchar(50)                           
, MAINCOMPLETELIMITT1       datetime year to second                                    
, MAINCOMPLETELIMITT2       datetime year to second                                 
, MAINCOMPLETELIMITT3       datetime year to second                                   
, SENDORGTYPE               varchar(25)                           
, MAINFAULTGENERANTCITY     varchar(50)                           
, MAINNETSORTTWO            varchar(50)                           
, MAINNETSORTTHREE          varchar(50)                           
, MAINREJECTCOUNT           integer                   
, MAINDELAYFLAG             varchar(5)                             
, SENDYEAR                  integer                    
, SENDMONTH                 integer                    
, SENDDAY                   integer                     
, MAINIFRECORD              integer 
, LINKFAULTRESPONSELEVEL    varchar(255)                          
                          
, LINKIFMUTUALCOMMUNICATION varchar(255)                          
, LINKIFSAFE                varchar(255)                          
, LINKIFEXCUTENETCHANGE     varchar(255)                          
                          
, LINKFAULTAVOIDTIME        datetime year to second                          
, LINKOPERRENEWTIME         datetime year to second                           
, LINKAFFECTTIMELENGTH      varchar(255)                          
                          
                         
                          
, LINKIFGREATFAULT          varchar(255)                          
, LINKIFURGENTFAULT         varchar(255)                          
                            
, LINKFAULTREASONSORT       varchar(50)                            
, LINKFAULTREASONSUBSECTION varchar(50)                            
, LINKIFFINALLYSOLVEPROJECT varchar(50)                            
, LINKIFADDCASEDATABASE     varchar(50)                           
, LINKIFDEFERRESOLVE        varchar(50)                          
, LINKFAULTDEALINFO         varchar(200)                          
, OPERATEYEAR               integer                              
, OPERATEMONTH              integer                               
, OPERATEDAY                integer           
)


