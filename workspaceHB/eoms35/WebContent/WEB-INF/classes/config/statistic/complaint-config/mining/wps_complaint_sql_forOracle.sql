select * from complaint_link
select * from complaint_main
select * from complaint_task

--�Ѵ���
select ----est_stat---
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
link.OPERATETYPE,link.OPERATETIME,link.OPERATEUSERID,link.OPERATEDEPTID,link.OPERATEROLEID,link.TOORGTYPE,link.TOORGUSERID,
link.TOORGDEPTID,link.TOORGROLEID,link.ACCEPTFLAG,link.COMPLETEFLAG,link.PRELINKID,link.PARENTLINKID,link.FIRSTLINKID,
link.AIID,link.ACTIVETEMPLATEID,link.NODETEMPLATENAME,link.NODEACCESSORIES,link.OPERATEORGTYPE,

--common task
task.SUBTASKFLAG,task.PARENTTASKID,task.taskstatus,task.operatetype taskoperatetype,
--bussines main
main.DEALTIME1,	
main.DEALTIME2,	
main.URGENTDEGREE,	
main.BTYPE1,	
main.BDEPTCONTACT,	
main.CUSTOMERNAME,	
main.CUSTOMPHONE,	
main.COMPLAINTTIME,	
main.FAULTTIME,	
main.COMPLAINTADD,	
main.COMPLAINTDESC,	
main.BDEPTCONTACTPHONE,	
main.REPEATCOMPLAINTTIMES,	
main.COMPLAINTTYPE1,	
main.COMPLAINTTYPE2,	
main.COMPLAINTTYPE,	
main.COMPLAINTREASON1,	
main.COMPLAINTREASON2,	
main.COMPLAINTREASON,	
main.CUSTOMTYPE,	
main.CUSTOMBRAND,	
main.CUSTOMLEVEL,	
main.CUSTOMATTRIBUTION,	
main.STARTDEALCITY,	
main.CALLERNO,	
main.CALLERREGISTVLR,	
main.CALLERDIALUPTYPE,	
main.CALLERISINTELLIGENTUSER,	
main.CALLEDPARTYNO,	
main.CALLEDPARTYREGISTVLR,	
main.CALLEDPARTYCALLC,
main.MAINCOMPLETELIMITT1,                        
main.MAINCOMPLETELIMITT2 ,                     
main.MAINIFDEFERRESOLVE ,          
main.MAINIFRECORD                            
,main.MAINQCREJECTTIMES                      
,main.SENDYEAR                              
,main.SENDMONTH                            
,main.SENDDAY                         
--bussines link                  
,link.LINKIFINVOKECHANGE                    
,link.LINKFAULTSTARTTIME                    
,link.LINKFAULTENDTIME                      
,link.LINKFAULTGENERANTPLACE               
,link.NDEPTCONTACT                          
,link.NDEPTCONTACTPHONE                     
,link.DEALRESULT                            
,link.ISSUEELIMINATTIME                     
,link.LINKCHECKRESULT                       
,link.LINKIFDEFERRESOLVE                    
,link.LINKIFINVOKECASEDATABASE              
,link.LINKCHANGESHEETID
,link.OPERATEYEAR                             
,link.OPERATEMONTH                           
,link.OPERATEDAY                 

from complaint_main main

join complaint_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join complaint_task task on link.aiid=task.id
left join taw_system_sub_role orole on link.operateroleid=orole.id
left join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid


--select * from complaint_link link , complaint_task task where link.aiid=task.id

--select * from complaint_task

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
main.SENDROLEID,main.SENDCONTACT,main.STATUS,main.HOLDSTATISFIED,main.ENDTIME,main.ENDUSERID,main.ENDDEPTID,main.ENDROLEID,
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
task.operatetype taskoperatetype,

