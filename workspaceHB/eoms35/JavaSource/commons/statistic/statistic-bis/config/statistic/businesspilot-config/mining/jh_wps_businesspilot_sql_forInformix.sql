----采集视图
create view v_businesspilot(
----est_stat---
                            est_data_status,
                            send_bigrole_id,
                            operate_bigrole_id,
                            send_dept_level,
                            operate_dept_level,
--common main
                            MAINID, SHEETID, TITLE, SHEETACCEPTLIMIT, SHEETCOMPLETELIMIT, SENDTIME, SENDUSERID,
                            SENDDEPTID,
                            SENDROLEID, SENDCONTACT, STATUS, HOLDSTATISFIED, ENDTIME, ENDRESULT, ENDUSERID, ENDDEPTID,
                            ENDROLEID,
                            DELETED, PIID, PARENTSHEETNAME, PARENTSHEETID, SHEETTEMPLATENAME, SHEETACCESSORIES,
                            TODEPTID, CANCELREASON,
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
    , MAINSTARTTIME, MAINENDTIME, MAINBUSDEPT, MAINPRODUCTTYPE, MAINPRODUCTNAME, MAINPRODUCTCODE, MAINISBUS,
                            MAINBUSSHEETID, MAINISPROJECT, MAINISSUCCESS, mainifrecord, SENDYEAR, SENDMONTH, SENDDAY
--bussines link                                                                
    , LINKOPERSTARTTIME, LINKOPERENDTIME, LINKTESTRESULT, LINKNETTYPE, LINKTGPOLICYACC, LINKDEVICEVERIFY, LINKISUPDATE,
                            LINKISSUCCESS, LINKSDENDTIME, LINKISFINISH, LINKISCHANGE, operateyear, operatemonth,
                            operateday
    ) as
    (
--已处理
        select
----est_stat---
2            est_data_status,
srole.roleid send_bigrole_id,
case when task.operatetype = 'subrole' then orole.roleid else null::integer end operate_bigrole_id,
            sd.linkid send_dept_level,
            od.linkid operate_dept_level,
--common main
            main.id MAINID, main.SHEETID, main.TITLE, main.SHEETACCEPTLIMIT, main.SHEETCOMPLETELIMIT, main.SENDTIME, main.SENDUSERID, main.SENDDEPTID,
            main.SENDROLEID, main.SENDCONTACT, main.STATUS, main.HOLDSTATISFIED, main.ENDTIME, main.ENDRESULT, main.ENDUSERID, main.ENDDEPTID, main.ENDROLEID,
            main.DELETED, main.PIID, main.PARENTSHEETNAME, main.PARENTSHEETID, main.SHEETTEMPLATENAME, main.SHEETACCESSORIES,
            main.TODEPTID, main.CANCELREASON,
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
                , main.MAINSTARTTIME
                , main.MAINENDTIME
                , main.MAINBUSDEPT
                , main.MAINPRODUCTTYPE
                , main.MAINPRODUCTNAME
                , main.MAINPRODUCTCODE
                , main.MAINISBUS
                , main.MAINBUSSHEETID
                , main.MAINISPROJECT
                , main.MAINISSUCCESS
                , main.mainifrecord
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
--bussines link                                   
                , link.LINKOPERSTARTTIME
                , link.LINKOPERENDTIME
                , link.LINKTESTRESULT
                , link.LINKNETTYPE
                , link.LINKTGPOLICYACC
                , link.LINKDEVICEVERIFY
                , link.LINKISUPDATE
                , link.LINKISSUCCESS
                , link.LINKSDENDTIME
                , link.LINKISFINISH
                , link.LINKISCHANGE
                , link.operateyear
                , link.operatemonth
                , link.operateday

        from businesspilot_main main
            join businesspilot_link link
        on main.id= link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
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
case when task.taskstatus = 2 then 0 when task.taskstatus = 8 then 1 end est_data_status,
srole.roleid                                                             send_bigrole_id,
case when task.operatetype = 'subrole' then orole.roleid else null::integer end operate_bigrole_id,
            sd.linkid send_dept_level,
            od.linkid operate_dept_level,
--common main
            main.id MAINID, main.SHEETID, main.TITLE, main.SHEETACCEPTLIMIT, main.SHEETCOMPLETELIMIT, main.SENDTIME, main.SENDUSERID, main.SENDDEPTID,
            main.SENDROLEID, main.SENDCONTACT, main.STATUS, main.HOLDSTATISFIED, main.ENDTIME, main.ENDRESULT, main.ENDUSERID, main.ENDDEPTID, main.ENDROLEID,
            main.DELETED, main.PIID, main.PARENTSHEETNAME, main.PARENTSHEETID, main.SHEETTEMPLATENAME, main.SHEETACCESSORIES,
            main.TODEPTID, main.CANCELREASON,
------user for find template---
--main.TEMPLATEFLAG
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
--common link
            null :: varchar (255) linkid,
            task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
            task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
            null :: varchar (100) OPERATETYPE,
            null ::datetime year to second OPERATETIME,
            case when task.taskowner!=task.operateroleid then task.taskowner else null :: varchar (255) end OPERATEUSERID,
            case when task.taskowner!=task.operateroleid then ud.deptid else u.deptid end OPERATEDEPTID,
            task.operateroleid OPERATEROLEID,
            null :: integer TOORGTYPE,
            null :: varchar (255) TOORGUSERID,
            null :: varchar (255) TOORGDEPTID,
            null :: varchar (255) TOORGROLEID,
            case when task.ACCEPTTIMELIMIT> current then 1 else 2 end ACCEPTFLAG,
            case when task.COMPLETETIMELIMIT> current then 1 else 2 end COMPLETEFLAG,
            task.PRELINKID,
            null :: varchar (255) PARENTLINKID,
            null :: varchar (255) FIRSTLINKID,
            task.id AIID,
            task.taskname ACTIVETEMPLATEID,
            null :: varchar (255) NODETEMPLATENAME,
            null :: varchar (255) NODEACCESSORIES,
            null :: varchar (30) OPERATEORGTYPE,
------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
            task.SUBTASKFLAG,
            task.PARENTTASKID,
            task.taskstatus,
            task.operatetype taskoperatetype
--bussines main
                , main.MAINSTARTTIME
                , main.MAINENDTIME
                , main.MAINBUSDEPT
                , main.MAINPRODUCTTYPE
                , main.MAINPRODUCTNAME
                , main.MAINPRODUCTCODE
                , main.MAINISBUS
                , main.MAINBUSSHEETID
                , main.MAINISPROJECT
                , main.MAINISSUCCESS
                , main.mainifrecord
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
--bussines link                               
                , null ::datetime year to second LINKOPERSTARTTIME
                , null ::datetime year to second LINKOPERENDTIME
                , null :: varchar (255) LINKTESTRESULT
                , null :: varchar (255) LINKNETTYPE
                , null :: varchar (255) LINKTGPOLICYACC
                , null :: varchar (255) LINKDEVICEVERIFY
                , null :: varchar (255) LINKISUPDATE
                , null :: varchar (255) LINKISSUCCESS
                , null ::datetime year to second LINKSDENDTIME
                , null :: varchar (255) LINKISFINISH
                , null :: varchar (255) LINKISCHANGE
                , null :: integer operateyear
                , null :: integer operatemonth
                , null :: integer operateday

        from businesspilot_main main
            join businesspilot_task task
        on main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
            left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
            left join taw_system_sub_role orole on orole.id=task.operateroleid
            left join taw_system_sub_role srole on srole.id= main.sendroleid
            left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
            left join taw_system_user u on u.userid = refr.userid
            join taw_system_dept sd on main.senddeptid=sd.deptid
            join taw_system_dept od on u.deptid=od.deptid
    )


