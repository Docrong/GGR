<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html>

<body>

<html:form method="post" action="/Kmrecord/savemain">
<html:hidden property="strutsAction" value="1"/>
<html:hidden property="strutsAction" value="1"/>
<html:hidden property="id" />
<html:hidden property="roomId" />
<html:hidden property="flag" />
<html:hidden property="dutydate" />
<html:hidden property="dutyman" />
<html:hidden property="hander" />
<html:hidden property="receiver" />
<html:hidden property="starttimeDefined" />
<html:hidden property="endtimeDefined" />
<html:hidden property="starttime" />
<html:hidden property="endtime" />
<html:hidden property="dutyman" />
<html:hidden property="hander" />
<html:hidden property="receiver" />
<html:hidden property="dutycheck" />
<%--<%
String dutycheck = request.getAttribute("dutycheck").toString();
String dutychecksub = request.getAttribute("dutychecksub").toString();
String typemode = request.getAttribute("typemode").toString();
%>

--%><table border="0" width="100%" cellspacing="1" cellpadding="1" class="listTable" align=center>

<TR class="tr_show">
        <TD align="center" class="label"><bean:message key="TawRmRecord.accessories"/></TD>
        <TD colSpan="6">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height=90 SCROLLING=YES SRC='${app}/kmmanager/Kmdutyfile/dutyfile.do?WORKSERIAL=<bean:write name="kmrecordForm" property="id"/>'>
        </IFRAME>
        </TD>
</TR>

</TABLE>

</html:form>


</body>
</html>

