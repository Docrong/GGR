----视图采集
create view v_netdata(
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
--common task
                      SUBTASKFLAG,
                      PARENTTASKID,
                      taskstatus,
                      taskoperatetype
--bussines main
    , MAINISSECURITY, MAINISCONNECT, MAINFACTORY, MAINCELLINFO, MAINCHANGESOURCE, MAINPARENTSHEETID, MAINISNEEDDESIGN,
                      MAINDESIGNID, MAINNETTYPETHREE, MAINNETTYPETWO, MAINNETTYPEONE, MAINREJECTTIMES, SENDYEAR,
                      SENDMONTH, SENDDAY, MAINIFRECORD, MAINEXECUTEENDDATE
--bussines link
    , LINKCOMPLETELIMITTIME, LINKINVOLVEDPROVINCE, LINKINVOLVEDCITY, LINKDESIGNKEY, LINKISCHECK, LINKPERMITRESULT,
                      LINKMANAGER, LINKCONTACT, LINKPLANSTARTTIME, LINKPLANENDTIME, LINKISEFFECTBUSINESS,
                      LINKBUSINESSDEPT, LINKISSENDTOFRONT, LINKCUTRESULT, LINKISPLAN, LINKISUPDATEWORK,
                      LINKISNEEDPROJECT, OPERATERCONTACT, OPERATEYEAR, OPERATEMONTH, OPERATEDAY, LINKFAILEDREASON
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
                , main.MAINISSECURITY
                , main.MAINISCONNECT
                , main.MAINFACTORY
                , main.MAINCELLINFO
                , main.MAINCHANGESOURCE
                , main.MAINPARENTSHEETID
                , main.MAINISNEEDDESIGN
                , main.MAINDESIGNID
                , main.MAINNETTYPETHREE
                , main.MAINNETTYPETWO
                , main.MAINNETTYPEONE
                , main.MAINREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.MAINIFRECORD
                , main.MAINEXECUTEENDDATE
--bussines link
                , link.LINKCOMPLETELIMITTIME
                , link.LINKINVOLVEDPROVINCE
                , link.LINKINVOLVEDCITY
                , link.LINKDESIGNKEY
                , link.LINKISCHECK
                , link.LINKPERMITRESULT
                , link.LINKMANAGER
                , link.LINKCONTACT
                , link.LINKPLANSTARTTIME
                , link.LINKPLANENDTIME
                , link.LINKISEFFECTBUSINESS
                , link.LINKBUSINESSDEPT
                , link.LINKISSENDTOFRONT
                , link.LINKCUTRESULT
                , link.LINKISPLAN
                , link.LINKISUPDATEWORK
                , link.LINKISNEEDPROJECT
                , link.OPERATERCONTACT
                , link.OPERATEYEAR
                , link.OPERATEMONTH
                , link.OPERATEDAY
                , link.LINKFAILEDREASON


        from netdata_main main
            join netdata_link link
        on main.id= link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
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
--common link
            null :: varchar (255) linkid,
            task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
            task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
            null :: integer OPERATETYPE,
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
            null :: varchar (50) OPERATEORGTYPE,
--common task
            task.SUBTASKFLAG,
            task.PARENTTASKID,
            task.taskstatus,
            task.operatetype taskoperatetype
--bussines main
                , main.MAINISSECURITY
                , main.MAINISCONNECT
                , main.MAINFACTORY
                , main.MAINCELLINFO
                , main.MAINCHANGESOURCE
                , main.MAINPARENTSHEETID
                , main.MAINISNEEDDESIGN
                , main.MAINDESIGNID
                , main.MAINNETTYPETHREE
                , main.MAINNETTYPETWO
                , main.MAINNETTYPEONE
                , main.MAINREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.MAINIFRECORD
                , main.MAINEXECUTEENDDATE
--bussines link
                , null ::DATETIME YEAR TO SECOND LINKCOMPLETELIMITTIME
                , null :: varchar (255) LINKINVOLVEDPROVINCE
                , null :: varchar (255) LINKINVOLVEDCITY
                , null :: varchar (255) LINKDESIGNKEY
                , null :: varchar (255) LINKISCHECK
                , null :: varchar (255) LINKPERMITRESULT
                , null :: varchar (255) LINKMANAGER
                , null :: varchar (255) LINKCONTACT
                , null ::DATETIME YEAR TO SECOND LINKPLANSTARTTIME
                , null ::DATETIME YEAR TO SECOND LINKPLANENDTIME
                , null :: varchar (255) LINKISEFFECTBUSINESS
                , null :: varchar (255) LINKBUSINESSDEPT
                , null :: varchar (255) LINKISSENDTOFRONT
                , null :: varchar (255) LINKCUTRESULT
                , null :: varchar (255) LINKISPLAN
                , null :: varchar (255) LINKISUPDATEWORK
                , null :: varchar (255) LINKISNEEDPROJECT
                , null :: varchar (255) OPERATERCONTACT
                , null :: integer OPERATEYEAR
                , null :: integer OPERATEMONTH
                , null :: integer OPERATEDAY
                , null :: varchar (255) LINKFAILEDREASON

        from netdata_main main
            join netdata_task task
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
values (44, '2007-07-07 00:00:00', '网络数据管理工单基础表采集的上一次操作的时间');


--中间表
CREATE TABLE est_netdata
(
    EST_DATA_STATUS       integer,
    send_bigrole_id       integer,
    operate_bigrole_id    integer,
    send_dept_level       varchar(30),
    operate_dept_level    varchar(30),
    MAINID                varchar(255),
    SHEETID               varchar(255),
    TITLE                 varchar(255),
    SHEETACCEPTLIMIT      DATETIME YEAR TO SECOND,
    SHEETCOMPLETELIMIT    DATETIME YEAR TO SECOND,
    SENDTIME              DATETIME YEAR TO SECOND,
    SENDUSERID            varchar(255),
    SENDDEPTID            varchar(255),
    SENDROLEID            varchar(255),
    SENDCONTACT           varchar(255),
    STATUS                integer,
    HOLDSTATISFIED        integer,
    ENDTIME               DATETIME YEAR TO SECOND,
    ENDRESULT             varchar(255),
    ENDUSERID             varchar(255),
    ENDDEPTID             varchar(255),
    ENDROLEID             varchar(255),
    DELETED               integer,
    PIID                  varchar(255),
    PARENTSHEETNAME       varchar(255),
    PARENTSHEETID         varchar(255),
    SHEETTEMPLATENAME     varchar(255),
    SHEETACCESSORIES      varchar(255),
    TODEPTID              varchar(255),
    CANCELREASON          varchar(255),
    LINKID                varchar(255),
    NODEACCEPTLIMIT       DATETIME YEAR TO SECOND,
    NODECOMPLETELIMIT     DATETIME YEAR TO SECOND,
    OPERATETYPE           integer,
    OPERATETIME           DATETIME YEAR TO SECOND,
    OPERATEUSERID         varchar(255),
    OPERATEDEPTID         varchar(255),
    OPERATEROLEID         varchar(255),
    TOORGTYPE             integer,
    TOORGUSERID           varchar(255),
    TOORGDEPTID           varchar(255),
    TOORGROLEID           varchar(255),
    ACCEPTFLAG            integer,
    COMPLETEFLAG          integer,
    PRELINKID             varchar(255),
    PARENTLINKID          varchar(255),
    FIRSTLINKID           varchar(255),
    AIID                  varchar(255),
    ACTIVETEMPLATEID      varchar(255),
    NODETEMPLATENAME      varchar(255),
    NODEACCESSORIES       varchar(255),
    OPERATEORGTYPE        varchar(50),
    SUBTASKFLAG           varchar(10),
    PARENTTASKID          varchar(50),
    TASKSTATUS            varchar(255),
    TASKOPERATETYPE       varchar(255),
    MAINISSECURITY        varchar(50),
    MAINISCONNECT         varchar(50),
    MAINFACTORY           varchar(50),
    MAINCELLINFO          varchar(50),
    MAINCHANGESOURCE      varchar(50),
    MAINPARENTSHEETID     varchar(50),
    MAINISNEEDDESIGN      varchar(50),
    MAINDESIGNID          varchar(50),
    MAINNETTYPETHREE      varchar(50),
    MAINNETTYPETWO        varchar(50),
    MAINNETTYPEONE        varchar(50),
    MAINREJECTTIMES       INTEGER,
    SENDYEAR              INTEGER,
    SENDMONTH             INTEGER,
    SENDDAY               INTEGER,
    MAINIFRECORD          INTEGER,
    MAINEXECUTEENDDATE    DATETIME YEAR TO SECOND,
    LINKCOMPLETELIMITTIME DATETIME YEAR TO SECOND,
    LINKINVOLVEDPROVINCE  varchar(50),
    LINKINVOLVEDCITY      varchar(50),
    LINKDESIGNKEY         varchar(50),
    LINKISCHECK           varchar(50),
    LINKPERMITRESULT      varchar(50),
    LINKMANAGER           varchar(50),
    LINKCONTACT           varchar(50),
    LINKPLANSTARTTIME     DATETIME YEAR TO SECOND,
    LINKPLANENDTIME       DATETIME YEAR TO SECOND,
    LINKISEFFECTBUSINESS  varchar(50),
    LINKBUSINESSDEPT      varchar(50),
    LINKISSENDTOFRONT     varchar(50),
    LINKCUTRESULT         varchar(50),
    LINKISPLAN            varchar(50),
    LINKISUPDATEWORK      varchar(50),
    LINKISNEEDPROJECT     varchar(50),
    OPERATERCONTACT       varchar(255),
    OPERATEYEAR           INTEGER,
    OPERATEMONTH          INTEGER,
    OPERATEDAY            INTEGER,
    LINKFAILEDREASON      varchar(50)
)


