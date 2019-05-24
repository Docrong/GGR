<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<SCRIPT LANGUAGE="JavaScript">
<!--
if(window.operner)
   window.opener.close();
// -->
function _click()
{
//var framedoc=document.frames(0).document;
//framedoc.forms[0].submit();
form1.submit();
}
</SCRIPT>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
</HEAD>
<BODY leftmargin="0" topmargin="0" class="clssclbar">

<FORM name="form1" action="saveRec.do" method="post">
<input type="hidden" name="parent_id" value="<%=request.getAttribute("parent_id")%>">
<input type="hidden" name="dutydate" value="<%=request.getAttribute("dutydate")%>">
<input type="hidden" name="roomId" value="<%=request.getAttribute("roomId")%>">
<input type="hidden" name="Cycle" value="<%=request.getAttribute("Cycle")%>">
<input type="hidden" name="History" value="<%=request.getAttribute("History")%>">
<input type="hidden" name="Summary" value="<%=request.getAttribute("Summary")%>">
<input type="hidden" name="deptId" value="<%=request.getAttribute("deptId")%>">
<input type="hidden" name="month" value="<%=request.getAttribute("month")%>">
<input type="hidden" name="deptName" value="<%=request.getAttribute("deptName")%>">
<input type="hidden" name="Action" value="<%=request.getAttribute("Action")%>">


<input type="hidden" name="workserial" value="<%=request.getAttribute("workserial")%>">

<input type="hidden" name="Action" value="<%=request.getAttribute("Action")%>">
<%--
<%String forward=!"View".equals(request.getAttribute("Action"))?"recordmain.do?WORKSERIAL=":"viewmain.do?id=";%>
--%>
<br>

<TABLE width=100% border="0"  cellspacing="1" cellpadding="1"  class="table_show" align="center" bgColor="#f3f3f3">
<TR>
     <td align="center" class="clsfth">
       <font size="3" color="red"><%=!"View".equals(request.getAttribute("Action"))?"填写":"查看"%>值班记录</font>
     </td>
</TR>
<%--
<TR>
        <TD colSpan="6" class="clsfth">
        <IFRAME id='iframe1' width="100%" FRAMEBORDER=0  height="280" SRC='../TawRmRecord/<%=forward+request.getAttribute("workserial")%>'>
        </IFRAME>
        </TD>
</TR>
--%>
</TABLE>

<table  width=100% border="0"  cellspacing="1" cellpadding="1"  class="table_show" align="center" bgColor="#f3f3f3">
<%=request.getAttribute("strTable")%>
</table>

<table width=100% border="0"  cellspacing="1" cellpadding="1"  align="center" >
<tr>
  <td align="center">
<%if(!"View".equals(request.getAttribute("Action"))){%>
   <input type="button" Class="clsbtn2" name="save" value="提交" onclick="_click();">
   <input type="reset" Class="clsbtn2" name="load" value="取消" >
    <%if("true".equals(request.getAttribute("Summary"))){%>
     <input type="button" Class="clsbtn2" name="back" value="返回" onclick="window.history.back();">
    <%}else{%>
       <input type="button" Class="clsbtn2" name="back" value="返回" onclick="form1.action='addDefRecord.do';form1.submit();">
    <%}%>
<%} else if("true".equals(request.getAttribute("Summary"))){%>
     <input type="button" Class="clsbtn2" name="back" value="返回" onclick="window.history.back();">
<%} else {%>
   <input type="button" Class="clsbtn2" name="back" value="返回" onclick="form1.action='addDefRecord.do';form1.submit();">
<%}%>
  </td>
</tr>
</table>
</FORM>
</BODY>
</HTML>
