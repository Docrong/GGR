----采集视图
create view v_circuitdispatch(
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
    , MAINIFSAFE, MAINIFMUTUALCOMMUNICATION, MAINNETSORTONE, MAINEQUIPMENTFACTORY, MAINIFCHANGEPROJECT,
                              MAINCHANGESOURCE, MAINTOUCHSHEETID, MAINRESOURCENO, MAINNETSORTTWO, MAINNETSORTTHREE,
                              MAINREJECTTIMES, SENDYEAR, SENDMONTH, SENDDAY, MAINIFRECORD, MAINEXECUTEENDDATE
--bussines link
    , LINKEXECUTEENDDATE, LINKPROGRAMMEEXPLAIN, LINKPROGRAMMEKEY, LINKPROGRAMMENO, LINKPERMITRESULT,
                              LINKEXCUTEPRINCIPAL, LINKCONTACT, LINKPLANSTARTDATE, LINKPLANENDDATE,
                              LINKIFAFFECTOPERATION, LINKREFEROPERATEDEPT, LINKIFNOTIFY, LINKEXCUTERESULT,
                              LINKISACCORDANCEPROGRAMME, LINKTESTRESULT, LINKIFUPDATEPLAN, LINKCIRCUITUPDATE,
                              LINKISTOMAINTENANCE, LINKFAILEDREASON, LINKINVOLVEDPROVINCE, LINKINVOLVEDCITY,
                              OPERATEYEAR, OPERATEMONTH, OPERATEDAY, LINKREJECTREASON
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
--common task
            task.SUBTASKFLAG,
            task.PARENTTASKID,
            task.taskstatus,
            task.operatetype taskoperatetype
--bussines main
                , main.MAINIFSAFE
                , main.MAINIFMUTUALCOMMUNICATION
                , main.MAINNETSORTONE
                , main.MAINEQUIPMENTFACTORY
                , main.MAINIFCHANGEPROJECT
                , main.MAINCHANGESOURCE
                , main.MAINTOUCHSHEETID
                , main.MAINRESOURCENO
                , main.MAINNETSORTTWO
                , main.MAINNETSORTTHREE
                , main.MAINREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.MAINIFRECORD
                , main.MAINEXECUTEENDDATE
--bussines link
                , link.LINKEXECUTEENDDATE
                , link.LINKPROGRAMMEEXPLAIN
                , link.LINKPROGRAMMEKEY
                , link.LINKPROGRAMMENO
                , link.LINKPERMITRESULT
                , link.LINKEXCUTEPRINCIPAL
                , link.LINKCONTACT
                , link.LINKPLANSTARTDATE
                , link.LINKPLANENDDATE
                , link.LINKIFAFFECTOPERATION
                , link.LINKREFEROPERATEDEPT
                , link.LINKIFNOTIFY
                , link.LINKEXCUTERESULT
                , link.LINKISACCORDANCEPROGRAMME
                , link.LINKTESTRESULT
                , link.LINKIFUPDATEPLAN
                , link.LINKCIRCUITUPDATE
                , link.LINKISTOMAINTENANCE
                , link.LINKFAILEDREASON
                , link.LINKINVOLVEDPROVINCE
                , link.LINKINVOLVEDCITY
                , link.OPERATEYEAR
                , link.OPERATEMONTH
                , link.OPERATEDAY
                , link.LINKREJECTREASON


        from circuitdispatch_main main
            join circuitdispatch_link link
        on main.id= link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
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
            null ::DATETIME YEAR TO SECOND OPERATETIME,
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
                , main.MAINIFSAFE
                , main.MAINIFMUTUALCOMMUNICATION
                , main.MAINNETSORTONE
                , main.MAINEQUIPMENTFACTORY
                , main.MAINIFCHANGEPROJECT
                , main.MAINCHANGESOURCE
                , main.MAINTOUCHSHEETID
                , main.MAINRESOURCENO
                , main.MAINNETSORTTWO
                , main.MAINNETSORTTHREE
                , main.MAINREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.MAINIFRECORD
                , main.MAINEXECUTEENDDATE
--bussines link
                , null ::DATETIME YEAR TO SECOND LINKEXECUTEENDDATE
                , null :: varchar (255) LINKPROGRAMMEEXPLAIN
                , null :: varchar (255) LINKPROGRAMMEKEY
                , null :: varchar (255) LINKPROGRAMMENO
                , null :: integer LINKPERMITRESULT
                , null :: varchar (255) LINKEXCUTEPRINCIPAL
                , null :: varchar (255) LINKCONTACT
                , null ::DATETIME YEAR TO SECOND LINKPLANSTARTDATE
                , null ::DATETIME YEAR TO SECOND LINKPLANENDDATE
                , null :: integer LINKIFAFFECTOPERATION
                , null :: varchar (255) LINKREFEROPERATEDEPT
                , null :: integer LINKIFNOTIFY
                , null :: integer LINKEXCUTERESULT
                , null :: integer LINKISACCORDANCEPROGRAMME
                , null :: integer LINKTESTRESULT
                , null :: integer LINKIFUPDATEPLAN
                , null :: integer LINKCIRCUITUPDATE
                , null :: integer LINKISTOMAINTENANCE
                , null :: varchar (255) LINKFAILEDREASON
                , null :: varchar (255) LINKINVOLVEDPROVINCE
                , null :: varchar (255) LINKINVOLVEDCITY
                , null :: integer OPERATEYEAR
                , null :: integer OPERATEMONTH
                , null :: integer OPERATEDAY
                , null :: varchar (255) LINKREJECTREASON

        from circuitdispatch_main main
            join circuitdispatch_task task
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
values (42, '2007-07-07 00:00:00', '电路调度工单基础表采集的上一次操作的时间');


--中间表
CREATE TABLE est_circuitdispatch
(
    EST_DATA_STATUS           integer,
    send_bigrole_id           integer,
    operate_bigrole_id        integer,
    send_dept_level           varchar(30),
    operate_dept_level        varchar(30),
    MAINID                    varchar(255),
    SHEETID                   varchar(255),
    TITLE                     varchar(255),
    SHEETACCEPTLIMIT          DATETIME YEAR TO SECOND,
    SHEETCOMPLETELIMIT        DATETIME YEAR TO SECOND,
    SENDTIME                  DATETIME YEAR TO SECOND,
    SENDUSERID                varchar(255),
    SENDDEPTID                varchar(255),
    SENDROLEID                varchar(255),
    SENDCONTACT               varchar(255),
    STATUS                    integer,
    HOLDSTATISFIED            integer,
    ENDTIME                   DATETIME YEAR TO SECOND,
    ENDRESULT                 varchar(255),
    ENDUSERID                 varchar(255),
    ENDDEPTID                 varchar(255),
    ENDROLEID                 varchar(255),
    DELETED                   integer,
    PIID                      varchar(255),
    PARENTSHEETNAME           varchar(255),
    PARENTSHEETID             varchar(255),
    SHEETTEMPLATENAME         varchar(255),
    SHEETACCESSORIES          varchar(255),
    TODEPTID                  varchar(255),
    CANCELREASON              varchar(255),
    LINKID                    varchar(255),
    NODEACCEPTLIMIT           DATETIME YEAR TO SECOND,
    NODECOMPLETELIMIT         DATETIME YEAR TO SECOND,
    OPERATETYPE               varchar(100),
    OPERATETIME               DATETIME YEAR TO SECOND,
    OPERATEUSERID             varchar(255),
    OPERATEDEPTID             varchar(255),
    OPERATEROLEID             varchar(255),
    TOORGTYPE                 integer,
    TOORGUSERID               varchar(255),
    TOORGDEPTID               varchar(255),
    TOORGROLEID               varchar(255),
    ACCEPTFLAG                integer,
    COMPLETEFLAG              integer,
    PRELINKID                 varchar(255),
    PARENTLINKID              varchar(255),
    FIRSTLINKID               varchar(255),
    AIID                      varchar(255),
    ACTIVETEMPLATEID          varchar(255),
    NODETEMPLATENAME          varchar(255),
    NODEACCESSORIES           varchar(255),
    OPERATEORGTYPE            varchar(30),
    SUBTASKFLAG               varchar(10),
    PARENTTASKID              varchar(50),
    TASKSTATUS                varchar(255),
    TASKOPERATETYPE           varchar(255),
    MAINIFSAFE                integer,
    MAINIFMUTUALCOMMUNICATION integer,
    MAINNETSORTONE            varchar(255),
    MAINEQUIPMENTFACTORY      varchar(255),
    MAINIFCHANGEPROJECT       integer,
    MAINCHANGESOURCE          varchar(255),
    MAINTOUCHSHEETID          varchar(255),
    MAINRESOURCENO            varchar(255),
    MAINNETSORTTWO            varchar(255),
    MAINNETSORTTHREE          varchar(255),
    MAINREJECTTIMES           integer,
    SENDYEAR                  INTEGER,
    SENDMONTH                 INTEGER,
    SENDDAY                   INTEGER,
    MAINIFRECORD              integer,
    MAINEXECUTEENDDATE        DATETIME YEAR TO SECOND,
    LINKEXECUTEENDDATE        DATETIME YEAR TO SECOND,
    LINKPROGRAMMEEXPLAIN      varchar(255),
    LINKPROGRAMMEKEY          varchar(255),
    LINKPROGRAMMENO           varchar(255),
    LINKPERMITRESULT          integer,
    LINKEXCUTEPRINCIPAL       varchar(255),
    LINKCONTACT               varchar(255),
    LINKPLANSTARTDATE         DATETIME YEAR TO SECOND,
    LINKPLANENDDATE           DATETIME YEAR TO SECOND,
    LINKIFAFFECTOPERATION     integer,
    LINKREFEROPERATEDEPT      varchar(255),
    LINKIFNOTIFY              integer,
    LINKEXCUTERESULT          integer,
    LINKISACCORDANCEPROGRAMME integer,
    LINKTESTRESULT            integer,
    LINKIFUPDATEPLAN          integer,
    LINKCIRCUITUPDATE         integer,
    LINKISTOMAINTENANCE       integer,
    LINKFAILEDREASON          varchar(255),
    LINKINVOLVEDPROVINCE      varchar(255),
    LINKINVOLVEDCITY          varchar(255),
    OPERATEYEAR               integer,
    OPERATEMONTH              integer,
    OPERATEDAY                integer,
    LINKREJECTREASON          varchar(255)
)extent size 102400 next size 51200 lock mode row;

---建采集相关索引
create index index_circuitdispatchtask_sheetkey on circuitdispatch_task
    (sheetkey) using btree  in indexdbs;
create index index_circuitdispatchlink_mainid on circuitdispatch_link
    (mainid) using btree  in indexdbs;
create index index_circuitdispatchlink_aiid on circuitdispatch_link
    (aiid) using btree  in indexdbs;


create index index_estcircuitdispatch_mainid on est_circuitdispatch
    (mainid) using btree  in indexdbs;
create index index_estcircuitdispatch_sendtime on est_circuitdispatch
    (sendtime) using btree  in indexdbs;
create index index_estcircuitdispatch_endtime on est_circuitdispatch
    (endtime) using btree  in indexdbs;