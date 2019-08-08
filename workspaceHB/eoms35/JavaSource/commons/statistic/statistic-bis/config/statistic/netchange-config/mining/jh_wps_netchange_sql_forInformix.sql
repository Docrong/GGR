----采集视图
create view v_netchange(
----est_stat---
                        est_data_status,
                        send_bigrole_id,
                        operate_bigrole_id,
                        send_dept_level,
                        operate_dept_level,
--common main
                        MAINID, SHEETID, TITLE, SHEETACCEPTLIMIT, SHEETCOMPLETELIMIT, SENDTIME, SENDUSERID, SENDDEPTID,
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
                        taskoperatetype, MAINSPECIALTYTYPE, MAINIFSAFE, MAINIFMUTUALCOMMUNICATION, MAINNETSORTONE,
                        MAINNETSORTTWO, MAINNETSORTTHREE, MAINEQUIPMENTFACTORY, MAINCHANGESOURCE,
                        MAINEXPECTATIONCOMPLETETIME, MAINIFREQUIREPROJECT, MAINTOUCHSHEETID, MAINREJECTTIMES, SENDYEAR,
                        SENDMONTH, SENDDAY, MAINIFRECORD, MAINEXECUTEENDDATE
--bussines link
    , LINKENDDATE, LINKPROJECTKEY, LINKAUDITINGRESULT, LINKPERMITRESULT, LINKIFSTARTCIRCUIT, LINKIFSTARTSOFT,
                        LINKIFSTARTDATA, LINKEXCUTERESULT, LINKIFACCORDINGPROJECT, LINKIFEXCUTESUCCESS,
                        LINKISTOMAINTENANCE, OPERATEYEAR, OPERATEMONTH, OPERATEDAY, LINKEXECUTEENDDATE, LINKFAILEDREASON
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
                , main.MAINSPECIALTYTYPE
                , main.MAINIFSAFE
                , main.MAINIFMUTUALCOMMUNICATION
                , main.MAINNETSORTONE
                , main.MAINNETSORTTWO
                , main.MAINNETSORTTHREE
                , main.MAINEQUIPMENTFACTORY
                , main.MAINCHANGESOURCE
                , main.MAINEXPECTATIONCOMPLETETIME
                , main.MAINIFREQUIREPROJECT
                , main.MAINTOUCHSHEETID
                , main.MAINREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.MAINIFRECORD
                , main.MAINEXECUTEENDDATE
--bussines link
                , link.LINKENDDATE
                , link.LINKPROJECTKEY
                , link.LINKAUDITINGRESULT
                , link.LINKPERMITRESULT
                , link.LINKIFSTARTCIRCUIT
                , link.LINKIFSTARTSOFT
                , link.LINKIFSTARTDATA
                , link.LINKEXCUTERESULT
                , link.LINKIFACCORDINGPROJECT
                , link.LINKIFEXCUTESUCCESS
                , link.LINKISTOMAINTENANCE
                , link.OPERATEYEAR
                , link.OPERATEMONTH
                , link.OPERATEDAY
                , link.LINKEXECUTEENDDATE
                , link.LINKFAILEDREASON

        from netchange_main main
            join netchange_link link
        on main.id= link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
            left join netchange_task task on link.aiid=task.id
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
            null :: integer OPERATETYPE,
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
            null :: varchar (255) OPERATEORGTYPE,
------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
            task.SUBTASKFLAG,
            task.PARENTTASKID,
            task.taskstatus,
            task.operatetype taskoperatetype
--bussines main
                , main.MAINSPECIALTYTYPE
                , main.MAINIFSAFE
                , main.MAINIFMUTUALCOMMUNICATION
                , main.MAINNETSORTONE
                , main.MAINNETSORTTWO
                , main.MAINNETSORTTHREE
                , main.MAINEQUIPMENTFACTORY
                , main.MAINCHANGESOURCE
                , main.MAINEXPECTATIONCOMPLETETIME
                , main.MAINIFREQUIREPROJECT
                , main.MAINTOUCHSHEETID
                , main.MAINREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.MAINIFRECORD
                , main.MAINEXECUTEENDDATE
--bussines link 
                , null ::datetime year to second LINKENDDATE
                , null :: varchar (255) LINKPROJECTKEY
                , null :: varchar (255) LINKAUDITINGRESULT
                , null :: varchar (255) LINKPERMITRESULT
                , null :: integer LINKIFSTARTCIRCUIT
                , null :: integer LINKIFSTARTSOFT
                , null :: integer LINKIFSTARTDATA
                , null :: varchar (255) LINKEXCUTERESULT
                , null :: varchar (255) LINKIFACCORDINGPROJECT
                , null :: varchar (255) LINKIFEXCUTESUCCESS
                , null :: varchar (255) LINKISTOMAINTENANCE
                , null :: integer OPERATEYEAR
                , null :: integer OPERATEMONTH
                , null :: integer OPERATEDAY
                , null ::datetime year to second LINKEXECUTEENDDATE
                , null :: varchar (255) LINKFAILEDREASON

        from netchange_main main
            join netchange_task task
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
values (45, '2007-07-07 00:00:00', '网络综合调整工单基础表采集的上一次操作的时间');


--中间表
CREATE TABLE est_netchange
(
    EST_DATA_STATUS             integer,
    send_bigrole_id             integer,
    operate_bigrole_id          integer,
    send_dept_level             varchar(30),
    operate_dept_level          varchar(30),
    MAINID                      varchar(255),
    SHEETID                     varchar(255),
    TITLE                       varchar(255),
    SHEETACCEPTLIMIT            DATETIME YEAR TO SECOND,
    SHEETCOMPLETELIMIT          DATETIME YEAR TO SECOND,
    SENDTIME                    DATETIME YEAR TO SECOND,
    SENDUSERID                  varchar(255),
    SENDDEPTID                  varchar(255),
    SENDROLEID                  varchar(255),
    SENDCONTACT                 varchar(255),
    STATUS                      integer,
    HOLDSTATISFIED              integer,
    ENDTIME                     DATETIME YEAR TO SECOND,
    ENDRESULT                   varchar(255),
    ENDUSERID                   varchar(255),
    ENDDEPTID                   varchar(255),
    ENDROLEID                   varchar(255),
    DELETED                     integer,
    PIID                        varchar(255),
    PARENTSHEETNAME             varchar(255),
    PARENTSHEETID               varchar(255),
    SHEETTEMPLATENAME           varchar(255),
    SHEETACCESSORIES            varchar(255),
    TODEPTID                    varchar(255),
    CANCELREASON                varchar(255),
    LINKID                      varchar(255),
    NODEACCEPTLIMIT             DATETIME YEAR TO SECOND,
    NODECOMPLETELIMIT           DATETIME YEAR TO SECOND,
    OPERATETYPE                 integer,
    OPERATETIME                 DATETIME YEAR TO SECOND,
    OPERATEUSERID               varchar(255),
    OPERATEDEPTID               varchar(255),
    OPERATEROLEID               varchar(255),
    TOORGTYPE                   integer,
    TOORGUSERID                 varchar(255),
    TOORGDEPTID                 varchar(255),
    TOORGROLEID                 varchar(255),
    ACCEPTFLAG                  integer,
    COMPLETEFLAG                integer,
    PRELINKID                   varchar(255),
    PARENTLINKID                varchar(255),
    FIRSTLINKID                 varchar(255),
    AIID                        varchar(255),
    ACTIVETEMPLATEID            varchar(255),
    NODETEMPLATENAME            varchar(255),
    NODEACCESSORIES             varchar(255),
    OPERATEORGTYPE              varchar(25),
    SUBTASKFLAG                 varchar(10),
    PARENTTASKID                varchar(50),
    TASKSTATUS                  varchar(255),
    taskoperatetype             varchar(255)
--bussines main
    ,
    MAINSPECIALTYTYPE           varchar(255),
    MAINIFSAFE                  varchar(255),
    MAINIFMUTUALCOMMUNICATION   varchar(255),
    MAINNETSORTONE              varchar(255),
    MAINNETSORTTWO              varchar(255),
    MAINNETSORTTHREE            varchar(255),
    MAINEQUIPMENTFACTORY        varchar(255),
    MAINCHANGESOURCE            varchar(255),
    MAINEXPECTATIONCOMPLETETIME DATETIME YEAR TO SECOND,
    MAINIFREQUIREPROJECT        varchar(255),
    MAINTOUCHSHEETID            varchar(255),
    MAINREJECTTIMES             INTEGER,
    SENDYEAR                    INTEGER,
    SENDMONTH                   INTEGER,
    SENDDAY                     INTEGER,
    MAINIFRECORD                integer,
    MAINEXECUTEENDDATE          DATETIME YEAR TO SECOND,
--bussines link
    LINKENDDATE                 DATETIME YEAR TO SECOND,
    LINKPROJECTKEY              varchar(255),
    LINKAUDITINGRESULT          varchar(255),
    LINKPERMITRESULT            varchar(255),
    LINKIFSTARTCIRCUIT          integer,
    LINKIFSTARTSOFT             integer,
    LINKIFSTARTDATA             integer,
    LINKEXCUTERESULT            varchar(255),
    LINKIFACCORDINGPROJECT      varchar(255),
    LINKIFEXCUTESUCCESS         varchar(255),
    LINKISTOMAINTENANCE         varchar(255),
    OPERATEYEAR                 INTEGER,
    OPERATEMONTH                INTEGER,
    OPERATEDAY                  INTEGER,
    LINKEXECUTEENDDATE          DATETIME YEAR TO SECOND,
    LINKFAILEDREASON            varchar(255)
)




