<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


    <title>My JSP 'index.jsp' starting page</title>

</head>

<body>
<form action="exportExcel">

    <!--<input type="hidden" id="sql" name="sql" value="select * from (select b.*, rownum rn from (select ID as ID, SHEETID as 工单号, TITLE as 标题, SHEETACCEPTLIMIT as 处理时限, SHEETCOMPLETELIMIT as 受理时限, SENDTIME as 派单时间, SENDUSERID as 派单人  from commonfault_main) b where rownum <= 500000) WHERE rn >= 0" />-->

    <!--<input type="hidden" id="sql" name="sql" value="select * from (select b.*, rownum rn from (select m.ID as ID, m.SHEETID as 工单号, m.TITLE as 标题, m.SHEETACCEPTLIMIT as 处理时限, m.SHEETCOMPLETELIMIT as 受理时限, m.SENDTIME as 派单时间, m.SENDUSERID as 派单人, m.SENDDEPTID as SENDDEPTID, m.SENDROLEID as SENDROLEID, m.SENDCONTACT as SENDCONTACT, m.ENDTIME as ENDTIME, m.ENDRESULT as ENDRESULT, m.STATUS as STATUS, l.REMARK as 备注, l.OPERATETYPE as OPERATETYPE, l.OPERATETIME as OPERATETIME, l.OPERATEUSERID as OPERATEUSERID, l.OPERATEDEPTID as OPERATEDEPTID, l.PIID as PIID, l.LINKFAULTRESPONSELEVEL as LINKFAULTRESPONSELEVEL from commonfault_main m, commonfault_link l where m.id = l.mainid) b where rownum <= 500000) WHERE rn >= 0" />-->

    <input type="hidden" id="sql" name="sql"
           value="SELECT m.ID as ID, m.SHEETID as 工单号, m.TITLE as 标题, m.SHEETACCEPTLIMIT as 处理时限, m.SHEETCOMPLETELIMIT as 受理时限, m.SENDTIME as 派单时间, m.SENDUSERID as 派单人, m.SENDDEPTID as SENDDEPTID, m.SENDROLEID as SENDROLEID, m.SENDCONTACT as SENDCONTACT, m.ENDTIME as ENDTIME, m.ENDRESULT as ENDRESULT, m.STATUS as STATUS, u.id as 用户ID, u.deptname as deptname, u.email as email, u.userid as userid, u.username as username FROM eomsv35.commonfault_main m,eomsv35.taw_system_user u WHERE  m.sendtime>Sysdate-1200 AND m.senduserid=u.userid"/>

    <input type="submit" value="提交"/>
</form>
</body>
</html>


