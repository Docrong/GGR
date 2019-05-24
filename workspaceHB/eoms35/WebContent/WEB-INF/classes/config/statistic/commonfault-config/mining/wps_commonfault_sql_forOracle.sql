select userid,deptid,password from taw_system_user

select * from commonfault_main

select * from commonfault_link

select * from commonfault_task

--bussines main 
MAINAPPLYSHEETID          VARCHAR2(510)  Y                         
MAINIFURGENTFAULT         VARCHAR2(510)  Y                         
MAINURGENTFAULTLOG        VARCHAR2(510)  Y                         
MAINIFSAFE                VARCHAR2(510)  Y                         
MAINIFMUTUALCOMMUNICATION VARCHAR2(510)  Y                         
MAINFAULTRESPONSELEVEL    VARCHAR2(510)  Y                         
MAINALARMNUM              VARCHAR2(510)  Y                         
MAINALARMSTATE            VARCHAR2(510)  Y                          
MAINALARMSOLVEDATE        DATE           Y                         
MAINNETSORTONE            VARCHAR2(510)  Y                         
MAINEQUIPMENTFACTORY      VARCHAR2(510)  Y                         
MAINEQUIPMENTNAME         VARCHAR2(510)  Y                         
MAINEQUIPMENTMODEL        VARCHAR2(510)  Y                         
MAINFAULTGENERANTTIME     DATE           Y                         
MAINFAULTGENERANTPRIV     VARCHAR2(510)  Y                         
MAINIFAFFECTOPERATION     VARCHAR2(510)  Y                         
                        
MAINAFFECTSTARTTIME       DATE           Y                         
MAINFAULTDISCOVERABLEMODE VARCHAR2(510)  Y                         
                      
                         
MAINSENDMODE              VARCHAR2(510)  Y                         
MAINALARMID               VARCHAR2(50)   Y                         
MAINALARMSOURCE           VARCHAR2(50)   Y                         
MAINALARMLOGICSORT        VARCHAR2(50)   Y                         
MAINALARMLOGICSORTSUB     VARCHAR2(50)   Y                         
MAINFAULTSPECIALTY        VARCHAR2(50)   Y                         
MAINEQUIPMENTTYPE         VARCHAR2(50)   Y                         
MAINNETNAME               VARCHAR2(50)   Y                         
MAINCOMPLETELIMITT1       DATE           Y                         
MAINCOMPLETELIMITT2       DATE           Y                         
MAINCOMPLETELIMITT3       DATE           Y                         
SENDORGTYPE               VARCHAR2(25)   Y                         
MAINFAULTGENERANTCITY     VARCHAR2(50)   Y                         
MAINNETSORTTWO            VARCHAR2(50)   Y                         
MAINNETSORTTHREE          VARCHAR2(50)   Y                         
MAINREJECTCOUNT           NUMBER         Y        0                
MAINDELAYFLAG             VARCHAR2(5)    Y                         
SENDYEAR                  NUMBER         Y        0                
SENDMONTH                 NUMBER         Y        0                
SENDDAY                   NUMBER         Y        0                
MAINIFRECORD              NUMBER         Y        0  

--bussines link
LINKFAULTRESPONSELEVEL    VARCHAR2(510)  Y                         
                        
LINKIFMUTUALCOMMUNICATION VARCHAR2(510)  Y                         
LINKIFSAFE                VARCHAR2(510)  Y                         
LINKIFEXCUTENETCHANGE     VARCHAR2(510)  Y                         
                        
LINKFAULTAVOIDTIME        TIMESTAMP(6)   Y                         
LINKOPERRENEWTIME         TIMESTAMP(6)   Y                         
LINKAFFECTTIMELENGTH      VARCHAR2(510)  Y                         
                        
                         
                         
LINKIFGREATFAULT          VARCHAR2(510)  Y                         
LINKIFURGENTFAULT         VARCHAR2(510)  Y                         
                          
LINKFAULTREASONSORT       VARCHAR2(50)   Y                         
LINKFAULTREASONSUBSECTION VARCHAR2(50)   Y                         
LINKIFFINALLYSOLVEPROJECT VARCHAR2(50)   Y                         
LINKIFADDCASEDATABASE     VARCHAR2(50)   Y                         
LINKIFDEFERRESOLVE        VARCHAR2(50)   Y                        
LINKFAULTDEALINFO         VARCHAR2(200)  Y                         
OPERATEYEAR               NUMBER         Y        0                
OPERATEMONTH              NUMBER         Y        0                
OPERATEDAY                NUMBER         Y        0

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
case when task.taskstatus=2 then 0 when task.taskstatus=8 then 1 end est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else to_number(null) end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
------user for find template---
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
,to_char(null) LINKFAULTRESPONSELEVEL                          
                              
