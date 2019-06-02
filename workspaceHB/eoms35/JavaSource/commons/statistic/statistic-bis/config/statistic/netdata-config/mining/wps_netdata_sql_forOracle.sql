select userid,deptid,password from taw_system_user

select * from netdata_main

select * from netdata_link

select * from netdata_task


--main

MAINISSECURITY     VARCHAR2(50)   Y                         
MAINISCONNECT      VARCHAR2(50)   Y                         
MAINFACTORY        VARCHAR2(50)   Y                         
MAINCELLINFO       VARCHAR2(50)   Y                         
MAINCHANGESOURCE   VARCHAR2(50)   Y                         
MAINPARENTSHEETID  VARCHAR2(50)   Y                         
MAINISNEEDDESIGN   VARCHAR2(50)   Y                         
MAINDESIGNID       VARCHAR2(50)   Y                         
MAINNETTYPETHREE   VARCHAR2(50)   Y                         
MAINNETTYPETWO     VARCHAR2(50)   Y                         
MAINNETTYPEONE     VARCHAR2(50)   Y                         
MAINREJECTTIMES    INTEGER        Y                         
SENDYEAR           INTEGER        Y                         
SENDMONTH          INTEGER        Y                         
SENDDAY            INTEGER        Y                         
MAINIFRECORD       INTEGER        Y        0                
MAINEXECUTEENDDATE DATE           Y                         
 
--link            
LINKCOMPLETELIMITTIME DATE           Y                        
LINKINVOLVEDPROVINCE  VARCHAR2(50)   Y                         
LINKINVOLVEDCITY      VARCHAR2(50)   Y                         
LINKDESIGNKEY         VARCHAR2(50)   Y                         
LINKISCHECK           VARCHAR2(50)   Y                         
LINKPERMITRESULT      VARCHAR2(50)   Y                         
LINKMANAGER           VARCHAR2(50)   Y                         
LINKCONTACT           VARCHAR2(50)   Y                         
LINKPLANSTARTTIME     DATE           Y                         
LINKPLANENDTIME       DATE           Y                         
LINKISEFFECTBUSINESS  VARCHAR2(50)   Y                         
LINKBUSINESSDEPT      VARCHAR2(50)   Y                         
LINKISSENDTOFRONT     VARCHAR2(50)   Y                         
LINKCUTRESULT         VARCHAR2(50)   Y                         
LINKISPLAN            VARCHAR2(50)   Y                         
                       
LINKISUPDATEWORK      VARCHAR2(50)   Y                         
LINKISNEEDPROJECT     VARCHAR2(50)   Y                         
OPERATERCONTACT       VARCHAR2(255)  Y                         
OPERATEYEAR           INTEGER        Y                         
OPERATEMONTH          INTEGER        Y                         
OPERATEDAY            INTEGER        Y                         
LINKFAILEDREASON      VARCHAR2(50) Y        





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
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
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
,link.OPERATEYEAR                                 
,link.OPERATEMONTH                             
,link.OPERATEDAY                                   
,link.LINKFAILEDREASON      


from netdata_main main

join netdata_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join netdata_task task on link.aiid=task.id
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
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
,main.SENDYEAR                             
,main.SENDMONTH                                
,main.SENDDAY                                
,main.MAINIFRECORD                    
,main.MAINEXECUTEENDDATE   
--bussines link
,to_date(null) LINKCOMPLETELIMITTIME                       
,to_char(null) LINKINVOLVEDPROVINCE                          
,to_char(null) LINKINVOLVEDCITY                          
,to_char(null) LINKDESIGNKEY                            
,to_char(null) LINKISCHECK                                
,to_char(null) LINKPERMITRESULT                            
,to_char(null) LINKMANAGER                                
,to_char(null) LINKCONTACT                                
,to_date(null) LINKPLANSTARTTIME                            
,to_date(null) LINKPLANENDTIME                           
,to_char(null) LINKISEFFECTBUSINESS                         
,to_char(null) LINKBUSINESSDEPT                              
,to_char(null) LINKISSENDTOFRONT                            
,to_char(null) LINKCUTRESULT                                
,to_char(null) LINKISPLAN                                 
                              
,to_char(null) LINKISUPDATEWORK                            
,to_char(null) LINKISNEEDPROJECT                            
,to_char(null) OPERATERCONTACT                              
,to_number(null) OPERATEYEAR                                
,to_number(null) OPERATEMONTH                                
,to_number(null) OPERATEDAY                                 
,to_char(null) LINKFAILEDREASON       
  
