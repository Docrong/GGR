----采集视图
create or replace view v_circuitdispatch(
----est_stat---
                                         est_data_status,
                                         send_bigrole_id,
                                         operate_bigrole_id,
                                         send_dept_level,
                                         operate_dept_level,
--common main
                                         MAINID, SHEETID, TITLE, SHEETACCEPTLIMIT, SHEETCOMPLETELIMIT, SENDTIME,
                                         SENDUSERID, SENDDEPTID,
                                         SENDROLEID, SENDCONTACT, STATUS, HOLDSTATISFIED, ENDTIME, ENDRESULT, ENDUSERID,
                                         ENDDEPTID, ENDROLEID,
                                         DELETED, PIID, PARENTSHEETNAME, PARENTSHEETID, SHEETTEMPLATENAME,
                                         SHEETACCESSORIES,
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
                                         MAINCHANGESOURCE, MAINTOUCHSHEETID, MAINRESOURCENO, MAINNETSORTTWO,
                                         MAINNETSORTTHREE, MAINREJECTTIMES, SENDYEAR, SENDMONTH, SENDDAY, MAINIFRECORD,
                                         MAINEXECUTEENDDATE
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
            2                                                                                 est_data_status
             ,
            srole.roleid                                                                      send_bigrole_id
             ,
            case when task.operatetype = 'subrole' then orole.roleid else to_number(null) end operate_bigrole_id
             ,
            sd.linkid                                                                         send_dept_level
             ,
            od.linkid                                                                         operate_dept_level
             ,
--common main
            main.id                                                                           MAINID
             , main.SHEETID
             , main.TITLE
             , main.SHEETACCEPTLIMIT
             , main.SHEETCOMPLETELIMIT
             , main.SENDTIME
             , main.SENDUSERID
             , main.SENDDEPTID
             ,
            main.SENDROLEID
             , main.SENDCONTACT
             , main.STATUS
             , main.HOLDSTATISFIED
             , main.ENDTIME
             , main.ENDRESULT
             , main.ENDUSERID
             , main.ENDDEPTID
             , main.ENDROLEID
             ,
            main.DELETED
             , main.PIID
             , main.PARENTSHEETNAME
             , main.PARENTSHEETID
             , main.SHEETTEMPLATENAME
             , main.SHEETACCESSORIES
             ,
            main.TODEPTID
             , main.CANCELREASON
             ,
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
--common link
            link.ID                                                                           linkid
             ,
            task.ACCEPTTIMELIMIT                                                              NODEACCEPTLIMIT
             ,
            task.COMPLETETIMELIMIT                                                            NODECOMPLETELIMIT
             ,
            link.OPERATETYPE
             ,
            link.OPERATETIME
             ,
            link.OPERATEUSERID
             ,
            link.OPERATEDEPTID
             ,
            link.OPERATEROLEID
             ,
            link.TOORGTYPE
             ,
            link.TOORGUSERID
             ,
            link.TOORGDEPTID
             ,
            link.TOORGROLEID
             ,
            link.ACCEPTFLAG
             ,
            link.COMPLETEFLAG
             ,
            link.PRELINKID
             ,
            link.PARENTLINKID
             ,
            link.FIRSTLINKID
             ,
            link.AIID
             ,
            link.ACTIVETEMPLATEID
             ,
            link.NODETEMPLATENAME
             ,
            link.NODEACCESSORIES
             ,
            link.OPERATEORGTYPE
             ,
--common task
            task.SUBTASKFLAG
             ,
            task.PARENTTASKID
             ,
            task.taskstatus
             ,
            task.operatetype                                                                  taskoperatetype
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
                      on main.id = link.mainid and main.templateflag = 0 and link.TEMPLATEFLAG = 0
                 left join circuitdispatch_task task on link.aiid = task.id
                 left join taw_system_sub_role orole on link.operateroleid = orole.id
                 left join taw_system_sub_role srole on main.sendroleid = srole.id
                 join taw_system_dept sd on main.senddeptid = sd.deptid
                 join taw_system_dept od on link.operatedeptid = od.deptid
    )
    union all
    (
--未处理
        select
----est_stat---
            case when task.taskstatus = 2 then 0 when task.taskstatus = 8 then 1 end                  est_data_status
             ,
            srole.roleid                                                                              send_bigrole_id
             ,
            case when task.operatetype = 'subrole' then orole.roleid else to_number(null) end         operate_bigrole_id
             ,
            sd.linkid                                                                                 send_dept_level
             ,
            od.linkid                                                                                 operate_dept_level
             ,
--common main
            main.id                                                                                   MAINID
             , main.SHEETID
             , main.TITLE
             , main.SHEETACCEPTLIMIT
             , main.SHEETCOMPLETELIMIT
             , main.SENDTIME
             , main.SENDUSERID
             , main.SENDDEPTID
             ,
            main.SENDROLEID
             , main.SENDCONTACT
             , main.STATUS
             , main.HOLDSTATISFIED
             , main.ENDTIME
             , main.ENDRESULT
             , main.ENDUSERID
             , main.ENDDEPTID
             , main.ENDROLEID
             ,
            main.DELETED
             , main.PIID
             , main.PARENTSHEETNAME
             , main.PARENTSHEETID
             , main.SHEETTEMPLATENAME
             , main.SHEETACCESSORIES
             ,
            main.TODEPTID
             , main.CANCELREASON
             ,
------user for find template---
--main.TEMPLATEFLAG
------common main backup----
--main.sendorgtype sendorgtype,send_year,send_month,send_day,send_week,send_bigrole_id
--common link
            to_char(null)                                                                             linkid
             ,
            task.ACCEPTTIMELIMIT                                                                      NODEACCEPTLIMIT
             ,
            task.COMPLETETIMELIMIT                                                                    NODECOMPLETELIMIT
             ,
            to_char(null)                                                                             OPERATETYPE
             ,
            to_date(null)                                                                             OPERATETIME
             ,
            case when task.taskowner != task.operateroleid then task.taskowner else to_char(null) end OPERATEUSERID
             ,
            case when task.taskowner != task.operateroleid then ud.deptid else u.deptid end           OPERATEDEPTID
             ,
            task.operateroleid                                                                        OPERATEROLEID
             ,
            to_number(null)                                                                           TOORGTYPE
             ,
            to_char(null)                                                                             TOORGUSERID
             ,
            to_char(null)                                                                             TOORGDEPTID
             ,
            to_char(null)                                                                             TOORGROLEID
             ,
            case
                when to_date(to_char(task.ACCEPTTIMELIMIT, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') > sysdate
                    then 1
                else 2 end                                                                            ACCEPTFLAG
             ,
            case
                when to_date(to_char(task.COMPLETETIMELIMIT, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') >
                     sysdate then 1
                else 2 end                                                                            COMPLETEFLAG
             ,
            task.PRELINKID
             ,
            to_char(null)                                                                             PARENTLINKID
             ,
            to_char(null)                                                                             FIRSTLINKID
             ,
            task.id                                                                                   AIID
             ,
            task.taskname                                                                             ACTIVETEMPLATEID
             ,
            to_char(null)                                                                             NODETEMPLATENAME
             ,
            to_char(null)                                                                             NODEACCESSORIES
             ,
            to_char(null)                                                                             OPERATEORGTYPE
             ,
             ------common link backup----
--operate_year,operate_month,operate_day,operate_week,
--common task
            task.SUBTASKFLAG
             ,
            task.PARENTTASKID
             ,
            task.taskstatus
             ,
            task.operatetype                                                                          taskoperatetype
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
             , to_date(null)                                                                          LINKEXECUTEENDDATE
             , to_char(null)                                                                          LINKPROGRAMMEEXPLAIN
             , to_char(null)                                                                          LINKPROGRAMMEKEY
             , to_char(null)                                                                          LINKPROGRAMMENO
             , to_number(null)                                                                        LINKPERMITRESULT
             , to_char(null)                                                                          LINKEXCUTEPRINCIPAL
             , to_char(null)                                                                          LINKCONTACT
             , to_date(null)                                                                          LINKPLANSTARTDATE
             , to_date(null)                                                                          LINKPLANENDDATE
             , to_number(null)                                                                        LINKIFAFFECTOPERATION
             , to_char(null)                                                                          LINKREFEROPERATEDEPT
             , to_number(null)                                                                        LINKIFNOTIFY
             , to_number(null)                                                                        LINKEXCUTERESULT
             , to_number(null)                                                                        LINKISACCORDANCEPROGRAMME
             , to_number(null)                                                                        LINKTESTRESULT
             , to_number(null)                                                                        LINKIFUPDATEPLAN
             , to_number(null)                                                                        LINKCIRCUITUPDATE
             , to_number(null)                                                                        LINKISTOMAINTENANCE
             , to_char(null)                                                                          LINKFAILEDREASON
             , to_char(null)                                                                          LINKINVOLVEDPROVINCE
             , to_char(null)                                                                          LINKINVOLVEDCITY
             , to_number(null)                                                                        OPERATEYEAR
             , to_number(null)                                                                        OPERATEMONTH
             , to_number(null)                                                                        OPERATEDAY
             , to_char(null)                                                                          LINKREJECTREASON

        from circuitdispatch_main main

                 join circuitdispatch_task task
                      on main.id = task.sheetkey and task.taskstatus != 5 and main.templateflag = 0
                 left join taw_system_user ud on task.taskowner != task.operateroleid and task.taskowner = ud.userid
                 left join taw_system_sub_role orole on orole.id = task.operateroleid
                 left join taw_system_sub_role srole on srole.id = main.sendroleid
                 left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
                 left join taw_system_user u on u.userid = refr.userid
                 join taw_system_dept sd on main.senddeptid = sd.deptid
                 join taw_system_dept od on u.deptid = od.deptid
    )


--初始化工单采集时间
insert into est_last_oper
values (42, to_date('2007-07-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '电路调度工单基础表采集的上一次操作的时间');


--中间表
CREATE TABLE est_circuitdispatch
(
    EST_DATA_STATUS           NUMBER(5),
    send_bigrole_id           number(5),
    operate_bigrole_id        number(5),
    send_dept_level           VARCHAR2(30),
    operate_dept_level        VARCHAR2(30),
    MAINID                    VARCHAR2(510),
    SHEETID                   VARCHAR2(510),
    TITLE                     VARCHAR2(510),
    SHEETACCEPTLIMIT          DATE,
    SHEETCOMPLETELIMIT        DATE,
    SENDTIME                  DATE,
    SENDUSERID                VARCHAR2(510),
    SENDDEPTID                VARCHAR2(510),
    SENDROLEID                VARCHAR2(510),
    SENDCONTACT               VARCHAR2(510),
    STATUS                    number(10),
    HOLDSTATISFIED            number(10),
    ENDTIME                   DATE,
    ENDRESULT                 VARCHAR2(510),
    ENDUSERID                 VARCHAR2(510),
    ENDDEPTID                 VARCHAR2(510),
    ENDROLEID                 VARCHAR2(510),
    DELETED                   NUMBER(10),
    PIID                      VARCHAR2(510),
    PARENTSHEETNAME           VARCHAR2(510),
    PARENTSHEETID             VARCHAR2(510),
    SHEETTEMPLATENAME         VARCHAR2(510),
    SHEETACCESSORIES          VARCHAR2(510),
    TODEPTID                  VARCHAR2(510),
    CANCELREASON              VARCHAR2(510),
    LINKID                    VARCHAR2(510),
    NODEACCEPTLIMIT           DATE,
    NODECOMPLETELIMIT         DATE,
    OPERATETYPE               VARCHAR2(100),
    OPERATETIME               DATE,
    OPERATEUSERID             VARCHAR2(510),
    OPERATEDEPTID             VARCHAR2(510),
    OPERATEROLEID             VARCHAR2(510),
    TOORGTYPE                 NUMBER(10),
    TOORGUSERID               VARCHAR2(510),
    TOORGDEPTID               VARCHAR2(510),
    TOORGROLEID               VARCHAR2(510),
    ACCEPTFLAG                NUMBER(10),
    COMPLETEFLAG              NUMBER(10),
    PRELINKID                 VARCHAR2(510),
    PARENTLINKID              VARCHAR2(510),
    FIRSTLINKID               VARCHAR2(510),
    AIID                      VARCHAR2(510),
    ACTIVETEMPLATEID          VARCHAR2(510),
    NODETEMPLATENAME          VARCHAR2(510),
    NODEACCESSORIES           VARCHAR2(510),
    OPERATEORGTYPE            VARCHAR2(30),
    SUBTASKFLAG               VARCHAR2(10),
    PARENTTASKID              VARCHAR2(50),
    TASKSTATUS                VARCHAR2(510),
    TASKOPERATETYPE           VARCHAR2(510),
    MAINIFSAFE                NUMBER(10),
    MAINIFMUTUALCOMMUNICATION NUMBER(10),
    MAINNETSORTONE            VARCHAR2(255),
    MAINEQUIPMENTFACTORY      VARCHAR2(255),
    MAINIFCHANGEPROJECT       NUMBER(10),
    MAINCHANGESOURCE          VARCHAR2(510),
    MAINTOUCHSHEETID          VARCHAR2(510),
    MAINRESOURCENO            VARCHAR2(510),
    MAINNETSORTTWO            VARCHAR2(255),
    MAINNETSORTTHREE          VARCHAR2(255),
    MAINREJECTTIMES           NUMBER(10),
    SENDYEAR                  INTEGER,
    SENDMONTH                 INTEGER,
    SENDDAY                   INTEGER,
    MAINIFRECORD              NUMBER(10),
    MAINEXECUTEENDDATE        date,
    LINKEXECUTEENDDATE        date,
    LINKPROGRAMMEEXPLAIN      VARCHAR2(255),
    LINKPROGRAMMEKEY          VARCHAR2(510),
    LINKPROGRAMMENO           VARCHAR2(510),
    LINKPERMITRESULT          NUMBER(10),
    LINKEXCUTEPRINCIPAL       VARCHAR2(510),
    LINKCONTACT               VARCHAR2(510),
    LINKPLANSTARTDATE         date,
    LINKPLANENDDATE           date,
    LINKIFAFFECTOPERATION     NUMBER(10),
    LINKREFEROPERATEDEPT      VARCHAR2(510),
    LINKIFNOTIFY              NUMBER(10),
    LINKEXCUTERESULT          NUMBER(10),
    LINKISACCORDANCEPROGRAMME NUMBER(10),
    LINKTESTRESULT            NUMBER(10),
    LINKIFUPDATEPLAN          NUMBER(10),
    LINKCIRCUITUPDATE         NUMBER(10),
    LINKISTOMAINTENANCE       NUMBER(10),
    LINKFAILEDREASON          VARCHAR2(510),
    LINKINVOLVEDPROVINCE      VARCHAR2(510),
    LINKINVOLVEDCITY          VARCHAR2(510),
    OPERATEYEAR               number,
    OPERATEMONTH              number,
    OPERATEDAY                number,
    LINKREJECTREASON          VARCHAR2(500)
)


---建采集相关索引
create index index_circuitdispatchtask_sheetkey on circuitdispatch_task
    (sheetkey) TABLESPACE indexdbs;
create index index_circuitdispatchlink_mainid on circuitdispatch_link
    (mainid) TABLESPACE indexdbs;
create index index_circuitdispatchlink_aiid on circuitdispatch_link
    (aiid) using btree  in indexdbs;


create index index_estcircuitdispatch_mainid on est_circuitdispatch
    (mainid) TABLESPACE indexdbs;
create index index_estcircuitdispatch_sendtime on est_circuitdispatch
    (sendtime) TABLESPACE indexdbs;
create index index_estcircuitdispatch_endtime on est_circuitdispatch
    (endtime) TABLESPACE indexdbs;