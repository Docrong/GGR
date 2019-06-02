select userid,deptid,password from taw_system_user

select * from urgentfault_main
      
select * from urgentfault_link

select * from urgentfault_task


--�Ѵ���
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


--δ����
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
to_char(null) OPERATEORGTYPE,
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
,to_char(null) LINKIFGREATFAULT                               
,to_char(null) LINKFAULTGENERANTPLACE                         
,to_date(null) LINKFAULTAVOIDTIME                               
,to_char(null) LINKAFFECTTIMELENGTH                            
                          
                            
,to_char(null) LINKGREATFAULTID                              
,to_char(null) LINKIFAFFIRM                                   
                                                    
,to_char(null) LINKFAULTREASON                              
,to_char(null) LINKFAULTSUBREASON                         
,to_number(null) OPERATEYEAR                               
,to_number(null) OPERATEMONTH                               
,to_number(null) OPERATEDAY            
                 
from urgentfault_main main

join urgentfault_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid

--��ʱ����
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
select * from v_urgentfault
select distinct * from v_urgentfault
drop view v_urgentfault
--drop view v_urgentfault
create or replace view v_urgentfault(
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
--�Ѵ���
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
--δ����
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
to_char(null) OPERATEORGTYPE,
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
,to_char(null) LINKIFGREATFAULT                               
,to_char(null) LINKFAULTGENERANTPLACE                         
,to_date(null) LINKFAULTAVOIDTIME                               
,to_char(null) LINKAFFECTTIMELENGTH                            
                         
                            
,to_char(null) LINKGREATFAULTID                              
,to_char(null) LINKIFAFFIRM                                   
                                                    
,to_char(null) LINKFAULTREASON                              
,to_char(null) LINKFAULTSUBREASON                         
,to_number(null) OPERATEYEAR                               
,to_number(null) OPERATEMONTH                               
,to_number(null) OPERATEDAY            
                 
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



-----��ʱ����

create table est_last_oper(
id number(5) not null primary key,--��Ӧ��������
last_operate_time TIMESTAMP(6),
comments varchar2(128)--��ע
)

delete from est_last_oper

--��ʼ�����񹤵��ɼ�ʱ��
insert into est_last_oper values (53,to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'����Ϲ������ɼ�����һ�β����ʱ��');

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

--�м��
CREATE TABLE est_urgentfault
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
, taskoperatetype varchar2(510)
--bussines main
,MAINSENDCONTENTTYPE     VARCHAR2(255)                         
,MAINIFGREATFAULT         VARCHAR2(255)                          
,MAINGREATFAULTID          VARCHAR2(255)                          
,MAINFAULTSHEETID           VARCHAR2(255)                           
,MAINFAULTGENERANTTIME       date                  
,MAINFAULTGENERANTPLACE      VARCHAR2(255)                         
,MAINEQUIPMENTFACTORY          VARCHAR2(255)                     
                
,MAINFAULTREASON                VARCHAR2(255)                      
,MAINTRAFFICLOSS               VARCHAR2(255)                       
,MAINIMPACTUSERNUM              VARCHAR2(255)                      
,MAINIFAFFECTOPERATION          VARCHAR2(255)                      
,MAINAFFECTAREA                 VARCHAR2(255)                      
          
,MAINAFFECTSTARTTIME           date                     
,MAINAFFECTOPERATIONLEVEL        VARCHAR2(255)                     
,MAINAFFECTOPERATIONSORT          VARCHAR2(255)                    
,MAINTRIGGERUSERCOMPLAINT         VARCHAR2(255)                   
,MAINIFREPORT                    VARCHAR2(255)                      
,MAINIFACHIEVEPROJECTSTARTUP      VARCHAR2(255)                     
,MAINURGENTLEVE                   VARCHAR2(255)                   
,MAINFAULTSPECIALITY                VARCHAR2(255)                
,MAINEQUIPINTEGRATOR              VARCHAR2(255)                   
,MAINFAULTAVOIDTIME                 date                  
,MAINAFFECTTIMELENGTH                 VARCHAR2(255)                                      
,MAINNETSORTONE                         VARCHAR2(255)              
,MAINNETSORTTWO                        VARCHAR2(255)              
,MAINNETSORTTHREE                       VARCHAR2(255)              
,MAINFAULTSUBREASON                    VARCHAR2(255)           
,MAINFAULTLOCATION                      VARCHAR2(255)             
,MAINREPORTLIMIT                       date             
,SENDYEAR                             number     
,SENDMONTH                            number   
,SENDDAY			      number
,MAINIFRECORD                NUMBER
--bussines link
,LINKIFGREATFAULT                     VARCHAR2(255)          
,LINKFAULTGENERANTPLACE                VARCHAR2(255)         
,LINKFAULTAVOIDTIME                    date         
,LINKAFFECTTIMELENGTH                 VARCHAR2(255)           
          
   
,LINKGREATFAULTID                     VARCHAR2(255)         
,LINKIFAFFIRM                        VARCHAR2(255)           
                                 
,LINKFAULTREASON                     VARCHAR2(35)         
,LINKFAULTSUBREASON                    VARCHAR2(35)     
,OPERATEYEAR                            number     
,OPERATEMONTH                           number      
,OPERATEDAY				number
)
select * from est_last_oper