--bussines main
main.DEALTIME1,	
main.DEALTIME2,	
main.URGENTDEGREE,	
main.BTYPE1,	
main.BDEPTCONTACT,	
main.CUSTOMERNAME,	
main.CUSTOMPHONE,	
main.COMPLAINTTIME,	
main.FAULTTIME,	
main.COMPLAINTADD,	
main.COMPLAINTDESC,	
main.BDEPTCONTACTPHONE,	
main.REPEATCOMPLAINTTIMES,	
main.COMPLAINTTYPE1,	
main.COMPLAINTTYPE2,	
main.COMPLAINTTYPE,	
main.COMPLAINTREASON1,	
main.COMPLAINTREASON2,	
main.COMPLAINTREASON,	
main.CUSTOMTYPE,	
main.CUSTOMBRAND,	
main.CUSTOMLEVEL,	
main.CUSTOMATTRIBUTION,	
main.STARTDEALCITY,	
main.CALLERNO,	
main.CALLERREGISTVLR,	
main.CALLERDIALUPTYPE,	
main.CALLERISINTELLIGENTUSER,	
main.CALLEDPARTYNO,	
main.CALLEDPARTYREGISTVLR,	
main.CALLEDPARTYCALLC,
main.MAINCOMPLETELIMITT1,                        
main.MAINCOMPLETELIMITT2 ,                     
main.MAINIFDEFERRESOLVE , 
main.MAINIFRECORD                            
,main.MAINQCREJECTTIMES                      
,main.SENDYEAR                              
,main.SENDMONTH                            
,main.SENDDAY         
--bussines link              
,to_char(null) LINKIFINVOKECHANGE                    
,to_date(null) LINKFAULTSTARTTIME                    
,to_date(null) LINKFAULTENDTIME                      
,to_char(null) LINKFAULTGENERANTPLACE  
,to_char(null) NDEPTCONTACT                          
,to_char(null) NDEPTCONTACTPHONE                     
,to_char(null) DEALRESULT                            
,to_date(null) ISSUEELIMINATTIME                    
,to_char(null) LINKCHECKRESULT                       
,to_char(null) LINKIFDEFERRESOLVE                    
,to_char(null) LINKIFINVOKECASEDATABASE            
,to_char(null) LINKCHANGESHEETID 
,to_number(null) OPERATEYEAR                             
,to_number(null) OPERATEMONTH                           
,to_number(null) OPERATEDAY        
from complaint_main main

join complaint_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid   


select * from v_complaint

