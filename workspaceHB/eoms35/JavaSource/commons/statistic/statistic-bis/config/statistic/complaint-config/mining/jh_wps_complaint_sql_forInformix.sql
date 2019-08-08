---采集视图
create view v_complaint(
                        est_data_status,
                        send_bigrole_id,
                        operate_bigrole_id,
                        send_dept_level,
                        operate_dept_level,
--common main
                        MAINID, SHEETID, TITLE, SHEETACCEPTLIMIT, SHEETCOMPLETELIMIT, SENDTIME, SENDUSERID, SENDDEPTID,
                        SENDROLEID, SENDCONTACT, STATUS, HOLDSTATISFIED, ENDTIME, ENDUSERID, ENDDEPTID, ENDROLEID,
                        DELETED, PIID, PARENTSHEETNAME, PARENTSHEETID, SHEETTEMPLATENAME, SHEETACCESSORIES,
                        TODEPTID, CANCELREASON,
--common link
                        linkid,
                        NODEACCEPTLIMIT,
                        NODECOMPLETELIMIT,
                        OPERATETYPE, OPERATETIME, OPERATEUSERID, OPERATEDEPTID, OPERATEROLEID, TOORGTYPE, TOORGUSERID,
                        TOORGDEPTID, TOORGROLEID, ACCEPTFLAG, COMPLETEFLAG, PRELINKID, PARENTLINKID, FIRSTLINKID,
                        AIID, ACTIVETEMPLATEID, NODETEMPLATENAME, NODEACCESSORIES, OPERATEORGTYPE,

--common task
                        SUBTASKFLAG, PARENTTASKID, taskstatus, taskoperatetype,
--bussines main
                        DEALTIME1,
                        DEALTIME2,
                        URGENTDEGREE,
                        BTYPE1,
                        BDEPTCONTACT,
                        CUSTOMERNAME,
                        CUSTOMPHONE,
                        COMPLAINTTIME,
                        FAULTTIME,
                        COMPLAINTADD,
                        COMPLAINTDESC,
                        BDEPTCONTACTPHONE,
                        REPEATCOMPLAINTTIMES,
                        COMPLAINTTYPE1,
                        COMPLAINTTYPE2,
                        COMPLAINTTYPE,
                        COMPLAINTTYPE4,
                        COMPLAINTTYPE5,
                        COMPLAINTTYPE6,
                        COMPLAINTTYPE7,
                        COMPLAINTREASON1,
                        COMPLAINTREASON2,
                        COMPLAINTREASON,
                        CUSTOMTYPE,
                        CUSTOMBRAND,
                        CUSTOMLEVEL,
                        CUSTOMATTRIBUTION,
                        STARTDEALCITY,
                        CALLERNO,
                        CALLERREGISTVLR,
                        CALLERDIALUPTYPE,
                        CALLERISINTELLIGENTUSER,
                        CALLEDPARTYNO,
                        CALLEDPARTYREGISTVLR,
                        CALLEDPARTYCALLC,
                        MAINCOMPLETELIMITT1,
                        MAINCOMPLETELIMITT2,
                        MAINIFDEFERRESOLVE,
                        MAINIFRECORD, MAINQCREJECTTIMES, SENDYEAR, SENDMONTH, SENDDAY, maindelayflag, mainlastrepeattime
--bussines link
    , LINKIFINVOKECHANGE, LINKFAULTSTARTTIME, LINKFAULTENDTIME, LINKFAULTGENERANTPLACE, NDEPTCONTACT, NDEPTCONTACTPHONE,
                        DEALRESULT, ISSUEELIMINATTIME, LINKCHECKRESULT, LINKIFDEFERRESOLVE, LINKIFINVOKECASEDATABASE,
                        LINKCHANGESHEETID, OPERATEYEAR, OPERATEMONTH, OPERATEDAY
    ) as
    (
--已处理
        select ----est_stat---
               2::integer est_data_status,
               srole.roleid send_bigrole_id,
               case when task.operatetype = 'subrole' then orole.roleid else null::integer end operate_bigrole_id,
            sd.linkid send_dept_level,
            od.linkid operate_dept_level,
--common main
            main.id MAINID, main.SHEETID, main.TITLE, main.SHEETACCEPTLIMIT, main.SHEETCOMPLETELIMIT, main.SENDTIME, main.SENDUSERID, main.SENDDEPTID,
            main.SENDROLEID, main.SENDCONTACT, main.STATUS, main.HOLDSTATISFIED, main.ENDTIME, main.ENDUSERID, main.ENDDEPTID, main.ENDROLEID,
            main.DELETED, main.PIID, main.PARENTSHEETNAME, main.PARENTSHEETID, main.SHEETTEMPLATENAME, main.SHEETACCESSORIES,
            main.TODEPTID, main.CANCELREASON,
--common link
            link.ID linkid,
            task.ACCEPTTIMELIMIT NODEACCEPTLIMIT,
            task.COMPLETETIMELIMIT NODECOMPLETELIMIT,
            link.OPERATETYPE, link.OPERATETIME, link.OPERATEUSERID, link.OPERATEDEPTID, link.OPERATEROLEID, link.TOORGTYPE, link.TOORGUSERID,
            link.TOORGDEPTID, link.TOORGROLEID, link.ACCEPTFLAG, link.COMPLETEFLAG, link.PRELINKID, link.PARENTLINKID, link.FIRSTLINKID,
            link.AIID, link.ACTIVETEMPLATEID, link.NODETEMPLATENAME, link.NODEACCESSORIES, link.OPERATEORGTYPE,

--common task
            task.SUBTASKFLAG, task.PARENTTASKID, task.taskstatus, task.operatetype taskoperatetype,
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
            main.MAINCOMPLETELIMITT2,
            main.MAINIFDEFERRESOLVE,
            main.MAINIFRECORD
                , main.MAINQCREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
--bussines link
                , main.maindelayflag
                , main.mainlastrepeattime
                , link.LINKIFINVOKECHANGE
                , link.LINKFAULTSTARTTIME
                , link.LINKFAULTENDTIME
                , link.LINKFAULTGENERANTPLACE
                , link.NDEPTCONTACT
                , link.NDEPTCONTACTPHONE
                , link.DEALRESULT
                , link.ISSUEELIMINATTIME
                , link.LINKCHECKRESULT
                , link.LINKIFDEFERRESOLVE
                , link.LINKIFINVOKECASEDATABASE
                , link.LINKCHANGESHEETID
                , link.OPERATEYEAR
                , link.OPERATEMONTH
                , link.OPERATEDAY

        from complaint_main main
            join complaint_link link
        on main.id= link.mainid and main.templateflag=0 and link.TEMPLATEFLAG=0
            left join complaint_task task on link.aiid=task.id
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
            main.SENDROLEID, main.SENDCONTACT, main.STATUS, main.HOLDSTATISFIED, main.ENDTIME, main.ENDUSERID, main.ENDDEPTID, main.ENDROLEID,
            main.DELETED, main.PIID, main.PARENTSHEETNAME, main.PARENTSHEETID, main.SHEETTEMPLATENAME, main.SHEETACCESSORIES,
            main.TODEPTID, main.CANCELREASON,
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
            main.MAINCOMPLETELIMITT2,
            main.MAINIFDEFERRESOLVE,
            main.MAINIFRECORD
                , main.MAINQCREJECTTIMES
                , main.SENDYEAR
                , main.SENDMONTH
                , main.SENDDAY
                , main.maindelayflag
                , main.mainlastrepeattime
--bussines link

                , null :: varchar (255) LINKIFINVOKECHANGE
                , null ::datetime year to second LINKFAULTSTARTTIME
                , null ::datetime year to second LINKFAULTENDTIME
                , null :: varchar (255) LINKFAULTGENERANTPLACE
                , null :: varchar (255) NDEPTCONTACT
                , null :: varchar (255) NDEPTCONTACTPHONE
                , null :: varchar (255) DEALRESULT
                , null ::datetime year to second ISSUEELIMINATTIME
                , null :: varchar (255) LINKCHECKRESULT
                , null :: varchar (255) LINKIFDEFERRESOLVE
                , null :: varchar (255) LINKIFINVOKECASEDATABASE
                , null :: varchar (255) LINKCHANGESHEETID
                , null :: integer OPERATEYEAR
                , null :: integer OPERATEMONTH
                , null :: integer OPERATEDAY

        from complaint_main main
            join complaint_task task
        on main.templateflag=0 and main.id=task.sheetkey and task.taskstatus!=5 and main.templateflag=0
            left join taw_system_user ud on task.taskowner!=task.operateroleid and task.taskowner=ud.userid
            left join taw_system_sub_role orole on orole.id=task.operateroleid
            left join taw_system_sub_role srole on srole.id= main.sendroleid
            left join taw_system_userrefrole refr on refr.subroleid = task.operateroleid
            left join taw_system_user u on u.userid = refr.userid
            join taw_system_dept sd on main.senddeptid=sd.deptid
            join taw_system_dept od on u.deptid=od.deptid
    )



