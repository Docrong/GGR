<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*"%>
<%
Vector planVect=(Vector)request.getAttribute("planVect");
String roomId=(String)request.getAttribute("roomId");
%>
<html>
<head>
<title>ѡ����ҵ��Ŀ</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0" class="clssclbar" >
<form name="form1" action="">
<table width="100%" align="center" height="100%">
    <tr>
      <td colspan="2"  align="center">��ҵ��Ŀ</td>
    </tr>
<%
if(planVect!=null && planVect.size()>0)
{
TawRmDefineTree  tawRmDefineTree=null;
for(Iterator itr=planVect.iterator();itr.hasNext();)
{
  tawRmDefineTree=(TawRmDefineTree)itr.next();
%>
    <tr>
      <td align="center">
        <a href="javascript:preview('<%=tawRmDefineTree.getNodeId()%>');"><%=tawRmDefineTree.getName()%></a>
      </td>
    </tr>
    <%}}else{%>
    <tr>
      <td>
        û��ֵ����ҵ�ƻ�!
      </td>
    </tr>
<%}%>
    </table>
</form>
</body>
<script language="javascript">
<!--
function preview(parent_id)
{
  window.open("preview.do?roomId=<%=roomId%>&parent_id="+parent_id+"&Action=<%=request.getAttribute("Action")%>");
}
-->
</script>
</html>