from netdata_main main

join netdata_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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
select * from v_netdata
select distinct * from v_netdata

--drop view v_commomfault
create or replace view v_netdata(
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
--common task
SUBTASKFLAG,
PARENTTASKID,
taskstatus,
taskoperatetype
--bussines main
,MAINISSECURITY                       
,MAINISCONNECT                          
,MAINFACTORY                               
,MAINCELLINFO                              
,MAINCHANGESOURCE                          
,MAINPARENTSHEETID                         
,MAINISNEEDDESIGN                          
,MAINDESIGNID                               
,MAINNETTYPETHREE                         
,MAINNETTYPETWO                             
,MAINNETTYPEONE                           
,MAINREJECTTIMES                       
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
,OPERATEYEAR                                 
,OPERATEMONTH                             
,OPERATEDAY                                   
,LINKFAILEDREASON
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
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
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
,link.OPERATEYEAR                                 
,link.OPERATEMONTH                             
,link.OPERATEDAY                                   
,link.LINKFAILEDREASON      


from netdata_main main

join netdata_link link on main.id=link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
left join netdata_task task on link.aiid=task.id
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
to_number(null) OPERATEORGTYPE,
--common task
task.SUBTASKFLAG,
task.PARENTTASKID,
task.taskstatus,
task.operatetype taskoperatetype
--bussines main
,main.MAINISSECURITY                       
,main.MAINISCONNECT                          
,main.MAINFACTORY                               
,main.MAINCELLINFO                              
,main.MAINCHANGESOURCE                          
,main.MAINPARENTSHEETID                         
,main.MAINISNEEDDESIGN                          
,main.MAINDESIGNID                               
,main.MAINNETTYPETHREE                         
,main.MAINNETTYPETWO                             
,main.MAINNETTYPEONE                           
,main.MAINREJECTTIMES                       
,main.SENDYEAR                             
,main.SENDMONTH                                
,main.SENDDAY                                
,main.MAINIFRECORD                    
,main.MAINEXECUTEENDDATE   
--bussines link
,to_date(null) LINKCOMPLETELIMITTIME                       
,to_char(null) LINKINVOLVEDPROVINCE                          
,to_char(null) LINKINVOLVEDCITY                          
,to_char(null) LINKDESIGNKEY                            
,to_char(null) LINKISCHECK                                
,to_char(null) LINKPERMITRESULT                            
,to_char(null) LINKMANAGER                                
,to_char(null) LINKCONTACT                                
,to_date(null) LINKPLANSTARTTIME                            
,to_date(null) LINKPLANENDTIME                           
,to_char(null) LINKISEFFECTBUSINESS                         
,to_char(null) LINKBUSINESSDEPT                              
,to_char(null) LINKISSENDTOFRONT                            
,to_char(null) LINKCUTRESULT                                
,to_char(null) LINKISPLAN                                 
                              
,to_char(null) LINKISUPDATEWORK                            
,to_char(null) LINKISNEEDPROJECT                            
,to_char(null) OPERATERCONTACT                              
,to_number(null) OPERATEYEAR                                
,to_number(null) OPERATEMONTH                                
,to_number(null) OPERATEDAY                                 
,to_char(null) LINKFAILEDREASON       
  
from netdata_main main

join netdata_task task on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
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
insert into est_last_oper values (44,to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS'),'
网络数据管理工单基础表采集的上一次操作的时间');

--drop table est_last_oper
--delete from est_last_oper
select * from est_last_oper

SELECT last_operate_time 
FROM est_last_oper  
WHERE 
id=51

select link.operatetime from commonfault_link link

SELECT count(distinct mainid) FROM commonfault_link HAVING max(operatetime) >= to_date('2007-07-07 00:00:00','YYYY-MM-DD HH24:MI:SS') and max(operatetime) < sysdate

select mainid,operatetime from commonfault_link

--delete from est_commonfault
select * from est_netdata
--drop table est_circuitdispatch
--中间表


CREATE TABLE est_netdata
(
  EST_DATA_STATUS NUMBER(5)
, send_bigrole_id number(5)
, operate_bigrole_id number(5)
, send_dept_level VARCHAR2(30)
, operate_dept_level  VARCHAR2(30)
, MAINID VARCHAR2(510)
, SHEETID VARCHAR2(510)
, TITLE VARCHAR2(510)
, SHEETACCEPTLIMIT DATE
, SHEETCOMPLETELIMIT DATE
, SENDTIME DATE
, SENDUSERID VARCHAR2(510)
, SENDDEPTID VARCHAR2(510)
, SENDROLEID VARCHAR2(510)
, SENDCONTACT VARCHAR2(510)
, STATUS number(10)
, HOLDSTATISFIED number(10)
, ENDTIME DATE
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
, NODEACCEPTLIMIT DATE
, NODECOMPLETELIMIT DATE
, OPERATETYPE NUMBER(10)
, OPERATETIME DATE
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
, OPERATEORGTYPE NUMBER(25)
, SUBTASKFLAG VARCHAR2(10)
, PARENTTASKID VARCHAR2(50)
, TASKSTATUS VARCHAR2(510)
, TASKOPERATETYPE VARCHAR2(510)
,MAINISSECURITY     VARCHAR2(50),                           
MAINISCONNECT      VARCHAR2(50),                           
MAINFACTORY        VARCHAR2(50),                            
MAINCELLINFO       VARCHAR2(50),                            
MAINCHANGESOURCE   VARCHAR2(50),                            
MAINPARENTSHEETID  VARCHAR2(50),                            
MAINISNEEDDESIGN   VARCHAR2(50),                            
MAINDESIGNID       VARCHAR2(50),                            
MAINNETTYPETHREE   VARCHAR2(50),                            
MAINNETTYPETWO     VARCHAR2(50),                            
MAINNETTYPEONE     VARCHAR2(50),                           
MAINREJECTTIMES    INTEGER,                                 
SENDYEAR           INTEGER,                                 
SENDMONTH          INTEGER,                                 
SENDDAY            INTEGER,                                 
MAINIFRECORD       INTEGER,                             
MAINEXECUTEENDDATE DATE,       
LINKCOMPLETELIMITTIME DATE,                                   
LINKINVOLVEDPROVINCE  VARCHAR2(50),                         
LINKINVOLVEDCITY      VARCHAR2(50),                           
LINKDESIGNKEY         VARCHAR2(50),                            
LINKISCHECK           VARCHAR2(50),                            
LINKPERMITRESULT      VARCHAR2(50),                            
LINKMANAGER           VARCHAR2(50),                           
LINKCONTACT           VARCHAR2(50),                            
LINKPLANSTARTTIME     DATE ,                                   
LINKPLANENDTIME       DATE ,                                  
LINKISEFFECTBUSINESS  VARCHAR2(50),                           
LINKBUSINESSDEPT      VARCHAR2(50),                            
LINKISSENDTOFRONT     VARCHAR2(50),                            
LINKCUTRESULT         VARCHAR2(50),                            
LINKISPLAN            VARCHAR2(50),                            
                          
LINKISUPDATEWORK      VARCHAR2(50),                            
LINKISNEEDPROJECT     VARCHAR2(50),                            
OPERATERCONTACT       VARCHAR2(255),                           
OPERATEYEAR           INTEGER   ,                              
OPERATEMONTH          INTEGER    ,                             
OPERATEDAY            INTEGER    ,                            
LINKFAILEDREASON      VARCHAR2(50)                       
)




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
, MAINAPPLYGIST
, MAINDESCRIPTION
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
, LINKRISKEVALUATE
, LINKOPERATIONCONSTRUE
, LINKPROGRAMMENO
, LINKPERMITRESULT
, LINKPERMITIDEA
, LINKEXCUTEPRINCIPAL
, LINKCONTACT
, LINKPLANSTARTDATE
, LINKPLANENDDATE
, LINKNETAFFECTAREA
, LINKIFAFFECTOPERATION
, LINKAFFECTSITUATION
, LINKAFFECTNETMANAGE
, LINKREFEROPERATEDEPT
, LINKIFNOTIFY
, LINKEXCUTERESULT
, LINKISACCORDANCEPROGRAMME
, LINKEXCUTEEXPLAIN
, LINKAFFECTOPERATIONEXPLAIN

, LINKALARMRECORD
, LINKREPORTABNORMITYEXPLAIN
, LINKEXCUTECONSTRUE
, LINKIFUPDATEPLAN
, LINKPLANUPDATEIDEA
, LINKCIRCUITUPDATE
, LINKWORKPLAN
, LINKISTOMAINTENANCE
, LINKMAINTENANCEDES
, LINKFAILEDREASON
, LINKINVOLVEDPROVINCE
, LINKINVOLVEDCITY
, OPERATEYEAR
, OPERATEMONTH
, OPERATEDAY
FROM V_netdata
where 
mainid=？

select * from  est_netdata