create or replace view v_complaint as
(
--?????
select ----est_stat---
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
link.OPERATETYPE,link.OPERATETIME,link.OPERATEUSERID,link.OPERATEDEPTID,link.OPERATEROLEID,link.TOORGTYPE,link.TOORGUSERID,
link.TOORGDEPTID,link.TOORGROLEID,link.ACCEPTFLAG,link.COMPLETEFLAG,link.PRELINKID,link.PARENTLINKID,link.FIRSTLINKID,
link.AIID,link.ACTIVETEMPLATEID,link.NODETEMPLATENAME,link.NODEACCESSORIES,link.OPERATEORGTYPE,

--common task
task.SUBTASKFLAG,task.PARENTTASKID,task.taskstatus,task.operatetype taskoperatetype,
--bussines main
main.DEALTIME1,	
main.DEALTIME2,	
main.URGENTDEGREE,	
main.BTYPE1,	
main.BDEPTCONTACT,	
main.CUSTOMERNAME,	
main.CUSTOMPHONE,	
main.COMPLAINTTIME,	
main.FAULTTIME,	
main.COMPLAINTADD,	
main.COMPLAINTDESC,	
main.BDEPTCONTACTPHONE,	
main.REPEATCOMPLAINTTIMES,	
main.COMPLAINTTYPE1,	
main.COMPLAINTTYPE2,	
main.COMPLAINTTYPE,
main.COMPLAINTTYPE4,	
main.COMPLAINTTYPE5,	
main.COMPLAINTTYPE6,	
main.COMPLAINTTYPE7,		
main.COMPLAINTREASON1,	
main.COMPLAINTREASON2,	
main.COMPLAINTREASON,	
main.CUSTOMTYPE,	
main.CUSTOMBRAND,	
main.CUSTOMLEVEL,	
main.CUSTOMATTRIBUTION,	
main.STARTDEALCITY,	
main.CALLERNO,	
main.CALLERREGISTVLR,	
main.CALLERDIALUPTYPE,	
main.CALLERISINTELLIGENTUSER,	
main.CALLEDPARTYNO,	
main.CALLEDPARTYREGISTVLR,	
main.CALLEDPARTYCALLC,
main.MAINCOMPLETELIMITT1,                        
main.MAINCOMPLETELIMITT2 ,                     
main.MAINIFDEFERRESOLVE ,          
main.MAINIFRECORD                            
,main.MAINQCREJECTTIMES                      
,main.SENDYEAR                              
,main.SENDMONTH                            
,main.SENDDAY                         
--bussines link              
,link.LINKIFINVOKECHANGE                    
,link.LINKFAULTSTARTTIME                    
,link.LINKFAULTENDTIME                      
,link.LINKFAULTGENERANTPLACE               
,link.NDEPTCONTACT                          
,link.NDEPTCONTACTPHONE                     
,link.DEALRESULT                           
,link.ISSUEELIMINATTIME                     
,link.LINKCHECKRESULT                       
,link.LINKIFDEFERRESOLVE                    
,link.LINKIFINVOKECASEDATABASE              
,link.LINKCHANGESHEETID
,link.OPERATEYEAR                             
,link.OPERATEMONTH                           
,link.OPERATEDAY                 
,main.maindelayflag
,main.mainlastrepeattime 
from complaint_main main

join complaint_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join complaint_task task on link.aiid=task.id
left join taw_system_sub_role orole on link.operateroleid=orole.id
left join taw_system_sub_role srole on main.sendroleid=srole.id
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on link.operatedeptid=od.deptid
)
union all
(
--δ????
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
task.operatetype taskoperatetype,

--bussines main
main.DEALTIME1,	
main.DEALTIME2,	
main.URGENTDEGREE,	
main.BTYPE1,	
main.BDEPTCONTACT,	
main.CUSTOMERNAME,	
main.CUSTOMPHONE,	
main.COMPLAINTTIME,	
main.FAULTTIME,	
main.COMPLAINTADD,	
main.COMPLAINTDESC,	
main.BDEPTCONTACTPHONE,	
main.REPEATCOMPLAINTTIMES,	
main.COMPLAINTTYPE1,	
main.COMPLAINTTYPE2,	
main.COMPLAINTTYPE,	
main.COMPLAINTTYPE4,	
main.COMPLAINTTYPE5,	
main.COMPLAINTTYPE6,	
main.COMPLAINTTYPE7,	
main.COMPLAINTREASON1,	
main.COMPLAINTREASON2,	
main.COMPLAINTREASON,	
main.CUSTOMTYPE,	
main.CUSTOMBRAND,	
main.CUSTOMLEVEL,	
main.CUSTOMATTRIBUTION,	
main.STARTDEALCITY,	
main.CALLERNO,	
main.CALLERREGISTVLR,	
main.CALLERDIALUPTYPE,	
main.CALLERISINTELLIGENTUSER,	
main.CALLEDPARTYNO,	
main.CALLEDPARTYREGISTVLR,	
main.CALLEDPARTYCALLC,
main.MAINCOMPLETELIMITT1,                        
main.MAINCOMPLETELIMITT2 ,                     
main.MAINIFDEFERRESOLVE , 
main.MAINIFRECORD                            
,main.MAINQCREJECTTIMES                      
,main.SENDYEAR                              
,main.SENDMONTH                            
,main.SENDDAY         
--bussines link   
,to_char(null) LINKIFINVOKECHANGE                    
,to_date(null) LINKFAULTSTARTTIME                    
,to_date(null) LINKFAULTENDTIME                      
,to_char(null) LINKFAULTGENERANTPLACE               
,to_char(null) NDEPTCONTACT                          
,to_char(null) NDEPTCONTACTPHONE                     
,to_char(null) DEALRESULT                            
,to_date(null) ISSUEELIMINATTIME                     
,to_char(null) LINKCHECKRESULT                       
,to_char(null) LINKIFDEFERRESOLVE                    
,to_char(null) LINKIFINVOKECASEDATABASE            
,to_char(null) LINKCHANGESHEETID 
,to_number(null) OPERATEYEAR                             
,to_number(null) OPERATEMONTH                           
,to_number(null) OPERATEDAY   
,main.maindelayflag 
,main.mainlastrepeattime     
from complaint_main main

join complaint_task task on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
left join taw_system_sub_role orole on orole.id=task.operateroleid
left join taw_system_sub_role srole on srole.id=main.sendroleid
left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
left join taw_system_user u on u.userid = refr.userid
join taw_system_dept sd on main.senddeptid=sd.deptid
join taw_system_dept od on u.deptid=od.deptid   
)