insert into est_last_oper
values (52, '2007-07-07 00:00:00', '投诉处理流程流基础表采集的上一次操作的时间')


--中间表
CREATE TABLE est_complaint
(

    EST_DATA_STATUS          integer,
    send_bigrole_id          integer,
    operate_bigrole_id       integer,
    send_dept_level          varchar(30),
    operate_dept_level       varchar(30),
    MAINID                   varchar(255),
    SHEETID                  varchar(255),
    TITLE                    varchar(255),
    SHEETACCEPTLIMIT         datetime year to second,
    SHEETCOMPLETELIMIT       datetime year to second,
    SENDTIME                 datetime year to second,
    SENDUSERID               varchar(255),
    SENDDEPTID               varchar(255),
    SENDROLEID               varchar(255),
    SENDCONTACT              varchar(255),
    STATUS                   integer,
    HOLDSTATISFIED           integer,
    ENDTIME                  datetime year to second,
    ENDUSERID                varchar(255),
    ENDDEPTID                varchar(255),
    ENDROLEID                varchar(255),
    DELETED                  integer,
    PIID                     varchar(255),
    PARENTSHEETNAME          varchar(255),
    PARENTSHEETID            varchar(255),
    SHEETTEMPLATENAME        varchar(255),
    SHEETACCESSORIES         varchar(255),
    TODEPTID                 varchar(255),
    CANCELREASON             varchar(255),
    LINKID                   varchar(255),
    NODEACCEPTLIMIT          datetime year to second,
    NODECOMPLETELIMIT        datetime year to second,
    OPERATETYPE              varchar(100),
    OPERATETIME              datetime year to second,
    OPERATEUSERID            varchar(255),
    OPERATEDEPTID            varchar(255),
    OPERATEROLEID            varchar(255),
    TOORGTYPE                integer,
    TOORGUSERID              varchar(255),
    TOORGDEPTID              varchar(255),
    TOORGROLEID              varchar(255),
    ACCEPTFLAG               integer,
    COMPLETEFLAG             integer,
    PRELINKID                varchar(255),
    PARENTLINKID             varchar(255),
    FIRSTLINKID              varchar(255),
    AIID                     varchar(255),
    ACTIVETEMPLATEID         varchar(255),
    NODETEMPLATENAME         varchar(255),
    NODEACCESSORIES          varchar(255),
    OPERATEORGTYPE           varchar(30),
    SUBTASKFLAG              varchar(10),
    PARENTTASKID             varchar(50),
    TASKSTATUS               varchar(255),
    TASKOPERATETYPE          varchar(25),
    DEALTIME1                datetime year to second,
    DEALTIME2                datetime year to second,
    URGENTDEGREE             varchar(50),
    BTYPE1                   varchar(50),
    BDEPTCONTACT             varchar(50),
    CUSTOMERNAME             varchar(50),
    CUSTOMPHONE              varchar(50),
    COMPLAINTTIME            datetime year to second,
    FAULTTIME                datetime year to second,
    COMPLAINTADD             varchar(255),
    COMPLAINTDESC            varchar(255),
    BDEPTCONTACTPHONE        varchar(50),
    REPEATCOMPLAINTTIMES     varchar(50),
    COMPLAINTTYPE1           varchar(50),
    COMPLAINTTYPE2           varchar(50),
    COMPLAINTTYPE            varchar(50),
    COMPLAINTTYPE4           varchar(50),
    COMPLAINTTYPE5           varchar(50),
    COMPLAINTTYPE6           varchar(50),
    COMPLAINTTYPE7           varchar(50),
    COMPLAINTREASON1         varchar(50),
    COMPLAINTREASON2         varchar(50),
    COMPLAINTREASON          varchar(50),
    CUSTOMTYPE               varchar(50),
    CUSTOMBRAND              varchar(50),
    CUSTOMLEVEL              varchar(50),
    CUSTOMATTRIBUTION        varchar(255),
    STARTDEALCITY            varchar(255),
    CALLERNO                 varchar(255),
    CALLERREGISTVLR          varchar(255),
    CALLERDIALUPTYPE         varchar(255),
    CALLERISINTELLIGENTUSER  varchar(255),
    CALLEDPARTYNO            varchar(255),
    CALLEDPARTYREGISTVLR     varchar(255),
    CALLEDPARTYCALLC         varchar(255),
    MAINCOMPLETELIMITT1      datetime year to second,
    MAINCOMPLETELIMITT2      datetime year to second,
    MAINIFDEFERRESOLVE       varchar(50),
    MAINIFRECORD             integer,
    MAINQCREJECTTIMES        integer,
    SENDYEAR                 integer,
    SENDMONTH                integer,
    SENDDAY                  integer,
    MAINDELAYFLAG            varchar(5),
    mainlastrepeattime       datetime year to second,
    LINKIFINVOKECHANGE       varchar(50),
    LINKFAULTSTARTTIME       datetime year to second,
    LINKFAULTENDTIME         datetime year to second,
    LINKFAULTGENERANTPLACE   varchar(100),
    NDEPTCONTACT             varchar(50),
    NDEPTCONTACTPHONE        varchar(50),
    DEALRESULT               varchar(50),
    ISSUEELIMINATTIME        datetime year to second,
    LINKCHECKRESULT          varchar(50),
    LINKIFDEFERRESOLVE       varchar(50),
    LINKIFINVOKECASEDATABASE varchar(50),
    LINKCHANGESHEETID        varchar(50),
    OPERATEYEAR              integer,
    OPERATEMONTH             integer,
    OPERATEDAY               integer
)extent size 102400 next size 51200 lock mode row;


---建采集相关索引

create index index_complainttask_sheetkey on complaint_task
    (sheetkey) using btree  in indexdbs;
create index index_complaintlink_mainid on complaint_link
    (mainid) using btree  in indexdbs;
create index index_complaintlink_aiid on complaint_link
    (aiid) using btree  in indexdbs;


create index index_estcomplaint_mainid on est_complaint
    (mainid) using btree  in indexdbs;
create index index_estcomplaint_sendtime on est_complaint
    (sendtime) using btree  in indexdbs;
create index index_estcomplaint_endtime on est_complaint
    (endtime) using btree  in indexdbs;