--初始化工单采集时间
insert into est_last_oper
values (22, '2007-7-7 00:00:00', '新业务试点工单基础表采集的上一次操作的时间');


--中间表

CREATE TABLE est_businesspilot
(
    EST_DATA_STATUS    integer,
    send_bigrole_id    integer,
    operate_bigrole_id integer,
    send_dept_level    VARCHAR(30),
    operate_dept_level VARCHAR(30),
    MAINID             VARCHAR(255),
    SHEETID            VARCHAR(255),
    TITLE              VARCHAR(255),
    SHEETACCEPTLIMIT   datetime year to second,
    SHEETCOMPLETELIMIT datetime year to second,
    SENDTIME           datetime year to second,
    SENDUSERID         VARCHAR(255),
    SENDDEPTID         VARCHAR(255),
    SENDROLEID         VARCHAR(255),
    SENDCONTACT        VARCHAR(255),
    STATUS             integer,
    HOLDSTATISFIED     integer,
    ENDTIME            datetime year to second,
    ENDRESULT          VARCHAR(255),
    ENDUSERID          VARCHAR(255),
    ENDDEPTID          VARCHAR(255),
    ENDROLEID          VARCHAR(255),
    DELETED            integer,
    PIID               VARCHAR(255),
    PARENTSHEETNAME    VARCHAR(255),
    PARENTSHEETID      VARCHAR(255),
    SHEETTEMPLATENAME  VARCHAR(255),
    SHEETACCESSORIES   VARCHAR(255),
    TODEPTID           VARCHAR(255),
    CANCELREASON       VARCHAR(255),
    LINKID             VARCHAR(255),
    NODEACCEPTLIMIT    datetime year to second,
    NODECOMPLETELIMIT  datetime year to second,
    OPERATETYPE        varchar(100),
    OPERATETIME        datetime year to second,
    OPERATEUSERID      VARCHAR(255),
    OPERATEDEPTID      VARCHAR(255),
    OPERATEROLEID      VARCHAR(255),
    TOORGTYPE          integer,
    TOORGUSERID        VARCHAR(255),
    TOORGDEPTID        VARCHAR(255),
    TOORGROLEID        VARCHAR(255),
    ACCEPTFLAG         integer,
    COMPLETEFLAG       integer,
    PRELINKID          VARCHAR(255),
    PARENTLINKID       VARCHAR(255),
    FIRSTLINKID        VARCHAR(255),
    AIID               VARCHAR(255),
    ACTIVETEMPLATEID   VARCHAR(255),
    NODETEMPLATENAME   VARCHAR(255),
    NODEACCESSORIES    VARCHAR(255),
    OPERATEORGTYPE     VARCHAR(30),
    SUBTASKFLAG        VARCHAR(50),
    PARENTTASKID       VARCHAR(50),
    TASKSTATUS         VARCHAR(255),
    taskoperatetype    VARCHAR(255)
--bussines main             
    ,
    MAINSTARTTIME      datetime year to second,
    MAINENDTIME        datetime year to second,
    MAINBUSDEPT        VARCHAR(50),
    MAINPRODUCTTYPE    VARCHAR(255),
    MAINPRODUCTNAME    VARCHAR(255),
    MAINPRODUCTCODE    VARCHAR(255),
    MAINISBUS          VARCHAR(50),
    MAINBUSSHEETID     VARCHAR(25),
    MAINISPROJECT      VARCHAR(50),
    MAINISSUCCESS      VARCHAR(20),
    mainifrecord       VARCHAR(6),
    SENDYEAR           integer,
    SENDMONTH          integer,
    SENDDAY            integer
--bussines link                       
    ,
    LINKOPERSTARTTIME  datetime year to second,
    LINKOPERENDTIME    datetime year to second,
    LINKTESTRESULT     VARCHAR(255),
    LINKNETTYPE        VARCHAR(100),
    LINKTGPOLICYACC    VARCHAR(250),
    LINKDEVICEVERIFY   VARCHAR(255),
    LINKISUPDATE       VARCHAR(50),
    LINKISSUCCESS      VARCHAR(50),
    LINKSDENDTIME      datetime year to second,
    LINKISFINISH       VARCHAR(50),
    LINKISCHANGE       VARCHAR(50),
    OPERATEYEAR        integer,
    OPERATEMONTH       integer,
    OPERATEDAY         integer
)extent size 102400 next size 51200 lock mode row;


---建采集相关索引
create index index_businesspilottask_sheetkey on businesspilot_task
    (sheetkey) using btree  in indexdbs;
create index index_businesspilotlink_mainid on businesspilot_link
    (mainid) using btree  in indexdbs;
create index index_businesspilotlink_aiid on businesspilot_link
    (aiid) using btree  in indexdbs;


create index index_estbusinesspilot_mainid on est_businesspilot
    (mainid) using btree  in indexdbs;
create index index_estbusinesspilot_sendtime on est_businesspilot
    (sendtime) using btree  in indexdbs;
create index index_estbusinesspilot_endtime on est_businesspilot
    (endtime) using btree  in indexdbs;