,to_char(null) LINKIFMUTUALCOMMUNICATION                         
,to_char(null) LINKIFSAFE                                       
,to_char(null) LINKIFEXCUTENETCHANGE                            
                              
,to_date(null) LINKFAULTAVOIDTIME                              
,to_date(null) LINKOPERRENEWTIME                               
,to_char(null) LINKAFFECTTIMELENGTH                            
                                  
                                   
                              
,to_char(null) LINKIFGREATFAULT                                
,to_char(null) LINKIFURGENTFAULT                               
                             
,to_char(null) LINKFAULTREASONSORT                            
,to_char(null) LINKFAULTREASONSUBSECTION                        
,to_char(null) LINKIFFINALLYSOLVEPROJECT                        
,to_char(null) LINKIFADDCASEDATABASE                            
,to_char(null) LINKIFDEFERRESOLVE                             
,to_char(null) LINKFAULTDEALINFO                            
,to_number(null) OPERATEYEAR                          
,to_number(null) OPERATEMONTH                          
,to_number(null) OPERATEDAY          

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
select * from v_commomfault
select distinct * from v_commomfault
select 
drop view v_commomfault

create or replace view v_commomfault (
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
2 est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else to_number(null) end operate_bigrole_id,
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
case when task.taskstatus=2 then 0 when task.taskstatus=8 then 1 end est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype='subrole' then orole.roleid else to_number(null) end operate_bigrole_id,
sd.linkid send_dept_level,
od.linkid operate_dept_level,
--common main
main.id MAINID,main.SHEETID,main.TITLE,main.SHEETACCEPTLIMIT,main.SHEETCOMPLETELIMIT,main.SENDTIME,main.SENDUSERID,main.SENDDEPTID,
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
main.DELETED,main.PIID,main.PARENTSHEETNAME,main.PARENTSHEETID,main.SHEETTEMPLATENAME,main.SHEETACCESSORIES,
main.TODEPTID,main.CANCELREASON,
------user for find template---
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
,to_char(null) LINKFAULTRESPONSELEVEL                          
                              
,to_char(null) LINKIFMUTUALCOMMUNICATION                         
,to_char(null) LINKIFSAFE                                       
,to_char(null) LINKIFEXCUTENETCHANGE                            
                              
,to_date(null) LINKFAULTAVOIDTIME                              
,to_date(null) LINKOPERRENEWTIME                               
,to_char(null) LINKAFFECTTIMELENGTH                            
                                 
                                  
                               
,to_char(null) LINKIFGREATFAULT                                
,to_char(null) LINKIFURGENTFAULT                               
                              
,to_char(null) LINKFAULTREASONSORT                            
,to_char(null) LINKFAULTREASONSUBSECTION                        
,to_char(null) LINKIFFINALLYSOLVEPROJECT                        
,to_char(null) LINKIFADDCASEDATABASE                            
,to_char(null) LINKIFDEFERRESOLVE                             
,to_char(null) LINKFAULTDEALINFO                            
,to_number(null) OPERATEYEAR                          
,to_number(null) OPERATEMONTH                          
,to_number(null) OPERATEDAY          

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

select * from est_last_oper

create table est_last_oper(
id number(5) not null primary key,--对应工单类型
last_operate_time TIMESTAMP(6),
comments varchar2(128)--标注
)

delete from est_last_oper

--初始化任务工单采集时间
insert into est_last_oper values (51,to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'故障工单基础表采集的上一次操作的时间');

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
--中间表
CREATE TABLE est_commonfault
(
  EST_DATA_STATUS NUMBER(5)
, send_bigrole_id number(5)
, operate_bigrole_id number(5)
, send_dept_level VARCHAR2(30)
, operate_dept_level  VARCHAR2(30)
, MAINID VARCHAR2(510)
, SHEETID VARCHAR2(510)
, TITLE VARCHAR2(510)
, SHEETACCEPTLIMIT date
, SHEETCOMPLETELIMIT date
, SENDTIME date
, SENDUSERID VARCHAR2(510)
, SENDDEPTID VARCHAR2(510)
, SENDROLEID VARCHAR2(510)
, SENDCONTACT VARCHAR2(510)
, STATUS number(10)
, HOLDSTATISFIED number(10)
, ENDTIME date

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
, NODEACCEPTLIMIT date
, NODECOMPLETELIMIT date
, OPERATETYPE NUMBER(10)
, OPERATETIME date
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
, OPERATEORGTYPE VARCHAR2(25)
, SUBTASKFLAG VARCHAR2(10)
, PARENTTASKID VARCHAR2(50)
, TASKSTATUS VARCHAR2(510)
, taskoperatetype VARCHAR2(25)
, MAINAPPLYSHEETID          VARCHAR2(510)                         
, MAINIFURGENTFAULT         VARCHAR2(510)                          
, MAINURGENTFAULTLOG        VARCHAR2(510)                           
, MAINIFSAFE                VARCHAR2(510)                           
, MAINIFMUTUALCOMMUNICATION VARCHAR2(510)                           
, MAINFAULTRESPONSELEVEL    VARCHAR2(510)                           
, MAINALARMNUM              VARCHAR2(510)                          
, MAINALARMSTATE            VARCHAR2(510)                            
, MAINALARMSOLVEDATE        DATE                                    
, MAINNETSORTONE            VARCHAR2(510)                           
, MAINEQUIPMENTFACTORY      VARCHAR2(510)                           
, MAINEQUIPMENTNAME         VARCHAR2(510)                          
, MAINEQUIPMENTMODEL        VARCHAR2(510)                           
, MAINFAULTGENERANTTIME     DATE                                    
, MAINFAULTGENERANTPRIV     VARCHAR2(510)                           
, MAINIFAFFECTOPERATION     VARCHAR2(510)                          
                           
, MAINAFFECTSTARTTIME       DATE                                    
, MAINFAULTDISCOVERABLEMODE VARCHAR2(510)                           
                           
                           
, MAINSENDMODE              VARCHAR2(510)                           
, MAINALARMID               VARCHAR2(50)                           
, MAINALARMSOURCE           VARCHAR2(50)                           
, MAINALARMLOGICSORT        VARCHAR2(50)                            
, MAINALARMLOGICSORTSUB     VARCHAR2(50)                           
, MAINFAULTSPECIALTY        VARCHAR2(50)                           
, MAINEQUIPMENTTYPE         VARCHAR2(50)                           
, MAINNETNAME               VARCHAR2(50)                           
, MAINCOMPLETELIMITT1       DATE                                    
, MAINCOMPLETELIMITT2       DATE                                  
, MAINCOMPLETELIMITT3       DATE                                   
, SENDORGTYPE               VARCHAR2(25)                           
, MAINFAULTGENERANTCITY     VARCHAR2(50)                           
, MAINNETSORTTWO            VARCHAR2(50)                           
, MAINNETSORTTHREE          VARCHAR2(50)                           
, MAINREJECTCOUNT           NUMBER                   
, MAINDELAYFLAG             VARCHAR2(5)                             
, SENDYEAR                  NUMBER                    
, SENDMONTH                 NUMBER                    
, SENDDAY                   NUMBER                     
, MAINIFRECORD              NUMBER 
, LINKFAULTRESPONSELEVEL    VARCHAR2(510)                          
                           
, LINKIFMUTUALCOMMUNICATION VARCHAR2(510)                          
, LINKIFSAFE                VARCHAR2(510)                          
, LINKIFEXCUTENETCHANGE     VARCHAR2(510)                          
                          
, LINKFAULTAVOIDTIME        date                          
, LINKOPERRENEWTIME         date                           
, LINKAFFECTTIMELENGTH      VARCHAR2(510)                          
                         
                          
                          
, LINKIFGREATFAULT          VARCHAR2(510)                          
, LINKIFURGENTFAULT         VARCHAR2(510)                          
                            
, LINKFAULTREASONSORT       VARCHAR2(50)                            
, LINKFAULTREASONSUBSECTION VARCHAR2(50)                            
, LINKIFFINALLYSOLVEPROJECT VARCHAR2(50)                            
, LINKIFADDCASEDATABASE     VARCHAR2(50)                           
, LINKIFDEFERRESOLVE        VARCHAR2(50)                          
, LINKFAULTDEALINFO         VARCHAR2(200)                          
, OPERATEYEAR               NUMBER                              
, OPERATEMONTH              NUMBER                               
, OPERATEDAY                NUMBER           
)