select * from est_last_oper where id=52 for update 

insert into est_last_oper
values (52,to_date('2007-11-11 11:11:11','yyyy-mm-dd hh24:mi:ss'),'Ͷ�ߴ����������ɼ�����һ�β����ʱ��')


drop table est_complaint
--�м��
CREATE TABLE est_complaint
(
  mainlastrepeattime date
, EST_DATA_STATUS NUMBER(5)
, maindelayflag VARCHAR2(5)
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
, OPERATEORGTYPE VARCHAR2(25)
, SUBTASKFLAG VARCHAR2(10)
, PARENTTASKID VARCHAR2(50)
, TASKSTATUS VARCHAR2(510)
, TASKOPERATETYPE VARCHAR2(25)
,DEALTIME1               date
,DEALTIME2               date
,URGENTDEGREE            VARCHAR2(50)
,BTYPE1                  VARCHAR2(50)
,BDEPTCONTACT            VARCHAR2(50)
,CUSTOMERNAME            VARCHAR2(50)
,CUSTOMPHONE             VARCHAR2(50)
,COMPLAINTTIME           date
,FAULTTIME               date
,COMPLAINTADD            VARCHAR2(255)
,COMPLAINTDESC           VARCHAR2(2000)
,BDEPTCONTACTPHONE       VARCHAR2(50)
,REPEATCOMPLAINTTIMES    VARCHAR2(50)
,COMPLAINTTYPE1          VARCHAR2(50)
,COMPLAINTTYPE2          VARCHAR2(50)
,COMPLAINTTYPE           VARCHAR2(50)
,COMPLAINTTYPE4           VARCHAR2(50)
,COMPLAINTTYPE5           VARCHAR2(50)
,COMPLAINTTYPE6           VARCHAR2(50)
,COMPLAINTTYPE7           VARCHAR2(50)
,COMPLAINTREASON1        VARCHAR2(50)
,COMPLAINTREASON2        VARCHAR2(50)
,COMPLAINTREASON         VARCHAR2(50)
,CUSTOMTYPE              VARCHAR2(50)
,CUSTOMBRAND             VARCHAR2(50)
,CUSTOMLEVEL             VARCHAR2(50)
,CUSTOMATTRIBUTION       VARCHAR2(255)
,STARTDEALCITY           VARCHAR2(255)
,CALLERNO                VARCHAR2(255)
,CALLERREGISTVLR         VARCHAR2(255)
,CALLERDIALUPTYPE        VARCHAR2(255)
,CALLERISINTELLIGENTUSER VARCHAR2(255)
,CALLEDPARTYNO           VARCHAR2(255)
,CALLEDPARTYREGISTVLR    VARCHAR2(255)
,CALLEDPARTYCALLC        VARCHAR2(255)
,MAINCOMPLETELIMITT1     date                       
,MAINCOMPLETELIMITT2     date                         
,MAINIFDEFERRESOLVE      VARCHAR2(50)
,MAINIFRECORD            NUMBER(10)                 
,MAINQCREJECTTIMES       NUMBER(10)                  
,SENDYEAR                NUMBER               
,SENDMONTH               NUMBER                  
,SENDDAY                 NUMBER  
,LINKIFINVOKECHANGE       VARCHAR2(50)                      
,LINKFAULTSTARTTIME       date                      
,LINKFAULTENDTIME         date                     
,LINKFAULTGENERANTPLACE   VARCHAR2(100)                     
,NDEPTCONTACT             VARCHAR2(50)                      
,NDEPTCONTACTPHONE        VARCHAR2(50)                      
,DEALRESULT               VARCHAR2(50)                      
,ISSUEELIMINATTIME        date                      
,LINKCHECKRESULT          VARCHAR2(50)                      
,LINKIFDEFERRESOLVE       VARCHAR2(50)                      
,LINKIFINVOKECASEDATABASE VARCHAR2(50)                      
,LINKCHANGESHEETID        VARCHAR2(50)   
,OPERATEYEAR              NUMBER                       
,OPERATEMONTH             NUMBER                 
,OPERATEDAY               NUMBER        
)

select * from est_complaint